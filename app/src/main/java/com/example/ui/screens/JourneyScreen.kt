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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.audio.StationPlayer
import com.example.data.localization.AppStrings
import com.example.data.model.AppLanguage
import com.example.data.model.BookLibrary
import com.example.data.model.JourneyStations
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

/**
 * LE VOYAGE DE LA PRIÈRE — Saison 1 de Dhawq.
 *
 * Ten stations, from the wudu water to the precious sitting after the salam.
 * The intended rhythm is one station before each prayer — then the phone is
 * put away and the prayer is lived without it. The audio (2-4 minutes per
 * station) drops into `res/raw/dhawq_station_XX.mp3`; until then each card
 * shows a quiet "coming soon" chip instead of a broken player.
 */
@Composable
fun JourneyScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val language by viewModel.appLanguage.collectAsState()
    val isDayMode = LocalSaatanThemeIsDay.current
    val context = LocalContext.current
    val ramadanState by viewModel.ramadanState.collectAsState()

    val stations = JourneyStations.ALL
    val stationPlayer = remember { StationPlayer() }
    var playbackState by remember { mutableStateOf(StationPlayer.PlaybackState.IDLE) }
    var activeStationOrder by remember { mutableStateOf(-1) }

    DisposableEffect(Unit) {
        stationPlayer.onStateChanged = StationPlayer.OnStateChanged { playbackState = it }
        onDispose { stationPlayer.releasePlayer() }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(if (isDayMode) com.example.ui.theme.DawnCream950 else Midnight950)
            .testTag("journey_screen")
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
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Spa,
                            contentDescription = null,
                            tint = GoldPrimary,
                            modifier = Modifier.size(26.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = AppStrings.journeyTitle(language),
                            fontFamily = CinzelFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 19.sp,
                            letterSpacing = 1.sp,
                            color = GoldLight
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = AppStrings.journeySubtitle(language),
                        fontFamily = AmiriFontFamily,
                        fontSize = 12.5.sp,
                        lineHeight = 19.sp,
                        color = if (isDayMode) DawnTextSecondary else TextSecondary
                    )
                }
            }

            // Ramadan season card — the entry point to the Last Nights.
            item {
                LastNightsSeasonCard(
                    language = language,
                    ramadanState = ramadanState,
                    isDayMode = isDayMode,
                    onOpen = { viewModel.navigateTo(SaatanScreen.LAST_NIGHTS) }
                )
            }

            // The ten stations
            itemsIndexed(stations, key = { _, station -> station.order }) { _, station ->
                val isPlayingHere = activeStationOrder == station.order &&
                    playbackState == StationPlayer.PlaybackState.PLAYING

                CelestialCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 6.dp)
                        .testTag("journey_station_${station.order}"),
                    backgroundColor = if (isDayMode) androidx.compose.ui.graphics.Color.White.copy(alpha = 0.97f) else Midnight900.copy(alpha = 0.95f),
                    borderColor = if (isPlayingHere) GoldPrimary else GoldBorder
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                // Station number in a gold ring
                                Box(
                                    modifier = Modifier
                                        .size(34.dp)
                                        .clip(CircleShape)
                                        .background(GoldPrimary.copy(alpha = 0.14f))
                                        .border(1.dp, GoldPrimary.copy(alpha = 0.55f), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "${station.order}",
                                        fontFamily = CinzelFontFamily,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp,
                                        color = TextGold
                                    )
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(
                                        text = station.arabicName,
                                        fontFamily = AmiriFontFamily,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 19.sp,
                                        color = TextPrimary
                                    )
                                    Text(
                                        text = if (language == AppLanguage.AR) station.nameEn
                                        else if (language == AppLanguage.FR) station.nameFr
                                        else station.nameEn,
                                        fontSize = 11.sp,
                                        letterSpacing = 0.8.sp,
                                        color = TextMuted
                                    )
                                }
                            }

                            // Play / pause — or the quiet "coming soon" chip.
                            // Audio files are per-language: the Arabic track
                            // keeps the base name (dhawq_station_01), English
                            // adds "_en", French "_fr". Missing tracks degrade
                            // gracefully into the "coming soon" chip.
                            val audioResName = station.audioResName + when (language) {
                                AppLanguage.AR -> ""
                                AppLanguage.EN -> "_en"
                                AppLanguage.FR -> "_fr"
                            }
                            val audioReady = remember(station.order, language) {
                                context.resources.getIdentifier(
                                    audioResName, "raw", context.packageName
                                ) != 0
                            }
                            if (audioReady) {
                                Box(
                                    modifier = Modifier
                                        .size(42.dp)
                                        .clip(CircleShape)
                                        .background(GoldPrimary.copy(alpha = 0.16f))
                                        .border(1.dp, GoldPrimary, CircleShape)
                                        .clickable {
                                            if (isPlayingHere) {
                                                stationPlayer.pause()
                                            } else {
                                                val started = stationPlayer.play(context, audioResName)
                                                if (started) activeStationOrder = station.order
                                            }
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = if (isPlayingHere) Icons.Default.Pause else Icons.Default.PlayArrow,
                                        contentDescription = if (isPlayingHere) AppStrings.journeyPause(language)
                                        else AppStrings.journeyListen(language),
                                        tint = GoldPrimary,
                                        modifier = Modifier.size(22.dp)
                                    )
                                }
                            } else {
                                Text(
                                    text = AppStrings.journeyComingSoon(language),
                                    fontSize = 9.5.sp,
                                    color = TextMuted,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(50))
                                        .background(if (isDayMode) DawnCream800 else Midnight800)
                                        .border(1.dp, GoldBorder, RoundedCornerShape(50))
                                        .padding(horizontal = 10.dp, vertical = 5.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // The meaning of the station
                        Text(
                            text = when (language) {
                                AppLanguage.AR -> station.meaningAr
                                AppLanguage.FR -> station.meaningFr
                                AppLanguage.EN -> station.meaningEn
                            },
                            fontFamily = AmiriFontFamily,
                            fontSize = 13.sp,
                            lineHeight = 20.sp,
                            color = if (isDayMode) DawnTextPrimary else TextPrimary
                        )

                        // The chapter(s) of the book woven into this station (Sprint 4).
                        val stationChapters = BookLibrary.chaptersForStation(station.order)
                        if (stationChapters.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(9.dp))
                            FeatureChip(
                                text = AppStrings.bookJourneyChip(language),
                                testTag = "journey_book_chip_${station.order}",
                                icon = Icons.Default.AutoStories,
                                onClick = { viewModel.openBook(stationChapters.first().id) }
                            )
                        }

                        // Station 6 — "Le Dialogue": the Fatiha stepper screen
                        // (book ch22, hadith qudsi Muslim n°395) opens directly.
                        if (station.order == 6) {
                            Spacer(modifier = Modifier.height(6.dp))
                            FeatureChip(
                                text = AppStrings.bookFatihaChip(language),
                                testTag = "journey_fatiha_chip_6",
                                icon = Icons.Default.QuestionAnswer,
                                emphasized = true,
                                onClick = { viewModel.openFatiha() }
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // The classical source — editorial rule: no source, no screen.
                        Text(
                            text = AppStrings.journeySourcePrefix(language) + " · " + station.sourceRef,
                            fontSize = 10.sp,
                            color = TextMuted,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            // Footer: the progress line and the reminder of the rule.
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = AppStrings.journeyStationProgress(language, stations.size, stations.size),
                        fontSize = 11.sp,
                        color = TextMuted
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
        }
    }
}
