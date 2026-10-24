package com.example.ui.screens

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.localization.AppStrings
import com.example.data.model.AppLanguage
import com.example.data.model.RamadanNightState
import com.example.ui.MainViewModel
import com.example.ui.SaatanScreen
import com.example.ui.components.CelestialCard
import com.example.ui.theme.AmiriFontFamily
import com.example.ui.theme.CinzelFontFamily
import com.example.ui.theme.DawnCream800
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
import kotlinx.coroutines.delay

/**
 * LES DERNIÈRES NUITS — the annual climax of Dhawq.
 *
 * During the last ten nights of Ramadan the whole product converges here:
 * the bridge between Laylat al-Qadr (1000 months ≈ 83 years and 4 months)
 * and the "two hours" concept — one sincere night can outweigh an entire
 * life. The screen lists the ten nights (odd nights highlighted, per the
 * hadith of Bukhari) and offers the vigil mode: a dark, silent count-up
 * timer that stays out of the way of worship.
 */
@Composable
fun LastNightsScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val language by viewModel.appLanguage.collectAsState()
    val isDayMode = LocalSaatanThemeIsDay.current
    val ramadanState by viewModel.ramadanState.collectAsState()

    var showVigil by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(if (isDayMode) com.example.ui.theme.DawnCream950 else Midnight950)
            .testTag("last_nights_screen")
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(bottom = 96.dp)
        ) {
            // Header
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 20.dp)
                ) {
                    Text(
                        text = "🌙",
                        fontSize = 30.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = AppStrings.lastNightsTitle(language),
                        fontFamily = CinzelFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 19.sp,
                        letterSpacing = 1.sp,
                        color = GoldLight
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    // The verse, in Arabic calligraphy, then translated.
                    Text(
                        text = AppStrings.qadrVerseQuote(language),
                        fontFamily = AmiriFontFamily,
                        fontSize = 20.sp,
                        lineHeight = 30.sp,
                        color = if (isDayMode) DawnTextPrimary else TextPrimary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = AppStrings.qadrVerseRef(language),
                        fontSize = 10.5.sp,
                        color = TextMuted
                    )
                }
            }

            // The bridge: one night = your whole life (the Sa'atan engine).
            item {
                CelestialCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 6.dp)
                        .testTag("last_nights_bridge_card"),
                    backgroundColor = if (isDayMode) androidx.compose.ui.graphics.Color.White.copy(alpha = 0.97f)
                    else Midnight900.copy(alpha = 0.95f),
                    borderColor = GoldPrimary.copy(alpha = 0.7f)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = AppStrings.lastNightsBridgeTitle(language),
                            fontFamily = CinzelFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = GoldLight
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = AppStrings.lastNightsBridgeBody(language),
                            fontFamily = AmiriFontFamily,
                            fontSize = 13.sp,
                            lineHeight = 20.sp,
                            color = if (isDayMode) DawnTextPrimary else TextPrimary
                        )
                    }
                }
            }

            // The hadith: seek it in the odd nights.
            item {
                CelestialCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 6.dp),
                    backgroundColor = if (isDayMode) androidx.compose.ui.graphics.Color.White.copy(alpha = 0.97f)
                    else Midnight900.copy(alpha = 0.95f),
                    borderColor = GoldBorder
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = AppStrings.seekLaylatHadith(language),
                            fontFamily = AmiriFontFamily,
                            fontSize = 13.5.sp,
                            lineHeight = 21.sp,
                            color = if (isDayMode) DawnTextPrimary else TextPrimary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = AppStrings.seekLaylatRef(language),
                            fontSize = 10.sp,
                            color = TextMuted
                        )
                    }
                }
            }

            // The ten nights — odd nights glow, the current one is marked.
            items((21..30).toList()) { night ->
                val isOdd = night % 2 == 1
                val isCurrent = ramadanState.isLastTenNights &&
                    ramadanState.lastTenNightNumber == night

                CelestialCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 4.dp)
                        .testTag("last_night_card_$night"),
                    backgroundColor = if (isDayMode) androidx.compose.ui.graphics.Color.White.copy(alpha = 0.97f)
                    else Midnight900.copy(alpha = 0.95f),
                    borderColor = when {
                        isCurrent -> GoldPrimary
                        isOdd -> GoldPrimary.copy(alpha = 0.45f)
                        else -> GoldBorder
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = AppStrings.nightLabel(language, night),
                            fontFamily = AmiriFontFamily,
                            fontSize = 15.sp,
                            color = if (isDayMode) DawnTextPrimary else TextPrimary
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (isCurrent) {
                                Text(
                                    text = AppStrings.inProgressBadge(language, night),
                                    fontSize = 9.5.sp,
                                    color = TextGold,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(50))
                                        .background(GoldPrimary.copy(alpha = 0.16f))
                                        .border(1.dp, GoldPrimary, RoundedCornerShape(50))
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                            }
                            if (isOdd) {
                                Text(
                                    text = "✦ " + AppStrings.oddNightBadge(language),
                                    fontSize = 9.5.sp,
                                    color = if (isDayMode) DawnTextSecondary else TextSecondary,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(50))
                                        .background(if (isDayMode) DawnCream800 else Midnight800)
                                        .border(1.dp, GoldBorder, RoundedCornerShape(50))
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }
                    }
                }
            }

            // The vigil button — the only call to action of the season.
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(50))
                            .background(GoldPrimary.copy(alpha = 0.16f))
                            .border(1.2.dp, GoldPrimary, RoundedCornerShape(50))
                            .clickable { showVigil = true }
                            .padding(horizontal = 20.dp, vertical = 16.dp)
                            .testTag("vigil_button"),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "🌙  " + AppStrings.vigilButton(language),
                            fontFamily = CinzelFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = TextGold
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = AppStrings.vigilHint(language),
                        fontSize = 10.5.sp,
                        color = TextMuted,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

    if (showVigil) {
        NightVigilOverlay(
            language = language,
            onExit = { showVigil = false }
        )
    }
}

/**
 * The vigil mode: the screen becomes a dark, silent companion for the night
 * of worship. A count-up timer, one line of dhikr, nothing else — by design
 * the phone stays out of the way of the prayer itself (Serment #4).
 */
@Composable
private fun NightVigilOverlay(
    language: AppLanguage,
    onExit: () -> Unit
) {
    var elapsedSeconds by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            elapsedSeconds++
        }
    }

    val hours = elapsedSeconds / 3600
    val minutes = (elapsedSeconds % 3600) / 60
    val seconds = elapsedSeconds % 60
    val formatted = String.format(
        java.util.Locale.US, "%02d:%02d:%02d", hours, minutes, seconds
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Midnight950)
            .testTag("last_nights_vigil"),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = AppStrings.lastNightsTitle(language),
                fontFamily = AmiriFontFamily,
                fontSize = 15.sp,
                color = TextMuted
            )
            Spacer(modifier = Modifier.height(26.dp))
            Text(
                text = formatted,
                fontFamily = CinzelFontFamily,
                fontSize = 46.sp,
                letterSpacing = 2.sp,
                color = GoldPrimary
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = AppStrings.vigilDhikr(language),
                fontFamily = AmiriFontFamily,
                fontSize = 16.sp,
                color = TextSecondary
            )
            Spacer(modifier = Modifier.height(50.dp))
            TextButton(onClick = onExit) {
                Text(
                    text = AppStrings.vigilExit(language),
                    fontSize = 12.sp,
                    color = TextMuted
                )
            }
        }
    }
}

/**
 * The season card shown at the top of the Journey screen — the entry point
 * to the Last Nights. Always visible (the season is announced all year),
 * with a live badge during the last ten nights.
 */
@Composable
fun LastNightsSeasonCard(
    language: AppLanguage,
    ramadanState: RamadanNightState,
    isDayMode: Boolean,
    onOpen: () -> Unit
) {
    CelestialCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 6.dp)
            .clickable { onOpen() }
            .testTag("last_nights_season_card"),
        backgroundColor = if (isDayMode) androidx.compose.ui.graphics.Color.White.copy(alpha = 0.97f)
        else Midnight900.copy(alpha = 0.95f),
        borderColor = if (ramadanState.isLastTenNights) GoldPrimary else GoldBorder
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "🌙  " + AppStrings.lastNightsSeasonCardTitle(language),
                    fontFamily = CinzelFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = GoldLight
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = AppStrings.lastNightsSeasonCardBody(language),
                    fontFamily = AmiriFontFamily,
                    fontSize = 11.5.sp,
                    lineHeight = 17.sp,
                    color = if (isDayMode) DawnTextSecondary else TextSecondary
                )
                if (ramadanState.isLastTenNights) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = AppStrings.inProgressBadge(language, ramadanState.lastTenNightNumber),
                        fontSize = 9.5.sp,
                        color = TextGold,
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(GoldPrimary.copy(alpha = 0.16f))
                            .border(1.dp, GoldPrimary, RoundedCornerShape(50))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "›",
                fontSize = 22.sp,
                color = TextMuted
            )
        }
    }
}
