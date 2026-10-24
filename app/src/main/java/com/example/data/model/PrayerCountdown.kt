package com.example.data.model

data class PrayerTimeItem(
    val key: String,
    val nameFr: String,
    val nameAr: String,
    val timeStr: String,
    val iconEmoji: String,
    val isNext: Boolean = false
)

data class PrayerCountdownState(
    val cityName: String = "Paris",
    val countryName: String = "France",
    val hijriDateFormatted: String = "1448 H",
    val nextPrayerNameFr: String = "Fajr",
    val nextPrayerNameAr: String = "الفجر",
    val nextPrayerTimeStr: String = "06:00",
    val nextPrayerEmoji: String = "🌅",
    val secondsUntilNextPrayer: Long = 0L,
    val formattedCountdown: String = "00h 00m 00s",
    val prayersList: List<PrayerTimeItem> = emptyList(),
    val isLoading: Boolean = false,
    val isUsingFallback: Boolean = false,
    /** True when the next prayer is tomorrow's Fajr (all of today's have passed). */
    val isNextPrayerTomorrow: Boolean = false
)
