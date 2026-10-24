package com.example.ui.screens

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.calculator.DivineTimeCalculator
import com.example.data.localization.AppStrings
import com.example.data.model.AppLanguage
import com.example.ui.MainViewModel
import com.example.ui.components.BismillahHeader
import com.example.ui.components.CelestialCard
import com.example.ui.components.triggerSpiritualHaptic
import com.example.ui.theme.CelestialCyan
import com.example.ui.theme.CinzelFontFamily
import com.example.ui.theme.DawnCream800
import com.example.ui.theme.DawnCream900
import com.example.ui.theme.DawnGoldPrimary
import com.example.ui.theme.DawnTextMuted
import com.example.ui.theme.DawnTextPrimary
import com.example.ui.theme.DawnTextSecondary
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

/**
 * A benchmark shown in the converter. Only the numeric input and the emoji are
 * stored here: every user-visible label is resolved from [AppStrings] and the
 * divine equivalent is computed from [DivineTimeCalculator], so a change in the
 * maths or the language can never leave stale copy behind.
 */
data class StrikingExample(
    val id: String,
    val emoji: String,
    val years: Double = 0.0,
    val days: Double = 0.0,
    val minutes: Double = 0.0
)

private val STRIKING_EXAMPLES = listOf(
    StrikingExample(id = "fajr", emoji = "🌅", minutes = 5.0),
    StrikingExample(id = "ramadan", emoji = "🌙", days = 30.0),
    StrikingExample(id = "prophet", emoji = "🕌", years = 63.0),
    StrikingExample(id = "studies", emoji = "🎓", years = 20.0),
    StrikingExample(id = "qadr", emoji = "✨", years = 83.33),
    StrikingExample(id = "scroll", emoji = "📱", minutes = 30.0),
    StrikingExample(id = "sleep", emoji = "😴", minutes = 480.0)
)

@Composable
fun TemporalConverterScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var selectedTab by remember { mutableIntStateOf(0) } // 0: Earth -> Divine, 1: Divine -> Earth
    val language by viewModel.appLanguage.collectAsState()
    val isDayMode by viewModel.isDayMode.collectAsState()

    val earthYears by viewModel.earthInputYears.collectAsState()
    val earthDays by viewModel.earthInputDays.collectAsState()
    val earthMinutes by viewModel.earthInputMinutes.collectAsState()
    val divineResult by viewModel.converterResult.collectAsState()

    val divineMinutesInput by viewModel.divineInputMinutes.collectAsState()
    val reverseResult by viewModel.reverseResult.collectAsState()

    val backgroundColor = if (isDayMode) DawnCream900 else Midnight950
    val cardColor = if (isDayMode) DawnCream800 else Midnight800
    val insetColor = if (isDayMode) DawnCream900 else Midnight700
    val textPrimary = if (isDayMode) DawnTextPrimary else TextPrimary
    val textSecondary = if (isDayMode) DawnTextSecondary else TextSecondary
    val textMuted = if (isDayMode) DawnTextMuted else TextMuted
    val accentGold = if (isDayMode) DawnGoldPrimary else GoldPrimary
    val goldText = if (isDayMode) DawnGoldPrimary else TextGold

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentPadding = PaddingValues(bottom = 96.dp)
    ) {
        item {
            BismillahHeader()
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "⏳ " + AppStrings.converterTitle(language),
                    fontFamily = CinzelFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    letterSpacing = 1.sp,
                    color = accentGold,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = AppStrings.converterSubtitle(language),
                    fontSize = 12.5.sp,
                    color = textSecondary,
                    textAlign = TextAlign.Center
                )
            }
        }

        // Mode Tabs
        item {
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = if (isDayMode) DawnCream800 else Midnight900,
                contentColor = accentGold,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                        color = accentGold
                    )
                },
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .border(1.dp, GoldBorder, RoundedCornerShape(14.dp))
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = {
                        triggerSpiritualHaptic(context)
                        selectedTab = 0
                    },
                    text = {
                        Text(
                            text = AppStrings.tabEarthToDivine(language),
                            fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 13.sp
                        )
                    }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = {
                        triggerSpiritualHaptic(context)
                        selectedTab = 1
                    },
                    text = {
                        Text(
                            text = AppStrings.tabDivineToEarth(language),
                            fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 13.sp
                        )
                    }
                )
            }
        }

        // Active Converter Calculator Box
        item {
            CelestialCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                borderColor = accentGold.copy(alpha = 0.6f)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    if (selectedTab == 0) {
                        // Earth -> Divine
                        Text(
                            text = AppStrings.enterEarthDuration(language),
                            fontFamily = CinzelFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp,
                            color = goldText
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                value = earthYears,
                                onValueChange = { viewModel.setEarthYears(it) },
                                label = { Text(AppStrings.yearsFieldLabel(language), fontSize = 11.sp) },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier.weight(1f),
                                colors = converterFieldColors(isDayMode),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = earthDays,
                                onValueChange = { viewModel.setEarthDays(it) },
                                label = { Text(AppStrings.daysFieldLabel(language), fontSize = 11.sp) },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier.weight(1f),
                                colors = converterFieldColors(isDayMode),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = earthMinutes,
                                onValueChange = { viewModel.setEarthMinutes(it) },
                                label = { Text(AppStrings.minutesFieldLabel(language), fontSize = 11.sp) },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier.weight(1f),
                                colors = converterFieldColors(isDayMode),
                                singleLine = true
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(cardColor)
                                .border(1.dp, GoldBorder, RoundedCornerShape(12.dp))
                                .padding(14.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = AppStrings.equivalentWithAllahTitle(language),
                                    fontFamily = CinzelFontFamily,
                                    fontSize = 10.sp,
                                    letterSpacing = 1.sp,
                                    color = textSecondary
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = divineResult,
                                    fontFamily = CinzelFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 22.sp,
                                    color = goldText
                                )
                            }
                        }
                    } else {
                        // Divine -> Earth
                        Text(
                            text = AppStrings.enterDivineMinutes(language),
                            fontFamily = CinzelFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp,
                            color = goldText
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = divineMinutesInput,
                            onValueChange = { viewModel.setDivineMinutes(it) },
                            label = { Text(AppStrings.divineMinutesHint(language), fontSize = 12.sp) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                            modifier = Modifier.fillMaxWidth(),
                            colors = converterFieldColors(isDayMode),
                            singleLine = true
                        )

                        Spacer(modifier = Modifier.height(14.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(cardColor)
                                .border(1.dp, GoldBorder, RoundedCornerShape(12.dp))
                                .padding(14.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = AppStrings.equivalentOnEarthTitle(language),
                                    fontFamily = CinzelFontFamily,
                                    fontSize = 10.sp,
                                    letterSpacing = 1.sp,
                                    color = textSecondary
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = reverseResult,
                                    fontFamily = CinzelFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    color = CelestialCyan
                                )
                            }
                        }
                    }
                }
            }
        }

        // Section Title: Striking Examples
        item {
            Column(modifier = Modifier.padding(start = 24.dp, top = 16.dp, bottom = 8.dp)) {
                Text(
                    text = AppStrings.strikingBenchmarksTitle(language),
                    fontFamily = CinzelFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    letterSpacing = 1.sp,
                    color = GoldLight
                )
                Text(
                    text = AppStrings.strikingBenchmarksSubtitle(language),
                    fontSize = 11.5.sp,
                    color = textMuted
                )
            }
        }

        // List of Striking Examples
        items(STRIKING_EXAMPLES) { example ->
            CelestialCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 6.dp)
                    .clickable {
                        triggerSpiritualHaptic(context)
                        selectedTab = 0
                        viewModel.applyConverterPreset(
                            years = example.years,
                            days = example.days,
                            minutes = example.minutes
                        )
                    },
                backgroundColor = cardColor.copy(alpha = 0.8f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(insetColor)
                            .border(1.dp, GoldBorder, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = example.emoji, fontSize = 22.sp)
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = AppStrings.exampleTitle(language, example.id),
                            fontFamily = CinzelFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = textPrimary
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = AppStrings.exampleEarth(language, example.id),
                                fontSize = 12.5.sp,
                                color = CelestialCyan
                            )
                            Text(
                                text = " = ",
                                fontSize = 12.sp,
                                color = textMuted,
                                modifier = Modifier.padding(horizontal = 4.dp)
                            )
                            Text(
                                text = DivineTimeCalculator.earthToDivineFormatted(
                                    years = example.years,
                                    days = example.days,
                                    minutes = example.minutes,
                                    lang = language
                                ),
                                fontFamily = CinzelFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 13.sp,
                                color = goldText
                            )
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = AppStrings.exampleDescription(language, example.id),
                            fontSize = 11.5.sp,
                            color = textSecondary,
                            lineHeight = 15.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun converterFieldColors(isDayMode: Boolean) = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = if (isDayMode) DawnGoldPrimary else GoldPrimary,
    unfocusedBorderColor = if (isDayMode) DawnCream800 else Midnight700,
    focusedTextColor = if (isDayMode) DawnTextPrimary else TextPrimary,
    unfocusedTextColor = if (isDayMode) DawnTextPrimary else TextPrimary
)
