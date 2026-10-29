package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.localization.AppStrings
import com.example.data.model.AppLanguage
import com.example.data.model.TraditionLayer
import com.example.data.model.UsRhythm
import com.example.ui.theme.CinzelFontFamily
import com.example.ui.theme.GoldBorder
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.Midnight800
import com.example.ui.theme.Midnight900
import com.example.ui.theme.Midnight950
import com.example.ui.theme.TextGold
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

/**
 * The US onboarding flow (strategy §1 — 5 screens, empathic, no judgment):
 *
 *  0. Feeling check — multi-select, no scores, nothing stored
 *  1. The invitation — five pauses, one ancient rhythm, honest about therapy
 *  2. A real testimony — "it didn't fix everything, it gave me somewhere to
 *     come back to"
 *  3. Starting point — Beginner (light skin) / Full (original tradition) /
 *     Explore (gradual)
 *  4. Permission & disclaimer — non-skippable "I understand" + 988
 *
 * Steps 0–3 can be skipped (jumping straight to the disclaimer); the
 * disclaimer itself is the only mandatory step (US-02). The rhythm choice
 * also carries the skin: Beginner → Presence Rhythm light, Full → original
 * names visible, Explore → gradual.
 */
@Composable
fun UsOnboardingScreen(
    language: AppLanguage,
    onComplete: (rhythm: UsRhythm, layer: TraditionLayer) -> Unit,
    modifier: Modifier = Modifier
) {
    var step by remember { mutableStateOf(0) }
    var rhythm by remember { mutableStateOf(UsRhythm.BEGINNER) }
    var legalUnderstood by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Midnight950)
            .testTag("us_onboarding_root")
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 36.dp)
        ) {
            Text(
                text = AppStrings.onboardingStepLabel(language, step + 1, 5),
                fontFamily = CinzelFontFamily,
                fontSize = 12.sp,
                color = TextSecondary,
                modifier = Modifier.testTag("us_onboarding_progress")
            )
            Spacer(modifier = Modifier.height(18.dp))

            when (step) {
                0 -> FeelingStep(
                    language = language,
                    onContinue = { step = 1 },
                    onSkip = { step = 4 }
                )
                1 -> InvitationStep(
                    language = language,
                    onContinue = { step = 2 },
                    onSkip = { step = 4 }
                )
                2 -> VoiceStep(
                    language = language,
                    onContinue = { step = 3 },
                    onSkip = { step = 4 }
                )
                3 -> RhythmStep(
                    language = language,
                    selected = rhythm,
                    onSelect = { rhythm = it },
                    onContinue = { step = 4 },
                    onSkip = { step = 4 }
                )
                else -> LegalStep(
                    language = language,
                    understood = legalUnderstood,
                    onUnderstoodChange = { legalUnderstood = it },
                    onBegin = {
                        val layer = when (rhythm) {
                            UsRhythm.FULL -> TraditionLayer.ON
                            UsRhythm.EXPLORE -> TraditionLayer.GRADUAL
                            UsRhythm.BEGINNER -> TraditionLayer.OFF
                        }
                        onComplete(rhythm, layer)
                    }
                )
            }
        }
    }
}

@Composable
private fun StepButton(
    text: String,
    enabled: Boolean,
    tag: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .testTag(tag),
        colors = ButtonDefaults.buttonColors(
            containerColor = GoldPrimary,
            contentColor = Midnight950,
            disabledContainerColor = Midnight800,
            disabledContentColor = TextSecondary
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}

@Composable
private fun SkipLink(
    text: String,
    tag: String,
    onSkip: () -> Unit
) {
    Text(
        text = text,
        fontSize = 12.sp,
        color = TextSecondary,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onSkip)
            .padding(vertical = 10.dp)
            .testTag(tag)
    )
}

@Composable
private fun FeelingStep(
    language: AppLanguage,
    onContinue: () -> Unit,
    onSkip: () -> Unit
) {
    var selected by remember { mutableStateOf(setOf<Int>()) }

    Column {
        Text(
            text = AppStrings.onboardingFeelingTitle(language),
            fontFamily = CinzelFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = TextGold
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = AppStrings.onboardingFeelingSubtitle(language),
            fontSize = 14.sp,
            color = TextPrimary,
            lineHeight = 20.sp
        )
        Spacer(modifier = Modifier.height(20.dp))

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            (0..4).forEach { index ->
                val isSelected = index in selected
                Text(
                    text = AppStrings.onboardingFeelingOption(language, index),
                    fontSize = 13.5.sp,
                    color = if (isSelected) TextGold else TextPrimary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isSelected) GoldPrimary.copy(alpha = 0.16f) else Midnight800)
                        .border(
                            width = if (isSelected) 1.5.dp else 1.dp,
                            color = if (isSelected) GoldPrimary else GoldBorder,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable {
                            selected = if (isSelected) selected - index else selected + index
                        }
                        .padding(horizontal = 14.dp, vertical = 12.dp)
                        .testTag("us_onboarding_feeling_$index")
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        StepButton(
            text = AppStrings.onboardingContinueBtn(language),
            enabled = selected.isNotEmpty(),
            tag = "us_onboarding_continue",
            onClick = onContinue
        )
        Spacer(modifier = Modifier.height(6.dp))
        SkipLink(
            text = AppStrings.onboardingSkip(language),
            tag = "us_onboarding_skip",
            onSkip = onSkip
        )
    }
}

@Composable
private fun InvitationStep(
    language: AppLanguage,
    onContinue: () -> Unit,
    onSkip: () -> Unit
) {
    Column {
        Text(
            text = AppStrings.onboardingInvitationTitle(language),
            fontFamily = CinzelFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = TextGold
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = AppStrings.onboardingInvitationBody(language),
            fontSize = 14.sp,
            color = TextPrimary,
            lineHeight = 21.sp
        )
        Spacer(modifier = Modifier.height(24.dp))
        StepButton(
            text = AppStrings.onboardingContinueBtn(language),
            enabled = true,
            tag = "us_onboarding_continue",
            onClick = onContinue
        )
        Spacer(modifier = Modifier.height(6.dp))
        SkipLink(
            text = AppStrings.onboardingSkip(language),
            tag = "us_onboarding_skip",
            onSkip = onSkip
        )
    }
}

@Composable
private fun VoiceStep(
    language: AppLanguage,
    onContinue: () -> Unit,
    onSkip: () -> Unit
) {
    Column {
        Text(
            text = AppStrings.onboardingVoiceQuote(language),
            fontSize = 15.sp,
            color = TextPrimary,
            lineHeight = 22.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = AppStrings.onboardingVoiceAuthor(language),
            fontSize = 12.5.sp,
            color = TextGold
        )
        Spacer(modifier = Modifier.height(24.dp))
        StepButton(
            text = AppStrings.onboardingContinueBtn(language),
            enabled = true,
            tag = "us_onboarding_continue",
            onClick = onContinue
        )
        Spacer(modifier = Modifier.height(6.dp))
        SkipLink(
            text = AppStrings.onboardingSkip(language),
            tag = "us_onboarding_skip",
            onSkip = onSkip
        )
    }
}

@Composable
private fun RhythmStep(
    language: AppLanguage,
    selected: UsRhythm,
    onSelect: (UsRhythm) -> Unit,
    onContinue: () -> Unit,
    onSkip: () -> Unit
) {
    Column {
        Text(
            text = AppStrings.onboardingRhythmTitle(language),
            fontFamily = CinzelFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = TextGold
        )
        Spacer(modifier = Modifier.height(18.dp))

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf(
                UsRhythm.BEGINNER to AppStrings.onboardingRhythmBeginner(language),
                UsRhythm.FULL to AppStrings.onboardingRhythmFull(language),
                UsRhythm.EXPLORE to AppStrings.onboardingRhythmExplore(language)
            ).forEach { (option, label) ->
                val isSelected = option == selected
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isSelected) GoldPrimary.copy(alpha = 0.16f) else Midnight800)
                        .border(
                            width = if (isSelected) 1.5.dp else 1.dp,
                            color = if (isSelected) GoldPrimary else GoldBorder,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable { onSelect(option) }
                        .padding(horizontal = 14.dp, vertical = 12.dp)
                        .testTag("us_onboarding_rhythm_${option.key}")
                ) {
                    Box(
                        modifier = Modifier
                            .size(18.dp)
                            .border(2.dp, if (isSelected) GoldPrimary else GoldBorder, CircleShape)
                            .background(
                                if (isSelected) GoldPrimary else Color.Transparent,
                                CircleShape
                            )
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = label,
                        fontSize = 13.sp,
                        color = if (isSelected) TextGold else TextPrimary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = AppStrings.onboardingRhythmCaption(language),
            fontSize = 11.5.sp,
            color = TextSecondary
        )
        Spacer(modifier = Modifier.height(18.dp))
        StepButton(
            text = AppStrings.onboardingContinueBtn(language),
            enabled = true,
            tag = "us_onboarding_continue",
            onClick = onContinue
        )
        Spacer(modifier = Modifier.height(6.dp))
        SkipLink(
            text = AppStrings.onboardingSkip(language),
            tag = "us_onboarding_skip",
            onSkip = onSkip
        )
    }
}

@Composable
private fun LegalStep(
    language: AppLanguage,
    understood: Boolean,
    onUnderstoodChange: (Boolean) -> Unit,
    onBegin: () -> Unit
) {
    Column {
        Text(
            text = AppStrings.onboardingLegalTitle(language),
            fontFamily = CinzelFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = TextGold
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = AppStrings.onboardingLegalBody(language),
            fontSize = 14.sp,
            color = TextPrimary,
            lineHeight = 21.sp
        )
        Spacer(modifier = Modifier.height(18.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Midnight900)
                .border(1.dp, GoldBorder, RoundedCornerShape(12.dp))
                .clickable { onUnderstoodChange(!understood) }
                .padding(horizontal = 8.dp)
                .testTag("us_onboarding_legal_row")
        ) {
            Checkbox(
                checked = understood,
                onCheckedChange = onUnderstoodChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = GoldPrimary,
                    uncheckedColor = TextSecondary
                ),
                modifier = Modifier.testTag("us_onboarding_legal_checkbox")
            )
            Text(
                text = AppStrings.onboardingLegalCheckbox(language),
                fontSize = 14.sp,
                color = TextPrimary
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        StepButton(
            text = AppStrings.onboardingBeginBtn(language),
            enabled = understood,
            tag = "us_onboarding_begin",
            onClick = onBegin
        )
    }
}
