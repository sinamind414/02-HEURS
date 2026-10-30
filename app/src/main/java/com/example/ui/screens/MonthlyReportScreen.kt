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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.DailyJournalEntity
import com.example.data.localization.AppStrings
import com.example.data.model.AppLanguage
import com.example.data.model.InnerWeather
import com.example.data.model.MonthlyReport
import com.example.ui.MainViewModel
import com.example.ui.components.CelestialCard
import com.example.ui.theme.CinzelFontFamily
import com.example.ui.theme.GoldBorder
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.Midnight800
import com.example.ui.theme.Midnight900
import com.example.ui.theme.TextGold
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextSecondary
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Monthly Presence Legacy (Presence Rhythm, strategy §8) — up to four weeks
 * of pauses kept and of the Inner Weather, read as a quiet trend, plus the
 * gentle unlock of the Eternal Perspective after three to four weeks of
 * returning. Counts only, never scores (Serment #2).
 */
@Composable
fun MonthlyReportScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val journals by viewModel.recentJournals.collectAsState()
    val language by viewModel.appLanguage.collectAsState()
    val todayIso = remember { SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date()) }
    val report = remember(journals, todayIso) { MonthlyReport.build(journals, todayIso) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 18.dp)
            .testTag("monthly_report_root")
    ) {
        Text(
            text = AppStrings.monthlyCardTitle(language),
            fontFamily = CinzelFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            letterSpacing = 1.sp,
            color = GoldPrimary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = AppStrings.monthlyCardSubtitle(language),
            fontSize = 12.sp,
            color = TextSecondary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(18.dp))

        if (report.weeks.isEmpty() || report.totalPauses == 0) {
            Text(
                text = AppStrings.monthlyNoData(language),
                fontSize = 12.5.sp,
                color = TextSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            Text(
                text = AppStrings.monthlyTotalLine(language, report.totalPauses),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextGold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(14.dp))

            report.weeks.forEachIndexed { index, week ->
                CelestialCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .testTag("monthly_week_row"),
                    backgroundColor = Midnight900.copy(alpha = 0.7f),
                    borderColor = GoldBorder.copy(alpha = 0.6f)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = AppStrings.monthlyWeekLine(language, index + 1, week.pausesKept),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = TextGold
                            )
                            if (week.moodAfterTotal > 0) {
                                val dominant = week.moodAfterCounts.maxByOrNull { it.value }?.key
                                if (dominant != null) {
                                    Text(text = dominant.emoji, fontSize = 16.sp)
                                }
                            }
                        }
                        if (week.moodAfterTotal > 0) {
                            Spacer(modifier = Modifier.height(4.dp))
                            week.moodAfterCounts.entries
                                .sortedByDescending { it.value }
                                .forEach { (mood, count) ->
                                    Text(
                                        text = AppStrings.weeklyMoodCountLine(
                                            language, mood, count, week.moodAfterTotal
                                        ),
                                        fontSize = 11.5.sp,
                                        color = TextSecondary
                                    )
                                }
                        }
                    }
                }
            }
        }

        // ── Presence Legacy unlock ──
        Spacer(modifier = Modifier.height(24.dp))
        CelestialCard(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("monthly_legacy_box"),
            backgroundColor = Midnight800.copy(alpha = 0.9f),
            borderColor = GoldPrimary.copy(alpha = 0.35f)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = AppStrings.monthlyLegacyTitle(language),
                    fontFamily = CinzelFontFamily,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.8.sp,
                    color = GoldPrimary
                )
                Spacer(modifier = Modifier.height(6.dp))
                if (report.eternalPerspectiveUnlocked) {
                    Text(
                        text = AppStrings.monthlyLegacyBody(language),
                        fontSize = 12.5.sp,
                        lineHeight = 17.sp,
                        color = TextSecondary
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(18.dp))
                            .background(GoldPrimary)
                            .clickable {
                                viewModel.navigateTo(com.example.ui.SaatanScreen.CONVERTER)
                            }
                            .padding(horizontal = 16.dp, vertical = 10.dp)
                            .testTag("monthly_legacy_unlock_btn"),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = AppStrings.monthlyLegacyUnlock(language),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Midnight900
                        )
                    }
                } else {
                    Text(
                        text = AppStrings.monthlyLegacyLocked(language, report.weeksUsed),
                        fontSize = 12.5.sp,
                        lineHeight = 17.sp,
                        color = TextSecondary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))
    }
}

/** Entry card on the light home (slice 5). */
@Composable
fun MonthlyReportCard(
    journals: List<DailyJournalEntity>,
    language: AppLanguage,
    onOpen: () -> Unit,
    modifier: Modifier = Modifier
) {
    val todayIso = remember { SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date()) }
    val report = remember(journals, todayIso) { MonthlyReport.build(journals, todayIso) }

    CelestialCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .clickable { onOpen() }
            .testTag("monthly_report_card"),
        backgroundColor = Midnight800.copy(alpha = 0.9f),
        borderColor = GoldPrimary.copy(alpha = 0.4f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Timeline,
                    contentDescription = null,
                    tint = GoldPrimary,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = AppStrings.monthlyCardTitle(language),
                    fontFamily = CinzelFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    letterSpacing = 1.sp,
                    color = GoldPrimary
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = AppStrings.monthlyCardSubtitle(language),
                fontSize = 11.5.sp,
                color = TextSecondary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = AppStrings.monthlyTotalLine(language, report.totalPauses),
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextGold
            )
        }
    }
}
