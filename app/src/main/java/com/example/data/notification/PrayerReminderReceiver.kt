package com.example.data.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.data.local.SaatanDatabase
import com.example.data.model.AppLanguage
import com.example.data.model.NotificationCadence
import com.example.data.model.TraditionLayer
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

                // Gentle voice on the light Presence Rhythm skin (slice 2c):
                // the skin is already persisted, so the receiver needs no
                // app-run to pick it up.
                val lightSkin = TraditionLayer.fromKey(profile.traditionLayer).isLightSkin

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
                        // §9 "fréquence réduite si skips répétés" (slice 4):
                        // three quiet days for this moment = today's reminder
                        // steps aside — silently, re-arming as usual. Nothing
                        // is said, nothing is scored (Serment #2).
                        val reminderUsedLately = NotificationCadence.shouldShowDailyReminder(
                            journals = runCatching { dao.recentJournalsOnce() }.getOrDefault(emptyList()),
                            kind = kind,
                            todayIso = NotificationCadence.todayIso()
                        )
                        if (reminderUsedLately) {
                            DivineNotificationHelper.showPrayerNotification(
                                appContext,
                                kind,
                                language,
                                lightSkin
                            )
                        }
                        DivineNotificationHelper.schedulePrayerReminder(appContext, kind, nextAt)
                    }
                    // Weekly: next Friday, at the Jumu'ah hour (cached Dhuhr / 13:00).
                    DivineNotificationHelper.KIND_FRIDAY -> {
                        val nextAt = DivineNotificationHelper.nextFridayTrigger(zone, cache?.dhuhr, now)
                        if (nextAt == null) return@launch
                        DivineNotificationHelper.showPrayerNotification(
                            appContext,
                            DivineNotificationHelper.KIND_FRIDAY,
                            language,
                            lightSkin
                        )
                        DivineNotificationHelper.schedulePrayerReminder(
                            appContext,
                            DivineNotificationHelper.KIND_FRIDAY,
                            nextAt
                        )
                    }
                    // §9 (slice 4): Sunday-evening weekly report nudge. Light
                    // skin only, and only when the week holds data — a silent
                    // week is never nudged. Re-arms for next Sunday either way.
                    DivineNotificationHelper.KIND_WEEKLY_REPORT -> {
                        val nextAt = NotificationCadence.nextSundayEveningTrigger(now, zone)
                        if (nextAt == null) return@launch
                        val weekHasData = lightSkin && NotificationCadence.weekHasAnyData(
                            journals = runCatching { dao.recentJournalsOnce() }.getOrDefault(emptyList()),
                            todayIso = NotificationCadence.todayIso()
                        )
                        if (weekHasData) {
                            DivineNotificationHelper.showWeeklyReportNotification(appContext, language)
                        }
                        DivineNotificationHelper.schedulePrayerReminder(
                            appContext,
                            DivineNotificationHelper.KIND_WEEKLY_REPORT,
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
