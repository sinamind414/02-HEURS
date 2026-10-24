package com.example.data.notification

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.MainActivity
import com.example.R
import com.example.data.calculator.DivineTimeCalculator
import com.example.data.local.SaatanDatabase
import com.example.data.localization.AppStrings
import com.example.data.model.AppLanguage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.util.Calendar

/**
 * Fires the daily "divine time" reminder and re-arms itself for the next day.
 *
 * The alarm is re-scheduled from scratch on every fire instead of relying on
 * `setInexactRepeating(INTERVAL_DAY)`: the latter repeats on a fixed 86 400 000
 * ms interval, which drifts by an hour across a DST transition.
 */
class DailyDivineReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val pendingResult = goAsync()
        val appContext = context.applicationContext

        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
            try {
                val profile = runCatching {
                    SaatanDatabase.getDatabase(appContext).journalDao().getUserProfile().firstOrNull()
                }.getOrNull()

                val language = AppLanguage.fromCode(profile?.languageCode ?: AppLanguage.FR.code)

                if (profile != null && profile.notificationsEnabled && profile.reminderSocialScroll) {
                    val divineMinutes = currentDivineMinutes(profile.birthTimestampMs, profile.lifeExpectancyYears)
                    DivineNotificationHelper.showNotification(
                        context = appContext,
                        title = AppStrings.dailyReminderNotifTitle(language),
                        message = AppStrings.dailyReminderNotifBody(language, divineMinutes),
                        language = language
                    )
                    // Re-arm for the next day, using the freshly computed value.
                    DivineNotificationHelper.scheduleDailyReminder(appContext, divineMinutes, language)
                }
            } finally {
                pendingResult.finish()
            }
        }
    }

    private fun currentDivineMinutes(birthTimestampMs: Long, lifeExpectancyYears: Int): Double {
        val info = DivineTimeCalculator.calculateDivineTime(
            birthTimestampMs = birthTimestampMs,
            lifeExpectancyYears = lifeExpectancyYears
        )
        return info.divineHours * 60.0 + info.divineMinutes + (info.divineSeconds / 60.0)
    }
}

object DivineNotificationHelper {
    const val CHANNEL_ID = "saatan_divine_reminders"
    const val NOTIFICATION_ID = 1001
    private const val REQUEST_CODE = 1002
    private const val REMINDER_HOUR = 13

    fun createNotificationChannel(context: Context, language: AppLanguage = AppLanguage.FR) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                AppStrings.notificationChannelName(language),
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = AppStrings.notificationChannelDescription(language)
            }
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showNotification(
        context: Context,
        title: String,
        message: String,
        language: AppLanguage = AppLanguage.FR
    ) {
        createNotificationChannel(context, language)

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            // A dedicated monochrome drawable is required: the adaptive launcher
            // icon renders as an opaque white blob in the status bar.
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        try {
            val manager = NotificationManagerCompat.from(context)
            manager.notify(NOTIFICATION_ID, builder.build())
        } catch (_: SecurityException) {
            // POST_NOTIFICATIONS permission denied on Android 13+
        }
    }

    /**
     * Schedules the next 13:00 local reminder. The trigger is recomputed from
     * the wall clock every time, so the reminder stays at 13:00 across DST
     * changes and across daylight saving transitions.
     */
    fun scheduleDailyReminder(
        context: Context,
        divineMinutes: Double,
        language: AppLanguage = AppLanguage.FR
    ) {
        createNotificationChannel(context, language)

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager ?: return
        val intent = Intent(context, DailyDivineReminderReceiver::class.java).apply {
            putExtra("EXTRA_DIVINE_MINUTES", divineMinutes)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            REQUEST_CODE,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val triggerAt = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, REMINDER_HOUR)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            if (timeInMillis <= System.currentTimeMillis()) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }.timeInMillis

        try {
            // minSdk is 24, so `setAndAllowWhileIdle` is always available; the
            // old pre-Marshmallow branch could never run.
            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAt, pendingIntent)
        } catch (_: SecurityException) {
            // Exact alarm permission restricted: the reminder is simply skipped.
        }
    }

    fun cancelDailyReminder(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager ?: return
        val intent = Intent(context, DailyDivineReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            REQUEST_CODE,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
    }
}
