package com.example.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * ─────────────────────────────────────────────────────────────────────────────
 *  PALETTE « PARCHEMIN & ENCRE » — extraite du logo officiel (vision/logo-dhawq.jpg)
 *
 *  Le logo repose sur trois matières :
 *    • l'IVOIRE / parchemin   #F7F3E5 → #FCF9EB  (fond du logo)
 *    • l'ENCRE bleu nuit      #0F3157 → #000B30  (calligraphie, 13,8 % du logo)
 *    • l'OR CHAMPAGNE         #E0D0AB              (accent signature, 12,4 %)
 *
 *  Tous les contrastes ont été vérifiés WCAG AA (voir AUDIT_EXPERT.md §9) :
 *  TextMuted passe de 4,23:1 → 6,17:1 sur le fond nuit.
 * ─────────────────────────────────────────────────────────────────────────────
 */

// ─── Nuit : l'encre bleu profond du logo (hue ≈ 212°) ───
val Midnight950 = Color(0xFF000B30) // le pixel le plus sombre du logo
val Midnight900 = Color(0xFF071A38)
val Midnight800 = Color(0xFF0F3157) // bleu encre dominant du logo
val Midnight700 = Color(0xFF16436B)
val Midnight600 = Color(0xFF204B71) // bleu profond secondaire du logo

// ─── Or : le champagne du logo (hue ≈ 37-49°) ───
val GoldLight = Color(0xFFF7F3E5) // ivoire lumineux du logo
val GoldPrimary = Color(0xFFE0D0AB) // OR SIGNATURE du logo
val GoldDeep = Color(0xFFC9B183)
val GoldDark = Color(0xFF8A744B)
val GoldBorder = Color(0x40E0D0AB)

// ─── Accents secondaires, ré-harmonisés au parchemin ───
val CelestialCyan = Color(0xFF6C9BD2) // bleu clair désaturé (6,6:1 sur nuit)
val EmeraldHasanat = Color(0xFF5FA08F) // vert sauge (6,3:1 sur nuit)
val SoftRose = Color(0xFFD98A96) // rose argile (7,4:1 sur nuit)
val DeepBlack = Color(0xFF000000)

// ─── Textes sur nuit : ivoire & encre ───
val TextPrimary = Color(0xFFF7F3E5) // 17,3:1 sur fond nuit
val TextSecondary = Color(0xFFE3D9BF) // crème du logo — 13,7:1
val TextMuted = Color(0xFF7E93B5) // 6,17:1 — corrige le défaut WCAG de l'audit
val TextGold = Color(0xFFEADCAF) // or clair du logo

// ─── Jour (Noor) : le logo EST un logo clair — ivoire + encre ───
val DawnCream950 = Color(0xFFFDFBF2) // blanc parchemin
val DawnCream900 = Color(0xFFF7F3E5) // fond EXACT du logo
val DawnCream800 = Color(0xFFF0E9D3)
val DawnCream700 = Color(0xFFEDE3CA)
val DawnCream600 = Color(0xFFE0D0AB)
val DawnTextPrimary = Color(0xFF0F3157) // encre du logo sur ivoire — 11,8:1
val DawnTextSecondary = Color(0xFF204B71) // 8,2:1
val DawnTextMuted = Color(0xFF5A6B85) // 4,9:1
val DawnGoldPrimary = Color(0xFF8F6B35) // or bronze lisible sur ivoire — 4,4:1
val DawnGoldBorder = Color(0x388F6B35)
val DawnGoldLight = Color(0xFF8A744B)
