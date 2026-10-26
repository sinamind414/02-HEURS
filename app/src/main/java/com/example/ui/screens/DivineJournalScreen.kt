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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.calculator.DivineTimeCalculator
import com.example.data.localization.AppStrings
import com.example.ui.MainViewModel
import com.example.ui.components.BismillahHeader
import com.example.ui.components.CelestialCard
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

@Composable
fun DivineJournalScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val todayJournal by viewModel.todayJournal.collectAsState()
    val language by viewModel.appLanguage.collectAsState()
    val isDayMode by viewModel.isDayMode.collectAsState()

    var noteText by remember(todayJournal?.dailyNote) {
        mutableStateOf(todayJournal?.dailyNote ?: "")
    }

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

        // Title and Today Divine Value
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "📖 " + AppStrings.journalTitle(language),
                    fontFamily = CinzelFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 19.sp,
                    letterSpacing = 1.sp,
                    color = accentGold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(cardColor)
                        .border(1.dp, GoldBorder, RoundedCornerShape(20.dp))
                        .padding(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = AppStrings.todayIsLabel(language),
                        fontFamily = CinzelFontFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = CelestialCyan,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = AppStrings.howFilledQuestion(language),
                    fontSize = 13.sp,
                    color = textSecondary,
                    textAlign = TextAlign.Center
                )
            }
        }

        // Divine Score Banner
        item {
            val score = todayJournal?.divineScoreStars ?: 1
            CelestialCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                backgroundColor = cardColor.copy(alpha = 0.9f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = AppStrings.dailyScoreLabel(language),
                            fontFamily = CinzelFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            letterSpacing = 1.sp,
                            color = GoldLight
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = AppStrings.scoreMessage(language, score),
                            fontSize = 12.sp,
                            color = textSecondary
                        )
                    }

                    Row {
                        for (i in 1..5) {
                            Icon(
                                imageVector = if (i <= score) Icons.Filled.Star else Icons.Outlined.Star,
                                contentDescription = null,
                                // Empty stars stay readable (>=3:1): Midnight700
                                // was too dim against the rating card.
                                tint = if (i <= score) accentGold else textMuted,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }
        }

        // Daily Deeds Checklist Section
        item {
            Column(modifier = Modifier.padding(start = 24.dp, top = 12.dp, bottom = 6.dp)) {
                Text(
                    text = "🕌 " + AppStrings.prayerChecklistTitle(language),
                    fontFamily = CinzelFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    letterSpacing = 0.5.sp,
                    color = GoldLight
                )
            }
        }

        // Prayers Checklist
        item {
            CelestialCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 4.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    val prayers = listOf(
                        "fajr" to (todayJournal?.fajrDone == true),
                        "dhuhr" to (todayJournal?.dhuhrDone == true),
                        "asr" to (todayJournal?.asrDone == true),
                        "maghrib" to (todayJournal?.maghribDone == true),
                        "isha" to (todayJournal?.ishaDone == true)
                    )
                    prayers.forEach { (key, done) ->
                        DeedCheckRow(
                            title = AppStrings.prayerDeedLabel(language, key),
                            subtitle = AppStrings.prayerDeedSubtitle(language, key),
                            isChecked = done,
                            isDayMode = isDayMode,
                            onCheck = {
                                triggerSpiritualHaptic(context)
                                viewModel.toggleHabit(key, it)
                            }
                        )
                    }
                }
            }
        }

        // High Hassanat Deeds
        item {
            Column(modifier = Modifier.padding(start = 24.dp, top = 14.dp, bottom = 6.dp)) {
                Text(
                    text = AppStrings.hassanatSectionTitle(language),
                    fontFamily = CinzelFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    letterSpacing = 0.5.sp,
                    color = GoldLight
                )
            }
        }

        item {
            CelestialCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 4.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    // Quran Pages
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = AppStrings.quranPagesTitle(language),
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                color = textPrimary
                            )
                            Text(
                                text = AppStrings.quranHasanatNote(language),
                                fontSize = 11.5.sp,
                                color = textSecondary
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            val pages = todayJournal?.quranPagesRead ?: 0
                            Button(
                                onClick = {
                                    triggerSpiritualHaptic(context)
                                    viewModel.updateQuranPages(maxOf(0, pages - 1))
                                },
                                modifier = Modifier.size(34.dp),
                                contentPadding = PaddingValues(0.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = insetColor)
                            ) {
                                Text("-", fontSize = 18.sp, color = textPrimary)
                            }
                            Text(
                                text = AppStrings.pagesReadLabel(language, pages),
                                fontFamily = CinzelFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = goldText,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            )
                            Button(
                                onClick = {
                                    triggerSpiritualHaptic(context)
                                    viewModel.updateQuranPages(pages + 1)
                                },
                                modifier = Modifier.size(34.dp),
                                contentPadding = PaddingValues(0.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = accentGold)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = null,
                                    tint = Midnight950,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(insetColor)
                    )

                    DeedCheckRow(
                        title = "🤲 " + AppStrings.sadaqaDone(language),
                        subtitle = AppStrings.sadaqaSubtitle(language),
                        isChecked = todayJournal?.sadaqaGiven == true,
                        isDayMode = isDayMode,
                        onCheck = {
                            triggerSpiritualHaptic(context)
                            viewModel.toggleHabit("sadaqa", it)
                        }
                    )

                    DeedCheckRow(
                        title = "📿 " + AppStrings.eveningDhikr(language),
                        subtitle = AppStrings.dhikrSubtitle(language),
                        isChecked = todayJournal?.morningEveningDhikr == true,
                        isDayMode = isDayMode,
                        onCheck = {
                            triggerSpiritualHaptic(context)
                            viewModel.toggleHabit("dhikr", it)
                        }
                    )

                    DeedCheckRow(
                        title = "🤝 " + AppStrings.goodDeed(language),
                        subtitle = AppStrings.goodDeedSubtitle(language),
                        isChecked = todayJournal?.goodDeedDone == true,
                        isDayMode = isDayMode,
                        onCheck = {
                            triggerSpiritualHaptic(context)
                            viewModel.toggleHabit("good_deed", it)
                        }
                    )
                }
            }
        }

        // Interactive Tasbih Counter
        item {
            val count = todayJournal?.tasbihCountToday ?: 0
            CelestialCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                borderColor = CelestialCyan.copy(alpha = 0.5f)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = AppStrings.tasbihTitle(language),
                        fontFamily = CinzelFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        letterSpacing = 1.sp,
                        color = CelestialCyan
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "سُبْحَانَ اللَّهِ • الْحَمْدُ لِلَّهِ • اللَّهُ أَكْبَرُ",
                        fontFamily = AmiriFontFamily,
                        fontSize = 20.sp,
                        color = goldText,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    // Big Tap Button for Tasbih
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.radialGradient(
                                    colors = listOf(insetColor, cardColor)
                                )
                            )
                            .border(2.dp, accentGold, CircleShape)
                            .clickable {
                                triggerSpiritualHaptic(context, 35)
                                viewModel.tapTasbih()
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "$count",
                                fontFamily = CinzelFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 28.sp,
                                color = goldText
                            )
                            Text(
                                text = AppStrings.tasbihTapLabel(language),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = textSecondary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    // A dhikr takes about 2 earth seconds; convert that honestly
                    // instead of using a magic 0.00003 constant.
                    val divinePerTap =
                        DivineTimeCalculator.DIVINE_SECONDS_PER_EARTH_MINUTE / 60.0 * 2.0
                    Text(
                        text = AppStrings.tasbihDivineEquivalent(language, count * divinePerTap),
                        fontSize = 11.5.sp,
                        color = textMuted
                    )
                }
            }
        }

        // Reflection Note of the Day
        item {
            CelestialCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 6.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = AppStrings.doNotWasteQuote(language),
                        fontFamily = CinzelFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.5.sp,
                        color = goldText
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = noteText,
                        onValueChange = {
                            noteText = it
                            viewModel.updateJournalNote(it)
                        },
                        placeholder = {
                            Text(
                                text = AppStrings.journalNotePlaceholder(language),
                                fontSize = 12.5.sp,
                                color = textMuted
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(110.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = accentGold,
                            unfocusedBorderColor = insetColor,
                            focusedTextColor = textPrimary,
                            unfocusedTextColor = textPrimary
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun DeedCheckRow(
    title: String,
    subtitle: String,
    isChecked: Boolean,
    isDayMode: Boolean,
    onCheck: (Boolean) -> Unit
) {
    val textPrimary = if (isDayMode) DawnTextPrimary else TextPrimary
    val textSecondary = if (isDayMode) DawnTextSecondary else TextSecondary
    val goldText = if (isDayMode) DawnGoldPrimary else TextGold
    val uncheckedColor = if (isDayMode) DawnCream800 else Midnight700

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCheck(!isChecked) }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheck,
            colors = CheckboxDefaults.colors(
                checkedColor = if (isDayMode) DawnGoldPrimary else GoldPrimary,
                checkmarkColor = Midnight950,
                uncheckedColor = uncheckedColor
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 13.5.sp,
                fontWeight = if (isChecked) FontWeight.SemiBold else FontWeight.Normal,
                color = if (isChecked) goldText else textPrimary
            )
            Text(
                text = subtitle,
                fontSize = 11.sp,
                color = textSecondary
            )
        }
    }
}
