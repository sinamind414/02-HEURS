package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.localization.AppStrings
import com.example.data.model.AppLanguage
import com.example.data.model.DivineTimeInfo
import com.example.ui.theme.AmiriFontFamily
import com.example.ui.theme.CelestialCyan
import com.example.ui.theme.CinzelFontFamily
import com.example.ui.theme.GoldBorder
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.LocalSaatanThemeIsDay
import com.example.ui.theme.Midnight700
import com.example.ui.theme.Midnight800
import com.example.ui.theme.Midnight900
import com.example.ui.theme.TextGold
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import kotlin.math.cos
import kotlin.math.sin

/**
 * Circular Canvas Progress Visualization of the "Two Divine Hours" (120 divine minutes)
 * Maps the user's age directly as a position on a clock face (0m to 120m).
 */
@Composable
fun TwoDivineHoursClockDial(
    divineTime: DivineTimeInfo,
    language: AppLanguage,
    modifier: Modifier = Modifier,
    sizeDp: Dp = 270.dp
) {
    val isDayMode = LocalSaatanThemeIsDay.current

    // Pulsing glow for hand beacon
    val infiniteTransition = rememberInfiniteTransition(label = "clock_pulse")
    val pulseGlow by infiniteTransition.animateFloat(
        initialValue = 0.35f,
        targetValue = 0.95f,
        animationSpec = infiniteRepeatable(
            animation = tween(1600, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "clock_beacon"
    )

    // Calculate position in the 120 divine minutes
    // Total divine minutes = (hours * 60) + minutes + (seconds / 60)
    val totalDivineMinutesLived = (divineTime.divineHours * 60.0) +
            divineTime.divineMinutes.toDouble() +
            (divineTime.divineSeconds.toDouble() / 60.0)

    // 120 minutes mapped to 360 degrees (1 minute = 3 degrees)
    val totalMinutesSpan = 120.0
    val progressFraction = (totalDivineMinutesLived / totalMinutesSpan).coerceIn(0.0, 1.0).toFloat()
    val handAngleDegrees = (progressFraction * 360f) - 90f // Start at top (12 o'clock / 0m)

    val cardBg = if (isDayMode) Color.White.copy(alpha = 0.95f) else Midnight900.copy(alpha = 0.95f)
    val cardBorder = if (isDayMode) GoldPrimary.copy(alpha = 0.35f) else GoldBorder
    val trackBgColor = if (isDayMode) Color(0xFFE2DCcb) else Midnight700.copy(alpha = 0.5f)
    val dialTickColor = if (isDayMode) Color(0xFF9E8B65) else GoldBorder

    CelestialCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .testTag("two_divine_hours_clock_dial"),
        backgroundColor = cardBg,
        borderColor = cardBorder
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = AppStrings.clockFaceTitle(language),
                        fontFamily = CinzelFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        letterSpacing = 1.sp,
                        color = GoldLight
                    )
                    Text(
                        text = AppStrings.clockFaceSubtitle(language),
                        fontSize = 11.5.sp,
                        color = TextSecondary
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(GoldPrimary.copy(alpha = 0.15f))
                        .border(1.dp, GoldPrimary.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = AppStrings.dialTotalMinutesLabel(language),
                        fontFamily = CinzelFontFamily,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextGold
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // The Canvas Clock Face
            Box(
                modifier = Modifier.size(sizeDp),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val canvasCenter = Offset(size.width / 2f, size.height / 2f)
                    val strokeWidth = 14.dp.toPx()
                    val outerRadius = (size.minDimension / 2f) - strokeWidth - 6.dp.toPx()
                    val innerRadius = outerRadius - strokeWidth - 6.dp.toPx()

                    // 1. Draw outer circle track (Hour 1: 0 to 60m)
                    drawCircle(
                        color = trackBgColor,
                        radius = outerRadius,
                        center = canvasCenter,
                        style = Stroke(width = strokeWidth)
                    )

                    // 2. Draw inner circle track (Hour 2: 60 to 120m)
                    drawCircle(
                        color = trackBgColor.copy(alpha = 0.35f),
                        radius = innerRadius,
                        center = canvasCenter,
                        style = Stroke(width = strokeWidth * 0.75f)
                    )

                    // 3. Draw 120-minute dial ticks (Major ticks every 10 min = 30°, Minor ticks every 2.5 min = 7.5°)
                    for (tickIndex in 0 until 48) {
                        val angleDeg = (tickIndex * (360.0 / 48.0)) - 90.0
                        val angleRad = Math.toRadians(angleDeg)
                        val isMajor = tickIndex % 4 == 0 // every 10 divine minutes

                        val tickLength = if (isMajor) 10.dp.toPx() else 4.dp.toPx()
                        val tickStartR = outerRadius + (strokeWidth / 2f) + 1.dp.toPx()
                        val tickEndR = tickStartR + tickLength

                        val pStart = Offset(
                            canvasCenter.x + (tickStartR * cos(angleRad)).toFloat(),
                            canvasCenter.y + (tickStartR * sin(angleRad)).toFloat()
                        )
                        val pEnd = Offset(
                            canvasCenter.x + (tickEndR * cos(angleRad)).toFloat(),
                            canvasCenter.y + (tickEndR * sin(angleRad)).toFloat()
                        )

                        drawLine(
                            color = if (isMajor) GoldPrimary.copy(alpha = 0.85f) else dialTickColor,
                            start = pStart,
                            end = pEnd,
                            strokeWidth = if (isMajor) 2.2.dp.toPx() else 1.dp.toPx(),
                            cap = StrokeCap.Round
                        )
                    }

                    // 4. Progress Arc for the 120-minute lifecycle
                    val sweepDegrees = progressFraction * 360f

                    if (sweepDegrees > 0f) {
                        val arcBrush = Brush.sweepGradient(
                            colors = listOf(
                                GoldPrimary,
                                CelestialCyan,
                                GoldLight,
                                GoldPrimary
                            ),
                            center = canvasCenter
                        )

                        drawArc(
                            brush = arcBrush,
                            startAngle = -90f,
                            sweepAngle = sweepDegrees,
                            useCenter = false,
                            topLeft = Offset(canvasCenter.x - outerRadius, canvasCenter.y - outerRadius),
                            size = Size(outerRadius * 2f, outerRadius * 2f),
                            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                        )
                    }

                    // 5. The Clock Hand (Aiguille Divine)
                    val handAngleRad = Math.toRadians(handAngleDegrees.toDouble())
                    val handLength = outerRadius + 2.dp.toPx()
                    val handTip = Offset(
                        canvasCenter.x + (handLength * cos(handAngleRad)).toFloat(),
                        canvasCenter.y + (handLength * sin(handAngleRad)).toFloat()
                    )

                    // Draw hand body (tapered needle from center)
                    val handPath = Path().apply {
                        val perpAngle = handAngleRad + Math.PI / 2.0
                        val baseWidth = 4.dp.toPx()
                        val baseLeft = Offset(
                            canvasCenter.x + (baseWidth * cos(perpAngle)).toFloat(),
                            canvasCenter.y + (baseWidth * sin(perpAngle)).toFloat()
                        )
                        val baseRight = Offset(
                            canvasCenter.x - (baseWidth * cos(perpAngle)).toFloat(),
                            canvasCenter.y - (baseWidth * sin(perpAngle)).toFloat()
                        )

                        moveTo(baseLeft.x, baseLeft.y)
                        lineTo(handTip.x, handTip.y)
                        lineTo(baseRight.x, baseRight.y)
                        close()
                    }

                    drawPath(
                        path = handPath,
                        brush = Brush.linearGradient(
                            colors = listOf(GoldLight, GoldPrimary, CelestialCyan),
                            start = canvasCenter,
                            end = handTip
                        )
                    )

                    // Hand tip glowing beacon
                    drawCircle(
                        color = GoldPrimary.copy(alpha = pulseGlow * 0.5f),
                        radius = 9.dp.toPx(),
                        center = handTip
                    )
                    drawCircle(
                        color = Color.White,
                        radius = 3.5.dp.toPx(),
                        center = handTip
                    )

                    // Center Hub Pivot
                    drawCircle(
                        color = Midnight800,
                        radius = 11.dp.toPx(),
                        center = canvasCenter
                    )
                    drawCircle(
                        color = GoldPrimary,
                        radius = 5.dp.toPx(),
                        center = canvasCenter
                    )
                }

                // Inner Central Display Text
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(32.dp)
                ) {
                    Text(
                        text = AppStrings.dialHourLabel(language),
                        fontFamily = CinzelFontFamily,
                        fontSize = 9.sp,
                        letterSpacing = 1.2.sp,
                        color = TextMuted
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = divineTime.formattedDivineTime,
                        fontFamily = CinzelFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = TextGold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = AppStrings.dialMinutePosition(language, totalDivineMinutesLived),
                        fontFamily = CinzelFontFamily,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = CelestialCyan
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = AppStrings.percentOfLifeLabel(language, progressFraction * 100f),
                        fontSize = 10.5.sp,
                        color = TextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Dial Milestones Guide
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DialMilestonePill("0m", "Naissance", isPast = totalDivineMinutesLived >= 0.0)
                DialMilestonePill("30m", "~20 ans", isPast = totalDivineMinutesLived >= 30.0)
                DialMilestonePill("60m", "~40 ans", isPast = totalDivineMinutesLived >= 60.0)
                DialMilestonePill("90m", "~60 ans", isPast = totalDivineMinutesLived >= 90.0)
                DialMilestonePill("120m", "~80 ans", isPast = totalDivineMinutesLived >= 120.0)
            }
        }
    }
}

@Composable
private fun DialMilestonePill(
    minutesLabel: String,
    ageLabel: String,
    isPast: Boolean
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(
                    if (isPast) GoldPrimary.copy(alpha = 0.2f)
                    else Midnight800
                )
                .border(
                    width = 1.dp,
                    color = if (isPast) GoldPrimary.copy(alpha = 0.6f) else Midnight700,
                    shape = RoundedCornerShape(6.dp)
                )
                .padding(horizontal = 6.dp, vertical = 2.dp)
        ) {
            Text(
                text = minutesLabel,
                fontFamily = CinzelFontFamily,
                fontWeight = if (isPast) FontWeight.Bold else FontWeight.Normal,
                fontSize = 10.sp,
                color = if (isPast) TextGold else TextMuted
            )
        }
        Text(
            text = ageLabel,
            fontSize = 9.sp,
            color = TextMuted,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}
