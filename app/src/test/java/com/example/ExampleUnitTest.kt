package com.example

import com.example.data.calculator.DivineTimeCalculator
import com.example.data.localization.AppStrings
import com.example.data.model.AppLanguage
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Unit tests for the divine-time maths and for the localisation contract.
 *
 * These replace the previous placeholder suite, which only re-implemented the
 * formulas inside the test and therefore could never catch a bug.
 */
class ExampleUnitTest {

    /**
     * Millisecond timestamp for [years] after the epoch.
     *
     * One extra second is added on purpose: timestamps are truncated to whole
     * milliseconds, so an exact boundary such as 41.6666… years would land a
     * hair *below* it and `floor()` would report 00:59:59 instead of 01:00:00.
     */
    private fun timestampForYears(years: Double): Long =
        (years * DivineTimeCalculator.DAYS_PER_EARTH_YEAR * DivineTimeCalculator.MS_PER_DAY)
            .toLong() + 1000L

    // ---------------------------------------------------------------------
    // Constants — the single source of truth
    // ---------------------------------------------------------------------

    @Test
    fun oneDivineSecondEqualsFourPointTwoEarthDays() {
        // 1000 earth years = 24 divine hours => 1 divine second = 4.2273 earth days.
        assertEquals(4.2273, DivineTimeCalculator.EARTH_DAYS_PER_DIVINE_SECOND, 0.0001)
    }

    @Test
    fun oneEarthYearIsEightySixPointFourDivineSeconds() {
        assertEquals(86.4, DivineTimeCalculator.DIVINE_SECONDS_PER_EARTH_YEAR, 0.0001)
        // 1 earth day, then down to a single earth minute.
        assertEquals(0.236555, DivineTimeCalculator.DIVINE_SECONDS_PER_EARTH_DAY, 0.000001)
        assertEquals(0.0001643, DivineTimeCalculator.DIVINE_SECONDS_PER_EARTH_MINUTE, 0.0000001)
    }

    @Test
    fun fiveMinutePrayerIsUnderAMillisecondOfDivineTime() {
        val fiveMinutes = DivineTimeCalculator.DIVINE_SECONDS_PER_EARTH_MINUTE * 5.0
        assertEquals(0.000821, fiveMinutes, 0.000001)
        assertTrue("a prayer must stay under 0.001 divine seconds", fiveMinutes < 0.001)
    }

    @Test
    fun lifetimePrayerBudgetIsTwoDivineMinutes() {
        // 80 years x 365.2425 days x 25 min/day = 730 485 earth minutes.
        assertEquals(2.00, DivineTimeCalculator.LIFETIME_PRAYER_DIVINE_MINUTES, 0.01)
        // ...which is 1.7 % of the 120 divine minutes of a life.
        assertEquals(1.67, DivineTimeCalculator.LIFETIME_PRAYER_PERCENT, 0.02)
    }

    @Test
    fun eightyYearsIsOnePointNineTwoDivineHours() {
        val divineHours = (80.0 / DivineTimeCalculator.EARTH_YEARS_PER_DIVINE_DAY) *
            DivineTimeCalculator.DIVINE_HOURS_PER_DAY
        assertEquals(1.92, divineHours, 0.0001)
        assertEquals(115.2, divineHours * 60.0, 0.0001)
    }

    // ---------------------------------------------------------------------
    // calculateDivineTime
    // ---------------------------------------------------------------------

    @Test
    fun newbornHasLivedNoDivineTime() {
        val info = DivineTimeCalculator.calculateDivineTime(
            birthTimestampMs = 1_000_000L,
            currentTimestampMs = 1_000_000L,
            lifeExpectancyYears = 80
        )
        assertEquals(0.0, info.earthAgeYears, 0.000001)
        assertEquals("00:00:00.0", info.formattedDivineTime)
        assertEquals(0f, info.percentageLived, 0.0001f)
    }

    @Test
    fun oneDivineHourOfLifeReadsAsExactlyOneOClock() {
        val birth = 0L
        val current = timestampForYears(DivineTimeCalculator.EARTH_YEARS_PER_DIVINE_HOUR)
        val info = DivineTimeCalculator.calculateDivineTime(
            birthTimestampMs = birth,
            currentTimestampMs = current,
            lifeExpectancyYears = 83
        )

        assertEquals("01:00:00.0", info.formattedDivineTime)
        // 1 divine hour out of ~1.992 divine hours of expected life.
        assertEquals(50.2f, info.percentageLived, 0.3f)
    }

    @Test
    fun eightyThreeYearsFillsTheTwoDivineHours() {
        val birth = 0L
        val current = timestampForYears(DivineTimeCalculator.EARTH_YEARS_PER_TWO_DIVINE_HOURS)
        val info = DivineTimeCalculator.calculateDivineTime(
            birthTimestampMs = birth,
            currentTimestampMs = current,
            lifeExpectancyYears = 83
        )

        assertEquals("02:00:00.0", info.formattedDivineTime)
        assertEquals(100f, info.percentageLived, 0.01f)
        assertEquals(0, info.remainingDivineHours)
        assertEquals(0, info.remainingDivineMinutes)
    }

    @Test
    fun zeroLifeExpectancyDoesNotProduceNaN() {
        val info = DivineTimeCalculator.calculateDivineTime(
            birthTimestampMs = 0L,
            currentTimestampMs = timestampForYears(30.0),
            lifeExpectancyYears = 0
        )
        assertEquals(0f, info.percentageLived, 0.0001f)
        assertEquals("00:00:00", info.formattedTotalDivineLife)
        assertFalse(info.percentageLived.isNaN())
    }

    @Test
    fun futureBirthDateIsClampedToZero() {
        val info = DivineTimeCalculator.calculateDivineTime(
            birthTimestampMs = timestampForYears(40.0),
            currentTimestampMs = 0L,
            lifeExpectancyYears = 80
        )
        assertEquals(0.0, info.earthAgeYears, 0.000001)
        assertEquals(0f, info.percentageLived, 0.0001f)
    }

    @Test
    fun earthAgeBreakdownStaysConsistentWithWholeYears() {
        val birth = 0L
        val current = timestampForYears(30.5)
        val info = DivineTimeCalculator.calculateDivineTime(
            birthTimestampMs = birth,
            currentTimestampMs = current,
            lifeExpectancyYears = 80
        )
        assertEquals(30, info.earthYearsInt)
        assertTrue("months must be 0..11", info.earthMonthsInt in 0..11)
        assertTrue("days must be 0..30", info.earthDaysInt in 0..30)
    }

    // ---------------------------------------------------------------------
    // Two divine hours mapping
    // ---------------------------------------------------------------------

    @Test
    fun twoHourMappingStartsAtZero() {
        val mapping = DivineTimeCalculator.calculateTwoDivineHoursMapping(0.0)
        assertEquals(1, mapping.currentHourNumber)
        assertEquals(0, mapping.minuteInCurrentHour)
        assertEquals(0.0, mapping.elapsedMinutes, 0.0001)
        assertEquals(0f, mapping.percentageOfTwoHours, 0.0001f)
        assertFalse(mapping.isTwoHoursComplete)
    }

    @Test
    fun twoHourMappingSplitsAtOneDivineHour() {
        val mapping = DivineTimeCalculator.calculateTwoDivineHoursMapping(
            DivineTimeCalculator.EARTH_YEARS_PER_DIVINE_HOUR
        )
        assertEquals(60.0, mapping.elapsedMinutes, 0.001)
        assertEquals(50f, mapping.percentageOfTwoHours, 0.01f)
        assertEquals(2, mapping.currentHourNumber)
        assertEquals(0, mapping.minuteInCurrentHour)
        assertFalse(mapping.isTwoHoursComplete)
    }

    @Test
    fun twoHourMappingCompletesAtEightyThreeYears() {
        val mapping = DivineTimeCalculator.calculateTwoDivineHoursMapping(
            DivineTimeCalculator.EARTH_YEARS_PER_TWO_DIVINE_HOURS
        )
        assertEquals(120.0, mapping.elapsedMinutes, 0.001)
        assertEquals(100f, mapping.percentageOfTwoHours, 0.001f)
        assertTrue(mapping.isTwoHoursComplete)
        assertEquals(0.0, mapping.remainingMinutesOfTwoHours, 0.001)
    }

    @Test
    fun twoHourMappingNeverOverflows() {
        val mapping = DivineTimeCalculator.calculateTwoDivineHoursMapping(150.0)
        assertEquals(100f, mapping.percentageOfTwoHours, 0.001f)
        assertEquals(1f, mapping.hour1Fraction, 0.0001f)
        assertEquals(1f, mapping.hour2Fraction, 0.0001f)
        assertEquals(0.0, mapping.remainingMinutesOfTwoHours, 0.001)
    }

    @Test
    fun stageBoundariesFollowTheDivineHours() {
        assertEquals("first_half_hour", DivineTimeCalculator.stageIdForAge(0.0))
        assertEquals("youth", DivineTimeCalculator.stageIdForAge(20.0))
        assertEquals("maturity", DivineTimeCalculator.stageIdForAge(45.0))
        assertEquals("wisdom", DivineTimeCalculator.stageIdForAge(70.0))
        assertEquals("end", DivineTimeCalculator.stageIdForAge(90.0))
    }

    // ---------------------------------------------------------------------
    // Converters
    // ---------------------------------------------------------------------

    @Test
    fun earthToDivineConverterFormatsInEachLanguage() {
        val en = DivineTimeCalculator.earthToDivineFormatted(years = 1.0, lang = AppLanguage.EN)
        val fr = DivineTimeCalculator.earthToDivineFormatted(years = 1.0, lang = AppLanguage.FR)
        val ar = DivineTimeCalculator.earthToDivineFormatted(years = 1.0, lang = AppLanguage.AR)

        // 1 earth year = 86.4 divine seconds = 1 min 26 s.
        assertTrue(en, en.contains("1min 26s"))
        assertTrue(fr, fr.contains("1min 26s"))
        assertTrue(en, en.endsWith("divine"))
        assertTrue(fr, fr.endsWith("divines"))
        assertTrue(ar, ar.contains("إلهية"))
    }

    @Test
    fun divineToEarthConverterKeepsADivineMinuteAtAboutTwoHundredFiftyThreeDays() {
        val result = DivineTimeCalculator.divineToEarthFormatted(minutes = 1.0, lang = AppLanguage.FR)
        assertTrue(result, result.startsWith("253 jours"))
    }

    @Test
    fun convertersClampNegativeInput() {
        assertEquals(
            DivineTimeCalculator.earthToDivineFormatted(years = 0.0),
            DivineTimeCalculator.earthToDivineFormatted(years = -50.0)
        )
        assertEquals(
            DivineTimeCalculator.divineToEarthFormatted(minutes = 0.0),
            DivineTimeCalculator.divineToEarthFormatted(minutes = -10.0)
        )
    }

    // ---------------------------------------------------------------------
    // Data + localisation contract
    // ---------------------------------------------------------------------

    @Test
    fun milestonesAreOrderedAndIdOnly() {
        val milestones = DivineTimeCalculator.LIFE_MILESTONES
        assertTrue(milestones.size >= 4)
        assertEquals("birth", milestones.first().id)
        assertEquals(0.0, milestones.first().earthAgeYears, 0.0001)
        assertTrue(
            "milestones must be ordered by age",
            milestones.zipWithNext().all { (a, b) -> b.earthAgeYears >= a.earthAgeYears }
        )
    }

    @Test
    fun everyStageIsTranslatedInEveryLanguage() {
        val stages = listOf("first_half_hour", "youth", "maturity", "wisdom", "end")
        for (lang in AppLanguage.values()) {
            for (stage in stages) {
                assertTrue(
                    "missing $stage title in $lang",
                    AppStrings.stageTitle(lang, stage).isNotBlank()
                )
                assertTrue(
                    "missing $stage quote in $lang",
                    AppStrings.stageQuote(lang, stage).isNotBlank()
                )
            }
        }
    }

    @Test
    fun everyLanguageProvidesTheMainHeadings() {
        for (lang in AppLanguage.values()) {
            assertTrue(AppStrings.appTitle(lang).isNotBlank())
            assertTrue(AppStrings.journalTitle(lang).isNotBlank())
            assertTrue(AppStrings.converterTitle(lang).isNotBlank())
            assertTrue(AppStrings.challengesTitle(lang).isNotBlank())
            assertTrue(AppStrings.lifeMapTitle(lang).isNotBlank())
            assertTrue(AppStrings.prayerName(lang, "fajr").isNotBlank())
        }
    }

    @Test
    fun prayerDeedSubtitleUsesTheRealPrayerCost() {
        for (lang in AppLanguage.values()) {
            val subtitle = AppStrings.prayerDeedSubtitle(lang, "fajr")
            assertTrue(subtitle, subtitle.contains("0.0008"))
        }
    }
}
