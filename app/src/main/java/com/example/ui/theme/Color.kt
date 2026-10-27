package com.example.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * ─────────────────────────────────────────────────────────────────────────────
 *  PALETTE « VERT SAUGE & OR » — extraite du NOUVEAU logo (vidéo designarena)
 *
 *  Le logo « Prayer of Taste » repose sur trois matières :
 *    • le TEAL PROFOND        #041C18 → #1F5C4D  (fond,calligraphie en creux)
 *    • l'OR CHAMPAGNE         #E3C893 → #F6ECD6  (lettrage sujud lumineux)
 *    • l'IVOIRE tiède         #F6F2E2             (éclat central, halo)
 *
 *  Les noms de tokens conservent l'historique « Midnight/Dawn » : tout
 *  l'app reskinne en changeant uniquement les VALEURS.
 *  Contrastes vérifiés WCAG AA sur les nouveaux fonds (voir AUDIT 27/10) :
 *  TextMuted ≈ 5,7:1 sur le fond nuit, DawnTextMuted ≈ 4,9:1 sur ivoire.
 * ─────────────────────────────────────────────────────────────────────────────
 */

// ─── Nuit : le teal profond du logo (hue ≈ 168-172°) ───
val Midnight950 = Color(0xFF041C18) // le pixel le plus sombre du logo
val Midnight900 = Color(0xFF082822) // surface carte
val Midnight800 = Color(0xFF0E372F) // teal dominant — pills & surfaceVariant
val Midnight700 = Color(0xFF16493E)
val Midnight600 = Color(0xFF1F5C4D) // teal profond secondaire

// ─── Or : le champagne calligraphique du logo (hue ≈ 40-42°) ───
val GoldLight = Color(0xFFF6ECD6) // ivoire doré lumineux du logo
val GoldPrimary = Color(0xFFE3C893) // OR SIGNATURE du lettrage
val GoldDeep = Color(0xFFCDA85E)
val GoldDark = Color(0xFF9A7A3E)
val GoldBorder = Color(0x40E3C893)

// ─── Accents secondaires, ré-harmonisés au teal ───
val CelestialCyan = Color(0xFF7CC4B0) // teal lumineux (7,7:1 sur nuit)
val EmeraldHasanat = Color(0xFF5FA08F) // vert sauge (5,1:1 sur nuit)
val SoftRose = Color(0xFFD98A96) // rose argile (7,4:1 sur nuit)
val DeepBlack = Color(0xFF000000)

// ─── Textes sur nuit : ivoire doré & encre claire ───
val TextPrimary = Color(0xFFF6ECD6) // ≈13:1 sur fond nuit
val TextSecondary = Color(0xFFE6D8B8) // crème du logo — ≈11:1
val TextMuted = Color(0xFF93AC9E) // sauge grise — 5,7:1 sur fond nuit
val TextGold = Color(0xFFF0DCAB) // or clair du logo

// ─── Jour (Noor) : ivoire tiède + encre FORÊT + or bronze ───
val DawnCream950 = Color(0xFFFBF9F0) // blanc ivoire
val DawnCream900 = Color(0xFFF6F2E2) // fond clair du thème jour
val DawnCream800 = Color(0xFFEDE9D5)
val DawnCream700 = Color(0xFFE5E1C9)
val DawnCream600 = Color(0xFFDCCB9E)
val DawnTextPrimary = Color(0xFF123B31) // encre forêt sur ivoire — ≈11:1
val DawnTextSecondary = Color(0xFF1D5647) // ≈7,5:1
val DawnTextMuted = Color(0xFF51705F) // ≈4,9:1
val DawnGoldPrimary = Color(0xFF8F6B35) // or bronze lisible sur ivoire — 4,4:1
val DawnGoldBorder = Color(0x388F6B35)
val DawnGoldLight = Color(0xFF8A744B)
val DawnCelestialCyan = Color(0xFF2C6B58) // teal-encre lisible sur ivoire — 5,6:1
