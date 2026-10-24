package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.calculator.DivineTimeCalculator
import com.example.data.localization.AppStrings
import com.example.data.model.AppLanguage
import com.example.ui.theme.AmiriFontFamily
import com.example.ui.theme.CelestialCyan
import com.example.ui.theme.CinzelFontFamily
import com.example.ui.theme.EmeraldHasanat
import com.example.ui.theme.GoldBorder
import com.example.ui.theme.GoldDark
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

/**
 * Visual representation component that maps an average human lifespan into
 * "Two Divine Hours" (00:00:00 to 02:00:00 / 120 divine minutes),
 * displaying the user's progress through their life within this conceptual timeframe.
 */
@Composable
fun TwoDivineHoursLifeMap(
    userAgeYears: Double,
    language: AppLanguage = AppLanguage.FR,
    modifier: Modifier = Modifier
) {
    var isExploreMode by remember { mutableStateOf(false) }
    var exploredAge by remember { mutableFloatStateOf(userAgeYears.toFloat().coerceIn(0f, 85f)) }

    val effectiveAge = if (isExploreMode) exploredAge.toDouble() else userAgeYears
    val mapping = remember(effectiveAge, language) {
        DivineTimeCalculator.calculateTwoDivineHoursMapping(effectiveAge, language)
    }

    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseGlow by infiniteTransition.animateFloat(
        initialValue = 0.35f,
        targetValue = 0.95f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "beaconGlow"
    )

    CelestialCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .testTag("two_divine_hours_life_map"),
        borderColor = GoldPrimary.copy(alpha = 0.7f),
        backgroundColor = Midnight900.copy(alpha = 0.96f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            // Header with toggle
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "🗺️", fontSize = 16.sp)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = AppStrings.lifeMapTitle(language),
                            fontFamily = CinzelFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.5.sp,
                            letterSpacing = 0.8.sp,
                            color = GoldLight
                        )
                    }
                    Text(
                        text = AppStrings.lifeMapSubtitle(language),
                        fontSize = 11.sp,
                        color = TextSecondary
                    )
                }

                // Exploration Mode Toggle Pill
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isExploreMode) GoldPrimary.copy(alpha = 0.2f) else Midnight800)
                        .border(
                            1.dp,
                            if (isExploreMode) GoldPrimary else Midnight700,
                            RoundedCornerShape(12.dp)
                        )
                        .clickable {
                            isExploreMode = !isExploreMode
                            if (isExploreMode) exploredAge = userAgeYears.toFloat().coerceIn(0f, 85f)
                        }
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .testTag("explore_mode_toggle")
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Tune,
                            contentDescription = AppStrings.exploreContentDescription(language),
                            tint = if (isExploreMode) GoldLight else TextMuted,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (isExploreMode) {
                                AppStrings.exploreBtn(language)
                            } else {
                                AppStrings.myAgeBtn(language)
                            },
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (isExploreMode) GoldLight else TextSecondary
                        )
                    }
                }
            }

            // Slider in Exploration Mode
            AnimatedVisibility(visible = isExploreMode) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = AppStrings.exploredAgeText(language, exploredAge.toInt()),
                            fontFamily = CinzelFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = CelestialCyan
                        )
                        Text(
                            text = AppStrings.divineMinutesGauge(language, mapping.elapsedMinutes),
                            fontFamily = CinzelFontFamily,
                            fontSize = 11.5.sp,
                            color = TextGold
                        )
                    }
                    Slider(
                        value = exploredAge,
                        onValueChange = { exploredAge = it },
                        valueRange = 0f..85f,
                        colors = SliderDefaults.colors(
                            thumbColor = CelestialCyan,
                            activeTrackColor = CelestialCyan,
                            inactiveTrackColor = Midnight700
                        ),
                        modifier = Modifier.height(28.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // THE 120-MINUTE CONTINUOUS TIMELINE GAUGE
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(Midnight950)
                    .border(1.dp, GoldBorder, RoundedCornerShape(14.dp))
                    .padding(14.dp)
            ) {
                // Top Legend: 0h (0m) -> 1h (60m) -> 2h (120m)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = AppStrings.lifeMapStartLabel(language),
                        fontFamily = CinzelFontFamily,
                        fontSize = 10.sp,
                        color = TextMuted
                    )
                    Text(
                        text = AppStrings.lifeMapHour1Label(language),
                        fontFamily = CinzelFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 10.sp,
                        color = GoldLight
                    )
                    Text(
                        text = AppStrings.lifeMapHour2Label(language),
                        fontFamily = CinzelFontFamily,
                        fontSize = 10.sp,
                        color = TextMuted
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Custom Canvas Track with Glowing Beacon
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Canvas(modifier = Modifier.fillMaxWidth().height(14.dp)) {
                        val trackHeight = size.height
                        val width = size.width
                        val radius = trackHeight / 2f

                        // Background track
                        drawRoundRect(
                            color = Midnight700,
                            size = size,
                            cornerRadius = androidx.compose.ui.geometry.CornerRadius(radius, radius)
                        )

                        // Mid-life marker at 50% (1h00 = 60m = 41.67 years)
                        val midX = width * 0.5f
                        drawLine(
                            color = GoldLight.copy(alpha = 0.5f),
                            start = Offset(midX, 0f),
                            end = Offset(midX, trackHeight),
                            strokeWidth = 2.dp.toPx()
                        )

                        // Progress fill
                        val progressWidth = (width * (mapping.percentageOfTwoHours / 100f)).coerceIn(0f, width)
                        if (progressWidth > 0f) {
                            drawRoundRect(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        GoldDark,
                                        GoldPrimary,
                                        CelestialCyan
                                    )
                                ),
                                size = androidx.compose.ui.geometry.Size(progressWidth, trackHeight),
                                cornerRadius = androidx.compose.ui.geometry.CornerRadius(radius, radius)
                            )
                        }

                        // Beacon Needle
                        if (progressWidth > 4.dp.toPx()) {
                            // Halo
                            drawCircle(
                                color = CelestialCyan.copy(alpha = pulseGlow * 0.5f),
                                radius = 10.dp.toPx(),
                                center = Offset(progressWidth, trackHeight / 2f)
                            )
                            // White Center Pin
                            drawCircle(
                                color = Color.White,
                                radius = 4.dp.toPx(),
                                center = Offset(progressWidth, trackHeight / 2f)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Markers below track
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "👶 " + AppStrings.lifeMapMarkerBirth(language),
                        fontSize = 10.sp,
                        color = TextMuted
                    )
                    Text(
                        text = "⚡ " + AppStrings.lifeMapMarkerTaklif(language),
                        fontSize = 10.sp,
                        color = TextMuted
                    )
                    Text(
                        text = "🏠 " + AppStrings.lifeMapMarkerMaturity(language),
                        fontSize = 10.sp,
                        color = GoldLight
                    )
                    Text(
                        text = "👴 " + AppStrings.lifeMapMarkerWisdom(language),
                        fontSize = 10.sp,
                        color = TextMuted
                    )
                    Text(
                        text = "☁️ " + AppStrings.lifeMapMarkerEternity(language),
                        fontSize = 10.sp,
                        color = TextMuted
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // DUAL-HOUR SPLIT CARDS (Heure 1 vs Heure 2)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Heure 1 Card
                HourBlockCard(
                    hourNumber = 1,
                    ageSpan = AppStrings.hourSpanFirst(language),
                    minuteElapsed = if (mapping.currentHourNumber == 1) mapping.minuteInCurrentHour else 60,
                    fraction = mapping.hour1Fraction,
                    isActive = mapping.currentHourNumber == 1,
                    isCompleted = mapping.currentHourNumber > 1,
                    language = language,
                    modifier = Modifier.weight(1f)
                )

                // Heure 2 Card
                HourBlockCard(
                    hourNumber = 2,
                    ageSpan = AppStrings.hourSpanSecond(language),
                    minuteElapsed = if (mapping.currentHourNumber == 2) mapping.minuteInCurrentHour else 0,
                    fraction = mapping.hour2Fraction,
                    isActive = mapping.currentHourNumber == 2,
                    isCompleted = mapping.percentageOfTwoHours >= 99f,
                    language = language,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // SPIRITUAL STAGE CARD FOR CURRENT PROGRESS
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Midnight800, Midnight900)
                        )
                    )
                    .border(1.dp, GoldBorder, RoundedCornerShape(14.dp))
                    .padding(14.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(if (mapping.currentHourNumber == 1) GoldPrimary else CelestialCyan)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = mapping.phaseTitle.uppercase(),
                                fontFamily = CinzelFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.5.sp,
                                color = GoldLight
                            )
                        }

                        // Current divine minute badge
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(GoldPrimary.copy(alpha = 0.15f))
                                .border(1.dp, GoldPrimary.copy(alpha = 0.4f), RoundedCornerShape(10.dp))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = AppStrings.lifeMapMinuteLabel(
                                    language, mapping.elapsedMinutes.toInt()
                                ),
                                fontFamily = CinzelFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                color = TextGold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = mapping.phaseSubtitle,
                        fontSize = 11.5.sp,
                        color = TextSecondary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = mapping.stageArabic,
                        fontFamily = AmiriFontFamily,
                        fontSize = 16.sp,
                        color = TextGold,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = mapping.stageQuote,
                        fontSize = 12.sp,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        color = TextPrimary,
                        lineHeight = 16.sp
                    )

                    Text(
                        text = mapping.stageReference,
                        fontFamily = CinzelFontFamily,
                        fontSize = 10.sp,
                        color = CelestialCyan,
                        modifier = Modifier.padding(top = 2.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Midnight700)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = AppStrings.remainingInHourLabel(
                                language, mapping.remainingMinutesInCurrentHour
                            ),
                            fontSize = 11.sp,
                            color = TextMuted
                        )
                        Text(
                            text = AppStrings.remainingInTwoHoursLabel(
                                language, mapping.remainingMinutesOfTwoHours
                            ),
                            fontFamily = CinzelFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 11.sp,
                            color = CelestialCyan
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HourBlockCard(
    hourNumber: Int,
    ageSpan: String,
    minuteElapsed: Int,
    fraction: Float,
    isActive: Boolean,
    isCompleted: Boolean,
    language: AppLanguage,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(if (isActive) Midnight800 else Midnight950)
            .border(
                width = if (isActive) 1.5.dp else 1.dp,
                color = if (isActive) GoldPrimary else if (isCompleted) EmeraldHasanat.copy(alpha = 0.5f) else Midnight700,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(12.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = AppStrings.hourNumberLabel(language, hourNumber),
                    fontFamily = CinzelFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.5.sp,
                    letterSpacing = 0.5.sp,
                    color = if (isActive) GoldLight else TextMuted
                )

                Text(
                    text = when {
                        isCompleted -> AppStrings.statusCompleted(language)
                        isActive -> AppStrings.statusInProgress(language)
                        else -> AppStrings.statusUpcoming(language)
                    },
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (isCompleted) EmeraldHasanat else if (isActive) GoldPrimary else TextMuted
                )
            }

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = ageSpan,
                fontSize = 11.sp,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(8.dp))

            LinearProgressIndicator(
                progress = { fraction },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = if (isCompleted) EmeraldHasanat else GoldPrimary,
                trackColor = Midnight700
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = AppStrings.hourProgressLabel(language, minuteElapsed),
                fontFamily = CinzelFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 11.5.sp,
                color = if (isActive) TextGold else TextSecondary
            )
        }
    }
}
