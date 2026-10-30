package com.example.data.model

import com.example.data.local.DailyJournalEntity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * Weekly Presence Report (Presence Rhythm slice 3, strategy §8).
 *
 * A small, honest, non-judging look back at the current week (Monday–Sunday):
 * how many pauses were kept, how the Inner Weather moved after the guided
 * pauses, and one light pattern ("counted, never scored" — Serment #2: no
 * percentage-vs-goal, no red/green, no streak, and the moods are counted as
 * raw traces exactly as logged, never weighted against each other).
 *
 * The whole computation is pure JVM (no Android imports): the unit tests feed
 * it fixture rows directly.
 */
data class WeeklyDayRow(
    val dateIso: String,
    /** 0 = Monday … 6 = Sunday. */
    val dayIndexFromMonday: Int,
    /** The five daily moments, in day order (fajr → isha), ticked or not. */
    val momentMarks: List<Boolean>,
    /** Inner Weather logged after a pause that day (null = not logged). */
    val moodAfter: InnerWeather?,
    /** The user wrote something in the light journal that day. */
    val hasNote: Boolean
)

data class WeeklyReport(
    /** ISO date of this week's Monday — also the "week key" of intentions. */
    val mondayIso: String,
    val sundayIso: String,
    val days: List<WeeklyDayRow>,
    /** Total moments marked across the week (0..35). */
    val pausesKept: Int,
    /** Inner Weather (after pause) raw counts — displayed, never scored. */
    val moodAfterCounts: Map<InnerWeather, Int>,
    /** Sum of all moods counted above. */
    val moodAfterTotal: Int,
    /** The moment of the day kept the most, if anything was kept. */
    val topMomentKey: String?,
    val topMomentCount: Int,
    /** False on a fresh install or an untouched week. */
    val hasAnyData: Boolean
) {
    companion object {
        /** Moment keys of the light home — same keys the return cards tick. */
        val LIGHT_MOMENT_KEYS = listOf("fajr", "dhuhr", "asr", "maghrib", "isha")

        private val isoFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

        /**
         * The Monday of the week containing [todayIso] — the stable key that
         * ties a gentle intention to the week it was made in. A mild anachronism
         * is harmless: a malformed date simply keys the current day.
         */
        fun currentWeekMondayIso(todayIso: String): String {
            val calendar = parseIso(todayIso)
            val daysFromMonday = ((calendar.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY) + 7) % 7
            calendar.add(Calendar.DAY_OF_YEAR, -daysFromMonday)
            return isoFormat.format(calendar.time)
        }

        private fun parseIso(dateIso: String): Calendar {
            val calendar = Calendar.getInstance()
            calendar.clear()
            val parsed = runCatching { isoFormat.parse(dateIso) }.getOrNull() ?: Date()
            calendar.time = parsed
            return calendar
        }

        /**
         * Aggregates the last journal rows into this week's report. Rows outside
         * the current Monday–Sunday window are ignored; missing days are empty.
         */
        fun build(recentJournals: List<DailyJournalEntity>, todayIso: String): WeeklyReport {
            val mondayCal = parseIso(currentWeekMondayIso(todayIso))
            val mondayIso = isoFormat.format(mondayCal.time)

            val byDate = recentJournals.associateBy { it.dateIso }

            val days = (0..6).map { index ->
                val dayCal = (mondayCal.clone() as Calendar)
                dayCal.add(Calendar.DAY_OF_YEAR, index)
                val dateIso = isoFormat.format(dayCal.time)
                val row = byDate[dateIso]
                WeeklyDayRow(
                    dateIso = dateIso,
                    dayIndexFromMonday = index,
                    momentMarks = listOf(
                        row?.fajrDone == true,
                        row?.dhuhrDone == true,
                        row?.asrDone == true,
                        row?.maghribDone == true,
                        row?.ishaDone == true
                    ),
                    moodAfter = row?.moodAfterPause
                        ?.takeIf { it.isNotBlank() }
                        ?.let { InnerWeather.fromKey(it) },
                    hasNote = !row?.dailyNote.isNullOrBlank()
                )
            }

            val pauses = days.sumOf { day -> day.momentMarks.count { it } }
            val moodCounts = linkedMapOf<InnerWeather, Int>()
            days.forEach { day ->
                day.moodAfter?.let { moodCounts[it] = (moodCounts[it] ?: 0) + 1 }
            }

            val momentCounts = IntArray(LIGHT_MOMENT_KEYS.size)
            days.forEach { day ->
                day.momentMarks.forEachIndexed { index, marked ->
                    if (marked) momentCounts[index]++
                }
            }
            var topKey: String? = null
            var topCount = 0
            momentCounts.forEachIndexed { index, count ->
                if (count > topCount) {
                    topCount = count
                    topKey = LIGHT_MOMENT_KEYS[index]
                }
            }

            val anyData = pauses > 0 || moodCounts.isNotEmpty() ||
                days.any { it.hasNote }

            return WeeklyReport(
                mondayIso = mondayIso,
                sundayIso = days.last().dateIso,
                days = days,
                pausesKept = pauses,
                moodAfterCounts = moodCounts,
                moodAfterTotal = moodCounts.values.sum(),
                topMomentKey = topKey,
                topMomentCount = topCount,
                hasAnyData = anyData
            )
        }
    }
}
