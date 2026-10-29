package com.example.data.model

/**
 * Presence Rhythm — the US-facing skin of Dhawq (strategy doc
 * STRATEGIE_US_DHAWQ_PRESENCE.md, §5 and §0 decision #2).
 *
 * The same codebase serves both positioningS:
 *
 *  * [TraditionLayer.OFF] — "Presence Rhythm": the five moments are named
 *    Morning Return / Midday Pause / Afternoon Reset / Sunset Presence /
 *    Night Return; no Arabic, no Salat vocabulary is shown up front. The
 *    content stays honest (the tradition layer is one tap away), it is only
 *    never imposed.
 *  * [TraditionLayer.GRADUAL] / [TraditionLayer.ON] — "Dhawq Presence": the
 *    original names (Fajr, Dhuhr…) and the full tradition content show.
 *
 * This is a product decision recorded by the owner on 29/10/2026 ("go
 * développe la version focalisée sur le malaise US"); the theological
 * committee review point COMITE_THEOLOGIQUE.md §3.5 #1 tracks its framing.
 */
enum class TraditionLayer(val key: String) {
    OFF("off"),
    GRADUAL("gradual"),
    ON("on");

    /** Light skin only for the fully hidden layer. */
    val isLightSkin: Boolean get() = this == OFF

    companion object {
        fun fromKey(key: String): TraditionLayer =
            entries.firstOrNull { it.key == key } ?: OFF
    }
}

/**
 * The daily rhythm chosen at onboarding (US-03): Beginner starts with 1–2
 * pauses, Full keeps all five returns, Explore opens the tradition hub first.
 * Never a shame signal: Full is not "better", it is only more.
 */
enum class UsRhythm(val key: String) {
    BEGINNER("beginner"),
    FULL("full"),
    EXPLORE("explore");

    companion object {
        fun fromKey(key: String): UsRhythm =
            entries.firstOrNull { it.key == key } ?: BEGINNER
    }
}

/**
 * Inner Weather — the five non-clinical check-in states (US-05). Deliberately
 * label-based, never scored: there is no "worse" red value, and the answer is
 * never aggregated (Serment #2). `fromKey` returns null on unknown keys so a
 * stale persisted value degrades to "no check-in yet", not to a wrong mood.
 */
enum class InnerWeather(val key: String, val emoji: String) {
    OVERWHELMED("overwhelmed", "🌊"),
    ANXIOUS("anxious", "🌫️"),
    NEUTRAL("neutral", "😐"),
    CALMER("calmer", "🌤️"),
    GROUNDED("grounded", "🌱");

    companion object {
        fun fromKey(key: String): InnerWeather? =
            entries.firstOrNull { it.key == key }
    }
}
