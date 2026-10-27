package com.example.ui.components

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
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.localization.AppStrings
import com.example.data.model.AppLanguage
import com.example.ui.theme.AmiriFontFamily
import com.example.ui.theme.CelestialCyan
import com.example.ui.theme.CinzelFontFamily
import com.example.ui.theme.EmeraldHasanat
import com.example.ui.theme.GoldBorder
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.Midnight700
import com.example.ui.theme.Midnight800
import com.example.ui.theme.Midnight900
import com.example.ui.theme.TextGold
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun LanguageSelectionDialog(
    currentLanguage: AppLanguage,
    onDismiss: () -> Unit,
    onLanguageSelected: (AppLanguage) -> Unit
) {
    val languages = listOf(
        Triple(AppLanguage.AR, AppLanguage.AR.displayName, AppStrings.languageDescription(AppLanguage.AR)),
        Triple(AppLanguage.FR, AppLanguage.FR.displayName, AppStrings.languageDescription(AppLanguage.FR)),
        Triple(AppLanguage.EN, AppLanguage.EN.displayName, AppStrings.languageDescription(AppLanguage.EN)),
        Triple(AppLanguage.ES, AppLanguage.ES.displayName, AppStrings.languageDescription(AppLanguage.ES)),
        Triple(AppLanguage.ID, AppLanguage.ID.displayName, AppStrings.languageDescription(AppLanguage.ID))
    )

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = Midnight900,
            border = androidx.compose.foundation.BorderStroke(1.dp, GoldPrimary),
            modifier = Modifier.fillMaxWidth().testTag("language_selection_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(22.dp)
            ) {
                Text(
                    text = "🌐 ${AppStrings.selectLanguageTitle(currentLanguage)}",
                    fontFamily = CinzelFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = GoldLight,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                languages.forEach { (lang, name, description) ->
                    val isSelected = currentLanguage == lang
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(if (isSelected) GoldPrimary.copy(alpha = 0.15f) else Midnight800)
                            .border(
                                width = if (isSelected) 1.5.dp else 1.dp,
                                color = if (isSelected) GoldPrimary else Midnight700,
                                shape = RoundedCornerShape(14.dp)
                            )
                            .clickable { onLanguageSelected(lang) }
                            .padding(horizontal = 14.dp, vertical = 12.dp)
                            .testTag("lang_option_${lang.code}")
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = lang.flagEmoji, fontSize = 22.sp)
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(
                                        text = name,
                                        fontFamily = if (lang == AppLanguage.AR) AmiriFontFamily else CinzelFontFamily,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        color = if (isSelected) TextGold else TextPrimary
                                    )
                                    Text(
                                        text = description,
                                        fontSize = 11.5.sp,
                                        color = TextSecondary
                                    )
                                }
                            }

                            if (isSelected) {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(CircleShape)
                                        .background(GoldPrimary),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = AppStrings.selectedContentDescription(currentLanguage),
                                        tint = Midnight900,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text(AppStrings.cancelBtn(currentLanguage), color = TextSecondary)
                    }
                }
            }
        }
    }
}
