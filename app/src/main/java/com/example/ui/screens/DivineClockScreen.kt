package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.R
import com.example.data.calculator.DivineTimeCalculator
import com.example.data.localization.AppStrings
import com.example.data.model.AppLanguage
import com.example.data.model.DivineTimeInfo
import com.example.data.model.PrayerCountdownState
import com.example.data.model.PrayerTimeItem
import com.example.ui.MainViewModel
import com.example.ui.SaatanScreen
import com.example.ui.components.BismillahHeader
import com.example.ui.components.CelestialCard
import com.example.ui.components.DivineCircularClock
import com.example.ui.components.DivinePrayerMotivatorCard
import com.example.ui.components.DivineTimeBreakdownBarChart
import com.example.ui.components.IslamicGoldenDivider
import com.example.ui.components.LanguageSelectionDialog
import com.example.ui.components.TwoDivineHoursClockDial
import com.example.ui.components.TwoDivineHoursLifeMap
import com.example.ui.components.triggerSpiritualHaptic
import com.example.ui.theme.AmiriFontFamily
import com.example.ui.theme.CelestialCyan
import com.example.ui.theme.CinzelFontFamily
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
import com.example.ui.theme.TextGold
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import java.util.Calendar
import java.util.Locale

@Composable
fun DivineClockScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val divineTime by viewModel.liveDivineTime.collectAsState()
    val showProfileDialog by viewModel.showProfileDialog.collectAsState()
    val userProfile by viewModel.userProfile.collectAsState()
    val prayerState by viewModel.prayerState.collectAsState()
    val showCityDialog by viewModel.showCityDialog.collectAsState()
    val currentLanguage by viewModel.appLanguage.collectAsState()
    val showLanguageDialog by viewModel.showLanguageDialog.collectAsState()
    val isDayMode by viewModel.isDayMode.collectAsState()
    val todayJournal by viewModel.todayJournal.collectAsState()

    val bgGradient = if (isDayMode) {
        Brush.verticalGradient(
            colors = listOf(
                com.example.ui.theme.DawnCream950.copy(alpha = 0.85f),
                com.example.ui.theme.DawnCream950
            )
        )
    } else {
        Brush.verticalGradient(
            colors = listOf(
                Midnight950.copy(alpha = 0.45f),
                Midnight950.copy(alpha = 0.92f),
                Midnight950
            )
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(if (isDayMode) com.example.ui.theme.DawnCream950 else Midnight950)
            .testTag("main_screen_container")
    ) {
        // Subtle decorative background header
        Image(
            painter = painterResource(id = R.drawable.divine_cosmos_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
        )
        // Dark/Dawn gradient overlay for readability
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .background(bgGradient)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 96.dp)
        ) {
            // Top App Bar
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.saatan_icon),
                            contentDescription = AppStrings.logoContentDescription(currentLanguage),
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .border(1.5.dp, GoldPrimary, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = AppStrings.appTitle(currentLanguage),
                                fontFamily = CinzelFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                letterSpacing = 1.2.sp,
                                color = GoldPrimary
                            )
                            Text(
                                text = AppStrings.appSubtitle(currentLanguage),
                                fontFamily = AmiriFontFamily,
                                fontSize = 12.sp,
                                color = TextSecondary
                            )
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Language Selector Pill
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(Midnight800)
                                .border(1.dp, GoldBorder, RoundedCornerShape(20.dp))
                                .clickable {
                                    triggerSpiritualHaptic(context)
                                    viewModel.openLanguageDialog()
                                }
                                .padding(horizontal = 9.dp, vertical = 5.dp)
                                .testTag("language_selector_button"),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = currentLanguage.flagEmoji, fontSize = 12.sp)
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = currentLanguage.code.uppercase(),
                                    fontFamily = CinzelFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp,
                                    color = GoldLight
                                )
                            }
                        }

                        // Day / Night Theme Selector Toggle
                        IconButton(
                            onClick = {
                                triggerSpiritualHaptic(context, 25)
                                viewModel.toggleThemeMode()
                            },
                            modifier = Modifier
                                .size(38.dp)
                                .clip(CircleShape)
                                .background(Midnight800)
                                .border(1.dp, GoldBorder, CircleShape)
                                .testTag("theme_mode_toggle")
                        ) {
                            Icon(
                                imageVector = if (isDayMode) Icons.Default.DarkMode else Icons.Default.LightMode,
                                contentDescription = if (isDayMode) {
                                    AppStrings.nightModeContentDescription(currentLanguage)
                                } else {
                                    AppStrings.dayModeContentDescription(currentLanguage)
                                },
                                tint = if (isDayMode) CelestialCyan else GoldPrimary,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        // Settings button
                        IconButton(
                            onClick = {
                                triggerSpiritualHaptic(context)
                                viewModel.openProfileDialog()
                            },
                            modifier = Modifier
                                .size(38.dp)
                                .clip(CircleShape)
                                .background(Midnight800)
                                .border(1.dp, GoldBorder, CircleShape)
                                .testTag("settings_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = AppStrings.birthSettingsContentDescription(currentLanguage),
                                tint = GoldLight,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }

            // Bismillah Header
            item {
                BismillahHeader(modifier = Modifier.padding(horizontal = 16.dp))
            }

            // Subtitle Title: TON TEMPS CHEZ ALLAH
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = AppStrings.yourTimeWithAllah(currentLanguage),
                        fontFamily = CinzelFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        letterSpacing = 1.5.sp,
                        color = GoldLight,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = AppStrings.centralConceptQuote(currentLanguage),
                        fontSize = 12.sp,
                        color = TextMuted,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }

            // 1. LE GRAND MOTIVEUR DE PRIÈRE (Ultimate Prayer Motivator)
            item {
                DivinePrayerMotivatorCard(
                    viewModel = viewModel,
                    prayerState = prayerState,
                    todayJournal = todayJournal,
                    language = currentLanguage
                )
            }

            // 2. CIRCULAR CANVAS PROGRESS VISUALIZATION: TWO DIVINE HOURS CLOCK DIAL
            item {
                TwoDivineHoursClockDial(
                    divineTime = divineTime,
                    language = currentLanguage
                )
            }

            // 3. TIME BREAKDOWN CANVAS BAR CHART (Spent vs Remaining + Lifetime Allocation)
            item {
                DivineTimeBreakdownBarChart(
                    divineTime = divineTime,
                    language = currentLanguage
                )
            }

            // 4. THE CENTRAL DIVINE TIME COUNTER CARD
            item {
                DivineTimeMainCounterCard(
                    divineTime = divineTime,
                    language = currentLanguage,
                    onEditBirthdate = {
                        triggerSpiritualHaptic(context)
                        viewModel.openProfileDialog()
                    }
                )
            }

            // 5. ALADHAN API INTEGRATION: PRAYER TIMES & COUNTDOWN NOTIFICATION CARD
            item {
                PrayerCountdownNotificationCard(
                    prayerState = prayerState,
                    language = currentLanguage,
                    onOpenCityDialog = {
                        triggerSpiritualHaptic(context)
                        viewModel.openCityDialog()
                    },
                    onRefresh = {
                        triggerSpiritualHaptic(context)
                        viewModel.loadPrayerTimes(prayerState.cityName, prayerState.countryName)
                    }
                )
            }

            // 6. VISUAL REPRESENTATION: TWO DIVINE HOURS LIFESPAN MAP (Interactive Slider)
            item {
                TwoDivineHoursLifeMap(
                    userAgeYears = divineTime.earthAgeYears,
                    language = currentLanguage
                )
            }

            // Circular Visualizer Dial
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    DivineCircularClock(
                        percentageLived = divineTime.percentageLived,
                        elapsedDivineTimeStr = divineTime.formattedDivineTime,
                        totalDivineLifeStr = divineTime.formattedTotalDivineLife,
                        language = currentLanguage,
                        sizeDp = 220.dp
                    )
                }
            }

            // Earth Time vs Divine Time Comparative Breakdown
            item {
                CelestialCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 6.dp)
                        .testTag("earth_vs_divine_stats")
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Text(
                            text = "⚖️ " + AppStrings.comparativeTitle(currentLanguage),
                            fontFamily = CinzelFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            letterSpacing = 1.sp,
                            color = GoldLight
                        )
                        Spacer(modifier = Modifier.height(14.dp))

                        // Row Earth
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "🌍", fontSize = 20.sp)
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(text = AppStrings.onEarth(currentLanguage), fontSize = 12.sp, color = TextSecondary)
                                    Text(
                                        text = AppStrings.earthAgeSummary(
                                            currentLanguage,
                                            divineTime.earthYearsInt,
                                            divineTime.earthMonthsInt,
                                            divineTime.earthDaysLived
                                        ),
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = TextPrimary
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(Midnight700)
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        // Row Divine Lived
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "☁️", fontSize = 20.sp)
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(text = AppStrings.withAllahLived(currentLanguage), fontSize = 12.sp, color = TextSecondary)
                                    Text(
                                        text = AppStrings.divineElapsedLabel(
                                            currentLanguage,
                                            divineTime.divineHours,
                                            divineTime.divineMinutes,
                                            divineTime.divineSeconds
                                        ),
                                        fontFamily = CinzelFontFamily,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = TextGold
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(Midnight700)
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        // Row Divine Remaining
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "⏳", fontSize = 20.sp)
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(text = AppStrings.withAllahRemaining(currentLanguage), fontSize = 12.sp, color = TextSecondary)
                                    Text(
                                        text = AppStrings.divineRemainingLabel(
                                            currentLanguage,
                                            divineTime.remainingDivineHours,
                                            divineTime.remainingDivineMinutes,
                                            divineTime.remainingDivineSeconds
                                        ),
                                        fontFamily = CinzelFontFamily,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = CelestialCyan
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Striking Impact Metric Banner: 1 divine second = 4.23 earth days
            item {
                CelestialCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 6.dp)
                        .testTag("impact_quote_card"),
                    backgroundColor = Midnight800.copy(alpha = 0.95f),
                    borderColor = GoldPrimary.copy(alpha = 0.5f)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(GoldPrimary.copy(alpha = 0.15f))
                                .border(1.dp, GoldPrimary, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "⚡", fontSize = 24.sp)
                        }
                        Spacer(modifier = Modifier.width(14.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = AppStrings.impactBannerTitle(currentLanguage),
                                fontFamily = CinzelFontFamily,
                                fontSize = 11.sp,
                                letterSpacing = 1.sp,
                                color = GoldLight
                            )
                            Text(
                                text = AppStrings.impactBannerText(currentLanguage),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )
                            Text(
                                text = AppStrings.impactBannerDesc(currentLanguage),
                                fontSize = 11.5.sp,
                                color = TextSecondary
                            )
                        }
                    }
                }
            }

            // Spiritual Reflection & Journal Call-to-Action
            item {
                CelestialCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    backgroundColor = Midnight700.copy(alpha = 0.85f),
                    borderColor = GoldPrimary.copy(alpha = 0.6f)
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "🤲 " + AppStrings.minuteDivineQuestion(currentLanguage),
                            fontFamily = CinzelFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.5.sp,
                            letterSpacing = 0.5.sp,
                            color = TextGold,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = divineTime.impactPhrase,
                            fontSize = 13.sp,
                            color = TextPrimary,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        Spacer(modifier = Modifier.height(14.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Button(
                                onClick = {
                                    triggerSpiritualHaptic(context)
                                    viewModel.navigateTo(SaatanScreen.JOURNAL)
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("minute_divine_button"),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = GoldPrimary,
                                    contentColor = Midnight950
                                ),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(
                                    text = AppStrings.openJournalBtn(currentLanguage),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.5.sp
                                )
                            }

                            OutlinedButton(
                                onClick = {
                                    triggerSpiritualHaptic(context)
                                    viewModel.navigateTo(SaatanScreen.IMMERSIVE_LIFE)
                                },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = GoldLight
                                ),
                                border = androidx.compose.foundation.BorderStroke(1.dp, GoldPrimary),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = AppStrings.watchIn2MinBtn(currentLanguage),
                                    fontSize = 12.5.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    // Profile Settings Dialog (Birthdate & Expectancy)
    if (showProfileDialog) {
        ProfileEditDialog(
            currentBirthMs = userProfile?.birthTimestampMs ?: 803260800000L,
            currentExpectancy = userProfile?.lifeExpectancyYears ?: 75,
            language = currentLanguage,
            onDismiss = { viewModel.closeProfileDialog() },
            onSave = { year, month, day, expectancy ->
                viewModel.updateProfile(year, month, day, expectancy)
            }
        )
    }

    // City Selection Dialog for Aladhan API
    if (showCityDialog) {
        CitySelectionDialog(
            currentCity = prayerState.cityName,
            currentCountry = prayerState.countryName,
            language = currentLanguage,
            onDismiss = { viewModel.closeCityDialog() },
            onCitySelected = { city, country ->
                triggerSpiritualHaptic(context)
                viewModel.selectCity(city, country)
            }
        )
    }

    // Language Selection Dialog (AR, EN, FR)
    if (showLanguageDialog) {
        LanguageSelectionDialog(
            currentLanguage = currentLanguage,
            onDismiss = { viewModel.closeLanguageDialog() },
            onLanguageSelected = { lang ->
                triggerSpiritualHaptic(context)
                viewModel.setLanguage(lang)
            }
        )
    }
}

/**
 * Aladhan API Prayer Times & Countdown Notification Card
 */
@Composable
fun PrayerCountdownNotificationCard(
    prayerState: PrayerCountdownState,
    language: AppLanguage = AppLanguage.FR,
    onOpenCityDialog: () -> Unit,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    CelestialCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .testTag("prayer_countdown_card"),
        borderColor = CelestialCyan.copy(alpha = 0.7f),
        backgroundColor = Midnight900.copy(alpha = 0.95f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Header: City and Hijri Date
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { onOpenCityDialog() }
                        .padding(horizontal = 4.dp, vertical = 2.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = AppStrings.cityContentDescription(language),
                        tint = CelestialCyan,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${prayerState.cityName}, ${prayerState.countryName} ✎",
                        fontFamily = CinzelFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = CelestialCyan
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = prayerState.hijriDateFormatted,
                        fontFamily = AmiriFontFamily,
                        fontSize = 13.sp,
                        color = TextGold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    IconButton(
                        onClick = onRefresh,
                        modifier = Modifier.size(28.dp)
                    ) {
                        if (prayerState.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(14.dp),
                                color = GoldPrimary,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = AppStrings.refreshContentDescription(language),
                                tint = TextSecondary,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Main Countdown Notification Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(Midnight800, Midnight700)
                        )
                    )
                    .border(1.dp, GoldBorder, RoundedCornerShape(16.dp))
                    .padding(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(GoldPrimary.copy(alpha = 0.15f))
                            .border(1.dp, GoldPrimary, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = prayerState.nextPrayerEmoji, fontSize = 24.sp)
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "${AppStrings.nextPrayerLabel(language)} : ${AppStrings.prayerName(language, prayerState.nextPrayerNameFr).uppercase()}",
                                fontFamily = CinzelFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.5.sp,
                                letterSpacing = 0.8.sp,
                                color = GoldLight
                            )
                            Text(
                                text = prayerState.nextPrayerNameAr,
                                fontFamily = AmiriFontFamily,
                                fontSize = 16.sp,
                                color = TextGold
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.Bottom,
                            modifier = Modifier.padding(vertical = 2.dp)
                        ) {
                            Text(
                                text = AppStrings.countdownLabel(language, prayerState.formattedCountdown),
                                fontFamily = CinzelFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = TextPrimary
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "(${prayerState.nextPrayerTimeStr})",
                                fontSize = 12.sp,
                                color = CelestialCyan
                            )
                        }

                        Text(
                            text = AppStrings.divineWaitingLabel(
                                language,
                                DivineTimeCalculator.earthToDivineFormatted(
                                    seconds = prayerState.secondsUntilNextPrayer.toDouble(),
                                    lang = language
                                )
                            ),
                            fontFamily = CinzelFontFamily,
                            fontSize = 11.sp,
                            color = GoldLight
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Horizontal strip of all prayer timings from Aladhan
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(prayerState.prayersList) { prayer ->
                    val isNext = prayer.isNext
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isNext) GoldPrimary.copy(alpha = 0.15f) else Midnight800)
                            .border(
                                width = if (isNext) 1.5.dp else 1.dp,
                                color = if (isNext) GoldPrimary else Midnight700,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = prayer.iconEmoji,
                                fontSize = 14.sp
                            )
                            Text(
                                text = AppStrings.prayerName(language, prayer.key),
                                fontFamily = CinzelFontFamily,
                                fontWeight = if (isNext) FontWeight.Bold else FontWeight.Medium,
                                fontSize = 11.sp,
                                color = if (isNext) TextGold else TextSecondary
                            )
                            Text(
                                text = prayer.timeStr,
                                fontFamily = CinzelFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = if (isNext) GoldLight else TextPrimary
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * The Central Iconic Box for the Divine Clock Screen:
 * ┌───────────────────────┐
 * │  00:47:23 / 01:41:00  │
 * │  ████████████░░░░░░░  │
 * │       46.7% vécu      │
 * └───────────────────────┘
 */
@Composable
fun DivineTimeMainCounterCard(
    divineTime: DivineTimeInfo,
    language: AppLanguage = AppLanguage.FR,
    onEditBirthdate: () -> Unit,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "shimmer")
    val shimmerAlpha by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alpha"
    )

    CelestialCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .testTag("divine_counter_card"),
        borderColor = GoldPrimary,
        backgroundColor = Midnight900.copy(alpha = 0.95f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Live Divine Time Reading
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = divineTime.formattedDivineTime,
                    fontFamily = CinzelFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    color = TextGold,
                    modifier = Modifier.testTag("divine_time_text")
                )
                Text(
                    text = " / ",
                    fontFamily = CinzelFontFamily,
                    fontSize = 20.sp,
                    color = TextMuted
                )
                Text(
                    text = divineTime.formattedTotalDivineLife,
                    fontFamily = CinzelFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    color = TextSecondary
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Glowing Golden Progress Bar: ████████████░░░░░░░
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Midnight700)
                    .border(1.dp, GoldBorder, RoundedCornerShape(10.dp))
                    .testTag("divine_progress_bar")
            ) {
                // Active fill with golden gradient and pulse
                val progressFraction = (divineTime.percentageLived / 100f).coerceIn(0f, 1f)
                Box(
                    modifier = Modifier
                        .fillMaxWidth(fraction = progressFraction)
                        .height(16.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    GoldDark,
                                    GoldPrimary,
                                    GoldLight.copy(alpha = shimmerAlpha)
                                )
                            )
                        )
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Percentage lived badge: 46.7% vécu
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .background(GoldPrimary.copy(alpha = 0.15f))
                        .border(1.dp, GoldPrimary.copy(alpha = 0.5f), RoundedCornerShape(14.dp))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                        .testTag("percentage_lived_badge")
                ) {
                    Text(
                        text = AppStrings.livedBadge(language, divineTime.percentageLived),
                        fontFamily = CinzelFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = GoldLight
                    )
                }

                // Quick edit birth date trigger
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { onEditBirthdate() }
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .testTag("edit_birthdate_button"),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = AppStrings.editContentDescription(language),
                        tint = CelestialCyan,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = AppStrings.ageYearsLabel(language, divineTime.earthYearsInt) + " ✎",
                        fontSize = 12.sp,
                        color = CelestialCyan
                    )
                }
            }
        }
    }
}

/**
 * City Selection Dialog for Aladhan API
 */
@Composable
fun CitySelectionDialog(
    currentCity: String,
    currentCountry: String,
    language: AppLanguage = AppLanguage.FR,
    onDismiss: () -> Unit,
    onCitySelected: (city: String, country: String) -> Unit
) {
    var cityInput by remember { mutableStateOf(currentCity) }
    var countryInput by remember { mutableStateOf(currentCountry) }

    val presetCities = listOf(
        Pair("Paris", "France"),
        Pair("Casablanca", "Morocco"),
        Pair("Alger", "Algeria"),
        Pair("Tunis", "Tunisia"),
        Pair("Makkah", "Saudi Arabia"),
        Pair("Madinah", "Saudi Arabia"),
        Pair("Cairo", "Egypt"),
        Pair("Dubai", "United Arab Emirates"),
        Pair("London", "United Kingdom"),
        Pair("New York", "United States"),
        Pair("Montreal", "Canada"),
        Pair("Istanbul", "Turkey")
    )

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = Midnight900,
            border = androidx.compose.foundation.BorderStroke(1.dp, GoldPrimary),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(22.dp)
            ) {
                Text(
                    text = "🕌 " + AppStrings.cityPrayerTitle(language),
                    fontFamily = CinzelFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = GoldLight,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = AppStrings.cityPrayerSubtitle(language),
                    fontSize = 11.5.sp,
                    color = TextSecondary,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Custom Inputs
                OutlinedTextField(
                    value = cityInput,
                    onValueChange = { cityInput = it },
                    label = { Text(AppStrings.cityFieldLabel(language), fontSize = 11.sp) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = GoldPrimary,
                        unfocusedBorderColor = Midnight700,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = countryInput,
                    onValueChange = { countryInput = it },
                    label = { Text(AppStrings.countryFieldLabel(language), fontSize = 11.sp) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = GoldPrimary,
                        unfocusedBorderColor = Midnight700,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = AppStrings.fastCities(language),
                    fontSize = 11.5.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextGold
                )

                Spacer(modifier = Modifier.height(6.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(presetCities) { preset ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(Midnight800)
                                .border(1.dp, GoldBorder, RoundedCornerShape(10.dp))
                                .clickable {
                                    cityInput = preset.first
                                    countryInput = preset.second
                                    onCitySelected(preset.first, preset.second)
                                }
                                .padding(horizontal = 8.dp, vertical = 5.dp)
                        ) {
                            Text(
                                text = preset.first,
                                fontSize = 11.sp,
                                color = TextPrimary
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text(AppStrings.cancelBtn(language), color = TextSecondary)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            if (cityInput.isNotBlank() && countryInput.isNotBlank()) {
                                onCitySelected(cityInput.trim(), countryInput.trim())
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GoldPrimary,
                            contentColor = Midnight950
                        )
                    ) {
                        Text(AppStrings.applyBtn(language), fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileEditDialog(
    currentBirthMs: Long,
    currentExpectancy: Int,
    language: AppLanguage = AppLanguage.FR,
    onDismiss: () -> Unit,
    onSave: (year: Int, month: Int, day: Int, expectancy: Int) -> Unit
) {
    val cal = remember {
        Calendar.getInstance().apply { timeInMillis = currentBirthMs }
    }
    var selectedYear by remember { mutableIntStateOf(cal.get(Calendar.YEAR)) }
    var selectedMonth by remember { mutableIntStateOf(cal.get(Calendar.MONTH) + 1) }
    var selectedDay by remember { mutableIntStateOf(cal.get(Calendar.DAY_OF_MONTH)) }
    var expectancy by remember { mutableFloatStateOf(currentExpectancy.toFloat()) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = Midnight900,
            border = androidx.compose.foundation.BorderStroke(1.dp, GoldPrimary),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(22.dp)
            ) {
                Text(
                    text = AppStrings.birthDateDialogTitle(language),
                    fontFamily = CinzelFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = GoldLight,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = AppStrings.birthDateDialogSubtitle(language),
                    fontSize = 12.sp,
                    color = TextSecondary,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = AppStrings.birthYearValueLabel(language, selectedYear),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )
                Slider(
                    value = selectedYear.toFloat(),
                    onValueChange = { selectedYear = it.toInt() },
                    valueRange = 1940f..2024f,
                    colors = SliderDefaults.colors(
                        thumbColor = GoldPrimary,
                        activeTrackColor = GoldPrimary,
                        inactiveTrackColor = Midnight700
                    )
                )

                Text(
                    text = AppStrings.birthMonthValueLabel(language, selectedMonth),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )
                Slider(
                    value = selectedMonth.toFloat(),
                    onValueChange = { selectedMonth = it.toInt() },
                    valueRange = 1f..12f,
                    steps = 10,
                    colors = SliderDefaults.colors(
                        thumbColor = CelestialCyan,
                        activeTrackColor = CelestialCyan,
                        inactiveTrackColor = Midnight700
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = AppStrings.lifeExpectancyValueLabel(language, expectancy.toInt()),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )
                Text(
                    text = AppStrings.prophetLifeSpanQuote(language),
                    fontSize = 11.sp,
                    color = TextMuted,
                    lineHeight = 14.sp
                )
                Slider(
                    value = expectancy,
                    onValueChange = { expectancy = it },
                    valueRange = 60f..95f,
                    colors = SliderDefaults.colors(
                        thumbColor = EmeraldHasanat,
                        activeTrackColor = EmeraldHasanat,
                        inactiveTrackColor = Midnight700
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text(AppStrings.cancelBtn(language), color = TextSecondary)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            onSave(selectedYear, selectedMonth, selectedDay, expectancy.toInt())
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GoldPrimary,
                            contentColor = Midnight950
                        )
                    ) {
                        Text(AppStrings.saveBtn(language), fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
