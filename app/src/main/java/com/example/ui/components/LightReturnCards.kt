package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.ui.MainViewModel
import com.example.ui.theme.CinzelFontFamily
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.Midnight800
import com.example.ui.theme.Midnight950
import com.example.ui.theme.TextGold
import com.example.ui.theme.TextSecondary

/**
 * The five return cards of the light Presence Rhythm home (slice 2a): one
 * checkbox per daily moment, light names only, and a "pause now" action on
 * the current/next moment that opens the guided pause (Live Companion).
 *
 * Ticking a card reuses the existing journal habits ("fajr" → fajrDone, …)
 * through [MainViewModel.toggleHabit], so the data model is unchanged — only
 * the words differ. The checkboxes are a trace, never a judgment: no
 * streaks, no score is displayed here.
 */
@Composable
fun LightReturnCards(
    viewModel: MainViewModel,
    todayJournal: DailyJournalEntity?,
    nextMomentKey: String?,
    language: AppLanguage,
    onPauseNow: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Neutral, time-of-day emojis: the light skin shows the shape of the
    // day, not the tradition's iconography.
    val moments = listOf(
        "fajr" to "🌅",
        "dhuhr" to "☀️",
        "asr" to "🌤️",
        "maghrib" to "🌇",
        "isha" to "🌙"
    )
    val currentKey = nextMomentKey?.trim()?.lowercase()

    CelestialCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .testTag("light_return_cards")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = AppStrings.lightReturnChecklistTitle(language),
                fontFamily = CinzelFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 13.5.sp,
                letterSpacing = 0.5.sp,
                color = TextGold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            moments.forEach { (key, emoji) ->
                val done = when (key) {
                    "fajr" -> todayJournal?.fajrDone == true
                    "dhuhr" -> todayJournal?.dhuhrDone == true
                    "asr" -> todayJournal?.asrDone == true
                    "maghrib" -> todayJournal?.maghribDone == true
                    else -> todayJournal?.ishaDone == true
                }
                val isCurrent = key == currentKey

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            if (isCurrent) GoldPrimary.copy(alpha = 0.10f) else Color.Transparent
                        )
                        .clickable { viewModel.toggleHabit(key, !done) }
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                        .testTag("light_return_${key}_card")
                ) {
                    Text(text = emoji, fontSize = 18.sp)
                    Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = AppStrings.lightReturnName(language, key),
                            fontFamily = CinzelFontFamily,
                            fontWeight = if (done) FontWeight.SemiBold else FontWeight.Medium,
                            fontSize = 13.sp,
                            color = if (done) TextGold else TextSecondary
                        )
                        if (isCurrent) {
                            Text(
                                text = AppStrings.lightPauseNowBtn(language),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextGold,
                                modifier = Modifier
                                    .clickable { onPauseNow() }
                                    .padding(top = 2.dp)
                                    .testTag("light_pause_now_button")
                            )
                        }
                    }
                    Checkbox(
                        checked = done,
                        onCheckedChange = { viewModel.toggleHabit(key, it) },
                        colors = CheckboxDefaults.colors(
                            checkedColor = GoldPrimary,
                            checkmarkColor = Midnight950,
                            uncheckedColor = TextSecondary
                        ),
                        modifier = Modifier.testTag("light_return_${key}_checkbox")
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = AppStrings.lightReturnChecklistNote(language),
                fontSize = 10.5.sp,
                color = TextSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
