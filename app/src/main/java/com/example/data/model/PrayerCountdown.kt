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

/**
 * The "Cycle Quotidien" of the Dhawq experience, derived from the cached
 * prayer timings and the city's wall clock:
 *
 *  * **Le Vide** — during the ~15 minutes following a prayer's adhan the app
 *    steps aside: opening it shows a single word, "Va." (Serment #4).
 *  * **La Minute Précieuse** — between 10 and 45 minutes after the prayer,
 *    the "assise after the salam" is offered, once per prayer per session.
 *  * **La Fenêtre d'or** — the last third of the night (between Maghrib and
 *    Fajr), when supplication is most likely to be answered.
 */
data class PrayerCycleState(
    /** True while now is inside the prayer window (adhan → +15 min). */
    val inPrayerWindow: Boolean = false,
    /** French name of the prayer whose window is open ("Fajr"...). */
    val windowPrayerNameFr: String = "",
    /** Arabic name of the prayer whose window is open. */
    val windowPrayerNameAr: String = "",
    /** Lowercase key of the prayer whose window is open ("fajr"...). */
    val windowPrayerKey: String = "",
    /** True between +10 and +45 minutes after a prayer's adhan. */
    val offerPreciousMinute: Boolean = false,
    /** Lowercase key of the prayer the precious-minute offer belongs to. */
    val preciousMinutePrayerKey: String = "",
    /** True during the last third of the night (Maghrib → Fajr span). */
    val inGoldenWindow: Boolean = false
)
