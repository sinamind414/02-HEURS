package com.example.data.model

/**
 * ─────────────────────────────────────────────────────────────────────────────
 *  LE VOYAGE DE LA PRIÈRE — Saison 1 de Dhawq (ذوق)
 *
 *  Dix stations pour préparer le cœur, du wudū' à l'assise précieuse — la
 *  structure pédagogique de la série « تذوق العبادات » (épisode 36, 39,5 M de
 *  vues) transformée en parcours quotidien : UNE station avant CHAQUE prière,
 *  puis le téléphone se tait et l'utilisateur va prier sans lui.
 *
 *  Chaque station porte sa source classique (Coran sourate:verset ou recueil
 *  de hadith). Règle éditoriale de Dhawq : pas de source, pas d'écran.
 *  Vérification finale par le comité théologique avant publication.
 * ─────────────────────────────────────────────────────────────────────────────
 */
data class JourneyStation(
    /** 1-based order of the station in the journey. */
    val order: Int,
    /** Arabic name of the station (displayed large, Amiri). */
    val arabicName: String,
    /** Transliterated / French name. */
    val nameFr: String,
    /** English name. */
    val nameEn: String,
    /** One-line meaning in Arabic. */
    val meaningAr: String,
    /** One-line meaning in French. */
    val meaningFr: String,
    /** One-line meaning in English. */
    val meaningEn: String,
    /** Classical reference displayed under the meaning. */
    val sourceRef: String,
    /**
     * Name of the raw audio resource (expected at
     * `app/src/main/res/raw/dhawq_station_01.mp3` etc.). Until the voice is
     * recorded the screen shows a quiet "coming soon" chip — never a broken
     * player.
     */
    val audioResName: String
)

object JourneyStations {

    val ALL: List<JourneyStation> = listOf(
        JourneyStation(
            order = 1,
            arabicName = "الماء",
            nameFr = "L'Eau",
            nameEn = "The Water",
            meaningAr = "الوضوء لا يغسل الجسد وحده، بل يهيّئ القلب للوقوف بين يدي الملك.",
            meaningFr = "Le wudū' ne lave pas seulement le corps : il prépare le cœur à se tenir devant le Roi.",
            meaningEn = "Wudu does not only wash the body: it prepares the heart to stand before the King.",
            sourceRef = "Coran 5:6",
            audioResName = "dhawq_station_01"
        ),
        JourneyStation(
            order = 2,
            arabicName = "المسير",
            nameFr = "La Marche",
            nameEn = "The Walk",
            meaningAr = "كل خطوة نحو الموعد محسوبة: أنت تجيب دعوة ملك الملوك.",
            meaningFr = "Chaque pas vers le Rendez-Vous est compté : tu réponds à une convocation du Roi des rois.",
            meaningEn = "Every step toward the Appointment is counted: you are answering a summons from the King of kings.",
            sourceRef = "Coran 62:9",
            audioResName = "dhawq_station_02"
        ),
        JourneyStation(
            order = 3,
            arabicName = "النداء",
            nameFr = "L'Appel",
            nameEn = "The Call",
            meaningAr = "«حيّ على الصلاة» — دعوة شخصية لك، لا مجرد تنبيه.",
            meaningFr = "« Ḥayya 'ala ṣ-ṣalāh » — viens à la prière : une invitation personnelle, pas une alarme.",
            meaningEn = "\"Hayya 'ala as-salah\" — come to prayer: a personal invitation, not an alarm.",
            sourceRef = "Coran 62:9",
            audioResName = "dhawq_station_03"
        ),
        JourneyStation(
            order = 4,
            arabicName = "الباب",
            nameFr = "La Porte",
            nameEn = "The Door",
            meaningAr = "«الله أكبر»: تترك الدنيا خلف الباب، فلا شيء أكبر منه.",
            meaningFr = "« Allāhu akbar » : tu laisses le monde derrière la porte — rien n'est plus grand que Lui.",
            meaningEn = "\"Allahu akbar\": you leave the world behind the door — nothing is greater than Him.",
            sourceRef = "Signification du takbīr (tafsīr classique)",
            audioResName = "dhawq_station_04"
        ),
        JourneyStation(
            order = 5,
            arabicName = "الاستفتاح",
            nameFr = "L'Ouverture",
            nameEn = "The Opening",
            meaningAr = "تُقرع الباب قبل الدخول: دعاء الاستفتاح يُخضّع القلب الذي جاء ليقف بين يديه.",
            meaningFr = "On frappe avant d'entrer : la du'ā' d'ouverture humble le cœur qui se présente.",
            meaningEn = "One knocks before entering: the opening du'a humbles the heart that comes to present itself.",
            sourceRef = "Sunnah d'ouverture — rapportée par 'Ā'isha (Bukhari & Muslim)",
            audioResName = "dhawq_station_05"
        ),
        JourneyStation(
            order = 6,
            arabicName = "الحوار",
            nameFr = "Le Dialogue",
            nameEn = "The Dialogue",
            meaningAr = "الفاتحة: آية آية تتكلم أنت ويجيب هو — حوار، لا تلاوة فحسب.",
            meaningFr = "Al-Fātiḥa : verset par verset, tu parles et Il répond. Un dialogue, pas une récitation.",
            meaningEn = "Al-Fatiha: verse by verse, you speak and He answers. A dialogue, not a recitation.",
            sourceRef = "Hadith qudsi — Sahih Muslim 395",
            audioResName = "dhawq_station_06"
        ),
        JourneyStation(
            order = 7,
            arabicName = "الركوع",
            nameFr = "L'Inclinaison",
            nameEn = "The Bowing",
            meaningAr = "ينحني الجسد ويشهد القلب: «سبحان ربي العظيم».",
            meaningFr = "Le corps se plie et le cœur témoigne : « Subḥāna Rabbiya l-'Aẓīm » — Gloire à mon Seigneur le Magnifique.",
            meaningEn = "The body folds and the heart witnesses: \"Subhana Rabbiya l-'Azim\" — Glory to my Lord, the Magnificent.",
            sourceRef = "Sunnah du rukū' (Sunan)",
            audioResName = "dhawq_station_07"
        ),
        JourneyStation(
            order = 8,
            arabicName = "القرب",
            nameFr = "La Proximité",
            nameEn = "The Nearness",
            meaningAr = "السجود: ما يكون العبد أقرب إلى ربه منه في هذه الحالة.",
            meaningFr = "Le sujūd : le serviteur n'est jamais aussi proche de son Seigneur que dans cette posture.",
            meaningEn = "Sujud: the servant is never closer to his Lord than in this posture.",
            sourceRef = "Sahih Muslim 482",
            audioResName = "dhawq_station_08"
        ),
        JourneyStation(
            order = 9,
            arabicName = "التشهد",
            nameFr = "Le Salut",
            nameEn = "The Greeting",
            meaningAr = "«التحيات لله»: كأنك ترى النبي ﷺ أمامك فتلقّي عليه السلام.",
            meaningFr = "« At-taḥiyyātu lillāh » : comme si tu voyais le Prophète ﷺ devant toi, et tu lui adresses le salut.",
            meaningEn = "\"At-tahiyyatu lillah\": as if you saw the Prophet ﷺ before you, greeting him with peace.",
            sourceRef = "Tashahhud d'Ibn Mas'ūd (Sahih al-Bukhari)",
            audioResName = "dhawq_station_09"
        ),
        JourneyStation(
            order = 10,
            arabicName = "الجلوس الثمين",
            nameFr = "L'Assise Précieuse",
            nameEn = "The Precious Sitting",
            meaningAr = "ابقَ قليلًا: ثلاث استغفارات وكلمة — كنز الصلاة يُجمع بعد التسليم.",
            meaningFr = "Reste encore un peu : trois istighfār, une parole — le trésor de la prière se ramasse après le salām.",
            meaningEn = "Stay a little: three istighfar, one word — the treasure of the prayer is gathered after the salam.",
            sourceRef = "Dhikr d'après la prière (Sahih Muslim)",
            audioResName = "dhawq_station_10"
        )
    )
}
