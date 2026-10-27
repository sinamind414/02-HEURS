# Étude de faisabilité — Ajout « pays/langues » (27/10/2026)

## Demande
Ajouter la liste fournie (~230 entrées + 15 populaires en tête) au sélecteur de langues.

## Constat clé : la liste est des PAYS, pas des langues
Le format (United States, Spain, France… + groupes A–Z) est un **sélecteur de pays** type picker international. Ce sont deux besoins différents dans l'app :

| Besoin | Où | État actuel |
|---|---|---|
| **Langue d'interface** | Pill header EN·FR·AR | 3 langues, enum `AppLanguage` |
| **Pays/ville (horaires de prière)** | `CitySelectionDialog` → API Aladhan | 12 presets en dur + champ libre, **non persisté** |

## Architecture actuelle des langues (mesurée)
- **Aucune string resource Android** (pas de `values-es/`…, `strings.xml` quasi vide) : 100 % des traductions vivent dans le **code Kotlin**.
- `AppStrings.kt` : **2 940 lignes, 356 fonctions, 454 branches `when(lang)`** ≈ **151 textes par langue**.
- + `FatihaScreen` (1 `when(language)`), `JourneyScreen` (2, dont **audio par langue**), `DivineTimeCalculator` (5 signatures avec `lang` : phrases d'impact, libellés), **notifications** (textes par langue).
- Persisté : `UserProfileEntity.languageCode` (« fr »/« ar »/« en »). Rien pour ville/pays (perdu au redémarrage, reload par défaut sur Paris).
- Polices : Amiri (arabe) + Cinzel (latin) uniquement.

## Scénario 1 — Les ~230 entrées comme LANGUES d'UI : ❌ INFAISABLE
- Coût par langue ≈ 151 textes + phrases calculator + textes Fatiha/Voyage + relecture ≈ **1 500–2 000 mots**.
- × 227 nouvelles langues ≈ **35 000 branches `when`** à produire et maintenir ; chaque futur texte × 230.
- Contenu religieux (versets, hadiths) : la traduction de masse sans relecture native est **théologiquement risquée** — rédhibitoire pour une app de ce type.
- Audio du Voyage : n'existe que pour 3 langues.
- Scripts (coréen, hébreu, hindi…) : polices manquantes, RTL/typographie à requalifier par langue.
- **Verdict : rejeté.** Aucun utilisateur n'a besoin de 230 langues d'UI ; il faut les bonnes.

## Scénario 2 — La liste comme sélecteur de PAYS (horaires) : ✅ FAISABLE (recommandé)
- Données pures : 1 fichier `CountryData.kt` (~230 entrées : code ISO, nom, drapeau emoji) — **aucune traduction requise**.
- Réécriture du `CitySelectionDialog` : recherche pays (drapeau + nom) + champ ville, presets conservés.
- Bonus fiabilité : l'API Aladhan accepte **lat/long** (plus précis que city+country pour les petits pays).
- Persistance : ajouter `cityName`/`countryName` à `UserProfileEntity` (migration Room triviale, 2 colonnes défaut `""`) — corrige au passage la perte de ville au redémarrage.
- Effort : **~1–2 h, risque quasi nul, zéro impact linguistique**.

## Scénario 3 — Étendre les langues d'UI 3 → 10-12 majeures : ⚠️ FAISABLE PAR VAGUES
- Les pays listés couvrent des langues majeures : **ES, PT, IT, DE, NL, TR, RU, UK/UR, KO, ID/MS, HI, BN…**
- Coût : 1 langue ≈ 1 itération (traduction + relecture native + tests RTL/polices).
- Pilotes suggérés : **Espagnol** (croissance musulmane hispanophone), **Indonésien** (plus grand pays musulman, listé), ou **Turc**.
- Règle d'or : le pays ne doit **jamais** forcer la langue — toujours un choix manuel.
- Option structurelle : migrer `AppStrings` vers `strings.xml` natifs (outils Play, pluriels) — chantier ~356 fonctions, à ne faire qu'une fois.

## Recommandation
1. **Maintenant** : Scénario 2 — picker de pays avec la liste fournie (vrai besoin, rapide, sûr).
2. **Ensuite** : Scénario 3 par vagues d'1-2 langues relues par un natif.
3. **Jamais** le Scénario 1.

## Tranche livrée entre-temps (27dbce8)
Sélecteur de langue segmenté **EN·FR·AR** dans le header (bascule directe, plus de dialogue) — vérifié sur émulateur : AR→FR→AR, build gate 60 tests / lint 0 erreur.
