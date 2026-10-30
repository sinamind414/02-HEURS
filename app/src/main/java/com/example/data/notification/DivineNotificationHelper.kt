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
import com.example.data.model.NotificationCadence
import com.example.data.model.TraditionLayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import com.example.data.local.PrayerTimingsCacheEntity
import com.example.data.local.UserProfileEntity
import java.util.Calendar
import java.util.TimeZone
import kotlin.math.abs

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
                    // Light skin (slice 2c): the 13:00 reminder speaks the
                    // gentle voice — no "divine time" arithmetic in the copy.
                    val lightSkin = com.example.data.model.TraditionLayer
                        .fromKey(profile.traditionLayer).isLightSkin
                    val divineMinutes = currentDivineMinutes(profile.birthTimestampMs, profile.lifeExpectancyYears)
                    DivineNotificationHelper.showNotification(
                        context = appContext,
                        title = if (lightSkin) {
                            AppStrings.gentleDailyNotifTitle(language)
                        } else {
                            AppStrings.dailyReminderNotifTitle(language)
                        },
                        message = if (lightSkin) {
                            AppStrings.gentleDailyNotifBody(language)
                        } else {
                            AppStrings.dailyReminderNotifBody(language, divineMinutes)
                        },
                        language = language,
                        lightSkin = lightSkin
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

    fun createNotificationChannel(
        context: Context,
        language: AppLanguage = AppLanguage.FR,
        lightSkin: Boolean = false
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Re-creating a channel with the same ID updates its name/description:
            // switching skins also switches the settings label honestly.
            val channel = NotificationChannel(
                CHANNEL_ID,
                if (lightSkin) {
                    AppStrings.gentleChannelName(language)
                } else {
                    AppStrings.notificationChannelName(language)
                },
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = if (lightSkin) {
                    AppStrings.gentleChannelDescription(language)
                } else {
                    AppStrings.notificationChannelDescription(language)
                }
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
        language: AppLanguage = AppLanguage.FR,
        notificationId: Int = NOTIFICATION_ID,
        lightSkin: Boolean = false,
        openWeeklyReport: Boolean = false
    ) {
        createNotificationChannel(context, language, lightSkin)

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            if (openWeeklyReport) {
                putExtra(EXTRA_OPEN_WEEKLY_REPORT, true)
            }
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // §9 "sortie facile « Not now » toujours": every reminder carries the
        // action. It only dismisses — no snooze trick, no state change, the
        // alarm chain re-arms exactly as before.
        val notNowIntent = Intent(context, NotNowReceiver::class.java).apply {
            action = ACTION_NOT_NOW
            putExtra(EXTRA_NOT_NOW_NOTIFICATION_ID, notificationId)
        }
        val notNowPendingIntent = PendingIntent.getBroadcast(
            context,
            notificationId,
            notNowIntent,
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
            .addAction(0, AppStrings.notifNotNow(language), notNowPendingIntent)
            .setAutoCancel(true)

        try {
            val manager = NotificationManagerCompat.from(context)
            manager.notify(notificationId, builder.build())
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

    // ─────────────────────────────────────────────────────────────
    //  Per-prayer reminders — Fajr / Dhuhr / Friday (P1-3)
    // ─────────────────────────────────────────────────────────────
    //
    // Every kind owns its own PendingIntent (per request code) so arming the
    // Fajr alarm never replaces the Dhuhr one in place. The alarm receivers
    // work from the persisted `prayer_timings_cache` snapshot: a receiver
    // runs without the app and must be able to re-arm tomorrow's adhan
    // without any network call.

    const val EXTRA_REMINDER_KIND = "extra_reminder_kind"
    const val KIND_FAJR = "fajr"
    const val KIND_DHUHR = "dhuhr"
    const val KIND_FRIDAY = "friday"

    /**
     * §9 (slice 4): the Sunday-evening weekly report nudge. Light skin only —
     * the report itself only exists on the Presence Rhythm home.
     */
    const val KIND_WEEKLY_REPORT = "weekly_report"

    /** MainActivity extra: jump straight to the weekly report screen. */
    const val EXTRA_OPEN_WEEKLY_REPORT = "open_weekly_report"

    /** §9: the easy exit — dismisses the reminder, nothing else. */
    const val ACTION_NOT_NOW = "com.dhawq.app.action.NOT_NOW"
    const val EXTRA_NOT_NOW_NOTIFICATION_ID = "extra_not_now_notification_id"

    const val NOTIFICATION_ID_FAJR = 1011
    const val NOTIFICATION_ID_DHUHR = 1012
    const val NOTIFICATION_ID_FRIDAY = 1013
    const val NOTIFICATION_ID_WEEKLY_REPORT = 1014

    private const val REQUEST_CODE_FAJR = 1111
    private const val REQUEST_CODE_DHUHR = 1112
    private const val REQUEST_CODE_FRIDAY = 1113
    private const val REQUEST_CODE_WEEKLY_REPORT = 1114

    /**
     * How long a timings snapshot stays a valid re-arm basis. Beyond it the
     * seasonal drift of the adhan (up to ~1 min/day) makes tomorrow's
     * schedule a guess: the alarms stop and await a fresh fetch.
     */
    const val TIMINGS_CACHE_MAX_AGE_MS = 7L * 24L * 60L * 60L * 1000L

    /** Fires immediately (test button, alarm receiver) — ids differ per kind.
     * On the light Presence Rhythm skin the copy switches to the gentle,
     * tradition-free voices (slice 2c): same moments, same hours, no
     * religious vocabulary. The 988 crisis copy is never touched by this. */
    fun showPrayerNotification(
        context: Context,
        kind: String,
        language: AppLanguage,
        lightSkin: Boolean = false
    ) {
        when (kind) {
            KIND_FAJR -> showNotification(
                context,
                if (lightSkin) {
                    AppStrings.gentleFajrNotifTitle(language)
                } else {
                    AppStrings.reminderFajrNotifTitle(language)
                },
                if (lightSkin) {
                    AppStrings.gentleFajrNotifBody(language)
                } else {
                    AppStrings.reminderFajrNotifBody(language)
                },
                language,
                NOTIFICATION_ID_FAJR,
                lightSkin
            )
            KIND_DHUHR -> showNotification(
                context,
                if (lightSkin) {
                    AppStrings.gentleDhuhrNotifTitle(language)
                } else {
                    AppStrings.reminderDhuhrNotifTitle(language)
                },
                if (lightSkin) {
                    AppStrings.gentleDhuhrNotifBody(language)
                } else {
                    AppStrings.reminderDhuhrNotifBody(language)
                },
                language,
                NOTIFICATION_ID_DHUHR,
                lightSkin
            )
            else -> showNotification(
                context,
                if (lightSkin) {
                    AppStrings.gentleFridayNotifTitle(language)
                } else {
                    AppStrings.reminderFridayNotifTitle(language)
                },
                if (lightSkin) {
                    AppStrings.gentleFridayNotifBody(language)
                } else {
                    AppStrings.reminderFridayNotifBody(language)
                },
                language,
                NOTIFICATION_ID_FRIDAY,
                lightSkin
            )
        }
    }

    /**
     * The Sunday-evening weekly report nudge (§9 / slice 4): light skin only,
     * and only when the week actually holds data — a silent week stays
     * silent, "small data" is never nudged into existence. Tapping the
     * notification opens the report screen directly.
     */
    fun showWeeklyReportNotification(
        context: Context,
        language: AppLanguage
    ) {
        showNotification(
            context = context,
            title = AppStrings.weeklyNotifTitle(language),
            message = AppStrings.weeklyNotifBody(language),
            language = language,
            notificationId = NOTIFICATION_ID_WEEKLY_REPORT,
            lightSkin = true,
            openWeeklyReport = true
        )
    }

    fun schedulePrayerReminder(context: Context, kind: String, triggerAtMillis: Long) {
        if (triggerAtMillis <= 0L) return
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager ?: return
        val intent = Intent(context, PrayerReminderReceiver::class.java).apply {
            putExtra(EXTRA_REMINDER_KIND, kind)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            requestCodeFor(kind),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        try {
            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent)
        } catch (_: SecurityException) {
            // exact alarm permission restricted: the reminder is simply skipped
        }
    }

    fun cancelPrayerReminder(context: Context, kind: String) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager ?: return
        val intent = Intent(context, PrayerReminderReceiver::class.java).apply {
            putExtra(EXTRA_REMINDER_KIND, kind)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            requestCodeFor(kind),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
    }

    private fun requestCodeFor(kind: String): Int = when (kind) {
        KIND_FAJR -> REQUEST_CODE_FAJR
        KIND_DHUHR -> REQUEST_CODE_DHUHR
        KIND_WEEKLY_REPORT -> REQUEST_CODE_WEEKLY_REPORT
        else -> REQUEST_CODE_FRIDAY
    }

    /**
     * The one place reconciling the stored flags with the real AlarmManager
     * state, for all three per-prayer kinds. Called by the ViewModel (profile
     * change, toggles, fresh timings) and by the boot receiver; the alarm
     * receiver itself only re-arms its own next occurrence.
     */
    fun syncPrayerReminderAlarms(
        context: Context,
        profile: UserProfileEntity,
        cache: PrayerTimingsCacheEntity?
    ) {
        val now = System.currentTimeMillis()
        val masterOn = profile.notificationsEnabled
        val zone = cityZone(cache)
        val cacheUsable = cache != null && (now - cache.savedAtMillis) <= TIMINGS_CACHE_MAX_AGE_MS

        val fajrAt = nextDailyTrigger(zone, cache?.fajr.orEmpty(), now)
        if (masterOn && profile.reminderFajr && fajrAt != null && cacheUsable) {
            schedulePrayerReminder(context, KIND_FAJR, fajrAt)
        } else {
            cancelPrayerReminder(context, KIND_FAJR)
        }

        val dhuhrAt = nextDailyTrigger(zone, cache?.dhuhr.orEmpty(), now)
        if (masterOn && profile.reminderDhuhr && dhuhrAt != null && cacheUsable) {
            schedulePrayerReminder(context, KIND_DHUHR, dhuhrAt)
        } else {
            cancelPrayerReminder(context, KIND_DHUHR)
        }

        val fridayAt = nextFridayTrigger(zone, cache?.dhuhr, now)
        if (masterOn && profile.reminderFriday && fridayAt != null) {
            schedulePrayerReminder(context, KIND_FRIDAY, fridayAt)
        } else {
            cancelPrayerReminder(context, KIND_FRIDAY)
        }

        // §9 (slice 4): the weekly report nudge. Light skin only — the
        // tradition home has no report — and re-armed every reconciliation
        // (profile change, boot, fresh timings), self-re-arming on fire.
        val weeklyReportAt = NotificationCadence.nextSundayEveningTrigger(now, zone)
        val lightSkin = TraditionLayer.fromKey(profile.traditionLayer).isLightSkin
        if (masterOn && lightSkin && weeklyReportAt != null) {
            schedulePrayerReminder(context, KIND_WEEKLY_REPORT, weeklyReportAt)
        } else {
            cancelPrayerReminder(context, KIND_WEEKLY_REPORT)
        }
    }

    /**
     * The city's timezone carried by the snapshot — the reminder must ring at
     * the adhan of the selected city, not of wherever the device currently is.
     */
    fun cityZone(cache: PrayerTimingsCacheEntity?): TimeZone {
        val id = cache?.timezoneId.orEmpty().trim()
        if (id.isNotEmpty()) {
            val tz = TimeZone.getTimeZone(id)
            // getTimeZone silently returns GMT for unknown ids: only accept
            // the round-trip hits (e.g. "Europe/Paris", "Asia/Almaty").
            if (tz.id == id) return tz
        }
        val offsetSeconds = cache?.utcOffsetSeconds ?: 0
        if (offsetSeconds != 0) {
            val totalMinutes = offsetSeconds / 60
            val sign = if (totalMinutes >= 0) "+" else "-"
            val hh = abs(totalMinutes) / 60
            val mm = abs(totalMinutes) % 60
            val gmt = "GMT$sign$hh" + if (mm != 0) ":" + mm.toString().padStart(2, '0') else ""
            return TimeZone.getTimeZone(gmt)
        }
        return TimeZone.getDefault()
    }

    /**
     * Next occurrence of a daily adhan ("HH:mm") in [zone]: today if that hour
     * is still ahead, tomorrow otherwise. Null when the time is unparsable.
     */
    fun nextDailyTrigger(zone: TimeZone, timeStr: String, nowMillis: Long): Long? {
        val minutes = parseMinutes(timeStr) ?: return null
        val cal = Calendar.getInstance(zone).apply {
            timeInMillis = nowMillis
            set(Calendar.HOUR_OF_DAY, minutes / 60)
            set(Calendar.MINUTE, minutes % 60)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        if (cal.timeInMillis <= nowMillis) {
            cal.add(Calendar.DAY_OF_YEAR, 1)
        }
        return cal.timeInMillis
    }

    /**
     * Next Friday at the Jumu'ah hour: the city's cached Dhuhr when known,
     * 13:00 otherwise — so it does not blindly stack on the 13:00 daily
     * divine reminder.
     */
    fun nextFridayTrigger(zone: TimeZone, dhuhrTimeStr: String?, nowMillis: Long): Long? {
        val minutes = dhuhrTimeStr?.let { parseMinutes(it) } ?: 13 * 60
        val cal = Calendar.getInstance(zone).apply {
            timeInMillis = nowMillis
            set(Calendar.HOUR_OF_DAY, minutes / 60)
            set(Calendar.MINUTE, minutes % 60)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        if (cal.timeInMillis <= nowMillis) {
            cal.add(Calendar.DAY_OF_YEAR, 1)
        }
        var guard = 0
        while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY && guard < 7) {
            cal.add(Calendar.DAY_OF_YEAR, 1)
            guard++
        }
        if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) return null
        return cal.timeInMillis
    }

    private fun parseMinutes(timeStr: String): Int? {
        val parts = timeStr.trim().split(":")
        if (parts.size < 2) return null
        val h = parts[0].toIntOrNull() ?: return null
        val m = parts[1].toIntOrNull() ?: return null
        if (h !in 0..23 || m !in 0..59) return null
        return h * 60 + m
    }
}
