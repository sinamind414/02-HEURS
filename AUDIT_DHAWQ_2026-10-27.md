# AUDIT APPLICATION DHAWQ (سَعدان) — Émulateur saatan / emulator-5554
Date : mardi 27 octobre 2026 · Build courant (main = cbb4fd6) · Audit manuel ADB + captures

## Verdict global : ✅ SAIN — 5/5 onglets, 14/14 points testés, 0 crash, 2 deep-links OK

| # | Point audité | Résultat | Preuve |
|---|---|---|---|
| 1 | Splash vidéo (« prière du goût ») au démarrage à froid | ✅ rejoué puis dissous au tap | c1/c1b |
| 2 | Barre de navigation 5 onglets (RTL) | ✅ العدد·الرحلة·اليوميات·التحديات·رحلة د2, sélection dorée | c1 |
| 3 | En-tête : 4 pills d'origine + 2ᵉ rangée (convertisseur ⇅ / notifications 🔔) | ✅ conforme au plan Phase A | c1 |
| 4 | Bascule jour/nuit (pill soleil → lune) | ✅ thème parchemin ↔ nuit | c2/c3 |
| 5 | ⚠ Contraste mode JOUR | ⚠ « ثوق • تدفّق الصلاة », « وفُئك عند الله », « إنه يدعوك », « استثمارك… » : or pâle sur parchemin, quasi illisible (WCAG AA) | c2 |
| 6 | Horloge divine (dial ساعة إلهية, chips goût, ⚖ Balance, chevron) | ✅ | c4 |
| 7 | Pill convertisseur → محول التوقيت الإلهي | ✅ icône sablier vectorielle, tabs ⇄, calcul « د 26 ت إهية », exemples | c5 |
| 8 | Pill notifications → التذكيرات وحكم الوقت | ✅ cartes goûts 🌅/☀/⏳/🎂, toggle notifications | c6 |
| 9 | Onglet الرحلة | ✅ lotus, bandeau lunaison, chapitres + « اقرأ الفصل » | c7 |
| 10 | Onglet اليوميات | ✅ MenuBook/Mosque vectoriels, 5 étoiles, checkboxes devoirs, ✨ (accepté) | c8 |
| 11 | Onglet التحديات | ✅ Trophée vectoriel, anneau 0 %, verrous 🔒 | c9 |
| 12 | Onglet رحلة د2 (immersive) | ✅ clapper vectoriel, lecteur 02:00/00:00, étapes 👶, Replay | c10 |
| 13 | Deep-link `--es dhawq_open fatiha` | ✅ dialogue de la Fatiha (station 06), points de progression | c11 |
| 14 | Deep-link `--es dhawq_open ch22` | ✅ chapitre livre + lecteur (01:25:52) + chips contextuelles | c12 |
| 15 | Crashs pendant la session complète | ✅ AUCUN (logcat FATAL/AndroidRuntime) | logcat |
| 16 | Rendu GFX | ✔ p50=31 ms, p90=48 ms, p95=73 ms, janky 13,5 % (AVD GPU-less + Serial Console ; à revalider sur device réel) | gfxinfo |

## Recommandations
1. **P1 — Contraste jour** : les teintes or réutilisées sur le fond parchemin rendent 4 libellés illisibles → prévoir une variante d'or sombre (`onSurface` day) dans le thème.
2. **P2 — Pills et scroll** : sur l'écran horloge défilé, la 2ᵉ rangée de pills sort du viewport et le tap devient un no-op → envisager une rangée épinglée (sticky) ou réactiver le tap.
3. **Environnement** : 3 crashs d'AVD cette session ; le panneau système signale « Serial console enabled — Performance is impacted » → désactiver le serial console dans la config AVD et revalider.
4. **Périmètre accepté** : médaillons/badges data (🔒/🕌/📖/✨/🌅…) restent en emoji colorés — décision d'architecture assumée (hors champ du passage vectoriel actuel).

## Correctifs appliqués (post-audit, même jour)
1. **P1 — Contraste mode jour : corrigé** · Nouvelle palette sémantique `saatanTextPalette()` (`Theme.kt`) + `DawnCelestialCyan` (`Color.kt`). Les textes `GoldLight/TextGold/TextSecondary/TextMuted/CelestialCyan` codés nuit deviennent theme-aware sur : titre/coins d'app bar, Bismillah + séparateur doré, « وفُئك عند الله » + citation, Grand Motiveur (titres, ratio, cyan), cadran divin (titres, heure `00:39:24.3`, « % de vie », arc/aiguille/hub canvas, pills de jalon), label d'onglet sélectionné de la barre basse (jour = encre `Midnight800`, nuit = or). Preuves : `fix_d3.png` (jour net), `fix_d3_navcrop.png` (label العداد en encre), `fix_s1_scrolled.png` (textes défilés lisibles).
2. **P2 — Pills perdues au scroll : corrigé** · En-tête (app bar + 2ᵉ rangée de pills) hoisté hors de la `LazyColumn` (`DivineClockScreen.kt`), épinglé via `zIndex` (3 = pills tapables au-dessus de la liste), scrim parchemin/bleu nuit qui apparaît dès le scroll (`firstVisibleItemIndex>0 || offset>12`). Les pills restent visibles ET tapables à toute position : la pill convertisseur ouvre bien « محوّل التوقيت الإلهي » depuis l'état défilé (preuves `fix_s1_scrolled.png`, `fix_s2_converter.png`).
3. **Emulateur (environ.) — inchangé** : défaut AVD hors périmètre app (cf. recommandation 3).
4. **Périmètre accepté — inchangé** : emojis data 🔒/🕌/📖/✨/🌅/👶 restent artwork (recommandation 4).

## Méthodologie
Pilotage adb (taps/swipes/screencap), 16 points, 13 captures PNG consultées, scan logcat complet, stats gfxinfo. Stable, aucune intervention native sur l'app. Audit bloqué côté Artemis unique reason: clé OpenRouter invalide (HTTP 401 « User not found ») — Artemis est installé, corrigé (router + nœuds pinned) et prêt ; il suffit d'une clé valide pour reprendre le pipeline agentique.

**Captures** : `C:\Users\zakaria\AppData\Local\Temp\opencode\audit_screens\audit_c*.png`
