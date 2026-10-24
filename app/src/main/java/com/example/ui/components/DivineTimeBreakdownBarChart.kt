package com.example.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.calculator.DivineTimeCalculator
import com.example.data.localization.AppStrings
import com.example.data.model.AppLanguage
import com.example.data.model.DivineTimeInfo
import java.util.Locale
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
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.LocalSaatanThemeIsDay
import com.example.ui.theme.Midnight700
import com.example.ui.theme.Midnight800
import com.example.ui.theme.Midnight900
import com.example.ui.theme.SoftRose
import com.example.ui.theme.TextGold
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

/**
 * Native Jetpack Compose Canvas Bar Chart rendering the divine time breakdown:
 * - Time Spent vs Time Remaining in the 'Divine Day' / 2 Divine Hours
 * - Lifetime allocation breakdown in divine minutes (Sleep, Work, Screens, Prayers)
 */

/** A whole life = 2 divine hours = 120 divine minutes. */
private const val TOTAL_DIVINE_MINUTES = 120f

/** 8 h of sleep per day = one third of a life. */
private const val SEGMENT_SLEEP = 40f

/** 7 h of work / worldly affairs per day. */
private const val SEGMENT_WORK = 35f

/** 3 h of screens per day. */
private const val SEGMENT_SCREENS = 15f

@Composable
fun DivineTimeBreakdownBarChart(
    divineTime: DivineTimeInfo,
    language: AppLanguage,
    modifier: Modifier = Modifier
) {
    val isDayMode = LocalSaatanThemeIsDay.current
    val cardBg = if (isDayMode) Color.White.copy(alpha = 0.95f) else Midnight900.copy(alpha = 0.95f)
    val cardBorder = if (isDayMode) GoldPrimary.copy(alpha = 0.35f) else GoldBorder

    // Day-mode text colours. The card background flips to white in day mode, so
    // the dark-mode cream/gold text would become invisible.
    val strongLabel = if (isDayMode) DawnGoldPrimary else GoldLight
    val bodyText = if (isDayMode) DawnTextPrimary else TextPrimary
    val captionText = if (isDayMode) DawnTextSecondary else TextSecondary
    val goldText = if (isDayMode) DawnGoldPrimary else TextGold

    // Prayers come straight from the calculator (≈ 2.00 divine minutes).
    val segmentPrayers = DivineTimeCalculator.LIFETIME_PRAYER_DIVINE_MINUTES.toFloat()
    // Whatever is left over: meals, travel, family, rest.
    val segmentRest = TOTAL_DIVINE_MINUTES - SEGMENT_SLEEP - SEGMENT_WORK - SEGMENT_SCREENS - segmentPrayers

    val spentFraction = (divineTime.percentageLived / 100f).coerceIn(0f, 1f)
    val remainingFraction = 1f - spentFraction

    val animatedSpent by animateFloatAsState(
        targetValue = spentFraction,
        animationSpec = tween(900),
        label = "spent_anim"
    )

    CelestialCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .testTag("divine_time_breakdown_chart"),
        backgroundColor = cardBg,
        borderColor = cardBorder
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = AppStrings.breakdownChartTitle(language),
                        fontFamily = CinzelFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        letterSpacing = 0.8.sp,
                        color = strongLabel
                    )
                    Text(
                        text = AppStrings.breakdownChartSubtitle(language),
                        fontSize = 11.5.sp,
                        color = captionText
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(GoldPrimary.copy(alpha = 0.15f))
                        .border(1.dp, GoldPrimary.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = AppStrings.thousandYearsEquals24Hours(language),
                        fontFamily = CinzelFontFamily,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = goldText
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Bar 1: Spent vs Remaining Canvas Bar
            Text(
                text = AppStrings.lifeBalanceTitle(language).uppercase(),
                fontFamily = CinzelFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 11.sp,
                color = captionText
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Canvas Bar
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(28.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                val totalW = size.width
                val h = size.height
                val spentW = totalW * animatedSpent
                val remainW = totalW - spentW

                // Spent segment
                if (spentW > 0f) {
                    drawRoundRect(
                        brush = Brush.horizontalGradient(
                            colors = listOf(GoldDark, GoldPrimary, GoldLight)
                        ),
                        topLeft = Offset(0f, 0f),
                        size = Size(spentW, h),
                        cornerRadius = CornerRadius(8.dp.toPx(), 8.dp.toPx())
                    )
                }

                // Remaining segment
                if (remainW > 0f) {
                    drawRoundRect(
                        brush = Brush.horizontalGradient(
                            colors = listOf(CelestialCyan.copy(alpha = 0.6f), CelestialCyan)
                        ),
                        topLeft = Offset(spentW, 0f),
                        size = Size(remainW, h),
                        cornerRadius = CornerRadius(8.dp.toPx(), 8.dp.toPx())
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Legend for Bar 1
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(GoldPrimary)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "${AppStrings.timeConsumedLabel(language)} : ${String.format(java.util.Locale.US, "%.1f%%", spentFraction * 100f)}",
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.Medium,
                        color = bodyText
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(CelestialCyan)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "${AppStrings.timeRemainingLabel(language)} : ${String.format(java.util.Locale.US, "%.1f%%", remainingFraction * 100f)}",
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.Medium,
                        color = bodyText
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Bar 2: Lifetime Budget Breakdown (Where do the 120 divine minutes go?)
            Text(
                text = "2. ${AppStrings.lifetimeBudgetTitle(language).uppercase()}",
                fontFamily = CinzelFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 11.sp,
                color = captionText
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Multi-segment Canvas Bar
            // The 120 divine minutes of a whole life, derived from the
            // calculator constants instead of hand-typed percentages.
            // Sleep = 8h/day (1/3 of life) = 40 min
            // Work/Dunya = 7h/day = 35 min
            // Screens = 3h/day = 15 min
            // Prayers = 5 x 5 min/day over a life = 2.0 min
            // Rest (meals, travel, family) = whatever is left = 28 min
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(26.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                val totalW = size.width
                val h = size.height

                val wSleep = totalW * (SEGMENT_SLEEP / TOTAL_DIVINE_MINUTES.toFloat())
                val wWork = totalW * (SEGMENT_WORK / TOTAL_DIVINE_MINUTES.toFloat())
                val wScreens = totalW * (SEGMENT_SCREENS / TOTAL_DIVINE_MINUTES.toFloat())
                val wRest = totalW * (segmentRest / TOTAL_DIVINE_MINUTES.toFloat())
                val wPrayers = totalW * (segmentPrayers / TOTAL_DIVINE_MINUTES.toFloat())

                var currentX = 0f

                // Sleep
                drawRect(Color(0xFF3A506B), topLeft = Offset(currentX, 0f), size = Size(wSleep, h))
                currentX += wSleep

                // Work
                drawRect(Color(0xFF5BC0BE), topLeft = Offset(currentX, 0f), size = Size(wWork, h))
                currentX += wWork

                // Screens
                drawRect(SoftRose, topLeft = Offset(currentX, 0f), size = Size(wScreens, h))
                currentX += wScreens

                // Rest
                drawRect(Color(0xFF6C757D), topLeft = Offset(currentX, 0f), size = Size(wRest, h))
                currentX += wRest

                // Prayers (Highlighted Glowing Emerald/Gold)
                drawRect(EmeraldHasanat, topLeft = Offset(currentX, 0f), size = Size(wPrayers, h))
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Detailed Breakdown Rows
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        if (isDayMode) DawnCream800.copy(alpha = 0.7f) else Midnight800.copy(alpha = 0.5f),
                        RoundedCornerShape(12.dp)
                    )
                    .border(
                        1.dp,
                        if (isDayMode) DawnCream900 else Midnight700,
                        RoundedCornerShape(12.dp)
                    )
                    .padding(10.dp)
            ) {
                AllocationRowItem(
                    color = Color(0xFF3A506B),
                    label = AppStrings.segmentSleep(language),
                    divineTime = AppStrings.divineMinutesShort(
                        language,
                        String.format(Locale.US, "%.0f", SEGMENT_SLEEP)
                    ),
                    percent = String.format(Locale.US, "%.1f%%", SEGMENT_SLEEP / TOTAL_DIVINE_MINUTES * 100f)
                )
                Spacer(modifier = Modifier.height(4.dp))
                AllocationRowItem(
                    color = Color(0xFF5BC0BE),
                    label = AppStrings.segmentWork(language),
                    divineTime = AppStrings.divineMinutesShort(
                        language,
                        String.format(Locale.US, "%.0f", SEGMENT_WORK)
                    ),
                    percent = String.format(Locale.US, "%.1f%%", SEGMENT_WORK / TOTAL_DIVINE_MINUTES * 100f)
                )
                Spacer(modifier = Modifier.height(4.dp))
                AllocationRowItem(
                    color = SoftRose,
                    label = AppStrings.segmentScreens(language),
                    divineTime = AppStrings.divineMinutesShort(
                        language,
                        String.format(Locale.US, "%.0f", SEGMENT_SCREENS)
                    ),
                    percent = String.format(Locale.US, "%.1f%%", SEGMENT_SCREENS / TOTAL_DIVINE_MINUTES * 100f)
                )
                Spacer(modifier = Modifier.height(4.dp))
                AllocationRowItem(
                    color = Color(0xFF6C757D),
                    label = AppStrings.segmentRest(language),
                    divineTime = AppStrings.divineMinutesShort(
                        language,
                        String.format(Locale.US, "%.0f", segmentRest)
                    ),
                    percent = String.format(
                        Locale.US,
                        "%.1f%%",
                        segmentRest / TOTAL_DIVINE_MINUTES * 100f
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                AllocationRowItem(
                    color = EmeraldHasanat,
                    label = AppStrings.segmentPrayers(language),
                    divineTime = AppStrings.divineMinutesOnly(
                        language,
                        String.format(Locale.US, "%.1f", segmentPrayers)
                    ),
                    percent = String.format(
                        Locale.US,
                        "%.1f%%",
                        DivineTimeCalculator.LIFETIME_PRAYER_PERCENT
                    ),
                    isHighlight = true
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Eye-opening highlight box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(EmeraldHasanat.copy(alpha = 0.12f))
                    .border(1.dp, EmeraldHasanat.copy(alpha = 0.45f), RoundedCornerShape(10.dp))
                    .padding(10.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "💡", fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = AppStrings.prayersOnlyTwoPercent(language),
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.Medium,
                        color = bodyText
                    )
                }
            }
        }
    }
}

@Composable
private fun AllocationRowItem(
    color: Color,
    label: String,
    divineTime: String,
    percent: String,
    isHighlight: Boolean = false
) {
    val isDayMode = LocalSaatanThemeIsDay.current
    val labelColor = when {
        isHighlight && isDayMode -> DawnGoldPrimary
        isHighlight -> TextGold
        isDayMode -> DawnTextSecondary
        else -> TextSecondary
    }
    val valueColor = if (isHighlight) EmeraldHasanat else if (isDayMode) DawnTextPrimary else TextPrimary
    val percentColor = if (isDayMode) DawnTextMuted else TextMuted

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(color)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = label,
                fontSize = if (isHighlight) 12.5.sp else 11.5.sp,
                fontWeight = if (isHighlight) FontWeight.Bold else FontWeight.Normal,
                color = labelColor
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = divineTime,
                fontFamily = CinzelFontFamily,
                fontSize = if (isHighlight) 11.5.sp else 10.5.sp,
                fontWeight = if (isHighlight) FontWeight.Bold else FontWeight.SemiBold,
                color = valueColor
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "($percent)",
                fontSize = 10.sp,
                color = percentColor
            )
        }
    }
}
