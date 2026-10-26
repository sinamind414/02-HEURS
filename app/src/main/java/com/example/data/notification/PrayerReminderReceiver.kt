package com.example.data.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.data.local.SaatanDatabase
import com.example.data.model.AppLanguage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

/**
 * Fires the Fajr / Dhuhr / Friday reminders and re-arms its own next
 * occurrence, straight from the persisted timings snapshot — no network, no
 * running app required.
 *
 * When a flag is off the ViewModel has already cancelled the pending alarm,
 * so this receiver simply never fires; the checks on entry are a safety net
 * for profile changes made while the alarm was already in the queue.
 */
class PrayerReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val kind = intent?.getStringExtra(DivineNotificationHelper.EXTRA_REMINDER_KIND) ?: return
        val pendingResult = goAsync()
        val appContext = context.applicationContext

        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
            try {
                val dao = runCatching {
                    SaatanDatabase.getDatabase(appContext).journalDao()
                }.getOrNull() ?: return@launch
                val profile = dao.getUserProfile().firstOrNull() ?: return@launch
                val cache = dao.getTimingsCache().firstOrNull()
                val language = AppLanguage.fromCode(profile.languageCode)

                if (!profile.notificationsEnabled) {
                    DivineNotificationHelper.cancelPrayerReminder(appContext, kind)
                    return@launch
                }
                val flagOn = when (kind) {
                    DivineNotificationHelper.KIND_FAJR -> profile.reminderFajr
                    DivineNotificationHelper.KIND_DHUHR -> profile.reminderDhuhr
                    else -> profile.reminderFriday
                }
                if (!flagOn) {
                    DivineNotificationHelper.cancelPrayerReminder(appContext, kind)
                    return@launch
                }

                val now = System.currentTimeMillis()
                val zone = DivineNotificationHelper.cityZone(cache)

                when (kind) {
                    // Adhan hour reminder: fires from the snapshot, re-arms for tomorrow.
                    DivineNotificationHelper.KIND_FAJR,
                    DivineNotificationHelper.KIND_DHUHR -> {
                        val cacheFresh =
                            cache != null &&
                                (now - cache.savedAtMillis) <= DivineNotificationHelper.TIMINGS_CACHE_MAX_AGE_MS
                        val timeStr = when (kind) {
                            DivineNotificationHelper.KIND_FAJR -> cache?.fajr
                            else -> cache?.dhuhr
                        }
                        val nextAt = if (cacheFresh && timeStr != null) {
                            DivineNotificationHelper.nextDailyTrigger(zone, timeStr, now)
                        } else {
                            null
                        }
                        if (nextAt == null) {
                            // Seasonally drifted or missing snapshot: stop the
                            // chain and await a fresh fetch on next app open.
                            DivineNotificationHelper.cancelPrayerReminder(appContext, kind)
                            return@launch
                        }
                        DivineNotificationHelper.showPrayerNotification(appContext, kind, language)
                        DivineNotificationHelper.schedulePrayerReminder(appContext, kind, nextAt)
                    }
                    // Weekly: next Friday, at the Jumu'ah hour (cached Dhuhr / 13:00).
                    else -> {
                        val nextAt = DivineNotificationHelper.nextFridayTrigger(zone, cache?.dhuhr, now)
                        if (nextAt == null) return@launch
                        DivineNotificationHelper.showPrayerNotification(
                            appContext,
                            DivineNotificationHelper.KIND_FRIDAY,
                            language
                        )
                        DivineNotificationHelper.schedulePrayerReminder(
                            appContext,
                            DivineNotificationHelper.KIND_FRIDAY,
                            nextAt
                        )
                    }
                }
            } finally {
                pendingResult.finish()
            }
        }
    }
}
