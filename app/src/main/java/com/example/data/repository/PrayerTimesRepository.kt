package com.example.data.repository

import com.example.data.model.PrayerCountdownState
import com.example.data.model.PrayerCycleState
import com.example.data.model.PrayerTimeItem
import com.example.data.model.RamadanNightState
import com.example.data.remote.AladhanClient
import com.example.data.remote.AladhanTimings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

/**
 * Fetches and caches the prayer times for a city.
 *
 * The timings returned by the API are expressed in the *city's* timezone, so
 * every comparison has to be made against the city's local wall clock — not
 * the device's.
 */
class PrayerTimesRepository {

    companion object {
        /** "Le Vide": minutes after the adhan during which the app steps aside. */
        const val PRAYER_WINDOW_MINUTES = 15

        /** "La Minute Précieuse": offered from this many minutes after the adhan. */
        const val PRECIOUS_MINUTE_MIN_MINUTES = 10

        /** ...and until this many minutes after the adhan. */
        const val PRECIOUS_MINUTE_MAX_MINUTES = 45
    }

    // The cache is written from Dispatchers.IO and read from the main thread
    // by the countdown ticker, hence @Volatile.
    @Volatile
    private var cachedTimings: AladhanTimings? = null

    @Volatile
    private var cachedHijriStr: String = "1448 H"

    @Volatile
    private var cachedCity: String = "Paris"

    @Volatile
    private var cachedCountry: String = "France"

    /** IANA timezone of the cached city, e.g. "Europe/Paris". */
    @Volatile
    private var cachedTimezone: String? = null

    /** Offset of the cached city relative to UTC, in seconds. */
    @Volatile
    private var cachedUtcOffsetSeconds: Int = 0

    /** True while the displayed timings are the built-in offline fallback. */
    @Volatile
    private var usingFallback: Boolean = false

    /** Local date (yyyy-MM-dd) the cached timings belong to. */
    @Volatile
    private var cachedDateIso: String? = null

    suspend fun fetchPrayerTimes(
        city: String = "Paris",
        country: String = "France"
    ): PrayerCountdownState = withContext(Dispatchers.IO) {
        cachedCity = city
        cachedCountry = country
        try {
            val response = AladhanClient.apiService.getTimingsByCity(city = city, country = country)
            val timings = response.data?.timings
            if (timings != null) {
                cachedTimings = timings
                cachedTimezone = response.data.meta?.timezone
                cachedUtcOffsetSeconds = response.data.meta?.timezoneOffset ?: 0
                cachedHijriStr = formatHijri(response.data.date?.hijri)
                cachedDateIso = todayIsoIn(cachedTimezone)
                usingFallback = false
                computeCountdownState(
                    timings = timings,
                    hijriStr = cachedHijriStr,
                    city = city,
                    country = country,
                    isFallback = false
                )
            } else {
                buildFallbackState(city, country)
            }
        } catch (_: Exception) {
            buildFallbackState(city, country)
        }
    }

    /**
     * Recomputes the countdown from the cached timings. The fallback state is
     * preserved: the dummy timings are never written into [cachedTimings], so
     * a single network failure does not destroy the real data and the UI keeps
     * showing that it is running on fallback values.
     */
    fun recalculateCurrentCountdown(
        city: String = cachedCity,
        country: String = cachedCountry
    ): PrayerCountdownState {
        val timings = cachedTimings
        return if (timings != null) {
            computeCountdownState(
                timings = timings,
                hijriStr = cachedHijriStr,
                city = city,
                country = country,
                isFallback = usingFallback
            )
        } else {
            buildFallbackState(city, country)
        }
    }

    /** True when the cached timings are the built-in offline fallback. */
    fun isUsingFallback(): Boolean = usingFallback

    /**
     * Ramadan season state parsed from the cached Hijri date string
     * (formatted as "21 Ramadan 1447 H" by [formatHijri]). Month matching is
     * tolerant of transliteration variants ("Ramadan", "Ramaḍān", رمضان).
     */
    fun ramadanNightState(): RamadanNightState {
        val hijri = cachedHijriStr.trim()
        if (hijri.isEmpty()) return RamadanNightState()

        val day = hijri.split(" ").firstOrNull()?.toIntOrNull() ?: 0
        val year = hijri.split(" ").lastOrNull { it.toIntOrNull() != null }?.toIntOrNull() ?: 0
        val isRamadan = hijri.contains("ramad", ignoreCase = true) || hijri.contains("رمضان")
        val isLastTen = isRamadan && day in 21..30

        return RamadanNightState(
            isRamadan = isRamadan,
            hijriDay = day,
            hijriYear = year,
            isLastTenNights = isLastTen,
            lastTenNightNumber = if (isLastTen) day else 0,
            isOddNight = isLastTen && day % 2 == 1
        )
    }

    /**
     * Computes the "Cycle Quotidien" state used by the Dhawq experience.
     * All thresholds are in minutes after the prayer's adhan, in the city's
     * wall clock (so the cycle stays correct when the device timezone differs
     * from the selected city):
     *  * 0..15   → "Le Vide": the app steps aside during the prayer.
     *  * 10..45  → "La Minute Précieuse" is offered (the assise after salam).
     *  * last third of the night (Maghrib → Fajr) → golden window badge.
     */
    fun currentCycleState(): PrayerCycleState {
        val timings = cachedTimings ?: return PrayerCycleState()
        val cal = cityCalendar()
        val nowMinutes = cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE)

        val prayers = listOf(
            Triple("fajr", "الفجر", cleanTime(timings.fajr)),
            Triple("dhuhr", "الظهر", cleanTime(timings.dhuhr)),
            Triple("asr", "العصر", cleanTime(timings.asr)),
            Triple("maghrib", "المغرب", cleanTime(timings.maghrib)),
            Triple("isha", "العشاء", cleanTime(timings.isha))
        )

        // Most recent prayer whose adhan has already passed today.
        var recentKey = ""
        var recentAr = ""
        var minutesSince = -1
        for ((key, arName, timeStr) in prayers) {
            val prayerMin = parseMinutesOfDay(timeStr) ?: continue
            val since = nowMinutes - prayerMin
            if (since in 0..1439 && since > minutesSince) {
                minutesSince = since
                recentKey = key
                recentAr = arName
            }
        }

        val inWindow = minutesSince in 0..PRAYER_WINDOW_MINUTES
        val offerMinute = minutesSince in PRECIOUS_MINUTE_MIN_MINUTES..PRECIOUS_MINUTE_MAX_MINUTES

        // Golden window: last third of the night, in "night coordinates"
        // measured from Maghrib (0) to the next Fajr (nightLength).
        var golden = false
        val maghribMin = parseMinutesOfDay(cleanTime(timings.maghrib))
        val fajrMin = parseMinutesOfDay(cleanTime(timings.fajr))
        if (maghribMin != null && fajrMin != null) {
            val nightLength = ((1440 - maghribMin) + fajrMin).coerceAtLeast(1)
            val posInNight = ((nowMinutes - maghribMin) + 1440) % 1440
            golden = posInNight >= 2 * nightLength / 3 && posInNight <= nightLength
        }

        val recentFr = when (recentKey) {
            "fajr" -> "Fajr"
            "dhuhr" -> "Dhuhr"
            "asr" -> "Asr"
            "maghrib" -> "Maghrib"
            "isha" -> "Isha"
            else -> ""
        }

        return PrayerCycleState(
            inPrayerWindow = inWindow,
            windowPrayerNameFr = if (inWindow) recentFr else "",
            windowPrayerNameAr = if (inWindow) recentAr else "",
            windowPrayerKey = if (inWindow) recentKey else "",
            offerPreciousMinute = offerMinute,
            preciousMinutePrayerKey = if (offerMinute) recentKey else "",
            inGoldenWindow = golden
        )
    }

    /**
     * The cached timings belong to a given local day. When that day is over the
     * cache is stale and has to be refreshed.
     */
    fun isCacheStale(): Boolean {
        val cached = cachedDateIso ?: return cachedTimings == null
        return cached != todayIsoIn(cachedTimezone)
    }

    private fun cleanTime(raw: String): String {
        return raw.trim().substringBefore(" ").trim()
    }

    private fun parseMinutesOfDay(timeStr: String): Int? {
        val cleaned = cleanTime(timeStr)
        val parts = cleaned.split(":")
        if (parts.size < 2) return null
        val h = parts[0].toIntOrNull() ?: return null
        val m = parts[1].toIntOrNull() ?: return null
        if (h !in 0..23 || m !in 0..59) return null
        return h * 60 + m
    }

    private fun formatHijri(hijri: com.example.data.remote.AladhanHijri?): String {
        if (hijri == null) return "1448 H"
        val day = hijri.date?.split("-")?.firstOrNull() ?: ""
        val month = hijri.month?.en ?: hijri.month?.ar ?: ""
        val year = hijri.year ?: "1448"
        return "$day $month $year H"
    }

    /**
     * Local date of the given timezone in the `yyyy-MM-dd` format.
     */
    private fun todayIsoIn(timezone: String?): String {
        val tz = timezone?.let { TimeZone.getTimeZone(it) } ?: TimeZone.getDefault()
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.US).apply { this.timeZone = tz }
        return format.format(java.util.Date())
    }

    /**
     * Current wall-clock time *in the city's timezone*.
     */
    private fun cityCalendar(): Calendar {
        val tz = cachedTimezone
            ?.takeIf { it.isNotBlank() }
            ?.let { TimeZone.getTimeZone(it) }
        val zone = when {
            tz != null && tz.id != "GMT" -> tz
            cachedUtcOffsetSeconds != 0 -> TimeZone.getTimeZone("GMT${cachedUtcOffsetSeconds}")
            else -> TimeZone.getDefault()
        }
        return Calendar.getInstance(zone)
    }

    private fun computeCountdownState(
        timings: AladhanTimings,
        hijriStr: String,
        city: String,
        country: String,
        isFallback: Boolean
    ): PrayerCountdownState {
        val cal = cityCalendar()
        val currentMinutesOfDay = cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE)
        val currentSeconds = cal.get(Calendar.SECOND)

        val fajrClean = cleanTime(timings.fajr)
        val sunriseClean = cleanTime(timings.sunrise)
        val dhuhrClean = cleanTime(timings.dhuhr)
        val asrClean = cleanTime(timings.asr)
        val maghribClean = cleanTime(timings.maghrib)
        val ishaClean = cleanTime(timings.isha)

        val prayerEntries = listOf(
            Triple("Fajr", "الفجر", fajrClean),
            Triple("Dhuhr", "الظهر", dhuhrClean),
            Triple("Asr", "العصر", asrClean),
            Triple("Maghrib", "المغرب", maghribClean),
            Triple("Isha", "العشاء", ishaClean)
        )

        // Find the next prayer of the day, or Fajr of the next day.
        var nextPrayer: Triple<String, String, String>? = null
        var diffMinutes = 0
        var isTomorrow = false

        for (entry in prayerEntries) {
            val prayerMin = parseMinutesOfDay(entry.third) ?: continue
            if (prayerMin > currentMinutesOfDay) {
                nextPrayer = entry
                diffMinutes = prayerMin - currentMinutesOfDay
                break
            }
        }

        // Every prayer of the day has passed: the next one is tomorrow's Fajr.
        if (nextPrayer == null) {
            nextPrayer = prayerEntries.first()
            val fajrMin = parseMinutesOfDay(nextPrayer.third) ?: 0
            diffMinutes = (1440 - currentMinutesOfDay) + fajrMin
            isTomorrow = true
        }

        val totalSecondsRemaining = maxOf(0L, (diffMinutes * 60L) - currentSeconds)
        val hoursRemaining = totalSecondsRemaining / 3600
        val minutesRemaining = (totalSecondsRemaining % 3600) / 60
        val secondsRemaining = totalSecondsRemaining % 60

        val formattedCountdown = String.format(
            Locale.US, "%02dh %02dm %02ds", hoursRemaining, minutesRemaining, secondsRemaining
        )

        val nextKey = nextPrayer.first

        val prayerList = listOf(
            PrayerTimeItem("fajr", "Fajr", "الفجر", fajrClean, "🌅", nextKey == "Fajr"),
            PrayerTimeItem("sunrise", "Chourouq", "الشروق", sunriseClean, "☀️", false),
            PrayerTimeItem("dhuhr", "Dhuhr", "الظهر", dhuhrClean, "🌞", nextKey == "Dhuhr"),
            PrayerTimeItem("asr", "Asr", "العصر", asrClean, "🌤️", nextKey == "Asr"),
            PrayerTimeItem("maghrib", "Maghrib", "المغرب", maghribClean, "🌇", nextKey == "Maghrib"),
            PrayerTimeItem("isha", "Isha", "العشاء", ishaClean, "🌙", nextKey == "Isha")
        )

        val nextEmoji = when (nextKey) {
            "Fajr" -> "🌅"
            "Dhuhr" -> "🌞"
            "Asr" -> "🌤️"
            "Maghrib" -> "🌇"
            else -> "🌙"
        }

        return PrayerCountdownState(
            cityName = city,
            countryName = country,
            hijriDateFormatted = hijriStr,
            nextPrayerNameFr = nextKey,
            nextPrayerNameAr = nextPrayer.second,
            nextPrayerTimeStr = nextPrayer.third,
            nextPrayerEmoji = nextEmoji,
            secondsUntilNextPrayer = totalSecondsRemaining,
            formattedCountdown = formattedCountdown,
            prayersList = prayerList,
            isLoading = false,
            isUsingFallback = isFallback,
            isNextPrayerTomorrow = isTomorrow
        )
    }

    /**
     * Builds the offline state. The dummy timings are deliberately *not* stored
     * in [cachedTimings]: doing so used to silently replace the real data and
     * reset the `isUsingFallback` flag one second later.
     */
    private fun buildFallbackState(city: String, country: String): PrayerCountdownState {
        val fallbackTimings = AladhanTimings(
            fajr = "06:15",
            sunrise = "07:45",
            dhuhr = "13:30",
            asr = "16:20",
            sunset = "19:00",
            maghrib = "19:05",
            isha = "20:30"
        )
        usingFallback = true
        return computeCountdownState(
            timings = fallbackTimings,
            hijriStr = "1448 H",
            city = city,
            country = country,
            isFallback = true
        )
    }
}
