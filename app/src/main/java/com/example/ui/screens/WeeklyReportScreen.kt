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
import androidx.compose.material.icons.filled.Insights
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.DailyJournalEntity
import com.example.data.local.UserProfileEntity
import com.example.data.localization.AppStrings
import com.example.data.model.AppLanguage
import com.example.data.model.InnerWeather
import com.example.data.model.WeeklyReport
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
 * Weekly Presence Report (Presence Rhythm slice 3, strategy §8) — all
 * sections non-judging: pauses kept (an honest count), the day graph, how
 * the Inner Weather moved after the guided pauses, one small pattern, the
 * "why this can help" box with its honest framing, and a gentle intention
 * for next week (held on device, never scored — Serment #2).
 *
 * Light skin only: the tradition home keeps its own dashboard and this
 * screen speaks the Presence Rhythm voice (banned-vocabulary tested).
 */
@Composable
fun WeeklyReportScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val journals by viewModel.recentJournals.collectAsState()
    val profile by viewModel.userProfile.collectAsState()
    val language by viewModel.appLanguage.collectAsState()
    val todayIso = remember { SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date()) }
    val report = remember(journals, todayIso) { WeeklyReport.build(journals, todayIso) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 18.dp)
            .testTag("weekly_report_root")
    ) {
        Text(
            text = AppStrings.weeklyCardTitle(language),
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
            text = weekRangeLabel(report, language),
            fontSize = 12.sp,
            fontFamily = androidx.compose.ui.text.font.FontFamily.Default,
            color = TextSecondary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(22.dp))

        // ── 1. The honest count ──
        Text(
            text = AppStrings.weeklyKeptLine(language, report.pausesKept),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextGold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        if (!report.hasAnyData) {
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = AppStrings.weeklyNoData(language),
                fontSize = 12.5.sp,
                color = TextSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        // ── 2. The day graph: five dots per day + the day's weather ──
        WeeklyDayGraph(report, language)

        // ── 3. How the weather moved after the pauses ──
        if (report.moodAfterTotal > 0) {
            Spacer(modifier = Modifier.height(22.dp))
            WeeklySectionTitle(AppStrings.weeklyTrendIntro(language))
            Spacer(modifier = Modifier.height(8.dp))
            report.moodAfterCounts.entries
                .sortedByDescending { it.value }
                .forEach { (mood, count) ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(vertical = 3.dp)
                    ) {
                        Text(text = mood.emoji, fontSize = 16.sp)
                        Text(
                            text = AppStrings.weeklyMoodCountLine(
                                language, mood, count, report.moodAfterTotal
                            ),
                            fontSize = 13.sp,
                            color = TextSecondary
                        )
                    }
                }
        }

        // ── 4. One small pattern ──
        if (report.topMomentKey != null && report.topMomentCount > 0) {
            Spacer(modifier = Modifier.height(22.dp))
            WeeklySectionTitle(AppStrings.weeklyInsightIntro(language))
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "« ${lightMomentLabel(report.topMomentKey, language)} »",
                    fontSize = 13.5.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextGold
                )
                Text(
                    text = "× ${report.topMomentCount}",
                    fontSize = 13.5.sp,
                    color = TextSecondary
                )
            }
        }

        // ── 5. Why this can help (science + source + disclaimer) ──
        Spacer(modifier = Modifier.height(24.dp))
        CelestialCard(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("weekly_why_box"),
            backgroundColor = Midnight800.copy(alpha = 0.9f),
            borderColor = GoldPrimary.copy(alpha = 0.35f)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = AppStrings.weeklyWhyTitle(language),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.8.sp,
                    color = GoldPrimary
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = AppStrings.exploreCardScienceBody(language),
                    fontSize = 12.5.sp,
                    lineHeight = 17.sp,
                    color = TextSecondary
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = AppStrings.exploreScienceDisclaimer(language),
                    fontSize = 11.sp,
                    lineHeight = 15.sp,
                    color = TextMuted
                )
            }
        }

        // ── 6. A gentle intention for next week ──
        Spacer(modifier = Modifier.height(24.dp))
        WeeklyIntentionSection(
            profile = profile,
            language = language,
            weekKey = report.mondayIso,
            onPick = { text ->
                viewModel.setWeeklyIntention(text, report.mondayIso)
            }
        )

        Spacer(modifier = Modifier.height(28.dp))
    }
}

/** The entry card on the light home (slice 3): the week at one glance. */
@Composable
fun WeeklyReportCard(
    journals: List<DailyJournalEntity>,
    language: AppLanguage,
    onOpen: () -> Unit,
    modifier: Modifier = Modifier
) {
    val todayIso = remember { SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date()) }
    val report = remember(journals, todayIso) { WeeklyReport.build(journals, todayIso) }

    CelestialCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .clickable { onOpen() }
            .testTag("weekly_report_card"),
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
                    imageVector = Icons.Default.Insights,
                    contentDescription = null,
                    tint = GoldPrimary,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = AppStrings.weeklyCardTitle(language),
                    fontFamily = CinzelFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    letterSpacing = 1.sp,
                    color = GoldPrimary
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = AppStrings.weeklyCardSubtitle(language),
                fontSize = 11.5.sp,
                color = TextSecondary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = AppStrings.weeklyKeptLine(language, report.pausesKept),
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextGold
            )
        }
    }
}

/** The Monday→Sunday dots: one column per day, five dots + the weather. */
@Composable
private fun WeeklyDayGraph(report: WeeklyReport, language: AppLanguage) {
    val initials = AppStrings.weeklyDayInitials(language)
    CelestialCard(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("weekly_day_graph"),
        backgroundColor = Midnight900.copy(alpha = 0.7f),
        borderColor = GoldBorder.copy(alpha = 0.6f)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp, horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            report.days.forEach { day ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    day.momentMarks.forEachIndexed { index, marked ->
                        Box(
                            modifier = Modifier
                                .padding(vertical = 2.dp)
                                .size(9.dp)
                                .clip(RoundedCornerShape(50))
                                .background(if (marked) GoldPrimary else Color.Transparent)
                                .border(
                                    width = 1.dp,
                                    color = if (marked) GoldPrimary else GoldBorder,
                                    shape = RoundedCornerShape(50)
                                )
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = day.moodAfter?.emoji ?: "·",
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = initials.getOrNull(day.dayIndexFromMonday)?.toString() ?: "",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextSecondary
                    )
                }
            }
        }
    }
}

@Composable
private fun WeeklySectionTitle(text: String) {
    Text(
        text = text,
        fontFamily = CinzelFontFamily,
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.8.sp,
        color = GoldPrimary
    )
}

@Composable
private fun WeeklyIntentionSection(
    profile: UserProfileEntity?,
    language: AppLanguage,
    weekKey: String,
    onPick: (String) -> Unit
) {
    val chips = listOf(
        AppStrings.weeklyIntentionChip1(language),
        AppStrings.weeklyIntentionChip2(language),
        AppStrings.weeklyIntentionChip3(language),
        AppStrings.weeklyIntentionChip4(language)
    )
    val heldForThisWeek = profile?.weeklyIntentionWeekKey == weekKey &&
        profile.weeklyIntentionText.isNotBlank()
    val heldText = if (heldForThisWeek) profile?.weeklyIntentionText.orEmpty() else ""

    Text(
        text = AppStrings.weeklyIntentionPrompt(language),
        fontFamily = CinzelFontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.8.sp,
        color = GoldPrimary
    )
    Spacer(modifier = Modifier.height(10.dp))

    chips.chunked(2).forEach { rowChips ->
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            rowChips.forEach { chip ->
                val selected = chip == heldText
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(18.dp))
                        .background(if (selected) GoldPrimary else Midnight800)
                        .border(
                            1.dp,
                            if (selected) GoldPrimary else GoldBorder,
                            RoundedCornerShape(18.dp)
                        )
                        .clickable { onPick(chip) }
                        .testTag("weekly_intention_chip")
                        .padding(horizontal = 14.dp, vertical = 9.dp)
                ) {
                    Text(
                        text = if (selected) "$chip ✓" else chip,
                        fontSize = 12.5.sp,
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                        color = if (selected) Midnight900 else TextGold
                    )
                }
            }
        }
    }

    if (heldForThisWeek) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = AppStrings.weeklyIntentionSaved(language),
            fontSize = 12.sp,
            color = TextSecondary
        )
    }
}

/** "Oct 26 – Nov 1" in the reader's language. */
private fun weekRangeLabel(report: WeeklyReport, language: AppLanguage): String {
    val format = SimpleDateFormat("MMM d", localeFor(language))
    val start = runCatching {
        format.format(SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(report.mondayIso)!!)
    }.getOrDefault("")
    val end = runCatching {
        format.format(SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(report.sundayIso)!!)
    }.getOrDefault("")
    return "$start – $end"
}

private fun localeFor(language: AppLanguage): Locale = when (language) {
    AppLanguage.FR -> Locale.FRENCH
    AppLanguage.AR -> Locale("ar")
    AppLanguage.ES -> Locale("es")
    AppLanguage.ID -> Locale("id")
    AppLanguage.EN -> Locale.US
}

private fun lightMomentLabel(key: String?, language: AppLanguage): String {
    val safeKey = key ?: return ""
    return AppStrings.lightReturnName(language, safeKey)
}
