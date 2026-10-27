package com.example.data.repository

import com.example.data.local.DailyJournalDao
import com.example.data.local.DailyJournalEntity
import com.example.data.local.PrayerTimingsCacheEntity
import com.example.data.local.UserProfileEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SaatanRepository(private val dao: DailyJournalDao) {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    fun getTodayIso(): String = dateFormat.format(Date())

    fun getUserProfile(): Flow<UserProfileEntity?> = dao.getUserProfile()

    suspend fun saveUserProfile(profile: UserProfileEntity) {
        dao.saveUserProfile(profile)
    }

    suspend fun updateBirthDateAndExpectancy(birthMs: Long, expectancyYears: Int) {
        val current = dao.getUserProfile().firstOrNull() ?: UserProfileEntity()
        dao.saveUserProfile(
            current.copy(
                birthTimestampMs = birthMs,
                lifeExpectancyYears = expectancyYears
            )
        )
    }

    suspend fun updateLanguage(langCode: String) {
        val current = dao.getUserProfile().firstOrNull() ?: UserProfileEntity()
        dao.saveUserProfile(current.copy(languageCode = langCode))
    }

    /** One-shot read for startup location restore (no Flow re-emission). */
    suspend fun getProfileOnce(): UserProfileEntity =
        dao.getUserProfile().firstOrNull() ?: UserProfileEntity()

    /** Persists the last city/country used for prayer times (v5). */
    suspend fun updateCity(city: String, country: String) {
        val current = dao.getUserProfile().firstOrNull() ?: UserProfileEntity()
        dao.saveUserProfile(current.copy(cityName = city, countryName = country))
    }

    /** Persists the day / night theme choice. */
    suspend fun updateThemeMode(isDay: Boolean) {
        val current = dao.getUserProfile().firstOrNull() ?: UserProfileEntity()
        dao.saveUserProfile(current.copy(isDayMode = isDay))
    }

    /**
     * Updates the notification preferences. Only the given flags are written so
     * that a single toggle does not clobber the others.
     */
    suspend fun updateNotificationPreferences(
        notificationsEnabled: Boolean? = null,
        reminderFajr: Boolean? = null,
        reminderDhuhr: Boolean? = null,
        reminderSocialScroll: Boolean? = null,
        reminderFriday: Boolean? = null
    ) {
        val current = dao.getUserProfile().firstOrNull() ?: UserProfileEntity()
        dao.saveUserProfile(
            current.copy(
                notificationsEnabled = notificationsEnabled ?: current.notificationsEnabled,
                reminderFajr = reminderFajr ?: current.reminderFajr,
                reminderDhuhr = reminderDhuhr ?: current.reminderDhuhr,
                reminderSocialScroll = reminderSocialScroll ?: current.reminderSocialScroll,
                reminderFriday = reminderFriday ?: current.reminderFriday
            )
        )
    }

    fun getTodayJournal(): Flow<DailyJournalEntity?> = dao.getJournalForDate(getTodayIso())

    /** Persists the last successful Aladhan snapshot (single row, REPLACE). */
    suspend fun saveTimingsCache(cache: PrayerTimingsCacheEntity) {
        dao.upsertTimingsCache(cache)
    }

    fun getTimingsCache(): Flow<PrayerTimingsCacheEntity?> = dao.getTimingsCache()

    fun getRecentJournals(): Flow<List<DailyJournalEntity>> = dao.getRecentJournals()

    suspend fun saveTodayJournal(entry: DailyJournalEntity) {
        dao.insertOrUpdateJournal(entry)
    }

    suspend fun toggleHabit(habitKey: String, isChecked: Boolean) {
        val today = getTodayIso()
        val current = dao.getJournalForDate(today).firstOrNull() ?: DailyJournalEntity(dateIso = today)
        val updated = when (habitKey) {
            "fajr" -> current.copy(fajrDone = isChecked)
            "dhuhr" -> current.copy(dhuhrDone = isChecked)
            "asr" -> current.copy(asrDone = isChecked)
            "maghrib" -> current.copy(maghribDone = isChecked)
            "isha" -> current.copy(ishaDone = isChecked)
            "sadaqa" -> current.copy(sadaqaGiven = isChecked)
            "dhikr" -> current.copy(morningEveningDhikr = isChecked)
            "good_deed" -> current.copy(goodDeedDone = isChecked)
            else -> current
        }

        // Recalculate divine score (1-5 stars)
        var stars = 0
        val prayersCount = listOf(updated.fajrDone, updated.dhuhrDone, updated.asrDone, updated.maghribDone, updated.ishaDone).count { it }
        if (prayersCount >= 3) stars += 1
        if (prayersCount == 5) stars += 1
        if (updated.quranPagesRead > 0) stars += 1
        if (updated.sadaqaGiven) stars += 1
        if (updated.morningEveningDhikr || updated.goodDeedDone) stars += 1
        val finalStars = minOf(5, maxOf(1, stars))

        dao.insertOrUpdateJournal(updated.copy(divineScoreStars = finalStars))
    }

    suspend fun updateQuranPages(pages: Int) {
        val today = getTodayIso()
        val current = dao.getJournalForDate(today).firstOrNull() ?: DailyJournalEntity(dateIso = today)
        dao.insertOrUpdateJournal(current.copy(quranPagesRead = maxOf(0, pages)))
    }

    suspend fun updateDailyNote(note: String) {
        val today = getTodayIso()
        val current = dao.getJournalForDate(today).firstOrNull() ?: DailyJournalEntity(dateIso = today)
        dao.insertOrUpdateJournal(current.copy(dailyNote = note))
    }

    /**
     * Records today's answer to "Quel goût ?" (Dhawq cycle). The value is
     * never aggregated, compared or scored — it exists only so the user can
     * see and change today's answer (Serment #2).
     */
    suspend fun updateSalahTaste(taste: String) {
        val today = getTodayIso()
        val current = dao.getJournalForDate(today).firstOrNull() ?: DailyJournalEntity(dateIso = today)
        dao.insertOrUpdateJournal(current.copy(salahTaste = taste))
    }

    suspend fun incrementTasbih() {
        val today = getTodayIso()
        val current = dao.getJournalForDate(today).firstOrNull() ?: DailyJournalEntity(dateIso = today)
        dao.insertOrUpdateJournal(current.copy(tasbihCountToday = current.tasbihCountToday + 1))

        val profile = dao.getUserProfile().firstOrNull() ?: UserProfileEntity()
        dao.saveUserProfile(profile.copy(totalTasbihAllTime = profile.totalTasbihAllTime + 1))
    }

    suspend fun resetTasbihToday() {
        val today = getTodayIso()
        val current = dao.getJournalForDate(today).firstOrNull() ?: DailyJournalEntity(dateIso = today)
        dao.insertOrUpdateJournal(current.copy(tasbihCountToday = 0))
    }

    /**
     * @param startMs the exact moment the session started. It is passed in
     *        rather than read back from the profile because the profile flow
     * emits asynchronously and a fresh read would still hold the old value.
     */
    suspend fun toggleTwoHourMode(activate: Boolean, startMs: Long = System.currentTimeMillis()) {
        val current = dao.getUserProfile().firstOrNull() ?: UserProfileEntity()
        dao.saveUserProfile(
            current.copy(
                twoHourModeActive = activate,
                twoHourModeStartMs = if (activate) startMs else 0L
            )
        )
    }
}
