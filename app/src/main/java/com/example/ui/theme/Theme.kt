package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalSaatanThemeIsDay = staticCompositionLocalOf { false }

/**
 * ─────────────────────────────────────────────────────────────────────────────
 *  PALETTE SÉMANTIQUE DES TEXTES (audit 27/10 — finding P1 « contraste jour »)
 *
 *  Les composants qui gardent une « matière » propre (cartes bleu nuit du
 *  logo, cartes dial blanches) ne lisent pas MaterialTheme.onSurface : leurs
 *  ors/crèmes de nuit codés en dur devenaient quasi illisibles sur le
 *  parchemin du mode jour. Cette palette fournit les rôles de texte utilisés
 *  par ces cartes, basculés automatiquement selon LocalSaatanThemeIsDay.
 *
 *  ⚠ Les cartes bleu nuit restées nuit-codées ne changent PAS :
 *  la palette ne doit être utilisée que pour les textes posés sur la
 *  matière claire du jour (ivoire/parchemin/blanc).
 * ─────────────────────────────────────────────────────────────────────────────
 */
data class SaatanTextPalette(
    val secondary: Color, // texte descriptif  (nuit: TextSecondary / jour: DawnTextSecondary)
    val muted: Color,     // légendes & hints (nuit: TextMuted     / jour: DawnTextMuted)
    val accent: Color,    // titres « or lumineux » (nuit: GoldLight / jour: DawnGoldPrimary)
    val gold: Color,      // or décoratif     (nuit: TextGold/Primary / jour: DawnGoldPrimary)
    val cyan: Color       // bleu céleste     (nuit: CelestialCyan  / jour: DawnCelestialCyan)
)

val SaatanNightTextPalette = SaatanTextPalette(
    secondary = TextSecondary,
    muted = TextMuted,
    accent = GoldLight,
    gold = TextGold,
    cyan = CelestialCyan
)

val SaatanDawnTextPalette = SaatanTextPalette(
    secondary = DawnTextSecondary,
    muted = DawnTextMuted,
    accent = DawnGoldPrimary,
    gold = DawnGoldPrimary,
    cyan = DawnCelestialCyan
)

/** Text palette resolved against the active day/night theme. */
@Composable
fun saatanTextPalette(): SaatanTextPalette =
    if (LocalSaatanThemeIsDay.current) SaatanDawnTextPalette else SaatanNightTextPalette

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
