package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.localization.AppStrings
import com.example.data.model.AppLanguage
import com.example.data.model.InnerWeather
import com.example.ui.MainViewModel
import com.example.ui.theme.CinzelFontFamily
import com.example.ui.theme.GoldBorder
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.Midnight800
import com.example.ui.theme.TextGold
import com.example.ui.theme.TextSecondary

/**
 * Inner Weather — the daily, non-clinical check-in of the Presence Rhythm
 * skin (strategy §2, US-05). Five emoji states, no slider, no red "worse"
 * value, no score: tapping an emoji stores today's label on device and
 * nothing else happens. Tapping again (another emoji) simply updates it —
 * the check-in is a temperature, never a verdict.
 */
@Composable
fun InnerWeatherCard(
    viewModel: MainViewModel,
    journalMoodKey: String?,
    language: AppLanguage,
    modifier: Modifier = Modifier
) {
    val currentMood = journalMoodKey?.let { InnerWeather.fromKey(it) }

    CelestialCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .testTag("inner_weather_card")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = AppStrings.innerWeatherTitle(language),
                fontFamily = CinzelFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 13.5.sp,
                letterSpacing = 0.5.sp,
                color = TextGold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                InnerWeather.entries.forEach { mood ->
                    val isSelected = currentMood == mood
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clip(RoundedCornerShape(14.dp))
                            .background(
                                if (isSelected) GoldPrimary.copy(alpha = 0.18f) else Midnight800
                            )
                            .border(
                                width = if (isSelected) 1.5.dp else 1.dp,
                                color = if (isSelected) GoldPrimary else GoldBorder,
                                shape = RoundedCornerShape(14.dp)
                            )
                            .clickable { viewModel.setMoodCheckIn(mood) }
                            .padding(horizontal = 8.dp, vertical = 6.dp)
                            .testTag("inner_weather_${mood.key}")
                    ) {
                        Text(text = mood.emoji, fontSize = 20.sp)
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = AppStrings.innerWeatherLabel(language, mood.key),
                            fontSize = 9.5.sp,
                            color = if (isSelected) TextGold else TextSecondary,
                            maxLines = 1
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = AppStrings.innerWeatherNote(language),
                fontSize = 10.5.sp,
                color = TextSecondary,
                textAlign = TextAlign.Center
            )
        }
    }
}
