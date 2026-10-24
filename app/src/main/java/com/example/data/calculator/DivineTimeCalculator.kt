package com.example.data.calculator

import com.example.data.localization.AppStrings
import com.example.data.model.AppLanguage
import com.example.data.model.DivineTimeInfo
import com.example.data.model.LifeMilestone
import com.example.data.model.QuranSpiritualQuote
import com.example.data.model.TwoDivineHoursProgress
import java.util.Locale
import kotlin.math.floor
import kotlin.math.max

/**
 * The theological premise of the application (Surah Al-Hajj 22:47):
 *
 *   1000 earth years  =  24 divine hours  =  86 400 divine seconds
 *
 * Everything below is a pure function of that single constant, so the whole
 * domain layer stays deterministic and unit testable.
 */
object DivineTimeCalculator {

    // ----- The single source of truth -------------------------------------
    const val EARTH_YEARS_PER_DIVINE_DAY = 1000.0
    const val DIVINE_HOURS_PER_DAY = 24.0
    const val DAYS_PER_EARTH_YEAR = 365.2425
    const val DAYS_PER_EARTH_MONTH = 30.4375
    const val MS_PER_DAY = 86400000.0
    const val DIVINE_SECONDS_PER_DAY = 86400.0

    /** 1 earth year = 86.4 divine seconds. */
    const val DIVINE_SECONDS_PER_EARTH_YEAR =
        (DIVINE_HOURS_PER_DAY * 3600.0) / EARTH_YEARS_PER_DIVINE_DAY

    /** 1 earth day = 0.236554 divine seconds. */
    const val DIVINE_SECONDS_PER_EARTH_DAY =
        DIVINE_SECONDS_PER_EARTH_YEAR / DAYS_PER_EARTH_YEAR

    /** 1 earth hour = 0.009856 divine seconds. */
    const val DIVINE_SECONDS_PER_EARTH_HOUR =
        DIVINE_SECONDS_PER_EARTH_DAY / 24.0

    /** 1 earth minute = 0.0001643 divine seconds. */
    const val DIVINE_SECONDS_PER_EARTH_MINUTE =
        DIVINE_SECONDS_PER_EARTH_HOUR / 60.0

    /**
     * Inverse conversion: 1 divine second = 365 242.5 / 86 400 ≈ 4.2273 earth days.
     *
     * NOTE: the "11.57 earth days" figure that used to be displayed all over the
     * app was simply wrong — it is the value of `1000 / 86400` (earth *years* per
     * divine second) mistakenly multiplied by 1000 a second time. The UI now
     * uses this constant, so the banner and the maths can no longer drift apart.
     */
    const val EARTH_DAYS_PER_DIVINE_SECOND =
        (EARTH_YEARS_PER_DIVINE_DAY * DAYS_PER_EARTH_YEAR) / DIVINE_SECONDS_PER_DAY

    /** 1 divine hour = 1000 / 24 ≈ 41.67 earth years. */
    const val EARTH_YEARS_PER_DIVINE_HOUR = EARTH_YEARS_PER_DIVINE_DAY / DIVINE_HOURS_PER_DAY

    /** 2 divine hours (the whole life) = 1000 / 12 ≈ 83.33 earth years. */
    const val EARTH_YEARS_PER_TWO_DIVINE_HOURS = EARTH_YEARS_PER_DIVINE_DAY / 12.0

    /** Total duration of a "2 divine hours" life, in divine minutes. */
    const val DIVINE_MINUTES_PER_TWO_HOURS = 120.0

    /** Reference lifespan used for "what share of a life" style statements. */
    const val REFERENCE_LIFESPAN_YEARS = 80.0

    /**
     * Five daily prayers, five minutes of ritual time each: 25 earth minutes
     * per day. This is the *only* figure the "prayer budget" is built from, so
     * the breakdown chart, the shock card and the challenges cannot drift.
     */
    const val DAILY_PRAYER_EARTH_MINUTES = 25.0

    /**
     * Ritual prayer across [REFERENCE_LIFESPAN_YEARS] of life, in divine
     * minutes. 80 x 365.2425 x 25 earth minutes = 730 485 earth minutes, which
     * is ≈ 2.00 divine minutes — about 1.7 % of the 120 divine minutes.
     */
    const val LIFETIME_PRAYER_DIVINE_MINUTES =
        REFERENCE_LIFESPAN_YEARS * DAYS_PER_EARTH_YEAR * DAILY_PRAYER_EARTH_MINUTES *
            DIVINE_SECONDS_PER_EARTH_MINUTE / 60.0

    /** Share of the two divine hours spent praying, in percent (≈ 1.67 %). */
    const val LIFETIME_PRAYER_PERCENT =
        LIFETIME_PRAYER_DIVINE_MINUTES / DIVINE_MINUTES_PER_TWO_HOURS * 100.0

    // ---------------------------------------------------------------------
    // Core conversion
    // ---------------------------------------------------------------------

    /**
     * Converts the time lived since [birthTimestampMs] into divine time.
     *
     * @param lifeExpectancyYears total lifespan used as the denominator of the
     *        "percentage lived" gauge. A value of 0 is tolerated and yields 0 %.
     */
    fun calculateDivineTime(
        birthTimestampMs: Long,
        lifeExpectancyYears: Int = 75,
        currentTimestampMs: Long = System.currentTimeMillis(),
        lang: AppLanguage = AppLanguage.FR
    ): DivineTimeInfo {
        val ageMs = max(0L, currentTimestampMs - birthTimestampMs)
        val ageDays = ageMs / MS_PER_DAY
        val ageInYears = ageDays / DAYS_PER_EARTH_YEAR

        // Age elapsed, expressed in divine time.
        val divineHoursTotal = (ageInYears / EARTH_YEARS_PER_DIVINE_DAY) * DIVINE_HOURS_PER_DAY
        val divineHours = floor(divineHoursTotal).toInt()
        val divineMinutes = floor((divineHoursTotal * 60.0) % 60.0).toInt()
        val totalSeconds = (divineHoursTotal * 3600.0) % 60.0
        val divineSeconds = floor(totalSeconds).toInt()
        val divineMillis = floor((totalSeconds - divineSeconds) * 1000.0).toInt()

        // Total lifespan expressed in divine time.
        val totalDivineLifeHours =
            (lifeExpectancyYears / EARTH_YEARS_PER_DIVINE_DAY) * DIVINE_HOURS_PER_DAY
        val totalLifeSec = max(0.0, totalDivineLifeHours * 3600.0)
        val totalLifeH = floor(totalDivineLifeHours).toInt()
        val totalLifeM = floor((totalDivineLifeHours * 60.0) % 60.0).toInt()
        val totalLifeS = floor(totalLifeSec % 60.0).toInt()

        // Guard against a zero (or negative) expectancy: the naive division
        // produced NaN / Infinity, which silently poisoned the progress bars.
        val percentageLived = if (totalDivineLifeHours <= 0.0) {
            0f
        } else {
            ((divineHoursTotal / totalDivineLifeHours) * 100.0).toFloat().coerceIn(0f, 100f)
        }

        val remainingSecTotal = max(0.0, totalLifeSec - (divineHoursTotal * 3600.0))
        val remH = floor(remainingSecTotal / 3600.0).toInt()
        val remM = floor((remainingSecTotal % 3600.0) / 60.0).toInt()
        val remS = floor(remainingSecTotal % 60.0).toInt()

        // Earth age breakdown. The remainder is derived from the days left after
        // the whole years, which keeps it consistent with DAYS_PER_EARTH_YEAR.
        val earthYearsInt = floor(ageInYears).toInt().coerceAtLeast(0)
        val daysAfterYears = ageDays - (earthYearsInt * DAYS_PER_EARTH_YEAR)
        val earthMonthsInt = floor(daysAfterYears / DAYS_PER_EARTH_MONTH).toInt().coerceIn(0, 11)
        val earthDaysInt =
            floor(daysAfterYears - (earthMonthsInt * DAYS_PER_EARTH_MONTH)).toInt().coerceAtLeast(0)

        val formattedDivine = String.format(
            Locale.US, "%02d:%02d:%02d.%01d", divineHours, divineMinutes, divineSeconds, divineMillis / 100
        )
        val formattedTotalLife = String.format(Locale.US, "%02d:%02d:%02d", totalLifeH, totalLifeM, totalLifeS)

        return DivineTimeInfo(
            earthAgeYears = ageInYears,
            earthDaysLived = ageDays.toLong(),
            earthYearsInt = earthYearsInt,
            earthMonthsInt = earthMonthsInt,
            earthDaysInt = earthDaysInt,
            divineHours = divineHours,
            divineMinutes = divineMinutes,
            divineSeconds = divineSeconds,
            divineMillis = divineMillis,
            formattedDivineTime = formattedDivine,
            formattedTotalDivineLife = formattedTotalLife,
            percentageLived = percentageLived,
            remainingDivineHours = remH,
            remainingDivineMinutes = remM,
            remainingDivineSeconds = remS,
            impactPhrase = getImpactPhrase(percentageLived, lang),
            lifeExpectancyYears = lifeExpectancyYears
        )
    }

    fun getImpactPhrase(percentageLived: Float, lang: AppLanguage = AppLanguage.FR): String =
        AppStrings.impactPhrase(lang, percentageLived)

    // ---------------------------------------------------------------------
    // Two divine hours mapping
    // ---------------------------------------------------------------------

    /**
     * Maps an average human lifespan onto the conceptual "Two Divine Hours"
     * (120 divine minutes).
     *
     * 1000 earth years = 24 divine hours => 1 divine hour = 41.67 earth years
     * and 2 divine hours = 83.33 earth years.
     */
    fun calculateTwoDivineHoursMapping(
        ageInYears: Double,
        lang: AppLanguage = AppLanguage.FR
    ): TwoDivineHoursProgress {
        val divineHours = (ageInYears / EARTH_YEARS_PER_DIVINE_DAY) * DIVINE_HOURS_PER_DAY
        val totalMinutesElapsed = divineHours * 60.0
        val totalSecondsElapsed = divineHours * 3600.0

        val percentage =
            ((totalMinutesElapsed / DIVINE_MINUTES_PER_TWO_HOURS) * 100.0).toFloat().coerceIn(0f, 100f)

        val isComplete = totalMinutesElapsed >= DIVINE_MINUTES_PER_TWO_HOURS
        val currentHour = if (totalMinutesElapsed < 60.0) 1 else 2
        val minuteInHour = if (currentHour == 1) {
            totalMinutesElapsed.toInt().coerceIn(0, 59)
        } else {
            (totalMinutesElapsed - 60.0).toInt().coerceIn(0, 59)
        }

        // Past 120 divine minutes the life is over: the hour is not "1 minute
        // left" any more, it is finished. Reporting both "1 min left in the
        // hour" and "0 min left overall" was contradictory.
        val remInHour = if (isComplete) 0 else 60 - minuteInHour
        val remTotal = max(0.0, DIVINE_MINUTES_PER_TWO_HOURS - totalMinutesElapsed)

        val h1Fraction = (totalMinutesElapsed / 60.0).toFloat().coerceIn(0f, 1f)
        val h2Fraction = ((totalMinutesElapsed - 60.0) / 60.0).toFloat().coerceIn(0f, 1f)

        val stageId = stageIdForAge(ageInYears)

        return TwoDivineHoursProgress(
            totalDivineMinutes = DIVINE_MINUTES_PER_TWO_HOURS,
            elapsedMinutes = totalMinutesElapsed,
            elapsedSeconds = totalSecondsElapsed,
            percentageOfTwoHours = percentage,
            currentHourNumber = currentHour,
            minuteInCurrentHour = minuteInHour,
            remainingMinutesInCurrentHour = remInHour,
            remainingMinutesOfTwoHours = remTotal,
            hour1Fraction = h1Fraction,
            hour2Fraction = h2Fraction,
            isTwoHoursComplete = isComplete,
            phaseTitle = AppStrings.stageTitle(lang, stageId),
            phaseSubtitle = AppStrings.stageSubtitle(lang, stageId),
            stageArabic = AppStrings.stageArabic(lang, stageId),
            stageQuote = AppStrings.stageQuote(lang, stageId),
            stageReference = AppStrings.stageReference(lang, stageId)
        )
    }

    /**
     * Stage boundaries follow the *divine hour* boundaries (41.67 and 83.33
     * earth years) so that the narrative never contradicts the dial.
     */
    fun stageIdForAge(ageInYears: Double): String = when {
        ageInYears < 15.0 -> "first_half_hour"
        ageInYears < EARTH_YEARS_PER_DIVINE_HOUR -> "youth"
        ageInYears < 60.0 -> "maturity"
        ageInYears < EARTH_YEARS_PER_TWO_DIVINE_HOURS -> "wisdom"
        else -> "end"
    }

    // ---------------------------------------------------------------------
    // Formatting helpers
    // ---------------------------------------------------------------------

    /** Converts an earth duration into a human readable divine duration. */
    fun earthToDivineFormatted(
        years: Double = 0.0,
        months: Double = 0.0,
        days: Double = 0.0,
        hours: Double = 0.0,
        minutes: Double = 0.0,
        seconds: Double = 0.0,
        lang: AppLanguage = AppLanguage.FR
    ): String {
        // Negative durations are meaningless: clamp instead of printing garbage.
        val y = years.coerceAtLeast(0.0)
        val mo = months.coerceAtLeast(0.0)
        val d = days.coerceAtLeast(0.0)
        val h = hours.coerceAtLeast(0.0)
        val mi = minutes.coerceAtLeast(0.0)
        val s = seconds.coerceAtLeast(0.0)

        val totalEarthYears = y + (mo / 12.0) + (d / DAYS_PER_EARTH_YEAR) +
                (h / (DAYS_PER_EARTH_YEAR * 24.0)) +
                (mi / (DAYS_PER_EARTH_YEAR * 1440.0)) +
                (s / (DAYS_PER_EARTH_YEAR * 86400.0))

        val divineHours = (totalEarthYears / EARTH_YEARS_PER_DIVINE_DAY) * DIVINE_HOURS_PER_DAY
        val totalSec = divineHours * 3600.0

        return when {
            totalSec >= 3600.0 -> AppStrings.divineUnitHoursMinutesSeconds(
                lang,
                (totalSec / 3600.0).toInt(),
                ((totalSec % 3600.0) / 60.0).toInt(),
                (totalSec % 60.0).toInt()
            )
            totalSec >= 60.0 -> AppStrings.divineUnitMinutesSeconds(
                lang,
                (totalSec / 60.0).toInt(),
                (totalSec % 60.0).toInt()
            )
            totalSec >= 1.0 -> AppStrings.divineUnitSeconds(lang, totalSec, 2)
            totalSec >= 0.001 -> AppStrings.divineUnitSeconds(lang, totalSec, 4)
            else -> AppStrings.divineUnitSeconds(lang, totalSec, 6)
        }
    }

    /** Converts a divine duration into a human readable earth duration. */
    fun divineToEarthFormatted(
        hours: Double = 0.0,
        minutes: Double = 0.0,
        seconds: Double = 0.0,
        lang: AppLanguage = AppLanguage.FR
    ): String {
        val h = hours.coerceAtLeast(0.0)
        val mi = minutes.coerceAtLeast(0.0)
        val s = seconds.coerceAtLeast(0.0)

        val totalDivineHours = h + (mi / 60.0) + (s / 3600.0)
        val earthYears = (totalDivineHours / DIVINE_HOURS_PER_DAY) * EARTH_YEARS_PER_DIVINE_DAY
        val earthDays = earthYears * DAYS_PER_EARTH_YEAR

        return when {
            earthYears >= 1.0 -> {
                val y = earthYears.toInt()
                val monthsExact = (earthYears - y) * 12.0
                val mo = floor(monthsExact).toInt()
                val daysExact = (monthsExact - mo) * DAYS_PER_EARTH_MONTH
                val dd = floor(daysExact).toInt()
                // The day component used to be dropped whenever the month
                // remainder was zero, losing up to 30 days of precision.
                when {
                    mo > 0 && dd > 0 -> AppStrings.earthYearsMonthsDays(lang, y, mo, dd)
                    mo > 0 -> AppStrings.earthYearsMonths(lang, y, mo)
                    dd > 0 -> AppStrings.earthYearsDays(lang, y, dd)
                    else -> AppStrings.earthYearsOnly(lang, y)
                }
            }
            earthDays >= 1.0 -> {
                val dd = earthDays.toInt()
                val hh = ((earthDays - dd) * 24.0).toInt()
                AppStrings.earthDaysHours(lang, dd, hh)
            }
            else -> {
                val hh = (earthDays * 24.0).toInt()
                val mm = ((earthDays * 24.0 - hh) * 60.0).toInt()
                AppStrings.earthHoursMinutes(lang, hh, mm)
            }
        }
    }

    // ---------------------------------------------------------------------
    // Milestones for the 120 second immersive visualizer
    // ---------------------------------------------------------------------
    //
    // The simulation clock is a *normalised* 120 units for a full life:
    // 1 simulation second ≈ 0.625 earth year. `divineRealMinutes` keeps the
    // mathematically correct divine position of the same life event.
    val LIFE_MILESTONES = listOf(
        LifeMilestone(
            id = "birth",
            earthAgeYears = 0.0,
            divineSecondsIn120sSimulation = 0f,
            divineRealMinutes = 0.0,
            iconEmoji = "👶"
        ),
        LifeMilestone(
            id = "childhood",
            earthAgeYears = 10.0,
            divineSecondsIn120sSimulation = 16f,
            divineRealMinutes = 14.4,
            iconEmoji = "🎈"
        ),
        LifeMilestone(
            id = "puberty",
            earthAgeYears = 15.0,
            divineSecondsIn120sSimulation = 24f,
            divineRealMinutes = 21.6,
            iconEmoji = "⚡"
        ),
        LifeMilestone(
            id = "youth_marriage",
            earthAgeYears = 25.0,
            divineSecondsIn120sSimulation = 40f,
            divineRealMinutes = 36.0,
            iconEmoji = "💍"
        ),
        LifeMilestone(
            id = "maturity_40",
            earthAgeYears = 40.0,
            divineSecondsIn120sSimulation = 64f,
            divineRealMinutes = 57.6,
            iconEmoji = "🏠"
        ),
        LifeMilestone(
            id = "wisdom_aging",
            earthAgeYears = 58.0,
            divineSecondsIn120sSimulation = 93f,
            divineRealMinutes = 83.5,
            iconEmoji = "👴"
        ),
        LifeMilestone(
            id = "return_to_allah",
            earthAgeYears = 75.0,
            divineSecondsIn120sSimulation = 120f,
            divineRealMinutes = 108.0,
            iconEmoji = "☁️"
        )
    )

    val QURAN_VERSES = listOf(
        QuranSpiritualQuote(
            id = "hajj_47",
            arabic = "وَإِنَّ يَوْمًا عِندَ رَبِّكَ كَأَلْفِ سَنَةٍ مِّمَّا تَعُدُّونَ"
        ),
        QuranSpiritualQuote(
            id = "muminun_112",
            arabic = "قَالَ كَمْ لَبِثْتُمْ فِي الْأَرْضِ عَدَدَ سِنِينَ • قَالُوا لَبِثْنَا يَوْمًا أَوْ بَعْضَ يَوْمٍ فَاسْأَلِ الْعَادِّينَ"
        ),
        QuranSpiritualQuote(
            id = "asr_103",
            arabic = "وَالْعَصْرِ • إِنَّ الْإِنسَانَ لَفِي خُسْرٍ • إِلَّا الَّذِينَ آمَنُوا وَعملُوا الصَّالِحَاتِ وَتَوَاصَوْا بِالْحَقِّ وَتَوَاصَوْا بِالصَّبْرِ"
        ),
        QuranSpiritualQuote(
            id = "naziat_46",
            arabic = "كَأَنَّهُمْ يَوْمَ يَرَوْنَهَا لَمْ يَلْبَثُوا إِلَّا عَشِيَّةً أَوْ ضُحَاهَا"
        ),
        QuranSpiritualQuote(
            id = "yunus_45",
            arabic = "وَيَوْمَ يَحْشُرُهُمْ كَأَن لَّمْ يَلْبَثُوا إِلَّا سَاعَةً مِّنَ النَّهَارِ يَتَعَارَفُونَ بَيْنَهُمْ"
        )
    )
}
