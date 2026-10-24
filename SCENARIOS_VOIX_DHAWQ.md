# Scénarios voix — Dhawq · Saison 1 : Le Voyage de la Prière
## Scripts d'enregistrement des 10 stations (arabe + anglais + français)

*24 octobre 2026 — document de production*

> **✅ ÉTAT : prototype vocal TRILINGUE COMPLET.**
> - **Arabe : 10/10** pistes générées (TTS, voix masculine choisie) — `res/raw/dhawq_station_01..10.mp3`
> - **Anglais : 10/10** pistes générées (TTS, voix masculine choisie) — `res/raw/dhawq_station_01..10_en.mp3`
> - **Français : 10/10** pistes générées (TTS, voix féminine choisie) — `res/raw/dhawq_station_01..10_fr.mp3`
> - Ces pistes TTS sont des **prototypes fonctionnels** : elles rendent le Voyage jouable dès maintenant. L'enregistrement humain définitif (récitant) remplacera ces fichiers aux mêmes noms, sans toucher au code.

---

## 1. Notes de production

### Direction artistique (identique pour les deux langues)

| Paramètre | Consigne |
|---|---|
| **Ton** | Bas, calme, intime — comme si on parlait à une seule personne à 3 h du matin. Jamais « prêcheur », jamais théâtral. |
| **Rythme** | Lent. ~120 mots/minute. Silences de 1-2 secondes entre les paragraphes (le silence fait partie du script). |
| **Distance** | Micro rapproché, chuchoté léger. L'auditeur a des écouteurs : on parle DANS l'oreille, pas devant une salle. |
| **Musique de fond** | **Aucune.** Voix seule, par design (le silence est le décor de Dhawq). |
| **Verset / hadith** | Ralenti net + micro-pause avant et après. Chaque citation porte sa référence à l'oral (« رواه مسلم », « reported by Muslim »). |

### Fichiers techniques

- Format : MP3 mono, 44,1 kHz, normalisation ≈ −16 LUFS, silence ambiant ≤ −60 dB.
- Nommage (déposés dans `app/src/main/res/raw/`) :

| Langue | Fichier | Reprise du lecteur |
|---|---|---|
| **Arabe** (défaut) | `dhawq_station_01.mp3` … `dhawq_station_10.mp3` | automatique dès le dépôt |
| **Anglais** | `dhawq_station_01_en.mp3` … `dhawq_station_10_en.mp3` | automatique |
| Français (à venir) | `dhawq_station_01_fr.mp3` … | automatique |

- Tant qu'un fichier n'existe pas, l'écran affiche la puce « Enregistrement à venir » — jamais un lecteur cassé.
- **Les scripts ci-dessous sont la version courte (~1 min 30)**. La version humaine définitive pourra les étoffer vers 2-4 min (les hadiths listés sont le socle ; le comité théologique valide avant enregistrement).

---

## 2. Les scripts — ARABE (النسخة العربية)

### المحطة 1 — الماء (L'Eau)

> بسم الله الرحمن الرحيم. المحطة الأولى: الماء.
> قبل أن تقف بين يدي الملك، تتوضأ. تأمل: لماذا الماء؟ قال تعالى: ﴿إِنَّ اللَّهَ يُحِبُّ التَّوَّابِينَ وَيُحِبُّ الْمُتَطَهِّرِينَ﴾. الوضوء ليس غسلاً للجسد وحده، بل تحضيرٌ للقلب. مع كل قطرة، اغسل عن قلبك همّ الدنيا.
> حين تغسل يديك، اسأل: إلى ماذا امتدّت يداي اليوم؟ فاغسلهما بنية التوبة. حين تغسل وجهك، تذكّر عينيك اللتين ستقفان غدًا بين يديه. حين تمسح رأسك، أخضِع له ما افتخرتَ به. حين تغسل قدميك، اسأل: إلى أين سأمشي بعد صلاتي؟ فطهِّر وجهتك.
> قال النبي ﷺ: «إذا توضأ العبد المسلم فغسل وجهه، خرج من وجهه كل خطيئة نظرتها عيناه، حتى يخرج نقيًّا من الذنوب». رواه مسلم.
> الآن قم إلى وضوئك بسكينة، وقل: اللهم اجعلني من التوّابين، واجعلني من المتطهّرين. ثم امشِ إلى موعدك.

### المحطة 2 — المسير (La Marche)

> المحطة الثانية: المسير.
> أنت الآن ذاهب إلى موعد. ليس إلى مهمة، بل إلى دعوة من ملك الملوك. قال تعالى: ﴿يَا أَيُّهَا الَّذِينَ آمَنُوا إِذَا نُودِيَ لِلصَّلَاةِ… فَاسْعَوْا إِلَى ذِكْرِ اللَّهِ﴾.
> كل خطوة محسوبة. قال النبي ﷺ: «من توضأ فأحسن الوضوء، ثم خرج إلى المسجد، لا يخرجه إلا الصلاة، لم يخطُ خطوة إلا رُفعت له بها درجة، وحُطّت عنه بها خطيئة». رواه مسلم.
> وإذا سمعت الإقامة فامشِ على سكينتك، فقد قال ﷺ: «إذا سمعتم الإقامة فامشوا إلى الصلاة، وعليكم السكينة، وأتوا ما تدركون».
> في الطريق، لا تلتفت إلى الدنيا. أنت ذاهب إلى من قال: «أنا عند ظنّ عبدي بي، وأنا معه إذا ذكرني». اجعل كل خطوة تقول: لبيك اللهم لبيك.
> الآن ارفع قلبك قبل قدمك، وامشِ.

### المحطة 3 — النداء (L'Appel)

> المحطة الثالثة: النداء.
> «حيّ على الصلاة، حيّ على الفلاح». ليست أغنية في الشارع، بل دعوة شخصية من ربّ السماء. لو وصلك خطاب من ملك الأرض يدعوك إلى قصره، لطارت الدنيا من الفرح. فكيف بدعوة من خالق الملوك، خمس مرات كل يوم؟
> قال النبي ﷺ: «لو يعلم الناس ما في النداء والصف الأول، ثم لم يجدوا إلا أن يستهموا عليه لاستهموا». متفق عليه.
> وبعد الأذان، رُدّ على الدعوة: «اللهم ربّ هذه الدعوة التامة، والصلاة القائمة، آتِ محمدًا الوسيلة والفضيلة، وابعثه مقامًا محمودًا الذي وعدته». قال ﷺ: «من قال ذلك حين يسمع النداء، حلّت له شفاعتي يوم القيامة». رواه البخاري.
> الآن، أجب النداء. فهو يناديك باسمك: حيّ على الصلاة.

### المحطة 4 — الباب (La Porte)

> المحطة الرابعة: الباب.
> «الله أكبر». كلمتان خفيفتان على اللسان، ثقيلتان في الميزان. حين تقولهما وترفع يديك، فأنت تُلقي الدنيا كلها خلف ظهرك: هاتفك، عملك، همومك، أحلامك الصغيرة. لا شيء أكبر من الله.
> قال ابن القيّم رحمه الله: معنى «الله أكبر» أنه لا شيء أكبر منه ولا مثيل له. فإذا قلتها فصدّقها بقلبك، وإلا كانت كلمةً بلا روح.
> قال النبي ﷺ: «إن الرجل لينصرف من الصلاة، ولم يُكتب له منها إلا نصفها، أو ثلثها، أو ربعها». رواه أحمد. ما الذي نقص؟ الحضور.
> أنت الآن على الباب. خلع النعلين رمزُ خلع الدنيا. خذ نفسًا عميقًا، قل: «الله أكبر»… وادخل.

### المحطة 5 — الاستفتاح (L'Ouverture)

> المحطة الخامسة: الاستفتاح.
> لا يدخل أحد على الملك إلا بإذن، ولا يبدأ أحد حديثه معه إلا بالثناء. حين كبّرتَ ودخلت، لا تُسرع إلى القراءة. قف لحظة، واثنِ عليه كما علّمنا رسول الله ﷺ.
> كان ﷺ يقول: «سبحانك اللهم وبحمدك، وتبارك اسمك، وتعالى جدُّك، ولا إله غيرك». رواه مسلم.
> وكان ﷺ يقول: «اللهم باعد بيني وبين خطاياي كما باعدت بين المشرق والمغرب». رواه البخاري.
> هذه الكلمات ليست تمهيدًا شكليًّا. إنها تذكيرٌ لقلبك: مع من أنت واقف؟ في ضيافة من أنت؟ تخيّل لو كنت تعلم أن الله ينظر إليك الآن. هو ينظر. فأطل الثناء، وأنت واقف بين يديه، يسمعك، أقرب إليك من حبل الوريد.
> قل الآن بلسانك، وأطل الوقوف بقلبك.

### المحطة 6 — الحوار (Le Dialogue)

> المحطة السادسة: الحوار.
> هذه أعجب لحظة في صلاتك كلها. قال رسول الله ﷺ فيما يرويه عن ربه: «قال الله تعالى: قسمتُ الصلاة بيني وبين عبدي نصفين. فإذا قال الحمد لله رب العالمين، قال الله: حمدني عبدي. وإذا قال الرحمن الرحيم، قال: أثنى عليّ عبدي. وإذا قال مالك يوم الدين، قال: مجّدني عبدي. فإذا قال إياك نعبد وإياك نستعين، قال: هذا بيني وبين عبدي. وإذا قال اهدنا الصراط المستقيم، قال: هذا لعبدي وله ما سأل». رواه مسلم.
> الفاتحة ليست تلاوةً تُسرع بها. إنها حوار: أنت تتكلم، وهو يجيب. فمهّل بعد كل آية لحظة، وتخيّل جواب ربّك.
> من قرأ الفاتحة ولم يعش الحوار، فكأنه ألقى رسالة ولم ينتظر الردّ.
> الآن، اقرأها كأنك تسمعه.

### المحطة 7 — الركوع (L'Inclinaison)

> المحطة السابعة: الركوع.
> الآن ينحني الجسد. لكن الانحناء الحقيقي في القلب. حين تقول: «سبحان ربي العظيم»، فأنت تشهد أن كل عظمة في هذا العالم — من مُلكٍ وجاهٍ وعلمٍ وجمال — هي من عظمته، وتضمحل أمام عظمته.
> قال تعالى: ﴿فَسَبِّحْ بِاسْمِ رَبِّكَ الْعَظِيمِ﴾. وفي الحديث الذي رواه مسلم: «أما الركوع فعظّموا فيه الرب».
> كان النبي ﷺ يقول في ركوعه: «اللهم لك ركعت، وبك آمنت، ولك أسلمت، خشع لك سمعي وبصري ومخّي وعظمي». رواه مسلم.
> فحين تركع، قلها بهدوء ثلاثًا، ثم زد: «لك ركعت»… وأشهد قلبك أن العظمة كلها له.
> الآن اركع، وأطل.

### المحطة 8 — القرب (La Proximité)

> المحطة الثامنة: القرب.
> قال رسول الله ﷺ: «أقربُ ما يكون العبد من ربه وهو ساجد، فأكثروا الدعاء». رواه مسلم.
> أقرب لحظة في حياتك كلها هي هذه: جبهتك على الأرض، وربّك يسمعك. لا سكرتير، ولا مواعيد، ولا واسطة. أنت… وهو.
> في سجودك، كلّمه بلغتك. لا تحتاج فصاحة. جرحك، حلمك، ذنبك، أمّك المريضة، قلبك القاسي: ضعها كلها هناك. فقد كان ﷺ يقول في سجوده: «اللهم اغفر لي ذنبي كله، دقّه وجلّه، وأوله وآخره، وسرّه وعلانيته». رواه مسلم.
> أسجدَ النبيُّ ﷺ سجودًا طويلًا حتى قالت عائشة رضي الله عنها: حتى ظننتُ أنه قد حدث أمر.
> الليلة، خصّص له سجدة. دمعت عيناك مرة هناك؟ تلك دمعةٌ يشهد لها الملك.
> الآن اسجد، وادعُ… فهو أقرب إليك في هذه اللحظة من كل شيء.

### المحطة 9 — التشهد (Le Salut)

> المحطة التاسعة: التشهد.
> الآن تجلس، وتُشهِد. قال ابن مسعود رضي الله عنه: علّمني رسول الله ﷺ التشهد، كفيّ يعلمي الكتابة: «التحيات لله والصلوات والطيبات، السلام عليك أيها النبي ورحمة الله وبركاته». رواه البخاري.
> تخيّل: أنت تُسلّم على النبي ﷺ، وهو يردّ عليك، كما أخبر: «ما من أحد يسلّم عليّ إلا ردّ الله عليّ روحي حتى أردّ عليه السلام».
> ثم الصلاة الإبراهيمية: «اللهم صلِّ على محمد وعلى آل محمد، كما صليت على إبراهيم وعلى آل إبراهيم». ومن صلّى عليّ صلاة، صلّى الله عليه بها عشرًا.
> وفي النهاية تُسلّم عن يمينك وشمالك: على الملائكة الموكّلين بك، الكاتبين الحاضرين.
> الآن اجلس كما تجلس لوداع حبيب… وأطل الكلام قبل الوداع.

### المحطة 10 — الجلوس الثمين (L'Assise Précieuse)

> المحطة العاشرة: الجلوس الثمين.
> صلاتك انتهت، وكنزك لم يبدأ. لا تفزع إلى هاتفك فور التسليم. ابقَ جالسًا.
> كان النبي ﷺ إذا انصرف من صلاته استغفر ثلاثًا، وقال: «اللهم أنت السلام ومنك السلام، تباركت يا ذا الجلال والإكرام». رواه مسلم.
> ثم قل: «لا إله إلا الله وحده لا شريك له، له الملك وله الحمد وهو على كل شيء قدير».
> هذه الدقائق هي عصارة الصلاة. من أذاقك حلاوتها؟ هو. فاسأله أن يُذيقك حلاوة الصلة به.
> ثم قم إلى دنياك وأنت تحمل سكينةَ ساعة. وستعود بعد ساعات إلى موعد جديد: إنه يدعوك.
> نلتقي في المحطة القادمة. والآن… اذهب، وصلِّ.

---

## 3. Les scripts — ANGLAIS (English version)

### Station 1 — The Water

> In the name of Allah, the Most Gracious, the Most Merciful. Station one: The Water.
> Before you stand before the King, you wash. But ask yourself: why water? Allah says: "Indeed, Allah loves those who repent, and He loves those who purify themselves." Wudu is not a wash for the body alone — it is preparation for the heart. With every drop, wash the weight of this world off your heart.
> When you wash your hands, ask: what did my hands reach for today? Wash them with the intention of repentance. When you wash your face, remember the eyes that will one day stand before Him. When you wipe your head, submit to Him whatever you took pride in. When you wash your feet, ask: where will I walk after this prayer? Purify your destination.
> The Prophet ﷺ said: "When a believing servant washes his face, every sin his eyes ever looked at leaves with the water, until he emerges pure." (Muslim)
> Now rise to your wudu with stillness, and say: O Allah, make me among those who repent, and among those who purify themselves. Then walk to your appointment.

### Station 2 — The Walk

> Station two: The Walk.
> You are going to an appointment. Not to a task — to an invitation from the King of kings. Allah says: "O you who believe, when the call is made for prayer… hasten to the remembrance of Allah."
> Every step is counted. The Prophet ﷺ said: "Whoever performs wudu well, then leaves for the mosque — with nothing driving him out but the prayer — does not take a step without being raised a degree by it, and a sin wiped away by it." (Muslim)
> And when you hear the iqama, walk with stillness. The Prophet ﷺ said: "When you hear the iqama, walk to the prayer with calmness and dignity, and join whatever you catch."
> On the way, do not turn back to the world. You are going to the One who said: "I am as My servant thinks of Me, and I am with him when he remembers Me." Let every step say: here I am, O Allah, here I am.
> Now raise your heart before your feet — and walk.

### Station 3 — The Call

> Station three: The Call.
> "Hayya 'ala as-salah. Hayya 'ala al-falah." Come to prayer. Come to success. This is not a song in the street. It is a personal invitation from the Lord of the heavens. If a letter arrived from the king of this earth inviting you to his palace, the whole world would glow with joy. So what about an invitation from the Creator of kings — five times, every single day?
> The Prophet ﷺ said: "If people knew what is in the call to prayer and the first row, and found no way to it except by drawing lots, they would draw lots for it." (Bukhari & Muslim)
> After the adhan, answer the invitation: "O Allah, Lord of this perfect call and the prayer to be offered, grant Muhammad the intercession and the highest rank, and raise him to the praiseworthy position You promised him." The Prophet ﷺ said: "Whoever says this when he hears the call, my intercession will be granted to him on the Day of Resurrection." (Bukhari)
> Now answer the Call. He is calling you by name: come to prayer.

### Station 4 — The Door

> Station four: The Door.
> "Allahu akbar." Two words, light on the tongue, heavy on the scales. When you say them and raise your hands, you are throwing the entire world behind your back: your phone, your work, your worries, your small dreams. Nothing is greater than Allah.
> Ibn al-Qayyim said: the meaning of Allahu akbar is that nothing is greater than Him, and nothing is like Him. So when you say it, mean it with your heart — otherwise it is a word without a soul.
> The Prophet ﷺ said: "A man may leave the prayer with only half of it written for him — or a third, or a quarter." (Ahmad) What was missing? Presence.
> You are at the door now. Taking off your sandals is the symbol of taking off the world. Take a deep breath. Say: Allahu akbar… and enter.

### Station 5 — The Opening

> Station five: The Opening.
> No one enters upon a king without permission, and no one begins speaking to him without praise. You have said the takbir and entered — do not rush into recitation. Stand a moment, and praise Him the way the Messenger of Allah ﷺ taught us.
> He ﷺ used to say: "Subhanaka Allahumma wa bihamdika, wa tabarakasmuka, wa ta'ala jadduka, wa la ilaha ghayruk." (Muslim)
> And he ﷺ used to say: "O Allah, put a distance between me and my sins as great as the distance You have placed between the East and the West." (Bukhari)
> These words are not a formality. They are a reminder to your heart: before whom are you standing? In whose hospitality are you? Imagine if you knew that Allah is looking at you right now. He is looking. So make the praise long — you are standing before Him, He hears you, nearer to you than your jugular vein.
> Say it now with your tongue, and lengthen the standing with your heart.

### Station 6 — The Dialogue

> Station six: The Dialogue.
> This is the most astonishing moment of your entire prayer. The Messenger of Allah ﷺ said, in what he narrates from his Lord: "Allah said: I have divided the prayer into two halves, between Me and My servant. When he says 'al-hamdu lillahi rabbi l-'alamin,' Allah says: My servant has praised Me. When he says 'ar-rahmani r-rahim,' Allah says: My servant has extolled Me. When he says 'maliki yawmi d-din,' Allah says: My servant has glorified Me. When he says 'iyyaka na'budu wa iyyaka nasta'in,' Allah says: this is between Me and My servant. And when he says 'ihdina s-sirata l-mustaqim,' Allah says: this is for My servant, and My servant shall have what he asked for." (Muslim)
> Al-Fatiha is not a recitation to rush through. It is a dialogue: you speak, and He answers. So pause for a moment after every verse, and imagine your Lord's reply.
> Whoever recites Al-Fatiha without living the dialogue has sent a letter and left before the reply.
> Now recite it as though you can hear Him.

### Station 7 — The Bowing

> Station seven: The Bowing.
> Now the body folds. But the true bowing happens in the heart. When you say "Subhana Rabbiya l-'Azim" — Glory to my Lord, the Magnificent — you are testifying that every greatness in this world, every kingdom, every fortune, every beauty, comes from His greatness, and melts away before it.
> Allah says: "So glorify the name of your Lord, the Most Great." And in the narration of Muslim: "As for the bowing, magnify the Lord in it."
> The Prophet ﷺ used to say in his bowing: "O Allah, to You I have bowed, in You I have believed, and to You I have submitted. My hearing, my sight, my mind and my bones are humbled before You." (Muslim)
> So when you bow tonight, say it slowly, three times — then add: "to You I have bowed"… and let your heart witness that all greatness belongs to Him.
> Now bow, and make it long.

### Station 8 — The Nearness

> Station eight: The Nearness.
> The Messenger of Allah ﷺ said: "The closest a servant ever is to his Lord is while he is prostrating, so increase your supplications." (Muslim)
> The closest moment of your entire life is this one: your forehead on the ground, and your Lord listening. No secretary, no appointments, no intermediary. You… and Him.
> In your prostration, speak to Him in your own words. You do not need eloquence. Your wound, your dream, your sin, your sick mother, your hardened heart — lay all of it there. The Prophet ﷺ used to say in his prostration: "O Allah, forgive me all my sin, the small and the great, the first and the last, the hidden and the open." (Muslim)
> He ﷺ would prostrate so long that 'A'ishah thought something had happened.
> Tonight, set aside one prostration just for Him. Have your eyes ever shed a tear down there? That is a tear the angels themselves witness.
> Now prostrate, and ask — for at this moment He is nearer to you than anything.

### Station 9 — The Greeting

> Station nine: The Greeting.
> Now you sit, and you testify. Ibn Mas'ud, may Allah be pleased with him, said: the Messenger of Allah ﷺ taught me the tashahhud, teaching me as he would teach me to write: "At-tahiyyatu lillah, wa s-salawatu wa t-tayyibat. As-salamu 'alayka ayyuha n-nabiyyu wa rahmatullahi wa barakatuh." (Bukhari)
> Imagine it: you are sending peace upon the Prophet ﷺ — and he answers you, as he told us: "There is no one who sends peace upon me without Allah returning my soul to me, so that I may return the greeting."
> Then the salawat: "O Allah, send blessings upon Muhammad and the family of Muhammad, as You blessed Ibrahim and the family of Ibrahim." Whoever sends one blessing upon me, Allah sends ten upon him.
> And at the very end, you turn to your right and your left: greeting the angels appointed over you — the scribes, present, writing.
> Now sit as you would sit with a beloved at farewell… and make the words long before the goodbye.

### Station 10 — The Precious Sitting

> Station ten: The Precious Sitting.
> Your prayer has ended — and your treasure has not yet begun. Do not lunge for your phone the moment you say the salam. Stay seated.
> When the Prophet ﷺ finished his prayer, he would seek forgiveness three times, then say: "O Allah, You are Peace, and from You comes peace. Blessed are You, Owner of Majesty and Honor." (Muslim)
> Then say: "There is no god but Allah alone, no partner has He. His is the kingdom, and His is the praise, and He is able to do all things."
> These few minutes are the essence of the prayer. Who gave you a taste of its sweetness? He did. So ask Him to let you taste the sweetness of closeness to Him.
> Then return to your world, carrying an hour's worth of stillness. In a few hours you will come back to a new appointment: He is calling you again.
> We will meet at the next station. And now… go, and pray.

---

## 4. Les scripts — FRANÇAIS (version française)

### Station 1 — L'Eau

> Au nom d'Allah, le Tout Miséricordieux, le Très Miséricordieux. Première station : l'Eau. Avant de te tenir devant le Roi, tu fais tes ablutions. Demande-toi : pourquoi l'eau ? Allah dit : « Certes, Allah aime ceux qui reviennent à Lui, et Il aime ceux qui se purifient. » Le wudū' n'est pas une toilette du corps seul : c'est une préparation du cœur. À chaque goutte, lave le poids de ce monde loin de ton cœur. Quand tu laves tes mains, demande : vers quoi mes mains se sont-elles tendues aujourd'hui ? Lave-les avec l'intention du repentir. Quand tu laves ton visage, souviens-toi de ces yeux qui se tiendront un jour devant Lui. Quand tu essuies ta tête, soumets-Lui ce dont tu fus fier. Quand tu laves tes pieds, demande : où marcherai-je après cette prière ? Purifie ta destination. Le Prophète ﷺ a dit : « Quand le serviteur croyant se lave le visage, chaque faute regardée par ses yeux s'en va avec l'eau, jusqu'à ce qu'il en ressorte pur de ses fautes. » Rapporté par Muslim. Maintenant, lève-toi vers ton wudū' avec quiétude, et dis : Ô Allah, fais-moi parmi ceux qui reviennent à Toi, et parmi ceux qui se purifient. Puis marche vers ton rendez-vous.

### Station 2 — La Marche

> Deuxième station : la Marche. Tu vas à un rendez-vous. Pas à une tâche — à une invitation du Roi des rois. Allah dit : « Ô vous qui croyez, quand on appelle à la prière… hâtez-vous vers le rappel d'Allah. » Chaque pas est compté. Le Prophète ﷺ a dit : « Quiconque fait ses ablutions parfaitement, puis sort vers la mosquée, sans être poussé que par la prière, ne fait pas un pas sans qu'un degré ne lui soit élevé et une faute effacée. » Rapporté par Muslim. Et quand tu entends l'iqāma, marche avec quiétude : « Quand vous entendez l'iqāma, marchez vers la prière avec calme et dignité, et rejoignez ce que vous attrapez. » En chemin, ne te retourne pas vers le monde. Tu vas vers Celui qui a dit : « Je suis selon l'attente que Mon serviteur a de Moi, et Je suis avec lui quand il M'évoque. » Que chaque pas dise : me voici, Ô Allah, me voici. Maintenant, élève ton cœur avant tes pieds — et marche.

### Station 3 — L'Appel

> Troisième station : l'Appel. « Ḥayya 'ala ṣ-ṣalāh, ḥayya 'ala al-falāh » — viens à la prière, viens à la réussite. Ce n'est pas une chanson dans la rue : c'est une invitation personnelle du Seigneur des cieux. Si une lettre du roi de cette terre t'invitait à son palais, le monde entier brillerait de joie. Alors qu'en est-il d'une invitation du Créateur des rois — cinq fois, chaque jour ? Le Prophète ﷺ a dit : « Si les gens savaient ce qu'il y a dans l'appel à la prière et dans le premier rang, et ne trouvaient d'autre moyen que le tirage au sort, ils tireraient au sort. » Rapporté par al-Bukhârî et Muslim. Après l'adhān, réponds à l'invitation : « Ô Allah, Seigneur de cet appel parfait et de la prière établie, accorde à Muḥammad l'intercession et l'excellence, et élève-le à la position louable que Tu lui as promise. » Le Prophète ﷺ a dit : « Quiconque dit cela en entendant l'appel, mon intercession lui sera accordée au Jour de la Résurrection. » Rapporté par al-Bukhârî. Maintenant, réponds à l'Appel. Il t'appelle par ton nom : viens à la prière.

### Station 4 — La Porte

> Quatrième station : la Porte. « Allāhu akbar. » Deux mots, légers sur la langue, lourds dans la balance. Quand tu les dis et que tu élèves tes mains, tu jettes le monde entier derrière ton dos : ton téléphone, ton travail, tes soucis, tes petits rêves. Rien n'est plus grand qu'Allah. Ibn al-Qayyim a dit : le sens d'Allāhu akbar est que rien n'est plus grand que Lui, et rien ne Lui ressemble. Quand tu le dis, crois-le avec ton cœur — sinon c'est une parole sans âme. Le Prophète ﷺ a dit : « Il arrive qu'un homme quitte la prière sans qu'il lui en soit écrit que la moitié, ou le tiers, ou le quart. » Rapporté par Ahmad. Qu'est-ce qui manquait ? La présence. Tu es à la porte maintenant. Ôter ses sandales est le symbole d'ôter le monde. Respire profondément. Dis : Allāhu akbar… et entre.

### Station 5 — L'Ouverture

> Cinquième station : l'Ouverture. Nul n'entre chez un roi sans permission, et nul ne commence à lui parler sans louange. Tu as dit le takbīr, tu es entré — ne te précipite pas dans la récitation. Tiens-toi un instant, et loue-Le comme le Messager d'Allah ﷺ nous l'a enseigné. Il disait : « Subḥānaka Allāhumma wa biḥamdika, wa tabāraka smuka, wa ta'ālā jadduka, wa lā ilāha ghayruk. » Rapporté par Muslim. Et il disait : « Ô Allah, éloigne de moi mes fautes comme Tu as éloigné l'Orient de l'Occident. » Rapporté par al-Bukhârî. Ces paroles ne sont pas un formalisme. Elles rappellent à ton cœur : devant qui es-tu debout ? En quelle hospitalité es-tu ? Imagine que tu saches qu'Allah te regarde en cet instant. Il te regarde. Alors allonge la louange — tu es debout devant Lui, Il t'entend, plus proche de toi que ta veine jugulaire. Dis-le maintenant avec ta langue, et allonge la station avec ton cœur.

### Station 6 — Le Dialogue

> Sixième station : le Dialogue. C'est le moment le plus étonnant de toute ta prière. Le Messager d'Allah ﷺ a dit, rapportant de son Seigneur : « Allah a dit : J'ai partagé la prière entre Moi et Mon serviteur, en deux moitiés. Quand il dit « al-ḥamdu lillāhi rabbi l-'ālamīn », Allah dit : Mon serviteur M'a loué. Quand il dit « ar-raḥmāni r-raḥīm », Allah dit : Mon serviteur M'a exalté. Quand il dit « māliki yawmi d-dīn », Allah dit : Mon serviteur M'a glorifié. Quand il dit « iyyāka na'budu wa iyyāka nasta'īn », Allah dit : ceci est entre Moi et Mon serviteur. Et quand il dit « ihdinā ṣ-ṣirāṭa l-mustaqīm », Allah dit : ceci est pour Mon serviteur, et Mon serviteur aura ce qu'il a demandé. » Rapporté par Muslim. Al-Fātiḥa n'est pas une récitation à précipiter. C'est un dialogue : tu parles, et Il répond. Pause après chaque verset, et imagine la réponse de ton Seigneur. Celui qui récite Al-Fātiḥa sans vivre le dialogue a posté une lettre et est parti avant la réponse. Maintenant, récite-la comme si tu L'entendais.

### Station 7 — L'Inclinaison

> Septième station : l'Inclinaison. Maintenant le corps se plie. Mais la vraie inclinaison se joue dans le cœur. Quand tu dis « Subḥāna Rabbiya l-'Aẓīm » — Gloire à mon Seigneur le Magnifique — tu témoignes que toute grandeur en ce monde, tout royaume, toute fortune, toute beauté, vient de Sa grandeur, et fond devant elle. Allah dit : « Glorifie le nom de ton Seigneur, le Très Grand. » Et dans le récit de Muslim : « Quant à l'inclinaison, magnifiez-y le Seigneur. » Le Prophète ﷺ disait dans son inclinaison : « Ô Allah, c'est pour Toi que je me suis incliné, en Toi j'ai cru, et à Toi je me suis soumis. Mon ouïe, ma vue, ma pensée et mes os s'humilient devant Toi. » Rapporté par Muslim. Quand tu t'inclines ce soir, dis-le lentement, trois fois — puis ajoute : « c'est pour Toi que je me suis incliné »… et que ton cœur témoigne que toute grandeur Lui appartient. Incline-toi maintenant, et allonge.

### Station 8 — La Proximité

> Huitième station : la Proximité. Le Messager d'Allah ﷺ a dit : « Le serviteur n'est jamais aussi proche de son Seigneur que lorsqu'il est en prosternation : multipliez donc les invocations. » Rapporté par Muslim. Le moment le plus proche de toute ta vie, c'est celui-ci : ton front au sol, et ton Seigneur qui t'écoute. Pas de secrétaire, pas de rendez-vous, pas d'intermédiaire. Toi… et Lui. Dans ta prosternation, parle-Lui avec tes mots. Tu n'as pas besoin d'éloquence. Ta blessure, ton rêve, ta faute, ta mère malade, ton cœur endurci : dépose tout là. Le Prophète ﷺ disait dans sa prosternation : « Ô Allah, pardonne-moi toute ma faute, la minime et l'énorme, la première et la dernière, celle qui est secrète et celle qui est publique. » Rapporté par Muslim. Il se prosternait si longtemps que 'Ā'isha pensa qu'il était arrivé quelque chose. Ce soir, réserve-Lui une prosternation. Tes yeux ont-ils versé une larme là-bas, un jour ? C'est une larme que les anges eux-mêmes constatent. Prosterne-toi maintenant, et invoque — car en cet instant, Il est plus proche de toi que tout.

### Station 9 — Le Salut

> Neuvième station : le Salut. Maintenant tu t'assieds, et tu attestes. Ibn Mas'ūd, qu'Allah l'agrée, a dit : le Messager d'Allah ﷺ m'a enseigné le tashahhud comme il m'enseignait l'écriture : « At-taḥiyyātu lillāh, wa ṣ-ṣalawātu wa ṭ-ṭayyibāt. As-salāmu 'alayka ayyuha n-nabiyyu wa raḥmatullāhi wa barakātuh. » Rapporté par al-Bukhârî. Imagine : tu adresses la paix au Prophète ﷺ — et il te répond, comme il nous l'a appris : « Nul ne prie sur moi sans qu'Allah ne me rende mon âme afin que je lui rende son salut. » Puis la prière abrahamique : « Ô Allah, prie sur Muḥammad et sur la famille de Muḥammad, comme Tu as prié sur Ibrāhīm et sur la famille d'Ibrāhīm. » Quiconque prie une fois sur moi, Allah prie dix fois sur lui. Et à la toute fin, tu tournes à droite et à gauche : saluant les anges chargés de toi — les scribes, présents, écrivant. Assieds-toi comme on s'assied pour les adieux d'un bien-aimé… et allonge les paroles avant l'adieu.

### Station 10 — L'Assise Précieuse

> Dixième station : l'Assise Précieuse. Ta prière est terminée — et ton trésor n'a pas commencé. Ne te jettes pas sur ton téléphone dès le salām. Reste assis. Quand le Prophète ﷺ achevait sa prière, il demandait pardon trois fois, puis disait : « Ô Allah, Toi la Paix, et de Toi vient la paix. Béni sois-Tu, Seigneur de Majesté et de Noblesse. » Rapporté par Muslim. Puis dis : « Nulle divinité qu'Allah, seul, sans associé. À Lui la royauté, à Lui la louange, et Il est capable de toute chose. » Ces quelques minutes sont l'essence de la prière. Qui t'a donné d'en goûter la douceur ? Lui. Alors demande-Lui de te faire goûter la douceur de la proximité avec Lui. Puis retourne à ton monde, portant une heure de quiétude. Dans quelques heures, tu reviendras à un nouveau rendez-vous : Il t'appelle encore. Nous nous retrouvons à la prochaine station. Et maintenant… va, et prie.

---

## 5. Sources classiques utilisées (pour le comité théologique)

| Station | Source |
|:---:|---|
| 1 | Coran 2:222 · Muslim (wudū') |
| 2 | Coran 62:9 · Muslim (les pas vers la mosquée) · Bukhari & Muslim (sakīna) · Bukhari & Muslim « أنا عند ظن عبدي » |
| 3 | Bukhari & Muslim (le mérite de l'adhan) · Bukhari (du'ā' après l'adhan) |
| 4 | Ibn al-Qayyim (madārij) · Ahmad (la prière amputée) |
| 5 | Muslim 399 (subhānaka) · Bukhari (bā'id baynī) · Coran 50:16 |
| 6 | **Muslim 395** (hadith qudsi du partage de la Fātiḥa) |
| 7 | Coran 56:96 · Muslim 482 · Muslim (du'ā' du rukū') |
| 8 | **Muslim 482** (la proximité dans le sujūd) · Muslim (ghufrān du sujūd) |
| 9 | Bukhari (tashahhud d'Ibn Mas'ūd) · Abū Dāwūd/Ahmad (la réponse au salām) · Muslim (salawat ×10) |
| 10 | Muslim 591 (istighfār ×3, anta s-salām) · Muslim/Bukhari (la parole après la prière) |

*Règle éditoriale Dhawq : pas de source, pas d'écran. Toute modification de ces textes passe par le comité de validation théologique avant enregistrement définitif.*
