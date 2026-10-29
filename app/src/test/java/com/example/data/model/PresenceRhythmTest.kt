package com.example.data.model

import com.example.data.localization.AppStrings
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
}
