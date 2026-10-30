package com.example.data.model

import com.example.data.local.DailyJournalEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Calendar
import java.util.TimeZone

/**
 * §9 notification cadence (slice 4): the frequency quietly drops after
 * repeated non-use, an empty history never silences a reminder (a fresh or
 * dormant user is not a "skipping" user), the weekly nudge only fires on a
 * week that holds data, and the Sunday-evening trigger is exact across
 * weeks. Nothing here ever scores or compares the user (Serment #2).
 */
class NotificationCadenceTest {

    private fun row(
        iso: String,
        fajr: Boolean = false,
        dhuhr: Boolean = false,
        asr: Boolean = false,
        note: String = ""
    ) = DailyJournalEntity(
        dateIso = iso,
        fajrDone = fajr,
        dhuhrDone = dhuhr,
        asrDone = asr,
        dailyNote = note
    )

    // ── Frequency drop after repeated non-use ──

    @Test
    fun threeQuietDaysForAKindSilenceItsReminderOnly() {
        val journals = listOf(
            row("2026-11-02", dhuhr = true),                 // fajr quiet, dhuhr used
            row("2026-11-03"),                               // both quiet
            row("2026-11-04", fajr = false, dhuhr = false)   // both quiet
        )
        // Window for 2026-11-05: Nov 2–4. Fajr was never marked → step aside.
        assertFalse(
            NotificationCadence.shouldShowDailyReminder(journals, "fajr", "2026-11-05")
        )
        // Dhuhr was used once in the window → the reminder stays.
        assertTrue(
            NotificationCadence.shouldShowDailyReminder(journals, "dhuhr", "2026-11-05")
        )
    }

    @Test
    fun emptyOrSparseHistoryNeverSilencesAReminder() {
        // A fresh install: no rows at all.
        assertTrue(NotificationCadence.shouldShowDailyReminder(emptyList(), "fajr", "2026-11-05"))
        // Rows exist, but all older than the look-back window.
        val oldRows = listOf(row("2026-10-20", fajr = false), row("2026-10-21"))
        assertTrue(
            NotificationCadence.shouldShowDailyReminder(oldRows, "fajr", "2026-11-05")
        )
        // Only today's row exists so far — the window ignores today.
        assertTrue(
            NotificationCadence.shouldShowDailyReminder(
                listOf(row("2026-11-05", fajr = false)), "fajr", "2026-11-05"
            )
        )
    }

    @Test
    fun garbageDatesSurviveTheCadenceCheck() {
        val journals = listOf(row("not-a-date"), row("", fajr = false))
        assertTrue(
            NotificationCadence.shouldShowDailyReminder(journals, "fajr", "also bad")
        )
        assertFalse(NotificationCadence.weekHasAnyData(journals, "also bad"))
    }

    @Test
    fun unknownKindsKeepTheirReminderByDefault() {
        assertTrue(
            NotificationCadence.shouldShowDailyReminder(
                listOf(row("2026-11-02"), row("2026-11-03"), row("2026-11-04")),
                "friday",
                "2026-11-05"
            )
        )
    }

    // ── The weekly report nudge only fires on a week that holds data ──

    @Test
    fun weekHasAnyDataCountsOnlyTheCurrentWeek() {
        // Thursday 2026-11-05; its Monday is 2026-11-02.
        val lastWeekFull = row("2026-11-01", fajr = true, dhuhr = true) // previous Sunday
        assertFalse(
            NotificationCadence.weekHasAnyData(listOf(lastWeekFull), "2026-11-05")
        )
        // A note alone counts as usage — the report explains itself gently.
        val noteOnly = row("2026-11-03", note = "one line about today")
        assertTrue(
            NotificationCadence.weekHasAnyData(listOf(lastWeekFull, noteOnly), "2026-11-05")
        )
        // Any single ticked moment in the week counts.
        assertTrue(
            NotificationCadence.weekHasAnyData(
                listOf(row("2026-11-05", asr = true)),
                "2026-11-05"
            )
        )
        assertFalse(NotificationCadence.weekHasAnyData(emptyList(), "2026-11-05"))
    }

    // ── The Sunday-evening trigger (§8: dimanche soir / lundi matin) ──

    private fun gmt(spec: String): Calendar {
        val datePart = spec.split(" ")[0].split("-").map { it.toInt() }
        val timeParts = spec.split(" ")[1].split(":").map { it.toInt() }
        return Calendar.getInstance(TimeZone.getTimeZone("GMT")).apply {
            clear()
            set(
                datePart[0], datePart[1] - 1, datePart[2],
                timeParts[0], timeParts[1], timeParts.getOrElse(2) { 0 }
            )
        }
    }

    @Test
    fun nextSundayEveningTriggerRollsToTheComingSunday() {
        val zone = TimeZone.getTimeZone("GMT")
        // Wednesday noon → the same week's Sunday evening.
        assertEquals(
            gmt("2026-11-01 19:00").timeInMillis,
            NotificationCadence.nextSundayEveningTrigger(
                gmt("2026-10-28 12:00").timeInMillis, zone
            )
        )
        // Sunday 18:00 → still today, one hour ahead.
        assertEquals(
            gmt("2026-11-01 19:00").timeInMillis,
            NotificationCadence.nextSundayEveningTrigger(
                gmt("2026-11-01 18:00").timeInMillis, zone
            )
        )
        // Sunday evening just after 19:00 → next week's Sunday.
        assertEquals(
            gmt("2026-11-08 19:00").timeInMillis,
            NotificationCadence.nextSundayEveningTrigger(
                gmt("2026-11-01 19:00:01").timeInMillis, zone
            )
        )
        // Year-end crossing: Saturday 2027-01-02 → Sunday 2027-01-03.
        assertEquals(
            gmt("2027-01-03 19:00").timeInMillis,
            NotificationCadence.nextSundayEveningTrigger(
                gmt("2027-01-02 08:00").timeInMillis, zone
            )
        )
        assertNotNull(
            NotificationCadence.nextSundayEveningTrigger(0L, zone) // epoch: Thursday
        )
    }
}
