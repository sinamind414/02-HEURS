# Politique de confidentialité — Dhawq

*Version 1.0 — 24 octobre 2026. Texte de référence pour la fiche Play Store et l'écran « Confidentialité » de l'app.*

> **La version courte, parce qu'elle est notre fierté :** Dhawq n'a pas de compte utilisateur, pas de publicité, pas d'analyse d'audience, pas de traceur. Ce qui se passe entre toi et ta prière ne nous regarde pas.

---

## 1. Ce que Dhawq stocke — sur ton téléphone, et nulle part ailleurs

| Donnée | Où | Pourquoi | Quitte l'appareil ? |
|---|---|---|---|
| Ta ville (nom) | Stockage local | Calculer les heures de prière | **Oui** — voir §2 |
| Ta date de naissance | Stockage local (protégé par le système) | L'écran « deux heures » : ce qui reste de ta vie | **Jamais** |
| Tes goûts de prière (« douce, lourde, absente ») | Base locale, seul le jour courant est affiché | Te proposer la question du soir | **Jamais** — et jamais agrégé en score ou série |
| Tes réglages (langue, notifications, thème) | Stockage local | Ton confort | **Jamais** |
| Les heures de prière mises en cache | Base locale | Que l'app fonctionne hors ligne | **Jamais** |

**Suppression totale : désinstalle l'application.** Il n'existe aucun serveur Dhawq contenant la moindre donnée te concernant, donc il n'y a rien à supprimer « de notre côté » — il n'y a pas de « notre côté ».

## 2. Ce qui quitte ton téléphone — une seule chose

Pour calculer les heures de prière, Dhawq interroge le service public **Aladhan** (`api.aladhan.com`). La requête contient exactement :

- le **nom de ta ville et ton pays** (celui que tu as tapé) ;
- la méthode de calcul (MWL par défaut) ;
- la date demandée.

Rien d'autre. **Pas d'identifiant publicitaire, pas d'identifiant d'appareil, pas de position GPS** (Dhawq ne demande pas la permission de localisation), pas de « modèle d'appareil », pas de statistiques d'usage. Et dès que les heures sont reçues, elles sont mises en cache : en mode hors ligne, l'app fonctionne sans rien transmettre.

## 3. Les autorisations demandées — et pourquoi chacune existe

| Autorisation | Justification | Peut-on la refuser ? |
|---|---|---|
| Internet | Interroger le service d'heures de prière | Oui — cache hors ligne |
| État du réseau | Savoir si le cache doit être utilisé | Oui |
| Vibration | Le retour tactile discret des gestes | Oui |
| Notifications | L'invitation aux rendez-vous de la prière | Oui — chaque rappel est désactivable |
| Démarrage (boot) | Rétablir les rappels après redémarrage du téléphone | Oui |

Aucune autre. Pas de localisation, pas de micro, pas de contacts, pas de stockage partagé, pas d'appareil photo.

## 4. Ce que Dhawq ne fera jamais

Nous l'écrivons noir sur blanc, parce que le marché nous a appris ce qui se fait ailleurs :

1. **Jamais de publicité** — aucune, ni bannière, ni vidéo, ni « sponsorisé ».
2. **Jamais de revente ni de partage de données** — avec personne : ni annonceurs, ni courtiers en données, ni tiers de quelque nature que ce soit.
3. **Jamais de traceur** ni de SDK d'analyse d'audience dans l'application.
4. **Jamais de profil** — pas de compte, pas d'e-mail, pas de téléphone.
5. **Jamais de score de piété** transmis, comparé ou affiché à quiconque — tes œuvres t'appartiennent.

Si un jour un service payant voyait le jour, il serait facturé à l'unité, sans abonnement piégé, et cette politique serait mise à jour **avant** tout changement, avec un opt-in explicite.

## 5. Les données des enfants

Dhawq n'est pas destinée aux enfants de moins de 13 ans et ne collecte aucune donnée les concernant — comme elle ne collecte celles de personne.

## 6. Contact

Une question sur cette politique, ou une demande relative à des données (rappel : elles sont toutes sur ton appareil) : **contact@dhawq.app** *(adresse à activer avant le lancement — voir Beta)*.

---

## Résumé arabe (الملخص بالعربية)

> **ذَوْق** لا يطلب إنشاء حساب، ولا يعرض الإعلانات، ولا يجمع إحصائيات، ولا يتتبع أحدًا. كل بياناتك محفوظة على هاتفك وحده: مدينتك، تاريخ ميلادك، ذوق صلاتك، وإعداداتك. الشيء الوحيد الذي يغادر هاتفك هو اسم مدينتك، لطلب مواقيت الصلاة من خدمة «الأذان» العامة. لا موقع جغرافي، لا معرّف للجهاز، لا بيع لأي معلومة — أبدًا. احذف التطبيق تُحذف بياناتك كلها.

## Résumé anglais (English summary)

> Dhawq has no accounts, no ads, no analytics, and no trackers. Everything the app stores — your city, your birth date, the taste of your prayer, your settings — lives on your phone and nowhere else. The only thing that ever leaves your device is your city's name, sent to the public Aladhan prayer-times service. No GPS, no device identifiers, no data sold or shared — ever. Delete the app, and every trace is gone.

---

*Cette politique entre en vigueur à la publication de la version publique de Dhawq et ne peut être rendue plus faible qu'elle ne l'est aujourd'hui — uniquement plus protectrice.*
