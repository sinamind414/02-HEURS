package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.localization.AppStrings
import com.example.data.model.AppLanguage
import com.example.ui.MainViewModel
import com.example.ui.components.BismillahHeader
import com.example.ui.components.CelestialCard
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
import com.example.ui.theme.Midnight950
import com.example.ui.theme.TextGold
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun ChallengesScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    // Collected as a StateFlow so the list refreshes as soon as the journal or
    // the profile changes. Reading `viewModel.getChallengesList()` from a
    // composable never subscribed to anything.
    val challenges by viewModel.challenges.collectAsState()
    val language by viewModel.appLanguage.collectAsState()
    val isDayMode by viewModel.isDayMode.collectAsState()

    val completedCount = challenges.count { it.isUnlocked }

    val backgroundColor = if (isDayMode) DawnCream900 else Midnight950
    val cardColor = if (isDayMode) DawnCream800 else Midnight800
    val cardColorDim = if (isDayMode) DawnCream800.copy(alpha = 0.7f) else Midnight800.copy(alpha = 0.7f)
    val textPrimary = if (isDayMode) DawnTextPrimary else TextPrimary
    val textSecondary = if (isDayMode) DawnTextSecondary else TextSecondary
    val textMuted = if (isDayMode) DawnTextMuted else TextMuted
    val accentGold = if (isDayMode) DawnGoldPrimary else GoldPrimary
    val trackColor = if (isDayMode) DawnCream800 else Midnight700
    val emptySlotColor = if (isDayMode) DawnCream800 else Midnight700

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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.EmojiEvents,
                        contentDescription = null,
                        tint = accentGold,
                        modifier = Modifier.size(22.dp)
                    )
                    Text(
                        text = AppStrings.challengesTitle(language),
                        fontFamily = CinzelFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        letterSpacing = 1.sp,
                        color = accentGold,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = AppStrings.challengesSubtitle(language),
                    fontSize = 12.5.sp,
                    color = textSecondary,
                    textAlign = TextAlign.Center
                )
            }
        }

        // Progression Banner
        item {
            CelestialCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                backgroundColor = cardColor
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = AppStrings.hassanatUnlockedTitle(language),
                            fontFamily = CinzelFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.5.sp,
                            letterSpacing = 1.sp,
                            color = GoldLight
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = AppStrings.challengesCompletedLabel(
                                language, completedCount, challenges.size
                            ),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = textPrimary
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .clip(CircleShape)
                            .background(accentGold.copy(alpha = 0.15f))
                            .border(1.dp, accentGold, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        val percentage = if (challenges.isEmpty()) {
                            0
                        } else {
                            ((completedCount.toFloat() / challenges.size) * 100).toInt()
                        }
                        Text(
                            text = "$percentage%",
                            fontFamily = CinzelFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = if (isDayMode) DawnGoldPrimary else TextGold
                        )
                    }
                }
            }
        }

        // Section Title
        item {
            Column(modifier = Modifier.padding(start = 24.dp, top = 12.dp, bottom = 6.dp)) {
                Text(
                    text = AppStrings.levelsAndBadgesTitle(language),
                    fontFamily = CinzelFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    letterSpacing = 0.5.sp,
                    color = GoldLight
                )
            }
        }

        items(challenges) { challenge ->
            CelestialCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 6.dp),
                borderColor = if (challenge.isUnlocked) accentGold else GoldBorder,
                backgroundColor = if (challenge.isUnlocked) cardColor else cardColorDim
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(46.dp)
                                .clip(CircleShape)
                                .background(
                                    if (challenge.isUnlocked) accentGold.copy(alpha = 0.2f) else emptySlotColor
                                )
                                .border(
                                    1.dp,
                                    if (challenge.isUnlocked) accentGold else GoldBorder,
                                    CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = challenge.iconEmoji, fontSize = 22.sp)
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = AppStrings.levelNameLabel(
                                        language, challenge.levelNumber, challenge.levelName
                                    ),
                                    fontFamily = CinzelFontFamily,
                                    fontSize = 10.5.sp,
                                    letterSpacing = 0.5.sp,
                                    color = if (challenge.isUnlocked) {
                                        if (isDayMode) DawnGoldPrimary else TextGold
                                    } else {
                                        textMuted
                                    }
                                )
                                Text(
                                    text = AppStrings.divineEquivalentShort(language, challenge.divineSeconds),
                                    fontFamily = CinzelFontFamily,
                                    fontSize = 10.5.sp,
                                    color = CelestialCyan
                                )
                            }
                            Text(
                                text = challenge.title,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = textPrimary
                            )
                            Text(
                                text = challenge.targetDescription,
                                fontSize = 11.5.sp,
                                color = textSecondary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        LinearProgressIndicator(
                            progress = {
                                if (challenge.maxProgress <= 0) 0f
                                else (challenge.progress.toFloat() / challenge.maxProgress).coerceIn(0f, 1f)
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(6.dp)
                                .clip(RoundedCornerShape(6.dp)),
                            color = if (challenge.isUnlocked) EmeraldHasanat else accentGold,
                            trackColor = trackColor
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "${challenge.progress}/${challenge.maxProgress}",
                            fontFamily = CinzelFontFamily,
                            fontSize = 11.sp,
                            color = textMuted
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Badge Pill
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = if (challenge.isUnlocked) Icons.Default.CheckCircle else Icons.Default.Lock,
                            contentDescription = null,
                            tint = if (challenge.isUnlocked) EmeraldHasanat else textMuted,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = AppStrings.badgeLabel(language, challenge.badgeName),
                            fontFamily = CinzelFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 11.sp,
                            color = if (challenge.isUnlocked) EmeraldHasanat else textMuted
                        )
                    }
                }
            }
        }
    }
}
