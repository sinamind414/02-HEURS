# Dhawq Presence — Package Stratégie US (v1, 2026-10-29)

> **Source** : revue externe transmise le 29/10/2026 (« Complete Revised Package for the US Market » + suites 1-3).
> **Statut** : document de stratégie produit — **rien n'est implémenté dans le code à cette date**.
> **Décisions ouvertes** : voir §0. Validation théologique requise : voir COMITE_THEOLOGIQUE.md §3.5.

---

## 0. Décisions ouvertes (à trancher avant tout développement)

1. **Identité produit** : « Presence Rhythm » (layer islamique caché par défaut) vs « Dhawq Presence » (islamique d'emblée). A/B test proposé — tension avec l'âme du projet (تذوق العبادات = goûter LA prière). **Décision propriétaire + comité requise.**
2. **Stack** : le package propose React Native/Flutter + Supabase = réécriture totale. **Recommandation contraire** : l'app existe déjà (Kotlin/Jetpack Compose natif, 60 tests, 5 langues, lint vert). Le pivot se fait par **feature-flag `tradition_layer`** dans la codebase actuelle.
3. **Nouveaux hadiths proposés** à valider par le comité (§4 ci-dessous).
4. **Monétisation** : freemium plus tard, jamais de paywall sur les 5 pauses de base.

---

## 1. Onboarding — 5 écrans max (English US)

### Screen 1 — Welcome
- **Title** : How have you been feeling lately?
- **Subtitle** : You're not alone. Millions of Americans feel scattered, anxious, or empty right now.
- **Options (multi-select, no judgment)** : Overwhelmed / racing thoughts · Lonely, even around people · Numb or disconnected · Scrolling but never satisfied · Just looking for more meaning
- Button : Continue

### Screen 2 — The Invitation
- **Title** : Five short pauses. One ancient rhythm.
- **Body** : For 1,400 years, people have used five daily moments of return — structured, embodied, and directed toward something larger than themselves.
  Science now confirms what tradition always knew: regular ritual, presence, and community help regulate the nervous system and restore a sense of meaning.
  This is not therapy. This is not a replacement for professional care.
  This is a daily return.
- Button : See how it works

### Screen 3 — Authentic Testimonial (short video or text + photo)
> "I used to wake up at 3am with my heart racing, scrolling until I felt worse. These five pauses gave me a rhythm I didn't know I needed. It didn't fix everything overnight, but it gave me somewhere to come back to."
> — James, 34, former addict, now in recovery (American convert)

### Screen 4 — Choose Your Starting Point
- **Title** : Start where you are
- Options : Beginner: 1–2 pauses a day (recommended) · Full rhythm: All 5 daily returns · Just explore first
- Small text : You can always change this later. No pressure.

### Screen 5 — Permission & Disclaimer
- We are not medical professionals. If you are in crisis, please reach out to a licensed therapist or call 988 (Suicide & Crisis Lifeline).
- This practice can complement, but never replace, professional mental health care.
- Button : Begin my first return

---

## 2. Dashboard (Home Screen)

- **Header** : Today's Returns
- 5 visual cards (Fajr / Dhuhr / Asr / Maghrib / Isha) avec checkboxes simples et soft progress bar.
- Label : « 5 moments of presence » (pas « 5 prayers » au premier abord)
- **Widget Inner Weather** : slider ou 5 emojis — How are you feeling right now? (before/after each pause)
- Today's Presence : 12 minutes · This week : 47 minutes of return
- Bouton discret : **Explore the tradition →** (unlocks Quran, hadith, convertisseur, Arabic)
- Weekly insight (small card) : « Your mood after pauses has been 23% calmer this week. »

---

## 3. Convertisseur révisé — « Eternal Perspective » (optionnel, jamais forcé)

**Où** : dans « Deeper Perspective » (après 7–14 jours d'usage) ou via petite icône « ∞ » sur le dashboard.

**Copy** :
- **Title** : A Different Scale of Time
- **Body** : The Quran (22:47) offers a striking image: "A day with your Lord is like a thousand years of what you count."
  Some scholars and seekers have reflected on this as a way to reframe our short lives. In that light, five daily pauses of a few minutes each, practiced over a lifetime, become something almost vanishingly small — and yet, for those who practice them, they often feel like the most real minutes of the day.
  This is not a scientific calculation. It is a spiritual invitation to consider: What if these minutes are not "lost time," but the most valuable time you have?
- **Interactive slider (optional)** : your age · average minutes per pause · result contemplatif.
- **Ton** : contemplatif, jamais « Allah t'a donné 120 minutes, vas-tu les rendre ? ». Plutôt « Here's a way some people hold this practice. »

> ✅ Conforme au cadrage « échelle symbolique de réflexion » appliqué le 28/10/2026 (note 5 langues dans l'écran + README + comité §3.4).

---

## 4. Versets / hadiths / études

### Versets (sagesse ancienne, traduction claire)
- **Quran 13:28** — "Unquestionably, by the remembrance of Allah hearts find rest." (le plus important pour l'anxiété)
- **Quran 22:47** — verset du convertisseur (image spirituelle)
- **Quran 2:152** — "Remember Me and I will remember you."
- **Quran 29:45** — "Indeed, prayer prohibits immorality and wrongdoing, and the remembrance of Allah is greater."
- **Quran 20:14** — "Establish prayer for My remembrance."
- **Quran 5:6** — wudu (présenté hygiène + intention)
- **Quran 62:9** — appel/leaving business

### Hadiths (courts, contextuels — statut à valider comité)
- "The closest a servant is to his Lord is when he is in prostration." (**Muslim** ✓ sahih)
- Hadith **Muslim 395** (Al-Fatiha comme dialogue) — déjà dans l'app ✓
- "Prayer is a light." (**Muslim** ✓ sahih)
- "The coolness of my eyes was placed in prayer." (**Ahmad** — authentifié par de nombreux savants, dont al-Albani ; attribution complète requise)

### Études & données US (section « Why this works » — toujours avec disclaimer)
- **U.S. Surgeon General Vivek Murthy (2023)** : Epidemic of Loneliness — isolation ≈ 15 cigarettes/jour.
- **Jonathan Haidt**, *The Anxious Generation* : smartphones + social media ↔ santé mentale des ados post-2010.
- **Pew Research & Harvard** : pratique religieuse régulière ↔ moins de dépression, plus de satisfaction et de sens.
- **NIH / méta-analyses** : rituel structuré, méditation et prière réduisent le cortisol, améliorent la régulation émotionnelle, diminuent la rumination.
- **Anne Case & Angus Deaton** : *Deaths of Despair* — perte de sens/communauté/structure.
- **Robert Putnam** : *Bowling Alone* — effondrement du capital social.

> Toujours afficher : « Correlation is not causation. This practice is not a treatment for clinical depression or anxiety disorders. »

---

## 5. Version light — « Presence Rhythm »

- **Sous-titre** : Five daily pauses. Ancient structure. Modern calm.
- Noms : Morning Return · Midday Pause · Afternoon Reset · Sunset Presence · Night Return.
- Pas d'arabe visible au départ, pas de « Salat », pas de wudu obligatoire.
- Guidage : souffle, posture, intention, gratitude universelle.
- Layer islamique **optionnel et progressif** : bouton « Unlock the original tradition ».
- Même codebase, un flag `tradition_layer`.

---

## 6. Live Prayer Companion (pendant la pause)

**Entrée** : Title « Your next return » · Subtitle « 4–8 minutes. You can stop anytime. » · Toggles Guided/Silent, English only / English + Arabic transliteration · Button « Begin this pause ».

**Flow guidé (beginner)** :
1. **Arrival (30–45 s)** — « Find a quiet spot if you can. Sit or stand comfortably. Let your shoulders drop. » / « Notice one thing you can feel right now. »
2. **Intention (20 s)** — « This moment is yours. No performance. Just return. » (layer : « I turn toward the One who created me. »)
3. **Body & breath (1 min)** — inhale 4, exhale 6. « If your mind races, that's normal. »
4. **Core presence (2–4 min)** — light : gratitude/ancrage universel. Tradition : stations simplifiées + Al-Fatiha interactive ligne par ligne + hadith discret.
5. **Closing (20–30 s)** — « Stay for three more breaths. You showed up. That's enough for now. »

**Audio** : voix calme, américaine, légèrement grave, pauses longues. Jamais pressé, jamais « tu dois ».

---

## 7. Post-Pause Reflection + Journal

**Immédiat (10–20 s)** : « How do you feel now? » — Inner Weather 5 états (Overwhelmed → Anxious → Neutral → Calmer → Grounded) + question ouverte rotative :
- What shifted, even a little?
- What did you notice in your body?
- What thought kept coming back?
- What are you putting down for now?

Buttons : Save & continue · Skip (no guilt).

**Journal « Notes from the returns »** : entrées courtes datées liées aux pauses, étoiles 1–5 « How present did this feel? », prompt hebdo, privé par défaut, export. Titre interne layer tradition : Divine Minutes Journal (jamais affiché en premier).

---

## 8. Weekly / Monthly Report

**Weekly** (dimanche soir / lundi matin) : minutes de présence, returns complétés (honnête, ex. 19/35), tendance Inner Weather (« calmer after 7 out of 12 pauses »), un insight non-jugeant, graph simple mood before/after.
Encadré « Why this can help » : études (NIH, Harvard) + Quran 13:28. Disclaimer visible. CTA : « Set a gentle intention for next week ».

**Monthly** : « Your Presence Legacy » — option d'activer Eternal Perspective après 3–4 semaines d'usage.

---

## 9. Notifications push (ton empathique)

Règles : max 3–5/jour selon le rythme · jamais « You missed Fajr » · sortie facile « Not now » toujours · fréquence réduite si skips répétés.

| Moment | Version A | Version B |
|---|---|---|
| Dawn | A quiet start is available. 4 minutes, if you want it. | Morning return is open. Come if it feels right. |
| Midday | One pause can interrupt the scroll. | Midday hinge. A short return is here. |
| Afternoon | The day is still yours. | Afternoon reset — whenever you're ready. |
| Sunset | A natural pause in the light. | Sunset presence. The day is turning. |
| Night | Before the feed — one last return, or simply rest. | Night return, or rest. Both are allowed. |
| After 3 days | You've shown up three days. That's a rhythm. | Three days of returning. No streak pressure. |
| Inactive 48h | No pressure. The pauses are still here. | We're here when you need a place to come back to. |
| Weekly | Your Presence Report is ready — small data, no judgment. | This week's returns, honestly counted. |

Jamais « Allah t'attend » ni guilt en notification. Crisis permanent in-app : « If you are in crisis, call or text 988 (US). This app is not emergency care. »

---

## 10. Explore the Tradition (layer progressif)

Hub : « Go deeper when you're ready » — « Everything here is optional. The five pauses work without it. »

Déblocage progressif (3–7–14–21 jours ou manuel) :
1. **Why five pauses?** — rythme, charnières naturelles du jour, 1 400 ans, Salat.
2. **The five names** — Fajr/Dhuhr/Asr/Maghrib/Isha.
3. **Water (Wudu)** — Quran 5:6, reset physique.
4. **Direction (Qibla)** — mini-map, permission.
5. **The conversation (Al-Fatiha)** — training interactif.
6. **The 10 stations** — journey (voir §11).
7. **A different scale of time** — Eternal Perspective (copy §3).
8. **Science & Revelation** — 13:28, 2:152, 20:14, 29:45 + études US + disclaimer.
9. **Voices from here** — témoignages convertis US, ton réel (« it helped me stay », jamais « I was cured »).
10. **Find a local community** — mosquées/MSA, géoloc avec permission, « Not now ».

Bouton permanent : **Keep it simple**.

---

## 11. Les 10 stations — « The Return Journey » (رحلة الصلاة adaptée)

Chaque station = 20–90 s. Light = langage universel. Tradition = verset + sens. Skip toujours visible.

1. **Arrive** — L: « You don't have to be ready. Just arrive. » / T: « The journey begins the moment you decide to return. »
2. **Water** — L: « Water on the skin. A small reset for the nervous system. » / T (5:6): « Allah does not intend to make difficulty for you, but to purify you. »
3. **Walk** — L: « Even three steps toward a quieter corner count. » / T (62:9): « Leave off business and hasten to the remembrance. »
4. **The Call** — L: « A signal that this moment is different. » / T: l'Adhan comme invitation, pas une alarme.
5. **Stand** — L: « Feet on the ground. Spine easy. You are here. » / T: Qiyam.
6. **Recite / Speak** — L: « A few words of presence, gratitude, or simply silence. » / T: Al-Fatiha (§12).
7. **Bow** — L: « A small lowering. The day can wait. » / T: Ruku — « Glory to my Lord, the Magnificent. »
8. **Prostrate** — L: « Forehead down, or simply a deeper exhale. » / T (Muslim): « The closest a servant is to his Lord is while prostrating. »
9. **Sit** — L: « A quiet seat between efforts. » / T: Tashahhud.
10. **Close & Return** — L: « The pause ends. You return different, even if only 2%. » / T: Salam.

---

## 12. Al-Fatiha Interactive Training (English US)

Intro : « This short chapter has been called a conversation. Some hear it as the servant speaking, and the Lord answering. »

| Ligne | Traduction | Sens |
|---|---|---|
| 1 | In the name of God, the Most Compassionate, the Most Merciful. | We begin by naming mercy, not achievement. |
| 2 | All praise is for God, Lord of all the worlds. | Gratitude before request. |
| 3 | The Most Compassionate, the Most Merciful. | Repeated on purpose. The first thing you meet is compassion. |
| 4 | Master of the Day of Judgment. | There is a larger scale than today's feed and today's fear. |
| 5 | You alone we worship, You alone we ask for help. | One direction. One place to put the overwhelm. |
| 6 | Guide us on the straight path. | Not "make me perfect." Just "show me the next right step." |
| 7 | The path of those You have blessed… | We ask to be among those who found their way back. |

Hadith Muslim 395 (dialogue divin) en carte optionnelle après la récitation.
Closing : « You don't have to feel the dialogue. Showing up for the words is already the return. »
Boutons : Repeat slowly / Continue journey / Keep it simple.

---

## 13. Challenges & Belonging

- **7-Day Presence Streak** — « Missed days don't reset you to zero. »
- **21-Day Rhythm** — 2 pauses/jour.
- **The Two-Minute Legacy** — version adoucie du « سباق الساعتين » : héritage silencieux, pas de course.
- Communauté anonyme opt-in : feed « I returned today », sans photos/noms, modération stricte.
- « Find a local community » : mosquées/groupes US, opt-in géoloc.
- Témoignages vidéo 30–45 s, ton réel, « it may not be for everyone ».

---

## 14. User stories (epics)

- **A — Onboarding** : dire comment je me sens sans jugement · choisir 1–2 pauses · disclaimer clair/988 · témoignage réel.
- **B — Daily Returns** : voir Today's Returns · démarrer en 1 tap · sortir à tout moment · guidage calme · cocher même imparfait.
- **C — Inner Weather & Journal** : mood avant/après · question ouverte skippable · privé par défaut · tendance non alarmiste.
- **D — Tradition layer** : Keep it simple toujours visible · déblocage progressif · rien d'imposé.
- **E — Belonging** : challenges sans reset humiliant · communauté opt-in · communauté locale optionnelle.
- **F — Confiance & crise** : bouton 988 permanent · pas de vente de données · export/suppression.

---

## 15. Schéma de données (si backend)

- **users** : id, created_at, locale, timezone, rhythm (beginner|full|custom), tradition_layer (off|gradual|on), notif_level, audio_voice, onboarded_at, crisis_ack_at
- **user_settings** : start_with_1_or_2, show_arabic, qibla_enabled, journal_sync (default false)
- **returns** : id, user_id, date, slot, started_at, completed_at, duration_sec, mode (guided|silent), skipped, stations_completed (json)
- **moods** : id, user_id, return_id, moment (before|after|daily), score 1–5, label, created_at
- **journal_entries** : id, user_id, return_id, prompt_key, body, presence_stars, created_at (chiffrement client recommandé)
- **unlocks** : user_id, item_key, unlocked_at, source (manual|day_3|day_7|day_14)
- **challenges** : id, user_id, type, started_at, target, progress, status — missed day ≠ reset
- **community_posts** (opt-in) : id, user_id_hash, body, created_at, moderated
- **content_cms** : key, locale, title, body, verse_ref, hadith_ref, study_ref, layer (light|tradition)
- **analytics_events** (anonymisés) : onboarding_complete, pause_started, pause_completed, mood_logged, layer_opened, crisis_tapped — **jamais le texte du journal**

> **Note intégration** : l'app actuelle est déjà natif Kotlin + Room/local-first. Ces tables correspondent à des entités Room existantes ou à ajouter (Returns ≈ DailyPrayerState, Moods ≈ à créer, Journal ≈ DivineJournalScreen).

---

## 16. Plan de lancement US

**Hooks** (ouvrir avec le malaise, pas l'islam) :
- "I have friends and still feel alone."
- "I close Instagram and feel worse."
- "My mind won't stop at 1am."
- "I don't need another productivity hack. I need a place to come back to."
- "Five pauses. Not a new religion on day one. A rhythm."

**Contenus TikTok/Reels/Shorts** : the 4-minute midday interrupt · loneliness in the body + « a structured pause is not therapy, but it is a hinge » · convert story réel · science card (Murthy 15 cigarettes, disclaimer à l'écran) · « Missed days don't reset you to zero » · split test Presence vs Dhawq.

**Canaux (ordre)** : 1. organique musulman US (groupes FB converts/MSA/reverts, Discord, newsletters masjid) · 2. micro-influence convertis · 3. TikTok/IG santé mentale + anti-scroll · 4. ads lookalikes (prayer for anxiety, daily ritual) · 5. landing 1 écran + témoignage + disclaimer + 988.

**Landing copy** :
- Title : You don't need a new personality. You need a daily return.
- Body : Five short pauses. An ancient daily rhythm. Used for 1,400 years. Now also studied for attention, mood, and meaning.
- Not therapy. Not a replacement for 988 or a licensed clinician.
- CTA : Begin with one pause today. · Secondary : Muslim? Unlock the original tradition anytime.

**Interdit au lancement** : « The Creator's solution for America » · « 80 years = 2 divine minutes, will you give them back? » · claims de guérison · guilt streaks / « you missed Fajr ».

**KPI 90 jours** : 1ère pause < 24h · Rétention J7 ≥ 25–35 % · % ouverture Explore · mood after > before · crisis tap suivi · reviews « calm / not preachy / I came back ».

---

## 17. Scripts audio — 5 pauses beginner (voix US calme)

Consignes : lente, graves légères, sourire sans énergie coach, silences réels, 4–7 min, pistes tradition séparées.

- **[Morning Return] ~5:30** — « Find a spot. Sitting is enough. / Let the shoulders drop. / Notice the air in the room. / Inhale for four… exhale for six. / Again. You don't have to feel spiritual. You only have to arrive. / If the mind is already running the day… that's normal. Come back to the next breath. / This morning is not a performance. / One more slow exhale. / You showed up. That's the return. / When you're ready, open your eyes. The day can start from here. »
- **[Midday Pause] ~4:30** — « You can do this at a desk. Phone face down if you can. / Feet on the floor. / One breath in… longer breath out. / The scroll can wait four minutes. / Notice one sensation — jaw, chest, hands. / You are allowed to stop producing for this moment. / If irritation shows up, let it sit next to you. Don't fix it. / Three breaths. Then return to the next task, a little less fused to it. »
- **[Afternoon Reset] ~5:00** — « Afternoons get noisy inside. That's why this pause exists. / Unclench the jaw. Drop the tongue from the roof of the mouth. / Inhale… exhale longer. / Ask, quietly: what am I carrying that is not due right now? / You don't need an answer. Just the question. / Stay for four breaths. / This is a reset, not a solution to the whole day. / When you stand up, take one slower step than usual. »
- **[Sunset Presence] ~6:00** — « The light is changing. Let that be the cue. / Sit or stand by a window if you can. / Watch one thing: sky, wall, steam from a cup. / Breathe. / The day is turning whether you hustle or not. / Name one thing that happened today that was not a disaster. Small is enough. / Two minutes of not improving yourself. / Close with three breaths. Peace does not have to be dramatic. »
- **[Night Return] ~6:30** — « Before the feed, or even after it — you can still return. / Lights lower if possible. / Body heavy in the chair or on the bed, sitting is fine. / Inhale four… exhale six. / The nervous system has been on all day. You are allowed to get off. / If anxiety about tomorrow arrives, say: not now. Then the breath. / You don't have to empty your mind. Just stop feeding it for a few minutes. / Last three breaths. / You can sleep. Or rest. Both count. / The pauses will be here in the morning. »

**Piste crise (in-app)** : « If you are in immediate danger or thinking of hurting yourself, please stop the exercise and contact 988 in the US, or local emergency services. You matter more than this recording. »

---

## 18. Micro-copy universel

Continue · Begin this pause · Finish this return · Skip for now · Keep it simple · Explore the tradition · Not now · Save & continue · I showed up · How do you feel now? · What shifted, even a little?

**États anti-honte** :
- 0/5 : « Some days are survival days. The rhythm waits. »
- Missed slot : « You missed one. The next one is still yours. »
- First pause : « Imperfect counts. Start the timer when you're ready. »
- Skip ×3 : « Want fewer reminders? We can go gentler. »
- Journal vide : « Nothing to say is still data. You showed up. »
- Unlock tradition : « Optional. The pauses already work. »
- Community off : « You can stay private. Belonging is a choice. »

**Paywall (plus tard)** : Free = 5 returns, journal, mood, guidage de base. Presence+ = audio premium, programmes 21/30 jours, bibliothèque tradition étendue, packs offline. Copy : « The pauses themselves stay free. Always. »

**Crisis overlay** : Title « Need help now? » · 988 Suicide & Crisis Lifeline (US) · NAMI / IASP · « You matter. This app cannot replace a human in an emergency. »

---

## 19. Wireframes textuels

**Dashboard** : [Header: Today's Returns · 3/5] · [5 cartes: Morning · Midday · Afternoon · Sunset · Night — checkbox + icône météo intérieure] · [Widget Inner Weather] · [Minutes this week: 47] · [insight: « Evenings work best for you »] · [Bottom nav: Home · Journal · Explore · Profile]

**Live Companion (Midday)** : [Timer discret 0:00 / ~6 min] · [Grande zone texte/audio] · [Progress dots des micro-stations] · [Pause / End anytime]

**Weekly Report** : [Titre: This week you returned 19 times] · [2 graphiques: minutes + mood before/after] · [Encadré science + 13:28] · [CTA: Gentle intention]

**Explore** : liste progressive avec cadenas, premier item déverrouillé : « Why five pauses? »
