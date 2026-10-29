package com.example.ui.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.localization.AppStrings
import com.example.data.model.AppLanguage
import com.example.data.model.InnerWeather
import com.example.ui.MainViewModel
import com.example.ui.theme.GoldBorder
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.Midnight800
import com.example.ui.theme.Midnight950
import com.example.ui.theme.TextGold
import com.example.ui.theme.TextSecondary

/**
 * The guided pause — the light skin's "Live Companion" (slice 2b, strategy
 * §5). Three short secular steps (breathe → notice → one intention) close
 * with the post-pause Inner Weather: the chosen label is written to
 * `moodAfterPause` and nothing else happens. No religious vocabulary, no
 * score, no "well done" — the pause is its own point.
 */
@Composable
fun GuidedPauseOverlay(
    viewModel: MainViewModel,
    language: AppLanguage,
    onClose: () -> Unit
) {
    // 0 breathe · 1 notice · 2 intention · 3 post-pause Inner Weather.
    var step by remember { mutableIntStateOf(0) }

    val breathTransition = rememberInfiniteTransition(label = "guided_breath")
    val breathScale by breathTransition.animateFloat(
        initialValue = 0.82f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(3800),
            repeatMode = RepeatMode.Reverse
        ),
        label = "guided_breath_scale"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Midnight950.copy(alpha = 0.98f))
            .testTag("guided_pause_root")
    ) {
        // Modal scrim — a click-absorbing layer UNDER the content (last
        // drawn sibling wins hit-testing): taps that miss every control die
        // here instead of falling through to the return cards behind the
        // pause (that is how a stray tap once ticked a checkbox behind).
        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { /* absorb — the pause asks for nothing else */ }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp, vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = AppStrings.guidedPauseTitle(language),
                fontSize = 12.sp,
                letterSpacing = 1.sp,
                color = TextSecondary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = AppStrings.onboardingStepLabel(language, step + 1, 4),
                fontSize = 11.sp,
                color = TextSecondary
            )
            Spacer(modifier = Modifier.height(26.dp))

            when (step) {
                0 -> {
                    // The breathing circle: the only "instruction" is visual.
                    Box(
                        modifier = Modifier
                            .size(140.dp)
                            .scale(breathScale)
                            .clip(CircleShape)
                            .background(GoldPrimary.copy(alpha = 0.12f))
                            .border(2.dp, GoldPrimary.copy(alpha = 0.7f), CircleShape)
                            .testTag("guided_pause_breath_circle")
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    GuidedPauseStepText(
                        title = AppStrings.guidedPauseBreatheTitle(language),
                        body = AppStrings.guidedPauseBreatheBody(language)
                    )
                }
                1 -> GuidedPauseStepText(
                    title = AppStrings.guidedPausePresenceTitle(language),
                    body = AppStrings.guidedPausePresenceBody(language),
                    tag = "guided_pause_step_notice"
                )
                2 -> GuidedPauseStepText(
                    title = AppStrings.guidedPauseIntentionTitle(language),
                    body = AppStrings.guidedPauseIntentionBody(language),
                    tag = "guided_pause_step_intention"
                )
                else -> {
                    // Post-pause Inner Weather — same five labels as the
                    // morning check-in, stored in moodAfterPause.
                    Text(
                        text = AppStrings.innerWeatherTitle(language),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextGold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    InnerWeather.entries.forEach { mood ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(14.dp))
                                .background(Midnight800)
                                .border(1.dp, GoldBorder, RoundedCornerShape(14.dp))
                                .clickable {
                                    viewModel.setMoodAfterPause(mood)
                                    onClose()
                                }
                                .padding(horizontal = 14.dp, vertical = 10.dp)
                                .testTag("guided_pause_mood_${mood.key}")
                        ) {
                            Text(text = mood.emoji, fontSize = 18.sp)
                            Spacer(modifier = Modifier.size(10.dp))
                            Text(
                                text = AppStrings.innerWeatherLabel(language, mood.key),
                                fontSize = 13.sp,
                                color = TextSecondary
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = AppStrings.innerWeatherNote(language),
                        fontSize = 10.5.sp,
                        color = TextSecondary,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
            if (step < 3) {
                Button(
                    onClick = { step++ },
                    modifier = Modifier.testTag("guided_pause_next"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GoldPrimary,
                        contentColor = Midnight950
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = AppStrings.onboardingContinueBtn(language),
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
            }
            TextButton(
                onClick = onClose,
                modifier = Modifier.testTag("guided_pause_close")
            ) {
                Text(
                    text = AppStrings.guidedPauseSkipBtn(language),
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }
        }
    }
}

@Composable
private fun GuidedPauseStepText(
    title: String,
    body: String,
    tag: String = "guided_pause_step_breathe"
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .testTag(tag)
    ) {
        Text(
            text = title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = TextGold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = body,
            fontSize = 14.sp,
            color = TextSecondary,
            textAlign = TextAlign.Center
        )
    }
}
