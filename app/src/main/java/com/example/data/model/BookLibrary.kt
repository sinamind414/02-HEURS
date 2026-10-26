// GENERATED FILE - do not hand-edit.
// Source: dawq el salat .txt (the full french book "La Priere Goustee", from the Fahem 36
// episode with Dr. Ahmed Al-Arabi, 23 fevrier 2024 - 2 h 12 min). Regenerate with
// Temp/opencode/gen_book.ps1. All text lives as UTF-8 strings; prose is French in every app language.

package com.example.data.model

/** Book text block: bold (`**`) and italic (`*`) markers are parsed at render time. */
enum class BookLineKind { PARAGRAPH, QUOTE, BULLET }

sealed class BookNode {
    data class Line(val kind: BookLineKind, val text: String) : BookNode()
    data class Table(val cells: List<BookTableCell>) : BookNode()
}

/** One row of the appendice table ("# - Texte - Source", 32 sourced entries). */
data class BookTableCell(val number: Int, val text: String, val source: String)

data class BookChapter(
    val id: String,
    val n: Int?,                 // chapter number, null for prologue/epilogue/appendice
    val partId: String,          // prologue | p1 | p2 | p3 | epilogue
    val title: String,
    val timestamp: String?,      // "01:06:45" - the anchor inside the source episode
    val timeSeconds: Int,        // same anchor, in seconds ($t= for YouTube)
    val station: Int?,           // 1..10 - the Voyage station this chapter belongs to
    val goFatiha: Boolean,       // true = the Fatiha dialogue chapter (hadith qudsi n.395)
    val body: List<BookNode>,
)

data class BookPart(
    val id: String,
    val kindLabel: String,       // "PREMIERE PARTIE" as printed in the book
    val title: String,
    val timestamp: String?,
    val timeSeconds: Int,
)

object BookLibrary {

    const val TITLE = "La Prière Goûtée"
    const val SUBTITLE = "Le livre de l'épisode — صلاة الذوق"
    const val META_ADAPTED = "Adapté de l'épisode « فاهم 36 | سلسلة تذوق العبادات — (1) الصلاة » avec Dr. Ahmed Al-Arabi"
    const val META_SOURCE = "Fahem Podcast · 23 février 2024 · 2 h 12 min · 39,5 millions de vues"
    const val VIDEO_URL = "https://www.youtube.com/watch?v=63_AOCldyXo"

    /** Open the chapter anchor inside the original episode (2 h 12 min). */
    fun passageUrl(clock: String?): String? =
        clock?.let { VIDEO_URL + "&t=" + secondsOf(it) + "s" }

    fun secondsOf(hhmmss: String): Int {
        val bits = hhmmss.split(':')
        if (bits.size != 3) return 0
        return (bits[0].toIntOrNull() ?: 0) * 3600 + (bits[1].toIntOrNull() ?: 0) * 60 + (bits[2].toIntOrNull() ?: 0)
    }

    const val NOTE_OF_LIBRAIRIE = "Ce livre n'est pas une transcription mot à mot de l'épisode : les paroles du Dr. Al-Arabi lui appartiennent, et leur style oral fait leur saveur — rien ne remplace l'écoute. C'est une **adaptation rédigée** : chaque chapitre du présent livre suit un chapitre officiel de l'épisode (l'horodatage est indiqué pour rejoindre le passage audio), et restitue, dans une langue écrite, l'enseignement, les récits et les textes qu'il traverse — Coran et hadiths cités avec leurs sources. Si un chapitre te prend à la gorge, va l'entendre dans la voix de celui qui l'a raconté."
    const val FOOTER_NOTE = "Livre tiré de l'épisode فاهم 36 — Fahem Podcast, avec Dr. Ahmed Al-Arabi (23 février 2024). Les récits des salaf proviennent des sources classiques indiquées ; la wording française, de l'auteur de ce document. Pour la version audio, dans la voix du conteur : https://www.youtube.com/watch?v=63_AOCldyXo."

    val PARTS = listOf(
        BookPart("prologue", "PROLOGUE", "Avant la première page", "00:00:00", 0),
        BookPart("p1", "PREMIÈRE PARTIE", "Le projet", "00:01:20", 80),
        BookPart("p2", "DEUXIÈME PARTIE", "Ils étaient des hommes comme nous", "00:27:19", 1639),
        BookPart("p3", "TROISIÈME PARTIE", "Le voyage, station par station", "01:06:45", 4005),
        BookPart("epilogue", "ÉPILOGUE", "La dernière page", "02:08:40", 7720),
    )

    val CHAPTERS = listOf(
        BookChapter("prologue", null, "prologue", "Avant la première page", "00:00:00", 0, null, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "L'épisode s'ouvre comme s'ouvraient les assemblées des savants : par une invocation. On demande à Allah de faire de cette rencontre une lumière, de la faire parvenir aux cœurs, de l'accepter. Puis, avant même de parler de prière, une image tombe — l'image qui hante tout le reste de la conversation :"),
            BookNode.Line(BookLineKind.PARAGRAPH, "Il fut un homme dont la jambe se gangréna. Les médecins dirent : il faut l'amputer. Il dit : attendez — laissez-moi entrer dans la prière d'abord. Il entra en prière. Ils scièrent. Et il ne sentit rien — pas un cri, pas un mouvement — jusqu'à ce qu'il eût fini. *Ce que le cœur goûte chasse ce que le corps subit.*"),
            BookNode.Line(BookLineKind.PARAGRAPH, "C'est une histoire de générations passées (on la lira au chapitre 12, celle de ʿUrwa) — mais elle pose la question que l'épisode posera pendant deux heures : **que connaissent ces gens dans leur prière, que nous ne connaissons pas dans la nôtre ?** Et surtout : est-ce un don réservé à eux — ou une porte qui s'apprend ?"),
            BookNode.Line(BookLineKind.PARAGRAPH, "La description de l'épisode pose le diagnostic en trois phrases, qu'on peut traduire fidèlement ainsi :"),
            BookNode.Line(BookLineKind.QUOTE, "*La validité d'une adoration ne dépend pas seulement des règles du fiqh : une adoration peut être **valide, valant décharge** — et pourtant **creuse**, vide d'effet spirituel et de relation avec Allah. Et il ne sied pas au serviteur d'en offrir une telle à son Seigneur, car **Allah est bon (Ṭayyib) et n'accepte que ce qui est bon**.* — Muslim (Zakat)"),
            BookNode.Line(BookLineKind.PARAGRAPH, "Le hadith-cadre, rapporté par Anas ibn Mālik (qu'Allah l'agrée), est celui-ci :"),
            BookNode.Line(BookLineKind.QUOTE, "**« La première chose dont le serviteur sera interrogé au Jour de la Résurrection est la prière : si elle est saine, l'ensemble de ses œuvres est sain ; et si elle est corrompue, l'ensemble de ses œuvres est corrompu. »** — al-Tabarānī, authentifié par al-Albānī (Ṣaḥīḥ al-Jāmiʿ 2573)"),
            BookNode.Line(BookLineKind.PARAGRAPH, "La prière n'est pas une case parmi cinq. C'est **la première page du livre qu'on lira de ta vie**. Tout ce qui suit n'existe que pour une question : quand cette page sera ouverte, que dira-t-elle ?"),
        )
        ),
        BookChapter("ch1", 1, "p1", "Quatre adorations, une seule relation", null, -1, null, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "Le Dr. Al-Arabi arrive avec un projet, pas avec un épisode. Une série : *Tadhaawuq al-ʿIbādāt* — **le goût des adorations**. Quatre épisodes, quatre piliers de la vie quotidienne du musulman : **la prière, le Coran, l'invocation, le rappel (dhikr)**."),
            BookNode.Line(BookLineKind.PARAGRAPH, "Pourquoi ces quatre-là ? Parce que la relation de chacun de nous avec Allah — quelle que soit sa hauteur spirituelle — ne sort jamais, au quotidien, de ces quatre actes. Ce sont les quatre portes de la maison. Et la prière est **la mère** : elle contient les trois autres. Elle est récitation du Coran, invocation, et rappel à la fois. Celui qui redresse la prière redresse, par elle, tout le reste ; celui qui la laisse pencher verra pencher le reste — c'est la promesse du hadith-cadre."),
            BookNode.Line(BookLineKind.PARAGRAPH, "L'hôte dit aussi sa raison profonde : *« Nous vivons une époque où la relation avec Allah est une cible. Chaque jour qui passe, à peine en trouveras-tu un où ton cœur n'est pas visé »* — par ce qui égare, par ce qui enivre, par ce qui endort. Si la relation avec Allah n'est pas entretenue, entretenue **à fond**, elle ne reste pas neutre : elle recule. Ce projet est un arsenal de réfection — commencer par la colonne : la prière."),
        )
        ),
        BookChapter("ch2", 2, "p1", "Pourquoi la prière ?", "00:10:45", 645, null, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "La question semble naïve. Elle ne l'est pas. Pourquoi *la prière*, et non le jeûne, non l'aumône, non même la foi déclarée ?"),
            BookNode.Line(BookLineKind.PARAGRAPH, "Parce que la prière est **le seul rendez-vous de ta vie dont l'heure a été fixée par le Roi Lui-même**. Tout le reste, tu le choisis : tu jeûnes quand tu décides (au-delà du mois), tu donnes quand tu décides. Les cinq prières, Lui les a convoquées. Cinq fois par jour, tu n'es pas celui qui prend l'initiative — tu es **l'invité**. Et la première marque d'amour, c'est de tenir ses rendez-vous."),
            BookNode.Line(BookLineKind.PARAGRAPH, "Le Coran lie la réussite entière de la vie à ce seul critère :"),
            BookNode.Line(BookLineKind.QUOTE, "﴿قَدْ أَفْلَحَ الْمُؤْمِنُونَ الَّذِينَ هُمْ فِي صَلَاتِهِمْ خَاشِعُونَ﴾ *« Réussissent les croyants, ceux qui sont humbles (khāshiʿūn) dans leurs prières. »* — Al-Mu'minūn 23:1-2"),
            BookNode.Line(BookLineKind.PARAGRAPH, "Remarque l'ordre : la réussite est promise, et **la première qualité énumérée** est l'humilité dans la prière — pas le nombre de prières, pas leur régularité : leur *khushūʿ*. Le mot est le centre de tout l'épisode : la présence du cœur, cette attention telle que le corps entre et le cœur entre avec lui."),
            BookNode.Line(BookLineKind.PARAGRAPH, "Et le Prophète ﷺ a dit : « On m'a fait aimer de votre monde : les femmes et le parfum — et **la fraîcheur de mes yeux a été placée dans la prière**. » (Ahmad, an-Nasā'ī — authentique). La fraîcheur de l'œil : ce repos qu'on ne trouve nulle part ailleurs. Non la corvée : le refuge."),
        )
        ),
        BookChapter("ch3", 3, "p1", "Comment la prière répare-t-elle ma relation avec Allah ?", "00:15:49", 949, null, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "Puisque le cœur est visé chaque jour, il lui faut chaque jour un lieu où se laver. La prière est ce lieu — **à condition d'y être présent**."),
            BookNode.Line(BookLineKind.PARAGRAPH, "Cinq fois par jour, la prière te coupe du flux. Le téléphone ne rentre pas. Le feed ne rentre pas. Les soucis attendent à la porte — la vraie question est de savoir s'ils restent dehors ou s'ils entrent avec toi en cachette (chapitre 19). Celui qui goûte sa prière en sort *nettoyé* ; celui qui l'a « faite » en sort comme il est entré. Le Coran dit :"),
            BookNode.Line(BookLineKind.QUOTE, "﴿وَاسْتَعِينُوا بِالصَّبْرِ وَالصَّلَاةِ﴾ *« Et cherchez aide dans la patience et la prière. »* — Al-Baqara 2:45"),
            BookNode.Line(BookLineKind.PARAGRAPH, "L'aide par la prière n'est pas une récompense différée : c'est immédiat. Le croyant qui a réellement prié — c'est-à-dire *rencontré* — change de visage en sortant. Les compagnons allaient au Prophète ﷺ avec des visages soucieux et revenaient illuminés : ils venaient de passer chez Celui qui détient les solutions."),
        )
        ),
        BookChapter("ch4", 4, "p1", "La supériorité de la prière sur toutes les adorations", "00:21:06", 1266, null, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "Un décompte simple, que l'épisode déroule :"),
            BookNode.Line(BookLineKind.BULLET, "**Elle fut prescrite au ciel**, pas sur terre. La nuit du Miʿrāj, Allah a convoqué Son Prophète ﷺ au-dessus des sept cieux pour Lui donner ce cadeau — cinquante prières réduites à cinq en valeur de cinquante. Aucune autre obligation n'a eu cet honneur : le jeûne, le pèlerinage, la zakat sont descendus sur terre. La prière, elle, s'est passée de la terre."),
            BookNode.Line(BookLineKind.BULLET, "**Elle est la seule qui ne s'interrompt jamais** — ni en voyage (on l'abrège mais on ne la supprime pas), ni dans la peur (voir chapitre 7 : la prière sous les sabres), ni dans la maladie (on prie debout, assis, couché, du regard). C'est la seule adoration qui a un mode pour chaque état de l'homme. Allah veut ce rendez-vous **dans toutes tes conditions** — tu n'es jamais excusé de L'oublier."),
            BookNode.Line(BookLineKind.BULLET, "**Elle efface les fautes entre deux rendez-vous** : « Les cinq prières, du vendredi au vendredi, effacent ce qui est entre elles, tant que les péchés majeurs sont évités. » (Muslim)"),
            BookNode.Line(BookLineKind.BULLET, "**Elle t'attend si tu la manques**, contrairement au jeûne de l'année : « Quiconque s'endort sans faire sa prière, qu'il la fasse lorsqu'il s'en souvient. » — le rendez-vous reste ouvert."),
            BookNode.Line(BookLineKind.BULLET, "**Elle est la dernière recommandation du Prophète ﷺ** à sa communauté mourante : « La prière, la prière, et ce que possèdent vos mains droites (les faibles sous votre protection). » La dernière chose qui devait rester sur ses lèvres pour nous."),
            BookNode.Line(BookLineKind.PARAGRAPH, "La prière est au reste des œuvres ce que la colonne vertébrale est au corps : elle ne fabrique pas les membres, mais sans elle tout s'effondre."),
        )
        ),
        BookChapter("ch5", 5, "p2", "La prière du Prophète ﷺ", null, -1, null, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "ʿĀ'isha (qu'Allah l'agrée) a dit : *« Le Messager d'Allah ﷺ parlait avec nous et nous parlions avec lui ; mais quand venait l'heure de la prière, c'était comme s'il ne nous connaissait pas et nous ne le connaissions pas »* — littéralement occupé, transporté ailleurs. (Abū Dāwūd, authentifié)"),
            BookNode.Line(BookLineKind.PARAGRAPH, "Il se levait la nuit alors que ses pieds portaient les fatigues du jour, et priait si longtemps que **ses pieds gonflaient**. On lui disait : tu le fais alors qu'Allah t'a pardonné ce qui est passé et ce qui vient ? Il répondait :"),
            BookNode.Line(BookLineKind.QUOTE, "**« أَفَلَا أَكُونُ عَبْدًا شَكُورًا — Ne serais-je pas un serviteur reconnaissant ? »** — Bukhārī & Muslim"),
            BookNode.Line(BookLineKind.PARAGRAPH, "Et l'épisode s'arrête sur ce détail : sa prosternation était si longue qu'on entendait **le bruit de son souffle** — comme le râle d'un enfant qui pleure en silence, disent certains récits. Voilà l'homme à qui le Paradis a été promis. Si lui se tenait ainsi, qu'attendons-nous de nos quatre minutes debout ?"),
            BookNode.Line(BookLineKind.PARAGRAPH, "Bilāl était son muezzin, et de Bilāl le Prophète ﷺ a dit qu'il avait entendu **ses pas au Paradis** (Bukhārī & Muslim) : deux rakʿa après les ablutions, discrètes, fidèles — c'est ce qui a fait passer un homme d'Abyssinie devant des générations. La grandeur d'une prière ne se mesure pas à sa longueur, mais à sa vérité."),
        )
        ),
        BookChapter("ch6", 6, "p2", "La prière de la Famille et des Compagnons", "00:36:13", 2173, null, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "**Abū Bakr [00:37:55]** — le plus doux des hommes était, dans la prière, le plus éploré. Il récitait le Coran à voix haute dans sa cour à La Mecque, et les femmes et les enfants des Quraysh s'arrêtaient pour écouter — les larmes coulant, incapable de s'en tenir. Quand il vieillit et que sa voix trembla davantage encore, on lui demanda de baisser le ton ; le Prophète ﷺ refusa : *« Laissez-le. Il pleure, et que celui qui ne pleure pas fasse comme s'il pleurait. »* Sa prière était une confidence, pas une formalité."),
            BookNode.Line(BookLineKind.PARAGRAPH, "**Tamīm al-Dārī [00:38:54]** — le compagnon dont on dit qu'il a passé une nuit entière sur un seul verset. Il récitait :"),
            BookNode.Line(BookLineKind.QUOTE, "﴿أَمْ حَسِبَ الَّذِينَ اجْتَرَحُوا السَّيِّئَاتِ أَنْ نَجْعَلَهُمْ كَالَّذِينَ آمَنُوا﴾ *« Ceux qui commettent les mauvaises actions comptent-ils que Nous les traiterons comme ceux qui croient... ? »* — Al-Jāthiya 45:21"),
            BookNode.Line(BookLineKind.PARAGRAPH, "...et il recommençait, et pleurait, jusqu'au matin. Une lampe, un verset, une nuit. Il n'avait personne à convaincre : c'était entre lui et son Seigneur. Ses voisins racontaient que sa lampe ne s'éteignait pas la nuit — et quand on a voulu voir ce qu'il faisait, on a trouvé un homme seul, debout, parlant à son Seigneur comme on parle à quelqu'un qui répond."),
            BookNode.Line(BookLineKind.PARAGRAPH, "**Les Compagnons en général [00:39:11]** — ils avaient appris la prière **du Prophète ﷺ lui-même**, geste par geste, et chacun l'a portée à sa hauteur : ʿUmar, dont la voix derrière le Prophète ﷺ fut couverte par le renvoi d'un hadith ; ʿUthmān qui récitait la nuit entière le Coran en une seule rakʿa ; ʿAlī dont les genoux avaient les marques des prières prolongées — *« comme les genoux du chameau »*, disait son fils. Ils n'avaient pas plus d'heures que nous. Ils avaient autre chose."),
        )
        ),
        BookChapter("ch7", 7, "p2", "La prière sous l'éclair des sabres", "00:41:07", 2467, null, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "On croit rêver : des hommes qui **maintiennent la prière pendant que les sabres brillent au-dessus d'eux**. La preuve en est coranique — Allah a révélé **la prière de la peur (ṣalāt al-khawf)**, décrite dans le Coran même :"),
            BookNode.Line(BookLineKind.QUOTE, "﴿وَإِذَا كُنْتَ فِيهِمْ فَأَقَمْتَ لَهُمُ الصَّلَاةَ﴾ *« Quand tu es parmi eux et que tu diriges leur prière... »* — An-Nisā' 4:102"),
            BookNode.Line(BookLineKind.PARAGRAPH, "Un groupe prie avec toi, un groupe monte la garde, puis on échange. Imagine : l'ennemi en face, et une partie de la communauté **en prière quand même**. Le rendez-vous ne s'annule pas sous la mort."),
            BookNode.Line(BookLineKind.PARAGRAPH, "Les récits de la génération suivante sont plus fous encore : des hommes tombés au champ d'honneur qu'on a retrouvés **en état de sujūd**, le front encore au sol. Le jour où leurs derniers instants sont venus, ils ont choisi de les finir dans la posture de la proximité. On ne meurt pas dans une posture qu'on n'a pas aimée toute sa vie."),
        )
        ),
        BookChapter("ch8", 8, "p2", "La prière des salaf", "00:43:31", 2611, null, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "Vient le défilé — l'épisode passe ici un long moment, et le livre aussi. Les salaf — les deux premières générations — ont laissé des traces de prière qui ressemblent à des légendes, et qui sont des biographies documentées."),
            BookNode.Line(BookLineKind.PARAGRAPH, "Ils dormaient peu la nuit, *« ils dormaient la moitié ou le tiers de la nuit, et le dernier tiers était à Allah »*. Saʿīd ibn al-Musayyib, vieux, se tenait debout des heures. Un homme vit Ḥasan al-Baṣrī sortant de la prière de l'aube, le visage nouveau, et lui demanda pourquoi ; il répondit : *« Viens-tu de sortir d'un déjeuner ? »* — quand tu as mangé, ça se voit."),
            BookNode.Line(BookLineKind.PARAGRAPH, "**La prière à la mosquée [00:52:11]** — une discipline collective : à Médine puis dans les villes de l'Islam, l'homme qui dormait à la maison était soupçonné : est-il malade ? L'épisode le rappelle : la communauté des prières était le pouls de la cité. Les rangs se serraient comme les briques d'un même mur ; quand quelqu'un manquait à l'appel, on allait de ses nouvelles — pas pour juger : parce qu'un rendez-vous manqué, c'est un frère peut-être tombé."),
        )
        ),
        BookChapter("ch9", 9, "p2", "Des histoires de notre temps", "00:53:52", 3232, null, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "Et le Dr. Al-Arabi de faire ce que la description de l'épisode annonce : descendre ces récits des livres vers le présent. Des hommes et des femmes **de notre époque**, sans épitaphe, dont il a été le témoin ou le rapporteur — le médecin qui a vu un patient se lever de son lit d'hôpital pour prier, la jambe morte, et le visage vivant ; le jeune homme ordinaire, sans turban ni barbe de savant, dont une seule rakʿa faisait honte à une assemblée."),
        )
        ),
        BookChapter("ch10", 10, "p2", "ʿAbdallāh ibn al-Zubayr, immobile", "00:56:09", 3369, null, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "Le siège de La Mecque : les catapultes d'al-Ḥajjāj bombardent la ville, et la cour de la Kaʿba reçoit des pierres. Les gens fuient la mosquée, les murs tombent. Ibn al-Zubayr, chef assiégé, **entre en prière**."),
            BookNode.Line(BookLineKind.PARAGRAPH, "Les pierres tombaient autour de lui, la cour se vidait, les éclats volaient — et il ne bougea pas. Les témoins disent qu'il resta dans sa première inclina **comme un bâton planté**. Quand il finit, on lui dit ce qui s'était passé : la mosquée s'était écroulée autour de lui et il n'avait pas tourné la tête."),
            BookNode.Line(BookLineKind.PARAGRAPH, "C'est l'histoire que le Prophète ﷺ avait vue d'avance : *« Il sera un jour un homme de ma communauté qui tiendra ferme quand les gens faibliront »*. Ibn al-Zubayr était le petit-frifils d'Abū Bakr — le sang ne fait pas la prière, mais l'héritage de la présence, lui, se transmet."),
        )
        ),
        BookChapter("ch11", 11, "p2", "Muslim ibn Yasar, l'absent de ce monde", "00:57:37", 3457, null, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "Le tertiaire de Basra. Un jour qu'il priait dans la mosquée, **une poutre s'effondra** — le vacarme, la poussière, la foule qui se précipite, les cris. Et Muslim dans sa prière, **n'a rien remarqué**. Il a fini sa rakʿa, a fait son salām, a regardé les gens affairés et demanda : *« Que se passe-t-il ? »* — on lui montra la poutre effondrée à quelques pas de lui."),
            BookNode.Line(BookLineKind.PARAGRAPH, "C'est cet homme — Muslim ibn Yasar — le modèle d'une expression devenue proverbe : quand quelqu'un priait avec une présence totale, on disait *« voilà un musulman ibn-yasari »* — un priant d'une autre planète. Le monde entier a beau s'écrouler à côté : son cœur est ailleurs, et son corps n'a qu'un seul occupant — le Roi."),
        )
        ),
        BookChapter("ch12", 12, "p2", "ʿUrwa ibn al-Zubayr, la jambe et le fils", "00:57:58", 3478, null, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "La plus douloureuse et la plus lumineuse des histoires du cycle. ʿUrwa — frère d'Ibn al-Zubayr, savant de Médine — développe une gangrène à la jambe. Les médecins disent : il faut couper, et vite. Il dit : **« Donnez-moi le temps d'entrer en prière. »** Il entre dans une prière volontaire, et les médecins, à sa demande, **lui scient la jambe pendant qu'il prie**. Il ne cille pas. Quand il finit, il relève sa jambe — il ne l'a pas sentie."),
            BookNode.Line(BookLineKind.PARAGRAPH, "Ce même jour — les historiens disent : pendant qu'on lui sciait la jambe — son fils bien-aimé (Muhammad, le plus cher de ses enfants) venait de mourir, piétiné par une bête de la maison. On craignit de lui annoncer les deux nouvelles d'un coup. Il dit une parole restée gravée dans les Siyar, dont le sens est :"),
            BookNode.Line(BookLineKind.QUOTE, "**« Ô Allah, je T'adorais avant cela. Si Tu as pris, Tu as laissé ; si Tu as éprouvé, Tu as donné la santé. »** — et il récita ﴿إِنَّا لِلَّهِ وَإِنَّا إِلَيْهِ رَاجِعُونَ﴾"),
            BookNode.Line(BookLineKind.PARAGRAPH, "La prière ne l'empêchait pas d'aimer son fils, ni d'avoir mal : elle l'empêchait **de se révolter contre Celui qui décrète**. Le goût de la prosternation lui avait appris une chose : le même Allah à qui il confiait sa jambe pouvait recevoir son enfant. Et la paix fut plus forte que la scie."),
        )
        ),
        BookChapter("ch13", 13, "p2", "ʿIbbād ibn Bishr, la nuit et la lumière", "00:59:56", 3596, null, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "Une nuit, ʿIbbād ibn Bishr et Usayd ibn Ḥuḍayr quittent le Prophète ﷺ tard, dans l'obscurité complète. Et voici qu'une **lumière** apparaît au bout de la lance de l'un d'eux — disent les récits : comme deux lampes marchant devant eux, les éclairant sur le chemin du retour. Quand ils se séparèrent, chacun eut sa lumière ; et la tradition rapporte que cette lumière était celle d'**anges qui avaient écouté la belle récitation d'Usayd** — car il récitait la sourate al-Baqara, cette même nuit, et sa récitation faisait descendre la lumière."),
            BookNode.Line(BookLineKind.PARAGRAPH, "Dans la version classique (Bukhārī, pour Usayd) : il récitait chez lui, sa jument attachée à côté ; l'animal s'agita, il s'arrêta, l'animal se calma ; il recommença, l'animal s'affola encore — et il leva la tête et vit **comme une nuée chargée de lampes** qui montait vers le ciel. Le lendemain, il le raconta au Prophète ﷺ qui lui dit : *« C'était les anges qui écoutaient ta voix ; si tu avais continué, les gens t'auraient vu ne serait-ce que... »* — la lumière ne se cachait pas."),
            BookNode.Line(BookLineKind.PARAGRAPH, "C'est la preuve par le sensible : **une récitation faite avec présence fait bouger le monde invisible**. Le ciel a de l'oreille."),
        )
        ),
        BookChapter("ch14", 14, "p2", "Un homme de notre temps", "01:02:20", 3740, null, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "L'épisode garde pour la fin des récits celui-ci : un homme **de notre époque** — pas un compagnon, pas un tābiʿī : un homme d'aujourd'hui, qu'on peut croiser — dont la prière avait la même densité. Le Dr. Al-Arabi le dit en substance : ce ne sont pas des espèces disparues ; ce sont des inconnus. Ils ne font pas la une des réseaux ; ils ne donnent pas de cours ; ils sont peut-être l'homme qui tient la porte, la femme qui fait le ménage. **Le khushūʿ n'est pas une époque révolue : c'est un secret que des gens vivent encore — juste pas devant toi.**"),
            BookNode.Line(BookLineKind.PARAGRAPH, "Pourquoi te le cacher ? Parce que la présence du cœur est la chose la plus facile à perdre par le regard des autres : le jour où l'homme prie *pour qu'on le voie*, il a tout perdu. Le khushūʿ vit dans l'ombre — et c'est là qu'il faut aller le chercher."),
        )
        ),
        BookChapter("ch15", 15, "p2", "Comment devenir comme eux ?", "01:04:33", 3873, null, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "La question que deux heures de récits ont fait germer. Et la réponse de l'épisode est le message le plus encourageant de tout le livre :"),
            BookNode.Line(BookLineKind.PARAGRAPH, "**Ce n'est pas un don. C'est une méthode.**"),
            BookNode.Line(BookLineKind.PARAGRAPH, "Ils étaient *« des hommes comme nous »* — ils mangeaient, ils avaient des familles, des dettes, des chagrins. Le Prophète ﷺ lui-même a rassuré sa communauté en ces termes : celui qui désire cette station et œuvre pour elle en aura la récompense. Et le verset qui donne le mode d'emploi de tout le reste du livre :"),
            BookNode.Line(BookLineKind.QUOTE, "﴿وَالَّذِينَ جَاهَدُوا فِينَا لَنَهْدِيَنَّهُمْ سُبُلَنَا﴾ *« Et ceux qui luttent pour Nous — Nous les guiderons certes sur Nos chemins. »* — Al-ʿAnkabūt 29:69"),
            BookNode.Line(BookLineKind.PARAGRAPH, "Pas de miracle : **une lutte (jihād) — contre la paresse, contre les pensées, contre la sortie précipitée** — et en échange, Allah Se charge de te guider *sur Ses chemins*, c'est-à-dire sur le chemin du goût. Le reste de l'épisode — et de ce livre — est la méthode, station par station : de l'eau des ablutions jusqu'à l'assise d'après le salām."),
        )
        ),
        BookChapter("ch16", 16, "p3", "L'eau : le wudū'", "01:06:45", 4005, 1, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "Le voyage commence avant la porte : **à l'eau**. Le Prophète ﷺ a dit :"),
            BookNode.Line(BookLineKind.QUOTE, "**« Quand le serviteur musulman (ou croyant) fait ses ablutions et lave son visage, chaque faute que ses yeux ont regardée s'en va avec l'eau — ou avec les dernières gouttes d'eau — jusqu'à ce qu'il sorte pur de ses fautes. »** — Muslim"),
            BookNode.Line(BookLineKind.PARAGRAPH, "Le wudū' est déjà une prière en miniature : un retour à l'état premier, celui du jour où tes parents t'ont donné ce nom. Et l'épisode insiste : **l'eau lave ce qu'elle touche, l'intention lave ce qu'elle ne touche pas.** Chaque membre a sa question :"),
            BookNode.Line(BookLineKind.BULLET, "**Les mains** — qu'ont-elles pris aujourd'hui qui n'était pas à elles ?"),
            BookNode.Line(BookLineKind.BULLET, "**La bouche** — qu'a-t-elle dit aujourd'hui qu'elle doit laisser partir avec l'eau ?"),
            BookNode.Line(BookLineKind.BULLET, "**Le visage** — ces yeux qui se tiendront un jour devant Lui."),
            BookNode.Line(BookLineKind.BULLET, "**La tête** — ce dont tu fus fier, soumets-le."),
            BookNode.Line(BookLineKind.BULLET, "**Les pieds** — où marches-tu après cette prière ? Purifie la destination."),
            BookNode.Line(BookLineKind.PARAGRAPH, "Et la sunna du Prophète ﷺ après les ablutions, regardant vers le ciel : *« Je T'atteste qu'il n'y a de divinité que Toi... accorde-moi Ton pardon »* — et le hadith : *« les portes du ciel sont ouvertes pour lui »* (Tirmidhī, Abū Dāwūd). Le wudū' est la montée au ciel, goutte par goutte."),
        )
        ),
        BookChapter("ch17", 17, "p3", "La marche vers la prière", "01:11:20", 4280, 2, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "Tu ne « vas pas prier ». Tu **réponds à une convocation**. Le Prophète ﷺ a dit :"),
            BookNode.Line(BookLineKind.QUOTE, "**« Quiconque fait ses ablutions parfaitement, puis sort vers la mosquée — sans autre motif que la prière — ne fait pas un pas sans qu'un degré ne lui soit élevé et une faute effacée. »** — Muslim"),
            BookNode.Line(BookLineKind.PARAGRAPH, "L'épisode fait remarquer la précision du hadith : la récompense est **au pas**, pas à la prière. Le trajet est déjà le rendez-vous. Et quand tu entends l'iqāma : marche avec dignité, sans courir — *« rejoins ce que tu atteins »* — car tu viens vers un Roi, et on ne court pas chez un Roi, on s'apprête."),
            BookNode.Line(BookLineKind.PARAGRAPH, "Pour la femme, la maison est sa mosquée — et la marche devient le déplacement intérieur : quitter la cuisine, quitter le bain, quitter le téléphone, physiquement. Le hadith dit : *« ne retienne pas les servantes d'Allah des mosquées »*, et la meilleure prière de la femme est chez elle — mais le trajet sacrifié existe chez elle aussi : il s'appelle **le dépôt de ce qu'elle tenait**."),
        )
        ),
        BookChapter("ch18", 18, "p3", "« Suis-je en train d'attendre que ce soit Lui qui m'appelle ? »", "01:14:10", 4450, 3, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "Le titre le plus beau de l'épisode. Une question à se poser devant l'adhan :"),
            BookNode.Line(BookLineKind.PARAGRAPH, "**Est-ce que j'attends l'appel — ou est-ce que je le subis ?**"),
            BookNode.Line(BookLineKind.PARAGRAPH, "Car il y a deux manières d'entendre « ḥayya ʿala ṣ-ṣalāh » : la sonnerie d'un réveil qu'on hait, ou **la voix d'un invitant**. Si une lettre du président t'invitait à son palais demain, tu ne dormirais pas. C'est le Créateur des présidents qui t'invite, cinq fois le jour — et l'épisode de remarquer amèrement : *nous traitons la convocation du Roi comme nous ne traiterions jamais celle d'un employeur.*"),
            BookNode.Line(BookLineKind.PARAGRAPH, "Le fidèle qui a compris se met à espérer l'heure suivante. C'est l'inversion totale de l'app : on ne subit plus les cinq prières, on les attend. Et le verset qui décrit ce renversement :"),
            BookNode.Line(BookLineKind.QUOTE, "﴿فِي رِيَاضِ الْجَنَّةِ... يَسْتَبْشِرُونَ﴾ *« ... ils se réjouissent »* — et le hadith : *« Donne-moi la fraîcheur de tes yeux dans la prière »*."),
        )
        ),
        BookChapter("ch19", 19, "p3", "Les clés des cœurs, avant la prière", "01:16:09", 4569, 4, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "Avant le takbīr, il y a un travail invisible — et l'épisode le nomme : **les pensées, c'est le lait sur le feu**. Tu ne peux pas empêcher le lait de monter ; tu peux baisser le feu."),
            BookNode.Line(BookLineKind.PARAGRAPH, "Trois clés données :"),
            BookNode.Line(BookLineKind.PARAGRAPH, "1. **Éteins le téléphone** — pas silencieux : éteins. Le messager qui te suit dans ta prière te suit dehors."),
            BookNode.Line(BookLineKind.PARAGRAPH, "2. **Règle avant d'entrer ce qui peut l'être** — la porte, l'enfant, la casserole : le Prophète ﷺ a dit : *« Quand l'un de vous mange, qu'il finisse »* et *« quand le dîner est servi, commencez par lui »* (Bukhārī & Muslim). La prière qui commence contre une casserole qui brûle a déjà perdu."),
            BookNode.Line(BookLineKind.PARAGRAPH, "3. **Décide avant le takbīr que tu te tiens devant Lui** — c'est la première bataille, et elle se gagne ou se perd avant le premier mot. Les pensées ont la force d'un envahisseur seulement si tu leur ouvres la porte ; ne réponds pas à la première pensée — elle est l'éclaireur des autres."),
        )
        ),
        BookChapter("ch20", 20, "p3", "Allāhu akbar : la porte", "01:19:57", 4797, 4, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "Deux mots légers sur la langue, et lourds comme le monde. Quand tu dis **Allāhu akbar** et que tes mains montent, tu jettes le monde derrière toi — littéralement, disent les savants : le geste du priant est celui qui **rejette ce qui est derrière lui** pour se tourner vers ce qui est devant."),
            BookNode.Line(BookLineKind.PARAGRAPH, "Ibn al-Qayyim (que l'épisode cite en ce sens) : le sens du takbīr est que **rien n'est plus grand qu'Allah** — ni ton téléphone, ni ta dette, ni ton chagrin, ni ta joie. Tu le dis de la langue ? Dis-le du cœur. Si ton cœur n'a pas dit « plus grand » pendant que ta bouche le disait, tu es entré chez le Roi en pensant à autre chose — et le hadith d'Ahmad est là pour rappeler la facture :"),
            BookNode.Line(BookLineKind.QUOTE, "*« Il arrive qu'un homme prie et qu'il ne lui soit écrit de sa prière que la moitié, le tiers, le quart... jusqu'à un dixième. »*"),
            BookNode.Line(BookLineKind.PARAGRAPH, "L'épisode en tire l'image juste : une lettre sans signature, un repas sans sel. La prière existe — le goût n'y est pas. Tout ce qu'on a lu jusqu'ici (les récits des salaf) n'était que le sel."),
        )
        ),
        BookChapter("ch21", 21, "p3", "L'invocation d'ouverture : frapper avant d'entrer", "01:22:47", 4967, 5, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "On n'entre pas chez un Roi en commençant à parler de soi. Le Prophète ﷺ, entre le takbīr et la Fātiha, se tenait un instant — et ses invocations d'ouverture sont pour nous la leçon de politesse céleste. La plus connue :"),
            BookNode.Line(BookLineKind.QUOTE, "**« Subḥānaka Allāhumma wa biḥamdika, wa tabāraka ismuka, wa taʿālā jadduka, wa lā ilāha ghayruk. »** *« Gloire à Toi, ô Allah, et louange ; béni soit Ton nom, exaltée soit Ta grandeur, et il n'y a de divinité que Toi. »** — Muslim"),
            BookNode.Line(BookLineKind.PARAGRAPH, "Et l'autre, plus bouleversante encore :"),
            BookNode.Line(BookLineKind.QUOTE, "**« Allāhumma bāʿid baynī wa bayna khaṭāyāya kamā bāʿadta bayna l-mashriqi wa l-maghrib »** *« Ô Allah, éloigne de moi mes fautes comme Tu as éloigné l'Orient de l'Occident. »** — Bukhārī & Muslim"),
            BookNode.Line(BookLineKind.PARAGRAPH, "Ce moment — les mains levées puis posées, le silence avant la Fātiha — **n'est pas un temps mort** : c'est le seuil. Les salaf disaient : la prière a un parfum, et ce parfum se sent dès l'ouverture. Reste-y quelques secondes de plus que d'habitude, et tu sentiras la suite changer."),
        )
        ),
        BookChapter("ch22", 22, "p3", "Al-Fātiha : le dialogue", "01:25:52", 5152, 6, true, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "*Le sommet de l'épisode : dix-sept minutes à elle seule. Pour une raison — ce n'est pas une récitation. C'est un échange.*"),
            BookNode.Line(BookLineKind.PARAGRAPH, "Le hadith qudsī, rapporté par Muslim (n° 395), le déclare mot à mot :"),
            BookNode.Line(BookLineKind.QUOTE, "**« Allah a dit : J'ai partagé la prière (la Fātiha) entre Moi et Mon serviteur, en deux moitiés — et Mon serviteur aura ce qu'il demande.** **Quand le serviteur dit ﴿الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ﴾, Allah dit : « Mon serviteur M'a loué (ḥamdaniʿabdī). »** **Quand il dit ﴿الرَّحْمَنِ الرَّحِيمِ﴾, Allah dit : « Mon serviteur M'a exalté. »** **Quand il dit ﴿مَالِكِ يَوْمِ الدِّينِ﴾, Allah dit : « Mon serviteur M'a glorifié. »** **Quand il dit ﴿إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ﴾, Allah dit : « Ceci est entre Moi et Mon serviteur, et Mon serviteur aura ce qu'il demande. »** **Quand il dit ﴿اهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَ﴾... Allah dit : « Ceci est pour Mon serviteur, et Mon serviteur aura ce qu'il a demandé. »**"),
            BookNode.Line(BookLineKind.PARAGRAPH, "Relis bien : **à chaque verset, une réponse**. Tu dis ta ligne, et Il dit la Sienne. Al-Fātiha est un drâme en sept répliques — un tête-à-tête. Le Prophète ﷺ a dit : *« Il n'y a de prière pour celui qui ne récite pas l'ouverture du Livre »* (Bukhārī & Muslim) — pas parce qu'il manque une formalité, mais parce que **le dialogue ne se fait pas sans les paroles du dialogue**."),
            BookNode.Line(BookLineKind.PARAGRAPH, "L'épisode s'attarde sur les stations de la Fātiha, et le livre les retient :"),
            BookNode.Line(BookLineKind.BULLET, "**﴿إِيَّاكَ نَعْبُدُ﴾** — le pronom « Toi » placé en premier : *Toi seul*. Tout le tawḥīd tient dans un pronom bien placé. Et le passage du « je » au « nous » : tu n'entres pas seul, tu entres avec la communauté."),
            BookNode.Line(BookLineKind.BULLET, "**﴿اهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَ﴾** — la plus grande demande qu'un serviteur puisse faire : la guidance. Et Il a promis de répondre. Demande-la **en attendant vraiment la réponse** : c'est la seule demande de ta journée à laquelle le Roi a déjà dit oui."),
            BookNode.Line(BookLineKind.BULLET, "**La pause (tarwiḥa)** après chaque verset — le Prophète ﷺ marquait une respiration. C'est cette pause qui fait la place de la réponse. Celui qui récite la Fātiha comme un SMS dicté a posté sa lettre et parti avant la réponse."),
        )
        ),
        BookChapter("ch23", 23, "p3", "La récitation après la Fātiha", "01:43:19", 6199, 6, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "Après le dialogue d'ouverture, le Prophète ﷺ récitait une sourate — et sa récitation était **lente, lettre par lettre** : *« Il prolongeait sa voix sur le mīm de ﴿بِسْمِ اللَّهِ﴾ »*. Le conseil de l'épisode est simple : **prends une sourate que ton cœur connaît** — pas la plus longue : celle qui te fait quelque chose. ʿĀ'isha entendait le Prophète ﷺ récitait ﴿الْقِيَامَةُ﴾ le matin, et s'arrêtait sur des versets pour demander : quelle sera ma fin ?"),
            BookNode.Line(BookLineKind.PARAGRAPH, "La récitation dans la prière n'est pas de la lecture : c'est **le Roi qui t'a donné la parole**, et tu La Lui rends. Réactive cette intention : ce que tu récites n'est pas du texte — c'est le discours que tu adresses à Celui qui te regarde."),
        )
        ),
        BookChapter("ch24", 24, "p3", "L'inclinaison : le corps témoigne", "01:46:14", 6374, 7, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "Le dos se plie. Et l'épisode dit une chose que les salaf répétaient : **l'inclina du corps est facile ; l'inclina du cœur, c'est la vraie**. Si ton dos s'est plié et que ton orgueil, lui, est resté debout — tu n'es pas incliné."),
            BookNode.Line(BookLineKind.QUOTE, "*« Quant à l'inclinaison, magnifiez-y le Seigneur (subḥūna fīhi r-Rabb). »** — Muslim"),
            BookNode.Line(BookLineKind.PARAGRAPH, "Le Prophète ﷺ disait dans son rukūʿ :"),
            BookNode.Line(BookLineKind.QUOTE, "**« Subḥāna Rabbiya l-ʿAẓīm »** — *« Gloire à mon Seigneur le Magnifique »* (×3 est le minimum de la sunna)"),
            BookNode.Line(BookLineKind.PARAGRAPH, "Et son invocation complète : *« Ô Allah, c'est pour Toi que je me suis incliné, en Toi j'ai cru, à Toi je me suis soumis ; mon ouïe, ma vue, mon crâne et mes os se sont humiliés devant Toi. »* (Muslim)"),
            BookNode.Line(BookLineKind.PARAGRAPH, "Trois tasbīḥ — pas trois formalités : trois témoignages que **toute grandeur que j'ai croisée aujourd'hui vient de Lui, et Lui revient**. Le rukūʿ est la station de la mise à plat de l'orgueil — reste-y jusqu'à ce que ton dos soit calme. Les salaf restaient dans le rukūʿ le temps de ﴿سَبِّحِ اسْمَ رَبِّكَ الْأَعْلَى﴾ récité lentement."),
        )
        ),
        BookChapter("ch25", 25, "p3", "Le relèvement : « Allah entend celui qui Le loue »", "01:50:47", 6647, 7, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "Tu te redresses, et tu dis : **« Samiʿa Allāhu li-man ḥamidah »** — *« Allah a entendu celui qui Le loue »*. Pas « entendra » : **a entendu**. Déjà. Tu viens de Le louer, et la réponse est déjà donnée."),
            BookNode.Line(BookLineKind.PARAGRAPH, "Puis : **« Rabbanā wa laka l-ḥamd »** — *« Notre Seigneur, et à Toi la louange »* — et le Prophète ﷺ ajoutait : *« une louange immense, pure, bénie, remplissant les cieux et la terre et ce qui est au-delà »* (Muslim)."),
            BookNode.Line(BookLineKind.PARAGRAPH, "L'épisode marque ici un temps : cette station debout, entre l'inclinaison et la prosternation, **est la plus proche du debout du Jour de la Résurrection**. Tu es debout, juste, devant Lui — comme tu le seras. Reste ce moment **le dos droit, le cœur droit** — ne te précipite pas vers la prosternation comme vers la suite d'une tâche : tu es entre deux stations, et les deux te regardent."),
        )
        ),
        BookChapter("ch26", 26, "p3", "La prosternation : la proximité", "01:55:07", 6907, 8, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "La station pour laquelle le mot « goût » a été inventé."),
            BookNode.Line(BookLineKind.QUOTE, "**« Le serviteur n'est jamais aussi proche de son Seigneur que lorsqu'il est en prosternation ; multipliez donc l'invocation. »** — Muslim"),
            BookNode.Line(BookLineKind.PARAGRAPH, "Il n'y a pas plus proche. Pas à la Kaʿba, pas au jour de ʿArafa, pas même au Paradis — **la proximité maximale de ta vie terrestre est ton front sur le sol**. Le corps prend la posture la plus basse possible, et le cœur la plus haute. C'est le paradoxe le plus beau de l'islam : **c'est en descendant que tu montes**."),
            BookNode.Line(BookLineKind.PARAGRAPH, "Le Prophète ﷺ disait dans sa sujūd :"),
            BookNode.Line(BookLineKind.QUOTE, "**« Subḥāna Rabbiya l-Aʿlā »** — et *« Ô Allah, pardonne-moi toute ma faute, la minime et l'énorme, la première et la dernière, celle qui est secrète et celle qui est publique. »** — Muslim"),
            BookNode.Line(BookLineKind.PARAGRAPH, "Son sujūd était si prolongé que ʿĀ'isha, le voyant tarder, **croyait qu'il lui était arrivé quelque chose**. Et le hadith qui donne la mesure : *« ...et j'ai interdit de réciter le Coran dans l'inclinaison et la prosternation »* — dans la sujūd on ne récite pas : **on parle**. C'est la chambre du dialogue intime — apportes-y ce que tu n'oses dire à personne : ta faute, ta mère malade, ton chagrin, ton rêve brisé, ton cœur endurci."),
            BookNode.Line(BookLineKind.PARAGRAPH, "L'épisode le dit comme une confidence : si tu pleures dans une prière, c'est dans la sujūd que ça viendra. Et si ça ne vient pas, **reste plus longtemps** — le sujūd ne se quitte pas par l'ennui, il se quitte par la paix."),
        )
        ),
        BookChapter("ch27", 27, "p3", "L'assise entre les deux prosternations", "02:00:11", 7211, 8, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "La station la plus oubliée — et l'épisode la sauve de l'oubli. Entre les deux sujūd, le Prophète ﷺ s'asseyait **jusqu'à ce que chaque vertèbre reprenne sa place** (Bukhārī). Il disait :"),
            BookNode.Line(BookLineKind.QUOTE, "**« Rabbanā ghfir lī wa rḥamnī wa hjurnī wa rfāʿnī wa ʿāfinī wa rzuqnī »** *« Ô mon Seigneur, pardonne-moi, accorde-moi miséricorde, guide-moi, élève-moi, donne-moi la santé et accorde-moi la subsistance. »** — Muslim"),
            BookNode.Line(BookLineKind.PARAGRAPH, "Six demandes — pardon, miséricorde, guidance, élévation, santé, subsistance : **le résumé de tout ce qu'un homme peut demander**. Et la sunna veut que ce moment soit un vrai repos, pas un pivot. Les salaf comparaient le priant à un voyageur : chaque station est une halte — et la halte ne se fait pas en courant."),
        )
        ),
        BookChapter("ch28", 28, "p3", "Le tashahhud : le salut", "02:03:08", 7388, 9, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "Assis, tu prononces les paroles qu'Ibn Masʿūd (qu'Allah l'agrée) disait être **le secret que le Prophète ﷺ lui mit dans le cœur, entre ses deux lèvres**, comme on enseigne l'écriture à un enfant :"),
            BookNode.Line(BookLineKind.QUOTE, "**« At-taḥiyyātu lillāhi wa ṣ-ṣalawātu wa ṭ-ṭayyibāt. As-salāmu ʿalayka ayyuha n-nabiyyu wa raḥmatu llāhi wa barakātuh... »** *« Les salutations sont à Allah, ainsi que les prières et les œuvres bonnes. Que la paix soit sur toi, ô Prophète, ainsi que la miséricorde d'Allah et Sa bénédiction... »** — Bukhārī & Muslim"),
            BookNode.Line(BookLineKind.PARAGRAPH, "Et l'épisode s'arrête sur ce vertige : **tu adresses la paix au Prophète ﷺ, ici, maintenant, en personne** — et les gens de science rappellent : *« Allah a des anges qui me font connaître le salām de ma communauté »* — le salām sur lui **lui parvient**. Puis la prière abrahamique, en élevant l'index — le doigt qui dit l'unicité au moment où tu cites le nom de Muhammad et d'Ibrāhīm : les deux témoins ensemble."),
            BookNode.Line(BookLineKind.PARAGRAPH, "Autour de toi, à droite et à gauche quand tu prononças le salām final : **les scribes**. Pas une image : la doctrine. Ta prière vient d'être reçue, enregistrée, envoyée. Que dira la page ?"),
        )
        ),
        BookChapter("ch29", 29, "p3", "La clôture de la prière", "02:05:39", 7539, 9, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "Le salām final n'est pas « la fin » — c'est **la sortie du palais**, et le Prophète ﷺ nous a appris ce qu'on dit en sortant :"),
            BookNode.Line(BookLineKind.QUOTE, "**« Allāhumma anta s-salām wa minka s-salām, tabārakta yā dha l-jalāli wa l-ikrām »** *« Ô Allah, Toi la Paix, et de Toi vient la paix ; béni sois-Tu, ô Détenteur de la majesté et de la générosité. »** — Muslim"),
            BookNode.Line(BookLineKind.PARAGRAPH, "Et avant cela, l'istighfār ×3 : *« Astaghfiru llāh »* — trois fois, comme un dernier nettoyage avant de repasser la porte du monde. Puis les deux témoignages, et la parole qui fait tomber les chaînes :"),
            BookNode.Line(BookLineKind.QUOTE, "*« Nulle divinité qu'Allah, seul, sans associé ; à Lui la royauté et à Lui la louange, et Il est capable de toute chose. »**"),
            BookNode.Line(BookLineKind.PARAGRAPH, "L'épisode marque ici la sobriété : la clôture est brève. Ce n'est pas elle qui est difficile — c'est de ne pas la transformer en foutoir de habitudes vides : trois mots d'istighfār **pensés** valent mieux que dix récités."),
        )
        ),
        BookChapter("ch30", 30, "p3", "L'assise précieuse", "02:07:08", 7628, 10, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "Dernière station, et la préférée du livre : **al-julūs al-thamīn — l'assise précieuse**."),
            BookNode.Line(BookLineKind.PARAGRAPH, "Le salām est dit. Le monde t'attend — le téléphone, la porte, la journée. Et c'est **exactement maintenant** que se joue le goût de toute ta prière : ne pars pas. Reste assis, une minute, trente secondes."),
            BookNode.Line(BookLineKind.PARAGRAPH, "L'épisode observe un fait que chacun peut vérifier : les gens fuient après le salām — le dernier genou à peine levé, déjà la main sur le téléphone. Pourquoi cette hâte ? Parce que le cœur n'était pas là. **La fuite après le salām est le thermomètre de la prière** — celui qui a rencontré reste ; celui qui a coché s'en va."),
            BookNode.Line(BookLineKind.PARAGRAPH, "Que faire dans cette assise ? Rien de compliqué : trois istighfār, la parole du tawḥīd, une invocation avec tes mots — et **le silence**. Sente ce qui reste. Les salaf appelaient ce moment *l'examen du butin* : la prière était la bataille, et maintenant on regarde ce qu'on ramène. Si tu ne ramènes rien, tu sais quelle station a fui."),
            BookNode.Line(BookLineKind.PARAGRAPH, "C'est cette assise que l'app Dhawq a transformée en rituel produit — *la Minute Précieuse* : un minuteur noir de soixante secondes, rien d'autre. Soixante secondes pour apprendre à ne pas fuir."),
        )
        ),
        BookChapter("epilogue", null, "epilogue", "La dernière page", "02:08:40", 7720, null, false, listOf(
            BookNode.Line(BookLineKind.PARAGRAPH, "L'épisode se ferme comme il s'était ouvert — par une invocation, et par le renvoi à la suite : *le goût du Coran, de l'invocation, du dhikr* (les épisodes 37, 38 et 42 de la même série). Le projet continue."),
            BookNode.Line(BookLineKind.PARAGRAPH, "Et si l'on devait refermer ce livre sur une seule phrase, ce serait celle qui a traversé les deux heures :"),
            BookNode.Line(BookLineKind.QUOTE, "**La prière peut être valable — et creuse. La question n'est jamais « l'as-tu faite ? » mais « l'as-tu goûtée ? » — et le goût n'est pas un don : c'est un voyage qui commence à l'eau des ablutions, se joue dans une Fātiha écoutée comme un dialogue, se couronne dans une prosternation qu'on ne veut plus quitter — et se vérifie à une assise d'une minute après le salām.**"),
            BookNode.Line(BookLineKind.PARAGRAPH, "Ils étaient des hommes comme nous. Ils ont mangé, marché, eu froid, eu des dettes et des enfants. La seule différence est qu'eux **vivaient leur rendez-vous** — et nous, tant de fois, nous le subissons."),
            BookNode.Line(BookLineKind.PARAGRAPH, "Le Roi a fixé l'heure. La prochaine est dans quelques heures."),
            BookNode.Line(BookLineKind.PARAGRAPH, "*Ne sois pas en retard à ton propre rendez-vous.*"),
        )
        ),
        BookChapter("appendice", null, "epilogue", "Les textes cités dans ce livre", null, -1, null, false, listOf(
            BookNode.Table(listOf(BookTableCell(1, "« La première comptabilité : la prière » (Anas)", "al-Tabarānī ; auth. al-Albānī (Ṣaḥīḥ al-Jāmiʿ 2573)"), BookTableCell(2, "« Allah est bon et n'accepte que ce qui est bon »", "Muslim"), BookTableCell(3, "« Réussissent les croyants... humbles dans leur prière »", "Coran 23:1-2"), BookTableCell(4, "« La fraîcheur de mes yeux est dans la prière »", "Ahmad, an-Nasā'ī"), BookTableCell(5, "« Cherchez aide dans la patience et la prière »", "Coran 2:45"), BookTableCell(6, "La prière de la peur", "Coran 4:102"), BookTableCell(7, "Les cinq prières effacent ce qui est entre elles", "Muslim"), BookTableCell(8, "« Ne serais-je pas un serviteur reconnaissant ? » (pieds gonflés)", "Bukhārī & Muslim"), BookTableCell(9, "« Comme s'il ne nous connaissait pas » (ʿĀ'isha)", "Abū Dāwūd"), BookTableCell(10, "Les pas de Bilāl au Paradis", "Bukhārī & Muslim"), BookTableCell(11, "Le wudū' lave les fautes avec l'eau", "Muslim"), BookTableCell(12, "Chaque pas vers la mosquée : un degré, une faute", "Muslim"), BookTableCell(13, "« Quand le dîner est servi, commencez par lui »", "Bukhārī & Muslim"), BookTableCell(14, "La prière divisée : moitié, tiers, quart", "Aḥmad"), BookTableCell(15, "Duʿā' d'ouverture (Subḥānaka...)", "Muslim"), BookTableCell(16, "« Éloigne mes fautes comme l'Orient de l'Occident »", "Bukhārī & Muslim"), BookTableCell(17, "Le hadith qudsī du partage de la Fātiha", "**Muslim 395**"), BookTableCell(18, "« Pas de prière sans la Fātiha »", "Bukhārī & Muslim"), BookTableCell(19, "« Quant à l'inclinaison, magnifiez-y le Seigneur »", "Muslim"), BookTableCell(20, "Duʿā' du rukūʿ (« mon ouïe, ma vue... »)", "Muslim"), BookTableCell(21, "« Allah a entendu celui qui Le loue » + « Rabbanā wa laka l-ḥamd »", "Bukhārī & Muslim"), BookTableCell(22, "« Le plus proche : la prosternation »", "**Muslim 482**"), BookTableCell(23, "« Pardonne-moi toute ma faute » (sujūd)", "Muslim"), BookTableCell(24, "« Jusqu'à ce que chaque vertèbre reprenne sa place »", "Bukhārī"), BookTableCell(25, "Les six demandes de l'assise intermédiaire", "Muslim"), BookTableCell(26, "Le tashahhud d'Ibn Masʿūd", "Bukhārī & Muslim"), BookTableCell(27, "« Allāhumma anta s-salām... »", "Muslim"), BookTableCell(28, "« Ceux qui luttent pour Nous, Nous les guiderons »", "Coran 29:69"), BookTableCell(29, "ʿUrwa : la jambe et le fils", "Siyar Aʿlām an-Nubalā'"), BookTableCell(30, "Ibn al-Zubayr immobile sous les catapultes", "Siyar Aʿlām an-Nubalā'"), BookTableCell(31, "La poutre effondrée (Muslim ibn Yasar)", "Siyar Aʿlām an-Nubalā'"), BookTableCell(32, "La lumière sur la lance (ʿIbbād / Usayd)", "Bukhārī (récit d'Usayd) ; Sunan"))),
        )
        ),
    )

    val BY_ID: Map<String, BookChapter> = CHAPTERS.associateBy { it.id }

    fun indexOf(id: String?): Int = CHAPTERS.indexOfFirst { it.id == id }

    fun chapterAt(index: Int): BookChapter? = CHAPTERS.getOrNull(index)

    /** Chapters woven into a Voyage station (station 4 owns two of them). */
    fun chaptersForStation(station: Int): List<BookChapter> =
        CHAPTERS.filter { it.station == station }
}
