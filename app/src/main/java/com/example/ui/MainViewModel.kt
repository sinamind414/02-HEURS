package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.calculator.DivineTimeCalculator
import com.example.data.local.DailyJournalEntity
import com.example.data.local.SaatanDatabase
import com.example.data.local.UserProfileEntity
import com.example.data.localization.AppStrings
import com.example.data.model.AppLanguage
import com.example.data.model.ChallengeItem
import com.example.data.model.DivineTimeInfo
import com.example.data.model.LifeMilestone
import com.example.data.model.PrayerCountdownState
import com.example.data.notification.DivineNotificationHelper
import com.example.data.repository.PrayerTimesRepository
import com.example.data.repository.SaatanRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.Calendar

enum class SaatanScreen {
    DIVINE_CLOCK,
    CONVERTER,
    JOURNAL,
    IMMERSIVE_LIFE,
    CHALLENGES,
    SETTINGS_VERSES
}

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: SaatanRepository
    private val prayerRepository = PrayerTimesRepository()

    val userProfile: StateFlow<UserProfileEntity?>
    val todayJournal: StateFlow<DailyJournalEntity?>
    val recentJournals: StateFlow<List<DailyJournalEntity>>

    /**
     * Challenges are derived from the journal + profile + language, exposed as a
     * [StateFlow] so the screen actually recomposes when the underlying data
     * changes. Reading `StateFlow.value` from a composable does not subscribe.
     */
    val challenges: StateFlow<List<ChallengeItem>>

    private val _currentScreen = MutableStateFlow(SaatanScreen.DIVINE_CLOCK)
    val currentScreen: StateFlow<SaatanScreen> = _currentScreen.asStateFlow()

    private val _liveDivineTime = MutableStateFlow<DivineTimeInfo>(
        DivineTimeCalculator.calculateDivineTime(
            birthTimestampMs = UserProfileEntity().birthTimestampMs,
            lifeExpectancyYears = UserProfileEntity().lifeExpectancyYears,
            lang = AppLanguage.FR
        )
    )
    val liveDivineTime: StateFlow<DivineTimeInfo> = _liveDivineTime.asStateFlow()

    // Immersive 2-Minute Life Journey Simulation state
    private val _simSeconds = MutableStateFlow(0f)
    val simSeconds: StateFlow<Float> = _simSeconds.asStateFlow()

    private val _simPlaying = MutableStateFlow(false)
    val simPlaying: StateFlow<Boolean> = _simPlaying.asStateFlow()

    private val _simFinished = MutableStateFlow(false)
    val simFinished: StateFlow<Boolean> = _simFinished.asStateFlow()

    private val _activeMilestone = MutableStateFlow<LifeMilestone?>(DivineTimeCalculator.LIFE_MILESTONES.firstOrNull())
    val activeMilestone: StateFlow<LifeMilestone?> = _activeMilestone.asStateFlow()

    // 2-Hour Meditation Mode Remaining Seconds (7200 seconds max)
    private val _twoHourSecondsRemaining = MutableStateFlow(TWO_HOUR_SECONDS)
    val twoHourSecondsRemaining: StateFlow<Long> = _twoHourSecondsRemaining.asStateFlow()

    // Converter state
    private val _earthInputYears = MutableStateFlow("1")
    val earthInputYears: StateFlow<String> = _earthInputYears.asStateFlow()

    private val _earthInputDays = MutableStateFlow("0")
    val earthInputDays: StateFlow<String> = _earthInputDays.asStateFlow()

    private val _earthInputMinutes = MutableStateFlow("0")
    val earthInputMinutes: StateFlow<String> = _earthInputMinutes.asStateFlow()

    private val _converterResult = MutableStateFlow(
        DivineTimeCalculator.earthToDivineFormatted(years = 1.0, days = 0.0, minutes = 0.0)
    )
    val converterResult: StateFlow<String> = _converterResult.asStateFlow()

    // Reverse Converter (Divine to Earth)
    private val _divineInputMinutes = MutableStateFlow("1")
    val divineInputMinutes: StateFlow<String> = _divineInputMinutes.asStateFlow()

    private val _reverseResult = MutableStateFlow(
        DivineTimeCalculator.divineToEarthFormatted(minutes = 1.0)
    )
    val reverseResult: StateFlow<String> = _reverseResult.asStateFlow()

    // Edit profile dialog
    private val _showProfileDialog = MutableStateFlow(false)
    val showProfileDialog: StateFlow<Boolean> = _showProfileDialog.asStateFlow()

    // Language state: FR (default), AR, EN
    private val _appLanguage = MutableStateFlow(AppLanguage.FR)
    val appLanguage: StateFlow<AppLanguage> = _appLanguage.asStateFlow()

    private val _showLanguageDialog = MutableStateFlow(false)
    val showLanguageDialog: StateFlow<Boolean> = _showLanguageDialog.asStateFlow()

    // Day / Night Theme Selector state
    private val _isDayMode = MutableStateFlow(false)
    val isDayMode: StateFlow<Boolean> = _isDayMode.asStateFlow()

    // Prayer Times & Aladhan Integration State
    private val _prayerState = MutableStateFlow(prayerRepository.recalculateCurrentCountdown("Paris", "France"))
    val prayerState: StateFlow<PrayerCountdownState> = _prayerState.asStateFlow()

    private val _showCityDialog = MutableStateFlow(false)
    val showCityDialog: StateFlow<Boolean> = _showCityDialog.asStateFlow()

    private var tickerJob: Job? = null
    private var prayerTickerJob: Job? = null
    private var simulationJob: Job? = null
    private var twoHourJob: Job? = null

    init {
        val database = SaatanDatabase.getDatabase(application)
        repository = SaatanRepository(database.journalDao())

        userProfile = repository.getUserProfile()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                UserProfileEntity()
            )

        todayJournal = repository.getTodayJournal()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                null
            )

        recentJournals = repository.getRecentJournals()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )

        challenges = combine(todayJournal, userProfile, _appLanguage) { journal, profile, lang ->
            buildChallengeList(journal, profile ?: UserProfileEntity(), lang)
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

        viewModelScope.launch {
            userProfile.collect { profile ->
                if (profile == null) return@collect

                _appLanguage.value = AppLanguage.fromCode(profile.languageCode)
                // Converter results are pre-formatted strings: re-render them
                // whenever the language changes so units are never stale.
                recomputeConverterResults()

                if (_isDayMode.value != profile.isDayMode) {
                    _isDayMode.value = profile.isDayMode
                }

                // Restore an in-flight 2 hour session after a process restart.
                if (profile.twoHourModeActive && twoHourJob?.isActive != true) {
                    val storedStart = profile.twoHourModeStartMs
                    if (storedStart > 0L) {
                        startTwoHourTimer(storedStart)
                    }
                }

                // Keep the daily reminder armed (or cancelled) in sync with the
                // stored preferences.
                syncDailyReminder(profile)
            }
        }

        startLiveTicker()
        loadPrayerTimes("Paris", "France")
        startPrayerCountdownTicker()
    }

    /**
     * The divine clock only changes its last displayed digit roughly every ten
     * hours of real time (one tenth of a divine second is ~10.1 earth hours),
     * so a 10 Hz ticker was burning CPU and forcing a full recomposition of
     * two canvases for nothing. One tick per second is plenty.
     */
    private fun startLiveTicker() {
        tickerJob?.cancel()
        tickerJob = viewModelScope.launch {
            while (isActive) {
                val profile = userProfile.value ?: UserProfileEntity()
                _liveDivineTime.value = DivineTimeCalculator.calculateDivineTime(
                    birthTimestampMs = profile.birthTimestampMs,
                    lifeExpectancyYears = profile.lifeExpectancyYears,
                    lang = _appLanguage.value
                )
                delay(1000)
            }
        }
    }

    private fun startPrayerCountdownTicker() {
        prayerTickerJob?.cancel()
        prayerTickerJob = viewModelScope.launch {
            while (isActive) {
                delay(1000)
                val current = _prayerState.value
                if (!current.isLoading) {
                    // Past local midnight in the selected city the cached
                    // timings belong to yesterday: refresh them instead of
                    // counting down against stale values (audit P1-1).
                    if (prayerRepository.isCacheStale()) {
                        loadPrayerTimes(current.cityName, current.countryName)
                    } else {
                        _prayerState.value = prayerRepository.recalculateCurrentCountdown(
                            city = current.cityName,
                            country = current.countryName
                        )
                    }
                }
            }
        }
    }

    fun loadPrayerTimes(city: String, country: String) {
        viewModelScope.launch {
            _prayerState.value = _prayerState.value.copy(isLoading = true)
            val updated = prayerRepository.fetchPrayerTimes(city = city, country = country)
            _prayerState.value = updated
        }
    }

    fun openCityDialog() {
        _showCityDialog.value = true
    }

    fun closeCityDialog() {
        _showCityDialog.value = false
    }

    fun selectCity(city: String, country: String) {
        _showCityDialog.value = false
        loadPrayerTimes(city, country)
    }

    fun navigateTo(screen: SaatanScreen) {
        _currentScreen.value = screen
    }

    fun openProfileDialog() {
        _showProfileDialog.value = true
    }

    fun closeProfileDialog() {
        _showProfileDialog.value = false
    }

    fun openLanguageDialog() {
        _showLanguageDialog.value = true
    }

    fun closeLanguageDialog() {
        _showLanguageDialog.value = false
    }

    fun setLanguage(language: AppLanguage) {
        _appLanguage.value = language
        _showLanguageDialog.value = false
        recomputeConverterResults()
        viewModelScope.launch {
            repository.updateLanguage(language.code)
        }
    }

    fun updateProfile(birthYear: Int, birthMonth: Int, birthDay: Int, expectancyYears: Int) {
        val cal = Calendar.getInstance()
        cal.set(birthYear, birthMonth - 1, birthDay, 0, 0, 0)
        cal.set(Calendar.MILLISECOND, 0)
        val birthMs = cal.timeInMillis

        viewModelScope.launch {
            repository.updateBirthDateAndExpectancy(birthMs, expectancyYears)
            _showProfileDialog.value = false
        }
    }

    // Journal actions
    fun toggleHabit(habitKey: String, isChecked: Boolean) {
        viewModelScope.launch {
            repository.toggleHabit(habitKey, isChecked)
        }
    }

    fun updateQuranPages(pages: Int) {
        viewModelScope.launch {
            repository.updateQuranPages(pages)
        }
    }

    fun updateJournalNote(note: String) {
        viewModelScope.launch {
            repository.updateDailyNote(note)
        }
    }

    fun tapTasbih() {
        viewModelScope.launch {
            repository.incrementTasbih()
        }
    }

    fun resetTasbihToday() {
        viewModelScope.launch {
            repository.resetTasbihToday()
        }
    }

    // Converter actions
    fun setEarthYears(v: String) {
        _earthInputYears.value = v
        recalculateEarthToDivine()
    }

    fun setEarthDays(v: String) {
        _earthInputDays.value = v
        recalculateEarthToDivine()
    }

    fun setEarthMinutes(v: String) {
        _earthInputMinutes.value = v
        recalculateEarthToDivine()
    }

    private fun recalculateEarthToDivine() {
        val y = _earthInputYears.value.toDoubleOrNull() ?: 0.0
        val d = _earthInputDays.value.toDoubleOrNull() ?: 0.0
        val m = _earthInputMinutes.value.toDoubleOrNull() ?: 0.0
        _converterResult.value = DivineTimeCalculator.earthToDivineFormatted(
            years = y,
            days = d,
            minutes = m,
            lang = _appLanguage.value
        )
    }

    /** Re-renders both converter outputs in the current language. */
    private fun recomputeConverterResults() {
        recalculateEarthToDivine()
        val m = _divineInputMinutes.value.toDoubleOrNull() ?: 0.0
        _reverseResult.value = DivineTimeCalculator.divineToEarthFormatted(
            minutes = m,
            lang = _appLanguage.value
        )
    }

    fun setDivineMinutes(v: String) {
        _divineInputMinutes.value = v
        val m = v.toDoubleOrNull() ?: 0.0
        _reverseResult.value = DivineTimeCalculator.divineToEarthFormatted(
            minutes = m,
            lang = _appLanguage.value
        )
    }

    fun applyConverterPreset(years: Double, days: Double, minutes: Double) {
        _earthInputYears.value = if (years > 0) years.toInt().toString() else "0"
        _earthInputDays.value = if (days > 0) days.toInt().toString() else "0"
        _earthInputMinutes.value = if (minutes > 0) minutes.toInt().toString() else "0"
        recalculateEarthToDivine()
    }

    // 2-Minute Life Journey Simulation Controls

    /**
     * The simulation must cover 120 units in 120 real seconds (the screen is
     * explicitly a "2 minute" experience). One tick every 100 ms therefore
     * advances the clock by 0.1 unit, not 0.2.
     */
    fun startOrResumeSimulation() {
        if (_simFinished.value) {
            _simSeconds.value = 0f
            _simFinished.value = false
        }
        _simPlaying.value = true
        simulationJob?.cancel()
        simulationJob = viewModelScope.launch {
            while (isActive && _simSeconds.value < SIMULATION_DURATION_SECONDS) {
                delay(100)
                _simSeconds.value += 0.1f
                updateMilestoneForProgress(_simSeconds.value)
            }
            if (_simSeconds.value >= SIMULATION_DURATION_SECONDS) {
                _simPlaying.value = false
                _simFinished.value = true
                _activeMilestone.value = DivineTimeCalculator.LIFE_MILESTONES.lastOrNull()
            }
        }
    }

    fun pauseSimulation() {
        _simPlaying.value = false
        simulationJob?.cancel()
        simulationJob = null
    }

    fun resetSimulation() {
        pauseSimulation()
        _simSeconds.value = 0f
        _simFinished.value = false
        _activeMilestone.value = DivineTimeCalculator.LIFE_MILESTONES.firstOrNull()
    }

    /**
     * Seeking while the simulation is running used to leave the playback job
     * alive, so the animation immediately overrode the slider position.
     */
    fun seekSimulation(targetSeconds: Float) {
        pauseSimulation()
        _simSeconds.value = targetSeconds.coerceIn(0f, SIMULATION_DURATION_SECONDS)
        _simFinished.value = (targetSeconds >= SIMULATION_DURATION_SECONDS)
        updateMilestoneForProgress(_simSeconds.value)
    }

    private fun updateMilestoneForProgress(seconds: Float) {
        val milestone = DivineTimeCalculator.LIFE_MILESTONES
            .lastOrNull { seconds >= it.divineSecondsIn120sSimulation }
            ?: DivineTimeCalculator.LIFE_MILESTONES.first()
        _activeMilestone.value = milestone
    }

    // Real 2-Hour Mode Toggle

    /**
     * The start timestamp is captured *before* the database write and handed
     * over explicitly. Reading it back from the `userProfile` flow right after
     * the write returned a stale value (the Room flow emits asynchronously),
     * which made the timer read a start date of `0` and switch itself off
     * immediately.
     */
    fun toggleTwoHourMeditationMode() {
        val currentlyActive = userProfile.value?.twoHourModeActive == true
        val newActive = !currentlyActive
        val startMs = System.currentTimeMillis()
        viewModelScope.launch {
            repository.toggleTwoHourMode(newActive, startMs)
            if (newActive) {
                startTwoHourTimer(startMs)
            } else {
                twoHourJob?.cancel()
                twoHourJob = null
                _twoHourSecondsRemaining.value = TWO_HOUR_SECONDS
            }
        }
    }

    private fun startTwoHourTimer(startMs: Long) {
        twoHourJob?.cancel()
        twoHourJob = viewModelScope.launch {
            while (isActive) {
                val elapsedSec = (System.currentTimeMillis() - startMs) / 1000L
                val remaining = maxOf(0L, TWO_HOUR_SECONDS - elapsedSec)
                _twoHourSecondsRemaining.value = remaining
                if (remaining <= 0L) {
                    repository.toggleTwoHourMode(false, 0L)
                    break
                }
                delay(1000)
            }
        }
    }

    // Day / Night theme
    fun toggleThemeMode() {
        setThemeMode(!_isDayMode.value)
    }

    fun setThemeMode(isDay: Boolean) {
        _isDayMode.value = isDay
        viewModelScope.launch {
            repository.updateThemeMode(isDay)
        }
    }

    // Notification preferences

    fun setNotificationsEnabled(enabled: Boolean) {
        viewModelScope.launch {
            repository.updateNotificationPreferences(notificationsEnabled = enabled)
        }
    }

    fun setReminderDaily(enabled: Boolean) {
        viewModelScope.launch { repository.updateNotificationPreferences(reminderSocialScroll = enabled) }
    }

    /**
     * Arms or cancels the daily 13:00 reminder depending on the stored
     * preferences. Called whenever the profile changes.
     */
    private fun syncDailyReminder(profile: UserProfileEntity) {
        val context = getApplication<Application>()
        if (profile.notificationsEnabled && profile.reminderSocialScroll) {
            val divineMinutes = run {
                val info = DivineTimeCalculator.calculateDivineTime(
                    birthTimestampMs = profile.birthTimestampMs,
                    lifeExpectancyYears = profile.lifeExpectancyYears
                )
                info.divineHours * 60.0 + info.divineMinutes + (info.divineSeconds / 60.0)
            }
            DivineNotificationHelper.scheduleDailyReminder(
                context,
                divineMinutes,
                AppLanguage.fromCode(profile.languageCode)
            )
        } else {
            DivineNotificationHelper.cancelDailyReminder(context)
        }
    }

    // Dynamic Challenge evaluation

    private fun buildChallengeList(
        journal: DailyJournalEntity?,
        profile: UserProfileEntity,
        lang: AppLanguage
    ): List<ChallengeItem> {
        val prayersCount = if (journal != null) {
            listOf(journal.fajrDone, journal.dhuhrDone, journal.asrDone, journal.maghribDone, journal.ishaDone)
                .count { it }
        } else {
            0
        }

        val quranPages = journal?.quranPagesRead ?: 0
        val sadaqa = journal?.sadaqaGiven == true
        val dhikr = journal?.morningEveningDhikr == true
        val tasbihCount = journal?.tasbihCountToday ?: 0
        val noteLength = journal?.dailyNote?.length ?: 0

        // 5 earth minutes of prayer expressed in divine seconds.
        val fiveMinutesDivine = DivineTimeCalculator.DIVINE_SECONDS_PER_EARTH_MINUTE * 5.0

        fun item(
            id: String,
            level: Int,
            title: String,
            target: String,
            emoji: String,
            divineSeconds: Double,
            progress: Int,
            max: Int,
            unlocked: Boolean,
            badge: String
        ) = ChallengeItem(
            id = id,
            levelNumber = level,
            levelName = AppStrings.challengeLevelName(lang, id),
            title = title,
            targetDescription = target,
            iconEmoji = emoji,
            divineSeconds = divineSeconds,
            progress = progress,
            maxProgress = max,
            isUnlocked = unlocked,
            badgeName = badge
        )

        return listOf(
            item(
                id = "c1_five_prayers",
                level = 1,
                title = AppStrings.challengeTitle(lang, "c1_five_prayers"),
                target = AppStrings.challengeTargetDescription(lang, "c1_five_prayers"),
                emoji = "🕌",
                divineSeconds = fiveMinutesDivine,
                progress = prayersCount,
                max = 5,
                unlocked = prayersCount == 5,
                badge = AppStrings.challengeBadgeName(lang, "c1_five_prayers")
            ),
            item(
                id = "c2_quran",
                level = 2,
                title = AppStrings.challengeTitle(lang, "c2_quran"),
                target = AppStrings.challengeTargetDescription(lang, "c2_quran"),
                emoji = "📖",
                divineSeconds = fiveMinutesDivine,
                progress = minOf(1, quranPages),
                max = 1,
                unlocked = quranPages >= 1,
                badge = AppStrings.challengeBadgeName(lang, "c2_quran")
            ),
            item(
                id = "c3_sadaqa_dhikr",
                level = 3,
                title = AppStrings.challengeTitle(lang, "c3_sadaqa_dhikr"),
                target = AppStrings.challengeTargetDescription(lang, "c3_sadaqa_dhikr"),
                emoji = "🤲",
                divineSeconds = fiveMinutesDivine * 2.0,
                progress = (if (sadaqa) 1 else 0) + (if (dhikr) 1 else 0),
                max = 2,
                unlocked = sadaqa && dhikr,
                badge = AppStrings.challengeBadgeName(lang, "c3_sadaqa_dhikr")
            ),
            item(
                id = "c4_tasbih_100",
                level = 4,
                title = AppStrings.challengeTitle(lang, "c4_tasbih_100"),
                target = AppStrings.challengeTargetDescription(lang, "c4_tasbih_100"),
                emoji = "📿",
                divineSeconds = fiveMinutesDivine * 3.0,
                progress = minOf(100, tasbihCount),
                max = 100,
                unlocked = tasbihCount >= 100,
                badge = AppStrings.challengeBadgeName(lang, "c4_tasbih_100")
            ),
            item(
                id = "c5_meditation",
                level = 5,
                title = AppStrings.challengeTitle(lang, "c5_meditation"),
                target = AppStrings.challengeTargetDescription(lang, "c5_meditation"),
                emoji = "⏳",
                divineSeconds = 108.0 * 60.0,
                progress = if (noteLength > 5) 1 else 0,
                max = 1,
                unlocked = noteLength > 5,
                badge = AppStrings.challengeBadgeName(lang, "c5_meditation")
            )
        )
    }

    override fun onCleared() {
        super.onCleared()
        tickerJob?.cancel()
        prayerTickerJob?.cancel()
        simulationJob?.cancel()
        twoHourJob?.cancel()
    }

    companion object {
        const val TWO_HOUR_SECONDS = 7200L
        const val SIMULATION_DURATION_SECONDS = 120f
    }
}
