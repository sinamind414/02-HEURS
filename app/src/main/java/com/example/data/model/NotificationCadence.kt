package com.example.data.model

import com.example.data.local.DailyJournalEntity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * §9 notification cadence rules (slice 4): max 3–5 gentle prompts a day by
 * design (Fajr + Dhuhr + the 13:00 companion, plus Friday / the weekly
 * report on their single day — never more), never a "you missed" message,
 * the always-visible "Not now" exit, and a quiet frequency drop when the
 * reminders are not being used.
 *
 * The frequency drop is deliberately silent and guilt-free: nothing is
 * displayed, counted or announced — for that kind of day the reminder simply
 * does not appear. A user who never opened the app is not a "skipping" user,
 * so an empty history always keeps the reminder on.
 *
 * Pure JVM (Calendar + SimpleDateFormat only) so it is unit-tested without
 * any Android framework.
 */
object NotificationCadence {

    /**
     * How many days before today the look-back window spans. Three idle days
     * for this kind = the reminder takes a rest for the day.
     */
    const val SKIP_LOOKBACK_DAYS = 3

    /** The week's data threshold for the weekly report nudge: any moment. */
    fun weekHasAnyData(journals: List<DailyJournalEntity>, todayIso: String): Boolean {
        val mondayIso = WeeklyReport.currentWeekMondayIso(todayIso)
        return journals.any { row ->
            row.dateIso >= mondayIso && row.dateIso <= todayIso && (
                row.fajrDone || row.dhuhrDone || row.asrDone ||
                    row.maghribDone || row.ishaDone || row.dailyNote.isNotBlank()
                )
        }
    }

    /**
     * §9 "fréquence réduite si skips répétés", for the Fajr / Dhuhr kinds.
     * Looks at the [SKIP_LOOKBACK_DAYS] days before today: if rows exist for
     * that window and this moment was never marked on any of them, today's
     * reminder steps aside (existing reminders still re-arm for tomorrow).
     */
    fun shouldShowDailyReminder(
        journals: List<DailyJournalEntity>,
        kind: String,
        todayIso: String
    ): Boolean {
        val window = lastDatesBefore(todayIso, SKIP_LOOKBACK_DAYS)
        val rowsInWindow = journals.filter { row -> row.dateIso in window }
        // No history at all in the window: a fresh or dormant user is not a
        // skipping user — the reminder stays.
        if (rowsInWindow.isEmpty()) return true

        val flagForKind: (DailyJournalEntity) -> Boolean = when (kind) {
            "fajr" -> { row -> row.fajrDone }
            "dhuhr" -> { row -> row.dhuhrDone }
            else -> return true // Friday / weekly kinds have their own cadence
        }
        // A day without a row counts as unmarked, but only against kinds the
        // row would have covered with data — an unrelated quiet day (no row)
        // alone never silences a reminder.
        val everyDaySilent = window.all { date ->
            val row = rowsInWindow.firstOrNull { it.dateIso == date }
            row == null || !flagForKind(row)
        }
        return !everyDaySilent
    }

    /**
     * Next Sunday-evening trigger (strategy §8: the report is read "dimanche
     * soir / lundi matin") in [zone]. Today counts when the hour is still
     * ahead. Pure Calendar math, same shape as the adhan triggers.
     */
    fun nextSundayEveningTrigger(nowMillis: Long, zone: TimeZone, hour: Int = 19): Long? {
        val cal = Calendar.getInstance(zone).apply {
            timeInMillis = nowMillis
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        if (cal.timeInMillis <= nowMillis) {
            cal.add(Calendar.DAY_OF_YEAR, 1)
        }
        var guard = 0
        while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && guard < 7) {
            cal.add(Calendar.DAY_OF_YEAR, 1)
            guard++
        }
        if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) return null
        return cal.timeInMillis
    }

    /** The device-local ISO day, used by the alarm receivers. */
    fun todayIso(): String =
        SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())

    /** [count] ISO days strictly before [todayIso], oldest first. */
    private fun lastDatesBefore(todayIso: String, count: Int): List<String> {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val cal = runCatching {
            format.parse(todayIso)?.let { Calendar.getInstance().apply { clear(); time = it } }
        }.getOrNull() ?: Calendar.getInstance()
        return (count downTo 1).map { back ->
            val day = (cal.clone() as Calendar)
            day.add(Calendar.DAY_OF_YEAR, -back)
            format.format(day.time)
        }
    }
}
