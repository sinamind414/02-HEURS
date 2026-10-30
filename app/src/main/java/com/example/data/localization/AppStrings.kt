package com.example.data.localization

import com.example.data.model.AppLanguage
import com.example.data.model.InnerWeather
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
        AppLanguage.ES -> "DHAwQ"
        AppLanguage.ID -> "DHAwQ"
        AppLanguage.FR -> "DHAwQ"
    }

    fun appSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "ذَوْق • تَذَوُّقُ الصَّلاة"
        AppLanguage.EN -> "ذَوْق • PRAYER, TASTED"
        AppLanguage.ES -> "ذَوْق • ORACIÓN, SABOREADA"
        AppLanguage.ID -> "ذَوْق • SALAT YANG DIRASAKAN"
        AppLanguage.FR -> "ذَوْق • LA PRIÈRE GOÛTÉE"
    }

    fun logoContentDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "شعار ذوق"
        AppLanguage.EN -> "Dhawq logo"
        AppLanguage.ES -> "Logotipo de Dhawq"
        AppLanguage.ID -> "Logo Dhawq"
        AppLanguage.FR -> "Logo Dhawq"
    }

    // ---------------------------------------------------------------------
    // Navigation
    // ---------------------------------------------------------------------

    fun navClock(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "العداد"
        AppLanguage.EN -> "Clock"
        AppLanguage.ES -> "Reloj"
        AppLanguage.ID -> "Jam"
        AppLanguage.FR -> "Compteur"
    }

    fun navConverter(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "المحوّل"
        AppLanguage.EN -> "Converter"
        AppLanguage.ES -> "Convertidor"
        AppLanguage.ID -> "Konverter"
        AppLanguage.FR -> "Convertisseur"
    }

    fun navJournal(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "اليوميات"
        AppLanguage.EN -> "Journal"
        AppLanguage.ES -> "Diario"
        AppLanguage.ID -> "Jurnal"
        AppLanguage.FR -> "Journal"
    }

    fun navVisualizer(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "رحلة د2"
        AppLanguage.EN -> "2m Journey"
        AppLanguage.ES -> "Viaje de 2 min"
        AppLanguage.ID -> "Perjalanan 2 mnt"
        AppLanguage.FR -> "Voyage 2m"
    }

    fun navChallenges(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "التحديات"
        AppLanguage.EN -> "Challenges"
        AppLanguage.ES -> "Desafíos"
        AppLanguage.ID -> "Tantangan"
        AppLanguage.FR -> "Défis"
    }

    fun navSettings(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "التذكير"
        AppLanguage.EN -> "Reminders"
        AppLanguage.ES -> "Recordatorios"
        AppLanguage.ID -> "Pengingat"
        AppLanguage.FR -> "Rappels"
    }

    // ---------------------------------------------------------------------
    // Main clock screen
    // ---------------------------------------------------------------------

    fun yourTimeWithAllah(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "🕐 وَقْتُكَ عِنْدَ اللَّه"
        AppLanguage.EN -> "🕐 YOUR TIME WITH ALLAH"
        AppLanguage.ES -> "🕐 TU TIEMPO CON ALLAH"
        AppLanguage.ID -> "🕐 WAKTUMU BERSAMA ALLAH"
        AppLanguage.FR -> "🕐 TON TEMPS CHEZ ALLAH"
    }

    fun centralConceptQuote(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« حياتك كلها = نحو ساعتين عند الله » (الحج: 47)"
        AppLanguage.EN -> "« Your entire life = ~2 hours with Allah » (Al-Hajj:47)"
        AppLanguage.ES -> "« Toda tu vida = ~2 horas con Allah » (Al-Hayy:47)"
        AppLanguage.ID -> "« Seluruh hidupmu = ~2 jam bersama Allah » (Al-Hajj:47)"
        AppLanguage.FR -> "« Ta vie entière = ~2 heures chez Allah » (Al-Hajj:47)"
    }

    fun livedBadge(lang: AppLanguage, percent: Float): String = when (lang) {
        AppLanguage.AR -> String.format(Locale.US, "%.1f%% مضى", percent)
        AppLanguage.EN -> String.format(Locale.US, "%.1f%% LIVED", percent)
        AppLanguage.ES -> String.format(Locale.US, "%.1f%% VIVIDO", percent)
        AppLanguage.ID -> String.format(Locale.US, "%.1f%% TERLALUI", percent)
        AppLanguage.FR -> String.format(Locale.US, "%.1f%% VÉCU", percent)
    }

    fun comparativeTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "⚖️ المقارنة الزمنية"
        AppLanguage.EN -> "⚖️ TIME COMPARISON"
        AppLanguage.ES -> "⚖️ COMPARACIÓN DE TIEMPO"
        AppLanguage.ID -> "⚖️ PERBANDINGAN WAKTU"
        AppLanguage.FR -> "⚖️ BILAN TEMPOREL COMPARATIF"
    }

    fun onEarth(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "في الأرض"
        AppLanguage.EN -> "On Earth"
        AppLanguage.ES -> "En la Tierra"
        AppLanguage.ID -> "Di Bumi"
        AppLanguage.FR -> "Sur Terre"
    }

    fun withAllahLived(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "عند الله (المقضي)"
        AppLanguage.EN -> "With Allah (Lived)"
        AppLanguage.ES -> "Con Allah (Vivido)"
        AppLanguage.ID -> "Bersama Allah (Dijalani)"
        AppLanguage.FR -> "Chez Allah (Consommé)"
    }

    fun withAllahRemaining(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الوقت الإلهي المتبقي المقدر"
        AppLanguage.EN -> "Estimated Divine Time Left"
        AppLanguage.ES -> "Tiempo divino restante estimado"
        AppLanguage.ID -> "Estimasi Sisa Waktu Ilahi"
        AppLanguage.FR -> "Temps Divin Restant Estimé"
    }

    /**
     * Earth age summary, e.g. "31 ans, 4 mois, 12 jours".
     */
    fun earthAgeSummary(lang: AppLanguage, years: Int, months: Int, days: Long): String = when (lang) {
        AppLanguage.AR -> "$years سنة، $months شهرًا، $days يومًا"
        AppLanguage.EN -> "$years yrs, $months months, $days days"
        AppLanguage.ES -> "$years a, $months meses, $days días"
        AppLanguage.ID -> "$years thn, $months bln, $days hari"
        AppLanguage.FR -> "$years ans, $months mois, $days jours"
    }

    fun divineElapsedLabel(lang: AppLanguage, hours: Int, minutes: Int, seconds: Int): String = when (lang) {
        AppLanguage.AR -> "$hours س $minutes د $seconds ث"
        AppLanguage.EN -> "${hours}h ${minutes}min ${seconds}sec"
        AppLanguage.ES -> "${hours}h ${minutes}min ${seconds}seg"
        AppLanguage.ID -> "${hours}j ${minutes}mnt ${seconds}dtk"
        AppLanguage.FR -> "${hours}h ${minutes}min ${seconds}sec"
    }

    fun divineRemainingLabel(lang: AppLanguage, hours: Int, minutes: Int, seconds: Int): String = when (lang) {
        AppLanguage.AR -> "~$hours س $minutes د $seconds ث"
        AppLanguage.EN -> "~ ${hours}h ${minutes}min ${seconds}sec"
        AppLanguage.ES -> "~ ${hours}h ${minutes}min ${seconds}seg"
        AppLanguage.ID -> "~ ${hours}j ${minutes}mnt ${seconds}dtk"
        AppLanguage.FR -> "~ ${hours}h ${minutes}min ${seconds}sec"
    }

    fun impactBannerTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "حقيقة الوقت"
        AppLanguage.EN -> "REALITY OF TIME"
        AppLanguage.ES -> "REALIDAD DEL TIEMPO"
        AppLanguage.ID -> "REALITAS WAKTU"
        AppLanguage.FR -> "RÉALITÉ DU TEMPS"
    }

    /**
     * 1 divine second = 1000 earth years / 24h / 365.2425 days
     * => 365242.5 / 86400 = 4.2273 earth days per divine second.
     */
    fun impactBannerText(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« كل ثانية إلهية = 4.23 يوماً أرضياً »"
        AppLanguage.EN -> "« Each divine second = 4.23 earth days »"
        AppLanguage.ES -> "« Cada segundo divino = 4.23 días terrestres »"
        AppLanguage.ID -> "« Setiap detik ilahi = 4.23 hari bumi »"
        AppLanguage.FR -> "« Chaque seconde divine = 4,23 jours terrestres »"
    }

    fun impactBannerDesc(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "كل نبضة في ساعتك الإلهية تبتلع أكثر من أربعة أيام من الدنيا."
        AppLanguage.EN -> "Every tick of your divine clock consumes more than four days of this world."
        AppLanguage.ES -> "Cada tic de tu reloj divino consume más de cuatro días de este mundo."
        AppLanguage.ID -> "Setiap detak jam ilahimu menghabiskan lebih dari empat hari dunia ini."
        AppLanguage.FR -> "Chaque battement de ton horloge divine engloutit plus de quatre jours d'ici-bas."
    }

    fun minuteDivineQuestion(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "🤲 ماذا قدمت في دقيقتك الإلهية؟"
        AppLanguage.EN -> "🤲 WHAT DID YOU DO WITH YOUR DIVINE MINUTE?"
        AppLanguage.ES -> "🤲 ¿QUÉ HICISTE CON TU MINUTO DIVINO?"
        AppLanguage.ID -> "🤲 APA YANG KAU LAKUKAN DENGAN MENIT ILAHIMU?"
        AppLanguage.FR -> "🤲 QU'AS-TU FAIT DE TA MINUTE DIVINE ?"
    }

    fun openJournalBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "فتح اليوميات"
        AppLanguage.EN -> "Open Journal"
        AppLanguage.ES -> "Abrir el diario"
        AppLanguage.ID -> "Buka Jurnal"
        AppLanguage.FR -> "Ouvrir le Journal"
    }

    fun watchIn2MinBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "شاهد في دقيقتين"
        AppLanguage.EN -> "Watch in 2 min"
        AppLanguage.ES -> "Ver en 2 min"
        AppLanguage.ID -> "Tonton 2 mnt"
        AppLanguage.FR -> "Voir en 2 min"
    }

    // ---------------------------------------------------------------------
    // Prayer times (Aladhan)
    // ---------------------------------------------------------------------

    fun nextPrayerLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الصلاة القادمة"
        AppLanguage.EN -> "NEXT PRAYER"
        AppLanguage.ES -> "PRÓXIMA ORACIÓN"
        AppLanguage.ID -> "SALAT BERIKUTNYA"
        AppLanguage.FR -> "PROCHAINE PRIÈRE"
    }

    fun inCountdown(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "بعد"
        AppLanguage.EN -> "IN"
        AppLanguage.ES -> "EN"
        AppLanguage.ID -> "DALAM"
        AppLanguage.FR -> "DANS"
    }

    fun countdownLabel(lang: AppLanguage, countdown: String): String = when (lang) {
        AppLanguage.AR -> "بعد $countdown"
        AppLanguage.EN -> "IN $countdown"
        AppLanguage.ES -> "EN $countdown"
        AppLanguage.ID -> "DALAM $countdown"
        AppLanguage.FR -> "DANS $countdown"
    }

    fun divineWaiting(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الانتظار عند الله"
        AppLanguage.EN -> "Waiting with Allah"
        AppLanguage.ES -> "Esperando con Allah"
        AppLanguage.ID -> "Menunggu bersama Allah"
        AppLanguage.FR -> "Attente chez Allah"
    }

    fun divineWaitingLabel(lang: AppLanguage, divineValue: String): String = when (lang) {
        AppLanguage.AR -> "الانتظار عند الله: $divineValue"
        AppLanguage.EN -> "Waiting with Allah: $divineValue"
        AppLanguage.ES -> "Esperando con Allah: $divineValue"
        AppLanguage.ID -> "Menunggu bersama Allah: $divineValue"
        AppLanguage.FR -> "Attente chez Allah : $divineValue"
    }

    fun cityPrayerTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "مدينة مواقيت الصلاة"
        AppLanguage.EN -> "Prayer Times City"
        AppLanguage.ES -> "Ciudad de horarios de oración"
        AppLanguage.ID -> "Kota Jadwal Salat"
        AppLanguage.FR -> "VILLE POUR LES PRIÈRES"
    }

    fun cityPrayerSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تُحسب المواقيت عبر واجهة Aladhan حسب مدينتك."
        AppLanguage.EN -> "Timings are computed by the Aladhan API for the city you select."
        AppLanguage.ES -> "Los horarios los calcula la API Aladhan para la ciudad que elijas."
        AppLanguage.ID -> "Jadwal dihitung oleh API Aladhan untuk kota yang kamu pilih."
        AppLanguage.FR -> "Horaires calculés via l'API Aladhan selon ta localisation."
    }

    fun fastCities(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "مدن سريعة :"
        AppLanguage.EN -> "Quick cities:"
        AppLanguage.ES -> "Ciudades rápidas:"
        AppLanguage.ID -> "Kota cepat:"
        AppLanguage.FR -> "Villes rapides :"
    }

    fun cityContentDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "المدينة"
        AppLanguage.EN -> "City"
        AppLanguage.ES -> "Ciudad"
        AppLanguage.ID -> "Kota"
        AppLanguage.FR -> "Ville"
    }

    fun refreshContentDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تحديث"
        AppLanguage.EN -> "Refresh"
        AppLanguage.ES -> "Actualizar"
        AppLanguage.ID -> "Segarkan"
        AppLanguage.FR -> "Actualiser"
    }

    fun prayerTimesFallbackBadge(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "⚠️ مواقيت احتياطية (تعذّر الاتصال)"
        AppLanguage.EN -> "⚠️ Offline fallback times"
        AppLanguage.ES -> "⚠️ Horarios sin conexión (respaldo)"
        AppLanguage.ID -> "⚠️ Jadwal cadangan luring"
        AppLanguage.FR -> "⚠️ Horaires de repli (réseau indisponible)"
    }

    fun prayerTimesError(lang: AppLanguage, error: String): String = when (lang) {
        AppLanguage.AR -> "تعذّر جلب المواقيت: $error"
        AppLanguage.EN -> "Could not fetch prayer times: $error"
        AppLanguage.ES -> "No se pudieron obtener los horarios de oración: $error"
        AppLanguage.ID -> "Gagal mengambil jadwal salat: $error"
        AppLanguage.FR -> "Impossible de récupérer les horaires : $error"
    }

    fun applyBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تطبيق"
        AppLanguage.EN -> "Apply"
        AppLanguage.ES -> "Aplicar"
        AppLanguage.ID -> "Terapkan"
        AppLanguage.FR -> "Appliquer"
    }

    fun cancelBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "إلغاء"
        AppLanguage.EN -> "Cancel"
        AppLanguage.ES -> "Cancelar"
        AppLanguage.ID -> "Batal"
        AppLanguage.FR -> "Annuler"
    }

    fun saveBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "حفظ"
        AppLanguage.EN -> "Save"
        AppLanguage.ES -> "Guardar"
        AppLanguage.ID -> "Simpan"
        AppLanguage.FR -> "Enregistrer"
    }

    // ---------------------------------------------------------------------
    // Two divine hours life map
    // ---------------------------------------------------------------------

    fun lifeMapTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "رحلة الساعتين الإلهيتين"
        AppLanguage.EN -> "THE 2 DIVINE HOURS JOURNEY"
        AppLanguage.ES -> "EL VIAJE DE LAS 2 HORAS DIVINAS"
        AppLanguage.ID -> "PERJALANAN 2 JAM ILAHI"
        AppLanguage.FR -> "LE VOYAGE DES 2 HEURES DIVINES"
    }

    fun lifeMapSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "حياتك كلها (~83 سنة) مرسومة في ساعتين عند الله (120 دقيقة)"
        AppLanguage.EN -> "Your whole life (~83 years) mapped into 2 hours with Allah (120 min)"
        AppLanguage.ES -> "Toda tu vida (~83 años) proyectada en 2 horas con Allah (120 min)"
        AppLanguage.ID -> "Seluruh hidupmu (~83 tahun) dipetakan ke 2 jam bersama Allah (120 mnt)"
        AppLanguage.FR -> "Ta vie entière (~83 ans) projetée sur 2 heures chez Allah (120 min)"
    }

    fun myAgeBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "عمري"
        AppLanguage.EN -> "My Age"
        AppLanguage.ES -> "Mi edad"
        AppLanguage.ID -> "Usiaku"
        AppLanguage.FR -> "Mon Âge"
    }

    fun exploreBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "استكشاف"
        AppLanguage.EN -> "Explore"
        AppLanguage.ES -> "Explorar"
        AppLanguage.ID -> "Jelajahi"
        AppLanguage.FR -> "Explorer"
    }

    fun exploreContentDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "استكشاف"
        AppLanguage.EN -> "Explore"
        AppLanguage.ES -> "Explorar"
        AppLanguage.ID -> "Jelajahi"
        AppLanguage.FR -> "Explorer"
    }

    fun exploredAgeText(lang: AppLanguage, age: Int): String = when (lang) {
        AppLanguage.AR -> "العمر المستكشف: $age سنة"
        AppLanguage.EN -> "Explored age: $age years"
        AppLanguage.ES -> "Edad explorada: $age años"
        AppLanguage.ID -> "Usia dijelajahi: $age tahun"
        AppLanguage.FR -> "Âge exploré : $age ans"
    }

    fun divineMinutesGauge(lang: AppLanguage, elapsed: Double): String = when (lang) {
        AppLanguage.AR -> String.format(Locale.US, "%.1f د / 120 د إلهية", elapsed)
        AppLanguage.EN -> String.format(Locale.US, "%.1f min / 120 divine min", elapsed)
        AppLanguage.ES -> String.format(Locale.US, "%.1f min / 120 min divinos", elapsed)
        AppLanguage.ID -> String.format(Locale.US, "%.1f mnt / 120 mnt ilahi", elapsed)
        AppLanguage.FR -> String.format(Locale.US, "%.1f min / 120 min divines", elapsed)
    }

    fun hour1Title(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الساعة الأولى (0 إلى ~42 سنة)"
        AppLanguage.EN -> "1st Divine Hour (0 to ~42 yrs)"
        AppLanguage.ES -> "1ra Hora Divina (0 a ~42 a)"
        AppLanguage.ID -> "Jam Ilahi ke-1 (0 s.d. ~42 thn)"
        AppLanguage.FR -> "1ère Heure Divine (0 à ~42 ans)"
    }

    fun hour2Title(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الساعة الثانية (~42 إلى ~83 سنة)"
        AppLanguage.EN -> "2nd Divine Hour (~42 to ~83 yrs)"
        AppLanguage.ES -> "2da Hora Divina (~42 a ~83 a)"
        AppLanguage.ID -> "Jam Ilahi ke-2 (~42 s.d. ~83 thn)"
        AppLanguage.FR -> "2ème Heure Divine (~42 à ~83 ans)"
    }

    fun statusInProgress(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "جارية ⚡"
        AppLanguage.EN -> "In progress ⚡"
        AppLanguage.ES -> "En curso ⚡"
        AppLanguage.ID -> "Sedang berlangsung ⚡"
        AppLanguage.FR -> "En cours ⚡"
    }

    fun statusCompleted(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "مكتملة ✓"
        AppLanguage.EN -> "Completed ✓"
        AppLanguage.ES -> "Completado ✓"
        AppLanguage.ID -> "Selesai ✓"
        AppLanguage.FR -> "Terminée ✓"
    }

    fun statusUpcoming(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "قادمة ⏳"
        AppLanguage.EN -> "Upcoming ⏳"
        AppLanguage.ES -> "Próximamente ⏳"
        AppLanguage.ID -> "Akan Datang ⏳"
        AppLanguage.FR -> "À venir ⏳"
    }

    fun lifeMapStartLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "0h00 (الولادة)"
        AppLanguage.EN -> "0h00 (Birth)"
        AppLanguage.ES -> "0h00 (Nacimiento)"
        AppLanguage.ID -> "0j00 (Lahir)"
        AppLanguage.FR -> "0h00 (Naissance)"
    }

    fun lifeMapHour1Label(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "1h00 (~42 سنة)"
        AppLanguage.EN -> "1h00 (~42 yrs)"
        AppLanguage.ES -> "1h00 (~42 a)"
        AppLanguage.ID -> "1j00 (~42 thn)"
        AppLanguage.FR -> "1h00 (~42 ans)"
    }

    fun lifeMapHour2Label(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "2h00 (~83 سنة)"
        AppLanguage.EN -> "2h00 (~83 yrs)"
        AppLanguage.ES -> "2h00 (~83 a)"
        AppLanguage.ID -> "2j00 (~83 thn)"
        AppLanguage.FR -> "2h00 (~83 ans)"
    }

    fun lifeMapMinuteLabel(lang: AppLanguage, minute: Int): String = when (lang) {
        AppLanguage.AR -> "الدقيقة $minute / 120"
        AppLanguage.EN -> "Minute $minute / 120"
        AppLanguage.ES -> "Minuto $minute / 120"
        AppLanguage.ID -> "Menit $minute / 120"
        AppLanguage.FR -> "Minute $minute / 120"
    }

    fun remainingInHourLabel(lang: AppLanguage, minutes: Int): String = when (lang) {
        AppLanguage.AR -> "المتبقي في الساعة: $minutes د"
        AppLanguage.EN -> "Remaining in this hour: $minutes min"
        AppLanguage.ES -> "Restante en esta hora: $minutes min"
        AppLanguage.ID -> "Sisa jam ini: $minutes mnt"
        AppLanguage.FR -> "Reste dans l'heure : $minutes min"
    }

    fun remainingInTwoHoursLabel(lang: AppLanguage, minutes: Double): String = when (lang) {
        AppLanguage.AR -> "المتبقي على الساعتين: ${String.format(Locale.US, "%.1f", minutes)} د"
        AppLanguage.EN -> "Remaining of the 2h: ${String.format(Locale.US, "%.1f", minutes)} min"
        AppLanguage.ES -> "Restante de las 2h: ${String.format(Locale.US, "%.1f", minutes)} min"
        AppLanguage.ID -> "Sisa dari 2j: ${String.format(Locale.US, "%.1f", minutes)} mnt"
        AppLanguage.FR -> "Reste sur les 2h : ${String.format(Locale.US, "%.1f", minutes)} min"
    }

    fun hourNumberLabel(lang: AppLanguage, hourNumber: Int): String = when (lang) {
        AppLanguage.AR -> "الساعة $hourNumber"
        AppLanguage.EN -> "HOUR $hourNumber"
        AppLanguage.ES -> "HORA $hourNumber"
        AppLanguage.ID -> "JAM $hourNumber"
        AppLanguage.FR -> "HEURE $hourNumber"
    }

    fun hourProgressLabel(lang: AppLanguage, minuteElapsed: Int): String = when (lang) {
        AppLanguage.AR -> "$minuteElapsed / 60 د"
        AppLanguage.EN -> "$minuteElapsed / 60 min"
        AppLanguage.ES -> "$minuteElapsed / 60 min"
        AppLanguage.ID -> "$minuteElapsed / 60 mnt"
        AppLanguage.FR -> "$minuteElapsed / 60 min"
    }

    // ---------------------------------------------------------------------
    // Profile dialog
    // ---------------------------------------------------------------------

    fun profileDialogTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "⚙️ الملف الروحي وتاريخ الميلاد"
        AppLanguage.EN -> "⚙️ Spiritual Profile & Birth Date"
        AppLanguage.ES -> "⚙️ Perfil Espiritual & Fecha de Nacimiento"
        AppLanguage.ID -> "⚙️ Profil Spiritual & Tanggal Lahir"
        AppLanguage.FR -> "⚙️ PROFIL SPIRITUEL & NAISSANCE"
    }

    fun birthDateDialogTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "⚙️ تاريخ الميلاد والعمر المتوقع"
        AppLanguage.EN -> "⚙️ BIRTH DATE & LIFE EXPECTANCY"
        AppLanguage.ES -> "⚙️ FECHA DE NACIMIENTO & ESPERANZA DE VIDA"
        AppLanguage.ID -> "⚙️ TANGGAL LAHIR & HARAPAN HIDUP"
        AppLanguage.FR -> "⚙️ DATE DE NAISSANCE & ESPÉRANCE"
    }

    fun birthDateDialogSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "اضبط تاريخ ميلادك لحساب وقتك الإلهي بدقة عند الله."
        AppLanguage.EN -> "Adjust your birth date to compute your divine time with Allah precisely."
        AppLanguage.ES -> "Ajusta tu fecha de nacimiento para calcular con precisión tu tiempo divino con Allah."
        AppLanguage.ID -> "Sesuaikan tanggal lahirmu untuk menghitung waktu ilahimu bersama Allah secara tepat."
        AppLanguage.FR -> "Ajuste ta date de naissance pour calculer précisément ton temps divin chez Allah."
    }

    fun birthDateLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تاريخ ميلادك"
        AppLanguage.EN -> "Your Birth Date"
        AppLanguage.ES -> "Tu Fecha de Nacimiento"
        AppLanguage.ID -> "Tanggal Lahirmu"
        AppLanguage.FR -> "Ta date de naissance :"
    }

    fun dayLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "اليوم"
        AppLanguage.EN -> "Day"
        AppLanguage.ES -> "Día"
        AppLanguage.ID -> "Hari"
        AppLanguage.FR -> "Jour"
    }

    fun monthLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الشهر"
        AppLanguage.EN -> "Month"
        AppLanguage.ES -> "Mes"
        AppLanguage.ID -> "Bulan"
        AppLanguage.FR -> "Mois"
    }

    fun yearLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "السنة"
        AppLanguage.EN -> "Year"
        AppLanguage.ES -> "Año"
        AppLanguage.ID -> "Tahun"
        AppLanguage.FR -> "Année"
    }

    fun birthYearValueLabel(lang: AppLanguage, year: Int): String = when (lang) {
        AppLanguage.AR -> "سنة الميلاد: $year"
        AppLanguage.EN -> "Birth year: $year"
        AppLanguage.ES -> "Año de nacimiento: $year"
        AppLanguage.ID -> "Tahun lahir: $year"
        AppLanguage.FR -> "Année de naissance : $year"
    }

    fun birthMonthValueLabel(lang: AppLanguage, month: Int): String = when (lang) {
        AppLanguage.AR -> "الشهر: $month"
        AppLanguage.EN -> "Month: $month"
        AppLanguage.ES -> "Mes: $month"
        AppLanguage.ID -> "Bulan: $month"
        AppLanguage.FR -> "Mois : $month"
    }

    fun birthDayValueLabel(lang: AppLanguage, day: Int): String = when (lang) {
        AppLanguage.AR -> "اليوم: $day"
        AppLanguage.EN -> "Day: $day"
        AppLanguage.ES -> "Día: $day"
        AppLanguage.ID -> "Hari: $day"
        AppLanguage.FR -> "Jour : $day"
    }

    fun dialMilestoneBirth(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الولادة"
        AppLanguage.EN -> "Birth"
        AppLanguage.ES -> "Nacimiento"
        AppLanguage.ID -> "Lahir"
        AppLanguage.FR -> "Naissance"
    }

    fun dialMilestoneYears(lang: AppLanguage, years: Int): String = when (lang) {
        AppLanguage.AR -> "≈$years سنة"
        AppLanguage.EN -> "~$years yrs"
        AppLanguage.ES -> "~$years a"
        AppLanguage.ID -> "~$years thn"
        AppLanguage.FR -> "~$years ans"
    }

    fun lifeExpectancyLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "العمر المقدر للحساب :"
        AppLanguage.EN -> "Estimated life expectancy:"
        AppLanguage.ES -> "Esperanza de vida estimada:"
        AppLanguage.ID -> "Estimasi harapan hidup:"
        AppLanguage.FR -> "Espérance de vie estimée :"
    }

    fun lifeExpectancyValueLabel(lang: AppLanguage, years: Int): String = when (lang) {
        AppLanguage.AR -> "العمر المقدر: $years سنة"
        AppLanguage.EN -> "Estimated life expectancy: $years years"
        AppLanguage.ES -> "Esperanza de vida estimada: $years años"
        AppLanguage.ID -> "Estimasi harapan hidup: $years tahun"
        AppLanguage.FR -> "Espérance de vie estimée : $years ans"
    }

    fun yearsUnit(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "سنة"
        AppLanguage.EN -> "years"
        AppLanguage.ES -> "años"
        AppLanguage.ID -> "tahun"
        AppLanguage.FR -> "ans"
    }

    fun ageYearsLabel(lang: AppLanguage, years: Int): String = when (lang) {
        AppLanguage.AR -> "العمر: $years سنة"
        AppLanguage.EN -> "Age: $years yrs"
        AppLanguage.ES -> "Edad: $years a"
        AppLanguage.ID -> "Usia: $years thn"
        AppLanguage.FR -> "Âge : $years ans"
    }

    fun prophetLifeSpanQuote(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« قال النبي ﷺ: عمر أمتي بين ستين وسبعين سنة »"
        AppLanguage.EN -> "« The Prophet ﷺ said: the lifespan of my community is between sixty and seventy years. »"
        AppLanguage.ES -> "« El Profeta ﷺ dijo: la vida de mi comunidad está entre sesenta y setenta años. »"
        AppLanguage.ID -> "« Nabi ﷺ bersabda: umur umatku antara enam puluh dan tujuh puluh tahun. »"
        AppLanguage.FR -> "« Le Prophète ﷺ a dit : « La durée de vie de ma communauté se situe entre 60 et 70 ans. »"
    }

    fun languageLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "لغة التطبيق :"
        AppLanguage.EN -> "App Language:"
        AppLanguage.ES -> "Idioma de la app:"
        AppLanguage.ID -> "Bahasa Aplikasi:"
        AppLanguage.FR -> "Langue de l'application :"
    }

    fun birthSettingsContentDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "إعدادات تاريخ الميلاد"
        AppLanguage.EN -> "Birth date settings"
        AppLanguage.ES -> "Ajustes de fecha de nacimiento"
        AppLanguage.ID -> "Pengaturan tanggal lahir"
        AppLanguage.FR -> "Paramètres de naissance"
    }

    fun editContentDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تعديل"
        AppLanguage.EN -> "Edit"
        AppLanguage.ES -> "Editar"
        AppLanguage.ID -> "Edit"
        AppLanguage.FR -> "Modifier"
    }

    fun cityFieldLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "المدينة (مثال: مكة، القاهرة، الدار البيضاء)"
        AppLanguage.EN -> "City (e.g. Paris, Lyon, Casablanca)"
        AppLanguage.ES -> "Ciudad (p. ej. París, Lyon, Casablanca)"
        AppLanguage.ID -> "Kota (mis. Paris, Lyon, Casablanca)"
        AppLanguage.FR -> "Ville (ex : Paris, Lyon, Casablanca)"
    }

    fun countryFieldLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الدولة (مثال: فرنسا، المغرب، الجزائر)"
        AppLanguage.EN -> "Country (e.g. France, Morocco, Algeria)"
        AppLanguage.ES -> "País (p. ej. Francia, Marruecos, Argelia)"
        AppLanguage.ID -> "Negara (mis. Prancis, Maroko, Aljazair)"
        AppLanguage.FR -> "Pays (ex : France, Maroc, Algérie)"
    }

    fun dayModeContentDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الوضع النهاري"
        AppLanguage.EN -> "Day mode"
        AppLanguage.ES -> "Modo día"
        AppLanguage.ID -> "Mode siang"
        AppLanguage.FR -> "Mode Jour"
    }

    fun nightModeContentDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الوضع الليلي"
        AppLanguage.EN -> "Night mode"
        AppLanguage.ES -> "Modo noche"
        AppLanguage.ID -> "Mode malam"
        AppLanguage.FR -> "Mode Nuit"
    }

    // ---------------------------------------------------------------------
    // Languages modal
    // ---------------------------------------------------------------------

    fun selectLanguageTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "اختر اللغة • Language"
        AppLanguage.EN -> "Select Language • اختر اللغة"
        AppLanguage.ES -> "Seleccionar idioma • اختر اللغة"
        AppLanguage.ID -> "Pilih Bahasa • اختر اللغة"
        AppLanguage.FR -> "Choisir la Langue • اختر اللغة"
    }

    fun selectLanguageContentDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "اختيار اللغة"
        AppLanguage.EN -> "Language selection"
        AppLanguage.ES -> "Selección de idioma"
        AppLanguage.ID -> "Pilih Bahasa"
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
        AppLanguage.ES -> "Perspectiva del Tiempo Divino Universal"
        AppLanguage.ID -> "Perspektif Waktu Ilahi Semesta"
        AppLanguage.FR -> "Langue par défaut de l'application"
    }

    // ---------------------------------------------------------------------
    // Converter screen
    // ---------------------------------------------------------------------

    fun converterTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "محوّل التوقيت الإلهي"
        AppLanguage.EN -> "TEMPORAL CONVERTER"
        AppLanguage.ES -> "CONVERTIDOR TEMPORAL"
        AppLanguage.ID -> "KONVERTER WAKTU"
        AppLanguage.FR -> "CONVERTISSEUR DE TEMPS"
    }

    fun converterSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "قِس مدتك الأرضية على مقياس القضاء الإلهي"
        AppLanguage.EN -> "Measure earthly duration against the scale of the Divine Decree"
        AppLanguage.ES -> "Mide una duración terrenal en la escala del Decreto Divino"
        AppLanguage.ID -> "Ukur durasi duniawi dengan skala Ketetapan Ilahi"
        AppLanguage.FR -> "Mesure la durée terrestre à l'échelle du Décret Divin"
    }

    fun tabEarthToDivine(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الأرض → الإلهي"
        AppLanguage.EN -> "Earth → Divine"
        AppLanguage.ES -> "Tierra → Divino"
        AppLanguage.ID -> "Bumi → Ilahi"
        AppLanguage.FR -> "Terrestre → Divin"
    }

    fun tabDivineToEarth(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الإلهي → الأرض"
        AppLanguage.EN -> "Divine → Earth"
        AppLanguage.ES -> "Divino → Tierra"
        AppLanguage.ID -> "Ilahi → Bumi"
        AppLanguage.FR -> "Divin → Terrestre"
    }

    fun earthDurationTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "أدخل المدة الأرضية"
        AppLanguage.EN -> "Enter Earth Duration"
        AppLanguage.ES -> "Introducir duración terrestre"
        AppLanguage.ID -> "Masukkan Durasi Bumi"
        AppLanguage.FR -> "Entre une durée terrestre :"
    }

    fun divineDurationTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "أدخل الدقائق الإلهية"
        AppLanguage.EN -> "Enter Divine Minutes"
        AppLanguage.ES -> "Introducir minutos divinos"
        AppLanguage.ID -> "Masukkan Menit Ilahi"
        AppLanguage.FR -> "Entre des minutes divines :"
    }

    fun equivalentWithAllah(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "المعادل عند الله :"
        AppLanguage.EN -> "Equivalent with Allah:"
        AppLanguage.ES -> "Equivalente con Allah:"
        AppLanguage.ID -> "Setara bersama Allah:"
        AppLanguage.FR -> "Équivalent auprès d'Allah :"
    }

    fun equivalentWithAllahTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "⚖️ المعادل بالزمن الإلهي عند الله"
        AppLanguage.EN -> "⚖️ EQUIVALENT IN DIVINE TIME WITH ALLAH"
        AppLanguage.ES -> "⚖️ EQUIVALENTE EN TIEMPO DIVINO CON ALLAH"
        AppLanguage.ID -> "⚖️ SETARA WAKTU ILAHI BERSAMA ALLAH"
        AppLanguage.FR -> "⚖️ ÉQUIVALENT EN TEMPS DIVIN CHEZ ALLAH"
    }

    fun equivalentOnEarth(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "المعادل على الأرض :"
        AppLanguage.EN -> "Equivalent on Earth:"
        AppLanguage.ES -> "Equivalente en la Tierra:"
        AppLanguage.ID -> "Setara di Bumi:"
        AppLanguage.FR -> "Équivalent sur terre :"
    }

    fun equivalentOnEarthTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "🌍 المعادل بالزمن الأرضي للناس"
        AppLanguage.EN -> "🌍 EQUIVALENT IN EARTHLY TIME FOR HUMANS"
        AppLanguage.ES -> "🌍 EQUIVALENTE EN TIEMPO TERRESTRE PARA HUMANOS"
        AppLanguage.ID -> "🌍 SETARA WAKTU BUMI BAGI MANUSIA"
        AppLanguage.FR -> "🌍 ÉQUIVALENT SUR TERRE POUR LES HOMMES"
    }

    fun meditativeScaleNote(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "مقياس رمزي للتأمل مستوحى من سورة الحج ٤٧ — تجسيد للتدبر وليس قياسًا للآخرة."
        AppLanguage.EN -> "A symbolic scale for reflection inspired by Quran 22:47 — a meditative illustration, not a measure of the Hereafter."
        AppLanguage.ES -> "Escala simbólica de reflexión inspirada en el Corán 22:47 — una ilustración meditativa, no una medida del más allá."
        AppLanguage.ID -> "Skala simbolis renungan yang terinspirasi Quran 22:47 — ilustrasi meditatif, bukan ukuran akhirat."
        AppLanguage.FR -> "Échelle symbolique de réflexion inspirée du Coran 22:47 — une illustration méditative, non une mesure de l'au-delà."
    }

    fun strikingBenchmarksTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "📊 أمثلة حية مؤثرة"
        AppLanguage.EN -> "📊 STRIKING BENCHMARKS"
        AppLanguage.ES -> "📊 COMPARATIVAS IMPACTANTES"
        AppLanguage.ID -> "📊 PERBANDINGAN MENGESANKAN"
        AppLanguage.FR -> "📊 EXEMPLES FRAPPANTS"
    }

    fun strikingBenchmarksSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "اضغط على مثال لحظه في الحساب فوراً"
        AppLanguage.EN -> "Tap an example to inject it into the calculator instantly"
        AppLanguage.ES -> "Toca un ejemplo para inyectarlo al instante en la calculadora"
        AppLanguage.ID -> "Ketuk contoh untuk memasukkannya langsung ke kalkulator"
        AppLanguage.FR -> "Touche un exemple pour l'injecter instantanément dans le calcul"
    }

    fun yearsFieldLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "سنوات"
        AppLanguage.EN -> "Years"
        AppLanguage.ES -> "Años"
        AppLanguage.ID -> "Tahun"
        AppLanguage.FR -> "Années"
    }

    fun daysFieldLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "أيام"
        AppLanguage.EN -> "Days"
        AppLanguage.ES -> "Días"
        AppLanguage.ID -> "Hari"
        AppLanguage.FR -> "Jours"
    }

    fun minutesFieldLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "دقائق"
        AppLanguage.EN -> "Minutes"
        AppLanguage.ES -> "Minutos"
        AppLanguage.ID -> "Menit"
        AppLanguage.FR -> "Minutes"
    }

    fun divineMinutesFieldLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "دقائق إلهية"
        AppLanguage.EN -> "Divine minutes"
        AppLanguage.ES -> "Minutos divinos"
        AppLanguage.ID -> "Menit ilahi"
        AppLanguage.FR -> "Minutes divines"
    }

    // ---------------------------------------------------------------------
    // Journal screen
    // ---------------------------------------------------------------------

    fun journalTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "يوميات الدقائق الإلهية"
        AppLanguage.EN -> "DIVINE MINUTES JOURNAL"
        AppLanguage.ES -> "DIARIO DE MINUTOS DIVINOS"
        AppLanguage.ID -> "JURNAL MENIT ILAHI"
        AppLanguage.FR -> "JOURNAL DES MINUTES DIVINES"
    }

    /**
     * One earth day = 86.4 / 365.2425 = 0.236554 divine seconds.
     */
    fun todayIsLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "اليوم = 0.2366 ثانية إلهية"
        AppLanguage.EN -> "Today = 0.2366 divine second"
        AppLanguage.ES -> "Hoy = 0.2366 segundo divino"
        AppLanguage.ID -> "Hari ini = 0.2366 detik ilahi"
        AppLanguage.FR -> "Aujourd'hui = 0.2366 sec divine"
    }

    fun howFilledQuestion(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "كيف عمرت هذه اللحظة الخاطفة؟"
        AppLanguage.EN -> "How did you fill this fraction of a second?"
        AppLanguage.ES -> "¿Cómo llenaste esta fracción de segundo?"
        AppLanguage.ID -> "Apa yang kamu lakukan dengan sebagian detik ini?"
        AppLanguage.FR -> "Comment as-tu rempli cette fraction de seconde ?"
    }

    fun prayerChecklistTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الصلوات الخمس في وقتها"
        AppLanguage.EN -> "5 Prayers on Time"
        AppLanguage.ES -> "5 oraciones a tiempo"
        AppLanguage.ID -> "5 Salat Tepat Waktu"
        AppLanguage.FR -> "LES 5 PRIÈRES DU JOUR"
    }

    fun hassanatSectionTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "✨ الحسنات والتطهير"
        AppLanguage.EN -> "✨ HASSANAT & SANCTIFICATION"
        AppLanguage.ES -> "✨ HASSANAT & SANTIFICACIÓN"
        AppLanguage.ID -> "✨ HASANAT & PENSUCIAN"
        AppLanguage.FR -> "✨ HASSANAT & SANCTIFICATION"
    }

    fun quranPagesTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "📖 قراءة القرآن الكريم"
        AppLanguage.EN -> "📖 Reading the Holy Quran"
        AppLanguage.ES -> "📖 Lectura del Sagrado Corán"
        AppLanguage.ID -> "📖 Membaca Al-Quran"
        AppLanguage.FR -> "📖 Lecture du Saint Coran"
    }

    fun quranHasanatNote(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "كل حرف = 10 حسنات"
        AppLanguage.EN -> "Every letter = 10 hassanat"
        AppLanguage.ES -> "Cada letra = 10 hassanat"
        AppLanguage.ID -> "Setiap huruf = 10 hasanat"
        AppLanguage.FR -> "Chaque lettre = 10 hassanat"
    }

    fun pagesUnit(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "صفحات"
        AppLanguage.EN -> "pages"
        AppLanguage.ES -> "páginas"
        AppLanguage.ID -> "halaman"
        AppLanguage.FR -> "pages"
    }

    fun pagesReadLabel(lang: AppLanguage, pages: Int): String = when (lang) {
        AppLanguage.AR -> "$pages ص"
        AppLanguage.EN -> "$pages p."
        AppLanguage.ES -> "$pages págs."
        AppLanguage.ID -> "$pages hlm."
        AppLanguage.FR -> "$pages p."
    }

    fun sadaqaDone(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "صدقة أو عمل خير"
        AppLanguage.EN -> "Charity or Sadaqa given"
        AppLanguage.ES -> "Caridad o sadaqa dada"
        AppLanguage.ID -> "Sedekah atau sadaqa diberikan"
        AppLanguage.FR -> "Sadaqa / Aumône donnée"
    }

    fun eveningDhikr(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "أذكار الصباح والمساء"
        AppLanguage.EN -> "Morning & Evening Dhikr"
        AppLanguage.ES -> "Dhikr de la mañana y de la tarde"
        AppLanguage.ID -> "Zikir Pagi & Petang"
        AppLanguage.FR -> "Dhikr du soir & matin"
    }

    fun goodDeed(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "إحسان وخلق طيب للآخرين"
        AppLanguage.EN -> "Good deed towards others"
        AppLanguage.ES -> "Buena obra hacia los demás"
        AppLanguage.ID -> "Kebaikan kepada sesama"
        AppLanguage.FR -> "Bonne action envers autrui"
    }

    fun tasbihTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "📿 المسبحة الإلهية"
        AppLanguage.EN -> "📿 Divine Tasbih"
        AppLanguage.ES -> "📿 Tasbih Divino"
        AppLanguage.ID -> "📿 Tasbih Ilahi"
        AppLanguage.FR -> "📿 TASBIH DIVIN DU JOUR"
    }

    fun tasbihTapsToday(lang: AppLanguage, count: Int): String = when (lang) {
        AppLanguage.AR -> "تسبيحات اليوم: $count"
        AppLanguage.EN -> "Taps today: $count"
        AppLanguage.ES -> "Toques hoy: $count"
        AppLanguage.ID -> "Ketukan hari ini: $count"
        AppLanguage.FR -> "Tours aujourd'hui : $count"
    }

    fun tasbihTapLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "اضغط"
        AppLanguage.EN -> "TAP"
        AppLanguage.ES -> "TOCA"
        AppLanguage.ID -> "KETUK"
        AppLanguage.FR -> "TAP"
    }

    fun tasbihHadith(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« أَفَضَلُ الذِّكْرِ سُبْحَانَ اللَّهِ وَالْحَمْدُ لِلَّهِ »"
        AppLanguage.EN -> "« The best remembrance is: SubhanAllah and Alhamdulillah. »"
        AppLanguage.ES -> "« El mejor recuerdo es: SubhanAllah y Alhamdulillah. »"
        AppLanguage.ID -> "« Zikir terbaik adalah: SubhanAllah dan Alhamdulillah. »"
        AppLanguage.FR -> "« Le dhikr le plus pharmacology est : SubhanAllah et Alhamdulillah. »"
    }

    /**
     * 1 divine second per tasbih = 1 earth second worth of praise.
     * 5 minutes of prayer = 5 * DIVINE_SECONDS_PER_EARTH_MINUTE ≈ 0.000821 divine seconds.
     */
    fun tasbihDivineEquivalent(lang: AppLanguage, divineSeconds: Double): String = when (lang) {
        AppLanguage.AR -> "المعادل المقدَّس: ${String.format(Locale.US, "%.4f", divineSeconds)} ثانية إلهية من الثواب"
        AppLanguage.EN -> "Sanctified equivalent: ${String.format(Locale.US, "%.4f", divineSeconds)} divine seconds of praise"
        AppLanguage.ES -> "Equivalente santificado: ${String.format(Locale.US, "%.4f", divineSeconds)} segundos divinos de alabanza"
        AppLanguage.ID -> "Setara yang disucikan: ${String.format(Locale.US, "%.4f", divineSeconds)} detik ilahi pujian"
        AppLanguage.FR -> "Équivalent sanctifié : ${String.format(Locale.US, "%.4f", divineSeconds)} sec divine d'éloges"
    }

    fun resetBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "إعادة ضبط"
        AppLanguage.EN -> "Reset"
        AppLanguage.ES -> "Reiniciar"
        AppLanguage.ID -> "Reset"
        AppLanguage.FR -> "Réinitialiser"
    }

    fun dailyScoreLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تقييم اليوم الإلهي :"
        AppLanguage.EN -> "Divine Daily Score:"
        AppLanguage.ES -> "Puntuación diaria divina:"
        AppLanguage.ID -> "Skor Ilahi Harian:"
        AppLanguage.FR -> "SCORE DIVIN DU JOUR"
    }

    fun doNotWasteQuote(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« لا تُضع ثانية إلهية واحدة في معصية الخالق »"
        AppLanguage.EN -> "« Do not waste a single divine second in sin »"
        AppLanguage.ES -> "« No desperdicies un solo segundo divino en el pecado »"
        AppLanguage.ID -> "« Jangan sia-siakan satu detik ilahi pun dalam maksiat »"
        AppLanguage.FR -> "« Ne gaspille pas une seconde divine dans le péché »"
    }

    fun notesLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "خواطر ومحاسبة النفس"
        AppLanguage.EN -> "Spiritual Notes & Reflections"
        AppLanguage.ES -> "Notas & Reflexiones Espirituales"
        AppLanguage.ID -> "Catatan & Renungan Spiritual"
        AppLanguage.FR -> "Notes & Bilan personnel"
    }

    fun notesPlaceholder(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "ماذا ادخرت اليوم في صحيفتك؟"
        AppLanguage.EN -> "What did you prepare today for your hereafter?"
        AppLanguage.ES -> "¿Qué preparaste hoy para tu otra vida?"
        AppLanguage.ID -> "Apa bekal hari ini untuk akhiratmu?"
        AppLanguage.FR -> "Qu'as-tu semé aujourd'hui pour l'éternité ?"
    }

    // ---------------------------------------------------------------------
    // Visualizer screen
    // ---------------------------------------------------------------------

    fun visualizerTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "المشهد المرئي للحياة (دقيقتان)"
        AppLanguage.EN -> "IMMERSIVE 2-MINUTE LIFE"
        AppLanguage.ES -> "VIDEA INMERSIVA DE 2 MINUTOS"
        AppLanguage.ID -> "HIDUP IMERSIF 2 MENIT"
        AppLanguage.FR -> "VISUALISE TA VIE EN 2 MINUTES"
    }

    fun visualizerSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "حياتك كلها تمر في 120 ثانية إلهية"
        AppLanguage.EN -> "Watch your entire life pass in 120 divine seconds"
        AppLanguage.ES -> "Mira pasar toda tu vida en 120 segundos divinos"
        AppLanguage.ID -> "Saksikan seluruh hidupmu berlalu dalam 120 detik ilahi"
        AppLanguage.FR -> "Toute ta vie résumée en 120 secondes divines"
    }

    fun startSimulationBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "بدء الرحلة"
        AppLanguage.EN -> "Start Simulation"
        AppLanguage.ES -> "Iniciar simulación"
        AppLanguage.ID -> "Mulai Simulasi"
        AppLanguage.FR -> "Lancer l'animation"
    }

    fun pauseSimulationBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "إيقاف مؤقت"
        AppLanguage.EN -> "Pause"
        AppLanguage.ES -> "Pausa"
        AppLanguage.ID -> "Jeda"
        AppLanguage.FR -> "Pause"
    }

    fun resetSimulationContentDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "إعادة الضبط"
        AppLanguage.EN -> "Reset"
        AppLanguage.ES -> "Reiniciar"
        AppLanguage.ID -> "Reset"
        AppLanguage.FR -> "Réinitialiser"
    }

    fun lifeJourneyComplete(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "لقد شاهدت حياتك كاملة في زمن إلهي حقيقي (دقيقتان)."
        AppLanguage.EN -> "You have just watched your entire life in divine real time (2 min)."
        AppLanguage.ES -> "Acabas de ver toda tu vida en tiempo real divino (2 min)."
        AppLanguage.ID -> "Kau baru menyaksikan seluruh hidupmu dalam waktu ilahi nyata (2 mnt)."
        AppLanguage.FR -> "Tu viens de voir ta vie entière en temps réel divin (2 min)."
    }

    fun earthAgeApproxLabel(lang: AppLanguage, years: Int): String = when (lang) {
        AppLanguage.AR -> "العمر الأرضي: ~$years سنة"
        AppLanguage.EN -> "Earthly age: ~$years yrs"
        AppLanguage.ES -> "Edad terrenal: ~$years a"
        AppLanguage.ID -> "Usia duniawi: ~$years thn"
        AppLanguage.FR -> "Âge terrestre : ~$years ans"
    }

    fun quranVerseLabel(lang: AppLanguage, verse: String): String = when (lang) {
        AppLanguage.AR -> "📖 $verse"
        AppLanguage.EN -> "📖 $verse"
        AppLanguage.ES -> "📖 $verse"
        AppLanguage.ID -> "📖 $verse"
        AppLanguage.FR -> "📖 $verse"
    }

    fun journeyStepsTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "🕰 مراحل الرحلة الأرضية"
        AppLanguage.EN -> "🕰 STEPS OF THE EARTHLY JOURNEY"
        AppLanguage.ES -> "🕰 ETAPAS DEL VIAJE TERRESTRE"
        AppLanguage.ID -> "🕰 TAHAPAN PERJALANAN DUNIA"
        AppLanguage.FR -> "🕰 ÉTAPES DU VOYAGE TERRESTRE"
    }

    fun twoHourLiveModeTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "⏳ وضع الساعتين المباشر"
        AppLanguage.EN -> "⏳ LIVE 2-HOUR MODE"
        AppLanguage.ES -> "⏳ MODO 2 HORAS EN VIVO"
        AppLanguage.ID -> "⏳ MODE 2 JAM LANGSUNG"
        AppLanguage.FR -> "⏳ LE MODE 2 HEURES EN DIRECT"
    }

    fun twoHourLiveModeSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "فعّل رملاً زمنياً من ساعتين حقيقيتين يرمز لحياتك كلها على الأرض."
        AppLanguage.EN -> "Activate a real 2-hour hourglass symbolising your whole life on Earth."
        AppLanguage.ES -> "Activa un reloj de arena real de 2 horas que simboliza toda tu vida en la Tierra."
        AppLanguage.ID -> "Aktifkan jam pasir 2 jam yang melambangkan seluruh hidupmu di Bumi."
        AppLanguage.FR -> "Active un sablier de 2h réelles symbolisant toute ta vie sur Terre."
    }

    fun twoHourCrossingLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "أنت تعبر رمزياً وجودك..."
        AppLanguage.EN -> "You are symbolically crossing your existence..."
        AppLanguage.ES -> "Estás cruzando simbólicamente tu existencia..."
        AppLanguage.ID -> "Kau sedang melintasi seluruh keberadaanmu secara simbolik..."
        AppLanguage.FR -> "Tu traverses symboliquement ton existence..."
    }

    // ---------------------------------------------------------------------
    // Challenges screen
    // ---------------------------------------------------------------------

    fun challengesTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "سباق الساعتين"
        AppLanguage.EN -> "THE 2-HOUR RACE"
        AppLanguage.ES -> "LA CARRERA DE 2 HORAS"
        AppLanguage.ID -> "LARI 2 JAM"
        AppLanguage.FR -> "LA COURSE DES 2 HEURES"
    }

    fun challengesSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "سارع إلى الخيرات والدرجات العلى قبل انقضاء ساعتك"
        AppLanguage.EN -> "Strive in good deeds before your 2 divine hours end"
        AppLanguage.ES -> "Multiplica las buenas obras antes de que terminen tus 2 horas divinas"
        AppLanguage.ID -> "Perbanyak kebaikan sebelum 2 jam ilahimu berakhir"
        AppLanguage.FR -> "Remplis tes 2 heures divines d'œuvres éternelles"
    }

    fun levelLabel(lang: AppLanguage, level: Int): String = when (lang) {
        AppLanguage.AR -> "المستوى $level"
        AppLanguage.EN -> "Level $level"
        AppLanguage.ES -> "Nivel $level"
        AppLanguage.ID -> "Level $level"
        AppLanguage.FR -> "Niveau $level"
    }

    fun levelNameLabel(lang: AppLanguage, level: Int, levelName: String): String = when (lang) {
        AppLanguage.AR -> "المستوى $level • $levelName"
        AppLanguage.EN -> "Level $level • $levelName"
        AppLanguage.ES -> "Nivel $level • $levelName"
        AppLanguage.ID -> "Level $level • $levelName"
        AppLanguage.FR -> "Niveau $level • $levelName"
    }

    fun hassanatUnlockedTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الحسنات المفتوحة"
        AppLanguage.EN -> "HASSANAT UNLOCKED"
        AppLanguage.ES -> "HASSANAT DESBLOQUEADAS"
        AppLanguage.ID -> "HASANAT DIDAPAT"
        AppLanguage.FR -> "HASSANAT DÉBLOQUÉES"
    }

    fun challengesCompletedLabel(lang: AppLanguage, completed: Int, total: Int): String = when (lang) {
        AppLanguage.AR -> "$completed من $total تحديات أُنجزت اليوم"
        AppLanguage.EN -> "$completed of $total challenges completed today"
        AppLanguage.ES -> "$completed de $total desafíos completados hoy"
        AppLanguage.ID -> "$completed dari $total tantangan selesai hari ini"
        AppLanguage.FR -> "$completed / $total Défis Accomplis Aujourd'hui"
    }

    fun levelsAndBadgesTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "🕌 المستويات والأوسمة الروحية"
        AppLanguage.EN -> "🕌 LEVELS & SPIRITUAL BADGES"
        AppLanguage.ES -> "🕌 NIVELES & INSIGNIAS ESPIRITUALES"
        AppLanguage.ID -> "🕌 LEVEL & LENCANA SPIRITUAL"
        AppLanguage.FR -> "🕌 NIVEAUX & BADGES SPIRITUELS"
    }

    fun badgeLabel(lang: AppLanguage, badgeName: String): String = when (lang) {
        AppLanguage.AR -> "وسام: $badgeName"
        AppLanguage.EN -> "Badge: $badgeName"
        AppLanguage.ES -> "Insignia: $badgeName"
        AppLanguage.ID -> "Lencana: $badgeName"
        AppLanguage.FR -> "Badge : $badgeName"
    }

    // ---------------------------------------------------------------------
    // Prayer motivator card
    // ---------------------------------------------------------------------

    fun prayerMotivatorTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "⚡ مُحفّز الصلاة في الميزان الإلهي"
        AppLanguage.EN -> "⚡ DIVINE PRAYER MOTIVATOR"
        AppLanguage.ES -> "⚡ MOTIVADOR DIVINO DE ORACIÓN"
        AppLanguage.ID -> "⚡ PEMOTIVASI SALAT ILAHI"
        AppLanguage.FR -> "⚡ LE GRAND MOTIVEUR DE PRIÈRE"
    }

    fun prayerMotivatorSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "لماذا لا تملك عذراً لتأخير الصلاة بعد اليوم؟"
        AppLanguage.EN -> "Why you will never have an excuse to delay prayer again"
        AppLanguage.ES -> "Por qué nunca tendrás más excusas para retrasar la oración"
        AppLanguage.ID -> "Mengapa tak ada lagi alasan menunda salat"
        AppLanguage.FR -> "Pourquoi tu n'auras plus jamais d'excuse pour retarder ta prière"
    }

    fun shockFiguresTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "⚡ صدمة الأرقام"
        AppLanguage.EN -> "⚡ THE SHOCK OF THE FIGURES"
        AppLanguage.ES -> "⚡ EL IMPACTO DE LAS CIFRAS"
        AppLanguage.ID -> "⚡ KEJUTAN ANGKA-ANGKA"
        AppLanguage.FR -> "⚡ LE CHOC DES CHIFFRES"
    }

    fun oneDayEqualsThousandYears(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "يوم واحد = 1000 سنة"
        AppLanguage.EN -> "1 day = 1000 years"
        AppLanguage.ES -> "1 día = 1000 años"
        AppLanguage.ID -> "1 hari = 1000 tahun"
        AppLanguage.FR -> "1 jour = 1000 ans"
    }

    /**
     * 5 earth minutes = 5 * DIVINE_SECONDS_PER_EARTH_MINUTE ≈ 0.000821 divine seconds.
     */
    fun prayerFiveMinutesShock(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "5 دقائق صلاة = 0.0008 ثانية إلهية فقط !"
        AppLanguage.EN -> "5 min of prayer = Only 0.0008 divine seconds!"
        AppLanguage.ES -> "¡5 min de oración = Solo 0.0008 segundos divinos!"
        AppLanguage.ID -> "5 mnt salat = Hanya 0.0008 detik ilahi!"
        AppLanguage.FR -> "5 minutes de prière = Seulement 0.0008 sec divine !"
    }

    /**
     * 5 daily prayers over 80 years ≈ 2.00 divine minutes out of 115.2 (≈ 1.7 % of life).
     */
    fun prayerLifetimeShock(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "80 سنة من الصلوات الخمس = نحو دقيقتين فقط عند الله !"
        AppLanguage.EN -> "80 years of 5 daily prayers = Only ~2 divine minutes with Allah!"
        AppLanguage.ES -> "¡80 años de 5 oraciones diarias = Solo ~2 minutos divinos con Allah!"
        AppLanguage.ID -> "80 tahun salat 5 waktu = Hanya ~2 menit ilahi bersama Allah!"
        AppLanguage.FR -> "80 ans de prières = Moins de 2 minutes chez Allah !"
    }

    fun prayerShockCallout(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« أنعم الله عليك بـ 120 دقيقة إلهية في عمرك، فهل تبخل عليه بدقيقتين لشراء الجنة الأبدية؟ »"
        AppLanguage.EN -> "« Allah granted you 120 divine minutes. Will you withhold 2 minutes to secure eternal Paradise? »"
        AppLanguage.ES -> "« Allah te concedió 120 minutos divinos. ¿Vas a retener 2 minutos para asegurar el Paraíso eterno? »"
        AppLanguage.ID -> "« Allah memberimu 120 menit ilahi. Maukah kamu menahan 2 menit untuk mengamankan surga abadi? »"
        AppLanguage.FR -> "« Allah t'a accordé 120 minutes divines. Lui refuseras-tu 2 minutes pour acheter le Paradis éternel ? »"
    }

    fun todayPrayerInvestment(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "استثمارك في الصلوات اليوم"
        AppLanguage.EN -> "Today's Prayer Investment"
        AppLanguage.ES -> "Inversión de oración de hoy"
        AppLanguage.ID -> "Investasi Salat Hari Ini"
        AppLanguage.FR -> "Ton investissement prière aujourd'hui"
    }

    fun prayersDoneRatio(lang: AppLanguage, done: Int): String = when (lang) {
        AppLanguage.AR -> "$done من 5 صلوات أُقيمت"
        AppLanguage.EN -> "$done of 5 prayers completed"
        AppLanguage.ES -> "$done de 5 oraciones completadas"
        AppLanguage.ID -> "$done dari 5 salat selesai"
        AppLanguage.FR -> "$done / 5 prières accomplies"
    }

    fun divineSecondsInvested(lang: AppLanguage, secondsStr: String): String = when (lang) {
        AppLanguage.AR -> "$secondsStr ثانية إلهية مدخرة للآخرة"
        AppLanguage.EN -> "$secondsStr divine sec invested for eternity"
        AppLanguage.ES -> "$secondsStr seg divinos invertidos para la eternidad"
        AppLanguage.ID -> "$secondsStr dtk ilahi diinvestasi untuk keabadian"
        AppLanguage.FR -> "$secondsStr sec divines investies pour l'éternité"
    }

    fun screenVsPrayerTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "مقارنة الصدمة : الهاتف مقابل الصلاة"
        AppLanguage.EN -> "Reality Shock: Screen Time vs Prayer"
        AppLanguage.ES -> "Choque de realidad: Pantalla vs Oración"
        AppLanguage.ID -> "Kejutan Realitas: Layar vs Salat"
        AppLanguage.FR -> "Le Choc : Téléphone vs Prière"
    }

    fun screenLifetimeStat(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "📱 3 ساعات هاتف يومياً = 15 دقيقة إلهية في حياتك (13% من عمرك)"
        AppLanguage.EN -> "📱 3h phone daily = 15 divine minutes in lifetime (13% of life)"
        AppLanguage.ES -> "📱 3h de teléfono al día = 15 minutos divinos de vida (13% de la vida)"
        AppLanguage.ID -> "📱 3j ponsel per hari = 15 menit ilahi seumur hidup (13% hidup)"
        AppLanguage.FR -> "📱 3h d'écran / jour = 15 minutes divines sur ta vie (13%)"
    }

    fun prayerLifetimeStat(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "🕌 الصلوات الخمس يومياً = دقيقتان إلهيتان فقط (1.7% من عمرك)"
        AppLanguage.EN -> "🕌 5 prayers daily = Only 2 divine minutes (1.7% of life)"
        AppLanguage.ES -> "🕌 5 oraciones diarias = Solo 2 minutos divinos (1.7% de la vida)"
        AppLanguage.ID -> "🕌 Salat 5 waktu = Hanya 2 menit ilahi (1.7% hidup)"
        AppLanguage.FR -> "🕌 5 prières / jour = SEULEMENT 2 minutes divines (1,7%)"
    }

    fun priorityQuestion(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "كيف تمنح الشاشات 15 دقيقة وتستثقل دقيقتين لربك؟"
        AppLanguage.EN -> "How can we give 15 minutes to screens and withhold 2 minutes from Allah?"
        AppLanguage.ES -> "¿Cómo podemos dar 15 minutos a las pantallas y negar 2 minutos a Allah?"
        AppLanguage.ID -> "Bagaimana mungkin memberi 15 menit untuk layar namun menahan 2 menit dari Allah?"
        AppLanguage.FR -> "Comment donner 15 minutes aux écrans et hésiter à donner 2 minutes à Allah ?"
    }

    fun prayerJudgementHadith(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« أول ما يُحاسب عليه عبدُ الله الصلاة » (حديث)"
        AppLanguage.EN -> "« The first thing a servant will be brought to account for is the prayer. » (Hadith)"
        AppLanguage.ES -> "« Lo primero por lo que el siervo rendirá cuentas es la oración. » (Hadiz)"
        AppLanguage.ID -> "« Hal pertama yang dimintai pertanggungjawaban seorang hamba adalah salat. » (Hadis)"
        AppLanguage.FR -> "« La première chose sur laquelle l'homme sera jugé est sa prière » (Hadith)"
    }

    // ---------------------------------------------------------------------
    // Clock dial + breakdown chart
    // ---------------------------------------------------------------------

    fun dialTotalMinutesLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "120 د"
        AppLanguage.EN -> "120 MIN"
        AppLanguage.ES -> "120 MIN"
        AppLanguage.ID -> "120 MNT"
        AppLanguage.FR -> "120 MIN"
    }

    fun dialHourLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "ساعة إلهية"
        AppLanguage.EN -> "DIVINE HOUR"
        AppLanguage.ES -> "HORA DIVINA"
        AppLanguage.ID -> "JAM ILAHI"
        AppLanguage.FR -> "HEURE DIVINE"
    }

    fun thousandYearsEquals24Hours(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "1000 سنة = 24 س"
        AppLanguage.EN -> "1000 YEARS = 24H"
        AppLanguage.ES -> "1000 AÑOS = 24H"
        AppLanguage.ID -> "1000 TAHUN = 24J"
        AppLanguage.FR -> "1000 ANS = 24H"
    }

    fun lifeBalanceTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "1. الميزان الزمني لحياتك (ساعتان إلهيتان)"
        AppLanguage.EN -> "1. TIME BALANCE OF YOUR LIFE (2 DIVINE HOURS)"
        AppLanguage.ES -> "1. BALANCE DE TIEMPO DE TU VIDA (2 HORAS DIVINAS)"
        AppLanguage.ID -> "1. NERACA WAKTU HIDUPMU (2 JAM ILAHI)"
        AppLanguage.FR -> "1. BILAN TEMPOREL DE TA VIE (2 HEURES DIVINES)"
    }

    fun prayersOnlyTwoPercent(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الصلوات الخمس لا تمثل سوى 1.7% من وجودك الإلهي. كل ما تبقى هو هبة لك!"
        AppLanguage.EN -> "The 5 prayers are only 1.7% of your divine existence. Everything else is a gift!"
        AppLanguage.ES -> "Las 5 oraciones son solo el 1.7% de tu existencia divina. ¡Todo lo demás es un regalo!"
        AppLanguage.ID -> "5 salat hanyalah 1.7% dari keberadaan ilahimu. Sisanya adalah anugerah!"
        AppLanguage.FR -> "Les 5 prières ne représentent que 1.7% de ton existence divine. Tout le reste t'est offert !"
    }

    fun livedTimeLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الوقت المضي"
        AppLanguage.EN -> "TIME LIVED"
        AppLanguage.ES -> "TIEMPO VIVIDO"
        AppLanguage.ID -> "WAKTU TERLALUI"
        AppLanguage.FR -> "TEMPS VÉCU"
    }

    // ---------------------------------------------------------------------
    // Settings & verses screen
    // ---------------------------------------------------------------------

    fun remindersTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "🕌 التذكيرات وحكم الوقت"
        AppLanguage.EN -> "🕌 REMINDERS & WISDOM OF TIME"
        AppLanguage.ES -> "🕌 RECORDATORIOS & SABIDURÍA DEL TIEMPO"
        AppLanguage.ID -> "🕌 PENGINGAT & HIKMAH WAKTU"
        AppLanguage.FR -> "🕌 RAPPELS & SAGESSES DU TEMPS"
    }

    fun remindersSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تنبيهات واعية كي لا تهدر ثانية أبداً"
        AppLanguage.EN -> "Aware alerts so you never waste a second again"
        AppLanguage.ES -> "Alertas conscientes para no desperdiciar jamás un segundo"
        AppLanguage.ID -> "Pemberitahuan sadar agar tak lagi menyia-nyiakan sedetik pun"
        AppLanguage.FR -> "Des alertes conscientes pour ne plus jamais gaspiller une seconde"
    }

    fun spiritualNotificationsTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "🔔 الإشعارات الروحية الذكية"
        AppLanguage.EN -> "🔔 SMART SPIRITUAL NOTIFICATIONS"
        AppLanguage.ES -> "🔔 NOTIFICACIONES ESPIRITUALES INTELIGENTES"
        AppLanguage.ID -> "🔔 NOTIFIKASI SPIRITUAL PINTAR"
        AppLanguage.FR -> "🔔 NOTIFICATIONS SPIRITUELLES INTELLIGENTES"
    }

    fun dailyReminderDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تلقَّ كل يوم في الساعة 13:00 تذكيراً لطيفاً وعميقاً محسوباً حسب تقدمك في ساعتيك الإلهيتين."
        AppLanguage.EN -> "Receive every day at 1:00 PM a gentle, deep reminder computed from your progress in your 2 divine hours."
        AppLanguage.ES -> "Recibe cada día a las 13:00 un recordatorio suave y profundo calculado según tu avance en tus 2 horas divinas."
        AppLanguage.ID -> "Terima setiap hari pukul 13.00 pengingat lembut dan mendalam yang dihitung dari kemajuanmu dalam 2 jam ilahimu."
        AppLanguage.FR -> "Reçois chaque jour à 13h00 un rappel doux et profond calculé selon ton avancement dans tes 2 heures divines."
    }

    fun celestialThemeTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "🌙 المظهر السماوي"
        AppLanguage.EN -> "🌙 CELESTIAL THEME"
        AppLanguage.ES -> "🌙 TEMA CELESTIAL"
        AppLanguage.ID -> "🌙 TEMA CAKRAWALA"
        AppLanguage.FR -> "🌙 THÈME CÉLESTE"
    }

    fun fundamentalVersesTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "📖 آيات الوقت الأساسية"
        AppLanguage.EN -> "📖 FUNDAMENTAL VERSES OF TIME"
        AppLanguage.ES -> "📖 VERSÍCULOS FUNDAMENTALES DEL TIEMPO"
        AppLanguage.ID -> "📖 AYAT-AYAT FUNDAMENTAL WAKTU"
        AppLanguage.FR -> "📖 VERSETS FONDAMENTAUX DU TEMPS"
    }

    fun fundamentalVersesSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "التصور القرآني لنسبية الوقت"
        AppLanguage.EN -> "The Quranic conception of the relativity of time"
        AppLanguage.ES -> "La concepción coránica de la relatividad del tiempo"
        AppLanguage.ID -> "Konsep Quran tentang relativitas waktu"
        AppLanguage.FR -> "La conception coranique de la relativité du temps"
    }

    fun lifeIsTwoHoursQuote(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« حياتك كلها = نحو ساعتين عند الله »"
        AppLanguage.EN -> "« Your entire life = ~2 hours with Allah »"
        AppLanguage.ES -> "« Toda tu vida = ~2 horas con Allah »"
        AppLanguage.ID -> "« Seluruh hidupmu = ~2 jam bersama Allah »"
        AppLanguage.FR -> "« Ta vie entière = ~2 heures chez Allah »"
    }

    fun hajj47Explanation(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "استناداً إلى الآية 47 من سورة الحج: 1000 سنة أرضية تعادل 24 ساعة إلهية. وعمر بشري من 70 إلى 80 سنة لا يمثل سوى ما بين 1س41د و1س55د عند الله."
        AppLanguage.EN -> "Based on verse 47 of Surah Al-Hajj: 1000 earth years equal 24 divine hours. A human lifespan of 70 to 80 years represents only between 1h41m and 1h55m with Allah."
        AppLanguage.ES -> "Según el versículo 47 de la sura Al-Hayy: 1000 años terrestres equivalen a 24 horas divinas. Una vida humana de 70 a 80 años representa solo entre 1h41m y 1h55m con Allah."
        AppLanguage.ID -> "Berdasarkan ayat 47 surah Al-Hajj: 1000 tahun bumi sama dengan 24 jam ilahi. Umur manusia 70-80 tahun hanya setara 1j41m hingga 1j55m bersama Allah."
        AppLanguage.FR -> "Basé sur le verset 47 de la Sourate Al-Hajj : 1000 ans terrestres équivalent à 24 heures divines. Une durée de vie humaine de 70 à 80 ans ne représente qu'entre 1h41 et 1h55 auprès d'Allah."
    }

    // ---------------------------------------------------------------------
    // Theme selector
    // ---------------------------------------------------------------------

    fun themeDayLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "نور الفجر (نهاري)"
        AppLanguage.EN -> "Celestial Dawn (Day)"
        AppLanguage.ES -> "Amanecer Celestial (Día)"
        AppLanguage.ID -> "Fajar Cakrawala (Siang)"
        AppLanguage.FR -> "Noor Céleste (Jour)"
    }

    fun themeNightLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الكون الليلي (ليلي)"
        AppLanguage.EN -> "Cosmic Night (Dark)"
        AppLanguage.ES -> "Noche Cósmica (Oscuro)"
        AppLanguage.ID -> "Malam Kosmik (Gelap)"
        AppLanguage.FR -> "Cosmos Nocturne (Nuit)"
    }

    // ---------------------------------------------------------------------
    // Notifications
    // ---------------------------------------------------------------------

    fun dailyReminderNotifTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "ذَوْق • تذكير الوقت الإلهي"
        AppLanguage.EN -> "DHAwQ • Divine Lifespan Reminder"
        AppLanguage.ES -> "DHAwQ • Recordatorio de Vida Divina"
        AppLanguage.ID -> "DHAwQ • Pengingat Umur Ilahi"
        AppLanguage.FR -> "DHAwQ • Ton Rappel Céleste"
    }

    fun dailyReminderNotifBody(lang: AppLanguage, divineMinutes: Double): String = when (lang) {
        AppLanguage.AR -> "لقد قطعت ${String.format(Locale.US, "%.1f", divineMinutes)} د من أصل 120 دقيقة إلهية. امنح 0.0008 ث لصلاتك الآن 🕌"
        AppLanguage.EN -> "You have travelled ${String.format(Locale.US, "%.1f", divineMinutes)}m of your 120 divine minutes. Grant 0.0008s for your prayer now 🕌"
        AppLanguage.ES -> "Has recorrido ${String.format(Locale.US, "%.1f", divineMinutes)}m de tus 120 minutos divinos. Concede 0.0008s para tu oración ahora 🕌"
        AppLanguage.ID -> "Kau telah menempuh ${String.format(Locale.US, "%.1f", divineMinutes)}m dari 120 menit ilahimu. Sisihkan 0.0008dtk untuk salatmu sekarang 🕌"
        AppLanguage.FR -> "Tu as parcouru ${String.format(Locale.US, "%.1f", divineMinutes)}m de tes 120 minutes divines. Accorde 0.0008s pour ta prière maintenant 🕌"
    }

    fun notificationChannelName(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تذكيرات الوقت الإلهي"
        AppLanguage.EN -> "Divine Time Reminders"
        AppLanguage.ES -> "Recordatorios de Tiempo Divino"
        AppLanguage.ID -> "Pengingat Waktu Ilahi"
        AppLanguage.FR -> "Rappels du Temps Divin"
    }

    fun notificationChannelDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تذكيرات يومية مبنية على ساعتيك الإلهيتين والصلاة"
        AppLanguage.EN -> "Daily reminders based on your 2 divine hours and prayer"
        AppLanguage.ES -> "Recordatorios diarios basados en tus 2 horas divinas y la oración"
        AppLanguage.ID -> "Pengingat harian berdasarkan 2 jam ilahimu dan salat"
        AppLanguage.FR -> "Rappels quotidiens basés sur tes 2 heures divines et la prière"
    }

    fun testReminderBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "إرسال إشعار تذكير فوري"
        AppLanguage.EN -> "Send instant reminder"
        AppLanguage.ES -> "Enviar recordatorio instantáneo"
        AppLanguage.ID -> "Kirim pengingat instan"
        AppLanguage.FR -> "Tester le rappel céleste"
    }

    fun reminderScheduledConfirm(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "✓ تم جدولة التذكير اليومي"
        AppLanguage.EN -> "✓ Daily reminder scheduled"
        AppLanguage.ES -> "✓ Recordatorio diario programado"
        AppLanguage.ID -> "✓ Pengingat harian dijadwalkan"
        AppLanguage.FR -> "✓ Rappel quotidien programmé"
    }

    fun notificationPermissionRequired(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "يجب منح إذن الإشعارات لعرض التذكيرات."
        AppLanguage.EN -> "Notification permission is required to show reminders."
        AppLanguage.ES -> "Se requiere permiso de notificaciones para mostrar recordatorios."
        AppLanguage.ID -> "Izin notifikasi diperlukan untuk menampilkan pengingat."
        AppLanguage.FR -> "L'autorisation des notifications est requise pour afficher les rappels."
    }

    // ---------------------------------------------------------------------
    // Data-layer content: impact phrases
    // ---------------------------------------------------------------------

    fun impactPhrase(lang: AppLanguage, percentageLived: Float): String = when {
        percentageLived < 25f -> when (lang) {
            AppLanguage.AR -> "أنت في نصف ساعتك الإلهية الأولى. ابنِ أساسك للأبدية!"
            AppLanguage.EN -> "You are in your first divine half-hour. Build the foundations of eternity!"
            AppLanguage.ES -> "Estás en tu primera media hora divina. ¡Construye los cimientos de la eternidad!"
            AppLanguage.ID -> "Kau berada di setengah jam ilahi pertamamu. Bangunlah fondasi keabadian!"
            AppLanguage.FR -> "Tu es dans ta première demi-heure divine. Construis tes fondations pour l'éternité !"
        }
        percentageLived < 50f -> when (lang) {
            AppLanguage.AR -> "اقتربت من نصف وقتك الإلهي... ماذا prepares لقاء الله؟"
            AppLanguage.EN -> "Almost half of your divine time is gone... What have you prepared for meeting Allah?"
            AppLanguage.ES -> "Casi la mitad de tu tiempo divino ha pasado... ¿Qué has preparado para el encuentro con Allah?"
            AppLanguage.ID -> "Hampir separuh waktu ilahimu telah berlalu... Apa persiapmu untuk menemui Allah?"
            AppLanguage.FR -> "Presque la moitié de ton temps divin... Qu'as-tu préparé pour la rencontre d'Allah ?"
        }
        percentageLived < 75f -> when (lang) {
            AppLanguage.AR -> "الوقت يمضي بسرعة مذهلة. كل ثانية إلهية لا تُقدَّر بثمن!"
            AppLanguage.EN -> "Time is passing at a dizzying speed. Every divine second is priceless!"
            AppLanguage.ES -> "El tiempo pasa a una velocidad vertiginosa. ¡Cada segundo divino es invaluable!"
            AppLanguage.ID -> "Waktu berlalu dengan sangat cepat. Setiap detik ilahi tak ternilai!"
            AppLanguage.FR -> "Le temps file à une vitesse vertigineuse. Chaque seconde divine est inestimable !"
        }
        percentageLived < 90f -> when (lang) {
            AppLanguage.AR -> "الدقائق الأخيرة من ساعتك... أكثِر من التوبة والحسنات! 🤲"
            AppLanguage.EN -> "The last minutes of your hour... Multiply the repentance and the hassanat! 🤲"
            AppLanguage.ES -> "Los últimos minutos de tu hora... ¡Multiplica el arrepentimiento y las hassanat! 🤲"
            AppLanguage.ID -> "Menit-menit terakhir jammu... Perbanyak tobat dan hasanat! 🤲"
            AppLanguage.FR -> "Les dernières minutes de ton heure... Multiplie le repentir et les hassanat ! 🤲"
        }
        else -> when (lang) {
            AppLanguage.AR -> "ظل الساعة يمتد. لم يستمر وقتك هنا إلا نَفَساً واحداً. كن مستعداً."
            AppLanguage.EN -> "The shadow of the hourglass stretches. Your time here lasted only a breath. Be ready."
            AppLanguage.ES -> "La sombra del reloj de arena se alarga. Tu tiempo aquí duró solo un aliento. Prepárate."
            AppLanguage.ID -> "Bayangan jam pasir merentang. Waktumu di sini hanyalah sekejap. Bersiaplah."
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
            AppLanguage.ES -> "La Primera Media Hora"
            AppLanguage.ID -> "Setengah Jam Pertama"
            AppLanguage.FR -> "La Première Demi-Heure"
        }
        "youth" -> when (lang) {
            AppLanguage.AR -> "الساعة الأولى • قمة الشباب"
            AppLanguage.EN -> "The First Hour • Full Youth"
            AppLanguage.ES -> "La Primera Hora • Plena Juventud"
            AppLanguage.ID -> "Jam Pertama • Puncak Muda"
            AppLanguage.FR -> "La Première Heure • Pleine Jeunesse"
        }
        "maturity" -> when (lang) {
            AppLanguage.AR -> "الساعة الثانية • النضج"
            AppLanguage.EN -> "The Second Hour • Maturity"
            AppLanguage.ES -> "La Segunda Hora • Madurez"
            AppLanguage.ID -> "Jam Kedua • Dewasa"
            AppLanguage.FR -> "La Seconde Heure • La Maturité"
        }
        "wisdom" -> when (lang) {
            AppLanguage.AR -> "الساعة الثانية • الحكمة"
            AppLanguage.EN -> "The Second Hour • Wisdom"
            AppLanguage.ES -> "La Segunda Hora • Sabiduría"
            AppLanguage.ID -> "Jam Kedua • Hikmah"
            AppLanguage.FR -> "La Seconde Heure • La Sagesse"
        }
        else -> when (lang) {
            AppLanguage.AR -> "نهاية الساعتين"
            AppLanguage.EN -> "The End of the Two Hours"
            AppLanguage.ES -> "El Fin de las Dos Horas"
            AppLanguage.ID -> "Akhir Dua Jam"
            AppLanguage.FR -> "Le Terme des Deux Heures"
        }
    }

    fun stageSubtitle(lang: AppLanguage, stageId: String): String = when (stageId) {
        "first_half_hour" -> when (lang) {
            AppLanguage.AR -> "البراءة والتعلّم (0 إلى 15 سنة)"
            AppLanguage.EN -> "Innocence & Learning (0 to 15 years)"
            AppLanguage.ES -> "Inocencia & Aprendizaje (0 a 15 años)"
            AppLanguage.ID -> "Kepolosan & Belajar (0 s.d. 15 tahun)"
            AppLanguage.FR -> "L'Innocence et l'Apprentissage (0 à 15 ans)"
        }
        "youth" -> when (lang) {
            AppLanguage.AR -> "البناء والعمل والإيمان (15 إلى ~42 سنة)"
            AppLanguage.EN -> "Building, Action & Faith (15 to ~42 years)"
            AppLanguage.ES -> "Construcción, Acción & Fe (15 a ~42 años)"
            AppLanguage.ID -> "Membangun, Berbuat & Beriman (15 s.d. ~42 tahun)"
            AppLanguage.FR -> "La Construction, l'Action et la Foi (15 à ~42 ans)"
        }
        "maturity" -> when (lang) {
            AppLanguage.AR -> "المراجعة والشكر (~42 إلى 60 سنة)"
            AppLanguage.EN -> "The Mid-Life Review & Gratitude (~42 to 60 years)"
            AppLanguage.ES -> "El Balance de Mediana Edad & la Gratitud (~42 a 60 años)"
            AppLanguage.ID -> "Muhasabah Usia Paruh Baya & Syukur (~42 s.d. 60 tahun)"
            AppLanguage.FR -> "Le Bilan de Mi-Vie et la Gratitude (~42 à 60 ans)"
        }
        "wisdom" -> when (lang) {
            AppLanguage.AR -> "الضوء الخافت والحصد (~60 إلى ~83 سنة)"
            AppLanguage.EN -> "The Fading Light & the Harvest (~60 to ~83 years)"
            AppLanguage.ES -> "La Luz que se Apaga & la Cosecha (~60 a ~83 años)"
            AppLanguage.ID -> "Cahaya yang Meredup & Panen (~60 s.d. ~83 tahun)"
            AppLanguage.FR -> "Le Crépuscule Lumineux et la Moisson (~60 à ~83 ans)"
        }
        else -> when (lang) {
            AppLanguage.AR -> "الدخول القريب إلى الأبدية (~83 سنة فما فوق)"
            AppLanguage.EN -> "The Imminent Entry into Eternity (~83 years and beyond)"
            AppLanguage.ES -> "La Entrada Inminente en la Eternidad (~83 años y más)"
            AppLanguage.ID -> "Saat Memasuki Keabadian (~83 tahun dan selebihnya)"
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
            AppLanguage.ES -> "« Allah os sacó de las entrañas de vuestras madres sin saber nada. »"
            AppLanguage.ID -> "« Allah mengeluarkan kamu dari perut ibu kalian dalam keadaan tidak tahu apa-apa. »"
            AppLanguage.FR -> "« Allah vous a fait sortir du ventre de vos mères dénués de tout savoir... »"
        }
        "youth" -> when (lang) {
            AppLanguage.AR -> "« اغتنم خمساً قبل خمس: شبابك قبل شيخوك... »"
            AppLanguage.EN -> "« Take advantage of five before five: your youth before your old age... »"
            AppLanguage.ES -> "« Aprovechad cinco antes de cinco: tu juventud antes de tu vejez... »"
            AppLanguage.ID -> "« Manfaatkan lima sebelum lima: masa mudamu sebelum masamu tua... »"
            AppLanguage.FR -> "« Profite de cinq choses avant cinq autres : ta jeunesse avant ta vieillesse... »"
        }
        "maturity" -> when (lang) {
            AppLanguage.AR -> "« لما بلغ أربعين سنة قال: ربِّ أوزعني أن أشكر نعمتك »"
            AppLanguage.EN -> "« When he reached forty, he said: My Lord, inspire me to be grateful for Your favour. »"
            AppLanguage.ES -> "« Al llegar a los cuarenta, dijo: Señor mío, inspírame para ser agradecido con Tu favor. »"
            AppLanguage.ID -> "« Ketika mencapai empat puluh tahun, dia berdoa: Tuhanku, anugerahkan aku bersyukur atas nikmat-Mu. »"
            AppLanguage.FR -> "« Arrivé à quarante ans, il dit : Ô Seigneur, inspire-moi de Te remercier... »"
        }
        "wisdom" -> when (lang) {
            AppLanguage.AR -> "« ألم نعطيك حياة كافية لتتدبر؟ »"
            AppLanguage.EN -> "« Did We not give you a life long enough to reflect? »"
            AppLanguage.ES -> "« ¿No os dimos una vida lo bastante larga para reflexionar? »"
            AppLanguage.ID -> "« Bukankah Kami berikan umur yang cukup panjang untuk merenung? »"
            AppLanguage.FR -> "« Ne vous avons-Nous pas donné une vie assez longue pour réfléchir ? »"
        }
        else -> when (lang) {
            AppLanguage.AR -> "« كل نفس ذائقة الموت. وإنما توفون أجوركم يوم القيامة »"
            AppLanguage.EN -> "« Every soul will taste death. On the Day of Resurrection you will be given your full reward. »"
            AppLanguage.ES -> "« Toda alma probará la muerte. El Día de la Resurrección se os dará vuestra recompensa completa. »"
            AppLanguage.ID -> "« Setiap jiwa akan merasakan mati. Pada Hari Kebangkitan kamu diberi pahala secara utuh. »"
            AppLanguage.FR -> "« Chaque âme goûtera la mort. Vous recevrez vos salaires complets. »"
        }
    }

    fun stageReference(lang: AppLanguage, stageId: String): String = when (stageId) {
        "first_half_hour" -> when (lang) {
            AppLanguage.AR -> "سورة النحل (16:78)"
            AppLanguage.EN -> "Surah An-Nahl (16:78)"
            AppLanguage.ES -> "Surah An-Nahl (16:78)"
            AppLanguage.ID -> "Surah An-Nahl (16:78)"
            AppLanguage.FR -> "Sourate An-Nahl (16:78)"
        }
        "youth" -> when (lang) {
            AppLanguage.AR -> "حديث صحيح (الحاكم)"
            AppLanguage.EN -> "Authentic Hadith (Al-Hakim)"
            AppLanguage.ES -> "Hadiz auténtico (Al-Hakim)"
            AppLanguage.ID -> "Hadis shahih (Al-Hakim)"
            AppLanguage.FR -> "Hadith authentique (Al-Hakim)"
        }
        "maturity" -> when (lang) {
            AppLanguage.AR -> "سورة الأحقاف (46:15)"
            AppLanguage.EN -> "Surah Al-Ahqaf (46:15)"
            AppLanguage.ES -> "Surah Al-Ahqaf (46:15)"
            AppLanguage.ID -> "Surah Al-Ahqaf (46:15)"
            AppLanguage.FR -> "Sourate Al-Ahqaf (46:15)"
        }
        "wisdom" -> when (lang) {
            AppLanguage.AR -> "سورة فاطر (35:37)"
            AppLanguage.EN -> "Surah Fatir (35:37)"
            AppLanguage.ES -> "Surah Fatir (35:37)"
            AppLanguage.ID -> "Surah Fatir (35:37)"
            AppLanguage.FR -> "Sourate Fatir (35:37)"
        }
        else -> when (lang) {
            AppLanguage.AR -> "سورة آل عمران (3:185)"
            AppLanguage.EN -> "Surah Al-Imran (3:185)"
            AppLanguage.ES -> "Surah Al-Imran (3:185)"
            AppLanguage.ID -> "Surah Al-Imran (3:185)"
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
            AppLanguage.ES -> "Nacimiento"
            AppLanguage.ID -> "Lahir"
            AppLanguage.FR -> "Naissance"
        }
        "childhood" -> when (lang) {
            AppLanguage.AR -> "الطفولة والبراءة"
            AppLanguage.EN -> "Childhood & Innocence"
            AppLanguage.ES -> "Infancia & Inocencia"
            AppLanguage.ID -> "Masa Kecil & Kepolosan"
            AppLanguage.FR -> "Enfance & Insouciance"
        }
        "puberty" -> when (lang) {
            AppLanguage.AR -> "البلوغ والتكليف"
            AppLanguage.EN -> "Puberty & Taklif"
            AppLanguage.ES -> "Pubertad & Taklif"
            AppLanguage.ID -> "Balig & Taklif"
            AppLanguage.FR -> "Puberté & Taklif"
        }
        "youth_marriage" -> when (lang) {
            AppLanguage.AR -> "الشباب وبناء الأسرة"
            AppLanguage.EN -> "Youth & Marriage"
            AppLanguage.ES -> "Juventud & Matrimonio"
            AppLanguage.ID -> "Masa Muda & Menikah"
            AppLanguage.FR -> "Jeunesse & Mariage"
        }
        "maturity_40" -> when (lang) {
            AppLanguage.AR -> "منتصف العمر (40 سنة)"
            AppLanguage.EN -> "Mid-Life (40 years)"
            AppLanguage.ES -> "Mediana edad (40 años)"
            AppLanguage.ID -> "Usia Paruh Baya (40 tahun)"
            AppLanguage.FR -> "Milieu de vie (40 ans)"
        }
        "wisdom_aging" -> when (lang) {
            AppLanguage.AR -> "الشيخوخة والشيب"
            AppLanguage.EN -> "Old Age & White Hair"
            AppLanguage.ES -> "Vejez & Cabello Blanco"
            AppLanguage.ID -> "Usia Senja & Rambut Putih"
            AppLanguage.FR -> "Vieillesse & Cheveux Blancs"
        }
        else -> when (lang) {
            AppLanguage.AR -> "الرجوع العظيم إلى الله"
            AppLanguage.EN -> "The Great Return to Allah"
            AppLanguage.ES -> "El Gran Retorno a Allah"
            AppLanguage.ID -> "Kembali Agung kepada Allah"
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

    /**
     * Roman-script stage name shown under the Arabic title (Cinzel pendant,
     * as seen in the Journey station cards and prayer rows). In Arabic mode
     * it resolves to the French name — previously it fell back to Arabic and
     * duplicated the Amiri heading ("الولادة" twice on the birth scene).
     */
    fun milestoneLatinTitle(lang: AppLanguage, id: String): String = when (lang) {
        AppLanguage.AR -> milestoneTitle(AppLanguage.FR, id)
        else -> milestoneTitle(lang, id)
    }

    fun milestoneDescription(lang: AppLanguage, id: String): String = when (id) {
        "birth" -> when (lang) {
            AppLanguage.AR -> "أول نفَس على الأرض. تصل عارياً عاجزاً، لكنك منعمٌ به رحمة الخالق."
            AppLanguage.EN -> "First breath on Earth. You arrive naked and helpless, yet filled with the Creator's mercy."
            AppLanguage.ES -> "Primer aliento en la Tierra. Llegas desnudo e indefenso, pero lleno de la misericordia del Creador."
            AppLanguage.ID -> "Napas pertama di Bumi. Kamu datang telanjang dan tak berdaya, namun penuh rahmat Sang Pencipta."
            AppLanguage.FR -> "Premier souffle sur Terre. Tu arrives nu et démuni, comblé par la miséricorde du Créateur."
        }
        "childhood" -> when (lang) {
            AppLanguage.AR -> "ألعاب ودهشة وبراءة. ما زال ريش الملائكة مرفوعاً لك."
            AppLanguage.EN -> "Games, wonder and innocence. The angels' wings are still raised for you."
            AppLanguage.ES -> "Juegos, asombro e inocencia. Las alas de los ángeles siguen alzadas por ti."
            AppLanguage.ID -> "Permainan, keajaiban dan kepolosan. Sayap para malaikat masih terhampar untukmu."
            AppLanguage.FR -> "Jeux, émerveillement et innocence. La plume des anges est encore levée pour toi."
        }
        "puberty" -> when (lang) {
            AppLanguage.AR -> "الكتابة تبدأ. تصير مسؤولاً عن صلاتك وكلامك وقلبك."
            AppLanguage.EN -> "The pen begins. You become accountable for your prayers, your words and your heart."
            AppLanguage.ES -> "La pluma comienza. Respondes por tus oraciones, tus palabras y tu corazón."
            AppLanguage.ID -> "Pena mulai menulis. Kamu bertanggung jawab atas salat, ucapan, dan hatimu."
            AppLanguage.FR -> "L'écriture commence. Tu deviens responsable de tes prières, de tes paroles et de ton cœur."
        }
        "youth_marriage" -> when (lang) {
            AppLanguage.AR -> "قوة الجسد وطموحاتك، ونصف الدين، وبناء بيت تقوى."
            AppLanguage.EN -> "Physical strength, ambitions, half of the religion, and the building of a pious home."
            AppLanguage.ES -> "Fuerza física, ambiciones, la mitad de la religión y la construcción de un hogar piadoso."
            AppLanguage.ID -> "Kekuatan fisik, ambisi, separuh agama, dan membangun rumah yang saleh."
            AppLanguage.FR -> "Force physique, ambitions, moitié de la religion et construction d'un foyer pieux."
        }
        "maturity_40" -> when (lang) {
            AppLanguage.AR -> "ذروة النضج. تدرك أن الشباب يمضي وأن الساعة تدور بسرعة."
            AppLanguage.EN -> "The summit of maturity. You realise that youth is fading and the hour turns fast."
            AppLanguage.ES -> "La cumbre de la madurez. Comprendes que la juventud se apaga y la hora gira rápido."
            AppLanguage.ID -> "Puncak kedewasaan. Kau sadari masa muda memudar dan waktu berputar cepat."
            AppLanguage.FR -> "Le sommet de la maturité. Tu réalises que la jeunesse s'éloigne et que l'heure tourne vite."
        }
        "wisdom_aging" -> when (lang) {
            AppLanguage.AR -> "يضعف الجسد، وتُبطئ الخطوات. وتزداد قيمة كل لحظة."
            AppLanguage.EN -> "The body weakens and the steps slow down. Every remaining moment gains value."
            AppLanguage.ES -> "El cuerpo se debilita y los pasos se lentifican. Cada momento restante gana valor."
            AppLanguage.ID -> "Tubuh melemah dan langkah melambat. Setiap sisa waktu semakin berharga."
            AppLanguage.FR -> "Le corps faiblit, les pas ralentissent. Les artifices du monde perdent toute valeur."
        }
        else -> when (lang) {
            AppLanguage.AR -> "يسقط الستار على هاتين الساعتين الصغيرتين. الأبدية تبدأ."
            AppLanguage.EN -> "The curtain falls on these two small hours. Eternity begins."
            AppLanguage.ES -> "El telón cae sobre estas dos pequeñas horas. La eternidad comienza."
            AppLanguage.ID -> "Tirai turun atas dua jam kecil ini. Keabadian dimulai."
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
            AppLanguage.ES -> "Surah An-Nahl (16:78)"
            AppLanguage.ID -> "Surah An-Nahl (16:78)"
            AppLanguage.FR -> "Sourate An-Nahl (16:78)"
        }
        "childhood" -> when (lang) {
            AppLanguage.AR -> "سورة الحديد (57:20)"
            AppLanguage.EN -> "Surah Al-Hadid (57:20)"
            AppLanguage.ES -> "Surah Al-Hadid (57:20)"
            AppLanguage.ID -> "Surah Al-Hadid (57:20)"
            AppLanguage.FR -> "Sourate Al-Hadid (57:20)"
        }
        "puberty" -> when (lang) {
            AppLanguage.AR -> "سورة ق (50:18)"
            AppLanguage.EN -> "Surah Qaf (50:18)"
            AppLanguage.ES -> "Surah Qaf (50:18)"
            AppLanguage.ID -> "Surah Qaf (50:18)"
            AppLanguage.FR -> "Sourate Qaf (50:18)"
        }
        "youth_marriage" -> when (lang) {
            AppLanguage.AR -> "سورة الروم (30:21)"
            AppLanguage.EN -> "Surah Ar-Rum (30:21)"
            AppLanguage.ES -> "Surah Ar-Rum (30:21)"
            AppLanguage.ID -> "Surah Ar-Rum (30:21)"
            AppLanguage.FR -> "Sourate Ar-Rum (30:21)"
        }
        "maturity_40" -> when (lang) {
            AppLanguage.AR -> "سورة الأحقاف (46:15)"
            AppLanguage.EN -> "Surah Al-Ahqaf (46:15)"
            AppLanguage.ES -> "Surah Al-Ahqaf (46:15)"
            AppLanguage.ID -> "Surah Al-Ahqaf (46:15)"
            AppLanguage.FR -> "Sourate Al-Ahqaf (46:15)"
        }
        "wisdom_aging" -> when (lang) {
            AppLanguage.AR -> "سورة الروم (30:54)"
            AppLanguage.EN -> "Surah Ar-Rum (30:54)"
            AppLanguage.ES -> "Surah Ar-Rum (30:54)"
            AppLanguage.ID -> "Surah Ar-Rum (30:54)"
            AppLanguage.FR -> "Sourate Ar-Rum (30:54)"
        }
        else -> when (lang) {
            AppLanguage.AR -> "سورة آل عمران (3:185)"
            AppLanguage.EN -> "Surah Al-Imran (3:185)"
            AppLanguage.ES -> "Surah Al-Imran (3:185)"
            AppLanguage.ID -> "Surah Al-Imran (3:185)"
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
            AppLanguage.ES -> "« Y un día junto a tu Señor es como mil años de lo que contáis. »"
            AppLanguage.ID -> "« Dan sehari bersama Tuhanmu seperti seribu tahun dari apa yang kamu hitung. »"
            AppLanguage.FR -> "« Et un jour auprès de ton Seigneur est comme mille ans de ce que vous comptez. »"
        }
        "muminun_112" -> when (lang) {
            AppLanguage.AR -> "« قال: كم لبثتم في الأرض عدد سنين؟ قالوا: لبثنا يوماً أو بعض يوم فاسأل العادين »"
            AppLanguage.EN -> "« He will say: How many years did you remain on earth? They will say: We remained a day or part of a day; ask those who count. »"
            AppLanguage.ES -> "« Dirá: ¿Cuántos años permanecisteis en la tierra? Dirán: Un día o parte de un día; preguntad a los que cuentan. »"
            AppLanguage.ID -> "« Dia berkata: Berapa lama kamu tinggal di bumi? Mereka menjawab: Sehari atau sebagian hari; tanyakan kepada yang menghitung. »"
            AppLanguage.FR -> "« Il dira : Combien d'années êtes-vous restés sur terre ? Ils diront : Nous y avons demeuré un jour, ou une partie d'un jour ; interroge donc ceux qui comptent. »"
        }
        "asr_103" -> when (lang) {
            AppLanguage.AR -> "« والعصر! إن الإنسان لفي خسر، إلا الذين آمنوا وعملوا الصالحات وتواصوا بالحق وتواصوا بالصبر »"
            AppLanguage.EN -> "« By Time! Indeed, mankind is in loss, except for those who believe and do righteous deeds and enjoin one another upon truth and enjoin one another upon patience. »"
            AppLanguage.ES -> "« ¡Por el tiempo! Ciertamente, la humanidad está en pérdida, excepto quienes creen y obran el bien y se recomiendan la verdad y la paciencia. »"
            AppLanguage.ID -> "« Demi waktu! Sungguh, manusia berada dalam kerugian, kecuali orang-orang beriman dan berbuat kebajikan serta saling menasihati untuk kebenaran dan kesabaran. »"
            AppLanguage.FR -> "« Par le Temps ! L'homme est certes en perdition, sauf ceux qui croient et accomplissent les bonnes œuvres, s'enjoignent mutuellement la vérité et s'enjoignent mutuellement l'endurance. »"
        }
        "naziat_46" -> when (lang) {
            AppLanguage.AR -> "« كأنهم يوم يرونها لم يلثوا إلا عشيةً أوضحاها »"
            AppLanguage.EN -> "« The day they see it, it will seem to them as if they had not remained more than an evening or a morning. »"
            AppLanguage.ES -> "« El día que lo vean, les parecerá como si no hubieran permanecido más que una tarde o una mañana. »"
            AppLanguage.ID -> "« Pada hari mereka melihatnya, terasa seperti tidak tinggal lebih dari sore atau pagi hari. »"
            AppLanguage.FR -> "« Le jour où ils la verront, ce sera comme s'ils n'étaient restés qu'un soir ou le matin d'après. »"
        }
        else -> when (lang) {
            AppLanguage.AR -> "« ويوم يحشرهم كأن لم يلبثوا إلا ساعة من النهار يتعارفون بينهم »"
            AppLanguage.EN -> "« And the day He will gather them, it will seem as if they had not remained more than an hour of a day, as they recognise one another. »"
            AppLanguage.ES -> "« Y el día que Él los reúna, parecerá como si no hubieran permanecido más de una hora de un día, mientras se reconocen unos a otros. »"
            AppLanguage.ID -> "« Dan pada hari Dia menghimpun mereka, terasa seolah-olah mereka tidak tinggal lebih dari satu jam dalam sehari, saat mereka saling mengenal. »"
            AppLanguage.FR -> "« Et le jour où Il les rassemblera, ce sera comme s'ils n'avaient demeuré qu'une heure d'un jour, pour se reconnaître mutuellement. »"
        }
    }

    fun quranVerseContextTheme(lang: AppLanguage, id: String): String = when (id) {
        "hajj_47" -> when (lang) {
            AppLanguage.AR -> "الوقت الإلهي"
            AppLanguage.EN -> "Divine Time"
            AppLanguage.ES -> "Tiempo Divino"
            AppLanguage.ID -> "Waktu Ilahi"
            AppLanguage.FR -> "Le Temps Divin"
        }
        "muminun_112" -> when (lang) {
            AppLanguage.AR -> "قِصر الحياة الدنيا"
            AppLanguage.EN -> "The brevity of this worldly life"
            AppLanguage.ES -> "La brevedad de esta vida mundanal"
            AppLanguage.ID -> "Singkatnya kehidupan dunia ini"
            AppLanguage.FR -> "La brièveté de la vie d'ici-bas"
        }
        "asr_103" -> when (lang) {
            AppLanguage.AR -> "قسم بالوقت"
            AppLanguage.EN -> "The Oath by Time"
            AppLanguage.ES -> "El Juramento por el Tiempo"
            AppLanguage.ID -> "Sumpah Demi Waktu"
            AppLanguage.FR -> "Le Serment par le Temps"
        }
        "naziat_46" -> when (lang) {
            AppLanguage.AR -> "أمسية أوضحى"
            AppLanguage.EN -> "An evening or a morning"
            AppLanguage.ES -> "Una tarde o una mañana"
            AppLanguage.ID -> "Sore atau pagi hari"
            AppLanguage.FR -> "Un matin ou un soir"
        }
        else -> when (lang) {
            AppLanguage.AR -> "ساعة واحدة"
            AppLanguage.EN -> "A single hour"
            AppLanguage.ES -> "Una sola hora"
            AppLanguage.ID -> "Hanya satu jam"
            AppLanguage.FR -> "Une seule heure"
        }
    }

    fun quranVerseReference(lang: AppLanguage, id: String): String = when (id) {
        "hajj_47" -> when (lang) {
            AppLanguage.AR -> "سورة الحج (22:47)"
            AppLanguage.EN -> "Surah Al-Hajj (22:47)"
            AppLanguage.ES -> "Surah Al-Hajj (22:47)"
            AppLanguage.ID -> "Surah Al-Hajj (22:47)"
            AppLanguage.FR -> "Sourate Al-Hajj (22:47)"
        }
        "muminun_112" -> when (lang) {
            AppLanguage.AR -> "سورة المؤمنون (23:112-113)"
            AppLanguage.EN -> "Surah Al-Mu'minun (23:112-113)"
            AppLanguage.ES -> "Surah Al-Mu'minun (23:112-113)"
            AppLanguage.ID -> "Surah Al-Mu'minun (23:112-113)"
            AppLanguage.FR -> "Sourate Al-Mu'minūn (23:112-113)"
        }
        "asr_103" -> when (lang) {
            AppLanguage.AR -> "سورة العصر (103:1-3)"
            AppLanguage.EN -> "Surah Al-Asr (103:1-3)"
            AppLanguage.ES -> "Surah Al-Asr (103:1-3)"
            AppLanguage.ID -> "Surah Al-Asr (103:1-3)"
            AppLanguage.FR -> "Sourate Al-Asr (103:1-3)"
        }
        "naziat_46" -> when (lang) {
            AppLanguage.AR -> "سورة النازعات (79:46)"
            AppLanguage.EN -> "Surah An-Nazi'at (79:46)"
            AppLanguage.ES -> "Surah An-Nazi'at (79:46)"
            AppLanguage.ID -> "Surah An-Nazi'at (79:46)"
            AppLanguage.FR -> "Sourate An-Nazi'at (79:46)"
        }
        else -> when (lang) {
            AppLanguage.AR -> "سورة يونس (10:45)"
            AppLanguage.EN -> "Surah Yunus (10:45)"
            AppLanguage.ES -> "Surah Yunus (10:45)"
            AppLanguage.ID -> "Surah Yunus (10:45)"
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
            AppLanguage.ES -> "El Primer Minuto"
            AppLanguage.ID -> "Menit Pertama"
            AppLanguage.FR -> "La Première Minute"
        }
        "c2_quran" -> when (lang) {
            AppLanguage.AR -> "الثانية المضيئة"
            AppLanguage.EN -> "The Luminous Second"
            AppLanguage.ES -> "El Segundo Luminoso"
            AppLanguage.ID -> "Detik yang Bercahaya"
            AppLanguage.FR -> "La Seconde Lumineuse"
        }
        "c3_sadaqa_dhikr" -> when (lang) {
            AppLanguage.AR -> "الربع الساعة الإلهي"
            AppLanguage.EN -> "The Divine Quarter-Hour"
            AppLanguage.ES -> "El Cuarto de Hora Divino"
            AppLanguage.ID -> "Seperempat Jam Ilahi"
            AppLanguage.FR -> "Le Quart d'Heure Divin"
        }
        "c4_tasbih_100" -> when (lang) {
            AppLanguage.AR -> "الذكر الدائم"
            AppLanguage.EN -> "Perpetual Remembrance"
            AppLanguage.ES -> "Recuerdo Perpetuo"
            AppLanguage.ID -> "Zikir Tanpa Henti"
            AppLanguage.FR -> "L'Évocation Perpétuelle"
        }
        else -> when (lang) {
            AppLanguage.AR -> "اليقظة بالساعة"
            AppLanguage.EN -> "The Consciousness of the Hour"
            AppLanguage.ES -> "La Conciencia de la Hora"
            AppLanguage.ID -> "Kesadaran Akan Waktu"
            AppLanguage.FR -> "La Conscience de l'Heure"
        }
    }

    fun challengeTitle(lang: AppLanguage, id: String): String = when (id) {
        "c1_five_prayers" -> when (lang) {
            AppLanguage.AR -> "الصلوات الخمس في وقتها"
            AppLanguage.EN -> "The 5 Prayers on Time"
            AppLanguage.ES -> "Las 5 oraciones a tiempo"
            AppLanguage.ID -> "5 Salat Tepat Waktu"
            AppLanguage.FR -> "Les 5 Prières à l'Heure"
        }
        "c2_quran" -> when (lang) {
            AppLanguage.AR -> "صفحة واحدة من القرآن"
            AppLanguage.EN -> "1 Page of Quran"
            AppLanguage.ES -> "1 página de Corán"
            AppLanguage.ID -> "1 Halaman Quran"
            AppLanguage.FR -> "1 Page de Coran"
        }
        "c3_sadaqa_dhikr" -> when (lang) {
            AppLanguage.AR -> "الصدقة والذكر اليومي"
            AppLanguage.EN -> "Sadaqa & Dhikr of the Day"
            AppLanguage.ES -> "Sadaqa & Dhikr del día"
            AppLanguage.ID -> "Sadaqa & Zikir Hari Ini"
            AppLanguage.FR -> "Sadaqa & Dhikr du Jour"
        }
        "c4_tasbih_100" -> when (lang) {
            AppLanguage.AR -> "100 تسبيحة اليوم"
            AppLanguage.EN -> "100 Tasbih Today"
            AppLanguage.ES -> "100 Tasbih hoy"
            AppLanguage.ID -> "100 Tasbih hari ini"
            AppLanguage.FR -> "100 Tasbih Aujourd'hui"
        }
        else -> when (lang) {
            AppLanguage.AR -> "حصيلة الساعتين"
            AppLanguage.EN -> "The Balance of 2 Hours"
            AppLanguage.ES -> "El Balance de 2 Horas"
            AppLanguage.ID -> "Neraca 2 Jam"
            AppLanguage.FR -> "Le Bilan des 2 Heures"
        }
    }

    fun challengeTargetDescription(lang: AppLanguage, id: String): String = when (id) {
        "c1_five_prayers" -> when (lang) {
            AppLanguage.AR -> "إقامة أركان اليوم الخمسة بين يدي خالقك"
            AppLanguage.EN -> "Fulfil the 5 pillars of the day in the presence of your Creator"
            AppLanguage.ES -> "Cumple los 5 pilares del día en presencia de tu Creador"
            AppLanguage.ID -> "Tunaikan 5 waktu hari ini di hadapan Sang Pencipta"
            AppLanguage.FR -> "Accomplir les 5 piliers de la journée devant ton Créateur"
        }
        "c2_quran" -> when (lang) {
            AppLanguage.AR -> "تلاوة وتدبّر صفحة على الأقل من كلام الله"
            AppLanguage.EN -> "Recite and reflect on at least one page of the Word of Allah"
            AppLanguage.ES -> "Recita y reflexiona al menos una página de la Palabra de Allah"
            AppLanguage.ID -> "Baca dan renungkan sedikitnya satu halaman Kalam Allah"
            AppLanguage.FR -> "Réciter et méditer au moins une page de la parole d'Allah"
        }
        "c3_sadaqa_dhikr" -> when (lang) {
            AppLanguage.AR -> "التصدّق وتقديس وقتك بذكر الله"
            AppLanguage.EN -> "Give charity and sanctify your time with the remembrance of Allah"
            AppLanguage.ES -> "Da caridad y santifica tu tiempo con el recuerdo de Allah"
            AppLanguage.ID -> "Bersedekahlah dan sucikan waktumu dengan zikir kepada Allah"
            AppLanguage.FR -> "Donner une aumône et sanctifier ton temps par l'évocation d'Allah"
        }
        "c4_tasbih_100" -> when (lang) {
            AppLanguage.AR -> "100 مرة سبحان الله أو الحمد لله أو أستغفر الله"
            AppLanguage.EN -> "100 times SubhanAllah, Alhamdulillah or Astaghfirullah"
            AppLanguage.ES -> "100 veces SubhanAllah, Alhamdulillah o Astaghfirullah"
            AppLanguage.ID -> "100 kali SubhanAllah, Alhamdulillah atau Astaghfirullah"
            AppLanguage.FR -> "100 formules de SubhanAllah, Alhamdulillah ou Astaghfirullah"
        }
        else -> when (lang) {
            AppLanguage.AR -> "شاهد رحلة الساعتين واكتب تأملاً في اليوميات"
            AppLanguage.EN -> "Watch the 2-hour journey and write a reflection in the journal"
            AppLanguage.ES -> "Mira el viaje de 2 horas y escribe una reflexión en el diario"
            AppLanguage.ID -> "Tonton perjalanan 2 jam dan tulis renungan di jurnal"
            AppLanguage.FR -> "Visionner le voyage des 2 heures et écrire une réflexion dans le journal"
        }
    }

    fun challengeBadgeName(lang: AppLanguage, id: String): String = when (id) {
        "c1_five_prayers" -> when (lang) {
            AppLanguage.AR -> "حارس الصلاة"
            AppLanguage.EN -> "Guardian of the Prayer"
            AppLanguage.ES -> "Guardián de la Oración"
            AppLanguage.ID -> "Penjaga Salat"
            AppLanguage.FR -> "Gardien de la Prière"
        }
        "c2_quran" -> when (lang) {
            AppLanguage.AR -> "رفيق القرآن"
            AppLanguage.EN -> "Companion of the Quran"
            AppLanguage.ES -> "Compañero del Corán"
            AppLanguage.ID -> "Sahabat Quran"
            AppLanguage.FR -> "Compagnon du Coran"
        }
        "c3_sadaqa_dhikr" -> when (lang) {
            AppLanguage.AR -> "قلب سخي"
            AppLanguage.EN -> "Generous Heart"
            AppLanguage.ES -> "Corazón Generoso"
            AppLanguage.ID -> "Hati Pemurah"
            AppLanguage.FR -> "Cœur Généreux"
        }
        "c4_tasbih_100" -> when (lang) {
            AppLanguage.AR -> "كل ثانية تُحسَب"
            AppLanguage.EN -> "Every Second Counts"
            AppLanguage.ES -> "Cada segundo cuenta"
            AppLanguage.ID -> "Setiap Detik Berharga"
            AppLanguage.FR -> "Chaque Seconde Compte"
        }
        else -> when (lang) {
            AppLanguage.AR -> "حارس الوقت"
            AppLanguage.EN -> "Guardian of Time"
            AppLanguage.ES -> "Guardián del Tiempo"
            AppLanguage.ID -> "Penjaga Waktu"
            AppLanguage.FR -> "Gardien du Temps"
        }
    }

    // ---------------------------------------------------------------------
    // Breakdown chart content
    // ---------------------------------------------------------------------

    fun breakdownChartTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الميزان الزمني للوجود"
        AppLanguage.EN -> "TIME BALANCE OF EXISTENCE"
        AppLanguage.ES -> "BALANCE DEL TIEMPO DE LA EXISTENCIA"
        AppLanguage.ID -> "NERACA WAKTU KEHIDUPAN"
        AppLanguage.FR -> "BILAN TEMPOREL DE L'EXISTENCE"
    }

    fun breakdownChartSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "أين تذهب حياتك كلها في ميزان الله؟"
        AppLanguage.EN -> "Where does your whole life go in the balance of Allah?"
        AppLanguage.ES -> "¿Dónde va tu vida entera en la balanza de Allah?"
        AppLanguage.ID -> "Ke mana perginya seluruh hidupmu dalam neraca Allah?"
        AppLanguage.FR -> "Où passe ta vie entière dans la balance d'Allah ?"
    }

    fun timeConsumedLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الوقت المستهلك"
        AppLanguage.EN -> "Time consumed"
        AppLanguage.ES -> "Tiempo consumido"
        AppLanguage.ID -> "Waktu terpakai"
        AppLanguage.FR -> "Temps consommé"
    }

    fun timeRemainingLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الوقت المتبقي"
        AppLanguage.EN -> "Time remaining"
        AppLanguage.ES -> "Tiempo restante"
        AppLanguage.ID -> "Sisa waktu"
        AppLanguage.FR -> "Temps restant"
    }

    fun lifetimeBudgetTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "ميزانية العمر بالكامل بالساعات الإلهية"
        AppLanguage.EN -> "FULL LIFETIME BUDGET IN DIVINE HOURS"
        AppLanguage.ES -> "PRESUPUESTO TOTAL DE VIDA EN HORAS DIVINAS"
        AppLanguage.ID -> "TOTAL ANGGARAN HIDUP DALAM JAM ILAHI"
        AppLanguage.FR -> "BUDGET TOTAL DE VIE EN HEURES DIVINES"
    }

    fun segmentSleep(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "النوم"
        AppLanguage.EN -> "Sleep"
        AppLanguage.ES -> "Sueño"
        AppLanguage.ID -> "Tidur"
        AppLanguage.FR -> "Sommeil"
    }

    fun segmentWork(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "العمل والدراسة"
        AppLanguage.EN -> "Work & Study"
        AppLanguage.ES -> "Trabajo & Estudio"
        AppLanguage.ID -> "Kerja & Belajar"
        AppLanguage.FR -> "Travail & Études"
    }

    fun segmentScreens(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الشاشات"
        AppLanguage.EN -> "Screens"
        AppLanguage.ES -> "Pantallas"
        AppLanguage.ID -> "Layar"
        AppLanguage.FR -> "Écrans"
    }

    fun segmentRest(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الطعام والتنقل والعائلة"
        AppLanguage.EN -> "Meals, travel, family"
        AppLanguage.ES -> "Comidas, viajes, familia"
        AppLanguage.ID -> "Makan, perjalanan, keluarga"
        AppLanguage.FR -> "Repas, trajets, famille"
    }

    fun segmentPrayers(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الصلوات الخمس"
        AppLanguage.EN -> "The 5 Prayers"
        AppLanguage.ES -> "Las 5 oraciones"
        AppLanguage.ID -> "5 Salat"
        AppLanguage.FR -> "Les 5 Prières"
    }

    // ---------------------------------------------------------------------
    // Clock dial content
    // ---------------------------------------------------------------------

    fun clockFaceTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الساعتان الإلهيتان"
        AppLanguage.EN -> "THE TWO DIVINE HOURS"
        AppLanguage.ES -> "LAS DOS HORAS DIVINAS"
        AppLanguage.ID -> "DUA JAM ILAHI"
        AppLanguage.FR -> "LES DEUX HEURES DIVINES"
    }

    fun clockFaceSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "حياتك كلها في 120 دقيقة عند الله"
        AppLanguage.EN -> "Your whole life in 120 minutes with Allah"
        AppLanguage.ES -> "Toda tu vida en 120 minutos con Allah"
        AppLanguage.ID -> "Seluruh hidupmu dalam 120 menit bersama Allah"
        AppLanguage.FR -> "Toute ta vie en 120 minutes chez Allah"
    }

    fun dialMinutePosition(lang: AppLanguage, minutesLived: Double): String = when (lang) {
        AppLanguage.AR -> "الدقيقة ${String.format(Locale.US, "%.1f", minutesLived)} من 120"
        AppLanguage.EN -> "Minute ${String.format(Locale.US, "%.1f", minutesLived)} of 120"
        AppLanguage.ES -> "Minuto ${String.format(Locale.US, "%.1f", minutesLived)} de 120"
        AppLanguage.ID -> "Menit ${String.format(Locale.US, "%.1f", minutesLived)} dari 120"
        AppLanguage.FR -> "Minute ${String.format(Locale.US, "%.1f", minutesLived)} sur 120"
    }

    // ---------------------------------------------------------------------
    // Visualizer extras
    // ---------------------------------------------------------------------

    fun endScreenVerseTranslation(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« كل نفس ذائقة الموت، وإنما توفون أجوركم يوم القيامة »"
        AppLanguage.EN -> "« Every soul will taste death, and you will only be given your full reward on the Day of Resurrection. »"
        AppLanguage.ES -> "« Toda alma probará la muerte, y solo se os dará vuestra recompensa completa el Día de la Resurrección. »"
        AppLanguage.ID -> "« Setiap jiwa akan merasakan mati, dan hanya pada Hari Kebangkitan kamu diberi pahala secara utuh. »"
        AppLanguage.FR -> "« Toute âme goûtera la mort. Et c'est seulement au Jour de la Résurrection que vous recevrez votre entière rétribution. »"
    }

    fun restartSimulationBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "إعادة البدء"
        AppLanguage.EN -> "Restart"
        AppLanguage.ES -> "Reiniciar"
        AppLanguage.ID -> "Mulai Ulang"
        AppLanguage.FR -> "Recommencer"
    }

    fun yearsShortLabel(lang: AppLanguage, years: Int): String = when (lang) {
        AppLanguage.AR -> "($years سنة)"
        AppLanguage.EN -> "($years yrs)"
        AppLanguage.ES -> "($years a)"
        AppLanguage.ID -> "($years thn)"
        AppLanguage.FR -> "($years ans)"
    }

    // ---------------------------------------------------------------------
    // Notification preference labels & previews
    // ---------------------------------------------------------------------

    fun masterNotificationsLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تفعيل الإشعارات"
        AppLanguage.EN -> "Enable notifications"
        AppLanguage.ES -> "Activar notificaciones"
        AppLanguage.ID -> "Aktifkan notifikasi"
        AppLanguage.FR -> "Activer les notifications"
    }

    fun reminderFajrLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تذكير الفجر"
        AppLanguage.EN -> "Fajr reminder"
        AppLanguage.ES -> "Recordatorio de Fajr"
        AppLanguage.ID -> "Pengingat Fajr"
        AppLanguage.FR -> "Rappel du Fajr"
    }

    fun reminderDhuhrLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تذكير الظهر"
        AppLanguage.EN -> "Dhuhr reminder"
        AppLanguage.ES -> "Recordatorio de Dhuhr"
        AppLanguage.ID -> "Pengingat Dhuhr"
        AppLanguage.FR -> "Rappel du Dhuhr"
    }

    fun reminderDailyLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "التذكير اليومي (13:00)"
        AppLanguage.EN -> "Daily reminder (1:00 PM)"
        AppLanguage.ES -> "Recordatorio diario (13:00)"
        AppLanguage.ID -> "Pengingat harian (13.00)"
        AppLanguage.FR -> "Rappel quotidien (13h00)"
    }

    fun reminderFridayLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تذكير يوم الجمعة"
        AppLanguage.EN -> "Friday reminder"
        AppLanguage.ES -> "Recordatorio de viernes"
        AppLanguage.ID -> "Pengingat Jumat"
        AppLanguage.FR -> "Rappel du vendredi"
    }

    // ── Per-prayer reminder notifications (real scheduling, P1-3) ──

    fun reminderFajrNotifTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "🕌 وقت الفجر"
        AppLanguage.EN -> "🕌 Time for Fajr"
        AppLanguage.ES -> "🕌 Hora del Fajr"
        AppLanguage.ID -> "🕌 Waktunya Fajr"
        AppLanguage.FR -> "🕌 L'heure du Fajr"
    }

    fun reminderFajrNotifBody(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "دخل وقت الفجر — قُم للصلاة، فقيام الليل عند الله خير من النوم."
        AppLanguage.EN -> "The time of Fajr has entered — rise for prayer; standing at night before Allah is better than sleep."
        AppLanguage.ES -> "Ha entrado la hora del Fajr — levántate para orar; estar de pie de noche ante Allah es mejor que dormir."
        AppLanguage.ID -> "Waktu Fajr telah masuk — bangunlah untuk salat; qiyam di malam hari lebih baik daripada tidur."
        AppLanguage.FR -> "L'heure du Fajr est entrée — lève-toi pour prier ; la veille devant Allah vaut mieux que le sommeil."
    }

    fun reminderDhuhrNotifTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "🕌 وقت الظهر"
        AppLanguage.EN -> "🕌 Time for Dhuhr"
        AppLanguage.ES -> "🕌 Hora del Dhuhr"
        AppLanguage.ID -> "🕌 Waktunya Dhuhr"
        AppLanguage.FR -> "🕌 L'heure du Dhuhr"
    }

    fun reminderDhuhrNotifBody(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "دخل وقت الظهر — في قلب النهار، اقطع خيط الدنيا واسجد لله."
        AppLanguage.EN -> "The time of Dhuhr has entered — at the heart of the day, cut the thread of business and settle before Allah."
        AppLanguage.ES -> "Ha entrado la hora del Dhuhr — en pleno corazón del día, corta el hilo de los asuntos y ponte ante Allah."
        AppLanguage.ID -> "Waktu Dhuhr telah masuk — di tengah hari, putuskan urusan dunia dan duduklah di hadapan Allah."
        AppLanguage.FR -> "L'heure du Dhuhr est entrée — au cœur du jour, coupe le fil des affaires et pose-toi devant Allah."
    }

    fun reminderFridayNotifTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "🕌 يوم الجمعة"
        AppLanguage.EN -> "🕌 Friday — Jumu'ah"
        AppLanguage.ES -> "🕌 Viernes — Yumu'a"
        AppLanguage.ID -> "🕌 Jumat — Jumu'ah"
        AppLanguage.FR -> "🕌 Vendredi — Jumu'ah"
    }

    fun reminderFridayNotifBody(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "أفضل أيام الأسبوع — أكثِر من الصلاة على النبي ﷺ."
        AppLanguage.EN -> "The best day of the week — multiply the salawat upon the Prophet ﷺ."
        AppLanguage.ES -> "El mejor día de la semana — multiplica las salawat sobre el Profeta ﷺ."
        AppLanguage.ID -> "Hari terbaik dalam sepekan — perbanyak selawat kepada Nabi ﷺ."
        AppLanguage.FR -> "Le meilleur jour de la semaine — multiplie les salawat sur le Prophète ﷺ."
    }

    /** Settings hint: prayer reminders depend on the selected city's timings. */
    fun reminderTimingsHint(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "صوتا الفجر والظهر يتبعان وقت مدينة المختارة، محدَّثَين عند كل فتح."
        AppLanguage.EN -> "Fajr and Dhuhr reminders follow your selected city times (Aladhan), refreshed on every app open."
        AppLanguage.ES -> "Los recordatorios de Fajr y Dhuhr siguen los horarios de tu ciudad (Aladhan), actualizados en cada apertura."
        AppLanguage.ID -> "Pengingat Fajr dan Dhuhr mengikuti jadwal kota pilihanmu (Aladhan), diperbarui setiap kali aplikasi dibuka."
        AppLanguage.FR -> "Les rappels du Fajr et du Dhuhr suivent les horaires de ta ville (Aladhan), actualisés à chaque ouverture."
    }

    fun notifPreviewWakeMoment(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الاستيقاظ"
        AppLanguage.EN -> "Wake up"
        AppLanguage.ES -> "Despertar"
        AppLanguage.ID -> "Bangun"
        AppLanguage.FR -> "Réveil"
    }

    fun notifPreviewWakeMessage(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« لقد نمت 0.078 ثانية إلهية. ابدأ بالفجر واربح آخرتك 🌅 »"
        AppLanguage.EN -> "« You just slept 0.078 divine seconds. Start with Fajr and win your hereafter 🌅 »"
        AppLanguage.ES -> "« Acabas de dormir 0.078 segundos divinos. Comienza con el Fajr y gana tu otra vida 🌅 »"
        AppLanguage.ID -> "« Kamu baru saja tidur 0.078 detik ilahi. Mulailah dengan Fajr dan raih akhiratmu 🌅 »"
        AppLanguage.FR -> "« Tu viens de dormir 0.078 sec divine. Commence avec Fajr et gagne ton au-delà 🌅 »"
    }

    fun notifPreviewNoonMoment(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الظهر"
        AppLanguage.EN -> "Noon"
        AppLanguage.ES -> "Mediodía"
        AppLanguage.ID -> "Tengah hari"
        AppLanguage.FR -> "Midi"
    }

    fun notifPreviewNoonMessage(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« مضى 0.118 ثانية إلهية اليوم. هل صليت الظهر في وقته؟ »"
        AppLanguage.EN -> "« 0.118 divine seconds have passed today. Have you prayed Dhuhr on time? »"
        AppLanguage.ES -> "« Hoy han pasado 0.118 segundos divinos. ¿Has orado el Dhuhr a tiempo? »"
        AppLanguage.ID -> "« Hari ini telah berlalu 0.118 detik ilahi. Sudahkah kamu salat Dhuhr tepat waktu? »"
        AppLanguage.FR -> "« 0.118 sec divine écoulée aujourd'hui. As-tu prié Dhuhr à l'heure ? »"
    }

    fun notifPreviewScrollMoment(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تصفح الشبكات > 30 دقيقة"
        AppLanguage.EN -> "Scrolling > 30 min"
        AppLanguage.ES -> "Scrolling > 30 min"
        AppLanguage.ID -> "Gulir > 30 mnt"
        AppLanguage.FR -> "Scroll réseaux > 30min"
    }

    fun notifPreviewScrollMessage(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« لقد أمضيت 0.005 ثانية إلهية على شاشتك... عُد إلى الجوهر. »"
        AppLanguage.EN -> "« You just spent 0.005 divine seconds on your screen... Return to what matters. »"
        AppLanguage.ES -> "« Acabas de gastar 0.005 segundos divinos en tu pantalla... Vuelve a lo que importa. »"
        AppLanguage.ID -> "« Kamu baru menghabiskan 0.005 detik ilahi di layar... Kembalilah kepada yang penting. »"
        AppLanguage.FR -> "« Tu viens de passer 0.005 sec divine sur ton écran... Reviens à l'essentiel. »"
    }

    fun notifPreviewBirthdayMoment(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "عيد الميلاد / العمر"
        AppLanguage.EN -> "Birthday / Age"
        AppLanguage.ES -> "Cumpleaños / Edad"
        AppLanguage.ID -> "Ulang tahun / Usia"
        AppLanguage.FR -> "Anniversaire / Âge"
    }

    fun notifPreviewBirthdayMessage(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« لقد أمضيت دقيقة إلهية جديدة على الأرض. لم يبق لك إلا القليل ⏳ »"
        AppLanguage.EN -> "« You have spent another divine minute on Earth. Little time remains ⏳ »"
        AppLanguage.ES -> "« Has gastado otro minuto divino en la Tierra. Queda poco tiempo ⏳ »"
        AppLanguage.ID -> "« Kamu baru menghabiskan satu menit ilahi di Bumi. Waktu tersisa sedikit ⏳ »"
        AppLanguage.FR -> "« Tu as passé une nouvelle minute divine sur Terre. Il te reste peu de temps ⏳ »"
    }

    fun notifPreviewFridayMoment(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الجمعة"
        AppLanguage.EN -> "Friday"
        AppLanguage.ES -> "Viernes"
        AppLanguage.ID -> "Jumat"
        AppLanguage.FR -> "Vendredi (Joumou'a)"
    }

    fun notifPreviewFridayMessage(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« يوم الجمعة. استثمر هذه الميكرو-ثانية الإلهية في الصلاة على النبي ﷺ »"
        AppLanguage.EN -> "« Friday. Invest this divine micro-second in prayers upon the Prophet ﷺ »"
        AppLanguage.ES -> "« Viernes. Invierte este micro-segundo divino en oraciones sobre el Profeta ﷺ »"
        AppLanguage.ID -> "« Jumat. Investasikan mikro-detik ilahi ini dengan selawat kepada Nabi ﷺ »"
        AppLanguage.FR -> "« Jour de Joumou'a. Investis cette micro-seconde divine dans la prière sur le Prophète ﷺ »"
    }

    // ---------------------------------------------------------------------
    // Life map axis markers (kept short: five fit on one row)
    // ---------------------------------------------------------------------

    fun lifeMapMarkerBirth(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الولادة"
        AppLanguage.EN -> "Birth"
        AppLanguage.ES -> "Nacimiento"
        AppLanguage.ID -> "Lahir"
        AppLanguage.FR -> "Début"
    }

    fun lifeMapMarkerTaklif(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "التكليف"
        AppLanguage.EN -> "Taklif"
        AppLanguage.ES -> "Taklif"
        AppLanguage.ID -> "Taklif"
        AppLanguage.FR -> "Taklif"
    }

    fun lifeMapMarkerMaturity(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "النضج"
        AppLanguage.EN -> "Maturity"
        AppLanguage.ES -> "Madurez"
        AppLanguage.ID -> "Kedewasaan"
        AppLanguage.FR -> "Maturité"
    }

    fun lifeMapMarkerWisdom(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الشيب"
        AppLanguage.EN -> "White hair"
        AppLanguage.ES -> "Cabello blanco"
        AppLanguage.ID -> "Rambut putih"
        AppLanguage.FR -> "Cheveux blancs"
    }

    fun lifeMapMarkerEternity(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الأبدية"
        AppLanguage.EN -> "Eternity"
        AppLanguage.ES -> "Eternidad"
        AppLanguage.ID -> "Keabadian"
        AppLanguage.FR -> "Éternité"
    }

    fun hourSpanFirst(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "0 إلى ~42 سنة"
        AppLanguage.EN -> "0 to ~42 yrs"
        AppLanguage.ES -> "0 a ~42 a"
        AppLanguage.ID -> "0 s.d. ~42 thn"
        AppLanguage.FR -> "0 à ~42 ans"
    }

    fun hourSpanSecond(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "~42 إلى ~83 سنة"
        AppLanguage.EN -> "~42 to ~83 yrs"
        AppLanguage.ES -> "~42 a ~83 a"
        AppLanguage.ID -> "~42 s.d. ~83 thn"
        AppLanguage.FR -> "~42 à ~83 ans"
    }

    fun selectedContentDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "محدد"
        AppLanguage.EN -> "Selected"
        AppLanguage.ES -> "Seleccionado"
        AppLanguage.ID -> "Dipilih"
        AppLanguage.FR -> "Sélectionné"
    }

    /** Localized display name for a prayer key (case-insensitive). */
    fun prayerName(lang: AppLanguage, key: String): String = when (key.lowercase()) {
        "fajr" -> when (lang) {
            AppLanguage.AR -> "الفجر"
            AppLanguage.EN -> "Fajr"
            AppLanguage.ES -> "Fajr"
            AppLanguage.ID -> "Fajr"
            AppLanguage.FR -> "Fajr"
        }
        "sunrise" -> when (lang) {
            AppLanguage.AR -> "الشروق"
            AppLanguage.EN -> "Sunrise"
            AppLanguage.ES -> "Amanecer"
            AppLanguage.ID -> "Terbit"
            AppLanguage.FR -> "Chourouq"
        }
        "dhuhr" -> when (lang) {
            AppLanguage.AR -> "الظهر"
            AppLanguage.EN -> "Dhuhr"
            AppLanguage.ES -> "Dhuhr"
            AppLanguage.ID -> "Dhuhr"
            AppLanguage.FR -> "Dhuhr"
        }
        "asr" -> when (lang) {
            AppLanguage.AR -> "العصر"
            AppLanguage.EN -> "Asr"
            AppLanguage.ES -> "Asr"
            AppLanguage.ID -> "Asr"
            AppLanguage.FR -> "Asr"
        }
        "maghrib" -> when (lang) {
            AppLanguage.AR -> "المغرب"
            AppLanguage.EN -> "Maghrib"
            AppLanguage.ES -> "Maghrib"
            AppLanguage.ID -> "Maghrib"
            AppLanguage.FR -> "Maghrib"
        }
        "isha" -> when (lang) {
            AppLanguage.AR -> "العشاء"
            AppLanguage.EN -> "Isha"
            AppLanguage.ES -> "Isha"
            AppLanguage.ID -> "Isha"
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
        AppLanguage.ES -> "${h}h ${m}min ${s}s divinos"
        AppLanguage.ID -> "${h}j ${m}mnt ${s}dtk ilahi"
        AppLanguage.FR -> "${h}h ${m}min ${s}s divines"
    }

    fun divineUnitMinutesSeconds(lang: AppLanguage, m: Int, s: Int): String = when (lang) {
        AppLanguage.AR -> "$m د $s ث إلهية"
        AppLanguage.EN -> "${m}min ${s}s divine"
        AppLanguage.ES -> "${m}min ${s}s divinos"
        AppLanguage.ID -> "${m}mnt ${s}dtk ilahi"
        AppLanguage.FR -> "${m}min ${s}s divines"
    }

    fun divineUnitSeconds(lang: AppLanguage, value: Double, decimals: Int): String {
        val formatted = String.format(Locale.US, "%.${decimals}f", value)
        return when (lang) {
            AppLanguage.AR -> "$formatted ثانية إلهية"
            AppLanguage.EN -> "$formatted divine sec"
            AppLanguage.ES -> "$formatted seg divinos"
            AppLanguage.ID -> "$formatted dtk ilahi"
            AppLanguage.FR -> "$formatted sec divine"
        }
    }

    fun divineMinutesShort(lang: AppLanguage, value: String): String = when (lang) {
        AppLanguage.AR -> "$value د إلهية"
        AppLanguage.EN -> "$value divine min"
        AppLanguage.ES -> "$value min divinos"
        AppLanguage.ID -> "$value mnt ilahi"
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
        AppLanguage.ES -> "$value SOLO MINUTOS DIVINOS"
        AppLanguage.ID -> "$value MENIT ILAHI SAJA"
        AppLanguage.FR -> "$value MIN DIVINES SEULEMENT"
    }

    fun percentOfLifeLabel(lang: AppLanguage, percent: Float): String {
        val formatted = String.format(Locale.US, "%.1f", percent)
        return when (lang) {
            AppLanguage.AR -> "$formatted% من حياتك"
            AppLanguage.EN -> "$formatted% of your life"
            AppLanguage.ES -> "$formatted% de tu vida"
            AppLanguage.ID -> "$formatted% dari hidupmu"
            AppLanguage.FR -> "$formatted% de ta vie"
        }
    }

    fun earthYearsMonthsDays(lang: AppLanguage, y: Int, mo: Int, dd: Int): String = when (lang) {
        AppLanguage.AR -> "$y سنة، $mo شهر ($dd يوم)"
        AppLanguage.EN -> "$y yrs, $mo months ($dd days)"
        AppLanguage.ES -> "$y a, $mo meses ($dd días)"
        AppLanguage.ID -> "$y thn, $mo bln ($dd hari)"
        AppLanguage.FR -> "$y ans, $mo mois ($dd j)"
    }

    fun earthYearsMonths(lang: AppLanguage, y: Int, mo: Int): String = when (lang) {
        AppLanguage.AR -> "$y سنة، $mo شهر"
        AppLanguage.EN -> "$y yrs, $mo months"
        AppLanguage.ES -> "$y a, $mo meses"
        AppLanguage.ID -> "$y thn, $mo bln"
        AppLanguage.FR -> "$y ans, $mo mois"
    }

    fun earthYearsDays(lang: AppLanguage, y: Int, dd: Int): String = when (lang) {
        AppLanguage.AR -> "$y سنة ($dd يوم)"
        AppLanguage.EN -> "$y yrs ($dd days)"
        AppLanguage.ES -> "$y a ($dd días)"
        AppLanguage.ID -> "$y thn ($dd hari)"
        AppLanguage.FR -> "$y ans ($dd j)"
    }

    fun earthYearsOnly(lang: AppLanguage, y: Int): String = when (lang) {
        AppLanguage.AR -> "$y سنة"
        AppLanguage.EN -> "$y yrs"
        AppLanguage.ES -> "$y a"
        AppLanguage.ID -> "$y thn"
        AppLanguage.FR -> "$y ans"
    }

    fun earthDaysHours(lang: AppLanguage, dd: Int, hh: Int): String = when (lang) {
        AppLanguage.AR -> "$dd يوم، $hh ساعة"
        AppLanguage.EN -> "$dd days, $hh hours"
        AppLanguage.ES -> "$dd días, $hh horas"
        AppLanguage.ID -> "$dd hari, $hh jam"
        AppLanguage.FR -> "$dd jours, $hh heures"
    }

    fun earthHoursMinutes(lang: AppLanguage, hh: Int, mm: Int): String = when (lang) {
        AppLanguage.AR -> "$hh ساعة، $mm دقيقة"
        AppLanguage.EN -> "$hh hours, $mm minutes"
        AppLanguage.ES -> "$hh horas, $mm minutos"
        AppLanguage.ID -> "$hh jam, $mm menit"
        AppLanguage.FR -> "$hh heures, $mm minutes"
    }

    // ---------------------------------------------------------------------
    // Journal screen extras
    // ---------------------------------------------------------------------

    fun scoreMessage(lang: AppLanguage, score: Int): String = when (score) {
        5 -> when (lang) {
            AppLanguage.AR -> "يوم من الذهب الخالص • الله أكبر"
            AppLanguage.EN -> "A day of pure gold • Allahu Akbar"
            AppLanguage.ES -> "Un día de oro puro • Allahu Akbar"
            AppLanguage.ID -> "Hari emas murni • Allahu Akbar"
            AppLanguage.FR -> "Journée d'or pur • Allahu Akbar"
        }
        4 -> when (lang) {
            AppLanguage.AR -> "استثمار روحي جميل جداً"
            AppLanguage.EN -> "A very fine spiritual investment"
            AppLanguage.ES -> "Una inversión espiritual muy fina"
            AppLanguage.ID -> "Investasi spiritual yang sangat indah"
            AppLanguage.FR -> "Très bel investissement spirituel"
        }
        3 -> when (lang) {
            AppLanguage.AR -> "انتظام جيد، واصل!"
            AppLanguage.EN -> "Good consistency, keep going!"
            AppLanguage.ES -> "¡Buena constancia, sigue así!"
            AppLanguage.ID -> "Konsistensi bagus, teruskan!"
            AppLanguage.FR -> "Bonne régularité, persévère !"
        }
        2 -> when (lang) {
            AppLanguage.AR -> "لا تدع الثانية تفلت منك"
            AppLanguage.EN -> "Do not let the second slip away"
            AppLanguage.ES -> "No dejes escapar el segundo"
            AppLanguage.ID -> "Jangan biarkan detik ini lepas"
            AppLanguage.FR -> "Ne laisse pas la seconde s'enfuir"
        }
        else -> when (lang) {
            AppLanguage.AR -> "ابدأ بخطوة نحو الله"
            AppLanguage.EN -> "Start with one step towards Allah"
            AppLanguage.ES -> "Empieza con un paso hacia Allah"
            AppLanguage.ID -> "Mulailah dengan satu langkah menuju Allah"
            AppLanguage.FR -> "Commence par un pas vers Allah"
        }
    }

    fun prayerDeedLabel(lang: AppLanguage, key: String): String {
        val name = prayerName(lang, key)
        val detail = when (key.lowercase()) {
            "fajr" -> when (lang) {
                AppLanguage.AR -> "صلاة الفجر"
                AppLanguage.EN -> "Dawn prayer"
                AppLanguage.ES -> "Oración del alba"
                AppLanguage.ID -> "Salat subuh"
                AppLanguage.FR -> "Prière de l'Aube"
            }
            "dhuhr" -> when (lang) {
                AppLanguage.AR -> "صلاة الظهر"
                AppLanguage.EN -> "Noon prayer"
                AppLanguage.ES -> "Oración del mediodía"
                AppLanguage.ID -> "Salat pertengahan hari"
                AppLanguage.FR -> "Prière de Midi"
            }
            "asr" -> when (lang) {
                AppLanguage.AR -> "صلاة العصر"
                AppLanguage.EN -> "Afternoon prayer"
                AppLanguage.ES -> "Oración de la tarde"
                AppLanguage.ID -> "Salat sore"
                AppLanguage.FR -> "Prière de l'Après-midi"
            }
            "maghrib" -> when (lang) {
                AppLanguage.AR -> "صلاة المغرب"
                AppLanguage.EN -> "Sunset prayer"
                AppLanguage.ES -> "Oración del atardecer"
                AppLanguage.ID -> "Salat magrib"
                AppLanguage.FR -> "Prière du Coucher"
            }
            else -> when (lang) {
                AppLanguage.AR -> "صلاة العشاء"
                AppLanguage.EN -> "Night prayer"
                AppLanguage.ES -> "Oración nocturna"
                AppLanguage.ID -> "Salat malam"
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
                AppLanguage.ES -> "Mejor que este mundo y todo lo que contiene"
                AppLanguage.ID -> "Lebih baik dari dunia dan seisinya"
                AppLanguage.FR -> "Meilleur que ce monde"
            }
            "dhuhr" -> when (lang) {
                AppLanguage.AR -> "الاستراحة السماوية"
                AppLanguage.EN -> "The celestial pause"
                AppLanguage.ES -> "La pausa celestial"
                AppLanguage.ID -> "Jeda surgawi"
                AppLanguage.FR -> "La pause céleste"
            }
            "asr" -> when (lang) {
                AppLanguage.AR -> "الصلاة الوسطى"
                AppLanguage.EN -> "The middle prayer"
                AppLanguage.ES -> "La oración intermedia"
                AppLanguage.ID -> "Salat pertengahan"
                AppLanguage.FR -> "La prière médiane"
            }
            "maghrib" -> when (lang) {
                AppLanguage.AR -> "شكر المساء"
                AppLanguage.EN -> "Evening gratitude"
                AppLanguage.ES -> "Gratitud de la tarde"
                AppLanguage.ID -> "Syukur petang"
                AppLanguage.FR -> "Gratitude du soir"
            }
            else -> when (lang) {
                AppLanguage.AR -> "خاتمة مضيئة"
                AppLanguage.EN -> "A luminous closing"
                AppLanguage.ES -> "Un cierre luminoso"
                AppLanguage.ID -> "Penutup penuh cahaya"
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
            AppLanguage.ES -> "$value seg divinos • $phrase"
            AppLanguage.ID -> "$value dtk ilahi • $phrase"
            AppLanguage.FR -> "$value sec divine • $phrase"
        }
    }

    fun sadaqaSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "حتى ابتسامة، أو مساعدة، أو كلمة طيبة"
        AppLanguage.EN -> "Even a smile, some help or a kind word"
        AppLanguage.ES -> "Incluso una sonrisa, una ayuda o una palabra amable"
        AppLanguage.ID -> "Bahkan senyuman, pertolongan, atau kata-kata baik"
        AppLanguage.FR -> "Même un sourire, de l'aide ou une parole bienveillante"
    }

    fun dhikrSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "درع روحي ليومك"
        AppLanguage.EN -> "A spiritual shield for the day"
        AppLanguage.ES -> "Un escudo espiritual para el día"
        AppLanguage.ID -> "Perisai spiritual untuk hari ini"
        AppLanguage.FR -> "Bouclier spirituel pour la journée"
    }

    fun goodDeedSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "للوالدين، للأقارب أو لأي إنسان"
        AppLanguage.EN -> "To your parents, relatives or a stranger"
        AppLanguage.ES -> "A tus padres, parientes o un desconocido"
        AppLanguage.ID -> "Untuk orang tuamu, kerabat, atau orang asing"
        AppLanguage.FR -> "Envers tes parents, tes proches ou un inconnu"
    }

    fun journalNotePlaceholder(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "اكتب وعياً أو توبة أو شكراً لهذا اليوم..."
        AppLanguage.EN -> "Write an insight, a repentance or a gratitude for today..."
        AppLanguage.ES -> "Escribe una idea, un arrepentimiento o una gratitud para hoy..."
        AppLanguage.ID -> "Tulis muhasabah, tobat, atau syukur untuk hari ini..."
        AppLanguage.FR -> "Écris une prise de conscience, un repentir ou une gratitude pour aujourd'hui..."
    }

    // ---------------------------------------------------------------------
    // Temporal converter extras
    // ---------------------------------------------------------------------

    fun enterEarthDuration(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "أدخل مدة أرضية:"
        AppLanguage.EN -> "ENTER AN EARTHLY DURATION:"
        AppLanguage.ES -> "INTRODUCE UNA DURACIÓN TERRESTRE:"
        AppLanguage.ID -> "MASUKKAN DURASI BUMI:"
        AppLanguage.FR -> "ENTRE UNE DURÉE TERRESTRE :"
    }

    fun enterDivineMinutes(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "أدخل دقائق إلهية:"
        AppLanguage.EN -> "ENTER DIVINE MINUTES:"
        AppLanguage.ES -> "INTRODUCE MINUTOS DIVINOS:"
        AppLanguage.ID -> "MASUKKAN MENIT ILAHI:"
        AppLanguage.FR -> "ENTRE DES MINUTES DIVINES :"
    }

    fun divineMinutesHint(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "دقائق إلهية (مثلاً: 1 = حوالي 253 يوماً)"
        AppLanguage.EN -> "Divine minutes (e.g. 1 = about 253 days)"
        AppLanguage.ES -> "Minutos divinos (p. ej. 1 = unos 253 días)"
        AppLanguage.ID -> "Menit ilahi (mis. 1 = sekitar 253 hari)"
        AppLanguage.FR -> "Minutes divines (ex : 1 = ~253 jours)"
    }

    fun exampleTitle(lang: AppLanguage, id: String): String = when (id) {
        "fajr" -> when (lang) {
            AppLanguage.AR -> "صلاة الفجر"
            AppLanguage.EN -> "Fajr Prayer"
            AppLanguage.ES -> "Oración del Fajr"
            AppLanguage.ID -> "Salat Fajr"
            AppLanguage.FR -> "Prière du Fajr"
        }
        "ramadan" -> when (lang) {
            AppLanguage.AR -> "شهر رمضان المبارك"
            AppLanguage.EN -> "The Blessed Month of Ramadan"
            AppLanguage.ES -> "El Mes Bendito de Ramadán"
            AppLanguage.ID -> "Bulan Suci Ramadan"
            AppLanguage.FR -> "Mois Béni de Ramadan"
        }
        "prophet" -> when (lang) {
            AppLanguage.AR -> "حياة النبي ﷺ"
            AppLanguage.EN -> "The Life of the Prophet ﷺ"
            AppLanguage.ES -> "La Vida del Profeta ﷺ"
            AppLanguage.ID -> "Kehidupan Nabi ﷺ"
            AppLanguage.FR -> "Vie du Prophète ﷺ"
        }
        "studies" -> when (lang) {
            AppLanguage.AR -> "كل سنوات دراستك"
            AppLanguage.EN -> "All your years of study"
            AppLanguage.ES -> "Todos tus años de estudio"
            AppLanguage.ID -> "Seluruh tahun belajarmu"
            AppLanguage.FR -> "Toute ta scolarité & études"
        }
        "qadr" -> when (lang) {
            AppLanguage.AR -> "ليلة القدر"
            AppLanguage.EN -> "The Night of Destiny (Laylatul Qadr)"
            AppLanguage.ES -> "La Noche del Destino (Láylat al-Qadr)"
            AppLanguage.ID -> "Malam Takdir (Lailatul Qadar)"
            AppLanguage.FR -> "Nuit du Destin (Laylatul Qadr)"
        }
        "scroll" -> when (lang) {
            AppLanguage.AR -> "التصفح على الشبكات الاجتماعية"
            AppLanguage.EN -> "Scrolling social media"
            AppLanguage.ES -> "Redes sociales"
            AppLanguage.ID -> "Gulir media sosial"
            AppLanguage.FR -> "Scroll sur réseaux sociaux"
        }
        else -> when (lang) {
            AppLanguage.AR -> "ليلة نوم"
            AppLanguage.EN -> "A night of sleep"
            AppLanguage.ES -> "Una noche de sueño"
            AppLanguage.ID -> "Malam tidur"
            AppLanguage.FR -> "Une nuit de sommeil"
        }
    }

    fun exampleEarth(lang: AppLanguage, id: String): String = when (id) {
        "fajr" -> when (lang) {
            AppLanguage.AR -> "5 دقائق"
            AppLanguage.EN -> "5 minutes"
            AppLanguage.ES -> "5 minutos"
            AppLanguage.ID -> "5 menit"
            AppLanguage.FR -> "5 minutes"
        }
        "ramadan" -> when (lang) {
            AppLanguage.AR -> "30 يوماً"
            AppLanguage.EN -> "30 days"
            AppLanguage.ES -> "30 días"
            AppLanguage.ID -> "30 hari"
            AppLanguage.FR -> "30 jours"
        }
        "prophet" -> when (lang) {
            AppLanguage.AR -> "63 سنة"
            AppLanguage.EN -> "63 years"
            AppLanguage.ES -> "63 años"
            AppLanguage.ID -> "63 tahun"
            AppLanguage.FR -> "63 ans"
        }
        "studies" -> when (lang) {
            AppLanguage.AR -> "20 سنة"
            AppLanguage.EN -> "20 years"
            AppLanguage.ES -> "20 años"
            AppLanguage.ID -> "20 tahun"
            AppLanguage.FR -> "20 ans"
        }
        "qadr" -> when (lang) {
            AppLanguage.AR -> "> 1000 شهر (83.3 سنة)"
            AppLanguage.EN -> "> 1000 months (83.3 yrs)"
            AppLanguage.ES -> "> 1000 meses (83.3 a)"
            AppLanguage.ID -> "> 1000 bulan (83.3 thn)"
            AppLanguage.FR -> "> 1000 mois (83.3 ans)"
        }
        "scroll" -> when (lang) {
            AppLanguage.AR -> "30 دقيقة"
            AppLanguage.EN -> "30 minutes"
            AppLanguage.ES -> "30 minutos"
            AppLanguage.ID -> "30 menit"
            AppLanguage.FR -> "30 minutes"
        }
        else -> when (lang) {
            AppLanguage.AR -> "8 ساعات"
            AppLanguage.EN -> "8 hours"
            AppLanguage.ES -> "8 horas"
            AppLanguage.ID -> "8 jam"
            AppLanguage.FR -> "8 heures"
        }
    }

    fun exampleDescription(lang: AppLanguage, id: String): String = when (id) {
        "fajr" -> when (lang) {
            AppLanguage.AR -> "خمس دقائق صغيرة على الأرض تمثل جزءاً من ألف من الثانية عند الله. ومع ذلك تضيء يومك كله."
            AppLanguage.EN -> "Five short minutes on earth are a thousandth of a second with Allah. Yet they light up your whole day."
            AppLanguage.ES -> "Cinco breves minutos en la tierra son una milésima de segundo con Allah. Sin embargo, iluminan todo tu día."
            AppLanguage.ID -> "Lima menit singkat di bumi hanyalah seperseribu detik bersama Allah. Namun ia menerangi seluruh harimu."
            AppLanguage.FR -> "5 petites minutes sur terre représentent un millième de seconde auprès d'Allah. Pourtant, elle illumine toute ta journée."
        }
        "ramadan" -> when (lang) {
            AppLanguage.AR -> "الصيام والقيام وروحانية شهر كامل لا تدوم سوى 7 ثوانٍ إلهية."
            AppLanguage.EN -> "The fasting, night prayers and fervour of a whole month last only 7 divine seconds."
            AppLanguage.ES -> "El ayuno, las oraciones nocturnas y el fervor de un mes entero duran solo 7 segundos divinos."
            AppLanguage.ID -> "Puasa, salat malam dan semangat sebulan penuh hanyalah 7 detik ilahi."
            AppLanguage.FR -> "Le jeûne, les prières nocturnes et la ferveur d'un mois entier ne durent que 7 secondes divines."
        }
        "prophet" -> when (lang) {
            AppLanguage.AR -> "حياة رسول الله ﷺ كلها، التي أنارت البشرية، دامت ساعة ونصف في الزمن الإلهي."
            AppLanguage.EN -> "The whole life of the Messenger of Allah ﷺ, which illuminated humanity, lasted 1h30 in divine time."
            AppLanguage.ES -> "Toda la vida del Mensajero de Allah ﷺ, que iluminó a la humanidad, duró 1h30 en tiempo divino."
            AppLanguage.ID -> "Seluruh hidup Rasul Allah ﷺ yang menerangi umat manusia hanyalah 1j30 dalam waktu ilahi."
            AppLanguage.FR -> "Toute la vie du Messager d'Allah ﷺ qui a illuminé l'humanité a duré 1h30 dans le temps divin."
        }
        "studies" -> when (lang) {
            AppLanguage.AR -> "عشرون سنة من الجهد والشهادات والمراجعة لا تزن حتى نصف ساعة إلهية."
            AppLanguage.EN -> "Twenty years of effort, diplomas and revision do not even weigh half a divine hour."
            AppLanguage.ES -> "Veinte años de esfuerzo, diplomas y revisiones no pesan ni media hora divina."
            AppLanguage.ID -> "Dua puluh tahun usaha, ijazah, dan belajar tak sampai setengah jam ilahi."
            AppLanguage.FR -> "Vingt années d'efforts, de diplômes et de révisions ne pèsent même pas une demi-heure divine."
        }
        "qadr" -> when (lang) {
            AppLanguage.AR -> "« خير من ألف شهر »: ليلة واحدة من العبادة على الأرض تتفوق على حياة إلهية كاملة من ساعتين!"
            AppLanguage.EN -> "« Better than a thousand months »: one single night of worship on earth surpasses a whole two-hour divine life!"
            AppLanguage.ES -> "« Mejor que mil meses »: ¡una sola noche de adoración en la tierra supera toda una vida divina de dos horas!"
            AppLanguage.ID -> "« Lebih baik dari seribu bulan »: satu malam ibadah di bumi melebihi seluruh kehidupan ilahi dua jam!"
            AppLanguage.FR -> "« Meilleure que 1000 mois » : Une seule nuit d'adoration sur Terre surpasse toute une vie divine de 2 heures !"
        }
        "scroll" -> when (lang) {
            AppLanguage.AR -> "جزء من ثانية إلهية محروق في الفراغ. احترس مما تزرع."
            AppLanguage.EN -> "A fraction of a divine second burned into the void. Beware of what you sow."
            AppLanguage.ES -> "Una fracción de segundo divino quemada en el vacío. Cuidado con lo que siembras."
            AppLanguage.ID -> "Sebagian kecil detik ilahi terbakar sia-sia. Hati-hati dengan apa yang kamu tanam."
            AppLanguage.FR -> "Une fraction de seconde divine brûlée dans le vide. Prends garde à ce que tu sèmes."
        }
        else -> when (lang) {
            AppLanguage.AR -> "كل ليلة، تخرج روحك عُشر ثانية إلهية قبل أن تعود."
            AppLanguage.EN -> "Every night your soul slips away for a tenth of a divine second before returning."
            AppLanguage.ES -> "Cada noche tu alma se escapa una décima de segundo divino antes de regresar."
            AppLanguage.ID -> "Setiap malam jiwamu pergi sepersepuluh detik ilahi sebelum kembali."
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
        AppLanguage.ES -> "Él te llama"
        AppLanguage.ID -> "Dia memanggilmu"
        AppLanguage.FR -> "Il t'appelle"
    }

    /** L'Invitation — the countdown card subtitle. */
    fun rendezVousLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الموعد"
        AppLanguage.EN -> "The Appointment"
        AppLanguage.ES -> "La Cita"
        AppLanguage.ID -> "Janji Temu"
        AppLanguage.FR -> "Le Rendez-Vous"
    }

    /** Le Vide — the single word shown while the prayer window is open. */
    fun leVideWord(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "اذهب."
        AppLanguage.EN -> "Go."
        AppLanguage.ES -> "Ve."
        AppLanguage.ID -> "Pergilah."
        AppLanguage.FR -> "Va."
    }

    /** Le Vide — the quiet line under the word. */
    fun leVideSubtitle(lang: AppLanguage, prayerFr: String, prayerAr: String): String = when (lang) {
        AppLanguage.AR -> "بدأ موعد $prayerAr. ينسحب التطبيق خلال صلاتك."
        AppLanguage.EN -> "The $prayerFr appointment has begun. The app steps aside during your prayer."
        AppLanguage.ES -> "La cita de $prayerFr ha comenzado. La app se aparta durante tu oración."
        AppLanguage.ID -> "Waktu $prayerFr telah masuk. Aplikasi menepi selama salatmu."
        AppLanguage.FR -> "Le Rendez-Vous de $prayerFr a commencé. L'application se retire pendant ta prière."
    }

    /** Le Vide — the discreet way back in. */
    fun leVideLater(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "لاحقًا"
        AppLanguage.EN -> "Later"
        AppLanguage.ES -> "Más tarde"
        AppLanguage.ID -> "Nanti"
        AppLanguage.FR -> "Plus tard"
    }

    /** La Minute Précieuse — title (the assise that follows the salam). */
    fun preciousMinuteTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الدقيقة الثمينة"
        AppLanguage.EN -> "The Precious Minute"
        AppLanguage.ES -> "El Minuto Precioso"
        AppLanguage.ID -> "Menit Berharga"
        AppLanguage.FR -> "La Minute Précieuse"
    }

    /** La Minute Précieuse — the invitation to stay seated. */
    fun preciousMinuteBody(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "لا ترحل بعدُ. ستّون ثانية."
        AppLanguage.EN -> "Do not leave yet. Sixty seconds."
        AppLanguage.ES -> "No te vayas todavía. Sesenta segundos."
        AppLanguage.ID -> "Jangan pergi dulu. Enam puluh detik."
        AppLanguage.FR -> "Ne pars pas encore. Soixante secondes."
    }

    /** La Minute Précieuse — shown when the sixty seconds are over. */
    fun preciousMinuteDone(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "آمين."
        AppLanguage.EN -> "Ameen."
        AppLanguage.ES -> "Amín."
        AppLanguage.ID -> "Amin."
        AppLanguage.FR -> "Amine."
    }

    /** La Minute Précieuse — discreet skip. */
    fun preciousMinuteSkip(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تخطٍّ"
        AppLanguage.EN -> "Skip"
        AppLanguage.ES -> "Omitir"
        AppLanguage.ID -> "Lewati"
        AppLanguage.FR -> "Passer"
    }

    /** La Question du Goût — asked once the minute is over. */
    fun tasteQuestion(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "صلاتك اليوم، ما طعمها؟"
        AppLanguage.EN -> "Today's prayer — what did it taste like?"
        AppLanguage.ES -> "La oración de hoy — ¿a qué supo?"
        AppLanguage.ID -> "Salat hari ini — bagaimana rasanya?"
        AppLanguage.FR -> "Ta prière d'aujourd'hui, quel goût ?"
    }

    fun tasteDouce(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "حلوة"
        AppLanguage.EN -> "Sweet"
        AppLanguage.ES -> "Dulce"
        AppLanguage.ID -> "Manis"
        AppLanguage.FR -> "Douce"
    }

    fun tasteLourde(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "ثقيلة"
        AppLanguage.EN -> "Heavy"
        AppLanguage.ES -> "Pesado"
        AppLanguage.ID -> "Berat"
        AppLanguage.FR -> "Lourde"
    }

    fun tasteAbsente(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "غائبة"
        AppLanguage.EN -> "Absent"
        AppLanguage.ES -> "Ausente"
        AppLanguage.ID -> "Tidak hadir"
        AppLanguage.FR -> "Absente"
    }

    fun tasteGoutee(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "مُذاقة"
        AppLanguage.EN -> "Tasted"
        AppLanguage.ES -> "Saboreado"
        AppLanguage.ID -> "Dirasakan"
        AppLanguage.FR -> "Goûtée"
    }

    /** La Fenêtre d'or — the last third of the night. */
    fun goldenWindowBadge(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "ساعة الإجابة — الباب مفتوح على مصراعيه"
        AppLanguage.EN -> "Hour of response — the door is wide open"
        AppLanguage.ES -> "Hora de respuesta — la puerta está abierta de par en par"
        AppLanguage.ID -> "Waktu mustajab — pintu terbuka lebar"
        AppLanguage.FR -> "Heure d'exaucement — la porte est grande ouverte"
    }

    // ─────────────────────────────────────────────────────────────
    //  LE VOYAGE DE LA PRIÈRE (Saison 1 — les 10 stations)
    // ─────────────────────────────────────────────────────────────

    /** Bottom navigation label for the Journey screen. */
    fun navJourney(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الرحلة"
        AppLanguage.EN -> "Journey"
        AppLanguage.ES -> "Viaje"
        AppLanguage.ID -> "Perjalanan"
        AppLanguage.FR -> "Voyage"
    }

    fun journeyTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "رحلة الصلاة"
        AppLanguage.EN -> "The Prayer Journey"
        AppLanguage.ES -> "El Viaje de la Oración"
        AppLanguage.ID -> "Perjalanan Salat"
        AppLanguage.FR -> "Le Voyage de la Prière"
    }

    fun journeySubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "عشر محطات لقلبك قبل جسدك. محطة واحدة قبل كل صلاة — ثم اذهب وصلِّ بلا هاتف."
        AppLanguage.EN -> "Ten stations for your heart before your body. One station before each prayer — then go and pray without your phone."
        AppLanguage.ES -> "Diez estaciones para tu corazón antes que para tu cuerpo. Una estación antes de cada oración — luego ve a orar sin tu teléfono."
        AppLanguage.ID -> "Sepuluh perhentian untuk hatimu sebelum jasadmu. Satu perhentian sebelum setiap salat — lalu salatlah tanpa ponsel."
        AppLanguage.FR -> "Dix stations pour ton cœur avant ton corps. Une station avant chaque prière — puis va prier sans ton téléphone."
    }

    /** "Station 3 of 10" progress label. */
    fun journeyStationProgress(lang: AppLanguage, index: Int, total: Int): String = when (lang) {
        AppLanguage.AR -> "المحطة $index من $total"
        AppLanguage.EN -> "Station $index of $total"
        AppLanguage.ES -> "Estación $index de $total"
        AppLanguage.ID -> "Perhentian $index dari $total"
        AppLanguage.FR -> "Station $index sur $total"
    }

    fun journeyListen(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "استمع قبل الصلاة"
        AppLanguage.EN -> "Listen before praying"
        AppLanguage.ES -> "Escuchar antes de orar"
        AppLanguage.ID -> "Dengar sebelum salat"
        AppLanguage.FR -> "Écouter avant de prier"
    }

    fun journeyPause(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "إيقاف مؤقت"
        AppLanguage.EN -> "Pause"
        AppLanguage.ES -> "Pausa"
        AppLanguage.ID -> "Jeda"
        AppLanguage.FR -> "Pause"
    }

    /** Shown when the audio of a station has not been recorded yet. */
    fun journeyComingSoon(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "التسجيل قريبًا"
        AppLanguage.EN -> "Recording coming soon"
        AppLanguage.ES -> "Grabación próximamente"
        AppLanguage.ID -> "Rekaman segera hadir"
        AppLanguage.FR -> "Enregistrement à venir"
    }

    /** Prefix before the classical source of a station. */
    fun journeySourcePrefix(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "المصدر"
        AppLanguage.EN -> "Source"
        AppLanguage.ES -> "Fuente"
        AppLanguage.ID -> "Sumber"
        AppLanguage.FR -> "Source"
    }

    // ─────────────────────────────────────────────────────────────
    //  LES DERNIÈRES NUITS (Ramadan — le climax annuel)
    // ─────────────────────────────────────────────────────────────

    fun lastNightsTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الليالي العشر الأخيرة"
        AppLanguage.EN -> "The Last Ten Nights"
        AppLanguage.ES -> "Las Diez Últimas Noches"
        AppLanguage.ID -> "Sepuluh Malam Terakhir"
        AppLanguage.FR -> "Les Dix Dernières Nuits"
    }

    fun lastNightsSeasonCardTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "موسم رمضان"
        AppLanguage.EN -> "Ramadan Season"
        AppLanguage.ES -> "Temporada de Ramadán"
        AppLanguage.ID -> "Musim Ramadan"
        AppLanguage.FR -> "Saison Ramadan"
    }

    fun lastNightsSeasonCardBody(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "التمس ليلة القدر في العشر الأواخر — ليلة خير من ألف شهر."
        AppLanguage.EN -> "Seek the Night of Decree in the last ten nights — a night better than a thousand months."
        AppLanguage.ES -> "Busca la Noche del Decreto en las diez últimas noches — una noche mejor que mil meses."
        AppLanguage.ID -> "Cari Malam Ketetapan pada sepuluh malam terakhir — malam yang lebih baik dari seribu bulan."
        AppLanguage.FR -> "Cherche la Nuit du Destin dans les dix dernières nuits — une nuit meilleure que mille mois."
    }

    fun lastNightsOpen(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "افتح الموسم"
        AppLanguage.EN -> "Open the season"
        AppLanguage.ES -> "Abrir la temporada"
        AppLanguage.ID -> "Buka musimnya"
        AppLanguage.FR -> "Ouvrir la saison"
    }

    fun qadrVerseQuote(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "﴿لَيْلَةُ الْقَدْرِ خَيْرٌ مِنْ أَلْفِ شَهْرٍ﴾"
        AppLanguage.EN -> "\"The Night of Decree is better than a thousand months.\""
        AppLanguage.ES -> "\"La Noche del Decreto es mejor que mil meses.\""
        AppLanguage.ID -> "\"Malam Ketetapan lebih baik daripada seribu bulan.\""
        AppLanguage.FR -> "« La Nuit du Destin est meilleure que mille mois. »"
    }

    fun qadrVerseRef(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "سورة القدر 97: 3"
        AppLanguage.EN -> "Quran 97:3"
        AppLanguage.ES -> "Corán 97:3"
        AppLanguage.ID -> "Quran 97:3"
        AppLanguage.FR -> "Coran 97:3"
    }

    fun lastNightsBridgeTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "ليلة واحدة = حياتك كلها"
        AppLanguage.EN -> "One night = your whole life"
        AppLanguage.ES -> "Una noche = toda tu vida"
        AppLanguage.ID -> "Satu malam = seluruh hidupmu"
        AppLanguage.FR -> "Une nuit = ta vie entière"
    }

    fun lastNightsBridgeBody(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "ألف شهر ≈ 83 سنة و4 أشهر — وهي مدة حياة كاملة بمقياس الساعتين عند الله. ليلة واحدة صادقة قد تزن أكثر من فيلم حياتك بأكمله."
        AppLanguage.EN -> "A thousand months ≈ 83 years and 4 months — an entire life on the scale of the two hours with God. One sincere night can outweigh your whole film."
        AppLanguage.ES -> "Mil meses ≈ 83 años y 4 meses — toda una vida en la escala de las dos horas con Dios. Una sola noche sincera puede pesar más que toda tu historia."
        AppLanguage.ID -> "Seribu bulan ≈ 83 tahun 4 bulan — satu umur utuh dalam skala dua jam bersama Tuhan. Satu malam yang tulus bisa melebihi seluruh kisahmu."
        AppLanguage.FR -> "Mille mois ≈ 83 ans et 4 mois — une vie entière à l'échelle des deux heures auprès de Dieu. Une seule nuit sincère peut peser plus que tout ton film."
    }

    fun seekLaylatHadith(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "«التمسوا ليلة القدر في الوتر من العشر الأواخر»"
        AppLanguage.EN -> "\"Seek the Night of Decree in the odd nights of the last ten.\""
        AppLanguage.ES -> "\"Busca la Noche del Decreto en las noches impares de las diez últimas.\""
        AppLanguage.ID -> "\"Cari Malam Ketetapan pada malam-malam ganjil sepuluh terakhir.\""
        AppLanguage.FR -> "« Cherchez la Nuit du Destin dans les nuits impaires des dix dernières. »"
    }

    fun seekLaylatRef(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "رواه البخاري"
        AppLanguage.EN -> "Reported by al-Bukhari"
        AppLanguage.ES -> "Transmitido por al-Bujari"
        AppLanguage.ID -> "Riwayat al-Bukhari"
        AppLanguage.FR -> "Rapporté par al-Bukhârî"
    }

    fun oddNightBadge(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "ليلة وتر"
        AppLanguage.EN -> "Odd night"
        AppLanguage.ES -> "Noche impar"
        AppLanguage.ID -> "Malam ganjil"
        AppLanguage.FR -> "Nuit impaire"
    }

    fun nightLabel(lang: AppLanguage, night: Int): String = when (lang) {
        AppLanguage.AR -> "الليلة $night"
        AppLanguage.EN -> "Night $night"
        AppLanguage.ES -> "Noche $night"
        AppLanguage.ID -> "Malam $night"
        AppLanguage.FR -> "Nuit $night"
    }

    fun vigilButton(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "ساهد هذه الليلة"
        AppLanguage.EN -> "Keep vigil tonight"
        AppLanguage.ES -> "Haz vigilia esta noche"
        AppLanguage.ID -> "Qiyam malam ini"
        AppLanguage.FR -> "Veiller cette nuit"
    }

    fun vigilHint(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "اسجد، ادعُ، اقرأ… وابقِ الهاتف صامتًا."
        AppLanguage.EN -> "Prostrate, supplicate, read… and let the phone stay silent."
        AppLanguage.ES -> "Postrarse, suplicar, leer… y dejar el teléfono en silencio."
        AppLanguage.ID -> "Sujud, berdoa, membaca… dan biarkan ponsel senyap."
        AppLanguage.FR -> "Prosterne-toi, invoque, lis… et laisse le téléphone se taire."
    }

    fun vigilExit(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "إنهاء السَّهَر"
        AppLanguage.EN -> "End the vigil"
        AppLanguage.ES -> "Terminar la vigilia"
        AppLanguage.ID -> "Akhiri qiyam"
        AppLanguage.FR -> "Quitter la veille"
    }

    fun vigilDhikr(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "سُبْحَانَ اللَّهِ • الْحَمْدُ لِلَّهِ • اللَّهُ أَكْبَرُ"
        AppLanguage.EN -> "سُبْحَانَ اللَّهِ • الْحَمْدُ لِلَّهِ • اللَّهُ أَكْبَرُ"
        AppLanguage.ES -> "سُبْحَانَ اللَّهِ • الْحَمْدُ لِلَّهِ • اللَّهُ أَكْبَرُ"
        AppLanguage.ID -> "سُبْحَانَ اللَّهِ • الْحَمْدُ لِلَّهِ • اللَّهُ أَكْبَرُ"
        AppLanguage.FR -> "سُبْحَانَ اللَّهِ • الْحَمْدُ لِلَّهِ • اللَّهُ أَكْبَرُ"
    }

    fun inProgressBadge(lang: AppLanguage, night: Int): String = when (lang) {
        AppLanguage.AR -> "جارية — الليلة $night"
        AppLanguage.EN -> "Ongoing — night $night"
        AppLanguage.ES -> "En curso — noche $night"
        AppLanguage.ID -> "Berlangsung — malam $night"
        AppLanguage.FR -> "En cours — nuit $night"
    }

    // ─────────────────────────────────────────────────────────────
    //  LE LIVRE — La Prière Goûtée (Sprint 4 : l'épisode en livre)
    // ─────────────────────────────────────────────────────────────

    /** Clock-header book button (A11y text). */
    fun bookOpenLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الكتاب — صلاة الذوق"
        AppLanguage.EN -> "The Book — The Tasted Prayer"
        AppLanguage.ES -> "El Libro — La Oración Saboreada"
        AppLanguage.ID -> "Kitab — Salat yang Dirasakan"
        AppLanguage.FR -> "Le Livre — La Prière Goûtée"
    }

    fun bookScreenTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "صلاة الذوق"
        AppLanguage.EN -> "The Tasted Prayer"
        AppLanguage.ES -> "La Oración Saboreada"
        AppLanguage.ID -> "Salat yang Dirasakan"
        AppLanguage.FR -> "La Prière Goûtée"
    }

    fun bookScreenSubtitle(lang: AppLanguage, total: Int): String = when (lang) {
        AppLanguage.AR -> "كتاب الحلقة — $total فصلًا مرتبطًا بالرحلة"
        AppLanguage.EN -> "The episode's book — $total chapters woven into the Journey"
        AppLanguage.ES -> "El libro del episodio — $total capítulos tejidos en el Viaje"
        AppLanguage.ID -> "Kitab episode — $total bab yang dianyam dalam Perjalanan"
        AppLanguage.FR -> "Le livre de l'épisode — $total chapitres reliés au Voyage"
    }

    fun bookIndexTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الفهرس"
        AppLanguage.EN -> "Contents"
        AppLanguage.ES -> "Índice"
        AppLanguage.ID -> "Daftar Isi"
        AppLanguage.FR -> "Sommaire"
    }

    fun bookBackToIndex(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "العودة إلى الفهرس"
        AppLanguage.EN -> "Back to the contents"
        AppLanguage.ES -> "Volver al índice"
        AppLanguage.ID -> "Kembali ke daftar isi"
        AppLanguage.FR -> "Retour au sommaire"
    }

    /** Opens the chapter anchor inside the original 2 h 12 min episode. */
    fun bookListenPassage(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الاستماع إلى المقطع"
        AppLanguage.EN -> "Listen to the passage"
        AppLanguage.ES -> "Escuchar el pasaje"
        AppLanguage.ID -> "Dengar cuplikannya"
        AppLanguage.FR -> "Écouter le passage"
    }

    /** Chapter label ("Chapitre 7"). */
    fun bookChapterN(lang: AppLanguage, n: Int): String = when (lang) {
        AppLanguage.AR -> "الفصل $n"
        AppLanguage.EN -> "Chapter $n"
        AppLanguage.ES -> "Capítulo $n"
        AppLanguage.ID -> "Bab $n"
        AppLanguage.FR -> "Chapitre $n"
    }

    fun bookPrologue(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "المقدمة"
        AppLanguage.EN -> "Prologue"
        AppLanguage.ES -> "Prólogo"
        AppLanguage.ID -> "Prolog"
        AppLanguage.FR -> "Prologue"
    }

    fun bookEpilogue(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الخاتمة"
        AppLanguage.EN -> "Epilogue"
        AppLanguage.ES -> "Epílogo"
        AppLanguage.ID -> "Epilog"
        AppLanguage.FR -> "Épilogue"
    }

    fun bookAppendice(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الملحق"
        AppLanguage.EN -> "Appendix"
        AppLanguage.ES -> "Apéndice"
        AppLanguage.ID -> "Lampiran"
        AppLanguage.FR -> "Appendice"
    }

    fun bookPrev(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الفصل السابق"
        AppLanguage.EN -> "Previous chapter"
        AppLanguage.ES -> "Capítulo anterior"
        AppLanguage.ID -> "Bab sebelumnya"
        AppLanguage.FR -> "Chapitre précédent"
    }

    fun bookNext(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الفصل التالي"
        AppLanguage.EN -> "Next chapter"
        AppLanguage.ES -> "Capítulo siguiente"
        AppLanguage.ID -> "Bab berikutnya"
        AppLanguage.FR -> "Chapitre suivant"
    }

    /** Chip inside a Voyage station card, opening its book chapter. */
    fun bookJourneyChip(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "اقرأ الفصل"
        AppLanguage.EN -> "Read the chapter"
        AppLanguage.ES -> "Leer el capítulo"
        AppLanguage.ID -> "Baca babnya"
        AppLanguage.FR -> "Lire le chapitre"
    }

    /** Chip linking a chapter to its Voyage station. */
    fun bookStationChip(lang: AppLanguage, station: Int): String = when (lang) {
        AppLanguage.AR -> "محطة $station من الرحلة"
        AppLanguage.EN -> "Journey station $station"
        AppLanguage.ES -> "Estación $station del viaje"
        AppLanguage.ID -> "Perhentian $station Perjalanan"
        AppLanguage.FR -> "Station $station du Voyage"
    }

    /** The special chapter 22 — the Fatiha dialogue hadith qudsi. */
    fun bookFatihaChip(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "حوار الفاتحة"
        AppLanguage.EN -> "The Fatiha dialogue"
        AppLanguage.ES -> "El diálogo de la Fatiha"
        AppLanguage.ID -> "Dialog Al-Fatiha"
        AppLanguage.FR -> "Le dialogue de la Fatiha"
    }

    // ── Al-Fatiha dialogue screen (Sprint 5, book chapter 22) ────────────
    // Only chrome lives here; the verse/hadith text itself is data inside
    // FatihaLibrary (the JourneyStation convention).

    fun fatihaTopBarLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "حوار الفاتحة"
        AppLanguage.EN -> "THE FATIHA DIALOGUE"
        AppLanguage.ES -> "EL DIÁLOGO DE LA FATIHA"
        AppLanguage.ID -> "DIALOG AL-FATIHA"
        AppLanguage.FR -> "LE DIALOGUE DE LA FATIHA"
    }

    fun fatihaStationLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "المحطة 06 · الحوار · قمّة الصلاة المذوقة"
        AppLanguage.EN -> "Station 06 · The Dialogue · The summit of the tasted prayer"
        AppLanguage.ES -> "Estación 06 · El Diálogo · La cumbre de la oración saboreada"
        AppLanguage.ID -> "Perhentian 06 · Dialog · Puncak salat yang dirasakan"
        AppLanguage.FR -> "Station 06 · الحوار · Le sommet de la prière goûtée"
    }

    fun fatihaTitlePart1(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "Al-Fatiha ليست تلاوة."
        AppLanguage.EN -> "Al-Fatiha is not a recitation."
        AppLanguage.ES -> "Al-Fatiha no es una recitación."
        AppLanguage.ID -> "Al-Fatiha bukan sekadar bacaan."
        AppLanguage.FR -> "Al-Fatiha n'est pas une récitation."
    }

    fun fatihaTitlePart2(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "بل حوار."
        AppLanguage.EN -> "It is a dialogue."
        AppLanguage.ES -> "Es un diálogo."
        AppLanguage.ID -> "Ini adalah dialog."
        AppLanguage.FR -> "C'est un dialogue."
    }

    fun fatihaSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "حديث قدسي — Muslim رقم 395 : « شَقَّتُ الصَّلاةَ بَيْنِي وَبَيْنَ عَبْدِي نِصْفَيْنِ ». تقدّم آيةً بآية، وبعد كلِّ آية توقّف فنفساً — واسمع الجواب."
        AppLanguage.EN -> "Hadith qudsi — Muslim n°395: \u201CI have divided the prayer between Me and My servant, in two halves.\u201D Move forward verse by verse. After each one, pause — and listen to the answer."
        AppLanguage.ES -> "Hadiz qudsi — Muslim n.º 395: \u201CHe dividido la oración entre Mi siervo y Yo, en dos mitades.\u201D Avanza versículo a versículo. Tras cada uno, haz una pausa — y escucha la respuesta."
        AppLanguage.ID -> "Hadis qudsi — Muslim no. 395: \u201CAku membagi salat antara Aku dan hamba-Ku menjadi dua bagian.\u201D Lanjutkan ayat demi ayat. Setelah setiap ayat, berhentilah — dan dengarkan jawabannya."
        AppLanguage.FR -> "Hadith qudsi — Muslim n°395 : « J'ai partagé la prière entre Moi et Mon serviteur, en deux moitiés. » Avance verset par verset. Après chacun, fais une pause — et écoute la réponse."
    }

    fun fatihaIntroTranslit(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "Bismillahir-Rahmanir-Rahim"
        AppLanguage.EN -> "Bismillahir-Rahmanir-Rahim — In the name of Allah, the Most Compassionate, the Most Merciful."
        AppLanguage.ES -> "Bismillahir-Rahmanir-Rahim — En el nombre de Allah, el Compasivo, el Misericordioso."
        AppLanguage.ID -> "Bismillahir-Rahmanir-Rahim — Dengan nama Allah Yang Maha Pengasih lagi Maha Penyayang."
        AppLanguage.FR -> "Bismillahir-Rahmanir-Rahim — Au nom d'Allah, le Tout Miséricordieux, le Très Miséricordieux."
    }

    fun fatihaIntroBody(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "أنت تطرق الباب. بعد لحظة ستتكلّم — وهو سيجيب، عند كلِّ آية. من أدرك ذلك لم يعد يُتلو الفاتحة كما كان."
        AppLanguage.EN -> "You are knocking at the door. In a moment you will speak — and He will answer, at every verse. Whoever grasps this never recites Al-Fatiha the same way again."
        AppLanguage.ES -> "Estás llamando a la puerta. En un momento hablarás — y Él responderá, en cada versículo. Quien comprende esto ya no recita Al-Fatiha igual."
        AppLanguage.ID -> "Kau mengetuk pintu. Sebentar lagi kau bicara — dan Dia menjawab di setiap ayat. Siapa memahami ini tak akan membaca Al-Fatiha dengan cara yang sama."
        AppLanguage.FR -> "Tu frappes à la porte. Dans un instant, tu vas parler — et Il va répondre, à chaque verset. Celui qui comprend cela ne récite plus jamais Al-Fatiha comme avant."
    }

    fun fatihaStart(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "ابدأ الحوار"
        AppLanguage.EN -> "Begin the dialogue"
        AppLanguage.ES -> "Comenzar el diálogo"
        AppLanguage.ID -> "Mulai dialog"
        AppLanguage.FR -> "Commencer le dialogue"
    }

    fun fatihaYouSay(lang: AppLanguage, verse: Int): String = when (lang) {
        AppLanguage.AR -> "تقول — آية $verse من 7"
        AppLanguage.EN -> "You say — verse $verse of 7"
        AppLanguage.ES -> "Dices — versículo $verse de 7"
        AppLanguage.ID -> "Kau membaca — ayat $verse dari 7"
        AppLanguage.FR -> "Tu dis — verset $verse / 7"
    }

    fun fatihaHeAnswers(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "هُوَ يُجِيبُ"
        AppLanguage.EN -> "He answers"
        AppLanguage.ES -> "Él responde"
        AppLanguage.ID -> "Dia menjawab"
        AppLanguage.FR -> "Il répond"
    }

    fun fatihaPauseNote(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "ولا تَعْجَلْ الآن. أبْقَ نَفَساً في هذا الجواب قبل الآية التالية — هنا بالضبط تُذوق الصلاة."
        AppLanguage.EN -> "Now, do not rush. Stay one breath inside this answer before the next verse — that is precisely where the prayer is tasted."
        AppLanguage.ES -> "Ahora, no te apresures. Quédate un aliento dentro de esta respuesta antes del próximo versículo — es ahí precisamente donde se saborea la oración."
        AppLanguage.ID -> "Sekarang, jangan tergesa. Tahan satu napas dalam jawaban ini sebelum ayat berikutnya — di situlah tepatnya salat dirasakan."
        AppLanguage.FR -> "Maintenant, ne cours pas. Reste une respiration dans cette réponse avant le verset suivant — c'est exactement là que se goûte la prière."
    }

    fun fatihaBack(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "رجوع"
        AppLanguage.EN -> "Back"
        AppLanguage.ES -> "Atrás"
        AppLanguage.ID -> "Kembali"
        AppLanguage.FR -> "Retour"
    }

    fun fatihaNextVerse(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الآية التالية"
        AppLanguage.EN -> "Next verse"
        AppLanguage.ES -> "Versículo siguiente"
        AppLanguage.ID -> "Ayat berikutnya"
        AppLanguage.FR -> "Verset suivant"
    }

    fun fatihaSayAmin(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "قل آمين"
        AppLanguage.EN -> "Say Âmîn"
        AppLanguage.ES -> "Di Âmîn"
        AppLanguage.ID -> "Ucapkan Âmîn"
        AppLanguage.FR -> "Dire Âmin"
    }

    fun fatihaFinaleQuote(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "﴿ وَلِعَبْدِي مَا سَأَلَ ﴾"
        AppLanguage.EN -> "\u201CAnd My servant shall have what he asked for.\u201D"
        AppLanguage.ES -> "\u201CY Mi siervo tendrá lo que pidió.\u201D"
        AppLanguage.ID -> "\u201CDan hamba-Ku akan mendapatkan apa yang dimintanya.\u201D"
        AppLanguage.FR -> "« Et Mon serviteur aura ce qu'il a demandé. »"
    }

    fun fatihaFinaleBody(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "عشتَ للتوّ في دقيقةٍ ما يُعلّمه الحلقة في سبعَ عَشَرَ دقيقة: الفاتحة مكالمةٌ مع الله. في المرة القادمة التي تُتلائها واقفاً، دَعِ كلَّ جوابٍ أن يأتي."
        AppLanguage.EN -> "You have just lived in one minute what the episode teaches in seventeen: Al-Fatiha is a conversation. Next time you recite it standing, let every answer arrive."
        AppLanguage.ES -> "Acabas de vivir en un minuto lo que el episodio enseña en diecisiete: Al-Fatiha es una conversación. La próxima vez que la recites de pie, deja llegar cada respuesta."
        AppLanguage.ID -> "Kau baru merasakan dalam satu menit apa yang diajarkan episode dalam tujuh belas: Al-Fatiha adalah percakapan. Saat membacanya berdiri, biarkan setiap jawaban datang."
        AppLanguage.FR -> "Tu viens de vivre en une minute ce que l'épisode enseigne en dix-sept : la Fatiha est une conversation. La prochaine fois que tu la réciteras debout, laisse chaque réponse arriver."
    }

    fun fatihaRestart(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "أعد الحوار"
        AppLanguage.EN -> "Restart the dialogue"
        AppLanguage.ES -> "Reiniciar el diálogo"
        AppLanguage.ID -> "Ulangi dialog"
        AppLanguage.FR -> "Recommencer le dialogue"
    }

    fun fatihaAdvice1Title(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "لا تُتلو"
        AppLanguage.EN -> "Don't recite"
        AppLanguage.ES -> "No recites"
        AppLanguage.ID -> "Jangan baca"
        AppLanguage.FR -> "Ne récite pas"
    }

    fun fatihaAdvice1Desc(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "حوار. جملة، جواب، نَفَس."
        AppLanguage.EN -> "Dialogue. One line, one answer, one breath."
        AppLanguage.ES -> "Diálogo. Una línea, una respuesta, un aliento."
        AppLanguage.ID -> "Dialog. Satu ayat, satu jawaban, satu helaan napas."
        AppLanguage.FR -> "Dialogue. Une phrase, une réponse, une respiration."
    }

    fun fatihaAdvice2Title(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "اسمع الجواب"
        AppLanguage.EN -> "Hear the answer"
        AppLanguage.ES -> "Escuchar la respuesta"
        AppLanguage.ID -> "Dengar jawabannya"
        AppLanguage.FR -> "Entends la réponse"
    }

    fun fatihaAdvice2Desc(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« حَمِدَنِي عَبْدِي » — هو عنك يحدّث."
        AppLanguage.EN -> "\u201CMy servant has praised Me\u201D — He is speaking about you."
        AppLanguage.ES -> "\u201CMi siervo Me ha alabado\u201D — Él habla de ti."
        AppLanguage.ID -> "\u201CHamba-Ku telah memuji-Ku\u201D — Dia berbicara tentangmu."
        AppLanguage.FR -> "« Mon serviteur M'a loué » — c'est de toi qu'Il parle."
    }

    fun fatihaAdvice3Title(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "عَقِّل بآمين"
        AppLanguage.EN -> "End with Âmîn"
        AppLanguage.ES -> "Terminar con Âmîn"
        AppLanguage.ID -> "Akhiri dengan Âmîn"
        AppLanguage.FR -> "Termine par Âmin"
    }

    fun fatihaAdvice3Desc(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الملائكة تقول آمين معك. لا تُمِحْ خائِناً."
        AppLanguage.EN -> "The angels say Âmîn with you. Do not murmur it while already fleeing."
        AppLanguage.ES -> "Los ángeles dicen Âmîn contigo. No lo murmures mientras ya huyes."
        AppLanguage.ID -> "Malaikat mengucapkan Âmîn bersamamu. Jangan membisikkannya sambil bergegas pergi."
        AppLanguage.FR -> "Les anges disent Âmin avec toi. Ne le murmure pas en fuyant."
    }

    // ── Logo splash (opening video) ─────────────────────────────────────
    // The calligraphy "Prayer of Taste" intro that plays on every app open.

    fun splashEnterHint(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "اضغط للدخول"
        AppLanguage.EN -> "Tap to enter"
        AppLanguage.ES -> "Toca para introducir"
        AppLanguage.ID -> "Ketuk untuk mengisi"
        AppLanguage.FR -> "Appuyez pour entrer"
    }

    fun bookNoteTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "ملاحظة الناشر"
        AppLanguage.EN -> "Publisher's note"
        AppLanguage.ES -> "Nota de la editorial"
        AppLanguage.ID -> "Catatan Penerbit"
        AppLanguage.FR -> "Note du libraire"
    }

    // ─────────────────────────────────────────────────────────────────────
    //  PRESENCE RHYTHM — the US-facing skin (29/10/2026).
    //  Light strings deliberately avoid all religious vocabulary; the
    //  tradition names live one explicit unlock away (Explore hub).
    // ─────────────────────────────────────────────────────────────────────

    /** Light-skin name of a daily moment ("fajr" → "Morning Return"). */
    fun lightReturnName(lang: AppLanguage, key: String): String = when (key.lowercase()) {
        "fajr" -> when (lang) {
            AppLanguage.AR -> "عودة الصباح"
            AppLanguage.EN -> "Morning Return"
            AppLanguage.ES -> "Regreso de la mañana"
            AppLanguage.ID -> "Kembali pagi"
            AppLanguage.FR -> "Retour du matin"
        }
        "dhuhr" -> when (lang) {
            AppLanguage.AR -> "وقفة الظهيرة"
            AppLanguage.EN -> "Midday Pause"
            AppLanguage.ES -> "Pausa del mediodía"
            AppLanguage.ID -> "Jeda siang"
            AppLanguage.FR -> "Pause de midi"
        }
        "asr" -> when (lang) {
            AppLanguage.AR -> "إعادة العصر"
            AppLanguage.EN -> "Afternoon Reset"
            AppLanguage.ES -> "Reinicio de tarde"
            AppLanguage.ID -> "Reset sore"
            AppLanguage.FR -> "Reset d'après-midi"
        }
        "maghrib" -> when (lang) {
            AppLanguage.AR -> "حضور الغروب"
            AppLanguage.EN -> "Sunset Presence"
            AppLanguage.ES -> "Presencia al atardecer"
            AppLanguage.ID -> "Hadir senja"
            AppLanguage.FR -> "Présence au couchant"
        }
        "isha" -> when (lang) {
            AppLanguage.AR -> "عودة الليل"
            AppLanguage.EN -> "Night Return"
            AppLanguage.ES -> "Regreso nocturno"
            AppLanguage.ID -> "Kembali malam"
            AppLanguage.FR -> "Retour de nuit"
        }
        else -> prayerName(lang, key)
    }

    // ── Light home header ──

    fun usHomeTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "عودات اليوم"
        AppLanguage.EN -> "TODAY'S RETURNS"
        AppLanguage.ES -> "TUS REGRESOS DE HOY"
        AppLanguage.ID -> "KEMBALIAN HARI INI"
        AppLanguage.FR -> "RETOURS DU JOUR"
    }

    fun usHomeQuote(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "خمس لحظات حضور عند مفاصل اليوم"
        AppLanguage.EN -> "Five moments of presence, at the natural hinges of the day"
        AppLanguage.ES -> "Cinco momentos de presencia, en las bisagras del día"
        AppLanguage.ID -> "Lima momen hadir di engsela hari"
        AppLanguage.FR -> "Cinq moments de présence, aux charnières naturelles du jour"
    }

    /** Label replacing "Next prayer" in the light schedule card. */
    fun usNextReturnLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "العودة التالية"
        AppLanguage.EN -> "Next return"
        AppLanguage.ES -> "Próximo regreso"
        AppLanguage.ID -> "Kembali berikutnya"
        AppLanguage.FR -> "Prochain retour"
    }

    // ── Inner Weather ──

    fun innerWeatherTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "كيف تشعر الآن؟"
        AppLanguage.EN -> "How are you feeling right now?"
        AppLanguage.ES -> "¿Cómo te sientes ahora?"
        AppLanguage.ID -> "Bagaimana perasaanmu sekarang?"
        AppLanguage.FR -> "Comment vous sentez-vous, maintenant ?"
    }

    fun innerWeatherNote(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "لك وحدك — بلا تقييم ولا مشاركة."
        AppLanguage.EN -> "Yours alone — never scored, never shared."
        AppLanguage.ES -> "Solo tuyo — sin notas, sin compartir."
        AppLanguage.ID -> "Hanya untukmu — tanpa nilai, tanpa dibagikan."
        AppLanguage.FR -> "Pour vous seul — jamais noté, jamais partagé."
    }

    fun innerWeatherLabel(lang: AppLanguage, key: String): String = when (key) {
        "overwhelmed" -> when (lang) {
            AppLanguage.AR -> "مُثقل"
            AppLanguage.EN -> "Overwhelmed"
            AppLanguage.ES -> "Abrumado"
            AppLanguage.ID -> "Kewalahan"
            AppLanguage.FR -> "Submergé"
        }
        "anxious" -> when (lang) {
            AppLanguage.AR -> "متوتر"
            AppLanguage.EN -> "Anxious"
            AppLanguage.ES -> "Ansioso"
            AppLanguage.ID -> "Cemas"
            AppLanguage.FR -> "Anxieux"
        }
        "neutral" -> when (lang) {
            AppLanguage.AR -> "محايد"
            AppLanguage.EN -> "Neutral"
            AppLanguage.ES -> "Neutral"
            AppLanguage.ID -> "Netral"
            AppLanguage.FR -> "Neutre"
        }
        "calmer" -> when (lang) {
            AppLanguage.AR -> "أهدأ"
            AppLanguage.EN -> "Calmer"
            AppLanguage.ES -> "Más tranquilo"
            AppLanguage.ID -> "Lebih tenang"
            AppLanguage.FR -> "Plus calme"
        }
        "grounded" -> when (lang) {
            AppLanguage.AR -> "متماسك"
            AppLanguage.EN -> "Grounded"
            AppLanguage.ES -> "En tierra"
            AppLanguage.ID -> "Menetap"
            AppLanguage.FR -> "Ancré"
        }
        else -> key
    }

    // ── US Onboarding (5 screens) ──

    fun onboardingStepLabel(lang: AppLanguage, step: Int, total: Int): String = when (lang) {
        AppLanguage.AR -> "$step / $total"
        AppLanguage.EN -> "$step of $total"
        AppLanguage.ES -> "$step de $total"
        AppLanguage.ID -> "$step dari $total"
        AppLanguage.FR -> "$step sur $total"
    }

    fun onboardingFeelingTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "كيف كنت تشعر مؤخرًا؟"
        AppLanguage.EN -> "How have you been feeling lately?"
        AppLanguage.ES -> "¿Cómo te has sentido últimamente?"
        AppLanguage.ID -> "Bagaimana perasaanmu belakangan ini?"
        AppLanguage.FR -> "Comment vous sentez-vous en ce moment ?"
    }

    fun onboardingFeelingSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "لست وحدك. ملايين الناس يشعرون الآن بالتشتت أو القلق أو الفراغ."
        AppLanguage.EN -> "You're not alone. Millions feel scattered, anxious, or empty right now."
        AppLanguage.ES -> "No estás solo. Millones se sienten dispersos, ansiosos o vacíos ahora mismo."
        AppLanguage.ID -> "Kamu tidak sendirian. Jutaan orang merasa tercecer, cemas, atau hampa saat ini."
        AppLanguage.FR -> "Vous n'êtes pas seul. Des millions de personnes se sentent dispersées, anxieuses ou vides en ce moment."
    }

    fun onboardingFeelingOption(lang: AppLanguage, index: Int): String = when (index) {
        0 -> when (lang) {
            AppLanguage.AR -> "مُثقل / أفكار متسارعة"
            AppLanguage.EN -> "Overwhelmed / racing thoughts"
            AppLanguage.ES -> "Abrumado / mente acelerada"
            AppLanguage.ID -> "Kewalahan / pikiran berkejar"
            AppLanguage.FR -> "Submergé / pensées qui s'emballent"
        }
        1 -> when (lang) {
            AppLanguage.AR -> "وحيد حتى بين الناس"
            AppLanguage.EN -> "Lonely, even around people"
            AppLanguage.ES -> "Solo, incluso acompañado"
            AppLanguage.ID -> "Kesepian meski ada orang"
            AppLanguage.FR -> "Seul, même entouré"
        }
        2 -> when (lang) {
            AppLanguage.AR -> "خدر أو منفصل"
            AppLanguage.EN -> "Numb or disconnected"
            AppLanguage.ES -> "Entumecido o desconectado"
            AppLanguage.ID -> "Mati rasa atau terputus"
            AppLanguage.FR -> "Anesthésié ou déconnecté"
        }
        3 -> when (lang) {
            AppLanguage.AR -> "أتصفح دون شبع"
            AppLanguage.EN -> "Scrolling but never satisfied"
            AppLanguage.ES -> "Scroll infinito sin saciarme"
            AppLanguage.ID -> "Scroll terus tapi tak pernah puas"
            AppLanguage.FR -> "Je scrolle sans jamais être rassasié"
        }
        else -> when (lang) {
            AppLanguage.AR -> "أبحث عن معنى"
            AppLanguage.EN -> "Just looking for more meaning"
            AppLanguage.ES -> "Busco más sentido"
            AppLanguage.ID -> "Mencari lebih banyak makna"
            AppLanguage.FR -> "Je cherche plus de sens"
        }
    }

    fun onboardingInvitationTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "خمس وقفات قصيرة. إيقاع قديم."
        AppLanguage.EN -> "Five short pauses. One ancient rhythm."
        AppLanguage.ES -> "Cinco pausas breves. Un ritmo antiguo."
        AppLanguage.ID -> "Lima jeda singkat. Satu irama kuno."
        AppLanguage.FR -> "Cinq pauses courtes. Un rythme ancien."
    }

    fun onboardingInvitationBody(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "منذ 1400 سنة، يعود الناس خمس مرات كل يوم إلى شيء أكبر منهم. تؤكد الأبحاث الحديثة ما عرفته التقليد: الانتظام والطقس والجماعة تساعد الجهاز العصبي على الهدوء وتعيد إحساس المعنى.\n\nهذه ليست علاجًا نفسيًا. إنها عودة يومية."
        AppLanguage.EN -> "For 1,400 years, people have used five daily moments of return — structured, embodied, and directed toward something larger than themselves. Research now confirms what tradition always knew: regular ritual, presence, and community help regulate the nervous system and restore a sense of meaning.\n\nThis is not therapy. This is not a replacement for professional care. This is a daily return."
        AppLanguage.ES -> "Durante 1.400 años, las personas han usado cinco momentos diarios de regreso: estructurados, encarnados y dirigidos a algo más grande que ellas. La investigación confirma lo que la tradición siempre supo: el ritual regular, la presencia y la comunidad ayudan a regular el sistema nervioso y devuelven el sentido.\n\nEsto no es terapia. No reemplaza la atención profesional. Es un regreso diario."
        AppLanguage.ID -> "Selama 1.400 tahun, manusia memakai lima momen kembali setiap hari: terstruktur, dihayati, dan diarahkan pada sesuatu yang lebih besar. Riset kini mengonfirmasi yang tradisi selalu tahu: ritual rutin, kehadiran, dan komunitas menenangkan sistem saraf dan memulihkan makna.\n\nIni bukan terapi. Ini bukan pengganti pertolongan profesional. Ini kepulangan harian."
        AppLanguage.FR -> "Depuis 1 400 ans, des êtres humains utilisent cinq moments quotidiens de retour — structurés, incarnés, dirigés vers quelque chose de plus grand qu'eux. La recherche confirme ce que la tradition savait : le rituel régulier, la présence et la communauté apaisent le système nerveux et rendent le sens.\n\nCe n'est pas une thérapie. Ce n'est pas un remplacement des soignants. C'est un retour quotidien."
    }

    fun onboardingVoiceQuote(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« كنت أستيقظ الثالثة فجرًا وقلبي يسرع، وأتصفح حتى أشعر بأسوأ. هذه الوقفات الخمس منحتني إيقاعًا لم أكن أعلم أنني أحتاجه. لم تصلح كل شيء بين عشية وضحاها، لكنها أعطتني مكانًا أعود إليه. »"
        AppLanguage.EN -> "\u201CI used to wake up at 3am with my heart racing, scrolling until I felt worse. These five pauses gave me a rhythm I didn't know I needed. It didn't fix everything overnight, but it gave me somewhere to come back to.\u201D"
        AppLanguage.ES -> "\u201CMe despertaba a las 3 am con el corazón acelerado, haciendo scroll hasta sentirme peor. Estas cinco pausas me dieron un ritmo que no sabía que necesitaba. No lo arregló todo de un día para otro, pero me dio un lugar al que volver.\u201D"
        AppLanguage.ID -> "\u201CDulu aku terbangun pukul 3 pagi dengan jantung berdebar, terus scrolling sampai merasa lebih buruk. Lima jeda ini memberiku irama yang tak kukenal perlu kukbutuhkan. Semuanya tak langsung membaik, tapi kini ada tempat untuk kembali.\u201D"
        AppLanguage.FR -> "« Je me réveillais à 3h du matin, le cœur au bord des lèvres, et je scrollais jusqu'à me sentir pire. Ces cinq pauses m'ont donné un rythme dont j'ignorais avoir besoin. Tout n'a pas été réparé du jour au lendemain, mais j'ai eu un endroit où revenir. »"
    }

    fun onboardingVoiceAuthor(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "جيمس، 34 سنة — في طريق التعافي"
        AppLanguage.EN -> "James, 34 — in recovery"
        AppLanguage.ES -> "James, 34 — en recuperación"
        AppLanguage.ID -> "James, 34 — dalam pemulihan"
        AppLanguage.FR -> "James, 34 ans — en chemin de guérison"
    }

    fun onboardingRhythmTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "ابدأ من حيث أنت"
        AppLanguage.EN -> "Start where you are"
        AppLanguage.ES -> "Empieza donde estás"
        AppLanguage.ID -> "Mulai dari tempatmu berada"
        AppLanguage.FR -> "Commencez où vous êtes"
    }

    fun onboardingRhythmBeginner(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "مبتدئ — 1–2 وقفات في اليوم (موصى به)"
        AppLanguage.EN -> "Beginner — 1–2 pauses a day (recommended)"
        AppLanguage.ES -> "Principiante — 1–2 pausas al día (recomendado)"
        AppLanguage.ID -> "Pemula — 1–2 jeda sehari (disarankan)"
        AppLanguage.FR -> "Débutant — 1–2 pauses par jour (recommandé)"
    }

    fun onboardingRhythmFull(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الإيقاع الكامل — العودات الخمس مع الأسماء الأصلية"
        AppLanguage.EN -> "Full rhythm — all 5 daily returns, original names"
        AppLanguage.ES -> "Ritmo completo — los 5 regresos diarios, nombres originales"
        AppLanguage.ID -> "Irama penuh — 5 kembali harian, nama aslinya"
        AppLanguage.FR -> "Rythme complet — les 5 retours quotidiens, noms originaux"
    }

    fun onboardingRhythmExplore(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "استكشف أولًا"
        AppLanguage.EN -> "Just explore first"
        AppLanguage.ES -> "Primero explorar"
        AppLanguage.ID -> "Jelajahi dulu"
        AppLanguage.FR -> "D'abord explorer"
    }

    fun onboardingRhythmCaption(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "يمكنك التغيير لاحقًا. بلا ضغط."
        AppLanguage.EN -> "You can always change this later. No pressure."
        AppLanguage.ES -> "Puedes cambiarlo más adelante. Sin presión."
        AppLanguage.ID -> "Kamu bisa mengubahnya nanti. Tanpa tekanan."
        AppLanguage.FR -> "Vous pourrez toujours changer. Sans pression."
    }

    fun onboardingLegalTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "قبل أن تبدأ"
        AppLanguage.EN -> "Before you begin"
        AppLanguage.ES -> "Antes de empezar"
        AppLanguage.ID -> "Sebelum memulai"
        AppLanguage.FR -> "Avant de commencer"
    }

    fun onboardingLegalBody(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "نحن لسنا مختصين طبيًا. إذا كنت في أزمة، فاطلب مساعدة معالج مرخّص أو اتصل/أرسل رسالة إلى 988 (خط أزمات الانتحار في الولايات المتحدة).\n\nهذه الممارسة قد تُكمل الرعاية المهنية، لكنها لا تستبدلها أبدًا."
        AppLanguage.EN -> "We are not medical professionals. If you are in crisis, please reach out to a licensed therapist or call or text 988 (Suicide & Crisis Lifeline, US).\n\nThis practice can complement, but never replace, professional mental health care."
        AppLanguage.ES -> "No somos profesionales médicos. Si estás en crisis, acude a un terapeuta licenciado o llama o escribe al 988 (Línea de Crisis, EE. UU.).\n\nEsta práctica puede complementar, pero nunca reemplazar, la atención profesional de salud mental."
        AppLanguage.ID -> "Kami bukan tenaga medis. Jika kamu sedang krisis, hubungi terapis berlisensi atau telepon/kirim SMS ke 988 (Lini Krisis, AS).\n\nPraktik ini bisa melengkapi, namun tak pernah menggantikan, perawatan kesehatan mental profesional."
        AppLanguage.FR -> "Nous ne sommes pas professionnels de santé. En cas de crise, contactez un thérapeute agréé ou appelez/écrivez au 988 (ligne crise suicidaire, États-Unis).\n\nCette pratique peut compléter, mais jamais remplacer, un accompagnement professionnel de santé mentale."
    }

    fun onboardingLegalCheckbox(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "فهمت"
        AppLanguage.EN -> "I understand"
        AppLanguage.ES -> "Lo entiendo"
        AppLanguage.ID -> "Saya mengerti"
        AppLanguage.FR -> "J'ai compris"
    }

    fun onboardingBeginBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "ابدأ عودتي الأولى"
        AppLanguage.EN -> "Begin my first return"
        AppLanguage.ES -> "Comenzar mi primer regreso"
        AppLanguage.ID -> "Mulai kepulangan pertama"
        AppLanguage.FR -> "Commencer mon premier retour"
    }

    fun onboardingSkip(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "ليس الآن"
        AppLanguage.EN -> "Skip for now"
        AppLanguage.ES -> "Ahora no"
        AppLanguage.ID -> "Lewati dulu"
        AppLanguage.FR -> "Passer pour l'instant"
    }

    fun onboardingContinueBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "متابعة"
        AppLanguage.EN -> "Continue"
        AppLanguage.ES -> "Continuar"
        AppLanguage.ID -> "Lanjut"
        AppLanguage.FR -> "Continuer"
    }

    // ── Crisis support (988 — permanent, both skins) ──

    fun crisisSupportTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "هل تحتاج مساعدة الآن؟"
        AppLanguage.EN -> "Need help now?"
        AppLanguage.ES -> "¿Necesitas ayuda ahora?"
        AppLanguage.ID -> "Butuh bantuan sekarang?"
        AppLanguage.FR -> "Besoin d'aide maintenant ?"
    }

    fun crisisSupportBody(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "إذا كنت في خطر مباشر أو تفكر في إيذاء نفسك، اتصل بـ 911 (الولايات المتحدة) أو اذهب إلى الطوارئ. في الأزمة الانتحارية: اتصل أو أرسل رسالة إلى 988."
        AppLanguage.EN -> "If you are in immediate danger or considering self-harm, call 911 (US) or go to an ER. For suicidal crisis: call or text 988."
        AppLanguage.ES -> "Si estás en peligro inmediato o piensas en hacerte daño, llama al 911 (EE. UU.) o ve a urgencias. En crisis suicida: llama o escribe al 988."
        AppLanguage.ID -> "Jika kamu dalam bahaya langsung atau berpikir menyakiti diri, telepon 911 (AS) atau pergi ke UGD. Krisis bunuh diri: telepon/kirim SMS ke 988."
        AppLanguage.FR -> "En danger immédiat ou pensées d'auto-agression : appelez le 911 (États-Unis) ou allez aux urgences. Crise suicidaire : appelez ou écrivez au 988."
    }

    fun crisis988Button(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "اتصل أو أرسل رسالة إلى 988 (الولايات المتحدة)"
        AppLanguage.EN -> "Call or text 988 (US)"
        AppLanguage.ES -> "Llamar o escribir al 988 (EE. UU.)"
        AppLanguage.ID -> "Telepon atau SMS 988 (AS)"
        AppLanguage.FR -> "Appeler ou écrire au 988 (États-Unis)"
    }

    fun crisisNamiButton(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "موارد NAMI"
        AppLanguage.EN -> "NAMI resources"
        AppLanguage.ES -> "Recursos NAMI"
        AppLanguage.ID -> "Sumber daya NAMI"
        AppLanguage.FR -> "Ressources NAMI"
    }

    fun crisisIaspButton(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "IASP — دولي"
        AppLanguage.EN -> "IASP — international"
        AppLanguage.ES -> "IASP — internacional"
        AppLanguage.ID -> "IASP — internasional"
        AppLanguage.FR -> "IASP — international"
    }

    fun crisisAppNote(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "أنت مهم. هذا التطبيق لا يمكنه أن يستبدل إنسانًا في حالة طارئة."
        AppLanguage.EN -> "You matter. This app cannot replace a human in an emergency."
        AppLanguage.ES -> "Eres importante. Esta app no puede reemplazar a una persona en una emergencia."
        AppLanguage.ID -> "Kamu penting. Aplikasi ini tak bisa menggantikan manusia dalam keadaan darurat."
        AppLanguage.FR -> "Vous comptez. Cette application ne peut remplacer un humain en cas d'urgence."
    }

    fun crisisButtonLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "مساعدة — أزمة"
        AppLanguage.EN -> "Crisis support"
        AppLanguage.ES -> "Apoyo en crisis"
        AppLanguage.ID -> "Dukungan krisis"
        AppLanguage.FR -> "Soutien en crise"
    }

    fun crisisDismiss(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "إغلاق"
        AppLanguage.EN -> "Close"
        AppLanguage.ES -> "Cerrar"
        AppLanguage.ID -> "Tutup"
        AppLanguage.FR -> "Fermer"
    }

    // ── Explore the tradition hub ──

    fun exploreTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تعمّق متى شئت"
        AppLanguage.EN -> "Go deeper when you're ready"
        AppLanguage.ES -> "Profundiza cuando estés listo"
        AppLanguage.ID -> "Selami lebih dalam saat siap"
        AppLanguage.FR -> "Allez plus loin quand vous serez prêt"
    }

    fun exploreSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "كل شيء هنا اختياري. الوقفات الخمس تعمل بدونه."
        AppLanguage.EN -> "Everything here is optional. The five pauses work without it."
        AppLanguage.ES -> "Todo aquí es opcional. Las cinco pausas funcionan sin esto."
        AppLanguage.ID -> "Semua di sini opsional. Lima jeda bekerja tanpanya."
        AppLanguage.FR -> "Tout ici est optionnel. Les cinq pauses fonctionnent sans."
    }

    fun exploreUnlockBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "افتح التقليد الأصلي"
        AppLanguage.EN -> "Unlock the original tradition"
        AppLanguage.ES -> "Desbloquear la tradición original"
        AppLanguage.ID -> "Buka tradisi aslinya"
        AppLanguage.FR -> "Débloquer la tradition originale"
    }

    fun exploreKeepSimpleBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "أبقِ الأمر بسيطًا"
        AppLanguage.EN -> "Keep it simple"
        AppLanguage.ES -> "Mantenerlo simple"
        AppLanguage.ID -> "Tetap sederhana"
        AppLanguage.FR -> "Rester simple"
    }

    fun exploreOpen(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "افتح"
        AppLanguage.EN -> "Open"
        AppLanguage.ES -> "Abrir"
        AppLanguage.ID -> "Buka"
        AppLanguage.FR -> "Ouvrir"
    }

    fun exploreCardWhyFive(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "لماذا خمس وقفات؟"
        AppLanguage.EN -> "Why five pauses?"
        AppLanguage.ES -> "¿Por qué cinco pausas?"
        AppLanguage.ID -> "Mengapa lima jeda?"
        AppLanguage.FR -> "Pourquoi cinq pauses ?"
    }

    fun exploreCardWhyFiveBody(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "خمس لحظات موزعة على اليوم تصنع إيقاعًا يتعلّمه الجهاز العصبي: الصباح والظهيرة والعصر والغروب والليل — مفاصل طبيعية في وقت الإنسان. منذ 1400 سنة، هذه البنية تُدعى الصلاة."
        AppLanguage.EN -> "Five moments spread across the day create a rhythm the nervous system can learn. Morning, midday, afternoon, sunset, night — natural hinges in human time. For 1,400 years, this structure has been called Salat."
        AppLanguage.ES -> "Cinco momentos repartidos por el día crean un ritmo que el sistema nervioso puede aprender: mañana, mediodía, tarde, atardecer, noche — bisagras naturales del tiempo humano. Durante 1.400 años, esta estructura se ha llamado Salat."
        AppLanguage.ID -> "Lima momen sepanjang hari menciptakan irama yang bisa dipelajari sistem saraf: pagi, siang, sore, senja, malam — engsela alami waktu manusia. Selama 1.400 tahun, struktur ini disebut Salat."
        AppLanguage.FR -> "Cinq moments répartis dans la journée créent un rythme que le système nerveux peut apprendre : matin, midi, après-midi, crépuscule, nuit — des charnières naturelles du temps humain. Depuis 1 400 ans, cette structure s'appelle Salat."
    }

    fun exploreCardNames(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الأسماء الخمسة"
        AppLanguage.EN -> "The five names"
        AppLanguage.ES -> "Los cinco nombres"
        AppLanguage.ID -> "Lima nama"
        AppLanguage.FR -> "Les cinq noms"
    }

    fun exploreCardNamesBody(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الفجر، الظهر، العصر، المغرب، العشاء. أسماء من تقليد — ويمكنك مواصلة تسميتها العودة الصباحية، وقفة الظهيرة…"
        AppLanguage.EN -> "Fajr (dawn), Dhuhr (midday), Asr (afternoon), Maghrib (sunset), Isha (night). Names from a tradition — you can keep calling them Morning Return, Midday Pause…"
        AppLanguage.ES -> "Fajr (alba), Dhuhr (mediodía), Asr (tarde), Maghrib (atardecer), Isha (noche). Nombres de una tradición — puedes seguir llamándolos Regreso de la mañana, Pausa del mediodía…"
        AppLanguage.ID -> "Fajr (fajar), Dhuhr (siang), Asr (sore), Maghrib (senja), Isha (malam). Nama dari sebuah tradisi — boleh juga menyebutnya Kembali pagi, Jeda siang…"
        AppLanguage.FR -> "Fajr (aube), Dhuhr (midi), Asr (après-midi), Maghrib (crépuscule), Isha (nuit). Des noms d'une tradition — vous pouvez continuer à dire Retour du matin, Pause de midi…"
    }

    fun exploreCardWater(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الماء (الوضوء)"
        AppLanguage.EN -> "Water (Wudu)"
        AppLanguage.ES -> "Agua (Wudú)"
        AppLanguage.ID -> "Air (Wudu)"
        AppLanguage.FR -> "L'eau (Wudu)"
    }

    fun exploreCardWaterBody(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« وما يريد بكم عسرًا ولكن يريد أن يطهركم » (المائدة: 6). إعادة ضبط جسدية: الوجه والذراعان والرأس والقدمان. نظافة + نية. كثيرون يقولون إنها تغسل نهارَهم قبل أن يتكلموا."
        AppLanguage.EN -> "\u201CGod does not intend to make difficulty for you, but to purify you\u201D (Quran 5:6). A physical reset: face, arms, head, feet. Hygiene + intention. Many describe it as washing the day off before they speak."
        AppLanguage.ES -> "\u201CDios no quiere daros dificultad, sino purificaros\u201D (Corán 5:6). Un reinicio físico: rostro, brazos, cabeza, pies. Higiene + intención. Muchos lo describen como lavarse el día antes de hablar."
        AppLanguage.ID -> "\u201CAllah tidak ingin menyusahkanmu, tetapi ingin menyucikanmu\u201D (Al-Quran 5:6). Reset fisik: wajah, lengan, kepala, kaki. Kebersihan + niat. Banyak yang menyebutnya mencuci hari sebelum berbicara."
        AppLanguage.FR -> "« Dieu ne veut pas vous imposer de difficulté, mais Vous purifier » (Coran 5:6). Un reset physique : visage, bras, tête, pieds. Hygiène + intention. Beaucoup décrivent cela comme laver sa journée avant de parler."
    }

    fun exploreCardQibla(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الاتجاه (القبلة)"
        AppLanguage.EN -> "Direction (Qibla)"
        AppLanguage.ES -> "Dirección (Qibla)"
        AppLanguage.ID -> "Arah (Kiblat)"
        AppLanguage.FR -> "La direction (Qibla)"
    }

    fun exploreCardQiblaBody(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "اتجاه واحد مشترك. تذكير بأنك لست مركز العالم — ولست وحدك."
        AppLanguage.EN -> "One shared direction. A reminder that you are not the center — and that you are not alone."
        AppLanguage.ES -> "Una dirección compartida. Un recordatorio de que no eres el centro — y de que no estás solo."
        AppLanguage.ID -> "Satu arah yang sama. Pengingat bahwa kamu bukan pusat segalanya — dan tidak sendirian."
        AppLanguage.FR -> "Une direction partagée. Un rappel : vous n'êtes pas le centre — et vous n'êtes pas seul."
    }

    fun exploreCardFatiha(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الحوار (الفاتحة)"
        AppLanguage.EN -> "The conversation (Al-Fatiha)"
        AppLanguage.ES -> "La conversación (Al-Fátija)"
        AppLanguage.ID -> "Percakapan (Al-Fatiha)"
        AppLanguage.FR -> "La conversation (Al-Fatiha)"
    }

    fun exploreCardFatihaBody(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "سبع آيات سمعها كثيرون كحوار: العبد يتكلم، والرب يجيب. تجربة تفاعلية آية بآية."
        AppLanguage.EN -> "Seven verses many have heard as a dialogue: the servant speaks, the Lord answers. An interactive, verse-by-verse experience."
        AppLanguage.ES -> "Siete versículos que muchos han escuchado como diálogo: el siervo habla, el Señor responde. Una experiencia interactiva, versículo a versículo."
        AppLanguage.ID -> "Tujuh ayat yang banyak didengar sebagai percakapan: hamba berbicara, Tuhan menjawab. Pengalaman interaktif, ayat demi ayat."
        AppLanguage.FR -> "Sept versets que beaucoup ont entendus comme un dialogue : le serviteur parle, le Seigneur répond. Une expérience interactive, verset par verset."
    }

    fun exploreCardStations(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "المحطات العشر"
        AppLanguage.EN -> "The 10 stations"
        AppLanguage.ES -> "Las 10 estaciones"
        AppLanguage.ID -> "10 perhentian"
        AppLanguage.FR -> "Les 10 stations"
    }

    fun exploreCardStationsBody(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "رحلة العودة داخل الصلاة: الوصول، الماء، المشي، النداء، الوقوف، التلاوة، الركوع، السجود، الجلوس، السلام. كل محطة 20–90 ثانية."
        AppLanguage.EN -> "The return journey inside the prayer: arrive, water, walk, the call, stand, recite, bow, prostrate, sit, peace. Each station 20–90 seconds."
        AppLanguage.ES -> "El viaje de regreso dentro de la oración: llegar, agua, caminar, la llamada, estar de pie, recitar, inclinarse, postrarse, sentarse, paz. Cada estación, 20–90 segundos."
        AppLanguage.ID -> "Perjalanan kembali di dalam salat: datang, air, berjalan, seruan, berdiri, membaca, ruku, sujud, duduk, damai. Tiap perhentian 20–90 detik."
        AppLanguage.FR -> "Le voyage du retour dans la prière : arriver, l'eau, marcher, l'appel, se tenir, réciter, s'incliner, se prosterner, s'asseoir, la paix. Chaque station, 20–90 secondes."
    }

    fun exploreCardEternal(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "مقياس زمني مختلف"
        AppLanguage.EN -> "A different scale of time"
        AppLanguage.ES -> "Una escala de tiempo diferente"
        AppLanguage.ID -> "Skala waktu yang berbeda"
        AppLanguage.FR -> "Une autre échelle du temps"
    }

    fun exploreCardEternalBody(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« ويستعجل بك يوم هو كألف سنة مما تعدون » (الحج: 47) — صورة تأملية، لا حساب."
        AppLanguage.EN -> "\u201CA day with your Lord is like a thousand years of what you count\u201D (Quran 22:47) — a contemplative image, not a calculation."
        AppLanguage.ES -> "\u201CUn día junto a tu Señor es como mil años de los que contáis\u201D (Corán 22:47) — una imagen contemplativa, no un cálculo."
        AppLanguage.ID -> "\u201CSatu hari bersama Tuhanmu seperti seribu tahun menurut perhitunganmu\u201D (Al-Quran 22:47) — gambaran kontemplatif, bukan hitungan."
        AppLanguage.FR -> "« Un jour auprès de ton Seigneur est comme mille ans de ce que vous comptez » (Coran 22:47) — une image contemplative, pas un calcul."
    }

    fun exploreCardScience(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "العلم والوحي"
        AppLanguage.EN -> "Science & Revelation"
        AppLanguage.ES -> "Ciencia y Revelación"
        AppLanguage.ID -> "Sains dan Wahyu"
        AppLanguage.FR -> "Science & Révélation"
    }

    fun exploreCardScienceBody(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "« ألا بذكر الله تطمئن القلوب » (الرعد: 28). أبحاث أمريكية: وباء الوحدة (الجراح العام 2023)، العلاقة بين الطقس المنتظم والتنظيم العاطفي (NIH، Harvard)."
        AppLanguage.EN -> "\u201CUnquestionably, by the remembrance of God hearts find rest\u201D (Quran 13:28). US research: the loneliness epidemic (Surgeon General, 2023), regular ritual linked to better emotional regulation (NIH, Harvard)."
        AppLanguage.ES -> "\u201CCon el recuerdo de Dios se serenan los corazones\u201D (Corán 13:28). Investigación de EE. UU.: la epidemia de soledad (Surgeon General, 2023), el ritual regular vinculado a mejor regulación emocional (NIH, Harvard)."
        AppLanguage.ID -> "\u201CDengan mengingat Allah, hati menjadi tenteram\u201D (Al-Quran 13:28). Riset AS: epidemi kesepian (Surgeon General, 2023), ritual rutin terkait regulasi emosi yang lebih baik (NIH, Harvard)."
        AppLanguage.FR -> "« C'est par le souvenir de Dieu que les cœurs s'apaisent » (Coran 13:28). Recherche américaine : l'épidémie de solitude (Surgeon General, 2023), le rituel régulier associé à une meilleure régulation émotionnelle (NIH, Harvard)."
    }

    fun exploreScienceDisclaimer(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الارتباط ليس سببية. هذه الممارسة ليست علاجًا للاكتئاب أو القلق السريري."
        AppLanguage.EN -> "Correlation is not causation. This practice is not a treatment for clinical depression or anxiety."
        AppLanguage.ES -> "Correlación no es causalidad. Esta práctica no es un tratamiento para la depresión o la ansiedad clínicas."
        AppLanguage.ID -> "Korelasi bukan kausalitas. Praktik ini bukan pengobatan untuk depresi atau kecemasan klinis."
        AppLanguage.FR -> "Corrélation n'est pas causalité. Cette pratique n'est pas un traitement de la dépression ou de l'anxiété clinique."
    }

    fun exploreCardVoices(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "أصوات من هنا"
        AppLanguage.EN -> "Voices from here"
        AppLanguage.ES -> "Voces de aquí"
        AppLanguage.ID -> "Suara dari sini"
        AppLanguage.FR -> "Des voix d'ici"
    }

    fun exploreCardVoicesBody(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "شهادات حقيقية من أمريكيين: « ساعدني على البقاء » — وليس أبدًا « لقد شفاني »."
        AppLanguage.EN -> "Real testimonies from Americans: \u201Cit helped me stay\u201D — never \u201Cit cured me\u201D."
        AppLanguage.ES -> "Testimonios reales de estadounidenses: \u201Cme ayudó a seguir\u201D — nunca \u201Cme curó\u201D."
        AppLanguage.ID -> "Kesaksian nyata orang Amerika: \u201Chal ini membantuku bertahan\u201D — bukan pernah \u201Cmenyembuhkanku\u201D."
        AppLanguage.FR -> "Des témoignages réels d'Américains : « ça m'a aidé à tenir » — jamais « ça m'a guéri »."
    }

    fun exploreUnlockedNote(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "التقليد الأصلي ظاهر الآن — الأسماء وال مصادر."
        AppLanguage.EN -> "The original tradition is now visible — names and sources."
        AppLanguage.ES -> "La tradición original ya es visible — nombres y fuentes."
        AppLanguage.ID -> "Tradisi aslinya kini terlihat — nama dan sumbernya."
        AppLanguage.FR -> "La tradition originale est maintenant visible — noms et sources."
    }

    // ── Slice 2a: the five return cards (light home) ──

    fun lightReturnChecklistTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "أشّر وقفاتك"
        AppLanguage.EN -> "Mark your pauses"
        AppLanguage.ES -> "Marca tus pausas"
        AppLanguage.ID -> "Tandai jeda Anda"
        AppLanguage.FR -> "Cochez vos pauses"
    }

    fun lightReturnChecklistNote(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "بلا سلاسل ولا نقاط — أثر لطيف ليومك فقط، يبقى على جهازك."
        AppLanguage.EN -> "No streaks, no score — just a gentle trace of your day, kept on your device."
        AppLanguage.ES -> "Sin rachas ni puntuaciones — solo un registro amable de tu día, guardado en tu dispositivo."
        AppLanguage.ID -> "Tanpa rentetan, tanpa skor — sekadar jejak hari Anda, tersimpan di perangkat."
        AppLanguage.FR -> "Pas de séries, pas de note — juste une trace douce de votre journée, gardée sur votre appareil."
    }

    fun lightPauseNowBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "خذ هذه الوقفة الآن"
        AppLanguage.EN -> "Take this pause now"
        AppLanguage.ES -> "Toma esta pausa ahora"
        AppLanguage.ID -> "Ambil jeda ini sekarang"
        AppLanguage.FR -> "Faites cette pause maintenant"
    }

    // ── Slice 2b: the guided pause (Live Companion) ──

    fun guidedPauseTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "وقفة قصيرة"
        AppLanguage.EN -> "A short pause"
        AppLanguage.ES -> "Una pausa corta"
        AppLanguage.ID -> "Jeda singkat"
        AppLanguage.FR -> "Une courte pause"
    }

    fun guidedPauseBreatheTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تنفّس"
        AppLanguage.EN -> "Breathe"
        AppLanguage.ES -> "Respira"
        AppLanguage.ID -> "Bernapas"
        AppLanguage.FR -> "Respirez"
    }

    fun guidedPauseBreatheBody(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "اتبع الدائرة. ثلاثة أنفاس بطيئة — لا شيء آخر مطلوب."
        AppLanguage.EN -> "Follow the circle. Three slow breaths — nothing else to do."
        AppLanguage.ES -> "Sigue el círculo. Tres respiraciones lentas — nada más que hacer."
        AppLanguage.ID -> "Ikuti lingkaran ini. Tiga tarikan napas lambat — tidak perlu yang lain."
        AppLanguage.FR -> "Suivez le cercle. Trois respirations lentes — rien d'autre à faire."
    }

    fun guidedPausePresenceTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "لاحظ"
        AppLanguage.EN -> "Notice"
        AppLanguage.ES -> "Observa"
        AppLanguage.ID -> "Perhatikan"
        AppLanguage.FR -> "Remarquez"
    }

    fun guidedPausePresenceBody(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "سمِّ ثلاثة أشياء يمكنك أن تلمسها أو تسمعها أو تراها. دَع اليوم ينتظر دقيقة."
        AppLanguage.EN -> "Name three things you can touch, hear, or see. Let the day wait one minute."
        AppLanguage.ES -> "Nombra tres cosas que puedas tocar, oír o ver. Deja que el día espere un minuto."
        AppLanguage.ID -> "Sebutkan tiga hal yang bisa Anda sentuh, dengar, atau lihat. Biarkan hari menunggu satu menit."
        AppLanguage.FR -> "Nommez trois choses que vous pouvez toucher, entendre ou voir. Laissez la journée attendre une minute."
    }

    fun guidedPauseIntentionTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "نية واحدة"
        AppLanguage.EN -> "One intention"
        AppLanguage.ES -> "Una intención"
        AppLanguage.ID -> "Satu niat"
        AppLanguage.FR -> "Une intention"
    }

    fun guidedPauseIntentionBody(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "اختر شيئًا صغيرًا واحدًا يهم لبقية يومك. لا يحتاج إلى أن يكون كبيرًا."
        AppLanguage.EN -> "Choose one small thing that matters for the rest of your day. It doesn't need to be big."
        AppLanguage.ES -> "Elige una cosa pequeña que importe para el resto de tu día. No hace falta que sea grande."
        AppLanguage.ID -> "Pilih satu hal kecil yang penting untuk sisa hari Anda. Tak perlu besar."
        AppLanguage.FR -> "Choisissez une petite chose qui compte pour le reste de votre journée. Elle n'a pas besoin d'être grande."
    }

    fun guidedPauseFinishBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "إنهاء"
        AppLanguage.EN -> "Finish"
        AppLanguage.ES -> "Terminar"
        AppLanguage.ID -> "Selesai"
        AppLanguage.FR -> "Terminer"
    }

    fun guidedPauseSkipBtn(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تخطّي"
        AppLanguage.EN -> "Skip"
        AppLanguage.ES -> "Omitir"
        AppLanguage.ID -> "Lewati"
        AppLanguage.FR -> "Passer"
    }

    // ── Slice 2c: gentle notifications (light skin) ──

    fun gentleFajrNotifTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "عودة الصباح هنا"
        AppLanguage.EN -> "Your Morning Return is here"
        AppLanguage.ES -> "Llega tu Regreso de la mañana"
        AppLanguage.ID -> "Kembali pagi tiba"
        AppLanguage.FR -> "Votre Retour du matin est là"
    }

    fun gentleFajrNotifBody(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "دقيقة هادئة من الحضور قبل أن يبدأ اليوم."
        AppLanguage.EN -> "One quiet minute of presence before the day begins."
        AppLanguage.ES -> "Un minuto tranquilo de presencia antes de que empiece el día."
        AppLanguage.ID -> "Satu menit tenang sebelum hari Anda dimulai."
        AppLanguage.FR -> "Une minute de présence avant que la journée commence."
    }

    fun gentleDhuhrNotifTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "وقفة الظهيرة هنا"
        AppLanguage.EN -> "Your Midday Pause is here"
        AppLanguage.ES -> "Llega tu Pausa del mediodía"
        AppLanguage.ID -> "Jeda siang tiba"
        AppLanguage.FR -> "Votre Pause de midi est là"
    }

    fun gentleDhuhrNotifBody(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "إعادة ضبط قصيرة، في وسط كل شيء."
        AppLanguage.EN -> "A short reset, in the middle of everything."
        AppLanguage.ES -> "Un reinicio breve, en medio de todo."
        AppLanguage.ID -> "Penyetelan ulang singkat di tengah kesibukan."
        AppLanguage.FR -> "Un court instant de repli, au milieu de tout."
    }

    fun gentleFridayNotifTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "وقفتك الأسبوعية هنا"
        AppLanguage.EN -> "Your weekly pause is here"
        AppLanguage.ES -> "Llega tu pausa semanal"
        AppLanguage.ID -> "Jeda mingguanmu tiba"
        AppLanguage.FR -> "Votre pause hebdomadaire est là"
    }

    fun gentleFridayNotifBody(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "اليوم، نافذة سكينة أطول قليلًا."
        AppLanguage.EN -> "Today, a slightly longer window of calm."
        AppLanguage.ES -> "Hoy, una ventana de calma un poco más larga."
        AppLanguage.ID -> "Hari ini, jeda ketenangan yang sedikit lebih panjang."
        AppLanguage.FR -> "Aujourd'hui, une fenêtre de calme un peu plus longue."
    }

    fun gentleDailyNotifTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "دقيقة لنفسك"
        AppLanguage.EN -> "A minute for yourself"
        AppLanguage.ES -> "Un minuto para ti"
        AppLanguage.ID -> "Satu menit untuk Anda"
        AppLanguage.FR -> "Une minute pour vous"
    }

    fun gentleDailyNotifBody(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "يومك يمر. خذ دقيقة صادقة لتلاحظه."
        AppLanguage.EN -> "Your day is moving. Take one honest minute to notice it."
        AppLanguage.ES -> "Tu día avanza. Tómate un minuto sincero para notarlo."
        AppLanguage.ID -> "Hari Anda berjalan. Luangkan satu menit jujur untuk menyadarinya."
        AppLanguage.FR -> "Votre journée avance. Prenez une minute sincère pour la remarquer."
    }

    fun gentleChannelName(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "إيقاع الحضور"
        AppLanguage.EN -> "Presence Rhythm"
        AppLanguage.ES -> "Ritmo de presencia"
        AppLanguage.ID -> "Ritme Kehadiran"
        AppLanguage.FR -> "Rythme de présence"
    }

    fun gentleChannelDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "تذكيرات لطيفة لوقفاتك اليومية."
        AppLanguage.EN -> "Gentle reminders for your daily pauses."
        AppLanguage.ES -> "Recordatorios amables para tus pausas diarias."
        AppLanguage.ID -> "Pengingat lembut untuk jeda harian Anda."
        AppLanguage.FR -> "Des rappels doux pour vos pauses quotidiennes."
    }

    // ── Slice 2: the light home header ──
    // The brand mark ذَوْق (« to taste ») stays; what is tasted changes with
    // the skin: presence, never prayer vocabulary.

    fun lightHeaderSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "ذَوْق • إيقاعُ الحضور"
        AppLanguage.EN -> "ذَوْق • PRESENCE, TASTED"
        AppLanguage.ES -> "ذَوْق • PRESENCIA, SABOREADA"
        AppLanguage.ID -> "ذَوْق • KEHADIRAN YANG DIRASAKAN"
        AppLanguage.FR -> "ذَوْق • LA PRÉSENCE GOÛTÉE"
    }

    // ── Slice 3: the weekly presence report ──

    fun weeklyCardTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "أسبوعك بلطف"
        AppLanguage.EN -> "YOUR WEEK, GENTLY"
        AppLanguage.ES -> "TU SEMANA, CON SUAVIDAD"
        AppLanguage.ID -> "PEKANMU, DENGAN LEMBUT"
        AppLanguage.FR -> "VOTRE SEMAINE, EN DOUCEUR"
    }

    fun weeklyCardSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "نظرة صادقة صغيرة — بلا حكم."
        AppLanguage.EN -> "A small, honest look back — no judgment."
        AppLanguage.ES -> "Una mirada pequeña y honesta — sin juicios."
        AppLanguage.ID -> "Sekilas, jujur — tanpa menghakimi."
        AppLanguage.FR -> "Un petit retour honnête — aucun jugement."
    }

    fun weeklyKeptLine(lang: AppLanguage, kept: Int): String = when (lang) {
        AppLanguage.AR -> "الوقفات التي عشت هذا الأسبوع: $kept"
        AppLanguage.EN -> "Pauses kept this week: $kept"
        AppLanguage.ES -> "Pausas vividas esta semana: $kept"
        AppLanguage.ID -> "Jeda yang dijalani pekan ini: $kept"
        AppLanguage.FR -> "Pauses vécues cette semaine : $kept"
    }

    fun weeklyDayInitials(lang: AppLanguage): String = when (lang) {
        // Monday … Sunday, one letter per day column of the graph.
        AppLanguage.AR -> "إثأخجسح"
        AppLanguage.EN -> "MTWTFSS"
        AppLanguage.ES -> "LMXJVSD"
        AppLanguage.ID -> "SRKJJSM"
        AppLanguage.FR -> "LMMJVSD"
    }

    fun weeklyTrendIntro(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "كيف تحرّك الجوّ بعد وقفاتك"
        AppLanguage.EN -> "How the weather moved after your pauses"
        AppLanguage.ES -> "Cómo se movió tu clima tras tus pausas"
        AppLanguage.ID -> "Bagaimana suasana bergeser setelah jeda"
        AppLanguage.FR -> "Comment le climat a bougé après vos pauses"
    }

    fun weeklyMoodCountLine(lang: AppLanguage, mood: InnerWeather, count: Int, total: Int): String =
        when (lang) {
            AppLanguage.AR -> "${innerWeatherLabel(AppLanguage.AR, mood.key)} · $count من $total"
            AppLanguage.ES -> "${innerWeatherLabel(AppLanguage.ES, mood.key)} · $count de $total"
            AppLanguage.ID -> "${innerWeatherLabel(AppLanguage.ID, mood.key)} · $count dari $total"
            AppLanguage.EN -> "${innerWeatherLabel(AppLanguage.EN, mood.key)} · $count of $total"
            AppLanguage.FR -> "${innerWeatherLabel(AppLanguage.FR, mood.key)} · $count sur $total"
        }

    fun weeklyInsightIntro(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "نمط صغير — يُعَدّ ولا يُنطق بحكم"
        AppLanguage.EN -> "One small pattern — counted, never scored"
        AppLanguage.ES -> "Un pequeño patrón — contado, nunca puntuado"
        AppLanguage.ID -> "Satu pola kecil — dihitung, tidak dinilai"
        AppLanguage.FR -> "Un petit motif — compté, jamais noté"
    }

    fun weeklyInsightLine(lang: AppLanguage, momentLabel: String, count: Int): String = when (lang) {
        AppLanguage.AR -> "« $momentLabel » × $count"
        AppLanguage.ES -> "« $momentLabel » × $count"
        AppLanguage.ID -> "« $momentLabel » × $count"
        AppLanguage.EN -> "« $momentLabel » × $count"
        AppLanguage.FR -> "« $momentLabel » × $count"
    }

    fun weeklyNoData(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "أيام قليلة حتى الآن — لا بأس. الإيقاع ينتظر."
        AppLanguage.EN -> "Not many days yet — that's okay. The rhythm waits."
        AppLanguage.ES -> "Aún no hay muchos días — está bien. El ritmo espera."
        AppLanguage.ID -> "Belum banyak hari — tidak apa-apa. Ritmenya menunggu."
        AppLanguage.FR -> "Peu de jours pour l'instant — c'est très bien ainsi. Le rythme patiente."
    }

    fun weeklyWhyTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "لماذا قد يساعد هذا"
        AppLanguage.EN -> "Why this can help"
        AppLanguage.ES -> "Por qué esto puede ayudar"
        AppLanguage.ID -> "Mengapa ini bisa membantu"
        AppLanguage.FR -> "Pourquoi cela peut aider"
    }

    fun weeklyIntentionPrompt(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "نيّة لطيفة للأسبوع القادم"
        AppLanguage.EN -> "Set a gentle intention for next week"
        AppLanguage.ES -> "Elige una intención amable para la próxima semana"
        AppLanguage.ID -> "Tetapkan niat lembut untuk pekan depan"
        AppLanguage.FR -> "Choisissez une intention douce pour la semaine prochaine"
    }

    fun weeklyIntentionChip1(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "صباحات هادئة"
        AppLanguage.EN -> "Mornings, gently"
        AppLanguage.ES -> "Mañanas, en calma"
        AppLanguage.ID -> "Pagi dengan lembut"
        AppLanguage.FR -> "Des matins, en douceur"
    }

    fun weeklyIntentionChip2(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "وقفة منتصف اليوم"
        AppLanguage.EN -> "One midday pause"
        AppLanguage.ES -> "Una pausa al mediodía"
        AppLanguage.ID -> "Satu jeda siang"
        AppLanguage.FR -> "Une pause à midi"
    }

    fun weeklyIntentionChip3(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "اللطف مع نفسي"
        AppLanguage.EN -> "Kindness to myself"
        AppLanguage.ES -> "Amabilidad conmigo"
        AppLanguage.ID -> "Lembut pada diri sendiri"
        AppLanguage.FR -> "De la douceur pour moi"
    }

    fun weeklyIntentionChip4(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "الحضور بلا ضغط"
        AppLanguage.EN -> "Presence over pressure"
        AppLanguage.ES -> "Presencia sin presión"
        AppLanguage.ID -> "Hadir tanpa tekanan"
        AppLanguage.FR -> "La présence, sans pression"
    }

    fun weeklyIntentionSaved(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "محفوظة للأسبوع القادم ✓"
        AppLanguage.EN -> "Remembered for next week ✓"
        AppLanguage.ES -> "Recordada para la próxima semana ✓"
        AppLanguage.ID -> "Diingat untuk pekan depan ✓"
        AppLanguage.FR -> "Retenue pour la semaine prochaine ✓"
    }

    // ── Slice 2d: the light journal ──

    fun lightJournalTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "يومك بإيقاعك"
        AppLanguage.EN -> "YOUR DAY, AT YOUR PACE"
        AppLanguage.ES -> "TU DÍA, A TU RITMO"
        AppLanguage.ID -> "HARI ANDA, DENGAN RITME ANDA"
        AppLanguage.FR -> "VOTRE JOURNÉE, À VOTRE RYTHME"
    }

    fun lightJournalQuote(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "ما تفعله يوميًا أهم مما تفعله أحيانًا."
        AppLanguage.EN -> "What you do daily matters more than what you do once in a while."
        AppLanguage.ES -> "Lo que haces a diario importa más que lo que haces de vez en cuando."
        AppLanguage.ID -> "Apa yang Anda lakukan setiap hari lebih berarti daripada sesekali."
        AppLanguage.FR -> "Ce que vous faites chaque jour compte plus que ce que vous faites de temps en temps."
    }

    fun lightJournalQuestion(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "كيف كان يومك، بصدق؟"
        AppLanguage.EN -> "How was your day, honestly?"
        AppLanguage.ES -> "¿Cómo fue tu día, con honestidad?"
        AppLanguage.ID -> "Bagaimana harimu, dengan jujur?"
        AppLanguage.FR -> "Votre journée, en toute honnêteté ?"
    }

    fun lightJournalNotePlaceholder(lang: AppLanguage): String = when (lang) {
        AppLanguage.AR -> "سطر واحد عن يومك — لعينيك فقط."
        AppLanguage.EN -> "One line about today — for your eyes only."
        AppLanguage.ES -> "Una línea sobre hoy — solo para ti."
        AppLanguage.ID -> "Satu baris tentang hari ini — hanya untuk Anda."
        AppLanguage.FR -> "Une ligne sur votre journée — pour vos yeux seulement."
    }
}

