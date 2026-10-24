package com.example.data.localization

import com.example.data.model.AppLanguage
import java.util.Locale

/**
 * Central string catalog for the whole application.
 *
 * Every user visible text must come from here so that the FR / AR / EN language
 * switch is honoured everywhere. Screen composables receive the active
 * [AppLanguage] and call the matching accessor.
 */
object AppStrings {

    // ---------------------------------------------------------------------
    // App identity
    // ---------------------------------------------------------------------

    fun appTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "ذَوْق"
        AppLanguage.EN -> "DHAwQ"
        AppLanguage.FR -> "DHAwQ"
    }

    fun appSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "ذَوْق • تَذَوُّقُ الصَّلاة"
        AppLanguage.EN -> "ذَوْق • PRAYER, TASTED"
        AppLanguage.FR -> "ذَوْق • LA PRIÈRE GOÛTÉE"
    }

    fun logoContentDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "شعار ذوق"
        AppLanguage.EN -> "Dhawq logo"
        AppLanguage.FR -> "Logo Dhawq"
    }

    // ---------------------------------------------------------------------
    // Navigation
    // ---------------------------------------------------------------------

    fun navClock(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "العداد"
        AppLanguage.EN -> "Clock"
        AppLanguage.FR -> "Compteur"
    }

    fun navConverter(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "المحوّل"
        AppLanguage.EN -> "Converter"
        AppLanguage.FR -> "Convertisseur"
    }

    fun navJournal(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "اليوميات"
        AppLanguage.EN -> "Journal"
        AppLanguage.FR -> "Journal"
    }

    fun navVisualizer(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "رحلة د2"
        AppLanguage.EN -> "2m Journey"
        AppLanguage.FR -> "Voyage 2m"
    }

    fun navChallenges(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "التحديات"
        AppLanguage.EN -> "Challenges"
        AppLanguage.FR -> "Défis"
    }

    fun navSettings(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "التذكير"
        AppLanguage.EN -> "Reminders"
        AppLanguage.FR -> "Rappels"
    }

    // ---------------------------------------------------------------------
    // Main clock screen
    // ---------------------------------------------------------------------

    fun yourTimeWithAllah(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "🕐 وَقْتُكَ عِنْدَ اللَّه"
        AppLanguage.EN -> "🕐 YOUR TIME WITH ALLAH"
        AppLanguage.FR -> "🕐 TON TEMPS CHEZ ALLAH"
    }

    fun centralConceptQuote(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« حياتك كلها = نحو ساعتين عند الله » (الحج: 47)"
        AppLanguage.EN -> "« Your entire life = ~2 hours with Allah » (Al-Hajj:47)"
        AppLanguage.FR -> "« Ta vie entière = ~2 heures chez Allah » (Al-Hajj:47)"
    }

    fun livedBadge(lang: AppLanguage, percent: Float): String = when (lang) {
        AppLanguage.AR -> String.format(Locale.US, "%.1f%% مضى", percent)
        AppLanguage.EN -> String.format(Locale.US, "%.1f%% LIVED", percent)
        AppLanguage.FR -> String.format(Locale.US, "%.1f%% VÉCU", percent)
    }

    fun comparativeTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "⚖️ المقارنة الزمنية"
        AppLanguage.EN -> "⚖️ TIME COMPARISON"
        AppLanguage.FR -> "⚖️ BILAN TEMPOREL COMPARATIF"
    }

    fun onEarth(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "في الأرض"
        AppLanguage.EN -> "On Earth"
        AppLanguage.FR -> "Sur Terre"
    }

    fun withAllahLived(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "عند الله (المقضي)"
        AppLanguage.EN -> "With Allah (Lived)"
        AppLanguage.FR -> "Chez Allah (Consommé)"
    }

    fun withAllahRemaining(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الوقت الإلهي المتبقي المقدر"
        AppLanguage.EN -> "Estimated Divine Time Left"
        AppLanguage.FR -> "Temps Divin Restant Estimé"
    }

    /**
     * Earth age summary, e.g. "31 ans, 4 mois, 12 jours".
     */
    fun earthAgeSummary(lang: AppLanguage, years: Int, months: Int, days: Long): String = when (lang) {
        AppLanguage.AR -> "$years سنة، $months شهرًا، $days يومًا"
        AppLanguage.EN -> "$years yrs, $months months, $days days"
        AppLanguage.FR -> "$years ans, $months mois, $days jours"
    }

    fun divineElapsedLabel(lang: AppLanguage, hours: Int, minutes: Int, seconds: Int): String = when (lang) {
        AppLanguage.AR -> "$hours س $minutes د $seconds ث"
        AppLanguage.EN -> "${hours}h ${minutes}min ${seconds}sec"
        AppLanguage.FR -> "${hours}h ${minutes}min ${seconds}sec"
    }

    fun divineRemainingLabel(lang: AppLanguage, hours: Int, minutes: Int, seconds: Int): String = when (lang) {
        AppLanguage.AR -> "~$hours س $minutes د $seconds ث"
        AppLanguage.EN -> "~ ${hours}h ${minutes}min ${seconds}sec"
        AppLanguage.FR -> "~ ${hours}h ${minutes}min ${seconds}sec"
    }

    fun impactBannerTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "حقيقة الوقت"
        AppLanguage.EN -> "REALITY OF TIME"
        AppLanguage.FR -> "RÉALITÉ DU TEMPS"
    }

    /**
     * 1 divine second = 1000 earth years / 24h / 365.2425 days
     * => 365242.5 / 86400 = 4.2273 earth days per divine second.
     */
    fun impactBannerText(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« كل ثانية إلهية = 4.23 يوماً أرضياً »"
        AppLanguage.EN -> "« Each divine second = 4.23 earth days »"
        AppLanguage.FR -> "« Chaque seconde divine = 4,23 jours terrestres »"
    }

    fun impactBannerDesc(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "كل نبضة في ساعتك الإلهية تبتلع أكثر من أربعة أيام من الدنيا."
        AppLanguage.EN -> "Every tick of your divine clock consumes more than four days of this world."
        AppLanguage.FR -> "Chaque battement de ton horloge divine engloutit plus de quatre jours d'ici-bas."
    }

    fun minuteDivineQuestion(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "🤲 ماذا قدمت في دقيقتك الإلهية؟"
        AppLanguage.EN -> "🤲 WHAT DID YOU DO WITH YOUR DIVINE MINUTE?"
        AppLanguage.FR -> "🤲 QU'AS-TU FAIT DE TA MINUTE DIVINE ?"
    }

    fun openJournalBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "فتح اليوميات"
        AppLanguage.EN -> "Open Journal"
        AppLanguage.FR -> "Ouvrir le Journal"
    }

    fun watchIn2MinBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "شاهد في دقيقتين"
        AppLanguage.EN -> "Watch in 2 min"
        AppLanguage.FR -> "Voir en 2 min"
    }

    // ---------------------------------------------------------------------
    // Prayer times (Aladhan)
    // ---------------------------------------------------------------------

    fun nextPrayerLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الصلاة القادمة"
        AppLanguage.EN -> "NEXT PRAYER"
        AppLanguage.FR -> "PROCHAINE PRIÈRE"
    }

    fun inCountdown(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "بعد"
        AppLanguage.EN -> "IN"
        AppLanguage.FR -> "DANS"
    }

    fun countdownLabel(lang: AppLanguage, countdown: String): String = when (lang) {
        AppLanguage.AR -> "بعد $countdown"
        AppLanguage.EN -> "IN $countdown"
        AppLanguage.FR -> "DANS $countdown"
    }

    fun divineWaiting(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الانتظار عند الله"
        AppLanguage.EN -> "Waiting with Allah"
        AppLanguage.FR -> "Attente chez Allah"
    }

    fun divineWaitingLabel(lang: AppLanguage, divineValue: String): String = when (lang) {
        AppLanguage.AR -> "الانتظار عند الله: $divineValue"
        AppLanguage.EN -> "Waiting with Allah: $divineValue"
        AppLanguage.FR -> "Attente chez Allah : $divineValue"
    }

    fun cityPrayerTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "مدينة مواقيت الصلاة"
        AppLanguage.EN -> "Prayer Times City"
        AppLanguage.FR -> "VILLE POUR LES PRIÈRES"
    }

    fun cityPrayerSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تُحسب المواقيت عبر واجهة Aladhan حسب مدينتك."
        AppLanguage.EN -> "Timings are computed by the Aladhan API for the city you select."
        AppLanguage.FR -> "Horaires calculés via l'API Aladhan selon ta localisation."
    }

    fun fastCities(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "مدن سريعة :"
        AppLanguage.EN -> "Quick cities:"
        AppLanguage.FR -> "Villes rapides :"
    }

    fun cityContentDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "المدينة"
        AppLanguage.EN -> "City"
        AppLanguage.FR -> "Ville"
    }

    fun refreshContentDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تحديث"
        AppLanguage.EN -> "Refresh"
        AppLanguage.FR -> "Actualiser"
    }

    fun prayerTimesFallbackBadge(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "⚠️ مواقيت احتياطية (تعذّر الاتصال)"
        AppLanguage.EN -> "⚠️ Offline fallback times"
        AppLanguage.FR -> "⚠️ Horaires de repli (réseau indisponible)"
    }

    fun prayerTimesError(lang: AppLanguage, error: String): String = when (lang) {
        AppLanguage.AR -> "تعذّر جلب المواقيت: $error"
        AppLanguage.EN -> "Could not fetch prayer times: $error"
        AppLanguage.FR -> "Impossible de récupérer les horaires : $error"
    }

    fun applyBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تطبيق"
        AppLanguage.EN -> "Apply"
        AppLanguage.FR -> "Appliquer"
    }

    fun cancelBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "إلغاء"
        AppLanguage.EN -> "Cancel"
        AppLanguage.FR -> "Annuler"
    }

    fun saveBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "حفظ"
        AppLanguage.EN -> "Save"
        AppLanguage.FR -> "Enregistrer"
    }

    // ---------------------------------------------------------------------
    // Two divine hours life map
    // ---------------------------------------------------------------------

    fun lifeMapTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "رحلة الساعتين الإلهيتين"
        AppLanguage.EN -> "THE 2 DIVINE HOURS JOURNEY"
        AppLanguage.FR -> "LE VOYAGE DES 2 HEURES DIVINES"
    }

    fun lifeMapSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "حياتك كلها (~83 سنة) مرسومة في ساعتين عند الله (120 دقيقة)"
        AppLanguage.EN -> "Your whole life (~83 years) mapped into 2 hours with Allah (120 min)"
        AppLanguage.FR -> "Ta vie entière (~83 ans) projetée sur 2 heures chez Allah (120 min)"
    }

    fun myAgeBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "عمري"
        AppLanguage.EN -> "My Age"
        AppLanguage.FR -> "Mon Âge"
    }

    fun exploreBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "استكشاف"
        AppLanguage.EN -> "Explore"
        AppLanguage.FR -> "Explorer"
    }

    fun exploreContentDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "استكشاف"
        AppLanguage.EN -> "Explore"
        AppLanguage.FR -> "Explorer"
    }

    fun exploredAgeText(lang: AppLanguage, age: Int): String = when (lang) {
        AppLanguage.AR -> "العمر المستكشف: $age سنة"
        AppLanguage.EN -> "Explored age: $age years"
        AppLanguage.FR -> "Âge exploré : $age ans"
    }

    fun divineMinutesGauge(lang: AppLanguage, elapsed: Double): String = when (lang) {
        AppLanguage.AR -> String.format(Locale.US, "%.1f د / 120 د إلهية", elapsed)
        AppLanguage.EN -> String.format(Locale.US, "%.1f min / 120 divine min", elapsed)
        AppLanguage.FR -> String.format(Locale.US, "%.1f min / 120 min divines", elapsed)
    }

    fun hour1Title(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الساعة الأولى (0 إلى ~42 سنة)"
        AppLanguage.EN -> "1st Divine Hour (0 to ~42 yrs)"
        AppLanguage.FR -> "1ère Heure Divine (0 à ~42 ans)"
    }

    fun hour2Title(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الساعة الثانية (~42 إلى ~83 سنة)"
        AppLanguage.EN -> "2nd Divine Hour (~42 to ~83 yrs)"
        AppLanguage.FR -> "2ème Heure Divine (~42 à ~83 ans)"
    }

    fun statusInProgress(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "جارية ⚡"
        AppLanguage.EN -> "In progress ⚡"
        AppLanguage.FR -> "En cours ⚡"
    }

    fun statusCompleted(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "مكتملة ✓"
        AppLanguage.EN -> "Completed ✓"
        AppLanguage.FR -> "Terminée ✓"
    }

    fun statusUpcoming(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "قادمة ⏳"
        AppLanguage.EN -> "Upcoming ⏳"
        AppLanguage.FR -> "À venir ⏳"
    }

    fun lifeMapStartLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "0h00 (الولادة)"
        AppLanguage.EN -> "0h00 (Birth)"
        AppLanguage.FR -> "0h00 (Naissance)"
    }

    fun lifeMapHour1Label(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "1h00 (~42 سنة)"
        AppLanguage.EN -> "1h00 (~42 yrs)"
        AppLanguage.FR -> "1h00 (~42 ans)"
    }

    fun lifeMapHour2Label(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "2h00 (~83 سنة)"
        AppLanguage.EN -> "2h00 (~83 yrs)"
        AppLanguage.FR -> "2h00 (~83 ans)"
    }

    fun lifeMapMinuteLabel(lang: AppLanguage, minute: Int): String = when (lang) {
        AppLanguage.AR -> "الدقيقة $minute / 120"
        AppLanguage.EN -> "Minute $minute / 120"
        AppLanguage.FR -> "Minute $minute / 120"
    }

    fun remainingInHourLabel(lang: AppLanguage, minutes: Int): String = when (lang) {
        AppLanguage.AR -> "المتبقي في الساعة: $minutes د"
        AppLanguage.EN -> "Remaining in this hour: $minutes min"
        AppLanguage.FR -> "Reste dans l'heure : $minutes min"
    }

    fun remainingInTwoHoursLabel(lang: AppLanguage, minutes: Double): String = when (lang) {
        AppLanguage.AR -> "المتبقي على الساعتين: ${String.format(Locale.US, "%.1f", minutes)} د"
        AppLanguage.EN -> "Remaining of the 2h: ${String.format(Locale.US, "%.1f", minutes)} min"
        AppLanguage.FR -> "Reste sur les 2h : ${String.format(Locale.US, "%.1f", minutes)} min"
    }

    fun hourNumberLabel(lang: AppLanguage, hourNumber: Int): String = when (lang) {
        AppLanguage.AR -> "الساعة $hourNumber"
        AppLanguage.EN -> "HOUR $hourNumber"
        AppLanguage.FR -> "HEURE $hourNumber"
    }

    fun hourProgressLabel(lang: AppLanguage, minuteElapsed: Int): String = when (lang) {
        AppLanguage.AR -> "$minuteElapsed / 60 د"
        AppLanguage.EN -> "$minuteElapsed / 60 min"
        AppLanguage.FR -> "$minuteElapsed / 60 min"
    }

    // ---------------------------------------------------------------------
    // Profile dialog
    // ---------------------------------------------------------------------

    fun profileDialogTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "⚙️ الملف الروحي وتاريخ الميلاد"
        AppLanguage.EN -> "⚙️ Spiritual Profile & Birth Date"
        AppLanguage.FR -> "⚙️ PROFIL SPIRITUEL & NAISSANCE"
    }

    fun birthDateDialogTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "⚙️ تاريخ الميلاد والعمر المتوقع"
        AppLanguage.EN -> "⚙️ BIRTH DATE & LIFE EXPECTANCY"
        AppLanguage.FR -> "⚙️ DATE DE NAISSANCE & ESPÉRANCE"
    }

    fun birthDateDialogSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "اضبط تاريخ ميلادك لحساب وقتك الإلهي بدقة عند الله."
        AppLanguage.EN -> "Adjust your birth date to compute your divine time with Allah precisely."
        AppLanguage.FR -> "Ajuste ta date de naissance pour calculer précisément ton temps divin chez Allah."
    }

    fun birthDateLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تاريخ ميلادك"
        AppLanguage.EN -> "Your Birth Date"
        AppLanguage.FR -> "Ta date de naissance :"
    }

    fun dayLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "اليوم"
        AppLanguage.EN -> "Day"
        AppLanguage.FR -> "Jour"
    }

    fun monthLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الشهر"
        AppLanguage.EN -> "Month"
        AppLanguage.FR -> "Mois"
    }

    fun yearLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "السنة"
        AppLanguage.EN -> "Year"
        AppLanguage.FR -> "Année"
    }

    fun birthYearValueLabel(lang: AppLanguage, year: Int): String = when (lang) {
        AppLanguage.AR -> "سنة الميلاد: $year"
        AppLanguage.EN -> "Birth year: $year"
        AppLanguage.FR -> "Année de naissance : $year"
    }

    fun birthMonthValueLabel(lang: AppLanguage, month: Int): String = when (lang) {
        AppLanguage.AR -> "الشهر: $month"
        AppLanguage.EN -> "Month: $month"
        AppLanguage.FR -> "Mois : $month"
    }

    fun birthDayValueLabel(lang: AppLanguage, day: Int): String = when (lang) {
        AppLanguage.AR -> "اليوم: $day"
        AppLanguage.EN -> "Day: $day"
        AppLanguage.FR -> "Jour : $day"
    }

    fun dialMilestoneBirth(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الولادة"
        AppLanguage.EN -> "Birth"
        AppLanguage.FR -> "Naissance"
    }

    fun dialMilestoneYears(lang: AppLanguage, years: Int): String = when (lang) {
        AppLanguage.AR -> "≈$years سنة"
        AppLanguage.EN -> "~$years yrs"
        AppLanguage.FR -> "~$years ans"
    }

    fun lifeExpectancyLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "العمر المقدر للحساب :"
        AppLanguage.EN -> "Estimated life expectancy:"
        AppLanguage.FR -> "Espérance de vie estimée :"
    }

    fun lifeExpectancyValueLabel(lang: AppLanguage, years: Int): String = when (lang) {
        AppLanguage.AR -> "العمر المقدر: $years سنة"
        AppLanguage.EN -> "Estimated life expectancy: $years years"
        AppLanguage.FR -> "Espérance de vie estimée : $years ans"
    }

    fun yearsUnit(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "سنة"
        AppLanguage.EN -> "years"
        AppLanguage.FR -> "ans"
    }

    fun ageYearsLabel(lang: AppLanguage, years: Int): String = when (lang) {
        AppLanguage.AR -> "العمر: $years سنة"
        AppLanguage.EN -> "Age: $years yrs"
        AppLanguage.FR -> "Âge : $years ans"
    }

    fun prophetLifeSpanQuote(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« قال النبي ﷺ: عمر أمتي بين ستين وسبعين سنة »"
        AppLanguage.EN -> "« The Prophet ﷺ said: the lifespan of my community is between sixty and seventy years. »"
        AppLanguage.FR -> "« Le Prophète ﷺ a dit : « La durée de vie de ma communauté se situe entre 60 et 70 ans. »"
    }

    fun languageLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "لغة التطبيق :"
        AppLanguage.EN -> "App Language:"
        AppLanguage.FR -> "Langue de l'application :"
    }

    fun birthSettingsContentDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "إعدادات تاريخ الميلاد"
        AppLanguage.EN -> "Birth date settings"
        AppLanguage.FR -> "Paramètres de naissance"
    }

    fun editContentDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تعديل"
        AppLanguage.EN -> "Edit"
        AppLanguage.FR -> "Modifier"
    }

    fun cityFieldLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "المدينة (مثال: مكة، القاهرة، الدار البيضاء)"
        AppLanguage.EN -> "City (e.g. Paris, Lyon, Casablanca)"
        AppLanguage.FR -> "Ville (ex : Paris, Lyon, Casablanca)"
    }

    fun countryFieldLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الدولة (مثال: فرنسا، المغرب، الجزائر)"
        AppLanguage.EN -> "Country (e.g. France, Morocco, Algeria)"
        AppLanguage.FR -> "Pays (ex : France, Maroc, Algérie)"
    }

    fun dayModeContentDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الوضع النهاري"
        AppLanguage.EN -> "Day mode"
        AppLanguage.FR -> "Mode Jour"
    }

    fun nightModeContentDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الوضع الليلي"
        AppLanguage.EN -> "Night mode"
        AppLanguage.FR -> "Mode Nuit"
    }

    // ---------------------------------------------------------------------
    // Languages modal
    // ---------------------------------------------------------------------

    fun selectLanguageTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "اختر اللغة • Language"
        AppLanguage.EN -> "Select Language • اختر اللغة"
        AppLanguage.FR -> "Choisir la Langue • اختر اللغة"
    }

    fun selectLanguageContentDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "اختيار اللغة"
        AppLanguage.EN -> "Language selection"
        AppLanguage.FR -> "Sélection de la langue"
    }

    /**
     * Short blurb shown next to each language in the picker. It is written in
     * the language it describes, so the picker is readable whatever the
     * current UI language is.
     */
    fun languageDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "لغة القرآن الكريم والتوقيت الإلهي"
        AppLanguage.EN -> "Universal Divine Time perspective"
        AppLanguage.FR -> "Langue par défaut de l'application"
    }

    // ---------------------------------------------------------------------
    // Converter screen
    // ---------------------------------------------------------------------

    fun converterTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "محوّل التوقيت الإلهي"
        AppLanguage.EN -> "TEMPORAL CONVERTER"
        AppLanguage.FR -> "CONVERTISSEUR DE TEMPS"
    }

    fun converterSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "قِس مدتك الأرضية على مقياس القضاء الإلهي"
        AppLanguage.EN -> "Measure earthly duration against the scale of the Divine Decree"
        AppLanguage.FR -> "Mesure la durée terrestre à l'échelle du Décret Divin"
    }

    fun tabEarthToDivine(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الأرض → الإلهي"
        AppLanguage.EN -> "Earth → Divine"
        AppLanguage.FR -> "Terrestre → Divin"
    }

    fun tabDivineToEarth(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الإلهي → الأرض"
        AppLanguage.EN -> "Divine → Earth"
        AppLanguage.FR -> "Divin → Terrestre"
    }

    fun earthDurationTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "أدخل المدة الأرضية"
        AppLanguage.EN -> "Enter Earth Duration"
        AppLanguage.FR -> "Entre une durée terrestre :"
    }

    fun divineDurationTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "أدخل الدقائق الإلهية"
        AppLanguage.EN -> "Enter Divine Minutes"
        AppLanguage.FR -> "Entre des minutes divines :"
    }

    fun equivalentWithAllah(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "المعادل عند الله :"
        AppLanguage.EN -> "Equivalent with Allah:"
        AppLanguage.FR -> "Équivalent auprès d'Allah :"
    }

    fun equivalentWithAllahTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "⚖️ المعادل بالزمن الإلهي عند الله"
        AppLanguage.EN -> "⚖️ EQUIVALENT IN DIVINE TIME WITH ALLAH"
        AppLanguage.FR -> "⚖️ ÉQUIVALENT EN TEMPS DIVIN CHEZ ALLAH"
    }

    fun equivalentOnEarth(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "المعادل على الأرض :"
        AppLanguage.EN -> "Equivalent on Earth:"
        AppLanguage.FR -> "Équivalent sur terre :"
    }

    fun equivalentOnEarthTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "🌍 المعادل بالزمن الأرضي للناس"
        AppLanguage.EN -> "🌍 EQUIVALENT IN EARTHLY TIME FOR HUMANS"
        AppLanguage.FR -> "🌍 ÉQUIVALENT SUR TERRE POUR LES HOMMES"
    }

    fun strikingBenchmarksTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "📊 أمثلة حية مؤثرة"
        AppLanguage.EN -> "📊 STRIKING BENCHMARKS"
        AppLanguage.FR -> "📊 EXEMPLES FRAPPANTS"
    }

    fun strikingBenchmarksSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "اضغط على مثال لحظه في الحساب فوراً"
        AppLanguage.EN -> "Tap an example to inject it into the calculator instantly"
        AppLanguage.FR -> "Touche un exemple pour l'injecter instantanément dans le calcul"
    }

    fun yearsFieldLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "سنوات"
        AppLanguage.EN -> "Years"
        AppLanguage.FR -> "Années"
    }

    fun daysFieldLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "أيام"
        AppLanguage.EN -> "Days"
        AppLanguage.FR -> "Jours"
    }

    fun minutesFieldLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "دقائق"
        AppLanguage.EN -> "Minutes"
        AppLanguage.FR -> "Minutes"
    }

    fun divineMinutesFieldLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "دقائق إلهية"
        AppLanguage.EN -> "Divine minutes"
        AppLanguage.FR -> "Minutes divines"
    }

    // ---------------------------------------------------------------------
    // Journal screen
    // ---------------------------------------------------------------------

    fun journalTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "يوميات الدقائق الإلهية"
        AppLanguage.EN -> "DIVINE MINUTES JOURNAL"
        AppLanguage.FR -> "JOURNAL DES MINUTES DIVINES"
    }

    /**
     * One earth day = 86.4 / 365.2425 = 0.236554 divine seconds.
     */
    fun todayIsLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "اليوم = 0.2366 ثانية إلهية"
        AppLanguage.EN -> "Today = 0.2366 divine second"
        AppLanguage.FR -> "Aujourd'hui = 0.2366 sec divine"
    }

    fun howFilledQuestion(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "كيف عمرت هذه اللحظة الخاطفة؟"
        AppLanguage.EN -> "How did you fill this fraction of a second?"
        AppLanguage.FR -> "Comment as-tu rempli cette fraction de seconde ?"
    }

    fun prayerChecklistTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الصلوات الخمس في وقتها"
        AppLanguage.EN -> "5 Prayers on Time"
        AppLanguage.FR -> "LES 5 PRIÈRES DU JOUR"
    }

    fun hassanatSectionTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "✨ الحسنات والتطهير"
        AppLanguage.EN -> "✨ HASSANAT & SANCTIFICATION"
        AppLanguage.FR -> "✨ HASSANAT & SANCTIFICATION"
    }

    fun quranPagesTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "📖 قراءة القرآن الكريم"
        AppLanguage.EN -> "📖 Reading the Holy Quran"
        AppLanguage.FR -> "📖 Lecture du Saint Coran"
    }

    fun quranHasanatNote(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "كل حرف = 10 حسنات"
        AppLanguage.EN -> "Every letter = 10 hassanat"
        AppLanguage.FR -> "Chaque lettre = 10 hassanat"
    }

    fun pagesUnit(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "صفحات"
        AppLanguage.EN -> "pages"
        AppLanguage.FR -> "pages"
    }

    fun pagesReadLabel(lang: AppLanguage, pages: Int): String = when (lang) {
        AppLanguage.AR -> "$pages ص"
        AppLanguage.EN -> "$pages p."
        AppLanguage.FR -> "$pages p."
    }

    fun sadaqaDone(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "صدقة أو عمل خير"
        AppLanguage.EN -> "Charity or Sadaqa given"
        AppLanguage.FR -> "Sadaqa / Aumône donnée"
    }

    fun eveningDhikr(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "أذكار الصباح والمساء"
        AppLanguage.EN -> "Morning & Evening Dhikr"
        AppLanguage.FR -> "Dhikr du soir & matin"
    }

    fun goodDeed(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "إحسان وخلق طيب للآخرين"
        AppLanguage.EN -> "Good deed towards others"
        AppLanguage.FR -> "Bonne action envers autrui"
    }

    fun tasbihTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "📿 المسبحة الإلهية"
        AppLanguage.EN -> "📿 Divine Tasbih"
        AppLanguage.FR -> "📿 TASBIH DIVIN DU JOUR"
    }

    fun tasbihTapsToday(lang: AppLanguage, count: Int): String = when (lang) {
        AppLanguage.AR -> "تسبيحات اليوم: $count"
        AppLanguage.EN -> "Taps today: $count"
        AppLanguage.FR -> "Tours aujourd'hui : $count"
    }

    fun tasbihTapLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "اضغط"
        AppLanguage.EN -> "TAP"
        AppLanguage.FR -> "TAP"
    }

    fun tasbihHadith(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« أَفَضَلُ الذِّكْرِ سُبْحَانَ اللَّهِ وَالْحَمْدُ لِلَّهِ »"
        AppLanguage.EN -> "« The best remembrance is: SubhanAllah and Alhamdulillah. »"
        AppLanguage.FR -> "« Le dhikr le plus pharmacology est : SubhanAllah et Alhamdulillah. »"
    }

    /**
     * 1 divine second per tasbih = 1 earth second worth of praise.
     * 5 minutes of prayer = 5 * DIVINE_SECONDS_PER_EARTH_MINUTE ≈ 0.000821 divine seconds.
     */
    fun tasbihDivineEquivalent(lang: AppLanguage, divineSeconds: Double): String = when (lang) {
        AppLanguage.AR -> "المعادل المقدَّس: ${String.format(Locale.US, "%.4f", divineSeconds)} ثانية إلهية من الثواب"
        AppLanguage.EN -> "Sanctified equivalent: ${String.format(Locale.US, "%.4f", divineSeconds)} divine seconds of praise"
        AppLanguage.FR -> "Équivalent sanctifié : ${String.format(Locale.US, "%.4f", divineSeconds)} sec divine d'éloges"
    }

    fun resetBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "إعادة ضبط"
        AppLanguage.EN -> "Reset"
        AppLanguage.FR -> "Réinitialiser"
    }

    fun dailyScoreLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تقييم اليوم الإلهي :"
        AppLanguage.EN -> "Divine Daily Score:"
        AppLanguage.FR -> "SCORE DIVIN DU JOUR"
    }

    fun doNotWasteQuote(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« لا تُضع ثانية إلهية واحدة في معصية الخالق »"
        AppLanguage.EN -> "« Do not waste a single divine second in sin »"
        AppLanguage.FR -> "« Ne gaspille pas une seconde divine dans le péché »"
    }

    fun notesLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "خواطر ومحاسبة النفس"
        AppLanguage.EN -> "Spiritual Notes & Reflections"
        AppLanguage.FR -> "Notes & Bilan personnel"
    }

    fun notesPlaceholder(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "ماذا ادخرت اليوم في صحيفتك؟"
        AppLanguage.EN -> "What did you prepare today for your hereafter?"
        AppLanguage.FR -> "Qu'as-tu semé aujourd'hui pour l'éternité ?"
    }

    // ---------------------------------------------------------------------
    // Visualizer screen
    // ---------------------------------------------------------------------

    fun visualizerTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "المشهد المرئي للحياة (دقيقتان)"
        AppLanguage.EN -> "IMMERSIVE 2-MINUTE LIFE"
        AppLanguage.FR -> "VISUALISE TA VIE EN 2 MINUTES"
    }

    fun visualizerSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "حياتك كلها تمر في 120 ثانية إلهية"
        AppLanguage.EN -> "Watch your entire life pass in 120 divine seconds"
        AppLanguage.FR -> "Toute ta vie résumée en 120 secondes divines"
    }

    fun startSimulationBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "بدء الرحلة"
        AppLanguage.EN -> "Start Simulation"
        AppLanguage.FR -> "Lancer l'animation"
    }

    fun pauseSimulationBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "إيقاف مؤقت"
        AppLanguage.EN -> "Pause"
        AppLanguage.FR -> "Pause"
    }

    fun resetSimulationContentDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "إعادة الضبط"
        AppLanguage.EN -> "Reset"
        AppLanguage.FR -> "Réinitialiser"
    }

    fun lifeJourneyComplete(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "لقد شاهدت حياتك كاملة في زمن إلهي حقيقي (دقيقتان)."
        AppLanguage.EN -> "You have just watched your entire life in divine real time (2 min)."
        AppLanguage.FR -> "Tu viens de voir ta vie entière en temps réel divin (2 min)."
    }

    fun earthAgeApproxLabel(lang: AppLanguage, years: Int): String = when (lang) {
        AppLanguage.AR -> "العمر الأرضي: ~$years سنة"
        AppLanguage.EN -> "Earthly age: ~$years yrs"
        AppLanguage.FR -> "Âge terrestre : ~$years ans"
    }

    fun quranVerseLabel(lang: AppLanguage, verse: String): String = when (lang) {
        AppLanguage.AR -> "📖 $verse"
        AppLanguage.EN -> "📖 $verse"
        AppLanguage.FR -> "📖 $verse"
    }

    fun journeyStepsTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "🕰 مراحل الرحلة الأرضية"
        AppLanguage.EN -> "🕰 STEPS OF THE EARTHLY JOURNEY"
        AppLanguage.FR -> "🕰 ÉTAPES DU VOYAGE TERRESTRE"
    }

    fun twoHourLiveModeTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "⏳ وضع الساعتين المباشر"
        AppLanguage.EN -> "⏳ LIVE 2-HOUR MODE"
        AppLanguage.FR -> "⏳ LE MODE 2 HEURES EN DIRECT"
    }

    fun twoHourLiveModeSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "فعّل رملاً زمنياً من ساعتين حقيقيتين يرمز لحياتك كلها على الأرض."
        AppLanguage.EN -> "Activate a real 2-hour hourglass symbolising your whole life on Earth."
        AppLanguage.FR -> "Active un sablier de 2h réelles symbolisant toute ta vie sur Terre."
    }

    fun twoHourCrossingLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "أنت تعبر رمزياً وجودك..."
        AppLanguage.EN -> "You are symbolically crossing your existence..."
        AppLanguage.FR -> "Tu traverses symboliquement ton existence..."
    }

    // ---------------------------------------------------------------------
    // Challenges screen
    // ---------------------------------------------------------------------

    fun challengesTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "سباق الساعتين"
        AppLanguage.EN -> "THE 2-HOUR RACE"
        AppLanguage.FR -> "LA COURSE DES 2 HEURES"
    }

    fun challengesSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "سارع إلى الخيرات والدرجات العلى قبل انقضاء ساعتك"
        AppLanguage.EN -> "Strive in good deeds before your 2 divine hours end"
        AppLanguage.FR -> "Remplis tes 2 heures divines d'œuvres éternelles"
    }

    fun levelLabel(lang: AppLanguage, level: Int): String = when (lang) {
        AppLanguage.AR -> "المستوى $level"
        AppLanguage.EN -> "Level $level"
        AppLanguage.FR -> "Niveau $level"
    }

    fun levelNameLabel(lang: AppLanguage, level: Int, levelName: String): String = when (lang) {
        AppLanguage.AR -> "المستوى $level • $levelName"
        AppLanguage.EN -> "Level $level • $levelName"
        AppLanguage.FR -> "Niveau $level • $levelName"
    }

    fun hassanatUnlockedTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الحسنات المفتوحة"
        AppLanguage.EN -> "HASSANAT UNLOCKED"
        AppLanguage.FR -> "HASSANAT DÉBLOQUÉES"
    }

    fun challengesCompletedLabel(lang: AppLanguage, completed: Int, total: Int): String = when (lang) {
        AppLanguage.AR -> "$completed من $total تحديات أُنجزت اليوم"
        AppLanguage.EN -> "$completed of $total challenges completed today"
        AppLanguage.FR -> "$completed / $total Défis Accomplis Aujourd'hui"
    }

    fun levelsAndBadgesTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "🕌 المستويات والأوسمة الروحية"
        AppLanguage.EN -> "🕌 LEVELS & SPIRITUAL BADGES"
        AppLanguage.FR -> "🕌 NIVEAUX & BADGES SPIRITUELS"
    }

    fun badgeLabel(lang: AppLanguage, badgeName: String): String = when (lang) {
        AppLanguage.AR -> "وسام: $badgeName"
        AppLanguage.EN -> "Badge: $badgeName"
        AppLanguage.FR -> "Badge : $badgeName"
    }

    // ---------------------------------------------------------------------
    // Prayer motivator card
    // ---------------------------------------------------------------------

    fun prayerMotivatorTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "⚡ مُحفّز الصلاة في الميزان الإلهي"
        AppLanguage.EN -> "⚡ DIVINE PRAYER MOTIVATOR"
        AppLanguage.FR -> "⚡ LE GRAND MOTIVEUR DE PRIÈRE"
    }

    fun prayerMotivatorSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "لماذا لا تملك عذراً لتأخير الصلاة بعد اليوم؟"
        AppLanguage.EN -> "Why you will never have an excuse to delay prayer again"
        AppLanguage.FR -> "Pourquoi tu n'auras plus jamais d'excuse pour retarder ta prière"
    }

    fun shockFiguresTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "⚡ صدمة الأرقام"
        AppLanguage.EN -> "⚡ THE SHOCK OF THE FIGURES"
        AppLanguage.FR -> "⚡ LE CHOC DES CHIFFRES"
    }

    fun oneDayEqualsThousandYears(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "يوم واحد = 1000 سنة"
        AppLanguage.EN -> "1 day = 1000 years"
        AppLanguage.FR -> "1 jour = 1000 ans"
    }

    /**
     * 5 earth minutes = 5 * DIVINE_SECONDS_PER_EARTH_MINUTE ≈ 0.000821 divine seconds.
     */
    fun prayerFiveMinutesShock(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "5 دقائق صلاة = 0.0008 ثانية إلهية فقط !"
        AppLanguage.EN -> "5 min of prayer = Only 0.0008 divine seconds!"
        AppLanguage.FR -> "5 minutes de prière = Seulement 0.0008 sec divine !"
    }

    /**
     * 5 daily prayers over 80 years ≈ 2.00 divine minutes out of 115.2 (≈ 1.7 % of life).
     */
    fun prayerLifetimeShock(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "80 سنة من الصلوات الخمس = نحو دقيقتين فقط عند الله !"
        AppLanguage.EN -> "80 years of 5 daily prayers = Only ~2 divine minutes with Allah!"
        AppLanguage.FR -> "80 ans de prières = Moins de 2 minutes chez Allah !"
    }

    fun prayerShockCallout(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« أنعم الله عليك بـ 120 دقيقة إلهية في عمرك، فهل تبخل عليه بدقيقتين لشراء الجنة الأبدية؟ »"
        AppLanguage.EN -> "« Allah granted you 120 divine minutes. Will you withhold 2 minutes to secure eternal Paradise? »"
        AppLanguage.FR -> "« Allah t'a accordé 120 minutes divines. Lui refuseras-tu 2 minutes pour acheter le Paradis éternel ? »"
    }

    fun todayPrayerInvestment(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "استثمارك في الصلوات اليوم"
        AppLanguage.EN -> "Today's Prayer Investment"
        AppLanguage.FR -> "Ton investissement prière aujourd'hui"
    }

    fun prayersDoneRatio(lang: AppLanguage, done: Int): String = when (lang) {
        AppLanguage.AR -> "$done من 5 صلوات أُقيمت"
        AppLanguage.EN -> "$done of 5 prayers completed"
        AppLanguage.FR -> "$done / 5 prières accomplies"
    }

    fun divineSecondsInvested(lang: AppLanguage, secondsStr: String): String = when (lang) {
        AppLanguage.AR -> "$secondsStr ثانية إلهية مدخرة للآخرة"
        AppLanguage.EN -> "$secondsStr divine sec invested for eternity"
        AppLanguage.FR -> "$secondsStr sec divines investies pour l'éternité"
    }

    fun screenVsPrayerTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "مقارنة الصدمة : الهاتف مقابل الصلاة"
        AppLanguage.EN -> "Reality Shock: Screen Time vs Prayer"
        AppLanguage.FR -> "Le Choc : Téléphone vs Prière"
    }

    fun screenLifetimeStat(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "📱 3 ساعات هاتف يومياً = 15 دقيقة إلهية في حياتك (13% من عمرك)"
        AppLanguage.EN -> "📱 3h phone daily = 15 divine minutes in lifetime (13% of life)"
        AppLanguage.FR -> "📱 3h d'écran / jour = 15 minutes divines sur ta vie (13%)"
    }

    fun prayerLifetimeStat(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "🕌 الصلوات الخمس يومياً = دقيقتان إلهيتان فقط (1.7% من عمرك)"
        AppLanguage.EN -> "🕌 5 prayers daily = Only 2 divine minutes (1.7% of life)"
        AppLanguage.FR -> "🕌 5 prières / jour = SEULEMENT 2 minutes divines (1,7%)"
    }

    fun priorityQuestion(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "كيف تمنح الشاشات 15 دقيقة وتستثقل دقيقتين لربك؟"
        AppLanguage.EN -> "How can we give 15 minutes to screens and withhold 2 minutes from Allah?"
        AppLanguage.FR -> "Comment donner 15 minutes aux écrans et hésiter à donner 2 minutes à Allah ?"
    }

    fun prayerJudgementHadith(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« أول ما يُحاسب عليه عبدُ الله الصلاة » (حديث)"
        AppLanguage.EN -> "« The first thing a servant will be brought to account for is the prayer. » (Hadith)"
        AppLanguage.FR -> "« La première chose sur laquelle l'homme sera jugé est sa prière » (Hadith)"
    }

    // ---------------------------------------------------------------------
    // Clock dial + breakdown chart
    // ---------------------------------------------------------------------

    fun dialTotalMinutesLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "120 د"
        AppLanguage.EN -> "120 MIN"
        AppLanguage.FR -> "120 MIN"
    }

    fun dialHourLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "ساعة إلهية"
        AppLanguage.EN -> "DIVINE HOUR"
        AppLanguage.FR -> "HEURE DIVINE"
    }

    fun thousandYearsEquals24Hours(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "1000 سنة = 24 س"
        AppLanguage.EN -> "1000 YEARS = 24H"
        AppLanguage.FR -> "1000 ANS = 24H"
    }

    fun lifeBalanceTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "1. الميزان الزمني لحياتك (ساعتان إلهيتان)"
        AppLanguage.EN -> "1. TIME BALANCE OF YOUR LIFE (2 DIVINE HOURS)"
        AppLanguage.FR -> "1. BILAN TEMPOREL DE TA VIE (2 HEURES DIVINES)"
    }

    fun prayersOnlyTwoPercent(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الصلوات الخمس لا تمثل سوى 1.7% من وجودك الإلهي. كل ما تبقى هو هبة لك!"
        AppLanguage.EN -> "The 5 prayers are only 1.7% of your divine existence. Everything else is a gift!"
        AppLanguage.FR -> "Les 5 prières ne représentent que 1.7% de ton existence divine. Tout le reste t'est offert !"
    }

    fun livedTimeLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الوقت المضي"
        AppLanguage.EN -> "TIME LIVED"
        AppLanguage.FR -> "TEMPS VÉCU"
    }

    // ---------------------------------------------------------------------
    // Settings & verses screen
    // ---------------------------------------------------------------------

    fun remindersTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "🕌 التذكيرات وحكم الوقت"
        AppLanguage.EN -> "🕌 REMINDERS & WISDOM OF TIME"
        AppLanguage.FR -> "🕌 RAPPELS & SAGESSES DU TEMPS"
    }

    fun remindersSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تنبيهات واعية كي لا تهدر ثانية أبداً"
        AppLanguage.EN -> "Aware alerts so you never waste a second again"
        AppLanguage.FR -> "Des alertes conscientes pour ne plus jamais gaspiller une seconde"
    }

    fun spiritualNotificationsTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "🔔 الإشعارات الروحية الذكية"
        AppLanguage.EN -> "🔔 SMART SPIRITUAL NOTIFICATIONS"
        AppLanguage.FR -> "🔔 NOTIFICATIONS SPIRITUELLES INTELLIGENTES"
    }

    fun dailyReminderDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تلقَّ كل يوم في الساعة 13:00 تذكيراً لطيفاً وعميقاً محسوباً حسب تقدمك في ساعتيك الإلهيتين."
        AppLanguage.EN -> "Receive every day at 1:00 PM a gentle, deep reminder computed from your progress in your 2 divine hours."
        AppLanguage.FR -> "Reçois chaque jour à 13h00 un rappel doux et profond calculé selon ton avancement dans tes 2 heures divines."
    }

    fun celestialThemeTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "🌙 المظهر السماوي"
        AppLanguage.EN -> "🌙 CELESTIAL THEME"
        AppLanguage.FR -> "🌙 THÈME CÉLESTE"
    }

    fun fundamentalVersesTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "📖 آيات الوقت الأساسية"
        AppLanguage.EN -> "📖 FUNDAMENTAL VERSES OF TIME"
        AppLanguage.FR -> "📖 VERSETS FONDAMENTAUX DU TEMPS"
    }

    fun fundamentalVersesSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "التصور القرآني لنسبية الوقت"
        AppLanguage.EN -> "The Quranic conception of the relativity of time"
        AppLanguage.FR -> "La conception coranique de la relativité du temps"
    }

    fun lifeIsTwoHoursQuote(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« حياتك كلها = نحو ساعتين عند الله »"
        AppLanguage.EN -> "« Your entire life = ~2 hours with Allah »"
        AppLanguage.FR -> "« Ta vie entière = ~2 heures chez Allah »"
    }

    fun hajj47Explanation(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "استناداً إلى الآية 47 من سورة الحج: 1000 سنة أرضية تعادل 24 ساعة إلهية. وعمر بشري من 70 إلى 80 سنة لا يمثل سوى ما بين 1س41د و1س55د عند الله."
        AppLanguage.EN -> "Based on verse 47 of Surah Al-Hajj: 1000 earth years equal 24 divine hours. A human lifespan of 70 to 80 years represents only between 1h41m and 1h55m with Allah."
        AppLanguage.FR -> "Basé sur le verset 47 de la Sourate Al-Hajj : 1000 ans terrestres équivalent à 24 heures divines. Une durée de vie humaine de 70 à 80 ans ne représente qu'entre 1h41 et 1h55 auprès d'Allah."
    }

    // ---------------------------------------------------------------------
    // Theme selector
    // ---------------------------------------------------------------------

    fun themeDayLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "نور الفجر (نهاري)"
        AppLanguage.EN -> "Celestial Dawn (Day)"
        AppLanguage.FR -> "Noor Céleste (Jour)"
    }

    fun themeNightLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الكون الليلي (ليلي)"
        AppLanguage.EN -> "Cosmic Night (Dark)"
        AppLanguage.FR -> "Cosmos Nocturne (Nuit)"
    }

    // ---------------------------------------------------------------------
    // Notifications
    // ---------------------------------------------------------------------

    fun dailyReminderNotifTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "ذَوْق • تذكير الوقت الإلهي"
        AppLanguage.EN -> "DHAwQ • Divine Lifespan Reminder"
        AppLanguage.FR -> "DHAwQ • Ton Rappel Céleste"
    }

    fun dailyReminderNotifBody(lang: AppLanguage, divineMinutes: Double): String = when (lang) {
        AppLanguage.AR -> "لقد قطعت ${String.format(Locale.US, "%.1f", divineMinutes)} د من أصل 120 دقيقة إلهية. امنح 0.0008 ث لصلاتك الآن 🕌"
        AppLanguage.EN -> "You have travelled ${String.format(Locale.US, "%.1f", divineMinutes)}m of your 120 divine minutes. Grant 0.0008s for your prayer now 🕌"
        AppLanguage.FR -> "Tu as parcouru ${String.format(Locale.US, "%.1f", divineMinutes)}m de tes 120 minutes divines. Accorde 0.0008s pour ta prière maintenant 🕌"
    }

    fun notificationChannelName(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تذكيرات الوقت الإلهي"
        AppLanguage.EN -> "Divine Time Reminders"
        AppLanguage.FR -> "Rappels du Temps Divin"
    }

    fun notificationChannelDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تذكيرات يومية مبنية على ساعتيك الإلهيتين والصلاة"
        AppLanguage.EN -> "Daily reminders based on your 2 divine hours and prayer"
        AppLanguage.FR -> "Rappels quotidiens basés sur tes 2 heures divines et la prière"
    }

    fun testReminderBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "إرسال إشعار تذكير فوري"
        AppLanguage.EN -> "Send instant reminder"
        AppLanguage.FR -> "Tester le rappel céleste"
    }

    fun reminderScheduledConfirm(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "✓ تم جدولة التذكير اليومي"
        AppLanguage.EN -> "✓ Daily reminder scheduled"
        AppLanguage.FR -> "✓ Rappel quotidien programmé"
    }

    fun notificationPermissionRequired(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "يجب منح إذن الإشعارات لعرض التذكيرات."
        AppLanguage.EN -> "Notification permission is required to show reminders."
        AppLanguage.FR -> "L'autorisation des notifications est requise pour afficher les rappels."
    }

    // ---------------------------------------------------------------------
    // Data-layer content: impact phrases
    // ---------------------------------------------------------------------

    fun impactPhrase(lang: AppLanguage, percentageLived: Float): String = when {
        percentageLived < 25f -> when (lang) {
            AppLanguage.AR -> "أنت في نصف ساعتك الإلهية الأولى. ابنِ أساسك للأبدية!"
            AppLanguage.EN -> "You are in your first divine half-hour. Build the foundations of eternity!"
            AppLanguage.FR -> "Tu es dans ta première demi-heure divine. Construis tes fondations pour l'éternité !"
        }
        percentageLived < 50f -> when (lang) {
            AppLanguage.AR -> "اقتربت من نصف وقتك الإلهي... ماذا prepares لقاء الله؟"
            AppLanguage.EN -> "Almost half of your divine time is gone... What have you prepared for meeting Allah?"
            AppLanguage.FR -> "Presque la moitié de ton temps divin... Qu'as-tu préparé pour la rencontre d'Allah ?"
        }
        percentageLived < 75f -> when (lang) {
            AppLanguage.AR -> "الوقت يمضي بسرعة مذهلة. كل ثانية إلهية لا تُقدَّر بثمن!"
            AppLanguage.EN -> "Time is passing at a dizzying speed. Every divine second is priceless!"
            AppLanguage.FR -> "Le temps file à une vitesse vertigineuse. Chaque seconde divine est inestimable !"
        }
        percentageLived < 90f -> when (lang) {
            AppLanguage.AR -> "الدقائق الأخيرة من ساعتك... أكثِر من التوبة والحسنات! 🤲"
            AppLanguage.EN -> "The last minutes of your hour... Multiply the repentance and the hassanat! 🤲"
            AppLanguage.FR -> "Les dernières minutes de ton heure... Multiplie le repentir et les hassanat ! 🤲"
        }
        else -> when (lang) {
            AppLanguage.AR -> "ظل الساعة يمتد. لم يستمر وقتك هنا إلا نَفَساً واحداً. كن مستعداً."
            AppLanguage.EN -> "The shadow of the hourglass stretches. Your time here lasted only a breath. Be ready."
            AppLanguage.FR -> "L'ombre du sablier s'étire. Ton temps ici-bas n'a duré qu'un souffle. Sois prêt."
        }
    }

    // ---------------------------------------------------------------------
    // Data-layer content: life stages
    // ---------------------------------------------------------------------

    fun stageTitle(lang: AppLanguage, stageId: String): String = when (stageId) {
        "first_half_hour" -> when (lang) {
            AppLanguage.AR -> "النصف الساعة الأول"
            AppLanguage.EN -> "The First Half-Hour"
            AppLanguage.FR -> "La Première Demi-Heure"
        }
        "youth" -> when (lang) {
            AppLanguage.AR -> "الساعة الأولى • قمة الشباب"
            AppLanguage.EN -> "The First Hour • Full Youth"
            AppLanguage.FR -> "La Première Heure • Pleine Jeunesse"
        }
        "maturity" -> when (lang) {
            AppLanguage.AR -> "الساعة الثانية • النضج"
            AppLanguage.EN -> "The Second Hour • Maturity"
            AppLanguage.FR -> "La Seconde Heure • La Maturité"
        }
        "wisdom" -> when (lang) {
            AppLanguage.AR -> "الساعة الثانية • الحكمة"
            AppLanguage.EN -> "The Second Hour • Wisdom"
            AppLanguage.FR -> "La Seconde Heure • La Sagesse"
        }
        else -> when (lang) {
            AppLanguage.AR -> "نهاية الساعتين"
            AppLanguage.EN -> "The End of the Two Hours"
            AppLanguage.FR -> "Le Terme des Deux Heures"
        }
    }

    fun stageSubtitle(lang: AppLanguage, stageId: String): String = when (stageId) {
        "first_half_hour" -> when (lang) {
            AppLanguage.AR -> "البراءة والتعلّم (0 إلى 15 سنة)"
            AppLanguage.EN -> "Innocence & Learning (0 to 15 years)"
            AppLanguage.FR -> "L'Innocence et l'Apprentissage (0 à 15 ans)"
        }
        "youth" -> when (lang) {
            AppLanguage.AR -> "البناء والعمل والإيمان (15 إلى ~42 سنة)"
            AppLanguage.EN -> "Building, Action & Faith (15 to ~42 years)"
            AppLanguage.FR -> "La Construction, l'Action et la Foi (15 à ~42 ans)"
        }
        "maturity" -> when (lang) {
            AppLanguage.AR -> "المراجعة والشكر (~42 إلى 60 سنة)"
            AppLanguage.EN -> "The Mid-Life Review & Gratitude (~42 to 60 years)"
            AppLanguage.FR -> "Le Bilan de Mi-Vie et la Gratitude (~42 à 60 ans)"
        }
        "wisdom" -> when (lang) {
            AppLanguage.AR -> "الضوء الخافت والحصد (~60 إلى ~83 سنة)"
            AppLanguage.EN -> "The Fading Light & the Harvest (~60 to ~83 years)"
            AppLanguage.FR -> "Le Crépuscule Lumineux et la Moisson (~60 à ~83 ans)"
        }
        else -> when (lang) {
            AppLanguage.AR -> "الدخول القريب إلى الأبدية (~83 سنة فما فوق)"
            AppLanguage.EN -> "The Imminent Entry into Eternity (~83 years and beyond)"
            AppLanguage.FR -> "L'Entrée imminente dans l'Éternité (~83 ans et plus)"
        }
    }

    fun stageArabic(lang: AppLanguage, stageId: String): String = when (stageId) {
        "first_half_hour" -> "البراءة والغرس"
        "youth" -> "الشباب وقوة العزم"
        "maturity" -> "بلوغ الأشد والإنابة"
        "wisdom" -> "الشيبة والاقتراب"
        else -> "الرجوع والخلود"
    }

    fun stageQuote(lang: AppLanguage, stageId: String): String = when (stageId) {
        "first_half_hour" -> when (lang) {
            AppLanguage.AR -> "« أخرجكم الله من بطون أمهاتكم لا تعلمون شيئاً »"
            AppLanguage.EN -> "« Allah brought you forth from the wombs of your mothers, knowing nothing. »"
            AppLanguage.FR -> "« Allah vous a fait sortir du ventre de vos mères dénués de tout savoir... »"
        }
        "youth" -> when (lang) {
            AppLanguage.AR -> "« اغتنم خمساً قبل خمس: شبابك قبل شيخوك... »"
            AppLanguage.EN -> "« Take advantage of five before five: your youth before your old age... »"
            AppLanguage.FR -> "« Profite de cinq choses avant cinq autres : ta jeunesse avant ta vieillesse... »"
        }
        "maturity" -> when (lang) {
            AppLanguage.AR -> "« لما بلغ أربعين سنة قال: ربِّ أوزعني أن أشكر نعمتك »"
            AppLanguage.EN -> "« When he reached forty, he said: My Lord, inspire me to be grateful for Your favour. »"
            AppLanguage.FR -> "« Arrivé à quarante ans, il dit : Ô Seigneur, inspire-moi de Te remercier... »"
        }
        "wisdom" -> when (lang) {
            AppLanguage.AR -> "« ألم نعطيك حياة كافية لتتدبر؟ »"
            AppLanguage.EN -> "« Did We not give you a life long enough to reflect? »"
            AppLanguage.FR -> "« Ne vous avons-Nous pas donné une vie assez longue pour réfléchir ? »"
        }
        else -> when (lang) {
            AppLanguage.AR -> "« كل نفس ذائقة الموت. وإنما توفون أجوركم يوم القيامة »"
            AppLanguage.EN -> "« Every soul will taste death. On the Day of Resurrection you will be given your full reward. »"
            AppLanguage.FR -> "« Chaque âme goûtera la mort. Vous recevrez vos salaires complets. »"
        }
    }

    fun stageReference(lang: AppLanguage, stageId: String): String = when (stageId) {
        "first_half_hour" -> when (lang) {
            AppLanguage.AR -> "سورة النحل (16:78)"
            AppLanguage.EN -> "Surah An-Nahl (16:78)"
            AppLanguage.FR -> "Sourate An-Nahl (16:78)"
        }
        "youth" -> when (lang) {
            AppLanguage.AR -> "حديث صحيح (الحاكم)"
            AppLanguage.EN -> "Authentic Hadith (Al-Hakim)"
            AppLanguage.FR -> "Hadith authentique (Al-Hakim)"
        }
        "maturity" -> when (lang) {
            AppLanguage.AR -> "سورة الأحقاف (46:15)"
            AppLanguage.EN -> "Surah Al-Ahqaf (46:15)"
            AppLanguage.FR -> "Sourate Al-Ahqaf (46:15)"
        }
        "wisdom" -> when (lang) {
            AppLanguage.AR -> "سورة فاطر (35:37)"
            AppLanguage.EN -> "Surah Fatir (35:37)"
            AppLanguage.FR -> "Sourate Fatir (35:37)"
        }
        else -> when (lang) {
            AppLanguage.AR -> "سورة آل عمران (3:185)"
            AppLanguage.EN -> "Surah Al-Imran (3:185)"
            AppLanguage.FR -> "Sourate Al-Imran (3:185)"
        }
    }

    // ---------------------------------------------------------------------
    // Data-layer content: life milestones
    // ---------------------------------------------------------------------

    fun milestoneTitle(lang: AppLanguage, id: String): String = when (id) {
        "birth" -> when (lang) {
            AppLanguage.AR -> "الولادة"
            AppLanguage.EN -> "Birth"
            AppLanguage.FR -> "Naissance"
        }
        "childhood" -> when (lang) {
            AppLanguage.AR -> "الطفولة والبراءة"
            AppLanguage.EN -> "Childhood & Innocence"
            AppLanguage.FR -> "Enfance & Insouciance"
        }
        "puberty" -> when (lang) {
            AppLanguage.AR -> "البلوغ والتكليف"
            AppLanguage.EN -> "Puberty & Taklif"
            AppLanguage.FR -> "Puberté & Taklif"
        }
        "youth_marriage" -> when (lang) {
            AppLanguage.AR -> "الشباب وبناء الأسرة"
            AppLanguage.EN -> "Youth & Marriage"
            AppLanguage.FR -> "Jeunesse & Mariage"
        }
        "maturity_40" -> when (lang) {
            AppLanguage.AR -> "منتصف العمر (40 سنة)"
            AppLanguage.EN -> "Mid-Life (40 years)"
            AppLanguage.FR -> "Milieu de vie (40 ans)"
        }
        "wisdom_aging" -> when (lang) {
            AppLanguage.AR -> "الشيخوخة والشيب"
            AppLanguage.EN -> "Old Age & White Hair"
            AppLanguage.FR -> "Vieillesse & Cheveux Blancs"
        }
        else -> when (lang) {
            AppLanguage.AR -> "الرجوع العظيم إلى الله"
            AppLanguage.EN -> "The Great Return to Allah"
            AppLanguage.FR -> "Le Grand Retour vers Allah"
        }
    }

    fun milestoneArabicTitle(lang: AppLanguage, id: String): String = when (id) {
        "birth" -> "الولادة"
        "childhood" -> "الطفولة"
        "puberty" -> "التكليف الشرعي"
        "youth_marriage" -> "الشباب وبناء الأسرة"
        "maturity_40" -> "بلوغ الأشد"
        "wisdom_aging" -> "الشيبة والوقار"
        else -> "الرجوع إلى الله"
    }

    fun milestoneDescription(lang: AppLanguage, id: String): String = when (id) {
        "birth" -> when (lang) {
            AppLanguage.AR -> "أول نفَس على الأرض. تصل عارياً عاجزاً، لكنك منعمٌ به رحمة الخالق."
            AppLanguage.EN -> "First breath on Earth. You arrive naked and helpless, yet filled with the Creator's mercy."
            AppLanguage.FR -> "Premier souffle sur Terre. Tu arrives nu et démuni, comblé par la miséricorde du Créateur."
        }
        "childhood" -> when (lang) {
            AppLanguage.AR -> "ألعاب ودهشة وبراءة. ما زال ريش الملائكة مرفوعاً لك."
            AppLanguage.EN -> "Games, wonder and innocence. The angels' wings are still raised for you."
            AppLanguage.FR -> "Jeux, émerveillement et innocence. La plume des anges est encore levée pour toi."
        }
        "puberty" -> when (lang) {
            AppLanguage.AR -> "الكتابة تبدأ. تصير مسؤولاً عن صلاتك وكلامك وقلبك."
            AppLanguage.EN -> "The pen begins. You become accountable for your prayers, your words and your heart."
            AppLanguage.FR -> "L'écriture commence. Tu deviens responsable de tes prières, de tes paroles et de ton cœur."
        }
        "youth_marriage" -> when (lang) {
            AppLanguage.AR -> "قوة الجسد وطموحاتك، ونصف الدين، وبناء بيت تقوى."
            AppLanguage.EN -> "Physical strength, ambitions, half of the religion, and the building of a pious home."
            AppLanguage.FR -> "Force physique, ambitions, moitié de la religion et construction d'un foyer pieux."
        }
        "maturity_40" -> when (lang) {
            AppLanguage.AR -> "ذروة النضج. تدرك أن الشباب يمضي وأن الساعة تدور بسرعة."
            AppLanguage.EN -> "The summit of maturity. You realise that youth is fading and the hour turns fast."
            AppLanguage.FR -> "Le sommet de la maturité. Tu réalises que la jeunesse s'éloigne et que l'heure tourne vite."
        }
        "wisdom_aging" -> when (lang) {
            AppLanguage.AR -> "يضعف الجسد، وتُبطئ الخطوات. وتزداد قيمة كل لحظة."
            AppLanguage.EN -> "The body weakens and the steps slow down. Every remaining moment gains value."
            AppLanguage.FR -> "Le corps faiblit, les pas ralentissent. Les artifices du monde perdent toute valeur."
        }
        else -> when (lang) {
            AppLanguage.AR -> "يسقط الستار على هاتين الساعتين الصغيرتين. الأبدية تبدأ."
            AppLanguage.EN -> "The curtain falls on these two small hours. Eternity begins."
            AppLanguage.FR -> "Le rideau tombe sur ces ~2 petites heures divines. L'éternité commence."
        }
    }

    fun milestoneQuranVerse(lang: AppLanguage, id: String): String = when (id) {
        "birth" -> "وَاللَّهُ أَخْرَجَكُم مِّن بُطُونِ أُمَّهَاتِكُمْ لَا تَعْلَمُونَ شَيْئًا"
        "childhood" -> "اعْلَمُوا أَنَّمَا الْحَيَاةُ الدُّنْيَا لَعِبٌ وَلَهْوٌ"
        "puberty" -> "مَّا يَلْفِظُ مِن قَوْلٍ إِلَّا لَدَيْهِ رَقِيبٌ عَتِيدٌ"
        "youth_marriage" -> "وَمِنْ آيَاتِهِ أَنْ خَلَقَ لَكُم مِّنْ أَنفُسِكُمْ أَزْوَاجًا لِّتَسْكُنُوا إِلَيْهَا"
        "maturity_40" -> "حَتَّىٰ إِذَا بَلَغَ أَشُدَّهُ وَبَلَغَ أَرْبَعِينَ سَنَةً قَالَ رَبِّ أَوْزِعْنِي أَنْ أَشْكُرَ نِعْمَتَكَ"
        "wisdom_aging" -> "اللَّهُ الَّذِي خَلَقَكُم مِّن ضَعْفٍ ثُمَّ جَعَلَ مِن بَعْدِ ضَعْفٍ قُوَّةً ثُمَّ جَعَلَ مِن بَعْدِ قُوَّةٍ ضَعْفًا وَشَيْبَةً"
        else -> "كُلُّ نَفْسٍ ذَائِقَةُ الْمَوْتِ ۗ وَإِنَّمَا تُوَفَّوْنَ أُجُورَكُمْ يَوْمَ الْقِيَامَةِ"
    }

    fun milestoneQuranRef(lang: AppLanguage, id: String): String = when (id) {
        "birth" -> when (lang) {
            AppLanguage.AR -> "سورة النحل (16:78)"
            AppLanguage.EN -> "Surah An-Nahl (16:78)"
            AppLanguage.FR -> "Sourate An-Nahl (16:78)"
        }
        "childhood" -> when (lang) {
            AppLanguage.AR -> "سورة الحديد (57:20)"
            AppLanguage.EN -> "Surah Al-Hadid (57:20)"
            AppLanguage.FR -> "Sourate Al-Hadid (57:20)"
        }
        "puberty" -> when (lang) {
            AppLanguage.AR -> "سورة ق (50:18)"
            AppLanguage.EN -> "Surah Qaf (50:18)"
            AppLanguage.FR -> "Sourate Qaf (50:18)"
        }
        "youth_marriage" -> when (lang) {
            AppLanguage.AR -> "سورة الروم (30:21)"
            AppLanguage.EN -> "Surah Ar-Rum (30:21)"
            AppLanguage.FR -> "Sourate Ar-Rum (30:21)"
        }
        "maturity_40" -> when (lang) {
            AppLanguage.AR -> "سورة الأحقاف (46:15)"
            AppLanguage.EN -> "Surah Al-Ahqaf (46:15)"
            AppLanguage.FR -> "Sourate Al-Ahqaf (46:15)"
        }
        "wisdom_aging" -> when (lang) {
            AppLanguage.AR -> "سورة الروم (30:54)"
            AppLanguage.EN -> "Surah Ar-Rum (30:54)"
            AppLanguage.FR -> "Sourate Ar-Rum (30:54)"
        }
        else -> when (lang) {
            AppLanguage.AR -> "سورة آل عمران (3:185)"
            AppLanguage.EN -> "Surah Al-Imran (3:185)"
            AppLanguage.FR -> "Sourate Al-Imran (3:185)"
        }
    }

    // ---------------------------------------------------------------------
    // Data-layer content: standalone Quran verses
    // ---------------------------------------------------------------------

    fun quranVerseTranslation(lang: AppLanguage, id: String): String = when (id) {
        "hajj_47" -> when (lang) {
            AppLanguage.AR -> "« ويوم عند ربك كألف سنة مما تعدون »"
            AppLanguage.EN -> "« And a day with your Lord is like a thousand years of what you count. »"
            AppLanguage.FR -> "« Et un jour auprès de ton Seigneur est comme mille ans de ce que vous comptez. »"
        }
        "muminun_112" -> when (lang) {
            AppLanguage.AR -> "« قال: كم لبثتم في الأرض عدد سنين؟ قالوا: لبثنا يوماً أو بعض يوم فاسأل العادين »"
            AppLanguage.EN -> "« He will say: How many years did you remain on earth? They will say: We remained a day or part of a day; ask those who count. »"
            AppLanguage.FR -> "« Il dira : Combien d'années êtes-vous restés sur terre ? Ils diront : Nous y avons demeuré un jour, ou une partie d'un jour ; interroge donc ceux qui comptent. »"
        }
        "asr_103" -> when (lang) {
            AppLanguage.AR -> "« والعصر! إن الإنسان لفي خسر، إلا الذين آمنوا وعملوا الصالحات وتواصوا بالحق وتواصوا بالصبر »"
            AppLanguage.EN -> "« By Time! Indeed, mankind is in loss, except for those who believe and do righteous deeds and enjoin one another upon truth and enjoin one another upon patience. »"
            AppLanguage.FR -> "« Par le Temps ! L'homme est certes en perdition, sauf ceux qui croient et accomplissent les bonnes œuvres, s'enjoignent mutuellement la vérité et s'enjoignent mutuellement l'endurance. »"
        }
        "naziat_46" -> when (lang) {
            AppLanguage.AR -> "« كأنهم يوم يرونها لم يلثوا إلا عشيةً أوضحاها »"
            AppLanguage.EN -> "« The day they see it, it will seem to them as if they had not remained more than an evening or a morning. »"
            AppLanguage.FR -> "« Le jour où ils la verront, ce sera comme s'ils n'étaient restés qu'un soir ou le matin d'après. »"
        }
        else -> when (lang) {
            AppLanguage.AR -> "« ويوم يحشرهم كأن لم يلبثوا إلا ساعة من النهار يتعارفون بينهم »"
            AppLanguage.EN -> "« And the day He will gather them, it will seem as if they had not remained more than an hour of a day, as they recognise one another. »"
            AppLanguage.FR -> "« Et le jour où Il les rassemblera, ce sera comme s'ils n'avaient demeuré qu'une heure d'un jour, pour se reconnaître mutuellement. »"
        }
    }

    fun quranVerseContextTheme(lang: AppLanguage, id: String): String = when (id) {
        "hajj_47" -> when (lang) {
            AppLanguage.AR -> "الوقت الإلهي"
            AppLanguage.EN -> "Divine Time"
            AppLanguage.FR -> "Le Temps Divin"
        }
        "muminun_112" -> when (lang) {
            AppLanguage.AR -> "قِصر الحياة الدنيا"
            AppLanguage.EN -> "The brevity of this worldly life"
            AppLanguage.FR -> "La brièveté de la vie d'ici-bas"
        }
        "asr_103" -> when (lang) {
            AppLanguage.AR -> "قسم بالوقت"
            AppLanguage.EN -> "The Oath by Time"
            AppLanguage.FR -> "Le Serment par le Temps"
        }
        "naziat_46" -> when (lang) {
            AppLanguage.AR -> "أمسية أوضحى"
            AppLanguage.EN -> "An evening or a morning"
            AppLanguage.FR -> "Un matin ou un soir"
        }
        else -> when (lang) {
            AppLanguage.AR -> "ساعة واحدة"
            AppLanguage.EN -> "A single hour"
            AppLanguage.FR -> "Une seule heure"
        }
    }

    fun quranVerseReference(lang: AppLanguage, id: String): String = when (id) {
        "hajj_47" -> when (lang) {
            AppLanguage.AR -> "سورة الحج (22:47)"
            AppLanguage.EN -> "Surah Al-Hajj (22:47)"
            AppLanguage.FR -> "Sourate Al-Hajj (22:47)"
        }
        "muminun_112" -> when (lang) {
            AppLanguage.AR -> "سورة المؤمنون (23:112-113)"
            AppLanguage.EN -> "Surah Al-Mu'minun (23:112-113)"
            AppLanguage.FR -> "Sourate Al-Mu'minūn (23:112-113)"
        }
        "asr_103" -> when (lang) {
            AppLanguage.AR -> "سورة العصر (103:1-3)"
            AppLanguage.EN -> "Surah Al-Asr (103:1-3)"
            AppLanguage.FR -> "Sourate Al-Asr (103:1-3)"
        }
        "naziat_46" -> when (lang) {
            AppLanguage.AR -> "سورة النازعات (79:46)"
            AppLanguage.EN -> "Surah An-Nazi'at (79:46)"
            AppLanguage.FR -> "Sourate An-Nazi'at (79:46)"
        }
        else -> when (lang) {
            AppLanguage.AR -> "سورة يونس (10:45)"
            AppLanguage.EN -> "Surah Yunus (10:45)"
            AppLanguage.FR -> "Sourate Yunus (10:45)"
        }
    }

    // ---------------------------------------------------------------------
    // Challenges content
    // ---------------------------------------------------------------------

    fun challengeLevelName(lang: AppLanguage, id: String): String = when (id) {
        "c1_five_prayers" -> when (lang) {
            AppLanguage.AR -> "الدقيقة الأولى"
            AppLanguage.EN -> "The First Minute"
            AppLanguage.FR -> "La Première Minute"
        }
        "c2_quran" -> when (lang) {
            AppLanguage.AR -> "الثانية المضيئة"
            AppLanguage.EN -> "The Luminous Second"
            AppLanguage.FR -> "La Seconde Lumineuse"
        }
        "c3_sadaqa_dhikr" -> when (lang) {
            AppLanguage.AR -> "الربع الساعة الإلهي"
            AppLanguage.EN -> "The Divine Quarter-Hour"
            AppLanguage.FR -> "Le Quart d'Heure Divin"
        }
        "c4_tasbih_100" -> when (lang) {
            AppLanguage.AR -> "الذكر الدائم"
            AppLanguage.EN -> "Perpetual Remembrance"
            AppLanguage.FR -> "L'Évocation Perpétuelle"
        }
        else -> when (lang) {
            AppLanguage.AR -> "اليقظة بالساعة"
            AppLanguage.EN -> "The Consciousness of the Hour"
            AppLanguage.FR -> "La Conscience de l'Heure"
        }
    }

    fun challengeTitle(lang: AppLanguage, id: String): String = when (id) {
        "c1_five_prayers" -> when (lang) {
            AppLanguage.AR -> "الصلوات الخمس في وقتها"
            AppLanguage.EN -> "The 5 Prayers on Time"
            AppLanguage.FR -> "Les 5 Prières à l'Heure"
        }
        "c2_quran" -> when (lang) {
            AppLanguage.AR -> "صفحة واحدة من القرآن"
            AppLanguage.EN -> "1 Page of Quran"
            AppLanguage.FR -> "1 Page de Coran"
        }
        "c3_sadaqa_dhikr" -> when (lang) {
            AppLanguage.AR -> "الصدقة والذكر اليومي"
            AppLanguage.EN -> "Sadaqa & Dhikr of the Day"
            AppLanguage.FR -> "Sadaqa & Dhikr du Jour"
        }
        "c4_tasbih_100" -> when (lang) {
            AppLanguage.AR -> "100 تسبيحة اليوم"
            AppLanguage.EN -> "100 Tasbih Today"
            AppLanguage.FR -> "100 Tasbih Aujourd'hui"
        }
        else -> when (lang) {
            AppLanguage.AR -> "حصيلة الساعتين"
            AppLanguage.EN -> "The Balance of 2 Hours"
            AppLanguage.FR -> "Le Bilan des 2 Heures"
        }
    }

    fun challengeTargetDescription(lang: AppLanguage, id: String): String = when (id) {
        "c1_five_prayers" -> when (lang) {
            AppLanguage.AR -> "إقامة أركان اليوم الخمسة بين يدي خالقك"
            AppLanguage.EN -> "Fulfil the 5 pillars of the day in the presence of your Creator"
            AppLanguage.FR -> "Accomplir les 5 piliers de la journée devant ton Créateur"
        }
        "c2_quran" -> when (lang) {
            AppLanguage.AR -> "تلاوة وتدبّر صفحة على الأقل من كلام الله"
            AppLanguage.EN -> "Recite and reflect on at least one page of the Word of Allah"
            AppLanguage.FR -> "Réciter et méditer au moins une page de la parole d'Allah"
        }
        "c3_sadaqa_dhikr" -> when (lang) {
            AppLanguage.AR -> "التصدّق وتقديس وقتك بذكر الله"
            AppLanguage.EN -> "Give charity and sanctify your time with the remembrance of Allah"
            AppLanguage.FR -> "Donner une aumône et sanctifier ton temps par l'évocation d'Allah"
        }
        "c4_tasbih_100" -> when (lang) {
            AppLanguage.AR -> "100 مرة سبحان الله أو الحمد لله أو أستغفر الله"
            AppLanguage.EN -> "100 times SubhanAllah, Alhamdulillah or Astaghfirullah"
            AppLanguage.FR -> "100 formules de SubhanAllah, Alhamdulillah ou Astaghfirullah"
        }
        else -> when (lang) {
            AppLanguage.AR -> "شاهد رحلة الساعتين واكتب تأملاً في اليوميات"
            AppLanguage.EN -> "Watch the 2-hour journey and write a reflection in the journal"
            AppLanguage.FR -> "Visionner le voyage des 2 heures et écrire une réflexion dans le journal"
        }
    }

    fun challengeBadgeName(lang: AppLanguage, id: String): String = when (id) {
        "c1_five_prayers" -> when (lang) {
            AppLanguage.AR -> "حارس الصلاة"
            AppLanguage.EN -> "Guardian of the Prayer"
            AppLanguage.FR -> "Gardien de la Prière"
        }
        "c2_quran" -> when (lang) {
            AppLanguage.AR -> "رفيق القرآن"
            AppLanguage.EN -> "Companion of the Quran"
            AppLanguage.FR -> "Compagnon du Coran"
        }
        "c3_sadaqa_dhikr" -> when (lang) {
            AppLanguage.AR -> "قلب سخي"
            AppLanguage.EN -> "Generous Heart"
            AppLanguage.FR -> "Cœur Généreux"
        }
        "c4_tasbih_100" -> when (lang) {
            AppLanguage.AR -> "كل ثانية تُحسَب"
            AppLanguage.EN -> "Every Second Counts"
            AppLanguage.FR -> "Chaque Seconde Compte"
        }
        else -> when (lang) {
            AppLanguage.AR -> "حارس الوقت"
            AppLanguage.EN -> "Guardian of Time"
            AppLanguage.FR -> "Gardien du Temps"
        }
    }

    // ---------------------------------------------------------------------
    // Breakdown chart content
    // ---------------------------------------------------------------------

    fun breakdownChartTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الميزان الزمني للوجود"
        AppLanguage.EN -> "TIME BALANCE OF EXISTENCE"
        AppLanguage.FR -> "BILAN TEMPOREL DE L'EXISTENCE"
    }

    fun breakdownChartSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "أين تذهب حياتك كلها في ميزان الله؟"
        AppLanguage.EN -> "Where does your whole life go in the balance of Allah?"
        AppLanguage.FR -> "Où passe ta vie entière dans la balance d'Allah ?"
    }

    fun timeConsumedLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الوقت المستهلك"
        AppLanguage.EN -> "Time consumed"
        AppLanguage.FR -> "Temps consommé"
    }

    fun timeRemainingLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الوقت المتبقي"
        AppLanguage.EN -> "Time remaining"
        AppLanguage.FR -> "Temps restant"
    }

    fun lifetimeBudgetTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "ميزانية العمر بالكامل بالساعات الإلهية"
        AppLanguage.EN -> "FULL LIFETIME BUDGET IN DIVINE HOURS"
        AppLanguage.FR -> "BUDGET TOTAL DE VIE EN HEURES DIVINES"
    }

    fun segmentSleep(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "النوم"
        AppLanguage.EN -> "Sleep"
        AppLanguage.FR -> "Sommeil"
    }

    fun segmentWork(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "العمل والدراسة"
        AppLanguage.EN -> "Work & Study"
        AppLanguage.FR -> "Travail & Études"
    }

    fun segmentScreens(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الشاشات"
        AppLanguage.EN -> "Screens"
        AppLanguage.FR -> "Écrans"
    }

    fun segmentRest(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الطعام والتنقل والعائلة"
        AppLanguage.EN -> "Meals, travel, family"
        AppLanguage.FR -> "Repas, trajets, famille"
    }

    fun segmentPrayers(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الصلوات الخمس"
        AppLanguage.EN -> "The 5 Prayers"
        AppLanguage.FR -> "Les 5 Prières"
    }

    // ---------------------------------------------------------------------
    // Clock dial content
    // ---------------------------------------------------------------------

    fun clockFaceTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الساعتان الإلهيتان"
        AppLanguage.EN -> "THE TWO DIVINE HOURS"
        AppLanguage.FR -> "LES DEUX HEURES DIVINES"
    }

    fun clockFaceSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "حياتك كلها في 120 دقيقة عند الله"
        AppLanguage.EN -> "Your whole life in 120 minutes with Allah"
        AppLanguage.FR -> "Toute ta vie en 120 minutes chez Allah"
    }

    fun dialMinutePosition(lang: AppLanguage, minutesLived: Double): String = when (lang) {
        AppLanguage.AR -> "الدقيقة ${String.format(Locale.US, "%.1f", minutesLived)} من 120"
        AppLanguage.EN -> "Minute ${String.format(Locale.US, "%.1f", minutesLived)} of 120"
        AppLanguage.FR -> "Minute ${String.format(Locale.US, "%.1f", minutesLived)} sur 120"
    }

    // ---------------------------------------------------------------------
    // Visualizer extras
    // ---------------------------------------------------------------------

    fun endScreenVerseTranslation(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« كل نفس ذائقة الموت، وإنما توفون أجوركم يوم القيامة »"
        AppLanguage.EN -> "« Every soul will taste death, and you will only be given your full reward on the Day of Resurrection. »"
        AppLanguage.FR -> "« Toute âme goûtera la mort. Et c'est seulement au Jour de la Résurrection que vous recevrez votre entière rétribution. »"
    }

    fun restartSimulationBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "إعادة البدء"
        AppLanguage.EN -> "Restart"
        AppLanguage.FR -> "Recommencer"
    }

    fun yearsShortLabel(lang: AppLanguage, years: Int): String = when (lang) {
        AppLanguage.AR -> "($years سنة)"
        AppLanguage.EN -> "($years yrs)"
        AppLanguage.FR -> "($years ans)"
    }

    // ---------------------------------------------------------------------
    // Notification preference labels & previews
    // ---------------------------------------------------------------------

    fun masterNotificationsLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تفعيل الإشعارات"
        AppLanguage.EN -> "Enable notifications"
        AppLanguage.FR -> "Activer les notifications"
    }

    fun reminderFajrLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تذكير الفجر"
        AppLanguage.EN -> "Fajr reminder"
        AppLanguage.FR -> "Rappel du Fajr"
    }

    fun reminderDhuhrLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تذكير الظهر"
        AppLanguage.EN -> "Dhuhr reminder"
        AppLanguage.FR -> "Rappel du Dhuhr"
    }

    fun reminderDailyLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "التذكير اليومي (13:00)"
        AppLanguage.EN -> "Daily reminder (1:00 PM)"
        AppLanguage.FR -> "Rappel quotidien (13h00)"
    }

    fun reminderFridayLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تذكير يوم الجمعة"
        AppLanguage.EN -> "Friday reminder"
        AppLanguage.FR -> "Rappel du vendredi"
    }

    fun notifPreviewWakeMoment(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الاستيقاظ"
        AppLanguage.EN -> "Wake up"
        AppLanguage.FR -> "Réveil"
    }

    fun notifPreviewWakeMessage(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« لقد نمت 0.078 ثانية إلهية. ابدأ بالفجر واربح آخرتك 🌅 »"
        AppLanguage.EN -> "« You just slept 0.078 divine seconds. Start with Fajr and win your hereafter 🌅 »"
        AppLanguage.FR -> "« Tu viens de dormir 0.078 sec divine. Commence avec Fajr et gagne ton au-delà 🌅 »"
    }

    fun notifPreviewNoonMoment(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الظهر"
        AppLanguage.EN -> "Noon"
        AppLanguage.FR -> "Midi"
    }

    fun notifPreviewNoonMessage(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« مضى 0.118 ثانية إلهية اليوم. هل صليت الظهر في وقته؟ »"
        AppLanguage.EN -> "« 0.118 divine seconds have passed today. Have you prayed Dhuhr on time? »"
        AppLanguage.FR -> "« 0.118 sec divine écoulée aujourd'hui. As-tu prié Dhuhr à l'heure ? »"
    }

    fun notifPreviewScrollMoment(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تصفح الشبكات > 30 دقيقة"
        AppLanguage.EN -> "Scrolling > 30 min"
        AppLanguage.FR -> "Scroll réseaux > 30min"
    }

    fun notifPreviewScrollMessage(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« لقد أمضيت 0.005 ثانية إلهية على شاشتك... عُد إلى الجوهر. »"
        AppLanguage.EN -> "« You just spent 0.005 divine seconds on your screen... Return to what matters. »"
        AppLanguage.FR -> "« Tu viens de passer 0.005 sec divine sur ton écran... Reviens à l'essentiel. »"
    }

    fun notifPreviewBirthdayMoment(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "عيد الميلاد / العمر"
        AppLanguage.EN -> "Birthday / Age"
        AppLanguage.FR -> "Anniversaire / Âge"
    }

    fun notifPreviewBirthdayMessage(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« لقد أمضيت دقيقة إلهية جديدة على الأرض. لم يبق لك إلا القليل ⏳ »"
        AppLanguage.EN -> "« You have spent another divine minute on Earth. Little time remains ⏳ »"
        AppLanguage.FR -> "« Tu as passé une nouvelle minute divine sur Terre. Il te reste peu de temps ⏳ »"
    }

    fun notifPreviewFridayMoment(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الجمعة"
        AppLanguage.EN -> "Friday"
        AppLanguage.FR -> "Vendredi (Joumou'a)"
    }

    fun notifPreviewFridayMessage(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« يوم الجمعة. استثمر هذه الميكرو-ثانية الإلهية في الصلاة على النبي ﷺ »"
        AppLanguage.EN -> "« Friday. Invest this divine micro-second in prayers upon the Prophet ﷺ »"
        AppLanguage.FR -> "« Jour de Joumou'a. Investis cette micro-seconde divine dans la prière sur le Prophète ﷺ »"
    }

    // ---------------------------------------------------------------------
    // Life map axis markers (kept short: five fit on one row)
    // ---------------------------------------------------------------------

    fun lifeMapMarkerBirth(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الولادة"
        AppLanguage.EN -> "Birth"
        AppLanguage.FR -> "Début"
    }

    fun lifeMapMarkerTaklif(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "التكليف"
        AppLanguage.EN -> "Taklif"
        AppLanguage.FR -> "Taklif"
    }

    fun lifeMapMarkerMaturity(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "النضج"
        AppLanguage.EN -> "Maturity"
        AppLanguage.FR -> "Maturité"
    }

    fun lifeMapMarkerWisdom(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الشيب"
        AppLanguage.EN -> "White hair"
        AppLanguage.FR -> "Cheveux blancs"
    }

    fun lifeMapMarkerEternity(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الأبدية"
        AppLanguage.EN -> "Eternity"
        AppLanguage.FR -> "Éternité"
    }

    fun hourSpanFirst(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "0 إلى ~42 سنة"
        AppLanguage.EN -> "0 to ~42 yrs"
        AppLanguage.FR -> "0 à ~42 ans"
    }

    fun hourSpanSecond(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "~42 إلى ~83 سنة"
        AppLanguage.EN -> "~42 to ~83 yrs"
        AppLanguage.FR -> "~42 à ~83 ans"
    }

    fun selectedContentDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "محدد"
        AppLanguage.EN -> "Selected"
        AppLanguage.FR -> "Sélectionné"
    }

    /** Localized display name for a prayer key (case-insensitive). */
    fun prayerName(lang: AppLanguage, key: String): String = when (key.lowercase()) {
        "fajr" -> when (lang) {
            AppLanguage.AR -> "الفجر"
            AppLanguage.EN -> "Fajr"
            AppLanguage.FR -> "Fajr"
        }
        "sunrise" -> when (lang) {
            AppLanguage.AR -> "الشروق"
            AppLanguage.EN -> "Sunrise"
            AppLanguage.FR -> "Chourouq"
        }
        "dhuhr" -> when (lang) {
            AppLanguage.AR -> "الظهر"
            AppLanguage.EN -> "Dhuhr"
            AppLanguage.FR -> "Dhuhr"
        }
        "asr" -> when (lang) {
            AppLanguage.AR -> "العصر"
            AppLanguage.EN -> "Asr"
            AppLanguage.FR -> "Asr"
        }
        "maghrib" -> when (lang) {
            AppLanguage.AR -> "المغرب"
            AppLanguage.EN -> "Maghrib"
            AppLanguage.FR -> "Maghrib"
        }
        "isha" -> when (lang) {
            AppLanguage.AR -> "العشاء"
            AppLanguage.EN -> "Isha"
            AppLanguage.FR -> "Isha"
        }
        else -> key
    }

    // ---------------------------------------------------------------------
    // Duration units (calculator formatting)
    // ---------------------------------------------------------------------

    fun divineUnitHoursMinutesSeconds(lang: AppLanguage, h: Int, m: Int, s: Int): String = when (lang) {
        AppLanguage.AR -> "$h س $m د $s ث إلهية"
        AppLanguage.EN -> "${h}h ${m}min ${s}s divine"
        AppLanguage.FR -> "${h}h ${m}min ${s}s divines"
    }

    fun divineUnitMinutesSeconds(lang: AppLanguage, m: Int, s: Int): String = when (lang) {
        AppLanguage.AR -> "$m د $s ث إلهية"
        AppLanguage.EN -> "${m}min ${s}s divine"
        AppLanguage.FR -> "${m}min ${s}s divines"
    }

    fun divineUnitSeconds(lang: AppLanguage, value: Double, decimals: Int): String {
        val formatted = String.format(Locale.US, "%.${decimals}f", value)
        return when (lang) {
            AppLanguage.AR -> "$formatted ثانية إلهية"
            AppLanguage.EN -> "$formatted divine sec"
            AppLanguage.FR -> "$formatted sec divine"
        }
    }

    fun divineMinutesShort(lang: AppLanguage, value: String): String = when (lang) {
        AppLanguage.AR -> "$value د إلهية"
        AppLanguage.EN -> "$value divine min"
        AppLanguage.FR -> "$value min divines"
    }

    /**
     * Formats an arbitrary celestial duration by picking the unit that keeps
     * the number readable: fractions of a divine second for a single prayer,
     * divine hours/minutes for a whole challenge.
     */
    fun divineEquivalentShort(lang: AppLanguage, divineSeconds: Double): String {
        if (divineSeconds < 1.0) return divineUnitSeconds(lang, divineSeconds, 4)
        val total = divineSeconds.toLong()
        val h = (total / 3600).toInt()
        val m = ((total % 3600) / 60).toInt()
        val s = (total % 60).toInt()
        return if (h > 0) {
            divineUnitHoursMinutesSeconds(lang, h, m, s)
        } else {
            divineUnitMinutesSeconds(lang, m, s)
        }
    }

    fun divineMinutesOnly(lang: AppLanguage, value: String): String = when (lang) {
        AppLanguage.AR -> "$value دقيقة إلهية فقط"
        AppLanguage.EN -> "$value DIVINE MINUTES ONLY"
        AppLanguage.FR -> "$value MIN DIVINES SEULEMENT"
    }

    fun percentOfLifeLabel(lang: AppLanguage, percent: Float): String {
        val formatted = String.format(Locale.US, "%.1f", percent)
        return when (lang) {
            AppLanguage.AR -> "$formatted% من حياتك"
            AppLanguage.EN -> "$formatted% of your life"
            AppLanguage.FR -> "$formatted% de ta vie"
        }
    }

    fun earthYearsMonthsDays(lang: AppLanguage, y: Int, mo: Int, dd: Int): String = when (lang) {
        AppLanguage.AR -> "$y سنة، $mo شهر ($dd يوم)"
        AppLanguage.EN -> "$y yrs, $mo months ($dd days)"
        AppLanguage.FR -> "$y ans, $mo mois ($dd j)"
    }

    fun earthYearsMonths(lang: AppLanguage, y: Int, mo: Int): String = when (lang) {
        AppLanguage.AR -> "$y سنة، $mo شهر"
        AppLanguage.EN -> "$y yrs, $mo months"
        AppLanguage.FR -> "$y ans, $mo mois"
    }

    fun earthYearsDays(lang: AppLanguage, y: Int, dd: Int): String = when (lang) {
        AppLanguage.AR -> "$y سنة ($dd يوم)"
        AppLanguage.EN -> "$y yrs ($dd days)"
        AppLanguage.FR -> "$y ans ($dd j)"
    }

    fun earthYearsOnly(lang: AppLanguage, y: Int): String = when (lang) {
        AppLanguage.AR -> "$y سنة"
        AppLanguage.EN -> "$y yrs"
        AppLanguage.FR -> "$y ans"
    }

    fun earthDaysHours(lang: AppLanguage, dd: Int, hh: Int): String = when (lang) {
        AppLanguage.AR -> "$dd يوم، $hh ساعة"
        AppLanguage.EN -> "$dd days, $hh hours"
        AppLanguage.FR -> "$dd jours, $hh heures"
    }

    fun earthHoursMinutes(lang: AppLanguage, hh: Int, mm: Int): String = when (lang) {
        AppLanguage.AR -> "$hh ساعة، $mm دقيقة"
        AppLanguage.EN -> "$hh hours, $mm minutes"
        AppLanguage.FR -> "$hh heures, $mm minutes"
    }

    // ---------------------------------------------------------------------
    // Journal screen extras
    // ---------------------------------------------------------------------

    fun scoreMessage(lang: AppLanguage, score: Int): String = when (score) {
        5 -> when (lang) {
            AppLanguage.AR -> "يوم من الذهب الخالص • الله أكبر"
            AppLanguage.EN -> "A day of pure gold • Allahu Akbar"
            AppLanguage.FR -> "Journée d'or pur • Allahu Akbar"
        }
        4 -> when (lang) {
            AppLanguage.AR -> "استثمار روحي جميل جداً"
            AppLanguage.EN -> "A very fine spiritual investment"
            AppLanguage.FR -> "Très bel investissement spirituel"
        }
        3 -> when (lang) {
            AppLanguage.AR -> "انتظام جيد، واصل!"
            AppLanguage.EN -> "Good consistency, keep going!"
            AppLanguage.FR -> "Bonne régularité, persévère !"
        }
        2 -> when (lang) {
            AppLanguage.AR -> "لا تدع الثانية تفلت منك"
            AppLanguage.EN -> "Do not let the second slip away"
            AppLanguage.FR -> "Ne laisse pas la seconde s'enfuir"
        }
        else -> when (lang) {
            AppLanguage.AR -> "ابدأ بخطوة نحو الله"
            AppLanguage.EN -> "Start with one step towards Allah"
            AppLanguage.FR -> "Commence par un pas vers Allah"
        }
    }

    fun prayerDeedLabel(lang: AppLanguage, key: String): String {
        val name = prayerName(lang, key)
        val detail = when (key.lowercase()) {
            "fajr" -> when (lang) {
                AppLanguage.AR -> "صلاة الفجر"
                AppLanguage.EN -> "Dawn prayer"
                AppLanguage.FR -> "Prière de l'Aube"
            }
            "dhuhr" -> when (lang) {
                AppLanguage.AR -> "صلاة الظهر"
                AppLanguage.EN -> "Noon prayer"
                AppLanguage.FR -> "Prière de Midi"
            }
            "asr" -> when (lang) {
                AppLanguage.AR -> "صلاة العصر"
                AppLanguage.EN -> "Afternoon prayer"
                AppLanguage.FR -> "Prière de l'Après-midi"
            }
            "maghrib" -> when (lang) {
                AppLanguage.AR -> "صلاة المغرب"
                AppLanguage.EN -> "Sunset prayer"
                AppLanguage.FR -> "Prière du Coucher"
            }
            else -> when (lang) {
                AppLanguage.AR -> "صلاة العشاء"
                AppLanguage.EN -> "Night prayer"
                AppLanguage.FR -> "Prière de la Nuit"
            }
        }
        return "$name ($detail)"
    }

    fun prayerDeedSubtitle(lang: AppLanguage, key: String): String {
        val phrase = when (key.lowercase()) {
            "fajr" -> when (lang) {
                AppLanguage.AR -> "أفضل من الدنيا وما فيها"
                AppLanguage.EN -> "Better than this world and all it holds"
                AppLanguage.FR -> "Meilleur que ce monde"
            }
            "dhuhr" -> when (lang) {
                AppLanguage.AR -> "الاستراحة السماوية"
                AppLanguage.EN -> "The celestial pause"
                AppLanguage.FR -> "La pause céleste"
            }
            "asr" -> when (lang) {
                AppLanguage.AR -> "الصلاة الوسطى"
                AppLanguage.EN -> "The middle prayer"
                AppLanguage.FR -> "La prière médiane"
            }
            "maghrib" -> when (lang) {
                AppLanguage.AR -> "شكر المساء"
                AppLanguage.EN -> "Evening gratitude"
                AppLanguage.FR -> "Gratitude du soir"
            }
            else -> when (lang) {
                AppLanguage.AR -> "خاتمة مضيئة"
                AppLanguage.EN -> "A luminous closing"
                AppLanguage.FR -> "Clôture lumineuse"
            }
        }
        val value = String.format(
            Locale.US,
            "%.4f",
            com.example.data.calculator.DivineTimeCalculator.DIVINE_SECONDS_PER_EARTH_MINUTE * 5.0
        )
        return when (lang) {
            AppLanguage.AR -> "$value ثانية إلهية • $phrase"
            AppLanguage.EN -> "$value divine sec • $phrase"
            AppLanguage.FR -> "$value sec divine • $phrase"
        }
    }

    fun sadaqaSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "حتى ابتسامة، أو مساعدة، أو كلمة طيبة"
        AppLanguage.EN -> "Even a smile, some help or a kind word"
        AppLanguage.FR -> "Même un sourire, de l'aide ou une parole bienveillante"
    }

    fun dhikrSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "درع روحي ليومك"
        AppLanguage.EN -> "A spiritual shield for the day"
        AppLanguage.FR -> "Bouclier spirituel pour la journée"
    }

    fun goodDeedSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "للوالدين، للأقارب أو لأي إنسان"
        AppLanguage.EN -> "To your parents, relatives or a stranger"
        AppLanguage.FR -> "Envers tes parents, tes proches ou un inconnu"
    }

    fun journalNotePlaceholder(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "اكتب وعياً أو توبة أو شكراً لهذا اليوم..."
        AppLanguage.EN -> "Write an insight, a repentance or a gratitude for today..."
        AppLanguage.FR -> "Écris une prise de conscience, un repentir ou une gratitude pour aujourd'hui..."
    }

    // ---------------------------------------------------------------------
    // Temporal converter extras
    // ---------------------------------------------------------------------

    fun enterEarthDuration(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "أدخل مدة أرضية:"
        AppLanguage.EN -> "ENTER AN EARTHLY DURATION:"
        AppLanguage.FR -> "ENTRE UNE DURÉE TERRESTRE :"
    }

    fun enterDivineMinutes(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "أدخل دقائق إلهية:"
        AppLanguage.EN -> "ENTER DIVINE MINUTES:"
        AppLanguage.FR -> "ENTRE DES MINUTES DIVINES :"
    }

    fun divineMinutesHint(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "دقائق إلهية (مثلاً: 1 = حوالي 253 يوماً)"
        AppLanguage.EN -> "Divine minutes (e.g. 1 = about 253 days)"
        AppLanguage.FR -> "Minutes divines (ex : 1 = ~253 jours)"
    }

    fun exampleTitle(lang: AppLanguage, id: String): String = when (id) {
        "fajr" -> when (lang) {
            AppLanguage.AR -> "صلاة الفجر"
            AppLanguage.EN -> "Fajr Prayer"
            AppLanguage.FR -> "Prière du Fajr"
        }
        "ramadan" -> when (lang) {
            AppLanguage.AR -> "شهر رمضان المبارك"
            AppLanguage.EN -> "The Blessed Month of Ramadan"
            AppLanguage.FR -> "Mois Béni de Ramadan"
        }
        "prophet" -> when (lang) {
            AppLanguage.AR -> "حياة النبي ﷺ"
            AppLanguage.EN -> "The Life of the Prophet ﷺ"
            AppLanguage.FR -> "Vie du Prophète ﷺ"
        }
        "studies" -> when (lang) {
            AppLanguage.AR -> "كل سنوات دراستك"
            AppLanguage.EN -> "All your years of study"
            AppLanguage.FR -> "Toute ta scolarité & études"
        }
        "qadr" -> when (lang) {
            AppLanguage.AR -> "ليلة القدر"
            AppLanguage.EN -> "The Night of Destiny (Laylatul Qadr)"
            AppLanguage.FR -> "Nuit du Destin (Laylatul Qadr)"
        }
        "scroll" -> when (lang) {
            AppLanguage.AR -> "التصفح على الشبكات الاجتماعية"
            AppLanguage.EN -> "Scrolling social media"
            AppLanguage.FR -> "Scroll sur réseaux sociaux"
        }
        else -> when (lang) {
            AppLanguage.AR -> "ليلة نوم"
            AppLanguage.EN -> "A night of sleep"
            AppLanguage.FR -> "Une nuit de sommeil"
        }
    }

    fun exampleEarth(lang: AppLanguage, id: String): String = when (id) {
        "fajr" -> when (lang) {
            AppLanguage.AR -> "5 دقائق"
            AppLanguage.EN -> "5 minutes"
            AppLanguage.FR -> "5 minutes"
        }
        "ramadan" -> when (lang) {
            AppLanguage.AR -> "30 يوماً"
            AppLanguage.EN -> "30 days"
            AppLanguage.FR -> "30 jours"
        }
        "prophet" -> when (lang) {
            AppLanguage.AR -> "63 سنة"
            AppLanguage.EN -> "63 years"
            AppLanguage.FR -> "63 ans"
        }
        "studies" -> when (lang) {
            AppLanguage.AR -> "20 سنة"
            AppLanguage.EN -> "20 years"
            AppLanguage.FR -> "20 ans"
        }
        "qadr" -> when (lang) {
            AppLanguage.AR -> "> 1000 شهر (83.3 سنة)"
            AppLanguage.EN -> "> 1000 months (83.3 yrs)"
            AppLanguage.FR -> "> 1000 mois (83.3 ans)"
        }
        "scroll" -> when (lang) {
            AppLanguage.AR -> "30 دقيقة"
            AppLanguage.EN -> "30 minutes"
            AppLanguage.FR -> "30 minutes"
        }
        else -> when (lang) {
            AppLanguage.AR -> "8 ساعات"
            AppLanguage.EN -> "8 hours"
            AppLanguage.FR -> "8 heures"
        }
    }

    fun exampleDescription(lang: AppLanguage, id: String): String = when (id) {
        "fajr" -> when (lang) {
            AppLanguage.AR -> "خمس دقائق صغيرة على الأرض تمثل جزءاً من ألف من الثانية عند الله. ومع ذلك تضيء يومك كله."
            AppLanguage.EN -> "Five short minutes on earth are a thousandth of a second with Allah. Yet they light up your whole day."
            AppLanguage.FR -> "5 petites minutes sur terre représentent un millième de seconde auprès d'Allah. Pourtant, elle illumine toute ta journée."
        }
        "ramadan" -> when (lang) {
            AppLanguage.AR -> "الصيام والقيام وروحانية شهر كامل لا تدوم سوى 7 ثوانٍ إلهية."
            AppLanguage.EN -> "The fasting, night prayers and fervour of a whole month last only 7 divine seconds."
            AppLanguage.FR -> "Le jeûne, les prières nocturnes et la ferveur d'un mois entier ne durent que 7 secondes divines."
        }
        "prophet" -> when (lang) {
            AppLanguage.AR -> "حياة رسول الله ﷺ كلها، التي أنارت البشرية، دامت ساعة ونصف في الزمن الإلهي."
            AppLanguage.EN -> "The whole life of the Messenger of Allah ﷺ, which illuminated humanity, lasted 1h30 in divine time."
            AppLanguage.FR -> "Toute la vie du Messager d'Allah ﷺ qui a illuminé l'humanité a duré 1h30 dans le temps divin."
        }
        "studies" -> when (lang) {
            AppLanguage.AR -> "عشرون سنة من الجهد والشهادات والمراجعة لا تزن حتى نصف ساعة إلهية."
            AppLanguage.EN -> "Twenty years of effort, diplomas and revision do not even weigh half a divine hour."
            AppLanguage.FR -> "Vingt années d'efforts, de diplômes et de révisions ne pèsent même pas une demi-heure divine."
        }
        "qadr" -> when (lang) {
            AppLanguage.AR -> "« خير من ألف شهر »: ليلة واحدة من العبادة على الأرض تتفوق على حياة إلهية كاملة من ساعتين!"
            AppLanguage.EN -> "« Better than a thousand months »: one single night of worship on earth surpasses a whole two-hour divine life!"
            AppLanguage.FR -> "« Meilleure que 1000 mois » : Une seule nuit d'adoration sur Terre surpasse toute une vie divine de 2 heures !"
        }
        "scroll" -> when (lang) {
            AppLanguage.AR -> "جزء من ثانية إلهية محروق في الفراغ. احترس مما تزرع."
            AppLanguage.EN -> "A fraction of a divine second burned into the void. Beware of what you sow."
            AppLanguage.FR -> "Une fraction de seconde divine brûlée dans le vide. Prends garde à ce que tu sèmes."
        }
        else -> when (lang) {
            AppLanguage.AR -> "كل ليلة، تخرج روحك عُشر ثانية إلهية قبل أن تعود."
            AppLanguage.EN -> "Every night your soul slips away for a tenth of a divine second before returning."
            AppLanguage.FR -> "Chaque nuit, ton âme s'échappe un dixième de seconde divine avant de revenir."
        }
    }

    // ─────────────────────────────────────────────────────────────
    //  LE CYCLE QUOTIDIEN (Dhawq) — L'Invitation, Le Vide,
    //  La Minute Précieuse, La Question du Goût, La Fenêtre d'or
    // ─────────────────────────────────────────────────────────────

    /** L'Invitation — the countdown card title. */
    fun invitationTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "إنه يدعوك"
        AppLanguage.EN -> "He is calling you"
        AppLanguage.FR -> "Il t'appelle"
    }

    /** L'Invitation — the countdown card subtitle. */
    fun rendezVousLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الموعد"
        AppLanguage.EN -> "The Appointment"
        AppLanguage.FR -> "Le Rendez-Vous"
    }

    /** Le Vide — the single word shown while the prayer window is open. */
    fun leVideWord(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "اذهب."
        AppLanguage.EN -> "Go."
        AppLanguage.FR -> "Va."
    }

    /** Le Vide — the quiet line under the word. */
    fun leVideSubtitle(lang: AppLanguage, prayerFr: String, prayerAr: String): String = when (lang) {
        AppLanguage.AR -> "بدأ موعد $prayerAr. ينسحب التطبيق خلال صلاتك."
        AppLanguage.EN -> "The $prayerFr appointment has begun. The app steps aside during your prayer."
        AppLanguage.FR -> "Le Rendez-Vous de $prayerFr a commencé. L'application se retire pendant ta prière."
    }

    /** Le Vide — the discreet way back in. */
    fun leVideLater(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "لاحقًا"
        AppLanguage.EN -> "Later"
        AppLanguage.FR -> "Plus tard"
    }

    /** La Minute Précieuse — title (the assise that follows the salam). */
    fun preciousMinuteTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الدقيقة الثمينة"
        AppLanguage.EN -> "The Precious Minute"
        AppLanguage.FR -> "La Minute Précieuse"
    }

    /** La Minute Précieuse — the invitation to stay seated. */
    fun preciousMinuteBody(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "لا ترحل بعدُ. ستّون ثانية."
        AppLanguage.EN -> "Do not leave yet. Sixty seconds."
        AppLanguage.FR -> "Ne pars pas encore. Soixante secondes."
    }

    /** La Minute Précieuse — shown when the sixty seconds are over. */
    fun preciousMinuteDone(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "آمين."
        AppLanguage.EN -> "Ameen."
        AppLanguage.FR -> "Amine."
    }

    /** La Minute Précieuse — discreet skip. */
    fun preciousMinuteSkip(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تخطٍّ"
        AppLanguage.EN -> "Skip"
        AppLanguage.FR -> "Passer"
    }

    /** La Question du Goût — asked once the minute is over. */
    fun tasteQuestion(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "صلاتك اليوم، ما طعمها؟"
        AppLanguage.EN -> "Today's prayer — what did it taste like?"
        AppLanguage.FR -> "Ta prière d'aujourd'hui, quel goût ?"
    }

    fun tasteDouce(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "حلوة"
        AppLanguage.EN -> "Sweet"
        AppLanguage.FR -> "Douce"
    }

    fun tasteLourde(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "ثقيلة"
        AppLanguage.EN -> "Heavy"
        AppLanguage.FR -> "Lourde"
    }

    fun tasteAbsente(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "غائبة"
        AppLanguage.EN -> "Absent"
        AppLanguage.FR -> "Absente"
    }

    fun tasteGoutee(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "مُذاقة"
        AppLanguage.EN -> "Tasted"
        AppLanguage.FR -> "Goûtée"
    }

    /** La Fenêtre d'or — the last third of the night. */
    fun goldenWindowBadge(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "ساعة الإجابة — الباب مفتوح على مصراعيه"
        AppLanguage.EN -> "Hour of response — the door is wide open"
        AppLanguage.FR -> "Heure d'exaucement — la porte est grande ouverte"
    }

    // ─────────────────────────────────────────────────────────────
    //  LE VOYAGE DE LA PRIÈRE (Saison 1 — les 10 stations)
    // ─────────────────────────────────────────────────────────────

    /** Bottom navigation label for the Journey screen. */
    fun navJourney(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الرحلة"
        AppLanguage.EN -> "Journey"
        AppLanguage.FR -> "Voyage"
    }

    fun journeyTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "رحلة الصلاة"
        AppLanguage.EN -> "The Prayer Journey"
        AppLanguage.FR -> "Le Voyage de la Prière"
    }

    fun journeySubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "عشر محطات لقلبك قبل جسدك. محطة واحدة قبل كل صلاة — ثم اذهب وصلِّ بلا هاتف."
        AppLanguage.EN -> "Ten stations for your heart before your body. One station before each prayer — then go and pray without your phone."
        AppLanguage.FR -> "Dix stations pour ton cœur avant ton corps. Une station avant chaque prière — puis va prier sans ton téléphone."
    }

    /** "Station 3 of 10" progress label. */
    fun journeyStationProgress(lang: AppLanguage, index: Int, total: Int): String = when (lang) {
        AppLanguage.AR -> "المحطة $index من $total"
        AppLanguage.EN -> "Station $index of $total"
        AppLanguage.FR -> "Station $index sur $total"
    }

    fun journeyListen(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "استمع قبل الصلاة"
        AppLanguage.EN -> "Listen before praying"
        AppLanguage.FR -> "Écouter avant de prier"
    }

    fun journeyPause(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "إيقاف مؤقت"
        AppLanguage.EN -> "Pause"
        AppLanguage.FR -> "Pause"
    }

    /** Shown when the audio of a station has not been recorded yet. */
    fun journeyComingSoon(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "التسجيل قريبًا"
        AppLanguage.EN -> "Recording coming soon"
        AppLanguage.FR -> "Enregistrement à venir"
    }

    /** Prefix before the classical source of a station. */
    fun journeySourcePrefix(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "المصدر"
        AppLanguage.EN -> "Source"
        AppLanguage.FR -> "Source"
    }
}

