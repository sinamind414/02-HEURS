package com.example

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.ui.MainViewModel
import com.example.ui.SaatanScreen
import com.example.ui.components.triggerSpiritualHaptic
import com.example.ui.screens.ChallengesScreen
import com.example.ui.screens.DivineClockScreen
import com.example.ui.screens.DivineJournalScreen
import com.example.ui.screens.JourneyScreen
import com.example.ui.screens.LastNightsScreen
import com.example.ui.screens.LifeJourneyVisualizerScreen
import com.example.ui.screens.SettingsVersesScreen
import com.example.ui.screens.TemporalConverterScreen
import com.example.ui.theme.CinzelFontFamily
import com.example.ui.theme.DawnCream900
import com.example.ui.theme.DawnGoldPrimary
import com.example.ui.theme.GoldBorder
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.Midnight700
import com.example.ui.theme.Midnight900
import com.example.ui.theme.Midnight950
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.TextGold
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextSecondary

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.example.data.localization.AppStrings
import com.example.data.model.AppLanguage

data class NavItem(
    val screen: SaatanScreen,
    val label: String,
    val icon: ImageVector,
    val tag: String
)

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isDayMode by viewModel.isDayMode.collectAsState()
            MyApplicationTheme(isDayMode = isDayMode) {
                SaatanAppContent(viewModel)
            }
        }
    }
}

@Composable
fun SaatanAppContent(viewModel: MainViewModel) {
    val context = LocalContext.current
    val currentScreen by viewModel.currentScreen.collectAsState()
    val currentLanguage by viewModel.appLanguage.collectAsState()
    val isDayMode by viewModel.isDayMode.collectAsState()

    // Handle system back button: return to main divine clock screen
    BackHandler(enabled = currentScreen != SaatanScreen.DIVINE_CLOCK) {
        viewModel.navigateTo(SaatanScreen.DIVINE_CLOCK)
    }

    // Android 13+ requires an explicit runtime grant for notifications.
    // Without it `NotificationManager.notify()` throws a SecurityException that
    // is swallowed, so the app would silently never show a reminder.
    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { /* the reminder scheduler re-arms itself from the stored preferences */ }

    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val granted = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
            if (!granted) {
                notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    val navItems = listOf(
        NavItem(SaatanScreen.DIVINE_CLOCK, AppStrings.navClock(currentLanguage), Icons.Default.HourglassBottom, "nav_clock"),
        NavItem(SaatanScreen.JOURNEY, AppStrings.navJourney(currentLanguage), Icons.Default.Spa, "nav_journey"),
        NavItem(SaatanScreen.CONVERTER, AppStrings.navConverter(currentLanguage), Icons.Default.SwapVert, "nav_converter"),
        NavItem(SaatanScreen.JOURNAL, AppStrings.navJournal(currentLanguage), Icons.AutoMirrored.Filled.MenuBook, "nav_journal"),
        NavItem(SaatanScreen.IMMERSIVE_LIFE, AppStrings.navVisualizer(currentLanguage), Icons.Default.PlayCircle, "nav_visualizer"),
        NavItem(SaatanScreen.CHALLENGES, AppStrings.navChallenges(currentLanguage), Icons.Default.EmojiEvents, "nav_challenges"),
        NavItem(SaatanScreen.SETTINGS_VERSES, AppStrings.navSettings(currentLanguage), Icons.Default.Notifications, "nav_settings")
    )

    val backgroundColor = if (isDayMode) DawnCream900 else Midnight950
    val navBarColor = if (isDayMode) DawnCream900 else Midnight900
    val unselectedIconColor = if (isDayMode) DawnGoldPrimary else TextSecondary
    val unselectedTextColor = if (isDayMode) DawnGoldPrimary else TextMuted

    CompositionLocalProvider(
        LocalLayoutDirection provides (if (currentLanguage.isRtl) LayoutDirection.Rtl else LayoutDirection.Ltr)
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor),
            contentWindowInsets = WindowInsets.safeDrawing,
            bottomBar = {
                // NOTE: Material3's NavigationBar already applies
                // `NavigationBarDefaults.windowInsets` (which includes the
                // navigation bar inset). Applying `windowInsetsPadding` on top
                // of it would pad the bar twice, so it is intentionally not
                // used here. The height is also left to the Material default
                // instead of being squeezed to 68.dp.
                NavigationBar(
                    containerColor = navBarColor,
                    tonalElevation = 8.dp,
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = GoldBorder
                        )
                        .testTag("main_navigation_bar")
                ) {
                    navItems.forEach { item ->
                        val isSelected = currentScreen == item.screen
                        NavigationBarItem(
                            selected = isSelected,
                            onClick = {
                                triggerSpiritualHaptic(context, 20)
                                viewModel.navigateTo(item.screen)
                            },
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.label,
                                    modifier = Modifier.size(22.dp)
                                )
                            },
                            label = {
                                Text(
                                    text = item.label,
                                    fontFamily = CinzelFontFamily,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    fontSize = 10.sp,
                                    maxLines = 1
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Midnight950,
                                selectedTextColor = TextGold,
                                indicatorColor = GoldPrimary,
                                unselectedIconColor = unselectedIconColor,
                                unselectedTextColor = unselectedTextColor
                            ),
                            modifier = Modifier.testTag(item.tag)
                        )
                    }
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Crossfade(
                    targetState = currentScreen,
                    animationSpec = tween(250),
                    label = "screen_transition"
                ) { screen ->
                    when (screen) {
                        SaatanScreen.DIVINE_CLOCK -> DivineClockScreen(viewModel)
                        SaatanScreen.JOURNEY -> JourneyScreen(viewModel)
                        SaatanScreen.LAST_NIGHTS -> LastNightsScreen(viewModel)
                        SaatanScreen.CONVERTER -> TemporalConverterScreen(viewModel)
                        SaatanScreen.JOURNAL -> DivineJournalScreen(viewModel)
                        SaatanScreen.IMMERSIVE_LIFE -> LifeJourneyVisualizerScreen(viewModel)
                        SaatanScreen.CHALLENGES -> ChallengesScreen(viewModel)
                        SaatanScreen.SETTINGS_VERSES -> SettingsVersesScreen(viewModel)
                    }
                }
            }
        }
    }
}
