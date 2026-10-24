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
    val tasbihCountToday: Int = 0
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
    val isDayMode: Boolean = false
)
