package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.localization.AppStrings
import com.example.data.model.AppLanguage
import com.example.data.model.BookChapter
import com.example.data.model.BookLibrary
import com.example.data.model.BookLineKind
import com.example.data.model.BookNode
import com.example.ui.MainViewModel
import com.example.ui.SaatanScreen
import com.example.ui.components.CelestialCard
import com.example.ui.theme.AmiriFontFamily
import com.example.ui.theme.CinzelFontFamily
import com.example.ui.theme.DawnCream800
import com.example.ui.theme.DawnCream950
import com.example.ui.theme.DawnTextPrimary
import com.example.ui.theme.DawnTextSecondary
import com.example.ui.theme.GoldBorder
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.LocalSaatanThemeIsDay
import com.example.ui.theme.Midnight800
import com.example.ui.theme.Midnight900
import com.example.ui.theme.Midnight950
import com.example.ui.theme.TextGold
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

/**
 * LE LIVRE — "La Prière Goûtée" (Sprint 4).
 *
 * The 33 chapters of the Fāhem 36 episode book, static data in
 * [BookLibrary]. Two panes: the sommaire (parts + chapter rows) and the
 * reader (borrowed from the web prototype — timestamped anchors jump to
 * the original episode, every voyage-bound chapter links to its station).
 */
@Composable
fun BookScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val language by viewModel.appLanguage.collectAsState()
    val isDayMode = LocalSaatanThemeIsDay.current
    val pending = viewModel.pendingBookChapter.collectAsState()

    var openChapterId by rememberSaveable { mutableStateOf<String?>(null) }

    // Deep entry (station chip, intent hook): focus the reader, then consume.
    LaunchedEffect(pending.value) {
        val wanted = pending.value
        if (wanted != null) {
            openChapterId = wanted
            viewModel.consumePendingBookChapter()
        }
    }

    // System back: first back press closes the chapter, the second leaves the book.
    BackHandler(enabled = openChapterId != null) { openChapterId = null }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(if (isDayMode) DawnCream950 else Midnight950)
            .testTag("book_screen")
    ) {
        if (openChapterId == null) {
            BookIndex(
                language = language,
                isDayMode = isDayMode,
                onOpen = { openChapterId = it }
            )
        } else {
            val index = BookLibrary.indexOf(openChapterId)
            if (index >= 0) {
                BookReader(
                    viewModel = viewModel,
                    language = language,
                    isDayMode = isDayMode,
                    chapter = BookLibrary.CHAPTERS[index],
                    chapterIndex = index,
                    onBack = { openChapterId = null },
                    onOpenChapter = { openChapterId = it }
                )
            } else {
                // Malformed id (regenerated data / stale save): fall back to the sommaire.
                LaunchedEffect(openChapterId) { openChapterId = null }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────
// Sommaire
// ─────────────────────────────────────────────────────────────────

@Composable
private fun BookIndex(
    language: AppLanguage,
    isDayMode: Boolean,
    onOpen: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("book_index"),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(bottom = 96.dp)
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 20.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.AutoStories,
                        contentDescription = null,
                        tint = GoldPrimary,
                        modifier = Modifier.size(26.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = AppStrings.bookScreenTitle(language),
                        fontFamily = CinzelFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 19.sp,
                        letterSpacing = 1.sp,
                        color = GoldLight
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = AppStrings.bookScreenSubtitle(language, BookLibrary.CHAPTERS.size),
                    fontFamily = AmiriFontFamily,
                    fontSize = 12.5.sp,
                    lineHeight = 19.sp,
                    color = if (isDayMode) DawnTextSecondary else TextSecondary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = AppStrings.bookIndexTitle(language),
                    fontFamily = CinzelFontFamily,
                    fontSize = 10.sp,
                    letterSpacing = 2.5.sp,
                    color = GoldPrimary
                )
            }
        }

        BookLibrary.PARTS.forEach { part ->
            // Part heading: the book's own typography, kept as data.
            item(key = "part_${part.id}") {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = part.kindLabel,
                        fontFamily = CinzelFontFamily,
                        fontSize = 10.5.sp,
                        letterSpacing = 2.sp,
                        color = GoldPrimary
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = part.title,
                        fontFamily = CinzelFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        letterSpacing = 0.6.sp,
                        color = GoldLight
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(GoldPrimary.copy(alpha = 0.35f))
                    )
                }
            }

            items(
                BookLibrary.CHAPTERS.filter { it.partId == part.id },
                key = { it.id }
            ) { chapter ->
                BookChapterRow(
                    language = language,
                    isDayMode = isDayMode,
                    chapter = chapter,
                    onOpen = onOpen
                )
            }
        }

        // Note du libraire + archival footer.
        item {
            CelestialCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                backgroundColor = if (isDayMode) {
                    androidx.compose.ui.graphics.Color.White.copy(alpha = 0.97f)
                } else {
                    Midnight900.copy(alpha = 0.95f)
                },
                borderColor = GoldBorder
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = AppStrings.bookNoteTitle(language),
                        fontFamily = CinzelFontFamily,
                        fontSize = 10.5.sp,
                        letterSpacing = 2.sp,
                        color = GoldPrimary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = BookLibrary.NOTE_OF_LIBRAIRIE,
                        fontFamily = AmiriFontFamily,
                        fontSize = 12.sp,
                        lineHeight = 19.sp,
                        color = if (isDayMode) DawnTextSecondary else TextSecondary,
                        style = TextStyle(textDirection = TextDirection.Ltr)
                    )
                }
            }
            Text(
                text = BookLibrary.FOOTER_NOTE,
                fontFamily = AmiriFontFamily,
                fontSize = 10.sp,
                lineHeight = 15.sp,
                color = TextMuted,
                textAlign = TextAlign.Center,
                style = TextStyle(textDirection = TextDirection.Ltr),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp, vertical = 14.dp)
            )
        }
    }
}

@Composable
private fun BookChapterRow(
    language: AppLanguage,
    isDayMode: Boolean,
    chapter: BookChapter,
    onOpen: (String) -> Unit
) {
    CelestialCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 5.dp)
            .clickable { onOpen(chapter.id) }
            .testTag("book_row_${chapter.id}"),
        backgroundColor = if (isDayMode) {
            androidx.compose.ui.graphics.Color.White.copy(alpha = 0.97f)
        } else {
            Midnight900.copy(alpha = 0.95f)
        },
        borderColor = GoldBorder
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 13.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(GoldPrimary.copy(alpha = 0.14f))
                    .border(1.dp, GoldPrimary.copy(alpha = 0.55f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.AutoStories,
                    contentDescription = null,
                    tint = TextGold,
                    modifier = Modifier.size(14.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = chapterLabel(language, chapter),
                    fontFamily = CinzelFontFamily,
                    fontSize = 10.sp,
                    letterSpacing = 1.sp,
                    color = GoldPrimary
                )
                Text(
                    text = chapter.title,
                    fontFamily = AmiriFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.5.sp,
                    lineHeight = 19.sp,
                    color = if (isDayMode) DawnTextPrimary else TextPrimary,
                    style = TextStyle(textDirection = TextDirection.Ltr)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            if (chapter.station != null) {
                Text(
                    text = chapter.station.toString(),
                    fontFamily = CinzelFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    color = TextGold,
                    modifier = Modifier
                        .clip(CircleShape)
                        .border(1.dp, GoldPrimary.copy(alpha = 0.55f), CircleShape)
                        .padding(horizontal = 7.dp, vertical = 3.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
            }
            if (chapter.timestamp != null) {
                Text(
                    text = chapter.timestamp,
                    fontFamily = CinzelFontFamily,
                    fontSize = 9.5.sp,
                    color = TextMuted
                )
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────
// Lecteur
// ─────────────────────────────────────────────────────────────────

@Composable
private fun BookReader(
    viewModel: MainViewModel,
    language: AppLanguage,
    isDayMode: Boolean,
    chapter: BookChapter,
    chapterIndex: Int,
    onBack: () -> Unit,
    onOpenChapter: (String) -> Unit
) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("book_reader"),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(bottom = 96.dp)
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 14.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = AppStrings.bookBackToIndex(language),
                        tint = GoldLight,
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable { onBack() }
                            .padding(10.dp)
                            .size(24.dp)
                            .testTag("book_back")
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = AppStrings.bookIndexTitle(language),
                        fontFamily = CinzelFontFamily,
                        fontSize = 10.sp,
                        letterSpacing = 2.5.sp,
                        color = GoldPrimary,
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .clickable { onBack() }
                            .padding(horizontal = 6.dp, vertical = 3.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))

                    // Anchor inside the original episode: jumps to the t=<sec> mark.
                    if (chapter.timestamp != null) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .background(if (isDayMode) DawnCream800 else Midnight800)
                                .border(1.dp, GoldBorder, RoundedCornerShape(50))
                                .clickable {
                                    try {
                                        context.startActivity(
                                            Intent(
                                                Intent.ACTION_VIEW,
                                                Uri.parse(BookLibrary.passageUrl(chapter.timestamp))
                                            )
                                        )
                                    } catch (_: Exception) {
                                        // no browser: the chip stays decorative
                                    }
                                }
                                .padding(horizontal = 9.dp, vertical = 5.dp)
                                .testTag("book_passage_link")
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayCircle,
                                contentDescription = AppStrings.bookListenPassage(language),
                                tint = GoldPrimary,
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = chapter.timestamp,
                                fontFamily = CinzelFontFamily,
                                fontSize = 10.sp,
                                color = GoldLight
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = chapterLabel(language, chapter),
                    fontFamily = CinzelFontFamily,
                    fontSize = 11.sp,
                    letterSpacing = 2.sp,
                    color = GoldPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = chapter.title,
                    fontFamily = CinzelFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    lineHeight = 27.sp,
                    letterSpacing = 0.4.sp,
                    color = GoldLight,
                    style = TextStyle(textDirection = TextDirection.Ltr)
                )

                // Voyage bindings of the chapter.
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(top = 10.dp)
                ) {
                    if (chapter.station != null) {
                        FeatureChip(
                            text = AppStrings.bookStationChip(language, chapter.station),
                            testTag = "book_station_chip",
                            onClick = { viewModel.navigateTo(SaatanScreen.JOURNEY) }
                        )
                    }
                    if (chapter.goFatiha) {
                        FeatureChip(
                            text = AppStrings.bookFatihaChip(language),
                            testTag = "fatiha_book_chip",
                            onClick = { viewModel.openFatiha() },
                            emphasized = true
                        )
                    }
                }
            }
        }

        items(chapter.body) { node ->
            when (node) {
                is BookNode.Line -> when (node.kind) {
                    BookLineKind.PARAGRAPH -> BookBodyText(
                        text = node.text,
                        isDayMode = isDayMode,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 6.dp)
                    )
                    BookLineKind.QUOTE -> BookQuoteBlock(
                        text = node.text,
                        isDayMode = isDayMode,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 18.dp, vertical = 6.dp)
                    )
                    BookLineKind.BULLET -> Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            text = "•",
                            color = GoldPrimary,
                            fontSize = 13.5.sp,
                            modifier = Modifier.width(14.dp)
                        )
                        BookBodyText(
                            text = node.text,
                            isDayMode = isDayMode,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                is BookNode.Table -> BookAppendixTable(
                    table = node,
                    isDayMode = isDayMode,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                )
            }
        }

        if (chapter.id == BookLibrary.CHAPTERS.last().id) {
            item {
                Text(
                    text = BookLibrary.FOOTER_NOTE,
                    fontFamily = AmiriFontFamily,
                    fontSize = 10.sp,
                    lineHeight = 15.sp,
                    color = TextMuted,
                    textAlign = TextAlign.Center,
                    style = TextStyle(textDirection = TextDirection.Ltr),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 26.dp, vertical = 12.dp)
                )
            }
        }

        // Prev / next — the reader is one continuous ribbon across the parts.
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 18.dp)
            ) {
                val hasPrev = chapterIndex > 0
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isDayMode) DawnCream800 else Midnight800)
                        .border(1.dp, GoldBorder, RoundedCornerShape(12.dp))
                        .clickable(enabled = hasPrev) {
                            onOpenChapter(BookLibrary.CHAPTERS[chapterIndex - 1].id)
                        }
                        .padding(vertical = 12.dp)
                        .testTag("book_prev")
                ) {
                    Text(
                        text = "< " + AppStrings.bookPrev(language),
                        fontFamily = CinzelFontFamily,
                        fontSize = 11.5.sp,
                        color = if (hasPrev) GoldLight else TextMuted
                    )
                }
                val hasNext = chapterIndex < BookLibrary.CHAPTERS.lastIndex
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isDayMode) DawnCream800 else Midnight800)
                        .border(1.dp, GoldBorder, RoundedCornerShape(12.dp))
                        .clickable(enabled = hasNext) {
                            onOpenChapter(BookLibrary.CHAPTERS[chapterIndex + 1].id)
                        }
                        .padding(vertical = 12.dp)
                        .testTag("book_next")
                ) {
                    Text(
                        text = AppStrings.bookNext(language) + " >",
                        fontFamily = CinzelFontFamily,
                        fontSize = 11.5.sp,
                        color = if (hasNext) GoldLight else TextMuted
                    )
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────
// Text parcels
// ─────────────────────────────────────────────────────────────────

/** Chapter display label: localized "Chapitre N" or the book specials. */
private fun chapterLabel(language: AppLanguage, chapter: BookChapter): String = when {
    chapter.n != null -> AppStrings.bookChapterN(language, chapter.n)
    chapter.id == "prologue" -> AppStrings.bookPrologue(language)
    chapter.id == "epilogue" -> AppStrings.bookEpilogue(language)
    else -> AppStrings.bookAppendice(language)
}

/** Book prose (French in every app language). `**bold**` / `*italic*`. */
@Composable
private fun BookBodyText(
    text: String,
    isDayMode: Boolean,
    modifier: Modifier = Modifier
) {
    Text(
        text = annotatedBookText(text),
        fontFamily = AmiriFontFamily,
        fontSize = 13.5.sp,
        lineHeight = 21.5.sp,
        color = if (isDayMode) DawnTextPrimary else TextPrimary,
        style = TextStyle(textDirection = TextDirection.Ltr),
        modifier = modifier
    )
}

/** Gold bar quote — the book's quoted hadiths / verses. */
@Composable
private fun BookQuoteBlock(
    text: String,
    isDayMode: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(if (isDayMode) GoldPrimary.copy(alpha = 0.07f) else Midnight800.copy(alpha = 0.85f))
            .border(1.dp, GoldBorder, RoundedCornerShape(10.dp))
    ) {
        Row(modifier = Modifier.height(IntrinsicSize.Min)) {
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .fillMaxHeight()
                    .background(GoldPrimary.copy(alpha = 0.65f))
            )
            Text(
                text = annotatedBookText(text),
                fontFamily = AmiriFontFamily,
                fontSize = 13.5.sp,
                lineHeight = 21.5.sp,
                color = GoldLight,
                style = TextStyle(textDirection = TextDirection.Ltr),
                modifier = Modifier
                    .padding(start = 11.dp, end = 14.dp, top = 10.dp, bottom = 10.dp)
            )
        }
    }
}

/** The appendix: 32 sourced entries in a bordered list. */
@Composable
private fun BookAppendixTable(
    table: BookNode.Table,
    isDayMode: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(if (isDayMode) androidx.compose.ui.graphics.Color.White.copy(alpha = 0.97f) else Midnight900.copy(alpha = 0.95f))
            .border(1.dp, GoldBorder, RoundedCornerShape(10.dp))
    ) {
        table.cells.forEach { cell ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 13.dp, vertical = 8.dp)
            ) {
                Text(
                    text = cell.number.toString(),
                    fontFamily = CinzelFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = GoldPrimary,
                    modifier = Modifier.width(26.dp)
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = annotatedBookText(cell.text),
                        fontFamily = AmiriFontFamily,
                        fontSize = 12.5.sp,
                        lineHeight = 18.sp,
                        color = if (isDayMode) DawnTextPrimary else TextPrimary,
                        style = TextStyle(textDirection = TextDirection.Ltr)
                    )
                    Text(
                        text = annotatedBookText(cell.source),
                        fontFamily = AmiriFontFamily,
                        fontSize = 9.5.sp,
                        lineHeight = 13.sp,
                        color = TextMuted,
                        style = TextStyle(textDirection = TextDirection.Ltr)
                    )
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────
// Mini-markdown (**bold**, *italic*) parsed at render time
// ─────────────────────────────────────────────────────────────────

private data class BookSpan(val text: String, val bold: Boolean, val italic: Boolean)

private fun parseBookSpans(text: String): List<BookSpan> {
    val spans = ArrayList<BookSpan>()
    var bold = false
    var italic = false
    val current = StringBuilder()
    var i = 0
    while (i < text.length) {
        val c = text[i]
        if (c == '*') {
            val next = if (i + 1 < text.length) text[i + 1] else ' '
            if (next == '*') {
                if (current.isNotEmpty()) {
                    spans.add(BookSpan(current.toString(), bold, italic))
                    current.setLength(0)
                }
                bold = !bold
                i += 2
            } else {
                if (current.isNotEmpty()) {
                    spans.add(BookSpan(current.toString(), bold, italic))
                    current.setLength(0)
                }
                italic = !italic
                i += 1
            }
        } else {
            current.append(c)
            i += 1
        }
    }
    if (current.isNotEmpty()) spans.add(BookSpan(current.toString(), bold, italic))
    return spans.filter { it.text.isNotEmpty() }
}

private fun annotatedBookText(text: String): AnnotatedString = buildAnnotatedString {
    for (span in parseBookSpans(text)) {
        pushStyle(
            SpanStyle(
                fontWeight = if (span.bold) FontWeight.Bold else null,
                fontStyle = if (span.italic) FontStyle.Italic else null
            )
        )
        append(span.text)
        pop()
    }
}
