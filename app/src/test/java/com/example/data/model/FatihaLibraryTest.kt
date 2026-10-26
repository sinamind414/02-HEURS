package com.example.data.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Contract of the Fatiha dialogue data (Sprint 5, book chapter 22).
 *
 * The screen is only a stepper: if the data drifts (a verse missing, an
 * empty answer, the hadith's exalt/glorify mapping regressed to the v7
 * prototype's swapped labels), the dialogue silently loses a step without
 * ever crashing. These locks hold the seven official steps in place.
 */
class FatihaLibraryTest {

    @Test
    fun exactlySevenVersesInSurahOrder() {
        assertEquals(7, FatihaLibrary.VERSES.size)
        FatihaLibrary.VERSES.forEachIndexed { index, verse ->
            assertEquals(index + 1, verse.n)
        }
    }

    @Test
    fun everyFieldIsCarried() {
        FatihaLibrary.VERSES.forEach { v ->
            assertTrue("blank ar @${v.n}", v.ar.isNotBlank())
            assertTrue("blank translit @${v.n}", v.translit.isNotBlank())
            assertTrue("blank translationFr @${v.n}", v.translationFr.isNotBlank())
            assertTrue("blank translationEn @${v.n}", v.translationEn.isNotBlank())
            assertTrue("blank responseQuoteAr @${v.n}", v.responseQuoteAr.isNotBlank())
            assertTrue("blank responseFr @${v.n}", v.responseFr.isNotBlank())
            assertTrue("blank responseEn @${v.n}", v.responseEn.isNotBlank())
            assertTrue("blank responseArProse @${v.n}", v.responseArProse.isNotBlank())
        }
    }

    @Test
    fun noDuplicatedAyahText() {
        val arTexts = FatihaLibrary.VERSES.map { it.ar }
        assertEquals(arTexts.size, arTexts.toSet().size)
    }

    /**
     * Hadith qudsi (Muslim n°395) wording: أثنى on verse 2 = "exalté",
     * مجّد on verse 3 = "glorifié". The v7 web prototype had the two French
     * labels swapped — the book is authoritative; this keeps them locked.
     */
    @Test
    fun exaltGlorifyMappingFollowsTheBook() {
        val v2 = FatihaLibrary.VERSES[1]
        val v3 = FatihaLibrary.VERSES[2]
        assertTrue(v2.ar.startsWith("الرَّحْمَٰنِ"))
        assertTrue(v3.ar.startsWith("مَالِكِ"))
        assertTrue(
            "v2 must be أثنى = exalté: found '${v2.responseFr}'",
            v2.responseFr.contains("exalté")
        )
        assertTrue(
            "v3 must be مجّد = glorifié: found '${v3.responseFr}'",
            v3.responseFr.contains("glorifié")
        )
        assertFalse(
            "the swapped mapping must not come back",
            v2.responseFr.contains("glorifié") || v3.responseFr.contains("exalté")
        )
    }

    @Test
    fun hadithResponsesAreArabicPersonalAddress() {
        // Each hadith response carries the "abdi" dimension — that is what
        // makes the dialogue personal (the screen's whole message).
        val endings = listOf("عَبْدِي", "عَبْدِي", "عَبْدِي", "عَبْدِي", "عَبْدِي")
        FatihaLibrary.VERSES.take(5).forEachIndexed { i, v ->
            assertTrue(
                "verse ${v.n} has no عَبْدِي in its hadith quote",
                v.responseQuoteAr.contains(endings[i]) || v.responseQuoteAr.contains("عَبْدِي")
            )
        }
    }

    @Test
    fun constantsAreNonBlank() {
        assertTrue(FatihaLibrary.BISMILLAH_AR.isNotBlank())
        assertTrue(FatihaLibrary.AMIN_AR.isNotBlank())
        assertEquals("آمِين", FatihaLibrary.AMIN_AR)
    }
}
