package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Single-row snapshot of the last *successful* Aladhan fetch (the offline
 * fallback is never stored). It exists for one purpose: the reminder
 * scheduler. Alarm receivers run outside the app process — when the Fajr
 * alarm fires, `PrayerReminderReceiver` re-arms tomorrow's adhan from this
 * snapshot without performing any network call.
 *
 * The timings are strings in the city's wall clock ("06:27"); interpret them
 * always through [timezoneId], never the device timezone.
 */
@Entity(tableName = "prayer_timings_cache")
data class PrayerTimingsCacheEntity(
    @PrimaryKey val id: Int = 1,
    val city: String = "",
    val country: String = "",
    /** IANA id of the city's timezone, e.g. "Europe/Paris". Empty = device zone. */
    val timezoneId: String = "",
    /** Offset of the city relative to UTC, in seconds (fallback when timezoneId is blank). */
    val utcOffsetSeconds: Int = 0,
    /** Local date (yyyy-MM-dd, city timezone) the timings belong to. */
    val dateIso: String = "",
    /** System wall-clock time of the write — powers the 7-day staleness guard. */
    val savedAtMillis: Long = 0L,
    val fajr: String = "",
    val dhuhr: String = "",
    val asr: String = "",
    val maghrib: String = "",
    val isha: String = ""
)
