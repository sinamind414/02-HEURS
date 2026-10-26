package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyJournalDao {
    @Query("SELECT * FROM daily_journal WHERE dateIso = :dateIso LIMIT 1")
    fun getJournalForDate(dateIso: String): Flow<DailyJournalEntity?>

    @Query("SELECT * FROM daily_journal ORDER BY dateIso DESC LIMIT 30")
    fun getRecentJournals(): Flow<List<DailyJournalEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateJournal(entry: DailyJournalEntity)

    // Profile queries
    @Query("SELECT * FROM user_profile WHERE id = 1 LIMIT 1")
    fun getUserProfile(): Flow<UserProfileEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserProfile(profile: UserProfileEntity)

    // Prayer timings snapshot (single row) — feeds the reminder receivers
    // so they can re-arm tomorrow's adhan without touching the network.

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertTimingsCache(cache: PrayerTimingsCacheEntity)

    @Query("SELECT * FROM prayer_timings_cache WHERE id = 1 LIMIT 1")
    fun getTimingsCache(): Flow<PrayerTimingsCacheEntity?>
}
