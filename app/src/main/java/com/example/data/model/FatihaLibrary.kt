package com.example.data.model

/**
 * One step of the Al-Fatiha dialogue (Sprint 5, book chapter 22).
 *
 * The hadith qudsi (Muslim n°395) states that Allah answers each verse of
 * Al-Fatiha — the prayer is a conversation, not a recitation. Every verse
 * carries:
 *  * the ayah in Arabic (always displayed, RTL, Amiri), always diacritized;
 *  * a Latin transliteration (pronunciation aid, every language);
 *  * the translated meaning of the ayah (FR/EN — in the Arabic UI the ayah
 *    itself is displayed, no translation needed);
 *  * Allah's answer: the Arabic word of the hadith (displayed as the
 *    "calligraphy" line) + a localized prose line carrying the fuller
 *    statement (like the v7 web prototype, the app's FR copy follows the
 *    book; the hadith's exalté/glorifié vs glorifié/exalté mapping is NOT
 *    the one had in the prototype — verses 2 & 3 were swapped there, the
 *    book (Muslim n°395) puts أثنى = « exalté » on verse 2 and مجّد =
 *    « glorifié » on verse 3 — which is what this data encodes).
 *
 * Data lives here, trilingual, following the [JourneyStation] convention:
 * the screen keeps only layout/chrome, AppStrings carries no verse text.
 */
data class FatihaVerse(
    /** 1..7 — display order of the ayah within the surah. */
    val n: Int,
    /** The ayah itself, diacritized Arabic. */
    val ar: String,
    /** Latin transliteration of the ayah. */
    val translit: String,
    /** Meaning of the ayah — French. */
    val translationFr: String,
    /** Meaning of the ayah — English. */
    val translationEn: String,
    /** Allah's answer in the hadith qudsi, Arabic word (the khat calligraphy line). */
    val responseQuoteAr: String,
    /** Allah's answer, prose — French (opening with "Allah dit :" style). */
    val responseFr: String,
    /** Allah's answer, prose — English. */
    val responseEn: String,
    /** Allah's answer, prose — Arabic (fuller hadith wording, in quotes). */
    val responseArProse: String
)

object FatihaLibrary {

    /** Bismillah — the intro card opening. */
    const val BISMILLAH_AR = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ"

    /** The finale's large Arabic word (Âmîn). */
    const val AMIN_AR = "آمِين"

    val VERSES: List<FatihaVerse> = listOf(
        FatihaVerse(
            n = 1,
            ar = "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ",
            translit = "Alhamdu lillahi Rabbil-'alamin",
            translationFr = "Louange à Allah, Seigneur des mondes.",
            translationEn = "All praise is for Allah, Lord of all worlds.",
            responseQuoteAr = "حَمِدَنِي عَبْدِي",
            responseFr = "Allah dit : « Mon serviteur M'a loué. »",
            responseEn = "Allah says: \u201CMy servant has praised Me.\u201D",
            responseArProse = "قال الله : ﴿ حَمِدَنِي عَبْدِي ﴾"
        ),
        FatihaVerse(
            n = 2,
            ar = "الرَّحْمَٰنِ الرَّحِيمِ",
            translit = "Ar-Rahmanir-Rahim",
            translationFr = "Le Tout Miséricordieux, le Très Miséricordieux.",
            translationEn = "The Most Compassionate, the Most Merciful.",
            responseQuoteAr = "أَثْنَىٰ عَلَيَّ عَبْدِي",
            responseFr = "Allah dit : « Mon serviteur M'a exalté. »",
            responseEn = "Allah says: \u201CMy servant has exalted Me.\u201D",
            responseArProse = "قال الله : ﴿ أَثْنَىٰ عَلَيَّ عَبْدِي ﴾"
        ),
        FatihaVerse(
            n = 3,
            ar = "مَالِكِ يَوْمِ الدِّينِ",
            translit = "Maliki yawmid-din",
            translationFr = "Maître du Jour de la rétribution.",
            translationEn = "Master of the Day of Requital.",
            responseQuoteAr = "مَجَّدَنِي عَبْدِي",
            responseFr = "Allah dit : « Mon serviteur M'a glorifié. »",
            responseEn = "Allah says: \u201CMy servant has glorified Me.\u201D",
            responseArProse = "قال الله : ﴿ مَجَّدَنِي عَبْدِي ﴾"
        ),
        FatihaVerse(
            n = 4,
            ar = "إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ",
            translit = "Iyyaka na'budu wa iyyaka nasta'in",
            translationFr = "C'est Toi que nous adorons, et c'est Toi dont nous implorons le secours.",
            translationEn = "You alone we worship, and You alone we ask for help.",
            responseQuoteAr = "هَٰذَا بَيْنِي وَبَيْنَ عَبْدِي",
            responseFr = "Allah dit : « Ceci est entre Moi et Mon serviteur — et Mon serviteur aura ce qu'il demande. »",
            responseEn = "Allah says: \u201CThis is between Me and My servant — and My servant shall have what he asks for.\u201D",
            responseArProse = "قال الله : ﴿ هَٰذَا بَيْنِي وَبَيْنَ عَبْدِي — وَلِعَبْدِي مَا سَأَلَ ﴾"
        ),
        FatihaVerse(
            n = 5,
            ar = "اهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَ",
            translit = "Ihdinas-siratal-mustaqim",
            translationFr = "Guide-nous sur le droit chemin.",
            translationEn = "Guide us along the Straight Path.",
            responseQuoteAr = "هَٰذَا لِعَبْدِي",
            responseFr = "Allah dit : « Ceci est pour Mon serviteur. »",
            responseEn = "Allah says: \u201CThis is for My servant.\u201D",
            responseArProse = "قال الله : ﴿ هَٰذَا لِعَبْدِي — وَلِعَبْدِي مَا سَأَلَ ﴾"
        ),
        FatihaVerse(
            n = 6,
            ar = "صِرَاطَ الَّذِينَ أَنْعَمْتَ عَلَيْهِمْ",
            translit = "Siratal-ladhina an'amta 'alayhim",
            translationFr = "Le chemin de ceux que Tu as comblés de Tes bienfaits.",
            translationEn = "The path of those You have blessed.",
            responseQuoteAr = "وَلِعَبْدِي مَا سَأَلَ",
            responseFr = "Allah écoute la précision de ta demande — qui veux-tu suivre ?",
            responseEn = "Allah listens to the precision of your request — whom do you wish to follow?",
            responseArProse = "الله يُصْغِي إلى دقّة طلبك — مَن تَريد أن تَتبَعَ؟"
        ),
        FatihaVerse(
            n = 7,
            ar = "غَيْرِ الْمَغْضُوبِ عَلَيْهِمْ وَلَا الضَّالِّينَ",
            translit = "Ghayril-maghdubi 'alayhim wa lad-dallin",
            translationFr = "Non pas celui de ceux qui ont encouru Ta colère, ni celui des égarés. Âmin.",
            translationEn = "Not of those who earned anger, nor of those who went astr. Âmîn.",
            responseQuoteAr = "وَلِعَبْدِي مَا سَأَلَ",
            responseFr = "Allah dit : « Et Mon serviteur aura ce qu'il a demandé. » Dis Âmin — les anges le disent avec toi.",
            responseEn = "Allah says: \u201CAnd My servant shall have what he asked for.\u201D Say Âmîn — the angels say it with you.",
            responseArProse = "قال الله : ﴿ وَلِعَبْدِي مَا سَأَلَ ﴾ — قُل آمين؛ تُقالها معك الملائكة."
        )
    )
}
