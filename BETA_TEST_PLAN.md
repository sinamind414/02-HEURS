# Plan de test Beta — Dhawq 0.9.0

*24 octobre 2026 — 2 semaines sur appareils réels, avant le lancement mi-janvier 2027.*

---

## 0. Prérequis (avant touteBeta)

| # | Prérequis | Fait |
|---|---|---|
| P1 | **Compilation Android Studio** : premier build réel de l'ensemble (Sprints 0-3 n'ont jamais été compilés — vérification statique uniquement). Corriger les erreurs au fil. | ☐ |
| P2 | Installer sur un appareil physique minimal : 1 Android récent (13+), 1 Android ancien (8/9), 1 écran petit (≤ 5,5"). | ☐ |
| P3 | **Crochet de débogage Ramadan** : derrière `BuildConfig.DEBUG` uniquement, un réglage caché « forcer la date Hijri » (ex. « 21 Ramadan ») pour tester Les Dernières Nuits sans attendre 1448 H. À retirer du build de production par construction. | ☐ |
| P4 | Vérifier que le suffixe `_fr` est bien résolu par `JourneyScreen` (station → `dhawq_station_01_fr`) et la dégradation « Enregistrement à venir » quand une piste manque. | ☐ |

## 1. Semaine 1 — le socle

### Jour 1-2 : Cycle Quotidien
- [ ] L'Invitation s'affiche au bon moment ; « Va. » disparaît de lui-même.
- [ ] Le Vide (0-15 min post-adhan) puis la Minute Précieuse (10-45 min) — **une seule fois par prière et par session** (tuer/rouvrir l'app ne doit pas la re-proposer immédiatement).
- [ ] Le goût du soir : « douce / lourde / absente / goûtée » persiste (tuer l'app, revenir : le jour courant l'affiche toujours).
- [ ] **Serment #2** : nulle part un score, une série, une comparaison entre jours n'apparaît.
- [ ] La Fenêtre d'or s'annonce au dernier tiers de la nuit (vérifier sur Fajr réel).

### Jour 3-4 : passage à minuit + redémarrage
- [ ] Laisser l'app ouverte pendant le passage à minuit : bascule du jour, nouveau décompte, pas de crash.
- [ ] Mode hors ligne : couper les données → les heures en cache s'affichent, mention fallback visible.
- [ ] Redémarrer le téléphone : les rappels se rétablissent seuls (BootCompletedReceiver).
- [ ] Changer de ville : décompte recalculé, cache remplacé.

### Jour 5 : RTL arabe + mode jour
- [ ] Passer l'app en arabe : tout le parcours s'inverse proprement (rtl), textes non tronqués, ponctuation côté droit.
- [ ] **Mode jour sur les 4 écrans** (Horloge, Voyage, Dernières Nuits, veille) : aucun texte illisible — en particulier le verset 97:3 sur fond parchemin (corrigé le 24/10 : il était ivoire sur ivoire).
- [ ] Vérifier le contraste perçu des titres dorés en mode jour — noter toute illisibilité pour correction palette.

## 2. Semaine 2 — la voix et la saison

### Jour 6-7 : Le Voyage audio (trilingue)
- [ ] Chaque station joue en **FR, AR et EN** ; la piste correspond à la langue d'affichage.
- [ ] Lecture/pause/reprise ; le player ne reprend pas l'ancienne piste en changeant de station.
- [ ] Le téléphone en silencieux → l'audio ne coupe pas la sonnerie si une prière arrive **pendant** l'écoute : le lecteur se met en pause (Serment #4 — test avec un adhan simulé).
- [ ] Casque débranché en cours de lecture → pause propre, pas de son dans le haut-parleur.

### Jour 8-9 : Les Dernières Nuits (avec le crochet P3)
- [ ] Forcer « 21 Ramadan » → la carte saison passe en « en cours — nuit 21 », l'écran s'ouvre.
- [ ] Les **nuits impaires** portent l'accent doré ; la nuit forcée porte le badge « en cours ».
- [ ] Forcer « 27 » puis « 30 » puis « 1 Shawwal » → la saison s'éteint proprement (plus de badge, cartes neutres).
- [ ] **Le mode veille** : plein écran noir, le compteur monte, une ligne de dhikr ; sortie propre ; le téléphone en veille ne fait rien d'autre — aucune notification ne surgit par-dessus (à tester avec une notification de test).
- [ ] Hors Ramadan forcé : la carte saison reste visible (annonce de la saison) sans badge.

### Jour 10 : écritures + remontée
- [ ] Rotation de l'écran pendant la veille et le player : pas de redémarrage de piste.
- [ ] Memory : l'app ≤ ~40 Mo après install (30 pistes = 14 Mo inclus).
- [ ] Consigner tout écart dans ce fichier (section ci-dessous) ; bloquant → corriger avant Play Store.

## 3. Critères de sortie (go/no-go lancement)

1. Zéro crash sur les 4 écrans × 3 langues × 2 thèmes.
2. Les 4 serments vérifiés par un testeur externe qui ne connaît pas le projet.
3. Compilation en `release` (R8/ProGuard si activé) sans casser les réflexions Room/MediaPlayer.
4. La politique de confidentialité publiée sur la fiche Play Store.

## 4. Journal des anomalies

| # | Écran | Langue | Repro | Gravité | Statut |
|---|---|---|---|---|---|
| | | | | | |
