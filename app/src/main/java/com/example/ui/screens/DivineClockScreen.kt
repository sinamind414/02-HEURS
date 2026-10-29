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
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Balance
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Emergency
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.material.icons.filled.Mosque
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.VolunteerActivism
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.zIndex
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.R
import com.example.data.calculator.DivineTimeCalculator
import com.example.data.localization.AppStrings
import com.example.data.model.AppLanguage
import com.example.data.model.CountryData
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
import com.example.ui.components.InnerWeatherCard
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
import com.example.ui.theme.saatanTextPalette
import kotlinx.coroutines.delay
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
    val cycleState by viewModel.cycleState.collectAsState()
    // Presence Rhythm skin: "off" hides the theological chrome of the home
    // screen and renames the five moments (strategy §5).
    val tradition by viewModel.traditionLayer.collectAsState()
    val lightSkin = tradition.isLightSkin
    val textPalette = saatanTextPalette()

    // The app bar + pill rows are hoisted OUTSIDE the LazyColumn (audit P2):
    // this state drives the scrim that keeps them readable once the clock
    // content scrolls underneath.
    val lazyListState = rememberLazyListState()
    val headerOnSurface by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex > 0 ||
                lazyListState.firstVisibleItemScrollOffset > 12
        }
    }

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
        // Sticky-header scrim (audit P2): while the list scrolls, a
        // theme-tinted veil rises behind the fixed header so the pills never
        // superimpose on running text.
        val scrimColor = if (isDayMode) {
            com.example.ui.theme.DawnCream950
        } else {
            Midnight950
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(2f)
                .height(210.dp)
                .background(
                    if (headerOnSurface) {
                        Brush.verticalGradient(
                            0f to scrimColor,
                            0.6f to scrimColor,
                            1f to scrimColor.copy(alpha = 0f)
                        )
                    } else {
                        Brush.verticalGradient(
                            0f to scrimColor.copy(alpha = 0f),
                            1f to scrimColor.copy(alpha = 0f)
                        )
                    }
                )
        )

        // Fixed header (audit P2): the app bar and the two pill rows used to
        // scroll away with the clock content; they are hoisted above the list
        // so the pills stay reachable at any scroll position. zIndex lifts
        // them above the full-screen LazyColumn that would otherwise swallow
        // their taps.
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(3f)
        ) {
            // Top App Bar
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
                                // The new « Prayer of Taste » artwork wears in its
                                // native 768×992 geometry: an upright rounded
                                // rectangle instead of the old circular mask.
                                .width(40.dp)
                                .aspectRatio(768f / 992f)
                                .clip(RoundedCornerShape(12.dp))
                                .border(1.5.dp, GoldPrimary, RoundedCornerShape(12.dp))
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = AppStrings.appTitle(currentLanguage),
                                fontFamily = CinzelFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                letterSpacing = 1.2.sp,
                                color = textPalette.gold
                            )
                            Text(
                                text = AppStrings.appSubtitle(currentLanguage),
                                fontFamily = AmiriFontFamily,
                                fontSize = 12.sp,
                                color = textPalette.secondary
                            )
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Book button — La Prière Goûtée (Sprint 4)
                        IconButton(
                            onClick = {
                                triggerSpiritualHaptic(context)
                                viewModel.openBook()
                            },
                            modifier = Modifier
                                .size(38.dp)
                                .clip(CircleShape)
                                .background(Midnight800)
                                .border(1.dp, GoldBorder, CircleShape)
                                .testTag("book_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.AutoStories,
                                contentDescription = AppStrings.bookOpenLabel(currentLanguage),
                                tint = GoldLight,
                                modifier = Modifier.size(18.dp)
                            )
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

                        // Crisis support — permanent in both skins (988, US).
                        // Nothing about the tap is ever recorded.
                        IconButton(
                            onClick = {
                                triggerSpiritualHaptic(context)
                                viewModel.openCrisisSupport()
                            },
                            modifier = Modifier
                                .size(38.dp)
                                .clip(CircleShape)
                                .background(Midnight800)
                                .border(1.dp, GoldBorder, CircleShape)
                                .testTag("crisis_support_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Emergency,
                                contentDescription = AppStrings.crisisButtonLabel(currentLanguage),
                                tint = GoldPrimary,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }

                // Secondary destinations demoted from the bottom bar (nav
                // trimmed to 5 tabs): the temporal converter and the
                // notifications/verses settings stay one tap away as header
                // pills on the same midnight motif.
                // The language pill lives on this roomier second row: with
                // five languages (EN·FR·AR·ES·ID) it no longer fits beside
                // the app-bar buttons without overflowing in LTR.
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 2.dp)
                ) {
                    // Language Selector Pill — the five languages visible at
                    // a glance; tap a code to switch instantly, no
                    // intermediate dialog needed.
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(Midnight800)
                            .border(1.dp, GoldBorder, RoundedCornerShape(20.dp))
                            .padding(horizontal = 4.dp, vertical = 3.dp)
                            .testTag("language_selector_button"),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            listOf(AppLanguage.EN, AppLanguage.FR, AppLanguage.AR, AppLanguage.ES, AppLanguage.ID).forEach { lang ->
                                val isActive = lang == currentLanguage
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(14.dp))
                                        .background(
                                            if (isActive) GoldPrimary.copy(alpha = 0.28f)
                                            else Color.Transparent
                                        )
                                        .clickable {
                                            triggerSpiritualHaptic(context)
                                            viewModel.setLanguage(lang)
                                        }
                                        .padding(horizontal = 7.dp, vertical = 3.dp)
                                        .testTag("language_chip_${lang.code}")
                                ) {
                                    Text(
                                        text = lang.code.uppercase(),
                                        fontFamily = CinzelFontFamily,
                                        fontWeight = if (isActive) FontWeight.Bold else FontWeight.Medium,
                                        fontSize = 11.sp,
                                        color = if (isActive) TextGold else TextMuted
                                    )
                                }
                            }
                        }
                    }

                    IconButton(
                        onClick = {
                            triggerSpiritualHaptic(context)
                            viewModel.navigateTo(SaatanScreen.CONVERTER)
                        },
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(Midnight800)
                            .border(1.dp, GoldBorder, CircleShape)
                            .testTag("clock_converter_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.SwapVert,
                            contentDescription = AppStrings.navConverter(currentLanguage),
                            tint = GoldLight,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    IconButton(
                        onClick = {
                            triggerSpiritualHaptic(context)
                            viewModel.navigateTo(SaatanScreen.SETTINGS_VERSES)
                        },
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(Midnight800)
                            .border(1.dp, GoldBorder, CircleShape)
                            .testTag("clock_settings_verses_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = AppStrings.navSettings(currentLanguage),
                            tint = GoldLight,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyListState,
            contentPadding = PaddingValues(top = 126.dp, bottom = 96.dp)
        ) {
            // Bismillah Header — hidden on the light Presence Rhythm skin.
            if (!lightSkin) {
                item {
                    BismillahHeader(modifier = Modifier.padding(horizontal = 16.dp))
                }
            }

            // Subtitle Title: TON TEMPS CHEZ ALLAH (original) /
            // TODAY'S RETURNS (light Presence Rhythm skin).
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (lightSkin) {
                            AppStrings.usHomeTitle(currentLanguage)
                        } else {
                            AppStrings.yourTimeWithAllah(currentLanguage)
                        },
                        fontFamily = CinzelFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        letterSpacing = 1.5.sp,
                        color = textPalette.accent,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = if (lightSkin) {
                            AppStrings.usHomeQuote(currentLanguage)
                        } else {
                            AppStrings.centralConceptQuote(currentLanguage)
                        },
                        fontSize = 12.sp,
                        color = textPalette.muted,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }

            // 1. LE GRAND MOTIVEUR DE PRIÈRE — the guilt-free light skin
            // replaces it with the schedule + Inner Weather (slice 2 brings
            // the five return cards).
            if (!lightSkin) {
                item {
                    DivinePrayerMotivatorCard(
                        viewModel = viewModel,
                        prayerState = prayerState,
                        todayJournal = todayJournal,
                        language = currentLanguage,
                        cycleState = cycleState
                    )
                }
            }

            // 2. CIRCULAR CANVAS PROGRESS VISUALIZATION: TWO DIVINE HOURS CLOCK DIAL
            if (!lightSkin) {
                item {
                    TwoDivineHoursClockDial(
                        divineTime = divineTime,
                        language = currentLanguage,
                        lifeExpectancyYears = userProfile?.lifeExpectancyYears ?: 75
                    )
                }
            }

            // 3. TIME BREAKDOWN CANVAS BAR CHART (Spent vs Remaining + Lifetime Allocation)
            if (!lightSkin) {
                item {
                    DivineTimeBreakdownBarChart(
                        divineTime = divineTime,
                        language = currentLanguage
                    )
                }
            }

            // 4. THE CENTRAL DIVINE TIME COUNTER CARD
            if (!lightSkin) {
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
            }

            // 5. ALADHAN API INTEGRATION: PRAYER TIMES & COUNTDOWN NOTIFICATION CARD
            // — kept on both skins: it is the day's schedule, renamed on the
            // light skin ("Next return: Morning Return").
            item {
                PrayerCountdownNotificationCard(
                    prayerState = prayerState,
                    language = currentLanguage,
                    lightSkin = lightSkin,
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

            // 5b. INNER WEATHER — the daily check-in of the light skin.
            if (lightSkin) {
                item {
                    InnerWeatherCard(
                        viewModel = viewModel,
                        journalMoodKey = todayJournal?.moodCheckIn,
                        language = currentLanguage
                    )
                }
            }

            // 5c. EXPLORE THE TRADITION — the always-optional hub, both skins.
            item {
                CelestialCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 8.dp)
                        .testTag("explore_tradition_card"),
                    backgroundColor = Midnight800.copy(alpha = 0.9f),
                    borderColor = GoldPrimary.copy(alpha = 0.4f)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = AppStrings.exploreTitle(currentLanguage),
                            fontFamily = CinzelFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = GoldLight,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = AppStrings.exploreSubtitle(currentLanguage),
                            fontSize = 11.5.sp,
                            color = TextSecondary,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(
                            onClick = {
                                triggerSpiritualHaptic(context)
                                viewModel.navigateTo(SaatanScreen.EXPLORE)
                            },
                            modifier = Modifier.testTag("explore_tradition_button"),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GoldPrimary,
                                contentColor = Midnight950
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = AppStrings.exploreUnlockBtn(currentLanguage),
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.5.sp
                            )
                        }
                    }
                }
            }

            // 6. VISUAL REPRESENTATION: TWO DIVINE HOURS LIFESPAN MAP (Interactive Slider)
            if (!lightSkin) {
                item {
                    TwoDivineHoursLifeMap(
                        userAgeYears = divineTime.earthAgeYears,
                        language = currentLanguage
                    )
                }
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
            if (!lightSkin) {
            item {
                CelestialCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 6.dp)
                        .testTag("earth_vs_divine_stats")
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Balance,
                                contentDescription = null,
                                tint = GoldLight,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = AppStrings.comparativeTitle(currentLanguage),
                                fontFamily = CinzelFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                letterSpacing = 1.sp,
                                color = GoldLight
                            )
                        }
                        Spacer(modifier = Modifier.height(14.dp))

                        // Row Earth
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Public,
                                    contentDescription = null,
                                    tint = CelestialCyan,
                                    modifier = Modifier.size(22.dp)
                                )
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
                                Icon(
                                    imageVector = Icons.Default.Cloud,
                                    contentDescription = null,
                                    tint = TextSecondary,
                                    modifier = Modifier.size(22.dp)
                                )
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
                                Icon(
                                    imageVector = Icons.Default.HourglassBottom,
                                    contentDescription = null,
                                    tint = TextGold,
                                    modifier = Modifier.size(22.dp)
                                )
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
            }

            // Striking Impact Metric Banner: 1 divine second = 4.23 earth days
            if (!lightSkin) {
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
                            Icon(
                                imageVector = Icons.Default.Bolt,
                                contentDescription = null,
                                tint = GoldPrimary,
                                modifier = Modifier.size(26.dp)
                            )
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
            }

            // Spiritual Reflection & Journal Call-to-Action
            if (!lightSkin) {
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
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.VolunteerActivism,
                                contentDescription = null,
                                tint = TextGold,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = AppStrings.minuteDivineQuestion(currentLanguage),
                                fontFamily = CinzelFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.5.sp,
                                letterSpacing = 0.5.sp,
                                color = TextGold,
                                textAlign = TextAlign.Center
                            )
                        }
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

    // ─────────────────────────────────────────────────────────────────────
    //  LE CYCLE QUOTIDIEN (Dhawq)
    // ─────────────────────────────────────────────────────────────────────

    // LE VIDE — while the prayer window is open, the whole screen becomes a
    // single word: "Va." The dismissal is per-prayer (remembered only for the
    // current window): the next Rendez-Vous offers the silence again.
    // Not on the light skin: no "Va." overlay before the tradition is shown.
    val videKey = cycleState.windowPrayerKey
    var dismissedVideKey by remember { mutableStateOf<String?>(null) }
    if (cycleState.inPrayerWindow && videKey.isNotEmpty() && dismissedVideKey != videKey && !lightSkin) {
        LeVideOverlay(
            language = currentLanguage,
            prayerFr = cycleState.windowPrayerNameFr,
            prayerAr = cycleState.windowPrayerNameAr,
            onLater = { dismissedVideKey = videKey }
        )
    }

    // LA MINUTE PRÉCIEUSE — offered once, 10 to 45 minutes after the adhan
    // (the assise that follows the salam). Sixty silent seconds, then the
    // question of the taste. Tradition skin only.
    var showPreciousMinute by remember { mutableStateOf(false) }
    LaunchedEffect(cycleState.offerPreciousMinute, cycleState.preciousMinutePrayerKey) {
        if (cycleState.offerPreciousMinute && !lightSkin) {
            showPreciousMinute = true
            viewModel.markPreciousMinuteShown(cycleState.preciousMinutePrayerKey)
        }
    }
    if (showPreciousMinute) {
        PreciousMinuteDialog(
            language = currentLanguage,
            onSelectTaste = { taste ->
                viewModel.recordSalahTaste(taste)
                showPreciousMinute = false
            },
            onDismiss = { showPreciousMinute = false }
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
    lightSkin: Boolean = false,
    onOpenCityDialog: () -> Unit,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Light skin renames the five moments and hides the Hijri date, the
    // Arabic name and the "divine waiting" line — the schedule itself is
    // kept: it is what the rhythm is built on.
    fun momentName(key: String): String =
        if (lightSkin) {
            AppStrings.lightReturnName(language, key)
        } else {
            AppStrings.prayerName(language, key)
        }

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
                        text = "${prayerState.cityName}, ${prayerState.countryName}",
                        fontFamily = CinzelFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = CelestialCyan
                    )
                    // Trailing affordance: the row opens the city dialog.
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = CelestialCyan.copy(alpha = 0.75f),
                        modifier = Modifier
                            .padding(start = 3.dp)
                            .size(13.dp)
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (!lightSkin) {
                        Text(
                            text = prayerState.hijriDateFormatted,
                            fontFamily = AmiriFontFamily,
                            fontSize = 13.sp,
                            color = TextGold
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }
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
                                text = "${if (lightSkin) AppStrings.usNextReturnLabel(language) else AppStrings.nextPrayerLabel(language)} : ${momentName(prayerState.nextPrayerNameFr).uppercase()}",
                                fontFamily = CinzelFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.5.sp,
                                letterSpacing = 0.8.sp,
                                color = GoldLight
                            )
                            if (!lightSkin) {
                                Text(
                                    text = prayerState.nextPrayerNameAr,
                                    fontFamily = AmiriFontFamily,
                                    fontSize = 16.sp,
                                    color = TextGold
                                )
                            }
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

                        if (!lightSkin) {
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
                                text = momentName(prayer.key),
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
                        text = AppStrings.ageYearsLabel(language, divineTime.earthYearsInt),
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
    var selectedCountry by remember { mutableStateOf(CountryData.findByName(currentCountry)) }
    var showCountryList by remember { mutableStateOf(false) }
    var countrySearch by remember { mutableStateOf("") }

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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Mosque,
                        contentDescription = null,
                        tint = GoldLight,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = AppStrings.cityPrayerTitle(language),
                        fontFamily = CinzelFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = GoldLight,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = AppStrings.cityPrayerSubtitle(language),
                    fontSize = 11.5.sp,
                    color = TextSecondary,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(14.dp))

                if (showCountryList) {
                    // ── Mode recherche pays (~224 entrées, populaires en tête) ──
                    OutlinedTextField(
                        value = countrySearch,
                        onValueChange = { countrySearch = it },
                        label = { Text(AppStrings.countryFieldLabel(language), fontSize = 11.sp) },
                        singleLine = true,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Public,
                                contentDescription = null,
                                tint = GoldLight,
                                modifier = Modifier.size(16.dp)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("country_search_field"),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = GoldPrimary,
                            unfocusedBorderColor = Midnight700,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    val countryResults = remember(countrySearch) {
                        CountryData.search(countrySearch)
                    }
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 300.dp)
                            .testTag("country_list")
                    ) {
                        items(countryResults) { country ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(
                                        if (country.code == selectedCountry?.code) GoldPrimary.copy(alpha = 0.18f)
                                        else Color.Transparent
                                    )
                                    .clickable {
                                        selectedCountry = country
                                        showCountryList = false
                                    }
                                    .padding(horizontal = 8.dp, vertical = 7.dp)
                                    .testTag("country_option_${country.code}"),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = country.flagEmoji, fontSize = 16.sp)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = country.name,
                                    fontSize = 12.sp,
                                    color = TextPrimary,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(text = country.code, fontSize = 10.sp, color = TextMuted)
                            }
                        }
                    }
                } else {
                    // ── Mode ville + pays ──
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

                    // Country selector chip → opens the searchable country list.
                    // Replaces the old free-text field: typos like "Maroc" vs
                    // "Morocco" silently broke the Aladhan fetch.
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Midnight800)
                            .border(1.dp, GoldBorder, RoundedCornerShape(12.dp))
                            .clickable { showCountryList = true }
                            .padding(horizontal = 12.dp, vertical = 12.dp)
                            .testTag("country_picker_field")
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = selectedCountry?.flagEmoji ?: "🌐",
                                fontSize = 16.sp
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = selectedCountry?.name
                                    ?: AppStrings.countryFieldLabel(language),
                                fontSize = 12.sp,
                                color = if (selectedCountry != null) TextPrimary else TextMuted,
                                modifier = Modifier.weight(1f)
                            )
                            Text(text = "▾", fontSize = 12.sp, color = GoldPrimary)
                        }
                    }

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
                                        selectedCountry = CountryData.findByName(preset.second)
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
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text(AppStrings.cancelBtn(language), color = TextSecondary)
                    }
                    if (!showCountryList) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                val chosen = selectedCountry
                                if (cityInput.isNotBlank() && chosen != null) {
                                    onCitySelected(cityInput.trim(), chosen.name)
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
    // The upper bound must follow the device clock: a fixed "2024" already
    // prevented registering children born in 2025+ (audit P0-1).
    val currentYear = remember { Calendar.getInstance().get(Calendar.YEAR) }
    var selectedYear by remember {
        mutableIntStateOf(cal.get(Calendar.YEAR).coerceIn(1940, currentYear))
    }
    var selectedMonth by remember { mutableIntStateOf(cal.get(Calendar.MONTH) + 1) }
    var selectedDay by remember { mutableIntStateOf(cal.get(Calendar.DAY_OF_MONTH)) }
    var expectancy by remember { mutableFloatStateOf(currentExpectancy.toFloat()) }

    // A day slider needs a valid upper bound for the chosen month, and the
    // stored day must be re-clamped whenever year/month change — otherwise
    // Calendar silently rolls "31 February" into March (audit P0-1).
    val maxDayInMonth = daysInMonthOf(selectedYear, selectedMonth)
    LaunchedEffect(selectedYear, selectedMonth) {
        if (selectedDay > maxDayInMonth) selectedDay = maxDayInMonth
    }

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
                    valueRange = 1940f..currentYear.toFloat(),
                    steps = (currentYear - 1940 - 1).coerceAtLeast(0),
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

                Text(
                    text = AppStrings.birthDayValueLabel(language, selectedDay),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )
                Slider(
                    value = selectedDay.toFloat(),
                    onValueChange = { selectedDay = it.toInt() },
                    valueRange = 1f..maxDayInMonth.toFloat(),
                    steps = (maxDayInMonth - 2).coerceAtLeast(0),
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
                            // Defensive clamp: never let an impossible date
                            // (e.g. 31 February) reach Calendar.
                            val safeDay = selectedDay.coerceIn(1, daysInMonthOf(selectedYear, selectedMonth))
                            onSave(selectedYear, selectedMonth, safeDay, expectancy.toInt())
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

/**
 * Number of days in the given (year, month) — Calendar-based so it stays
 * correct on minSdk 24 (no java.time without desugaring). Used to bound the
 * birth-day slider and to prevent Calendar's silent day-overflow rollover.
 */
private fun daysInMonthOf(year: Int, month: Int): Int {
    val probe = Calendar.getInstance()
    probe.set(Calendar.YEAR, year)
    probe.set(Calendar.MONTH, month - 1)
    probe.set(Calendar.DAY_OF_MONTH, 1)
    return probe.getActualMaximum(Calendar.DAY_OF_MONTH)
}

/**
 * LE VIDE — the anti-feature at the heart of Dhawq (Serment #4).
 *
 * While the real prayer window is open, opening the app shows one word on
 * ink-black: "Va." No data, no clock, no cards. The app steps aside during
 * the prayer and waits. A discreet "Later" lets the user back in without
 * turning the silence into a prison.
 */
@Composable
private fun LeVideOverlay(
    language: AppLanguage,
    prayerFr: String,
    prayerAr: String,
    onLater: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Midnight950)
            .testTag("le_vide_overlay"),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = AppStrings.leVideWord(language),
                fontFamily = AmiriFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 64.sp,
                color = GoldPrimary
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = AppStrings.leVideSubtitle(language, prayerFr, prayerAr),
                fontSize = 13.sp,
                color = TextSecondary,
                textAlign = TextAlign.Center,
                lineHeight = 19.sp,
                modifier = Modifier.padding(horizontal = 44.dp)
            )
            Spacer(modifier = Modifier.height(44.dp))
            TextButton(onClick = onLater) {
                Text(
                    text = AppStrings.leVideLater(language),
                    fontSize = 12.sp,
                    color = TextMuted
                )
            }
        }
    }
}

/**
 * LA MINUTE PRÉCIEUSE — the assise that follows the salam (الجلوس الثمين).
 *
 * Sixty seconds, black and mute: no sound, no score, nothing to achieve.
 * When the minute is over the question of the taste appears — one tap, and
 * the dialog closes. Skipping is always allowed, quietly.
 */
@Composable
private fun PreciousMinuteDialog(
    language: AppLanguage,
    onSelectTaste: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var secondsLeft by remember { mutableIntStateOf(60) }

    LaunchedEffect(Unit) {
        while (secondsLeft > 0) {
            delay(1000)
            secondsLeft--
        }
    }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(28.dp),
            color = Midnight950,
            border = androidx.compose.foundation.BorderStroke(1.dp, GoldBorder),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = AppStrings.preciousMinuteTitle(language),
                    fontFamily = CinzelFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = GoldLight,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = AppStrings.preciousMinuteBody(language),
                    fontFamily = AmiriFontFamily,
                    fontSize = 13.sp,
                    color = TextSecondary,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(26.dp))

                if (secondsLeft > 0) {
                    // The countdown: a number and a thin gold thread that
                    // shortens. Deliberately no ring, no animation flourish.
                    Text(
                        text = "$secondsLeft",
                        fontFamily = AmiriFontFamily,
                        fontSize = 44.sp,
                        color = GoldPrimary
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(3.dp)
                            .background(Midnight800)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(secondsLeft / 60f)
                                .height(3.dp)
                                .background(GoldPrimary)
                        )
                    }
                    Spacer(modifier = Modifier.height(18.dp))
                    TextButton(onClick = onDismiss) {
                        Text(
                            text = AppStrings.preciousMinuteSkip(language),
                            fontSize = 11.sp,
                            color = TextMuted
                        )
                    }
                } else {
                    // The minute is complete: "Amine." then the question.
                    Text(
                        text = AppStrings.preciousMinuteDone(language),
                        fontFamily = AmiriFontFamily,
                        fontSize = 26.sp,
                        color = GoldPrimary
                    )
                    Spacer(modifier = Modifier.height(22.dp))
                    Text(
                        text = AppStrings.tasteQuestion(language),
                        fontFamily = AmiriFontFamily,
                        fontSize = 13.5.sp,
                        color = TextPrimary,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        PreciousTasteOption(
                            label = AppStrings.tasteDouce(language),
                            onClick = { onSelectTaste("douce") },
                            modifier = Modifier.weight(1f)
                        )
                        PreciousTasteOption(
                            label = AppStrings.tasteLourde(language),
                            onClick = { onSelectTaste("lourde") },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        PreciousTasteOption(
                            label = AppStrings.tasteAbsente(language),
                            onClick = { onSelectTaste("absente") },
                            modifier = Modifier.weight(1f)
                        )
                        PreciousTasteOption(
                            label = AppStrings.tasteGoutee(language),
                            onClick = { onSelectTaste("goutee") },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

/** One of the four taste answers inside the Precious Minute dialog. */
@Composable
private fun PreciousTasteOption(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(Midnight800)
            .border(1.dp, GoldBorder, RoundedCornerShape(50))
            .clickable { onClick() }
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .testTag("precious_taste_option"),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            maxLines = 1,
            color = TextSecondary
        )
    }
}
