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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.calculator.DivineTimeCalculator
import com.example.data.localization.AppStrings
import com.example.data.notification.DivineNotificationHelper
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
import com.example.ui.theme.EmeraldHasanat
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
import java.util.Locale

data class NotificationPreview(
    val moment: String,
    val iconEmoji: String,
    val message: String
)

@Composable
fun SettingsVersesScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val userProfile by viewModel.userProfile.collectAsState()
    val currentLanguage by viewModel.appLanguage.collectAsState()
    val isDayMode by viewModel.isDayMode.collectAsState()
    val divineTime by viewModel.liveDivineTime.collectAsState()

    var testSent by remember { mutableStateOf(false) }

    val backgroundColor = if (isDayMode) DawnCream900 else Midnight950
    val cardColor = if (isDayMode) DawnCream800 else Midnight800
    val textPrimary = if (isDayMode) DawnTextPrimary else TextPrimary
    val textSecondary = if (isDayMode) DawnTextSecondary else TextSecondary
    val textMuted = if (isDayMode) DawnTextMuted else TextMuted
    val accentGold = if (isDayMode) DawnGoldPrimary else GoldPrimary

    val notifications = listOf(
        NotificationPreview(
            moment = AppStrings.notifPreviewWakeMoment(currentLanguage),
            iconEmoji = "🌅",
            message = AppStrings.notifPreviewWakeMessage(currentLanguage)
        ),
        NotificationPreview(
            moment = AppStrings.notifPreviewNoonMoment(currentLanguage),
            iconEmoji = "☀️",
            message = AppStrings.notifPreviewNoonMessage(currentLanguage)
        ),
        NotificationPreview(
            moment = AppStrings.notifPreviewScrollMoment(currentLanguage),
            iconEmoji = "⏳",
            message = AppStrings.notifPreviewScrollMessage(currentLanguage)
        ),
        NotificationPreview(
            moment = AppStrings.notifPreviewBirthdayMoment(currentLanguage),
            iconEmoji = "🎂",
            message = AppStrings.notifPreviewBirthdayMessage(currentLanguage)
        ),
        NotificationPreview(
            moment = AppStrings.notifPreviewFridayMoment(currentLanguage),
            iconEmoji = "🕌",
            message = AppStrings.notifPreviewFridayMessage(currentLanguage)
        )
    )

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
                    text = AppStrings.remindersTitle(currentLanguage),
                    fontFamily = CinzelFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 19.sp,
                    letterSpacing = 1.sp,
                    color = accentGold,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = AppStrings.remindersSubtitle(currentLanguage),
                    fontSize = 12.5.sp,
                    color = textSecondary,
                    textAlign = TextAlign.Center
                )
            }
        }

        // Notification Previews
        item {
            Column(modifier = Modifier.padding(start = 24.dp, top = 12.dp, bottom = 6.dp)) {
                Text(
                    text = AppStrings.spiritualNotificationsTitle(currentLanguage),
                    fontFamily = CinzelFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    letterSpacing = 0.5.sp,
                    color = GoldLight
                )
            }
        }

        items(notifications) { notif ->
            CelestialCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 5.dp),
                backgroundColor = cardColor.copy(alpha = 0.85f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(if (isDayMode) DawnCream900 else Midnight700)
                            .border(1.dp, GoldBorder, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = notif.iconEmoji, fontSize = 20.sp)
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = notif.moment,
                            fontFamily = CinzelFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = CelestialCyan
                        )
                        Text(
                            text = notif.message,
                            fontSize = 12.sp,
                            color = textPrimary,
                            lineHeight = 16.sp
                        )
                    }
                }
            }
        }

        // Real, persisted notification preferences
        item {
            CelestialCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                backgroundColor = cardColor
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = AppStrings.spiritualNotificationsTitle(currentLanguage),
                        fontFamily = CinzelFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.5.sp,
                        color = GoldLight
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    val masterEnabled = userProfile?.notificationsEnabled == true
                    ReminderToggleRow(
                        label = AppStrings.masterNotificationsLabel(currentLanguage),
                        checked = masterEnabled,
                        isDayMode = isDayMode,
                        onCheckedChange = {
                            triggerSpiritualHaptic(context)
                            viewModel.setNotificationsEnabled(it)
                        }
                    )
                    ReminderToggleRow(
                        label = AppStrings.reminderDailyLabel(currentLanguage),
                        checked = userProfile?.reminderSocialScroll == true,
                        enabled = masterEnabled,
                        isDayMode = isDayMode,
                        onCheckedChange = { viewModel.setReminderDaily(it) }
                    )
                    // NOTE (audit P1-3): the Fajr / Dhuhr / Friday toggles were
                    // removed — they were persisted but never read by any
                    // scheduler. Only settings that actually drive the 13:00
                    // reminder are shown. Per-prayer reminders return with the
                    // Dhawq "Rendez-Vous" cycle, backed by real scheduling.
                }
            }
        }

        // Daily Scheduled Divine Reminder Test
        item {
            CelestialCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                backgroundColor = cardColor
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "⏳ " + AppStrings.dailyReminderNotifTitle(currentLanguage).uppercase(),
                        fontFamily = CinzelFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.5.sp,
                        color = GoldLight
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = AppStrings.dailyReminderDescription(currentLanguage),
                        fontSize = 12.sp,
                        color = textSecondary
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = {
                            triggerSpiritualHaptic(context, 40)
                            val totalDivineMin = (divineTime.divineHours * 60.0) +
                                    divineTime.divineMinutes +
                                    (divineTime.divineSeconds / 60.0)
                            DivineNotificationHelper.showNotification(
                                context = context,
                                title = AppStrings.dailyReminderNotifTitle(currentLanguage),
                                message = AppStrings.dailyReminderNotifBody(currentLanguage, totalDivineMin),
                                language = currentLanguage
                            )
                            DivineNotificationHelper.scheduleDailyReminder(
                                context,
                                totalDivineMin,
                                currentLanguage
                            )
                            testSent = true
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (testSent) EmeraldHasanat else accentGold,
                            contentColor = Midnight950
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = if (testSent) {
                                AppStrings.reminderScheduledConfirm(currentLanguage)
                            } else {
                                AppStrings.testReminderBtn(currentLanguage)
                            },
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }

        // Theme Mode Selector Card
        item {
            CelestialCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 6.dp),
                backgroundColor = cardColor
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
                            text = AppStrings.celestialThemeTitle(currentLanguage),
                            fontFamily = CinzelFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = GoldLight
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = if (isDayMode) {
                                AppStrings.themeDayLabel(currentLanguage)
                            } else {
                                AppStrings.themeNightLabel(currentLanguage)
                            },
                            fontSize = 12.sp,
                            color = textSecondary
                        )
                    }

                    Switch(
                        checked = isDayMode,
                        onCheckedChange = {
                            triggerSpiritualHaptic(context)
                            viewModel.toggleThemeMode()
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = accentGold,
                            checkedTrackColor = if (isDayMode) DawnCream800 else Midnight700,
                            uncheckedThumbColor = CelestialCyan,
                            uncheckedTrackColor = Midnight900
                        )
                    )
                }
            }
        }

        // Section: Quranic Verses on Divine Time
        item {
            Column(modifier = Modifier.padding(start = 24.dp, top = 18.dp, bottom = 6.dp)) {
                Text(
                    text = AppStrings.fundamentalVersesTitle(currentLanguage),
                    fontFamily = CinzelFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    letterSpacing = 0.5.sp,
                    color = GoldLight
                )
                Text(
                    text = AppStrings.fundamentalVersesSubtitle(currentLanguage),
                    fontSize = 11.5.sp,
                    color = textMuted
                )
            }
        }

        items(DivineTimeCalculator.QURAN_VERSES) { verse ->
            CelestialCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 6.dp),
                borderColor = GoldBorder,
                backgroundColor = cardColor
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = AppStrings.quranVerseContextTheme(currentLanguage, verse.id).uppercase(),
                        fontFamily = CinzelFontFamily,
                        fontSize = 10.5.sp,
                        letterSpacing = 1.sp,
                        color = CelestialCyan
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = verse.arabic,
                        fontFamily = AmiriFontFamily,
                        fontSize = 19.sp,
                        color = if (isDayMode) DawnGoldPrimary else TextGold,
                        textAlign = TextAlign.End,
                        lineHeight = 28.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = AppStrings.quranVerseTranslation(currentLanguage, verse.id),
                        fontSize = 13.sp,
                        color = textPrimary,
                        lineHeight = 18.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = AppStrings.quranVerseReference(currentLanguage, verse.id),
                        fontFamily = CinzelFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 11.sp,
                        color = GoldLight
                    )
                }
            }
        }

        // About Saatan App
        item {
            CelestialCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 14.dp),
                backgroundColor = if (isDayMode) DawnCream800 else Midnight900
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "سَاعَتَان • SA'ATAN",
                        fontFamily = CinzelFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = accentGold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = AppStrings.lifeIsTwoHoursQuote(currentLanguage),
                        fontSize = 12.5.sp,
                        fontStyle = FontStyle.Italic,
                        color = textSecondary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = AppStrings.hajj47Explanation(currentLanguage),
                        fontSize = 11.5.sp,
                        color = textMuted,
                        textAlign = TextAlign.Center,
                        lineHeight = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun ReminderToggleRow(
    label: String,
    checked: Boolean,
    isDayMode: Boolean,
    enabled: Boolean = true,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 12.5.sp,
            color = when {
                !enabled && isDayMode -> DawnTextMuted
                !enabled -> TextMuted
                isDayMode -> DawnTextPrimary
                else -> TextPrimary
            },
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked = checked,
            enabled = enabled,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = if (isDayMode) DawnGoldPrimary else GoldPrimary,
                checkedTrackColor = if (isDayMode) DawnCream900 else Midnight700,
                uncheckedThumbColor = CelestialCyan,
                uncheckedTrackColor = if (isDayMode) DawnCream900 else Midnight900
            )
        )
    }
}
