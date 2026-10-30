package com.example.data.model

import com.example.data.local.DailyJournalEntity

/**
 * Monthly Presence Legacy (Presence Rhythm, strategy §8): the light skin's
 * longer look back — four weeks of pauses kept and of the Inner Weather,
 * plus the quiet unlock of the Eternal Perspective (the contemplative
 * converter) after three to four weeks of returning.
 *
 * Counts only, never scores or compares (Serment #2): every week is shown
 * as a raw trace, no "better than last month", no percentage, no streak.
 *
 * Pure JVM: the unit tests feed it fixture rows directly.
 */
data class MonthlyWeek(
    val mondayIso: String,
    val sundayIso: String,
    val pausesKept: Int,
    val moodAfterCounts: Map<InnerWeather, Int>,
    val moodAfterTotal: Int,
    val topMomentKey: String?,
    val topMomentCount: Int,
    val hasAnyData: Boolean
)

data class MonthlyReport(
    /** Up to four weeks, oldest first. */
    val weeks: List<MonthlyWeek>,
    val totalPauses: Int,
    val weeksUsed: Int,
    /** Three or more weeks of returning unlocks the Eternal Perspective. */
    val eternalPerspectiveUnlocked: Boolean
) {
    companion object {
        private const val MAX_WEEKS = 4
        private const val UNLOCK_WEEKS = 3

        fun build(recentJournals: List<DailyJournalEntity>, todayIso: String): MonthlyReport {
            val distinctMondays = recentJournals
                .map { WeeklyReport.currentWeekMondayIso(it.dateIso) }
                .distinct()
                .sorted()
                .takeLast(MAX_WEEKS)

            val weeks = distinctMondays.map { monday ->
                val wr = WeeklyReport.build(recentJournals, monday)
                MonthlyWeek(
                    mondayIso = wr.mondayIso,
                    sundayIso = wr.sundayIso,
                    pausesKept = wr.pausesKept,
                    moodAfterCounts = wr.moodAfterCounts,
                    moodAfterTotal = wr.moodAfterTotal,
                    topMomentKey = wr.topMomentKey,
                    topMomentCount = wr.topMomentCount,
                    hasAnyData = wr.hasAnyData
                )
            }

            val weeksUsed = weeks.count { it.hasAnyData }
            return MonthlyReport(
                weeks = weeks,
                totalPauses = weeks.sumOf { it.pausesKept },
                weeksUsed = weeksUsed,
                eternalPerspectiveUnlocked = weeksUsed >= UNLOCK_WEEKS
            )
        }
    }
}
