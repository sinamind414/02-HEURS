package com.example.data.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.data.calculator.DivineTimeCalculator
import com.example.data.local.SaatanDatabase
import com.example.data.model.AppLanguage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

/**
 * Re-arms the daily divine reminder after a device reboot.
 *
 * `AlarmManager` alarms do not survive a reboot: without this receiver the
 * 13:00 reminder would silently die until the next time the user opens the
 * app (audit P1-2). Declared in the manifest with RECEIVE_BOOT_COMPLETED.
 */
class BootCompletedReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action != Intent.ACTION_BOOT_COMPLETED) return

        val pendingResult = goAsync()
        val appContext = context.applicationContext

        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
            try {
                val dao = runCatching {
                    SaatanDatabase.getDatabase(appContext).journalDao()
                }.getOrNull() ?: return@launch
                val profile = runCatching {
                    dao.getUserProfile().firstOrNull()
                }.getOrNull() ?: return@launch

                if (profile.notificationsEnabled && profile.reminderSocialScroll) {
                    val info = DivineTimeCalculator.calculateDivineTime(
                        birthTimestampMs = profile.birthTimestampMs,
                        lifeExpectancyYears = profile.lifeExpectancyYears
                    )
                    val divineMinutes =
                        info.divineHours * 60.0 + info.divineMinutes + (info.divineSeconds / 60.0)
                    DivineNotificationHelper.scheduleDailyReminder(
                        appContext,
                        divineMinutes,
                        AppLanguage.fromCode(profile.languageCode)
                    )
                }

                // Per-prayer reminders (P1-3): alarms do not survive a reboot.
                // Re-armed from the persisted timings snapshot, offline.
                val cache = runCatching { dao.getTimingsCache().firstOrNull() }.getOrNull()
                DivineNotificationHelper.syncPrayerReminderAlarms(appContext, profile, cache)
            } finally {
                pendingResult.finish()
            }
        }
    }
}
