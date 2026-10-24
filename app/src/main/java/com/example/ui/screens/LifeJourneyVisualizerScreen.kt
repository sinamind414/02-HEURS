package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.calculator.DivineTimeCalculator
import com.example.data.localization.AppStrings
import com.example.ui.MainViewModel
import com.example.ui.components.BismillahHeader
import com.example.ui.components.CelestialCard
import com.example.ui.components.TwoDivineHoursLifeMap
import com.example.ui.components.triggerSpiritualHaptic
import com.example.ui.theme.AmiriFontFamily
import com.example.ui.theme.CelestialCyan
import com.example.ui.theme.CinzelFontFamily
import com.example.ui.theme.DawnCream800
import com.example.ui.theme.DawnCream900
import com.example.ui.theme.DawnGoldPrimary
import com.example.ui.theme.DawnTextMuted
import com.example.ui.theme.DawnTextPrimary
import com.example.ui.theme.DawnTextSecondary
import com.example.ui.theme.EmeraldHasanat
import com.example.ui.theme.GoldBorder
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.Midnight700
import com.example.ui.theme.Midnight800
import com.example.ui.theme.Midnight900
import com.example.ui.theme.Midnight950
import com.example.ui.theme.TextGold
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import java.util.Locale

@Composable
fun LifeJourneyVisualizerScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val language by viewModel.appLanguage.collectAsState()
    val isDayMode by viewModel.isDayMode.collectAsState()
    val simSeconds by viewModel.simSeconds.collectAsState()
    val isPlaying by viewModel.simPlaying.collectAsState()
    val isFinished by viewModel.simFinished.collectAsState()
    val activeMilestone by viewModel.activeMilestone.collectAsState()
    val userProfile by viewModel.userProfile.collectAsState()
    val twoHourRemaining by viewModel.twoHourSecondsRemaining.collectAsState()

    val lifeExpectancy = (userProfile?.lifeExpectancyYears ?: 75).toDouble()
    val simMinutes = (simSeconds / 60).toInt()
    val simSecs = (simSeconds % 60).toInt()
    val earthAgeEquiv = (simSeconds / 120f) * lifeExpectancy

    val backgroundColor = if (isDayMode) DawnCream900 else Midnight950
    val cardColor = if (isDayMode) DawnCream800 else Midnight800
    val cardColorDim = if (isDayMode) DawnCream800.copy(alpha = 0.75f) else Midnight800.copy(alpha = 0.7f)
    val textPrimary = if (isDayMode) DawnTextPrimary else TextPrimary
    val textSecondary = if (isDayMode) DawnTextSecondary else TextSecondary
    val textMuted = if (isDayMode) DawnTextMuted else TextMuted
    val accentGold = if (isDayMode) DawnGoldPrimary else GoldPrimary
    val goldText = if (isDayMode) DawnGoldPrimary else TextGold

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 96.dp)
        ) {
            item {
                BismillahHeader()
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "🎬 " + AppStrings.visualizerTitle(language),
                        fontFamily = CinzelFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        letterSpacing = 1.sp,
                        color = accentGold,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = AppStrings.visualizerSubtitle(language),
                        fontSize = 12.sp,
                        color = textSecondary,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // The Cinema Display Screen Card
            item {
                CelestialCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 12.dp),
                    borderColor = if (isFinished) goldText else accentGold,
                    backgroundColor = Midnight950
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(290.dp)
                    ) {
                        // Cosmic background
                        Image(
                            painter = painterResource(id = R.drawable.divine_cosmos_bg),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Midnight950.copy(alpha = if (isFinished) 0.95f else 0.7f),
                                            Midnight950.copy(alpha = if (isFinished) 0.98f else 0.85f)
                                        )
                                    )
                                )
                        )

                        if (isFinished) {
                            // Monumental Quranic End Screen
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = AppStrings.milestoneQuranVerse(language, "return_to_allah"),
                                    fontFamily = AmiriFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp,
                                    color = TextGold,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = AppStrings.endScreenVerseTranslation(language),
                                    fontSize = 12.5.sp,
                                    color = TextPrimary,
                                    textAlign = TextAlign.Center,
                                    lineHeight = 17.sp
                                )
                                Text(
                                    text = AppStrings.milestoneQuranRef(language, "return_to_allah"),
                                    fontFamily = CinzelFontFamily,
                                    fontSize = 11.sp,
                                    color = CelestialCyan,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                                Spacer(modifier = Modifier.height(14.dp))
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Midnight800)
                                        .border(1.dp, GoldBorder, RoundedCornerShape(12.dp))
                                        .padding(horizontal = 12.dp, vertical = 6.dp)
                                ) {
                                    Text(
                                        text = AppStrings.lifeJourneyComplete(language),
                                        fontSize = 11.5.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = GoldLight,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        } else {
                            // Active Milestone Cinematic View
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(18.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                // Top Timer & Earth Age Counter
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(Midnight800)
                                            .padding(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        Text(
                                            text = String.format(
                                                Locale.US,
                                                "%02d:%02d / 02:00",
                                                simMinutes,
                                                simSecs
                                            ),
                                            fontFamily = CinzelFontFamily,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 13.sp,
                                            color = TextGold
                                        )
                                    }

                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(Midnight800)
                                            .padding(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        Text(
                                            text = AppStrings.earthAgeApproxLabel(
                                                language, earthAgeEquiv.toInt()
                                            ),
                                            fontFamily = CinzelFontFamily,
                                            fontSize = 12.sp,
                                            color = CelestialCyan
                                        )
                                    }
                                }

                                // Center Content
                                activeMilestone?.let { milestone ->
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier.padding(horizontal = 8.dp)
                                    ) {
                                        Text(
                                            text = milestone.iconEmoji,
                                            fontSize = 44.sp,
                                            modifier = Modifier.padding(bottom = 6.dp)
                                        )
                                        Text(
                                            text = AppStrings.milestoneArabicTitle(language, milestone.id),
                                            fontFamily = AmiriFontFamily,
                                            fontSize = 20.sp,
                                            color = TextGold,
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            text = AppStrings.milestoneTitle(language, milestone.id),
                                            fontFamily = CinzelFontFamily,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 16.sp,
                                            color = TextPrimary,
                                            textAlign = TextAlign.Center
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = AppStrings.milestoneDescription(language, milestone.id),
                                            fontSize = 12.sp,
                                            color = TextSecondary,
                                            textAlign = TextAlign.Center,
                                            lineHeight = 16.sp
                                        )
                                    }
                                }

                                // Bottom Verse Callout
                                activeMilestone?.let { milestone ->
                                    Text(
                                        text = "« " + AppStrings.milestoneQuranVerse(language, milestone.id) + " »",
                                        fontFamily = AmiriFontFamily,
                                        fontSize = 13.sp,
                                        color = GoldLight,
                                        textAlign = TextAlign.Center,
                                        maxLines = 2
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Player Timeline Slider & Controls
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 4.dp)
                ) {
                    Slider(
                        value = simSeconds,
                        onValueChange = {
                            viewModel.seekSimulation(it)
                        },
                        valueRange = 0f..120f,
                        colors = SliderDefaults.colors(
                            thumbColor = accentGold,
                            activeTrackColor = accentGold,
                            inactiveTrackColor = if (isDayMode) DawnCream800 else Midnight700
                        )
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                triggerSpiritualHaptic(context)
                                viewModel.resetSimulation()
                            },
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(cardColor)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = AppStrings.resetSimulationContentDescription(language),
                                tint = textSecondary
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Button(
                            onClick = {
                                triggerSpiritualHaptic(context, 40)
                                if (isPlaying) {
                                    viewModel.pauseSimulation()
                                } else {
                                    viewModel.startOrResumeSimulation()
                                }
                            },
                            modifier = Modifier
                                .height(48.dp)
                                .padding(horizontal = 12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = accentGold,
                                contentColor = Midnight950
                            ),
                            shape = RoundedCornerShape(24.dp)
                        ) {
                            Icon(
                                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                                contentDescription = if (isPlaying) {
                                    AppStrings.pauseSimulationBtn(language)
                                } else {
                                    AppStrings.startSimulationBtn(language)
                                },
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = when {
                                    isPlaying -> AppStrings.pauseSimulationBtn(language)
                                    isFinished -> AppStrings.restartSimulationBtn(language)
                                    else -> AppStrings.startSimulationBtn(language)
                                },
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }

            // Life Milestones Quick Jump List
            item {
                Column(modifier = Modifier.padding(start = 24.dp, top = 16.dp, bottom = 8.dp)) {
                    Text(
                        text = AppStrings.journeyStepsTitle(language),
                        fontFamily = CinzelFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        letterSpacing = 0.5.sp,
                        color = GoldLight
                    )
                }
            }

            items(DivineTimeCalculator.LIFE_MILESTONES) { milestone ->
                val isCurrent = activeMilestone?.id == milestone.id
                CelestialCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 4.dp)
                        .clickable {
                            triggerSpiritualHaptic(context)
                            viewModel.seekSimulation(milestone.divineSecondsIn120sSimulation)
                        },
                    backgroundColor = if (isCurrent) {
                        if (isDayMode) DawnCream800 else Midnight700
                    } else {
                        cardColorDim
                    },
                    borderColor = if (isCurrent) accentGold else GoldBorder
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = milestone.iconEmoji, fontSize = 24.sp)
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = AppStrings.milestoneTitle(language, milestone.id),
                                    fontSize = 13.5.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = if (isCurrent) goldText else textPrimary
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = AppStrings.yearsShortLabel(
                                        language, milestone.earthAgeYears.toInt()
                                    ),
                                    fontSize = 11.sp,
                                    color = CelestialCyan
                                )
                            }
                            Text(
                                text = AppStrings.milestoneDescription(language, milestone.id),
                                fontSize = 11.sp,
                                color = textSecondary,
                                maxLines = 1
                            )
                        }
                        Text(
                            text = String.format(
                                Locale.US,
                                "%02d:%02d",
                                (milestone.divineSecondsIn120sSimulation / 60).toInt(),
                                (milestone.divineSecondsIn120sSimulation % 60).toInt()
                            ),
                            fontFamily = CinzelFontFamily,
                            fontSize = 12.sp,
                            color = textMuted
                        )
                    }
                }
            }

            // Visual representation of the two divine hours life map
            item {
                val liveDivineTime by viewModel.liveDivineTime.collectAsState()
                TwoDivineHoursLifeMap(
                    userAgeYears = liveDivineTime.earthAgeYears,
                    language = language
                )
            }

            // KILLER FEATURE: Le Mode 2 Heures Symbolique
            item {
                val mode2hActive = userProfile?.twoHourModeActive == true
                CelestialCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 14.dp),
                    borderColor = EmeraldHasanat.copy(alpha = 0.8f),
                    backgroundColor = cardColor
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = AppStrings.twoHourLiveModeTitle(language),
                                    fontFamily = CinzelFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.5.sp,
                                    color = EmeraldHasanat
                                )
                                Text(
                                    text = AppStrings.twoHourLiveModeSubtitle(language),
                                    fontSize = 11.5.sp,
                                    color = textSecondary
                                )
                            }
                            Switch(
                                checked = mode2hActive,
                                onCheckedChange = {
                                    triggerSpiritualHaptic(context, 50)
                                    viewModel.toggleTwoHourMeditationMode()
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = EmeraldHasanat,
                                    checkedTrackColor = if (isDayMode) DawnCream800 else Midnight700
                                )
                            )
                        }

                        if (mode2hActive) {
                            Spacer(modifier = Modifier.height(10.dp))
                            val h = twoHourRemaining / 3600
                            val m = (twoHourRemaining % 3600) / 60
                            val s = twoHourRemaining % 60
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(if (isDayMode) DawnCream900 else Midnight900)
                                    .padding(12.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = String.format(Locale.US, "%02d:%02d:%02d", h, m, s),
                                        fontFamily = CinzelFontFamily,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 24.sp,
                                        color = goldText
                                    )
                                    Text(
                                        text = AppStrings.twoHourCrossingLabel(language),
                                        fontSize = 11.5.sp,
                                        color = textSecondary
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
