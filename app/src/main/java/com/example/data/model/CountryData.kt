package com.example.data.model

/**
 * Full country catalogue feeding the prayer-times country picker.
 *
 * Names follow the list provided by the product owner (English, ISO 3166-1
 * alpha-2 codes). The flag emoji is *derived* from the code at read time —
 * no 230 hardcoded emoji strings to maintain.
 *
 * Deliberate exclusions from the requested list:
 *  • "Zanzibar" — not a country (Tanzania), would duplicate TZ
 *  • "Other Country" ×2 — no ISO code, no prayer-times meaning
 * Renames/merges kept faithful otherwise: "Alderney" rides Guernsey (GG),
 * "Ascension Island" rides Saint Helena (SH), "Netherlands Antilles" keeps
 * the withdrawn AN code (its emoji falls back to letters on some devices).
 */
data class Country(val code: String, val name: String) {
    /** Regional-indicator pair derived from the ISO code (e.g. FR → 🇫🇷). */
    val flagEmoji: String
        get() = code.uppercase()
            .map { String(Character.toChars(0x1F1E6 + (it - 'A'))) }
            .joinToString("")
}

object CountryData {

    /** The 15 entries pinned at the top of the picker, in the requested order. */
    val popularCodes: List<String> = listOf(
        "US", "ES", "FR", "GB", "BR", "KR", "SA", "IL", "NL",
        "CA", "IT", "CL", "UA", "PL", "AU"
    )

    private val ENTRIES: List<Pair<String, String>> = listOf(
        // ── A ──
        "AF" to "Afghanistan", "AX" to "Aland Islands", "AL" to "Albania",
        "GG" to "Alderney", "DZ" to "Algeria", "AS" to "American Samoa",
        "AD" to "Andorra", "AO" to "Angola", "AI" to "Anguilla",
        "AG" to "Antigua and Barbuda", "AR" to "Argentina", "AM" to "Armenia",
        "AW" to "Aruba", "SH" to "Ascension Island", "AU" to "Australia",
        "AT" to "Austria", "AZ" to "Azerbaijan",
        // ── B ──
        "BS" to "Bahamas", "BH" to "Bahrain", "BD" to "Bangladesh",
        "BB" to "Barbados", "BY" to "Belarus", "BE" to "Belgium",
        "BZ" to "Belize", "BJ" to "Benin", "BM" to "Bermuda",
        "BT" to "Bhutan", "BO" to "Bolivia", "BA" to "Bosnia and Herzegovina",
        "BW" to "Botswana", "BR" to "Brazil", "BN" to "Brunei",
        "BG" to "Bulgaria", "BF" to "Burkina Faso", "BI" to "Burundi",
        // ── C ──
        "KH" to "Cambodia", "CM" to "Cameroon", "CA" to "Canada",
        "CV" to "Cape Verde", "BQ" to "Caribbean Netherlands",
        "KY" to "Cayman Islands", "CO" to "Colombia", "KM" to "Comoros",
        "CD" to "Congo, The Democratic Republic Of The",
        "CG" to "Congo, The Republic of Congo", "CK" to "Cook Islands",
        "CR" to "Costa Rica", "CI" to "Cote D'Ivoire", "HR" to "Croatia (local name: Hrvatska)",
        "CW" to "Curacao", "CY" to "Cyprus", "CZ" to "Czech Republic",
        // ── D ──
        "DK" to "Denmark", "DJ" to "Djibouti", "DM" to "Dominica",
        "DO" to "Dominican Republic",
        // ── E ──
        "EC" to "Ecuador", "EG" to "Egypt", "SV" to "El Salvador",
        "GQ" to "Equatorial Guinea", "ER" to "Eritrea", "EE" to "Estonia",
        "ET" to "Ethiopia",
        // ── F ──
        "FK" to "Falkland Islands (Malvinas)", "FO" to "Faroe Islands",
        "FJ" to "Fiji", "FI" to "Finland", "FR" to "France",
        "GF" to "French Guiana", "PF" to "French Polynesia",
        // ── G ──
        "GA" to "Gabon", "GM" to "Gambia", "GE" to "Georgia",
        "DE" to "Germany", "GH" to "Ghana", "GI" to "Gibraltar",
        "GR" to "Greece", "GL" to "Greenland", "GD" to "Grenada",
        "GP" to "Guadeloupe", "GU" to "Guam", "GT" to "Guatemala",
        "GG" to "Guernsey", "GN" to "Guinea", "GW" to "Guinea-Bissau",
        "GY" to "Guyana",
        // ── H ──
        "HT" to "Haiti", "HN" to "Honduras", "HK" to "Hong Kong,China",
        "HU" to "Hungary",
        // ── I ──
        "IS" to "Iceland", "IQ" to "Iraq", "IE" to "Ireland",
        "IL" to "Israel", "IT" to "Italy",
        // ── J ──
        "JM" to "Jamaica", "JP" to "Japan", "JE" to "Jersey", "JO" to "Jordan",
        // ── K ──
        "KZ" to "Kazakhstan", "KE" to "Kenya", "KI" to "Kiribati",
        "KR" to "Korea", "XK" to "Kosovo", "KW" to "Kuwait",
        "KG" to "Kyrgyzstan",
        // ── L ──
        "LA" to "Lao People's Democratic Republic", "LV" to "Latvia",
        "LB" to "Lebanon", "LS" to "Lesotho", "LR" to "Liberia",
        "LY" to "Libya", "LI" to "Liechtenstein", "LT" to "Lithuania",
        "LU" to "Luxembourg",
        // ── M ──
        "MO" to "Macau,China", "MK" to "Macedonia", "MG" to "Madagascar",
        "MW" to "Malawi", "MY" to "Malaysia", "MV" to "Maldives",
        "ML" to "Mali", "MT" to "Malta", "MH" to "Marshall Islands",
        "MQ" to "Martinique", "MR" to "Mauritania", "MU" to "Mauritius",
        "YT" to "Mayotte", "MX" to "Mexico", "FM" to "Micronesia",
        "MD" to "Moldova", "MC" to "Monaco", "MN" to "Mongolia",
        "ME" to "Montenegro", "MS" to "Montserrat", "MA" to "Morocco",
        "MZ" to "Mozambique", "MM" to "Myanmar",
        // ── N ──
        "NA" to "Namibia", "NR" to "Nauru", "NP" to "Nepal",
        "NL" to "Netherlands", "AN" to "Netherlands Antilles",
        "NC" to "New Caledonia", "NZ" to "New Zealand", "NI" to "Nicaragua",
        "NE" to "Niger", "NG" to "Nigeria", "NU" to "Niue",
        "NF" to "Norfolk Island", "MP" to "Northern Mariana Islands",
        "NO" to "Norway",
        // ── O ──
        "OM" to "Oman",
        // ── P ──
        "PK" to "Pakistan", "PW" to "Palau", "PS" to "Palestine",
        "PA" to "Panama", "PG" to "Papua New Guinea", "PY" to "Paraguay",
        "PE" to "Peru", "PH" to "Philippines", "PL" to "Poland",
        "PT" to "Portugal", "PR" to "Puerto Rico",
        // ── Q ──
        "QA" to "Qatar",
        // ── R ──
        "RE" to "Reunion", "RO" to "Romania",
        "RU" to "Russian Federation", "RW" to "Rwanda",
        // ── S ──
        "BL" to "Saint Barthelemy", "KN" to "Saint Kitts and Nevis",
        "LC" to "Saint Lucia", "MF" to "Saint Martin",
        "VC" to "Saint Vincent and the Grenadines", "WS" to "Samoa",
        "SM" to "San Marino", "ST" to "Sao Tome and Principe",
        "SA" to "Saudi Arabia", "SN" to "Senegal", "RS" to "Serbia",
        "SC" to "Seychelles", "SL" to "Sierra Leone", "SG" to "Singapore",
        "SX" to "Sint Maarten", "SK" to "Slovakia (Slovak Republic)",
        "SI" to "Slovenia", "SB" to "Solomon Islands", "SO" to "Somalia",
        "ZA" to "South Africa",
        "GS" to "South Georgia and the South Sandwich Islands",
        "SS" to "South Sudan", "ES" to "Spain", "LK" to "Sri Lanka",
        "PM" to "St. Pierre and Miquelon", "SR" to "Suriname",
        "SZ" to "Swaziland", "SE" to "Sweden", "CH" to "Switzerland",
        // ── T ──
        "TW" to "Taiwan,China", "TJ" to "Tajikistan", "TZ" to "Tanzania",
        "TH" to "Thailand", "TL" to "Timor-Leste", "TG" to "Togo",
        "TO" to "Tonga", "TT" to "Trinidad and Tobago", "TN" to "Tunisia",
        "TR" to "Turkey", "TM" to "Turkmenistan",
        "TC" to "Turks and Caicos Islands", "TV" to "Tuvalu",
        // ── U ──
        "UG" to "Uganda", "UA" to "Ukraine", "AE" to "United Arab Emirates",
        "GB" to "United Kingdom", "US" to "United States", "UY" to "Uruguay",
        "UZ" to "Uzbekistan",
        // ── V ──
        "VU" to "Vanuatu", "VA" to "Vatican City State (Holy See)",
        "VE" to "Venezuela", "VN" to "Vietnam",
        "VG" to "Virgin Islands (British)", "VI" to "Virgin Islands (U.S.)",
        // ── W ──
        "WF" to "Wallis And Futuna Islands",
        // ── Y ──
        "YE" to "Yemen",
        // ── Z ──
        "ZM" to "Zambia", "ZW" to "Zimbabwe"
    )

    val all: List<Country> = ENTRIES.map { Country(it.first, it.second) }

    private val byName: Map<String, Country> =
        all.associateBy { it.name.lowercase() }

    private val byCode: Map<String, Country> =
        all.associateBy { it.code.uppercase() }

    /** The pinned 15, resolved and de-duplicated, in requested order. */
    val popular: List<Country> = popularCodes
        .mapNotNull { byCode[it] }
        .distinctBy { it.code }

    fun findByName(name: String): Country? = byName[name.trim().lowercase()]

    /**
     * Filtered catalogue for the picker: blank query returns the 15 popular
     * entries first followed by the rest; otherwise name-or-code contains.
     */
    fun search(query: String): List<Country> {
        val q = query.trim()
        if (q.isEmpty()) return popular + all.filter { c -> popular.none { it.code == c.code } }
        return all.filter {
            it.name.contains(q, ignoreCase = true) || it.code.equals(q, ignoreCase = true)
        }
    }
}
