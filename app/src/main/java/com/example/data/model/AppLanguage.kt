package com.example.data.model

enum class AppLanguage(
    val code: String,
    val displayName: String,
    val flagEmoji: String,
    val isRtl: Boolean
) {
    FR("fr", "Français", "🇫🇷", false),
    AR("ar", "العربية", "🇸🇦", true),
    EN("en", "English", "🇬🇧", false),
    ES("es", "Español", "🇪🇸", false),
    ID("id", "Bahasa Indonesia", "🇮🇩", false);

    companion object {
        fun fromCode(code: String): AppLanguage =
            entries.firstOrNull { it.code.equals(code, ignoreCase = true) } ?: FR
    }
}
