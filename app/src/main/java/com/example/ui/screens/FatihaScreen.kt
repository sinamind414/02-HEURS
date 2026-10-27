package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.localization.AppStrings
import com.example.data.model.AppLanguage
import com.example.data.model.FatihaLibrary
import com.example.data.model.FatihaVerse
import com.example.ui.MainViewModel
import com.example.ui.SaatanScreen
import com.example.ui.components.CelestialCard
import com.example.ui.theme.AmiriFontFamily
import com.example.ui.theme.CinzelFontFamily
import com.example.ui.theme.DawnCream700
import com.example.ui.theme.DawnCream800
import com.example.ui.theme.DawnCream950
import com.example.ui.theme.DawnGoldLight
import com.example.ui.theme.DawnGoldPrimary
import com.example.ui.theme.DawnTextMuted
import com.example.ui.theme.DawnTextPrimary
import com.example.ui.theme.DawnTextSecondary
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.LocalSaatanThemeIsDay
import com.example.ui.theme.Midnight700
import com.example.ui.theme.Midnight800
import com.example.ui.theme.Midnight900
import com.example.ui.theme.Midnight950
import com.example.ui.theme.TextGold
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

/**
 * LE DIALOGUE DE LA FATIHA — Sprint 5 (book chapter 22, hadith qudsi
 * Muslim n°395).
 *
 * A step-by-step dialogue: an intro card (the Bismillah, the invitation),
 * then one card per verse (the ayah you say, Allah's answer from the hadith
 * qudsi, a pause invitation), then the Âmin finale. An 8-dot progression bar
 * lets the user jump to any verse; three advice cards close the screen.
 *
 * Ported from the v7 web prototype (`Fatiha.tsx`); verse data lives in
 * [FatihaLibrary], chrome strings in [AppStrings]. Not a bottom tab — it is
 * reached from book chapter 22's chip, from Voyage station 6, or via the
 * `dhawq_open fatiha` intent hook.
 */
@Composable
fun FatihaScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val language by viewModel.appLanguage.collectAsState()
    val isDayMode = LocalSaatanThemeIsDay.current

    // 0 = intro, 1..7 = verses, 8 = Âmin finale.
    var step by rememberSaveable { mutableIntStateOf(0) }

    // In-screen back: one press falls back to the intro card; the next press
    // (handled by SaatanAppContent) leaves the screen.
    BackHandler(enabled = step > 0) { step = 0 }

    val bg = if (isDayMode) DawnCream950 else Midnight950
    val cardBg = if (isDayMode) DawnCream800 else Midnight900
    val innerBg = if (isDayMode) DawnCream700 else Midnight900.copy(alpha = 0.55f)
    val noteBg = if (isDayMode) DawnCream700 else Midnight800.copy(alpha = 0.55f)
    val accent = if (isDayMode) DawnGoldPrimary else GoldPrimary
    val goldLight = if (isDayMode) DawnGoldLight else TextGold
    val text = if (isDayMode) DawnTextPrimary else TextPrimary
    val textSoft = if (isDayMode) DawnTextSecondary else TextSecondary
    val textFaint = if (isDayMode) DawnTextMuted else TextMuted
    val dotTodo = if (isDayMode) {
        com.example.ui.theme.DawnCream600
    } else {
        Midnight700
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(bg)
            .testTag("fatiha_screen"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ── Top bar: back to the divine clock ────────────────────────────
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = AppStrings.fatihaBack(language),
                    tint = goldLight,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { viewModel.navigateTo(SaatanScreen.DIVINE_CLOCK) }
                        .padding(10.dp)
                        .size(24.dp)
                        .testTag("fatiha_back_home")
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = AppStrings.fatihaTopBarLabel(language),
                    fontFamily = CinzelFontFamily,
                    fontSize = 10.sp,
                    letterSpacing = 2.sp,
                    color = accent,
                    style = androidx.compose.ui.text.TextStyle(
                        textDirection = TextDirection.Ltr
                    ),
                    modifier = Modifier.testTag("fatiha_top_label")
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }

        // ── Header: the invitation ───────────────────────────────────────
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = AppStrings.fatihaStationLabel(language),
                    fontFamily = CinzelFontFamily,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.8.sp,
                    textAlign = TextAlign.Center,
                    color = accent,
                    style = androidx.compose.ui.text.TextStyle(
                        textDirection = TextDirection.Ltr
                    )
                )
                Text(
                    text = AppStrings.fatihaTitlePart1(language),
                    fontFamily = CinzelFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 26.sp,
                    lineHeight = 34.sp,
                    textAlign = TextAlign.Center,
                    color = text
                )
                Text(
                    text = AppStrings.fatihaTitlePart2(language),
                    fontFamily = CinzelFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 26.sp,
                    lineHeight = 34.sp,
                    textAlign = TextAlign.Center,
                    color = goldLight
                )
                Text(
                    text = AppStrings.fatihaSubtitle(language),
                    fontSize = 12.5.sp,
                    lineHeight = 19.sp,
                    textAlign = TextAlign.Center,
                    color = textSoft,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        // ── Progression: 8 dots (intro + 7 verses); the finale lights all ─
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(3.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp, vertical = 10.dp)
            ) {
                repeat(8) { dot ->
                    val done = step > dot || step == 8
                    val active = step == dot
                    val color = when {
                        done -> accent
                        active -> goldLight
                        else -> dotTodo
                    }
                    // The 5 dp bar is drawn inside a >=44 dp touch cell —
                    // the visible bar stays slim, the hit area respects the
                    // Android/WCAG target minimum.
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .heightIn(min = 44.dp)
                            .clickable { step = dot }
                            .testTag("fatiha_dot_$dot"),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(5.dp)
                                .clip(RoundedCornerShape(50))
                                .background(color)
                        )
                    }
                }
            }
            // The finale step can only be reached through verse 7's button —
            // the eighth dot maps to the last verse, as in the prototype.
        }

        // ── The card of the current step ─────────────────────────────────
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 640.dp)
                    .padding(horizontal = 18.dp, vertical = 8.dp)
            ) {
                when {
                    step == 0 -> FatihaIntroCard(
                        language = language,
                        isDayMode = isDayMode,
                        cardBg = cardBg,
                        accent = accent,
                        goldLight = goldLight,
                        text = text,
                        textSoft = textSoft,
                        onStart = { step = 1 }
                    )
                    step == 8 -> FatihaFinaleCard(
                        language = language,
                        isDayMode = isDayMode,
                        cardBg = cardBg,
                        accent = accent,
                        goldLight = goldLight,
                        text = text,
                        textSoft = textSoft,
                        onRestart = { step = 0 }
                    )
                    else -> FatihaVerseCard(
                        verse = FatihaLibrary.VERSES[step - 1],
                        language = language,
                        isDayMode = isDayMode,
                        cardBg = cardBg,
                        innerBg = innerBg,
                        noteBg = noteBg,
                        accent = accent,
                        goldLight = goldLight,
                        text = text,
                        textSoft = textSoft,
                        textFaint = textFaint,
                        onBack = { step = step - 1 },
                        onNext = { step = step + 1 }
                    )
                }
            }
        }

        // ── The three advices (always visible, as in the prototype) ──────
        items(count = 1) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 640.dp)
                    .padding(horizontal = 22.dp, vertical = 6.dp)
            ) {
                listOf(
                    AppStrings.fatihaAdvice1Title(language) to
                        AppStrings.fatihaAdvice1Desc(language),
                    AppStrings.fatihaAdvice2Title(language) to
                        AppStrings.fatihaAdvice2Desc(language),
                    AppStrings.fatihaAdvice3Title(language) to
                        AppStrings.fatihaAdvice3Desc(language)
                ).forEach { (title, desc) ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .background(cardBg.copy(alpha = 0.65f))
                            .border(
                                1.dp,
                                accent.copy(alpha = 0.18f),
                                RoundedCornerShape(14.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        Text(
                            text = title,
                            fontFamily = CinzelFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp,
                            color = goldLight,
                            style = androidx.compose.ui.text.TextStyle(
                                textDirection = TextDirection.Ltr
                            )
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = desc,
                            fontSize = 11.sp,
                            lineHeight = 16.sp,
                            color = textFaint
                        )
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(28.dp)) }
    }
}

// ─────────────────────────────────────────────────────────────────────────
// Intro card
// ─────────────────────────────────────────────────────────────────────────

@Composable
private fun FatihaIntroCard(
    language: AppLanguage,
    isDayMode: Boolean,
    cardBg: Color,
    accent: Color,
    goldLight: Color,
    text: Color,
    textSoft: Color,
    onStart: () -> Unit
) {
    CelestialCard(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("fatiha_intro_card"),
        borderColor = accent.copy(alpha = 0.30f),
        backgroundColor = cardBg
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp, vertical = 28.dp)
        ) {
            Text(
                text = FatihaLibrary.BISMILLAH_AR,
                fontFamily = AmiriFontFamily,
                fontSize = 26.sp,
                lineHeight = 40.sp,
                textAlign = TextAlign.Center,
                color = goldLight,
                style = androidx.compose.ui.text.TextStyle(
                    textDirection = TextDirection.Ltr
                )
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = AppStrings.fatihaIntroTranslit(language),
                fontSize = 11.5.sp,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                textAlign = TextAlign.Center,
                color = textSoft
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = AppStrings.fatihaIntroBody(language),
                fontSize = 13.5.sp,
                lineHeight = 21.sp,
                textAlign = TextAlign.Center,
                color = text
            )
            Spacer(modifier = Modifier.height(22.dp))
            Text(
                text = AppStrings.fatihaStart(language),
                fontFamily = CinzelFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color = if (isDayMode) DawnCream950 else Midnight950,
                style = androidx.compose.ui.text.TextStyle(
                    textDirection = TextDirection.Ltr
                ),
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(accent)
                    .clickable(
                        onClick = onStart,
                        onClickLabel = AppStrings.fatihaStart(language)
                    )
                    .padding(horizontal = 22.dp, vertical = 11.dp)
                    .testTag("fatiha_start")
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────
// Verse card — the "Tu dis / Il répond" exchange
// ─────────────────────────────────────────────────────────────────────────

@Composable
private fun FatihaVerseCard(
    verse: FatihaVerse,
    language: AppLanguage,
    isDayMode: Boolean,
    cardBg: Color,
    innerBg: Color,
    noteBg: Color,
    accent: Color,
    goldLight: Color,
    text: Color,
    textSoft: Color,
    textFaint: Color,
    onBack: () -> Unit,
    onNext: () -> Unit
) {
    CelestialCard(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("fatiha_verse_card_${verse.n}"),
        borderColor = accent.copy(alpha = 0.16f),
        backgroundColor = cardBg.copy(alpha = 0.55f)
    ) {
        Column {
            // The ayah you say
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(innerBg)
                    .padding(horizontal = 20.dp, vertical = 26.dp)
            ) {
                Text(
                    text = AppStrings.fatihaYouSay(language, verse.n).uppercase(),
                    fontFamily = CinzelFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    letterSpacing = 1.6.sp,
                    textAlign = TextAlign.Center,
                    color = textFaint,
                    style = androidx.compose.ui.text.TextStyle(
                        textDirection = TextDirection.Ltr
                    )
                )
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = verse.ar,
                    fontFamily = AmiriFontFamily,
                    fontSize = 27.sp,
                    lineHeight = 44.sp,
                    textAlign = TextAlign.Center,
                    color = text,
                    style = androidx.compose.ui.text.TextStyle(
                        textDirection = TextDirection.Ltr
                    ),
                    modifier = Modifier.testTag("fatiha_verse_ar_${verse.n}")
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = verse.translit,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    color = accent,
                    modifier = Modifier.testTag("fatiha_verse_translit_${verse.n}")
                )
                Spacer(modifier = Modifier.height(2.dp))
                // In the Arabic UI the ayah itself is shown above: no gloss.
                if (language != AppLanguage.AR) {
                    Text(
                        text = if (language == AppLanguage.EN) {
                            verse.translationEn
                        } else {
                            verse.translationFr
                        },
                        fontSize = 12.5.sp,
                        lineHeight = 18.sp,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        textAlign = TextAlign.Center,
                        color = textSoft
                    )
                }
            }

            // The dialog hinge
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 1.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(if (isDayMode) DawnCream950 else Midnight950)
                        .border(1.dp, accent.copy(alpha = 0.5f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.QuestionAnswer,
                        contentDescription = null,
                        tint = accent,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            // Allah's answer
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 18.dp)
            ) {
                Text(
                    text = AppStrings.fatihaHeAnswers(language).uppercase(),
                    fontFamily = CinzelFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    letterSpacing = 1.6.sp,
                    textAlign = TextAlign.Center,
                    color = accent,
                    style = androidx.compose.ui.text.TextStyle(
                        textDirection = TextDirection.Ltr
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = verse.responseQuoteAr,
                    fontFamily = AmiriFontFamily,
                    fontSize = 21.sp,
                    lineHeight = 34.sp,
                    textAlign = TextAlign.Center,
                    color = goldLight,
                    style = androidx.compose.ui.text.TextStyle(
                        textDirection = TextDirection.Ltr
                    ),
                    modifier = Modifier.testTag("fatiha_response_${verse.n}")
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = when (language) {
                        AppLanguage.AR -> verse.responseArProse
                        AppLanguage.EN -> verse.responseEn
                        AppLanguage.ES, AppLanguage.ID -> verse.responseEn
                        AppLanguage.FR -> verse.responseFr
                    },
                    fontFamily = CinzelFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    lineHeight = 25.sp,
                    textAlign = TextAlign.Center,
                    color = text,
                    modifier = Modifier.testTag("fatiha_response_prose_${verse.n}")
                )
                Spacer(modifier = Modifier.height(16.dp))

                // The pause invitation — where the prayer is tasted
                Row(
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(noteBg)
                        .padding(horizontal = 14.dp, vertical = 12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.FormatQuote,
                        contentDescription = null,
                        tint = accent,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = AppStrings.fatihaPauseNote(language),
                        fontSize = 10.5.sp,
                        lineHeight = 16.sp,
                        color = textFaint
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                // Navigation
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = AppStrings.fatihaBack(language),
                        fontFamily = CinzelFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        color = textSoft,
                        style = androidx.compose.ui.text.TextStyle(
                            textDirection = TextDirection.Ltr
                        ),
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .border(
                                1.dp,
                                accent.copy(alpha = 0.35f),
                                RoundedCornerShape(50)
                            )
                            .clickable(
                                onClick = onBack,
                                onClickLabel = AppStrings.fatihaBack(language)
                            )
                            .padding(horizontal = 16.dp, vertical = 10.dp)
                            .testTag("fatiha_prev")
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(accent)
                            .clickable(
                                onClick = onNext,
                                onClickLabel = AppStrings.fatihaNextVerse(language)
                            )
                            .padding(horizontal = 18.dp, vertical = 10.dp)
                            .testTag("fatiha_next")
                    ) {
                        Text(
                            text = if (verse.n == 7) {
                                AppStrings.fatihaSayAmin(language)
                            } else {
                                AppStrings.fatihaNextVerse(language)
                            },
                            fontFamily = CinzelFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = if (isDayMode) DawnCream950 else Midnight950,
                            style = androidx.compose.ui.text.TextStyle(
                                textDirection = TextDirection.Ltr
                            )
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = if (isDayMode) DawnCream950 else Midnight950,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────
// Finale card — Âmin
// ─────────────────────────────────────────────────────────────────────────

@Composable
private fun FatihaFinaleCard(
    language: AppLanguage,
    isDayMode: Boolean,
    cardBg: Color,
    accent: Color,
    goldLight: Color,
    text: Color,
    textSoft: Color,
    onRestart: () -> Unit
) {
    CelestialCard(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("fatiha_finale_card"),
        borderColor = accent.copy(alpha = 0.30f),
        backgroundColor = cardBg
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp, vertical = 28.dp)
        ) {
            Icon(
                imageVector = Icons.Default.VolunteerActivism,
                contentDescription = null,
                tint = accent,
                modifier = Modifier.size(34.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = FatihaLibrary.AMIN_AR,
                fontFamily = AmiriFontFamily,
                fontSize = 32.sp,
                lineHeight = 46.sp,
                textAlign = TextAlign.Center,
                color = goldLight,
                style = androidx.compose.ui.text.TextStyle(
                    textDirection = TextDirection.Ltr
                ),
                modifier = Modifier.testTag("fatiha_amin_word")
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = AppStrings.fatihaFinaleQuote(language),
                fontFamily = CinzelFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                lineHeight = 26.sp,
                textAlign = TextAlign.Center,
                color = text
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = AppStrings.fatihaFinaleBody(language),
                fontSize = 12.5.sp,
                lineHeight = 20.sp,
                textAlign = TextAlign.Center,
                color = textSoft
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .border(1.dp, accent.copy(alpha = 0.5f), RoundedCornerShape(50))
                    .clickable(onClick = onRestart, onClickLabel = AppStrings.fatihaRestart(language))
                    .background(accent.copy(alpha = 0.10f))
                    .padding(horizontal = 18.dp, vertical = 10.dp)
                    .testTag("fatiha_restart")
            ) {
                Icon(
                    imageVector = Icons.Default.Replay,
                    contentDescription = null,
                    tint = goldLight,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    text = AppStrings.fatihaRestart(language),
                    fontFamily = CinzelFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = goldLight,
                    style = androidx.compose.ui.text.TextStyle(
                        textDirection = TextDirection.Ltr
                    )
                )
            }
        }
    }
}
