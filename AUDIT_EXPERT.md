# Audit expert — Application **Sa'atan** (ساعتان)

**Date de l'audit :** 24 octobre 2026
**Périmètre :** `sinamind414/02-HEURS`, branche `arena/3746622a-02-heurs`, HEAD `a359bb5`
**Méthode :** revue statique exhaustive du code source (11 045 lignes Kotlin de production + 374 lignes de tests), vérification numérique indépendante des formules du domaine, audit automatisé du catalogue i18n, analyse des ressources, du graphe Gradle et de la configuration de release.

> **Note de contexte :** le sandbox d'audit ne dispose ni de JDK ni d'accès aux dépôts Maven/Google. Aucune compilation ni exécution de test n'a donc pu être lancée. Toutes les conclusions ci-dessous sont issues d'une lecture ligne par ligne et de recalculs numériques indépendants (les formules ont été réimplémentées en Python et comparées aux constantes du code).

---

## 1. Verdict exécutif

| Axe | Note | Appréciation |
|---|:---:|---|
| **Justesse du domaine (mathématique du temps divin)** | 9/10 | Excellent — recalculé et validé à 100 % |
| **Sincérité théologique / cohérence du récit** | 8/10 | Solide, quelques raccourcis à surveiller |
| **Internationalisation (FR/AR/EN)** | 9/10 | Remarquable pour un projet solo |
| **Qualité des tests du domaine** | 8/10 | Vrais tests, bons cas limites |
| **Architecture & séparation des couches** | 6/10 | Propre sur le papier, plusieurs fuites |
| **Correction fonctionnelle (bugs)** | 5/10 | 4 bugs bloquants/majeurs confirmés |
| **Performance & recomposition** | 4/10 | Gaspillage structurel sur l'écran principal |
| **Accessibilité** | 3/10 | 0 déclaration sémantique dans tout le projet |
| **Préparation à la publication (Play Store)** | 3/10 | Identité technique non finalisée |
| **Finition / dette technique** | 6/10 | ~1,5 Mo de ressources mortes, code mort |

**Note globale pondérée : 6,4 / 10**

**En une phrase :** *Sa'atan* est une application à l'idée forte et au cœur mathématique **irréprochable**, portée par une internationalisation d'une qualité rare — mais elle souffre de **quatre défauts fonctionnels réels** (édition de date de naissance cassée, horaires de prière qui ne se rafraîchissent jamais, rappels qui meurent au redémarrage, réglages de notification factices) et d'un **gaspillage de recomposition** sur son écran principal, qui trahissent une génération/itération rapide non suivie d'une passe de finition.

---

## 2. Compréhension du domaine

L'application repose sur un seul axiome, extrait de **Sourate Al-Hajj (22:47)** :

> *« Et certes, un jour auprès de ton Seigneur équivaut à mille ans de ce que vous comptez. »*
> $$\text{1 000 années terrestres} = \text{24 heures divines} = \text{86 400 secondes divines}$$

Tout le reste en découle. J'ai réimplémenté la totalité des formules en Python pour les confronter aux constantes du code.

### 2.1 Vérification numérique — **tout est correct**

| Grandeur | Formule | Valeur attendue | Valeur dans le code | Statut |
|---|---|:---:|:---:|:---:|
| 1 an terrestre | $24\times3600/1000$ | 86,4 s divines | 86,4 | ✅ |
| 1 jour terrestre | $86{,}4/365{,}2425$ | 0,236555 s divines | 0,23655517 | ✅ |
| 1 heure terrestre | $0{,}236555/24$ | 0,0098565 s divines | 0,00985647 | ✅ |
| 1 minute terrestre | $/60$ | 0,00016427 s divines | 0,00016427 | ✅ |
| 1 seconde divine (inverse) | $1000\times365{,}2425/86400$ | 4,2273 jours terrestres | 4,22734375 | ✅ |
| 1 heure divine | $1000/24$ | 41,67 ans terrestres | 41,666… | ✅ |
| **2 heures divines (vie entière)** | $1000/12$ | **83,33 ans** | 83,333… | ✅ |
| Prière de 5 min | $5\times0{,}00016427$ | 0,000821 s divines | 0,000821 | ✅ |
| Budget prière sur 80 ans | $80\times365{,}2425\times25\times0{,}00016427/60$ | 2,00 min divines | 2,00 | ✅ |

**Points particulièrement bien traités :**
- L'année moyenne grégorienne/julienne **365,2425 j** est utilisée (et non 365 ou 365,25) — c'est le bon choix, cohérent avec le calendrier grégorien sur 400 ans.
- Le commentaire du code documente explicitement l'**erreur historique corrigée** : l'application affichait jadis « 1 seconde divine = 11,57 jours terrestres », qui était $1000/86400$ mal interprété. La valeur réelle 4,2273 jours est désormais dérivée d'une seule constante (`EARTH_DAYS_PER_DIVINE_SECOND`, `DivineTimeCalculator.kt:55`). C'est exactement le bon réflexe et cela mérite d'être salué.
- Les cas dégénératifs sont **tous gardés** : espérance de vie à 0 (→ 0 %, pas `NaN`), date de naissance dans le futur (→ clampé à 0), durées négatives dans les convertisseurs (→ clampées), dépassement des 120 minutes divines (`twoHourMappingNeverOverflows`).
- La constante `DAILY_PRAYER_EARTH_MINUTES = 25.0` (5 prières × 5 min) est **une seule source de vérité** partagée par le graphique en barres, la carte « choc » et les défis. C'est la bonne architecture : ces trois vues ne peuvent plus se contredire.

### 2.2 Réserves d'ordre théologique (à traiter avec prudence)

Trois points où l'application **affirme plus que ce que le texte source ne dit**. Ce n'est pas un bug de code, mais c'est un risque pour une application à vocation religieuse :

1. **« Ta vie entière = ~2 heures chez Allah »** — le verset parle d'**un jour** (24 h) égal à 1 000 ans, pas d'une vie. Ramener une vie humaine à « 2 heures » est une **inférence arithmétique** (83,33 ans ≈ 1000/12), pas un énoncé coranique. Elle est présentée comme une citation sourcée « (Al-Hajj:47) » (`AppStrings.centralConceptQuote`). Recommandation forte : requalifier en « *projection spirituelle inspirée de* Al-Hajj:47 » plutôt qu'en citation.
2. **Le hadîth des 60–70 ans** (espérance de vie de la communauté) est la base implicite du curseur 60–95 ans. Il n'est jamais cité, alors que le verset l'est. Asymétrie de sourçage.
3. **Attribution des versets** : les références affichées (`stageReference`, `quranVerseReference`) doivent être relues par une personne de science (*'ilm*). Une erreur de numérotation de sourate/verset dans une application de ce type est le défaut le plus coûteux en crédibilité. Le code ne permet pas de le vérifier (pas de *sanad* ni d'édition du Coran référencée).

---

## 3. Architecture

```
MainActivity (Compose, edge-to-edge, RTL)
   └── MainViewModel (AndroidViewModel) ─────────┬── SaatanRepository ── DailyJournalDao ── Room (v2)
                                                  └── PrayerTimesRepository ── Retrofit/Moshi ── api.aladhan.com
Domaine pur : DivineTimeCalculator (object, 0 dépendance Android)
i18n        : AppStrings (object, 265 fonctions, 361 triplets FR/AR/EN)
UI          : 6 écrans + 7 composants Canvas
```

**Ce qui est bien fait :**
- `DivineTimeCalculator` est un `object` **sans aucune dépendance Android** → le domaine est compilable en JVM pur et testable. C'est la décision architecturale la plus importante du projet, et elle est la bonne.
- `PrayerTimesRepository` fait un travail **rarement bien fait** : il compare l'heure courante à l'heure locale **de la ville** (`cityCalendar()`), pas à celle du téléphone, et gère le repli vers un offset GMT quand la timezone IANA est absente. La plupart des applications de prière se trompent sur ce point.
- Le fallback hors-ligne (`buildFallbackState`) **n'écrase pas le cache réel** — commentaire explicite dans le code (`PrayerTimesRepository.kt:161`). Excellent.
- `MainViewModel.challenges` est un `StateFlow` dérivé d'un `combine(journal, profile, language)` : la liste des défis se recompose automatiquement. Le commentaire (`MainViewModel.kt:40`) indique que c'était un bug corrigé.

**Ce qui pose problème :**

| Problème | Localisation | Impact |
|---|---|---|
| `MainViewModel` = **669 lignes**, 11 responsabilités (horloge, prière, simulateur, convertisseur, défis, thème, notifications, langue…) | `MainViewModel.kt` | Fichier god-object ; impossible à tester unitairement |
| **Logique métier dans l'UI** : `earthAgeEquiv`, `segmentRest`, `divinePerTap`, `totalMinutesSpan` calculés dans les composables | `LifeJourneyVisualizerScreen.kt:96`, `DivineTimeBreakdownBarChart.kt:79`, `DivineJournalScreen.kt:437`, `TwoDivineHoursClockDial.kt:97` | Duplique/diverge du calculateur central |
| **230 références de thème totalement qualifiées** (`com.example.ui.theme.Midnight900`) au lieu d'imports | partout | Signe de génération automatique ; lisibilité dégradée |
| Pas de couche `UseCase`, pas de DI (Hilt/Koin) | — | Le ViewModel instancie `PrayerTimesRepository()` directement (`MainViewModel.kt:37`) → **intestable** (impossible de mocker l'API) |
| `SaatanDatabase.getDatabase()` appelé depuis un `BroadcastReceiver` avec son propre `CoroutineScope` | `DivineNotificationHelper.kt:24` | Double source de vérité pour l'accès DB ; pas de gestion d'erreur Room |

---

## 4. Bugs confirmés

### 🔴 P0 — Bloquant

#### P0-1. La date de naissance ne peut pas être correctement saisie

`app/src/main/java/com/example/ui/screens/DivineClockScreen.kt:1240-1355`

Le dialogue de profil contient **un slider pour l'année, un slider pour le mois, un slider pour l'espérance de vie — mais aucun pour le jour.**

```kotlin
var selectedDay by remember { mutableIntStateOf(cal.get(Calendar.DAY_OF_MONTH)) }   // :1247
...
Slider(value = selectedYear.toFloat(), ..., valueRange = 1940f..2024f)  // :1289
Slider(value = selectedMonth.toFloat(), ..., valueRange = 1f..12f)      // :1305
// ← aucun slider pour selectedDay
onSave(selectedYear, selectedMonth, selectedDay, ...)                   // :1351
```

**Trois conséquences :**
1. **Le jour est figé** sur celui de la date enregistrée. Un utilisateur né le 15 ne pourra jamais saisir le 3.
2. **Risque de basculement silencieux** : `Calendar` est *lénifiant*. Si le jour mémorisé est 31 et que l'utilisateur sélectionne février, `Calendar` roule au 3 mars — la date de naissance devient fausse **sans aucun avertissement**.
3. **Année maximale 2024** : un enfant né en 2025 ou 2026 ne peut pas être enregistré (nous sommes en octobre 2026).

> **Correction :** ajouter un slider « jour » dont le `valueRange` est recalculé dynamiquement (`1f..daysInMonth(selectedYear, selectedMonth)`), porter la borne haute à l'année courante, et valider que la date résultante n'est pas dans le futur.

---

### 🟠 P1 — Majeur

#### P1-1. Les horaires de prière ne se rafraîchissent jamais au changement de jour

`app/src/main/java/com/example/data/repository/PrayerTimesRepository.kt:115`

```kotlin
fun isCacheStale(): Boolean {
    val cached = cachedDateIso ?: return cachedTimings == null
    return cached != todayIsoIn(cachedTimezone)
}
```

**Cette fonction n'est appelée nulle part.** Vérifié par recherche exhaustive dans `app/src` : une seule occurrence, sa déclaration.

Conséquence : les horaires sont téléchargés **une seule fois** au démarrage (`MainViewModel.kt:197`), puis le ticker (`MainViewModel.kt:222-232`) se contente de recalculer le compte à rebours **sur le cache de la veille**. Un utilisateur qui laisse l'application ouverte (ou en arrière-plan) verra, à partir de minuit, les horaires d'hier et un compte à rebours qui remonte à 23 h 59 au lieu d'afficher le Fajr du jour.

> **Correction :** dans `startPrayerCountdownTicker()`, appeler `if (prayerRepository.isCacheStale()) loadPrayerTimes(...)` avant de recalculer.

#### P1-2. Les rappels ne survivent pas à un redémarrage du téléphone

`app/src/main/AndroidManifest.xml` — aucun `<receiver>` pour `android.intent.action.BOOT_COMPLETED`, et la permission `RECEIVE_BOOT_COMPLETED` n'est pas déclarée.

L'alarme est posée par `AlarmManager.setAndAllowWhileIdle(...)`, qui est **effacée au redémarrage**. Le seul réarmement se produit quand l'utilisateur ouvre l'application (via `syncDailyReminder`, `MainViewModel.kt:192`). Un utilisateur qui redémarre son téléphone et n'ouvre pas l'application **ne recevra plus jamais aucun rappel** — sans que rien ne l'indique.

> **Correction :** ajouter un `BootReceiver` (`RECEIVE_BOOT_COMPLETED`) qui réarme l'alarme depuis les préférences Room, ou basculer sur `WorkManager` avec une période de 24 h (`WorkManager` persiste nativement au redémarrage — c'est la solution recommandée).

#### P1-3. Trois des quatre réglages de notification sont factices

`app/src/main/java/com/example/ui/screens/SettingsVersesScreen.kt:245-275` expose quatre interrupteurs persistés :
- Notifications activées
- **Rappel Fajr**
- **Rappel Dhuhr**
- **Rappel quotidien**
- **Rappel vendredi**

Or **une seule alarme est jamais programmée** : le rappel quotidien à 13 h (`REMINDER_HOUR = 13`, `DivineNotificationHelper.kt:69`). Vérifié : `scheduleDailyReminder` n'a que 3 points d'appel, tous pour le même rappel de 13 h.

`reminderFajr`, `reminderDhuhr` et `reminderFriday` sont **écrits en base et jamais lus** par quiconque planifie quoi que ce soit. L'utilisateur active « Rappel Fajr », l'interrupteur reste allumé, et **aucune notification ne sera jamais envoyée**.

Par ailleurs, l'écran affiche **5 aperçus de notifications** (réveil, midi, scroll, anniversaire, vendredi — `SettingsVersesScreen.kt:60-90`) alors qu'une seule existe réellement. C'est une **promise produit non tenue**, et sur une application spirituelle c'est particulièrement dommageable.

> **Correction :** soit implémenter les 4 rappels manquants (WorkManager + calcul de l'heure de chaque prière depuis le cache Aladhan), soit **retirer** les interrupteurs et les aperçus non fonctionnels. Ne jamais livrer un réglage qui ne fait rien.

#### P1-4. Deux pourcentages de vie différents sur le même écran

Sur l'écran principal coexistent deux jauges de « % de vie » calculées sur des dénominateurs différents :

| Composant | Dénominateur | Valeur affichée (profil par défaut, 24/10/2026) |
|---|---|:---:|
| `DivineCircularClock` — `percentageLived` | **espérance de vie** (75 ans par défaut) | **41,8 %** |
| `TwoDivineHoursClockDial` — `progressFraction` | **120 min divines** (83,33 ans, codé en dur) | **37,6 %** |

`DivineTimeBreakdownBarChart.kt:84` utilise `divineTime.percentageLived` (base 75 ans) pour la barre « vécu / restant », tandis que `TwoDivineHoursClockDial.kt:97` code en dur `val totalMinutesSpan = 120.0` (base 83,33 ans) au lieu d'utiliser la constante `DivineTimeCalculator.DIVINE_MINUTES_PER_TWO_HOURS`.

L'utilisateur lit deux chiffres différents pour ce qui apparaît être la même information. **La constante magique doit être remplacée par la référence au calculateur, et une seule notion de « vie écoulée » doit être choisie et documentée.**

---

### 🟡 P2 — Mineur

#### P2-1. Fuite de français dans l'interface AR/EN
`TwoDivineHoursClockDial.kt:349-353` — les cinq pastilles de jalons du cadran sont codées en dur :
```kotlin
DialMilestonePill("0m",   "Naissance", ...)
DialMilestonePill("30m",  "~20 ans",   ...)
DialMilestonePill("60m",  "~40 ans",   ...)
DialMilestonePill("90m",  "~60 ans",   ...)
DialMilestonePill("120m", "~80 ans",   ...)
```
C'est la **seule** fuite de ce type dans tout le projet (vérifié par analyse automatique de tous les `Text(...)` hors `AppStrings`). À déplacer dans `AppStrings`.

#### P2-2. Simulateur de vie : l'horloge de simulation diverge de l'horloge divine réelle

`LIFE_MILESTONES` porte deux positions pour le même événement (`DivineTimeCalculator.kt:290-360`) : une position normalisée sur 120 unités et la position divine réelle. Elles ne coïncident pas :

| Âge | Position sim (/120) | Minutes divines réelles | % des 120 min divines |
|---|:---:|:---:|:---:|
| 10 ans | 13,3 % | 14,40 | 12,0 % |
| 25 ans | 33,3 % | 36,00 | 30,0 % |
| 40 ans | 53,3 % | 57,60 | 48,0 % |
| 58 ans | 77,5 % | 83,50 | 69,6 % |
| **75 ans** | **100,0 %** | **108,00** | **90,0 %** |

À 75 ans, le simulateur annonce « la vie est finie » alors que le compteur divin n'en est qu'à 90 %. C'est documenté dans le code, mais l'utilisateur voit les deux. De plus, `earthAgeEquiv = (simSeconds / 120f) * lifeExpectancy` (`LifeJourneyVisualizerScreen.kt:96`) fait dépendre l'âge affiché de l'espérance de l'utilisateur, alors que les jalons ont des âges **fixes** — si l'espérance est réglée sur 90 ans, le jalon « retour à Allah (75 ans) » apparaîtra à 90 ans.

#### P2-3. Règle R8 qui ne protège rien
`app/proguard-rules.pro:54`
```proguard
-keep class com.example.notifications.** { *; }   # ← package inexistant
```
Le receiver est dans `com.example.data.notification` (singulier). La règle ne cible rien. En pratique l'AGP génère ses propres règles `aapt_rules.txt` pour les composants du manifeste, donc le receiver survivra — mais la règle est morte et donne une fausse impression de protection.

#### P2-4. Réarmement d'alarme à chaque frappe
`syncDailyReminder` est appelé à **chaque émission** du flux `userProfile` (`MainViewModel.kt:192`). Chaque tap de tasbih incrémente `totalTasbihAllTime` → écrit en base → le flux réémet → l'alarme est annulée et reposée. 100 taps de chapelet = 100 allers-retours vers `AlarmManager`. À dédier à un flux `distinctUntilChanged` sur les seuls champs pertinents.

#### P2-5. `minSdk 24` mais `setAndAllowWhileIdle`
Le commentaire (« minSdk est 24, donc `setAndAllowWhileIdle` est toujours disponible ») est exact (API 23+). Mais `setAndAllowWhileIdle` est **inexact** : le système peut différer l'exécution dans une fenêtre de maintenance. L'interface promet « 13 h 00 ». Utiliser `setAlarmClock` ou `WorkManager` si la précision est un engagement produit.

---

## 5. Performance

### 5.1 Le ticker 1 Hz est 36 000× trop fréquent

`MainViewModel.kt:207-220` recalcule `DivineTimeInfo` **chaque seconde** et l'expose via un `StateFlow` collecté à la racine de `DivineClockScreen`.

```
Un dixième de seconde divine = 0,1 × 4,2273 j = 10,1 heures terrestres
```

Le chiffre affiché (`00:45:09.4`) ne change donc que **toutes les ~10 heures**. Le ticker à 1 Hz déclenche **86 400 recompositions par jour** d'une sous-arborescence qui contient **deux `Canvas` lourds** (48 graduations + arcs tracés à la main), **quatre `rememberInfiniteTransition`** (pulsations permanentes à 1,4–2,2 s), et une `LazyColumn` complète — pour produire une valeur identique 36 000 fois de suite.

> **Correction :** dériver `liveDivineTime` d'un `flow` qui n'émet que lorsque la **chaîne formatée** change (ou au maximum une fois par minute), via `distinctUntilChanged { a, b -> a.formattedDivineTime == b.formattedDivineTime }`. Gain attendu : ~99,99 % des recompositions supprimées, et une baisse très sensible de la consommation batterie.

### 5.2 Ressources mortes : 1,46 Mo dans l'APK

| Fichier | Taille | Références |
|---|:---:|:---:|
| `drawable/divine_cosmos_header_1792516825325.jpg` | **1 031 Ko** | 0 |
| `drawable/saatan_launcher_icon_1792516812459.jpg` | **467 Ko** | 0 |

Ces deux fichiers (noms générés avec timestamp) ne sont référencés **nulle part**. Ils représentent à eux seuls **75 % du poids total de `res/`** (1,9 Mo) et sont aspirés dans l'APK pour rien. `isShrinkResources = true` en release ne les supprimera pas (ressources `drawable` non marquées, pas de `tools:keep`/`discard` configuré).

### 5.3 Polices
`amiri.ttf` (47 Ko) et `cinzel.ttf` (33 Ko) sont chargées en **plein**, alors qu'on ne peut pas sous-définir un TTF distribué dans `res/font` de façon simple. Acceptable, mais l'idéal serait d'utiliser les versions variables/downloadable fonts.

---

## 6. Internationalisation — le point fort

Audit automatisé de `AppStrings.kt` (2 341 lignes, **265 fonctions**, **361 triplets** FR/AR/EN) :

| Contrôle | Résultat |
|---|---|
| **Cohérence des spécificateurs de format** entre langues | **0 anomalie** sur 361 triplets |
| Branches `when(lang)` exhaustives | **100 %** (aucun `else` de repli sur la langue) |
| Chaînes FR strictement identiques à EN | 21 — **toutes légitimes** (noms propres : « Fajr », « SA'ATAN » ; unités : « Minutes », « pages » ; formules : `Minute $minute / 120`) |
| Textes visibles hors `AppStrings` | **1 seul** cas (P2-1) |
| Support RTL | `CompositionLocalProvider(LocalLayoutDirection …)` + `android:supportsRtl="true"` ✅ |

**C'est un résultat nettement au-dessus de la moyenne**, y compris pour des applications commerciales. Le catalogue centralisé avec passage explicite de `AppLanguage` (plutôt que le système de ressources Android) était le bon choix pour un trilinguisme avec bascule à chaud : la langue change **instantanément** sans recréer l'activité.

**Réserves :**
- 29 fonctions `AppStrings` ne sont **jamais appelées** (`notesPlaceholder`, `prayerTimesError`, `prayerTimesFallbackBadge`, `notificationPermissionRequired`, `profileDialogTitle`, `resetBtn`, `yearsUnit`, `pagesUnit`…). Présence notable de `prayerTimesError` et `prayerTimesFallbackBadge` : **des textes d'erreur réseau existent mais ne sont jamais affichés** — l'utilisateur ne sait donc pas quand il est sur le fallback hors-ligne, alors que l'information est disponible dans `PrayerCountdownState.isUsingFallback`.
- La détection de langue au premier lancement est absente : l'application démarre toujours en français, même sur un téléphone en arabe. Un simple `Locale.getDefault()` au premier lancement améliorerait beaucoup l'onboarding dans le monde arabe.

---

## 7. Tests

| Fichier | Lignes | Contenu |
|---|:---:|---|
| `ExampleUnitTest.kt` | 298 | **23 vrais tests** du domaine |
| `ExampleRobolectricTest.kt` | 54 | 3 tests (nom de l'app, échelle, mapping) |
| `ExampleInstrumentedTest.kt` | 22 | 1 test placeholder |

**Points forts :** les tests **ne réimplémentent pas** les formules (piège classique — le commentaire d'en-tête indique que c'était le cas avant et que cela a été corrigé). Ils couvrent les constantes, les cas limites (nouveau-né, espérance 0, naissance future, dépassement à 150 ans), les bornes des phases, le clampage des négatifs, et un **contrat de traduction** (`everyStageIsTranslatedInEveryLanguage`). C'est du très bon travail.

**Lacunes (couverture réelle ≈ 15 %) :**
- ❌ **Aucun test** de `PrayerTimesRepository` — pourtant il contient la logique la plus subtile du projet (fuseau de la ville, bascule « prière de demain », parsing, fallback). Les bugs P1-1 s'y cachent.
- ❌ **Aucun test** de `SaatanRepository` (calcul des étoiles, tasbih, thème).
- ❌ **Aucun test** de `MainViewModel` (impossible : il instancie `PrayerTimesRepository()` directement).
- ❌ **Aucun test Room** (DAO, migration 1→2).
- ❌ **Aucun test de composition** (les 6 écrans, 1 364 lignes pour `DivineClockScreen`, sont non testés).
- ⚠️ `app/schemas/` ne contient que `2.json` — le schéma v1 n'a jamais été exporté, la migration 1→2 n'est donc pas vérifiable automatiquement.
- ⚠️ Les fichiers s'appellent encore `Example*Test.kt`.

---

## 8. Sécurité & confidentialité

| Point | Statut | Détail |
|---|:---:|:---|
| Chiffrement réseau | ✅ | HTTPS uniquement (`https://api.aladhan.com/`), pas de `cleartextTrafficPermitted` |
| Clés API | ✅ | Aucune clé dans le code. `GEMINI_API_KEY` n'est **jamais utilisé** |
| Composants exportés | ✅ | `<receiver android:exported="false">` correct |
| Permissions | ✅ | Minimales (`INTERNET`, `VIBRATE`, `POST_NOTIFICATIONS`) |
| Signature release | ✅ | Externalisée via variables d'environnement, avec repli gracieux |
| R8/ProGuard | ⚠️ | Activé, mais règle morte (P2-3) |
| **Sauvegarde des données** | 🔴 | `android:allowBackup="true"` avec `data_extraction_rules.xml` **vide / non configuré** (les blocs `<include>`/`<exclude>` sont commentés). Les notes de journal — des réflexions personnelles intimes — sont donc **sauvegardées en clair sur Google Drive**. À restreindre explicitement. |
| Journalisation | ✅ | Aucune donnée personnelle loggée ; `Log.d/v/i` retirés en release |

---

## 9. Accessibilité — le point faible

**0 occurrence** de `Modifier.semantics` ou `Role.*` dans les 11 045 lignes de production, contre **20 `Modifier.clickable`**.

Conséquences concrètes pour un utilisateur de TalkBack :
- Les 20 éléments cliquables construits comme `Box { … }.clickable { … }` (pastilles de prière, sélecteur de langue, bascule mode exploration, carte de jalon…) sont annoncés comme du texte brut, **sans rôle « bouton »**, sans état coché/non coché.
- Les deux cadrans `Canvas` (`TwoDivineHoursClockDial`, `DivineCircularClock`) véhiculent **toute** l'information centrale de l'application (temps divin écoulé, progression) de façon **purement visuelle**, sans aucune équivalence textuelle. Un utilisateur aveugle n'a accès à rien de l'écran principal.
- Tailles de texte descendant à **9 sp** (`DialMilestonePill`, `prayerName` dans les chips), sous le minimum Material de 12 sp.
- Les `contentDescription = null` sur les `Icon` décoratifs sont corrects, mais plusieurs `Text` purement décoratifs (emoji seuls : 🌍 ☁️ ⏳ ⚡ 🗺️ 💡) sont lus comme « globe terrestre », « nuage »… à masquer avec `Modifier.clearAndSetSemantics {}`.
- **Contraste insuffisant en thème nuit** : `TextMuted` `#64748B` sur `Midnight950` `#030712` donne un ratio de **4,23:1**, sous les **4,5:1** exigés par WCAG AA pour du petit texte. Or `TextMuted` est utilisé massivement (légendes, pourcentages, mentions secondaires) précisément en petit corps (9–11 sp). Le thème jour, lui, passe tout juste (`DawnTextMuted` sur `DawnCream950` = **4,59:1**). Éclaircir `TextMuted` vers `#7C8BA1` (≈ 5,4:1) corrigerait le défaut sans dénaturer la charte.

---

## 10. Préparation à la publication

| # | Blocage | Détail |
|:---:|---|---|
| 1 | **`applicationId = "com.aistudio.saatan.kzvhym"`** | Identifiant généré par AI Studio (`kzvhym` = hash aléatoire). **Irréversible après publication** sur Play. À changer en `com.votredomaine.saatan` **avant** la première mise en ligne. |
| 2 | **`namespace = "com.example"`** | Package par défaut d'Android Studio. À aligner sur l'`applicationId`. |
| 3 | **`versionCode = 1`, `versionName = "1.0"`** | Jamais incrémenté malgré un historique de corrections. Sans `versionCode` croissant, Play rejettera les mises à jour. |
| 4 | **Méthode de calcul des prières figée** | `AladhanApiService.kt:15` — `method: Int = 2` (ISNA, *Islamic Society of North America*, angles 15°/15°). Pour une application dont le cœur de cible est le Maghreb (l'utilisateur est à Oran), c'est probablement **la mauvaise convention** : l'ISNA est calibrée pour l'Amérique du Nord et produit un Fajr plus tardif et un Isha plus tardif que les méthodes usuelles en Afrique du Nord (Muslim World League `3` — 18°/17°, ou Égyptienne `5`). L'écart atteint couramment **10 à 20 minutes** sur Fajr. **Aucun réglage utilisateur n'est exposé.** |
| 5 | **`metadata.json`** déclare `MAJOR_CAPABILITY_SERVER_SIDE_GEMINI_API` | Aucune dépendance Gemini, aucun appel. Déclaration à retirer. |
| 6 | Pas de politique de confidentialité | L'application collecte une date de naissance et des notes personnelles, et appelle une API tierce (Aladhan). Play exige une politique de confidentialité + le *Data Safety* renseigné. |
| 7 | `README.md` encore celui du template AI Studio | Contient des instructions obsolètes (« supprimez cette ligne `debugConfig` » — le bloc n'existe plus). |
| 8 | Un seul commit dans l'historique | Aucune traçabilité, aucun message de commit descriptif. |
| 9 | Pas de CI | Aucun workflow GitHub Actions : pas de `./gradlew build` ni `test` automatique. |
| 10 | Catalogue Gradle encombré | 12 dépendances déclarées et **jamais utilisées** : Coil, CameraX (×4), DataStore, Credentials, GoogleId, Navigation-Compose, Play-Services-Location, Accompanist-Permissions. |

---

## 11. Feuille de route de remédiation

### ⚡ Immédiat (avant toute démonstration publique)
1. **P0-1** — Ajouter le slider « jour » au dialogue de profil, porter 2024 → année courante, valider la date. *(30 min)*
2. **P1-1** — Brancher `isCacheStale()` dans le ticker de prière. *(15 min)*
3. **P1-2** — `BootReceiver` + `RECEIVE_BOOT_COMPLETED`, ou migration vers `WorkManager`. *(1-2 h)*
4. **P1-3** — Retirer (ou implémenter) les 3 interrupteurs et 4 aperçus de notification factices. *(1 h)*
5. **P1-4** — Remplacer `120.0` codé en dur par `DivineTimeCalculator.DIVINE_MINUTES_PER_TWO_HOURS` et unifier la notion de « vie écoulée ». *(30 min)*

### 🔧 Court terme (avant publication)
6. Corriger `applicationId`, `namespace`, `versionCode`. *(15 min — **à faire avant la 1ʳᵉ publication**)*
7. Supprimer les 1,46 Mo de ressources mortes. *(5 min)*
8. Rendre la méthode de calcul des prières configurable (défaut `3` MWL pour l'Afrique du Nord). *(1 h)*
9. `distinctUntilChanged` sur le ticker → gains massifs de batterie et de fluidité. *(30 min)*
10. P2-1 (fuite FR), P2-3 (règle R8), configurer `data_extraction_rules.xml` pour exclure les notes du backup. *(1 h)*

### 🏗️ Moyen terme (qualité durable)
11. Extraire les 11 responsabilités de `MainViewModel` ; introduire une DI et des `UseCase` pour rendre le ViewModel testable.
12. Rapatrier dans `DivineTimeCalculator` les 4 calculs métier aujourd'hui dans les composables.
13. Atteindre ~70 % de couverture, en priorité sur `PrayerTimesRepository` (fuseaux, bascule de jour, fallback) et `SaatanRepository`.
14. Passer l'accessibilité : `Modifier.semantics { role = Role.Button }` sur les 20 zones cliquables, équivalent textuel pour les deux cadrans `Canvas`, minimum 12 sp.
15. Requalifier la formule « 2 heures = une vie » en projection explicitement inspirée (et non citée) d'Al-Hajj:47, et faire relire les références coraniques par une personne de science.
16. Mettre en place une CI (build + tests + lint) et adopter des messages de commit conventionnels.

---

## 12. Conclusion

**Sa'atan n'est pas un prototype.** C'est une application avec une vraie colonne vertébrale : un domaine mathématique **pur, testé et numériquement exact**, une internationalisation trilingue **plus rigoureuse que la moyenne du marché**, et une intégration des horaires de prière qui traite correctement la difficile question du fuseau horaire de la ville. Les choix structurants — calculateur sans dépendance Android, constantes uniques partagées, `AppStrings` centralisé, garde-fous sur tous les cas dégénératifs — sont **les bons**.

Ce qui manque n'est ni l'idée ni la compétence : c'est **une passe de finition**. Les cinq défauts bloquants/majeurs identifiés ont tous la même signature : une fonctionnalité *écrite* mais jamais *reliée* (le slider de jour absent, `isCacheStale()` jamais appelé, le réarmement après reboot absent, les interrupteurs sans effet). Ce sont typiquement les traces d'une génération rapide qu'une seule session de tests manuels sur un téléphone réel — laissé allumé une nuit, redémarré une fois, et utilisé en arabe — aurait révélées.

**Recommandation :** consacrer une journée aux cinq points « Immédiat », puis une journée à une session de tests sur appareil réel (thème clair, RTL arabe, hors-ligne, passage à minuit, redémarrage). Après quoi cette application aura le niveau d'exigence de son ambition.

---

*Audit réalisé le 24 octobre 2026 — revue statique complète, vérification numérique indépendante des formules, analyse automatisée de l'i18n.*
