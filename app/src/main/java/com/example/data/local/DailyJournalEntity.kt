package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_journal")
data class DailyJournalEntity(
    @PrimaryKey val dateIso: String, // e.g. "2026-10-20"
    val timestamp: Long = System.currentTimeMillis(),
    val fajrDone: Boolean = false,
    val dhuhrDone: Boolean = false,
    val asrDone: Boolean = false,
    val maghribDone: Boolean = false,
    val ishaDone: Boolean = false,
    val quranPagesRead: Int = 0,
    val sadaqaGiven: Boolean = false,
    val morningEveningDhikr: Boolean = false,
    val goodDeedDone: Boolean = false,
    val dailyNote: String = "",
    val divineScoreStars: Int = 0,
    val tasbihCountToday: Int = 0,
    /**
     * Answer to the Dhawq evening question "Quel goût ?" ("douce", "lourde",
     * "absente", "goutee"). Only today's answer is ever displayed — it is
     * never aggregated into a score, streak or statistic (Serment #2).
     */
    val salahTaste: String = "",
    /**
     * Inner Weather check-in of the day (Presence Rhythm US-05). A raw
     * [com.example.data.model.InnerWeather] key — "" means "not logged
     * today". Stored but never scored, compared or aggregated (Serment #2).
     */
    val moodCheckIn: String = "",
    /** Inner Weather logged right after a completed pause ("" = none yet). */
    val moodAfterPause: String = ""
)

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val id: Int = 1,
    val birthTimestampMs: Long = 803260800000L, // Default ~ 1995-06-15
    val lifeExpectancyYears: Int = 75,
    val notificationsEnabled: Boolean = true,
    val reminderFajr: Boolean = true,
    val reminderDhuhr: Boolean = true,
    val reminderSocialScroll: Boolean = true,
    val reminderFriday: Boolean = true,
    val twoHourModeActive: Boolean = false,
    val twoHourModeStartMs: Long = 0L,
    val totalTasbihAllTime: Int = 0,
    val languageCode: String = "fr",
    /** Persisted day / night theme choice. */
    val isDayMode: Boolean = false,
    /** Last city/country used for the Aladhan prayer-times fetch (v5). */
    val cityName: String = "Paris",
    val countryName: String = "France",
    /**
     * US onboarding (Presence Rhythm, 29/10/2026) walked through to the
     * disclaimer — false until then, so the flow shows once on next launch.
     */
    val usOnboardingDone: Boolean = false,
    /** Timestamp of the "I understand" crisis disclaimer acknowledgment. */
    val crisisAckAtMs: Long = 0L,
    /** Daily rhythm chosen at onboarding: [com.example.data.model.UsRhythm] key. */
    val usRhythm: String = "beginner",
    /** [com.example.data.model.TraditionLayer] key — "off" = light skin. */
    val traditionLayer: String = "off"
)
