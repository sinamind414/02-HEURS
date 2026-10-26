package com.example.data.notification

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Calendar
import java.util.TimeZone
import com.example.data.local.PrayerTimingsCacheEntity

/**
 * Contract of the per-prayer reminder scheduling maths (P1-3).
 *
 * The receivers re-arm tomorrow's adhan from the persisted timings snapshot,
 * so the "next occurrence" computation must be deterministic: a regressed
 * trigger would silently push the Fajr reminder into the wrong hour.
 */
class DivineReminderTriggerTest {

    // A fixed "now": Sunday 2026-06-14 12:00 Paris summer time.
    private val zone = TimeZone.getTimeZone("Europe/Paris")
    private val now = calendarNow(2026, Calendar.JUNE, 14, 12, 0)

    private fun calendarNow(year: Int, month: Int, day: Int, hour: Int, minute: Int): Long {
        val cal = Calendar.getInstance(zone).apply {
            clear()
            set(year, month, day, hour, minute, 0)
        }
        return cal.timeInMillis
    }

    private fun triggerTime(millis: Long): Calendar {
        val cal = Calendar.getInstance(zone)
        cal.timeInMillis = millis
        return cal
    }

    // ------------------------------------------------------------------
    // nextDailyTrigger
    // ------------------------------------------------------------------

    @Test
    fun fajrLaterTodayIsScheduledToday() {
        // now 2026-06-14 12:00, Fajr is long past: the trigger rolls to tomorrow.
        val at = DivineNotificationHelper.nextDailyTrigger(zone, "04:57", now)!!
        val cal = triggerTime(at)
        assertEquals(Calendar.JUNE, cal.get(Calendar.MONTH))
        assertEquals(15, cal.get(Calendar.DAY_OF_MONTH))
        assertEquals(4, cal.get(Calendar.HOUR_OF_DAY))
        assertEquals(57, cal.get(Calendar.MINUTE))
    }

    @Test
    fun dhuhrAheadOfNowIsScheduledToday() {
        // push "now" to 10:00 same Sunday: Dhuhr 13:30 is later today.
        val morning = calendarNow(2026, Calendar.JUNE, 14, 10, 0)
        val at = DivineNotificationHelper.nextDailyTrigger(zone, "13:30", morning)!!
        val cal = triggerTime(at)
        assertEquals(14, cal.get(Calendar.DAY_OF_MONTH))
        assertEquals(13, cal.get(Calendar.HOUR_OF_DAY))
    }

    @Test
    fun exactBoundaryRollsToTomorrow() {
        val exactlyFajr = calendarNow(2026, Calendar.JUNE, 14, 4, 57)
        val at = DivineNotificationHelper.nextDailyTrigger(zone, "04:57", exactlyFajr)!!
        assertEquals(15, triggerTime(at).get(Calendar.DAY_OF_MONTH))
    }

    @Test
    fun unparsableTimesReturnNull() {
        assertNull(DivineNotificationHelper.nextDailyTrigger(zone, "", now))
        assertNull(DivineNotificationHelper.nextDailyTrigger(zone, "25:00", now))
        assertNull(DivineNotificationHelper.nextDailyTrigger(zone, "06::27", now))
        assertNull(DivineNotificationHelper.nextDailyTrigger(zone, "aab", now))
    }

    @Test
    fun secondsAreAlwaysZeroedOut() {
        val at = DivineNotificationHelper.nextDailyTrigger(zone, "04:57", now)!!
        assertEquals(0, triggerTime(at).get(Calendar.SECOND))
        assertEquals(0, triggerTime(at).get(Calendar.MILLISECOND))
    }

    // ------------------------------------------------------------------
    // nextFridayTrigger
    // ------------------------------------------------------------------

    @Test
    fun fridayIsWithinSevenDaysAndAligned() {
        val dhuhr = "13:30"
        val at = DivineNotificationHelper.nextFridayTrigger(zone, dhuhr, now)!!
        val cal = triggerTime(at)
        assertEquals(Calendar.FRIDAY, cal.get(Calendar.DAY_OF_WEEK))
        assertEquals(13, cal.get(Calendar.HOUR_OF_DAY))
        assertEquals(30, cal.get(Calendar.MINUTE))
        assertTrue(at > now)
        assertTrue(at <= now + 7L * 24L * 60L * 60L * 1000L)
    }

    @Test
    fun fridayWithoutCacheFallsBackTo1300() {
        val at = DivineNotificationHelper.nextFridayTrigger(zone, null, now)!!
        val cal = triggerTime(at)
        assertEquals(Calendar.FRIDAY, cal.get(Calendar.DAY_OF_WEEK))
        assertEquals(13, cal.get(Calendar.HOUR_OF_DAY))
        assertEquals(0, cal.get(Calendar.MINUTE))
    }

    @Test
    fun fridayWhenTonightIsFridayRollsToNextWeek() {
        // 2026-06-19 is a Friday; at 15:00 (past 13:00) the reminder must land
        // on the FOLLOWING Friday, not fire 2 hours after the fact.
        val fridayAfterNoon = calendarNow(2026, Calendar.JUNE, 19, 15, 0)
        val at = DivineNotificationHelper.nextFridayTrigger(zone, "13:30", fridayAfterNoon)!!
        val cal = triggerTime(at)
        assertEquals(Calendar.FRIDAY, cal.get(Calendar.DAY_OF_WEEK))
        assertTrue(at >= fridayAfterNoon + 6L * 24L * 60L * 60L * 1000L)
        assertTrue(at <= fridayAfterNoon + 8L * 24L * 60L * 60L * 1000L)
    }

    // ------------------------------------------------------------------
    // cityZone / staleness
    // ------------------------------------------------------------------

    @Test
    fun zoneComesFromTheSnapshot() {
        val cache = PrayerTimingsCacheEntity(timezoneId = "Europe/Paris")
        assertEquals("Europe/Paris", DivineNotificationHelper.cityZone(cache).id)
    }

    @Test
    fun utcOffsetFallsBackWhenTimezoneIdIsBlank() {
        val cache = PrayerTimingsCacheEntity(timezoneId = "", utcOffsetSeconds = 19800)
        // 19800 s = 5 h 30 m; the JVM normalises the offset to zero-padded
        // "GMT+05:30" style ids.
        assertTrue(
            DivineNotificationHelper.cityZone(cache).id.startsWith("GMT+05:30")
        )
    }

    @Test
    fun unknownZoneFallsBackToDevice() {
        // getTimeZone silently answers GMT for nonsensical ids — the resolver
        // must reject the spoof instead of scheduling everything on GMT.
        val cache = PrayerTimingsCacheEntity(timezoneId = "Mars/Olympus")
        assertEquals(TimeZone.getDefault().id, DivineNotificationHelper.cityZone(cache).id)
    }

    @Test
    fun nullCacheFallsBackToDevice() {
        assertEquals(TimeZone.getDefault().id, DivineNotificationHelper.cityZone(null).id)
    }

    @Test
    fun stalenessWindowCoversSevenDays() {
        val sevenDays = 7L * 24L * 60L * 60L * 1000L
        val fresh = PrayerTimingsCacheEntity(savedAtMillis = now)
        val stale = PrayerTimingsCacheEntity(savedAtMillis = now - sevenDays - 1)
        assertTrue((now - fresh.savedAtMillis) <= DivineNotificationHelper.TIMINGS_CACHE_MAX_AGE_MS)
        assertFalse((now - stale.savedAtMillis) <= DivineNotificationHelper.TIMINGS_CACHE_MAX_AGE_MS)
    }
}
