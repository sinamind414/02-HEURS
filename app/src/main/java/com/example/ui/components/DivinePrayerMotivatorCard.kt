package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.calculator.DivineTimeCalculator
import com.example.data.local.DailyJournalEntity
import com.example.data.localization.AppStrings
import com.example.data.model.AppLanguage
import com.example.data.model.PrayerCountdownState
import com.example.data.model.PrayerCycleState
import com.example.ui.MainViewModel
import com.example.ui.theme.AmiriFontFamily
import com.example.ui.theme.CelestialCyan
import com.example.ui.theme.CinzelFontFamily
import com.example.ui.theme.DawnCream900
import com.example.ui.theme.DawnTextPrimary
import com.example.ui.theme.DawnTextSecondary
import com.example.ui.theme.DawnGoldPrimary
import com.example.ui.theme.DawnCream800
import com.example.ui.theme.DawnCream600
import com.example.ui.theme.EmeraldHasanat
import com.example.ui.theme.GoldBorder
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.LocalSaatanThemeIsDay
import com.example.ui.theme.Midnight700
import com.example.ui.theme.Midnight800
import com.example.ui.theme.Midnight900
import com.example.ui.theme.Midnight950
import com.example.ui.theme.SoftRose
import com.example.ui.theme.TextGold
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

/**
 * Ultimate Prayer Motivator Component ("Le Grand Motiveur de Prière")
 * Translates prayer into divine time units to obliterate the excuse of "lack of time":
 * - 5 minutes on Earth ≈ 0.0008 divine seconds
 * - 80 years of 5 daily prayers = only ≈ 2.0 divine minutes out of 120 divine minutes
 * - Interactive daily 5-prayer check-in with live celestial investment metric
 * - Reality contrast between daily screen time vs prayer time
 */
@Composable
fun DivinePrayerMotivatorCard(
    viewModel: MainViewModel,
    prayerState: PrayerCountdownState,
    todayJournal: DailyJournalEntity?,
    language: AppLanguage,
    cycleState: PrayerCycleState = PrayerCycleState(),
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val isDayMode = LocalSaatanThemeIsDay.current

    var isExpandedDetails by remember { mutableStateOf(false) }

    val fajrDone = todayJournal?.fajrDone == true
    val dhuhrDone = todayJournal?.dhuhrDone == true
    val asrDone = todayJournal?.asrDone == true
    val maghribDone = todayJournal?.maghribDone == true
    val ishaDone = todayJournal?.ishaDone == true

    val completedCount = listOf(fajrDone, dhuhrDone, asrDone, maghribDone, ishaDone).count { it }
    val progress = (completedCount / 5f).coerceIn(0f, 1f)
    // One prayer is 5 earth minutes; convert it with the shared constant so the
    // card can never disagree with the calculator again.
    val divineSecondsPerPrayer =
        DivineTimeCalculator.DIVINE_SECONDS_PER_EARTH_MINUTE * 5.0
    val investedDivineSeconds = completedCount * divineSecondsPerPrayer

    val infiniteTransition = rememberInfiniteTransition(label = "pulse_glow")
    val pulseGlow by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 0.95f,
        animationSpec = infiniteRepeatable(
            animation = tween(1400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "motivator_glow"
    )

    val cardBg = if (isDayMode) Color.White.copy(alpha = 0.97f) else Midnight900.copy(alpha = 0.96f)
    val cardBorder = if (isDayMode) GoldPrimary.copy(alpha = 0.4f) else GoldPrimary.copy(alpha = 0.7f)

    CelestialCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .testTag("divine_prayer_motivator_card"),
        backgroundColor = cardBg,
        borderColor = cardBorder
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            // Header Banner
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(EmeraldHasanat.copy(alpha = 0.2f))
                            .border(1.dp, EmeraldHasanat, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "🕌", fontSize = 18.sp)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = AppStrings.invitationTitle(language),
                            fontFamily = CinzelFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            letterSpacing = 0.6.sp,
                            color = GoldLight
                        )
                        Text(
                            text = AppStrings.rendezVousLabel(language),
                            fontFamily = AmiriFontFamily,
                            fontSize = 12.sp,
                            color = TextSecondary
                        )
                    }
                }
            }

            // La Fenêtre d'or — the last third of the night (ساعة الإجابة).
            // A single quiet gold thread: no alarm, no badge count.
            if (cycleState.inGoldenWindow) {
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(GoldPrimary.copy(alpha = 0.12f))
                        .border(1.dp, GoldPrimary.copy(alpha = 0.45f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                        .testTag("golden_window_badge")
                ) {
                    Text(
                        text = "✦ " + AppStrings.goldenWindowBadge(language),
                        fontSize = 10.5.sp,
                        color = TextGold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // THE DIVINE TIME SHOCK BOX
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Midnight800,
                                Midnight950
                            )
                        )
                    )
                    .border(1.2.dp, GoldBorder, RoundedCornerShape(14.dp))
                    .padding(14.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = AppStrings.shockFiguresTitle(language),
                            fontFamily = CinzelFontFamily,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = GoldPrimary
                        )
                        Text(
                            text = AppStrings.oneDayEqualsThousandYears(language),
                            fontSize = 10.sp,
                            color = TextMuted
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "« ${AppStrings.prayerFiveMinutesShock(language)} »",
                        fontFamily = CinzelFontFamily,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextGold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "« ${AppStrings.prayerLifetimeShock(language)} »",
                        fontFamily = CinzelFontFamily,
                        fontSize = 13.5.sp,
                        fontWeight = FontWeight.Bold,
                        color = CelestialCyan
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Midnight700)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = AppStrings.prayerShockCallout(language),
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        color = TextPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // TODAY'S 5 PRAYERS CELESTIAL INVESTMENT PROGRESS
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = AppStrings.todayPrayerInvestment(language).uppercase(),
                    fontFamily = CinzelFontFamily,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = GoldLight
                )
                Text(
                    text = AppStrings.prayersDoneRatio(language, completedCount),
                    fontFamily = CinzelFontFamily,
                    fontSize = 11.5.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (completedCount == 5) EmeraldHasanat else TextGold
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Progress bar
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = EmeraldHasanat,
                trackColor = Midnight700,
                strokeCap = StrokeCap.Round
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = AppStrings.divineSecondsInvested(language, String.format(java.util.Locale.US, "%.5f", investedDivineSeconds)),
                fontSize = 11.sp,
                color = CelestialCyan
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 5 INTERACTIVE PRAYER CHECK-IN TILES
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                PrayerCheckinChip(
                    name = "Fajr",
                    arabicName = "الفجر",
                    isDone = fajrDone,
                    onClick = {
                        triggerSpiritualHaptic(context, 35)
                        viewModel.toggleHabit("fajr", !fajrDone)
                    },
                    modifier = Modifier.weight(1f)
                )
                PrayerCheckinChip(
                    name = "Dhuhr",
                    arabicName = "الظهر",
                    isDone = dhuhrDone,
                    onClick = {
                        triggerSpiritualHaptic(context, 35)
                        viewModel.toggleHabit("dhuhr", !dhuhrDone)
                    },
                    modifier = Modifier.weight(1f)
                )
                PrayerCheckinChip(
                    name = "Asr",
                    arabicName = "العصر",
                    isDone = asrDone,
                    onClick = {
                        triggerSpiritualHaptic(context, 35)
                        viewModel.toggleHabit("asr", !asrDone)
                    },
                    modifier = Modifier.weight(1f)
                )
                PrayerCheckinChip(
                    name = "Maghrib",
                    arabicName = "المغرب",
                    isDone = maghribDone,
                    onClick = {
                        triggerSpiritualHaptic(context, 35)
                        viewModel.toggleHabit("maghrib", !maghribDone)
                    },
                    modifier = Modifier.weight(1f)
                )
                PrayerCheckinChip(
                    name = "Isha",
                    arabicName = "العشاء",
                    isDone = ishaDone,
                    onClick = {
                        triggerSpiritualHaptic(context, 35)
                        viewModel.toggleHabit("isha", !ishaDone)
                    },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // ── LA QUESTION DU GOÛT (Dhawq) ──
            // One quiet question, one tap. The answer is stored for today
            // only and is never compared, scored or charted (Serment #2).
            val currentTaste = todayJournal?.salahTaste ?: ""
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        if (isDayMode) DawnCream900 else Midnight800.copy(alpha = 0.55f)
                    )
                    .padding(horizontal = 12.dp, vertical = 10.dp)
                    .testTag("taste_question_card")
            ) {
                Text(
                    text = AppStrings.tasteQuestion(language),
                    fontFamily = AmiriFontFamily,
                    fontSize = 12.5.sp,
                    color = if (isDayMode) DawnTextPrimary else TextPrimary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    TasteChip(
                        label = AppStrings.tasteDouce(language),
                        isSelected = currentTaste == "douce",
                        isDayMode = isDayMode,
                        onClick = { viewModel.recordSalahTaste("douce") },
                        modifier = Modifier.weight(1f)
                    )
                    TasteChip(
                        label = AppStrings.tasteLourde(language),
                        isSelected = currentTaste == "lourde",
                        isDayMode = isDayMode,
                        onClick = { viewModel.recordSalahTaste("lourde") },
                        modifier = Modifier.weight(1f)
                    )
                    TasteChip(
                        label = AppStrings.tasteAbsente(language),
                        isSelected = currentTaste == "absente",
                        isDayMode = isDayMode,
                        onClick = { viewModel.recordSalahTaste("absente") },
                        modifier = Modifier.weight(1f)
                    )
                    TasteChip(
                        label = AppStrings.tasteGoutee(language),
                        isSelected = currentTaste == "goutee",
                        isDayMode = isDayMode,
                        onClick = { viewModel.recordSalahTaste("goutee") },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // PRIORITY SHOCK TOGGLE: PHONE VS PRAYER
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Midnight800)
                    .border(1.dp, Midnight700, RoundedCornerShape(10.dp))
                    .clickable {
                        triggerSpiritualHaptic(context, 20)
                        isExpandedDetails = !isExpandedDetails
                    }
                    .padding(horizontal = 12.dp, vertical = 10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "⚖️", fontSize = 16.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = AppStrings.screenVsPrayerTitle(language),
                            fontFamily = CinzelFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 11.5.sp,
                            color = GoldLight
                        )
                    }

                    Icon(
                        imageVector = if (isExpandedDetails) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = GoldPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            AnimatedVisibility(visible = isExpandedDetails) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .background(Midnight950.copy(alpha = 0.8f), RoundedCornerShape(12.dp))
                        .border(1.dp, GoldBorder, RoundedCornerShape(12.dp))
                        .padding(12.dp)
                ) {
                    Text(
                        text = AppStrings.screenLifetimeStat(language),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = SoftRose
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = AppStrings.prayerLifetimeStat(language),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = EmeraldHasanat
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = AppStrings.priorityQuestion(language),
                        fontSize = 11.5.sp,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        color = TextGold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = AppStrings.prayerJudgementHadith(language),
                        fontFamily = AmiriFontFamily,
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                }
            }
        }
    }
}

@Composable
private fun PrayerCheckinChip(
    name: String,
    arabicName: String,
    isDone: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColor = if (isDone) EmeraldHasanat else GoldBorder
    val bgColor = if (isDone) EmeraldHasanat.copy(alpha = 0.18f) else Midnight800

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(bgColor)
            .border(1.dp, borderColor, RoundedCornerShape(10.dp))
            .clickable { onClick() }
            .padding(vertical = 8.dp, horizontal = 2.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = if (isDone) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                contentDescription = null,
                tint = if (isDone) EmeraldHasanat else TextMuted,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = arabicName,
                fontFamily = AmiriFontFamily,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = if (isDone) EmeraldHasanat else TextPrimary
            )
            Text(
                text = name,
                fontSize = 9.sp,
                color = if (isDone) EmeraldHasanat else TextSecondary,
                maxLines = 1
            )
        }
    }
}

/**
 * A single answer chip for "Quel goût ?". Selecting an already-selected chip
 * clears the answer — the question must always stay free to change (the
 * taste is never locked in, never scored).
 */
@Composable
private fun TasteChip(
    label: String,
    isSelected: Boolean,
    isDayMode: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(
                when {
                    isSelected && isDayMode -> DawnGoldPrimary.copy(alpha = 0.18f)
                    isSelected -> GoldPrimary.copy(alpha = 0.18f)
                    isDayMode -> DawnCream800
                    else -> Midnight800
                }
            )
            .border(
                width = 1.dp,
                color = when {
                    isSelected && isDayMode -> DawnGoldPrimary
                    isSelected -> GoldPrimary
                    isDayMode -> DawnCream600
                    else -> Midnight700
                },
                shape = RoundedCornerShape(50)
            )
            .clickable { onClick() }
            .padding(horizontal = 8.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            fontSize = 11.sp,
            maxLines = 1,
            color = when {
                isSelected -> if (isDayMode) DawnGoldPrimary else TextGold
                isDayMode -> DawnTextSecondary
                else -> TextSecondary
            }
        )
    }
}
