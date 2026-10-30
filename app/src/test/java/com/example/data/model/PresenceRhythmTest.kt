package com.example.data.model

import com.example.data.localization.AppStrings
import com.example.data.local.DailyJournalEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Contract of the Presence Rhythm US skin (29/10/2026): the light skin must
 * genuinely hide the tradition vocabulary, the enums must degrade safely on
 * garbage keys, and every new user-facing string must exist in all five
 * languages — including the crisis copy, where a missing translation would
 * be an actual safety problem.
 */
class PresenceRhythmTest {

    // ── TraditionLayer ──

    @Test
    fun traditionLayerKeysRoundTrip() {
        TraditionLayer.entries.forEach { layer ->
            assertEquals(layer, TraditionLayer.fromKey(layer.key))
        }
    }

    @Test
    fun traditionLayerGarbageKeyFallsBackToLight() {
        assertEquals(TraditionLayer.OFF, TraditionLayer.fromKey("unknown"))
        assertEquals(TraditionLayer.OFF, TraditionLayer.fromKey(""))
        assertEquals(TraditionLayer.OFF, TraditionLayer.fromKey("OFF "))
    }

    @Test
    fun lightSkinIsOnlyTheOffLayer() {
        assertTrue(TraditionLayer.OFF.isLightSkin)
        assertFalse(TraditionLayer.GRADUAL.isLightSkin)
        assertFalse(TraditionLayer.ON.isLightSkin)
    }

    // ── UsRhythm ──

    @Test
    fun usRhythmKeysRoundTripAndDefaultToBeginner() {
        UsRhythm.entries.forEach { rhythm ->
            assertEquals(rhythm, UsRhythm.fromKey(rhythm.key))
        }
        assertEquals(UsRhythm.BEGINNER, UsRhythm.fromKey("nope"))
    }

    // ── InnerWeather ──

    @Test
    fun innerWeatherHasFiveUniqueStates() {
        assertEquals(5, InnerWeather.entries.size)
        assertEquals(5, InnerWeather.entries.map { it.key }.toSet().size)
        InnerWeather.entries.forEach { mood ->
            assertTrue("blank emoji @${mood.key}", mood.emoji.isNotBlank())
        }
    }

    @Test
    fun innerWeatherUnknownKeyYieldsNullNotAWrongMood() {
        assertNull(InnerWeather.fromKey("depressed"))
        assertNull(InnerWeather.fromKey(""))
        assertNotNull(InnerWeather.fromKey("calmer"))
    }

    // ── Light names: the light-skin guarantee ──
    @Test
    fun lightReturnNamesCoverTheFiveDailyMomentsInAllLanguages() {
        val keys = listOf("fajr", "dhuhr", "asr", "maghrib", "isha")
        AppLanguage.entries.forEach { lang ->
            keys.forEach { key ->
                val name = AppStrings.lightReturnName(lang, key)
                assertTrue("blank light name $lang/$key", name.isNotBlank())
            }
        }
    }

    @Test
    fun lightReturnNamesNeverExposeTheTraditionVocabulary() {
        val banned = listOf(
            "fajr", "dhuhr", "asr", "maghrib", "isha", "salat", "salah",
            "allah", "wudu", "qibla", "prière", "oración",
            // Arabic tradition terms the light skin must not show either.
            // NB: "العصر" is deliberately absent — it is also the ordinary
            // MSA word for "afternoon", so the light name "إعادة العصر"
            // ("afternoon reset", as proposed by the strategy package) is a
            // time word, not the prayer name.
            "صلاة", "الفجر", "الظهر", "المغرب", "العشاء", "الوضوء", "القبلة"
        )
        AppLanguage.entries.forEach { lang ->
            listOf("fajr", "dhuhr", "asr", "maghrib", "isha").forEach { key ->
                val name = AppStrings.lightReturnName(lang, key).lowercase()
                banned.forEach { word ->
                    assertFalse(
                        "light name leaks '$word' ($lang/$key): $name",
                        name.contains(word)
                    )
                }
            }
        }
    }

    @Test
    fun lightReturnNameFallsBackToPrayerNameForUnknownKeys() {
        assertEquals(
            AppStrings.prayerName(AppLanguage.EN, "sunrise"),
            AppStrings.lightReturnName(AppLanguage.EN, "sunrise")
        )
    }

    // ── Onboarding + crisis copy: every language, no blank strings ──

    private fun allLanguagesNotBlank(
        value: (AppLanguage) -> String,
        label: String
    ) {
        AppLanguage.entries.forEach { lang ->
            assertTrue(
                "blank $label for ${lang.code}",
                value(lang).isNotBlank()
            )
        }
    }

    @Test
    fun onboardingCopyIsCompleteInAllFiveLanguages() {
        allLanguagesNotBlank({ AppStrings.onboardingFeelingTitle(it) }, "feelingTitle")
        allLanguagesNotBlank({ AppStrings.onboardingFeelingSubtitle(it) }, "feelingSubtitle")
        (0..4).forEach { index ->
            allLanguagesNotBlank(
                { AppStrings.onboardingFeelingOption(it, index) },
                "feelingOption$index"
            )
        }
        allLanguagesNotBlank({ AppStrings.onboardingInvitationTitle(it) }, "invitationTitle")
        allLanguagesNotBlank({ AppStrings.onboardingInvitationBody(it) }, "invitationBody")
        allLanguagesNotBlank({ AppStrings.onboardingVoiceQuote(it) }, "voiceQuote")
        allLanguagesNotBlank({ AppStrings.onboardingVoiceAuthor(it) }, "voiceAuthor")
        allLanguagesNotBlank({ AppStrings.onboardingRhythmBeginner(it) }, "rhythmBeginner")
        allLanguagesNotBlank({ AppStrings.onboardingRhythmFull(it) }, "rhythmFull")
        allLanguagesNotBlank({ AppStrings.onboardingRhythmExplore(it) }, "rhythmExplore")
        allLanguagesNotBlank({ AppStrings.onboardingLegalBody(it) }, "legalBody")
        allLanguagesNotBlank({ AppStrings.onboardingBeginBtn(it) }, "beginBtn")
    }

    @Test
    fun crisisCopyMentions988WhereTheNumberIsUSStandard() {
        listOf(AppLanguage.EN, AppLanguage.FR, AppLanguage.ES, AppLanguage.ID)
            .forEach { lang ->
                assertTrue(
                    "988 missing in crisis body (${lang.code})",
                    AppStrings.onboardingLegalBody(lang).contains("988")
                )
                assertTrue(
                    "988 missing in crisis dialog (${lang.code})",
                    AppStrings.crisisSupportBody(lang).contains("988")
                )
            }
    }

    @Test
    fun exploreHubCopyIsCompleteInAllFiveLanguages() {
        allLanguagesNotBlank({ AppStrings.exploreTitle(it) }, "exploreTitle")
        allLanguagesNotBlank({ AppStrings.exploreSubtitle(it) }, "exploreSubtitle")
        allLanguagesNotBlank({ AppStrings.exploreUnlockBtn(it) }, "exploreUnlockBtn")
        allLanguagesNotBlank({ AppStrings.exploreKeepSimpleBtn(it) }, "exploreKeepSimpleBtn")
        allLanguagesNotBlank({ AppStrings.exploreCardWhyFiveBody(it) }, "whyFiveBody")
        allLanguagesNotBlank({ AppStrings.exploreScienceDisclaimer(it) }, "scienceDisclaimer")
    }

    @Test
    fun scienceDisclaimerNeverPromisesATreatment() {
        AppLanguage.entries.forEach { lang ->
            val disclaimer = AppStrings.exploreScienceDisclaimer(lang).lowercase()
            // The disclaimer must contain a negation of treatment — an
            // affirmative claim would be a US regulatory violation (FTC).
            assertTrue(
                "disclaimer lacks negation (${lang.code}): $disclaimer",
                disclaimer.contains("not") || disclaimer.contains("n'est") ||
                    disclaimer.contains("no ") || disclaimer.contains("bukan") ||
                    disclaimer.contains("ليست") || disclaimer.contains("ليس")
            )
        }
    }

    // ── Slice 2: return cards, guided pause, gentle notifications, journal ──

    @Test
    fun slice2CopyIsCompleteInAllFiveLanguages() {
        allLanguagesNotBlank({ AppStrings.lightReturnChecklistTitle(it) }, "lightReturnChecklistTitle")
        allLanguagesNotBlank({ AppStrings.lightReturnChecklistNote(it) }, "lightReturnChecklistNote")
        allLanguagesNotBlank({ AppStrings.lightPauseNowBtn(it) }, "lightPauseNowBtn")
        allLanguagesNotBlank({ AppStrings.guidedPauseTitle(it) }, "guidedPauseTitle")
        allLanguagesNotBlank({ AppStrings.guidedPauseBreatheTitle(it) }, "guidedPauseBreatheTitle")
        allLanguagesNotBlank({ AppStrings.guidedPauseBreatheBody(it) }, "guidedPauseBreatheBody")
        allLanguagesNotBlank({ AppStrings.guidedPausePresenceTitle(it) }, "guidedPausePresenceTitle")
        allLanguagesNotBlank({ AppStrings.guidedPausePresenceBody(it) }, "guidedPausePresenceBody")
        allLanguagesNotBlank({ AppStrings.guidedPauseIntentionTitle(it) }, "guidedPauseIntentionTitle")
        allLanguagesNotBlank({ AppStrings.guidedPauseIntentionBody(it) }, "guidedPauseIntentionBody")
        allLanguagesNotBlank({ AppStrings.guidedPauseFinishBtn(it) }, "guidedPauseFinishBtn")
        allLanguagesNotBlank({ AppStrings.guidedPauseSkipBtn(it) }, "guidedPauseSkipBtn")
        // The post-pause mood reuses the Inner Weather vocabulary: it must
        // exist in every language too (it is written to moodAfterPause).
        allLanguagesNotBlank({ AppStrings.innerWeatherTitle(it) }, "innerWeatherTitle")
        allLanguagesNotBlank({ AppStrings.innerWeatherNote(it) }, "innerWeatherNote")
        InnerWeather.entries.forEach { mood ->
            allLanguagesNotBlank(
                { AppStrings.innerWeatherLabel(it, mood.key) },
                "innerWeatherLabel_${mood.key}"
            )
        }
        allLanguagesNotBlank({ AppStrings.gentleFajrNotifTitle(it) }, "gentleFajrNotifTitle")
        allLanguagesNotBlank({ AppStrings.gentleFajrNotifBody(it) }, "gentleFajrNotifBody")
        allLanguagesNotBlank({ AppStrings.gentleDhuhrNotifTitle(it) }, "gentleDhuhrNotifTitle")
        allLanguagesNotBlank({ AppStrings.gentleDhuhrNotifBody(it) }, "gentleDhuhrNotifBody")
        allLanguagesNotBlank({ AppStrings.gentleFridayNotifTitle(it) }, "gentleFridayNotifTitle")
        allLanguagesNotBlank({ AppStrings.gentleFridayNotifBody(it) }, "gentleFridayNotifBody")
        allLanguagesNotBlank({ AppStrings.gentleDailyNotifTitle(it) }, "gentleDailyNotifTitle")
        allLanguagesNotBlank({ AppStrings.gentleDailyNotifBody(it) }, "gentleDailyNotifBody")
        allLanguagesNotBlank({ AppStrings.gentleChannelName(it) }, "gentleChannelName")
        allLanguagesNotBlank({ AppStrings.gentleChannelDescription(it) }, "gentleChannelDescription")
        allLanguagesNotBlank({ AppStrings.lightHeaderSubtitle(it) }, "lightHeaderSubtitle")
        // Slice 3: the weekly presence report.
        allLanguagesNotBlank({ AppStrings.weeklyCardTitle(it) }, "weeklyCardTitle")
        allLanguagesNotBlank({ AppStrings.weeklyCardSubtitle(it) }, "weeklyCardSubtitle")
        allLanguagesNotBlank({ AppStrings.weeklyTrendIntro(it) }, "weeklyTrendIntro")
        allLanguagesNotBlank({ AppStrings.weeklyInsightIntro(it) }, "weeklyInsightIntro")
        allLanguagesNotBlank({ AppStrings.weeklyNoData(it) }, "weeklyNoData")
        allLanguagesNotBlank({ AppStrings.weeklyWhyTitle(it) }, "weeklyWhyTitle")
        allLanguagesNotBlank({ AppStrings.weeklyIntentionPrompt(it) }, "weeklyIntentionPrompt")
        allLanguagesNotBlank({ AppStrings.weeklyIntentionChip1(it) }, "weeklyIntentionChip1")
        allLanguagesNotBlank({ AppStrings.weeklyIntentionChip2(it) }, "weeklyIntentionChip2")
        allLanguagesNotBlank({ AppStrings.weeklyIntentionChip3(it) }, "weeklyIntentionChip3")
        allLanguagesNotBlank({ AppStrings.weeklyIntentionChip4(it) }, "weeklyIntentionChip4")
        allLanguagesNotBlank({ AppStrings.weeklyIntentionSaved(it) }, "weeklyIntentionSaved")
        // Slice 4: §9 cadence — the easy exit + the weekly report nudge.
        allLanguagesNotBlank({ AppStrings.notifNotNow(it) }, "notifNotNow")
        allLanguagesNotBlank({ AppStrings.weeklyNotifTitle(it) }, "weeklyNotifTitle")
        allLanguagesNotBlank({ AppStrings.weeklyNotifBody(it) }, "weeklyNotifBody")
        // Slice 5: the monthly Presence Legacy.
        allLanguagesNotBlank({ AppStrings.monthlyCardTitle(it) }, "monthlyCardTitle")
        allLanguagesNotBlank({ AppStrings.monthlyCardSubtitle(it) }, "monthlyCardSubtitle")
        allLanguagesNotBlank({ AppStrings.monthlyLegacyTitle(it) }, "monthlyLegacyTitle")
        allLanguagesNotBlank({ AppStrings.monthlyLegacyBody(it) }, "monthlyLegacyBody")
        AppLanguage.entries.forEach { lang ->
            assertTrue(
                "monthlyLegacyLocked injects the week count (${lang.code})",
                AppStrings.monthlyLegacyLocked(lang, 2).contains("2")
            )
        }
        allLanguagesNotBlank({ AppStrings.monthlyLegacyUnlock(it) }, "monthlyLegacyUnlock")
        AppLanguage.entries.forEach { lang ->
            assertTrue(
                "monthlyWeekLine injects week + count (${lang.code})",
                AppStrings.monthlyWeekLine(lang, 3, 5).let { line -> line.contains("3") && line.contains("5") }
            )
        }
        AppLanguage.entries.forEach { lang ->
            assertTrue(
                "monthlyTotalLine injects the count (${lang.code})",
                AppStrings.monthlyTotalLine(lang, 7).contains("7")
            )
        }
        allLanguagesNotBlank({ AppStrings.monthlyNoData(it) }, "monthlyNoData")
        AppLanguage.entries.forEach { lang ->
            val kept = AppStrings.weeklyKeptLine(lang, 3)
            assertTrue("weeklyKeptLine must inject the count (${lang.code}): $kept", kept.contains("3"))
            val initials = AppStrings.weeklyDayInitials(lang)
            assertTrue(
                "weeklyDayInitials must have 7 letters (${lang.code}): $initials",
                initials.length == 7
            )
            InnerWeather.entries.forEach { mood ->
                val line = AppStrings.weeklyMoodCountLine(lang, mood, 2, 5)
                assertTrue(
                    "weeklyMoodCountLine must embed label+counts (${lang.code}/${mood.key}): $line",
                    line.contains("2") && line.contains("5") &&
                        line.contains(AppStrings.innerWeatherLabel(lang, mood.key))
                )
            }
            val insight = AppStrings.weeklyInsightLine(lang, "Morning Return", 3)
            assertTrue(
                "weeklyInsightLine must inject the count (${lang.code}): $insight",
                insight.contains("3") && insight.contains("Morning Return")
            )
        }
        allLanguagesNotBlank({ AppStrings.lightJournalTitle(it) }, "lightJournalTitle")
        allLanguagesNotBlank({ AppStrings.lightJournalQuote(it) }, "lightJournalQuote")
        allLanguagesNotBlank({ AppStrings.lightJournalQuestion(it) }, "lightJournalQuestion")
        allLanguagesNotBlank({ AppStrings.lightJournalNotePlaceholder(it) }, "lightJournalNotePlaceholder")
    }

    /**
     * The light-skin guarantee, extended to slice 2: the return cards, the
     * guided pause, the gentle notifications and the light journal must
     * never expose the tradition vocabulary in ANY language — including
     * Arabic, where a missing translation would flash the original terms.
     */
    @Test
    fun gentleAndGuidedPauseCopyNeverExposeTheTraditionVocabulary() {
        val banned = listOf(
            "fajr", "dhuhr", "asr", "maghrib", "isha", "salat", "salah",
            "allah", "adhan", "wudu", "qibla", "pray", "prière", "oración",
            "shalat",
            // Arabic tradition terms (NB: "الظهر" absent — see the light
            // names test above; "وقفة الظهيرة" does not contain it).
            "صلاة", "الفجر", "المغرب", "العشاء", "الأذان", "الجمعة", "الوضوء", "القبلة"
        )
        val copies: List<Pair<String, (AppLanguage) -> String>> = listOf(
            "pauseNow" to AppStrings::lightPauseNowBtn,
            "checklistTitle" to AppStrings::lightReturnChecklistTitle,
            "checklistNote" to AppStrings::lightReturnChecklistNote,
            "guidedBreathe" to { lang ->
                AppStrings.guidedPauseBreatheTitle(lang) + " " + AppStrings.guidedPauseBreatheBody(lang)
            },
            "guidedNotice" to { lang ->
                AppStrings.guidedPausePresenceTitle(lang) + " " + AppStrings.guidedPausePresenceBody(lang)
            },
            "guidedIntention" to { lang ->
                AppStrings.guidedPauseIntentionTitle(lang) + " " + AppStrings.guidedPauseIntentionBody(lang)
            },
            "gentleFajr" to { lang ->
                AppStrings.gentleFajrNotifTitle(lang) + " " + AppStrings.gentleFajrNotifBody(lang)
            },
            "gentleDhuhr" to { lang ->
                AppStrings.gentleDhuhrNotifTitle(lang) + " " + AppStrings.gentleDhuhrNotifBody(lang)
            },
            "gentleFriday" to { lang ->
                AppStrings.gentleFridayNotifTitle(lang) + " " + AppStrings.gentleFridayNotifBody(lang)
            },
            "gentleDaily" to { lang ->
                AppStrings.gentleDailyNotifTitle(lang) + " " + AppStrings.gentleDailyNotifBody(lang)
            },
            "gentleChannel" to { lang ->
                AppStrings.gentleChannelName(lang) + " " + AppStrings.gentleChannelDescription(lang)
            },
            "lightJournalTitle" to AppStrings::lightJournalTitle,
            "lightJournalQuote" to AppStrings::lightJournalQuote,
            "lightJournalQuestion" to AppStrings::lightJournalQuestion,
            "lightJournalNote" to AppStrings::lightJournalNotePlaceholder,
            "lightHeaderSubtitle" to AppStrings::lightHeaderSubtitle,
            "weeklyCard" to { lang ->
                AppStrings.weeklyCardTitle(lang) + " " + AppStrings.weeklyCardSubtitle(lang)
            },
            "weeklyKept" to { lang -> AppStrings.weeklyKeptLine(lang, 4) },
            "weeklyTrendIntro" to AppStrings::weeklyTrendIntro,
            "weeklyInsightIntro" to AppStrings::weeklyInsightIntro,
            "weeklyNoData" to AppStrings::weeklyNoData,
            "weeklyWhy" to AppStrings::weeklyWhyTitle,
            "weeklyIntention" to { lang ->
                AppStrings.weeklyIntentionPrompt(lang) + " " +
                    AppStrings.weeklyIntentionChip1(lang) + " " +
                    AppStrings.weeklyIntentionChip2(lang) + " " +
                    AppStrings.weeklyIntentionChip3(lang) + " " +
                    AppStrings.weeklyIntentionChip4(lang) + " " +
                    AppStrings.weeklyIntentionSaved(lang)
            },
            "notifNotNow" to AppStrings::notifNotNow,
            "weeklyNotif" to { lang ->
                AppStrings.weeklyNotifTitle(lang) + " " + AppStrings.weeklyNotifBody(lang)
            },
            "monthlyCard" to { lang ->
                AppStrings.monthlyCardTitle(lang) + " " + AppStrings.monthlyCardSubtitle(lang)
            },
            "monthlyLegacy" to { lang ->
                AppStrings.monthlyLegacyTitle(lang) + " " +
                    AppStrings.monthlyLegacyBody(lang) + " " +
                    AppStrings.monthlyLegacyUnlock(lang) + " " +
                    AppStrings.monthlyTotalLine(lang, 4) + " " +
                    AppStrings.monthlyNoData(lang)
            }
        )
        AppLanguage.entries.forEach { lang ->
            copies.forEach { (label, value) ->
                val text = value(lang).lowercase()
                banned.forEach { word ->
                    assertFalse(
                        "slice2 '$label' leaks '$word' (${lang.code}): $text",
                        text.contains(word)
                    )
                }
            }
        }
    }

    // ── Weekly Presence Report (slice 3): pure aggregation ──

    private fun journalRow(
        iso: String,
        marks: List<Boolean> = listOf(false, false, false, false, false),
        mood: String = "",
        note: String = ""
    ) = DailyJournalEntity(
        dateIso = iso,
        fajrDone = marks.getOrElse(0) { false },
        dhuhrDone = marks.getOrElse(1) { false },
        asrDone = marks.getOrElse(2) { false },
        maghribDone = marks.getOrElse(3) { false },
        ishaDone = marks.getOrElse(4) { false },
        dailyNote = note,
        moodAfterPause = mood
    )

    @Test
    fun weeklyReportCountsOnlyTheCurrentWeek() {
        val report = WeeklyReport.build(
            recentJournals = listOf(
                journalRow("2026-10-20", listOf(true, true, true, true, true), "grounded"),
                journalRow("2026-10-27", listOf(true, true, false, false, false), "calmer", "one line"),
                journalRow("2026-11-03", listOf(true, false, false, false, false))
            ),
            todayIso = "2026-10-30" // Friday of the week Mon 26/10 – Sun 01/11
        )
        assertEquals("2026-10-26", report.mondayIso)
        assertEquals("2026-11-01", report.sundayIso)
        assertEquals(7, report.days.size)
        assertEquals(2, report.pausesKept)
        assertTrue(report.hasAnyData)
        // The Tuesday row sits at index 1 with its two first marks.
        val tuesday = report.days.first { it.dateIso == "2026-10-27" }
        assertEquals(1, tuesday.dayIndexFromMonday)
        assertEquals(listOf(true, true, false, false, false), tuesday.momentMarks)
        assertEquals(InnerWeather.CALMER, tuesday.moodAfter)
        assertTrue(tuesday.hasNote)
    }

    @Test
    fun weeklyMondayMathHandlesSundayAndYearEnd() {
        assertEquals("2026-10-26", WeeklyReport.currentWeekMondayIso("2026-11-01")) // Sunday
        assertEquals("2026-12-28", WeeklyReport.currentWeekMondayIso("2027-01-02")) // Saturday, next year
        assertEquals("2026-12-28", WeeklyReport.currentWeekMondayIso("2027-01-03")) // Sunday, next year
    }

    @Test
    fun weeklyReportCountsMoodsWithoutScoringThem() {
        val report = WeeklyReport.build(
            recentJournals = listOf(
                journalRow("2026-10-26", mood = "calmer"),
                journalRow("2026-10-28", mood = "calmer"),
                journalRow("2026-10-29", mood = "anxious"),
                journalRow("2026-10-30", mood = "weird") // unknown key → ignored
            ),
            todayIso = "2026-10-30"
        )
        assertEquals(2, report.moodAfterCounts[InnerWeather.CALMER])
        assertEquals(1, report.moodAfterCounts[InnerWeather.ANXIOUS])
        assertEquals(3, report.moodAfterTotal)
        assertNull(report.moodAfterCounts[InnerWeather.GROUNDED])
    }

    @Test
    fun weeklyReportTopMomentPrefersTheEarliestOnTies() {
        val report = WeeklyReport.build(
            recentJournals = listOf(
                journalRow("2026-10-26", listOf(true, true, false, false, false)),
                journalRow("2026-10-28", listOf(true, false, false, false, false))
            ),
            todayIso = "2026-10-30"
        )
        assertEquals(3, report.pausesKept)
        assertEquals("fajr", report.topMomentKey)
        assertEquals(2, report.topMomentCount)
    }

    @Test
    fun weeklyReportEmptyWeekIsSafeAndNeverJudges() {
        val report = WeeklyReport.build(emptyList(), todayIso = "2026-10-30")
        assertEquals(0, report.pausesKept)
        assertEquals(0, report.moodAfterTotal)
        assertFalse(report.hasAnyData)
        assertNull(report.topMomentKey)
        assertEquals(7, report.days.size)
        assertTrue(report.days.all { day -> day.momentMarks.none { it } && day.moodAfter == null })
    }

    @Test
    fun weeklyReportSurvivesGarbageDates() {
        val report = WeeklyReport.build(
            recentJournals = listOf(journalRow("not-a-date")),
            todayIso = "also bad"
        )
        assertEquals(7, report.days.size)
        assertTrue(report.mondayIso.isNotBlank())
        assertFalse(report.hasAnyData)
    }
}
