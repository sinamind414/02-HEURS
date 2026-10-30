package com.example.data.model

import com.example.data.local.DailyJournalEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Monthly Presence Legacy (slice 5): up to four weeks of pauses kept, the
 * quiet unlock of the Eternal Perspective after three to four weeks of
 * returning. Counts only — never scored, compared or streaked (Serment #2).
 */
class MonthlyReportTest {

    private fun row(iso: String, fajr: Boolean = false, dhuhr: Boolean = false, note: String = "") =
        DailyJournalEntity(dateIso = iso, fajrDone = fajr, dhuhrDone = dhuhr, dailyNote = note)

    @Test
    fun emptyHistoryIsSafeAndLocked() {
        val report = MonthlyReport.build(emptyList(), todayIso = "2026-10-30")
        assertTrue(report.weeks.isEmpty())
        assertEquals(0, report.totalPauses)
        assertEquals(0, report.weeksUsed)
        assertFalse(report.eternalPerspectiveUnlocked)
    }

    @Test
    fun oneWeekOfDataIsNotYetUnlocked() {
        val report = MonthlyReport.build(
            listOf(
                row("2026-10-26", fajr = true, dhuhr = true),
                row("2026-10-27", fajr = true)
            ),
            todayIso = "2026-10-30"
        )
        assertEquals(1, report.weeks.size)
        assertEquals(3, report.totalPauses)
        assertEquals(1, report.weeksUsed)
        assertFalse(report.eternalPerspectiveUnlocked)
    }

    @Test
    fun threeDistinctWeeksUnlockTheDeeperView() {
        val report = MonthlyReport.build(
            listOf(
                row("2026-10-05", fajr = true), // week Mon 2026-09-28? check: 2026-10-05 Monday
                row("2026-10-12", fajr = true),
                row("2026-10-19", fajr = true),
                row("2026-10-26", fajr = true)
            ),
            todayIso = "2026-10-30"
        )
        // 4 distinct weeks in window (capped at 4) → unlocked.
        assertTrue(report.weeks.size >= 3)
        assertTrue(report.eternalPerspectiveUnlocked)
        assertEquals(4, report.weeksUsed)
    }

    @Test
    fun garbageDatesDoNotCrash() {
        val report = MonthlyReport.build(
            listOf(row("not-a-date", fajr = true)),
            todayIso = "also bad"
        )
        // Folds into the current week — still a valid, safe report.
        assertTrue(report.weeks.size <= 4)
        assertTrue(report.totalPauses >= 0)
    }

    @Test
    fun capsAtFourWeeks() {
        val rows = listOf(
            row("2026-09-07", fajr = true),
            row("2026-09-14", fajr = true),
            row("2026-09-21", fajr = true),
            row("2026-09-28", fajr = true),
            row("2026-10-05", fajr = true),
            row("2026-10-12", fajr = true),
            row("2026-10-19", fajr = true),
            row("2026-10-26", fajr = true)
        )
        val report = MonthlyReport.build(rows, todayIso = "2026-10-30")
        assertTrue(report.weeks.size <= 4)
    }
}
