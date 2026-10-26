package com.example.data.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Contract of the generated book data (Sprint 4 — La Prière Goûtée).
 *
 * The source of truth is the full french book file; these tests pin the
 * structure so a regeneration cannot silently drop chapters or break the
 * Voyage bindings that other screens rely on.
 */
class BookLibraryTest {

    // -----------------------------------------------------------------
    // Structure
    // -----------------------------------------------------------------

    @Test
    fun libraryHoldsTheFivePartsOfTheBook() {
        assertEquals(
            listOf("prologue", "p1", "p2", "p3", "epilogue"),
            BookLibrary.PARTS.map { it.id }
        )
        assertEquals("La Prière Goûtée", BookLibrary.TITLE)
    }

    @Test
    fun chaptersRunFromPrologueToAppendice() {
        assertEquals(33, BookLibrary.CHAPTERS.size)
        assertEquals("prologue", BookLibrary.CHAPTERS.first().id)
        assertEquals("ch1", BookLibrary.CHAPTERS[1].id)
        assertEquals("ch30", BookLibrary.CHAPTERS[30].id)
        assertEquals("epilogue", BookLibrary.CHAPTERS[31].id)
        assertEquals("appendice", BookLibrary.CHAPTERS.last().id)
    }

    @Test
    fun chapterIdsAreUnique() {
        val ids = BookLibrary.CHAPTERS.map { it.id }
        assertEquals(ids.size, ids.toSet().size)
    }

    @Test
    fun everyChapterBelongsToAPart() {
        val partIds = BookLibrary.PARTS.map { it.id }.toSet()
        for (chapter in BookLibrary.CHAPTERS) {
            assertTrue(
                "${chapter.id} points to unknown part ${chapter.partId}",
                chapter.partId in partIds
            )
        }
    }

    @Test
    fun chapters30To1AreNumbered() {
        for (i in 1..30) {
            val chapter = BookLibrary.BY_ID["ch$i"] ?: error("missing ch$i")
            assertEquals(i, chapter.n)
        }
        assertNull(BookLibrary.BY_ID["prologue"]!!.n)
        assertNull(BookLibrary.BY_ID["appendice"]!!.n)
    }

    // -----------------------------------------------------------------
    // Timestamps / anchor inside the original episode
    // -----------------------------------------------------------------

    @Test
    fun secondsOfParsesClockStrings() {
        assertEquals(0, BookLibrary.secondsOf("00:00:00"))
        assertEquals(80, BookLibrary.secondsOf("00:01:20"))
        assertEquals(4005, BookLibrary.secondsOf("01:06:45"))
        assertEquals(7720, BookLibrary.secondsOf("02:08:40"))
    }

    @Test
    fun passageUrlUsesTheSecondsAnchor() {
        assertEquals(
            BookLibrary.VIDEO_URL + "&t=4005s",
            BookLibrary.passageUrl("01:06:45")
        )
        assertNull(BookLibrary.passageUrl(null))
    }

    @Test
    fun stampedChaptersCarryConsistentSeconds() {
        for (chapter in BookLibrary.CHAPTERS) {
            if (chapter.timestamp != null) {
                assertEquals(
                    chapter.timeSeconds.toLong(),
                    BookLibrary.secondsOf(chapter.timestamp!!).toLong()
                )
            } else {
                assertTrue(chapter.timeSeconds < 0)
            }
        }
    }

    // -----------------------------------------------------------------
    // Voyage bindings (station 1..10 + Fatiha dialogue)
    // -----------------------------------------------------------------

    @Test
    fun tenStationsAreAllCovered() {
        val sizes = (1..10).map { BookLibrary.chaptersForStation(it).size }
        assertTrue("every station must own at least one chapter", sizes.all { it > 0 })
        assertEquals(15, sizes.sum())
    }

    @Test
    fun stationFourSharesTwoChapters() {
        val ids = BookLibrary.chaptersForStation(4).map { it.id }
        assertEquals(listOf("ch19", "ch20"), ids)
    }

    @Test
    fun fatihaDialogueIsChapter22Only() {
        val fatiha = BookLibrary.CHAPTERS.filter { it.goFatiha }
        assertEquals(1, fatiha.size)
        assertEquals("ch22", fatiha.first().id)
        assertEquals(6, fatiha.first().station)
    }

    // -----------------------------------------------------------------
    // Body content sanity
    // -----------------------------------------------------------------

    @Test
    fun noBlockIsBlank() {
        for (chapter in BookLibrary.CHAPTERS) {
            for (node in chapter.body) {
                when (node) {
                    is BookNode.Line -> assertTrue(
                        "${chapter.id} has a blank line",
                        node.text.isNotBlank()
                    )
                    is BookNode.Table -> for (cell in node.cells) {
                        assertTrue(cell.text.isNotBlank())
                        assertTrue(cell.source.isNotBlank())
                    }
                }
            }
        }
    }

    @Test
    fun everyChapterHasReadableBody() {
        val totalChars = BookLibrary.CHAPTERS.sumOf { chapter ->
            chapter.body.sumOf { node ->
                when (node) {
                    is BookNode.Line -> node.text.length
                    is BookNode.Table -> node.cells.sumOf { it.text.length + it.source.length }
                }
            }
        }
        assertTrue(
            "book body looks truncated: $totalChars chars",
            totalChars > 30_000
        )
    }

    @Test
    fun appendiceListsThe32SourcedEntries() {
        val rows = BookLibrary.CHAPTERS.last()
            .body.filterIsInstance<BookNode.Table>()
            .flatMap { it.cells }
        assertEquals(32, rows.size)
        assertTrue((1..32).toList() == rows.map { it.number })
        assertTrue("first appendix entry must name the first accounting", rows.first().text.contains("La première comptabilité"))
    }

    @Test
    fun quotesAndBoldMarkersArePreservedForTheRenderer() {
        // ch16 — the wudu' water — keeps its hadith quote and the bullet list.
        val ch16 = BookLibrary.BY_ID["ch16"]!!
        assertTrue(ch16.body.any { it is BookNode.Line && it.kind == BookLineKind.QUOTE })
        assertTrue(ch16.body.count { it is BookNode.Line && it.kind == BookLineKind.BULLET } >= 5)
        assertTrue(ch16.body.any { it is BookNode.Line && it.text.contains("**") })

        // The prologue keeps the epigraph quote about empty worship.
        val prologue = BookLibrary.BY_ID["prologue"]!!
        assertTrue(prologue.body.any { it is BookNode.Line && it.kind == BookLineKind.QUOTE })

        // The epilogue keeps a readable closing body.
        val epilogue = BookLibrary.BY_ID["epilogue"]!!
        assertTrue(epilogue.body.isNotEmpty())
    }
}
