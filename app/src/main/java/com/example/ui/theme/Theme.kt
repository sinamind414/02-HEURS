package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalSaatanThemeIsDay = staticCompositionLocalOf { false }

private val SaatanNightColorScheme = darkColorScheme(
    primary = GoldPrimary,
    onPrimary = Midnight950,
    primaryContainer = Midnight700,
    onPrimaryContainer = GoldLight,
    secondary = CelestialCyan,
    onSecondary = Midnight950,
    secondaryContainer = Midnight600,
    onSecondaryContainer = Color.White,
    tertiary = EmeraldHasanat,
    onTertiary = Midnight950,
    background = Midnight950,
    onBackground = TextPrimary,
    surface = Midnight900,
    onSurface = TextPrimary,
    surfaceVariant = Midnight800,
    onSurfaceVariant = TextSecondary,
    outline = GoldBorder,
    outlineVariant = Midnight600,
    error = SoftRose,
    onError = Color.White
)

private val SaatanDayColorScheme = lightColorScheme(
    primary = DawnGoldPrimary,
    onPrimary = Color.White,
    primaryContainer = DawnCream800,
    onPrimaryContainer = DawnGoldLight,
    secondary = CelestialCyan,
    onSecondary = Color.White,
    secondaryContainer = DawnCream700,
    onSecondaryContainer = DawnTextPrimary,
    tertiary = EmeraldHasanat,
    onTertiary = Color.White,
    background = DawnCream950,
    onBackground = DawnTextPrimary,
    surface = DawnCream900,
    onSurface = DawnTextPrimary,
    surfaceVariant = DawnCream800,
    onSurfaceVariant = DawnTextSecondary,
    outline = DawnGoldBorder,
    outlineVariant = DawnCream700,
    error = SoftRose,
    onError = Color.White
)

@Composable
fun MyApplicationTheme(
    isDayMode: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = if (isDayMode) SaatanDayColorScheme else SaatanNightColorScheme

    CompositionLocalProvider(LocalSaatanThemeIsDay provides isDayMode) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}
