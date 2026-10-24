package com.example.data.model

data class DivineTimeInfo(
    val earthAgeYears: Double,
    val earthDaysLived: Long,
    val earthYearsInt: Int,
    val earthMonthsInt: Int,
    val earthDaysInt: Int,
    val divineHours: Int,
    val divineMinutes: Int,
    val divineSeconds: Int,
    val divineMillis: Int,
    val formattedDivineTime: String,
    val formattedTotalDivineLife: String,
    val percentageLived: Float,
    val remainingDivineHours: Int,
    val remainingDivineMinutes: Int,
    val remainingDivineSeconds: Int,
    val impactPhrase: String,
    val lifeExpectancyYears: Int
)

data class TwoDivineHoursProgress(
    val totalDivineMinutes: Double = 120.0, // 2 Divine Hours = 120 min
    val elapsedMinutes: Double,
    val elapsedSeconds: Double,
    val percentageOfTwoHours: Float,
    val currentHourNumber: Int, // 1 or 2
    val minuteInCurrentHour: Int, // 0..59
    val remainingMinutesInCurrentHour: Int,
    val remainingMinutesOfTwoHours: Double,
    val hour1Fraction: Float, // 0f..1f
    val hour2Fraction: Float, // 0f..1f
    /** True once the 120 divine minutes are exhausted (life expectancy reached). */
    val isTwoHoursComplete: Boolean = false,
    val phaseTitle: String,
    val phaseSubtitle: String,
    val stageArabic: String,
    val stageQuote: String,
    val stageReference: String
)

/**
 * A life event. All textual content is resolved through `AppStrings` using
 * [id] so that it can be localized.
 */
data class LifeMilestone(
    val id: String,
    val earthAgeYears: Double,
    /** Position on the normalised 120 unit simulation clock. */
    val divineSecondsIn120sSimulation: Float,
    /** Mathematically correct divine position of the same event. */
    val divineRealMinutes: Double,
    val iconEmoji: String
)

data class DailyHabitItem(
    val id: String,
    val title: String,
    val divineTimeEquivalent: String,
    val iconEmoji: String,
    val isCompleted: Boolean = false
)

data class ChallengeItem(
    val id: String,
    val levelNumber: Int,
    val levelName: String,
    val title: String,
    val targetDescription: String,
    val iconEmoji: String,
    /**
     * Celestial value of the challenge, in divine seconds. Kept numeric so the
     * UI can phrase it in the current language; the previous pre-formatted
     * French string leaked into the Arabic and English interfaces.
     */
    val divineSeconds: Double,
    val progress: Int,
    val maxProgress: Int,
    val isUnlocked: Boolean,
    val badgeName: String
)

data class QuranSpiritualQuote(
    val id: String,
    val arabic: String
)
