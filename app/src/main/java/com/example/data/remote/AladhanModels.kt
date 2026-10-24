package com.example.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AladhanResponse(
    @Json(name = "code") val code: Int = 200,
    @Json(name = "status") val status: String = "OK",
    @Json(name = "data") val data: AladhanData? = null
)

@JsonClass(generateAdapter = true)
data class AladhanData(
    @Json(name = "timings") val timings: AladhanTimings? = null,
    @Json(name = "date") val date: AladhanDateContainer? = null,
    @Json(name = "meta") val meta: AladhanMeta? = null
)

@JsonClass(generateAdapter = true)
data class AladhanTimings(
    @Json(name = "Fajr") val fajr: String = "06:00",
    @Json(name = "Sunrise") val sunrise: String = "07:30",
    @Json(name = "Dhuhr") val dhuhr: String = "13:00",
    @Json(name = "Asr") val asr: String = "16:00",
    @Json(name = "Sunset") val sunset: String = "18:45",
    @Json(name = "Maghrib") val maghrib: String = "18:50",
    @Json(name = "Isha") val isha: String = "20:15"
)

@JsonClass(generateAdapter = true)
data class AladhanDateContainer(
    @Json(name = "readable") val readable: String? = null,
    @Json(name = "hijri") val hijri: AladhanHijri? = null
)

@JsonClass(generateAdapter = true)
data class AladhanHijri(
    @Json(name = "date") val date: String? = null,
    @Json(name = "month") val month: AladhanHijriMonth? = null,
    @Json(name = "year") val year: String? = null
)

@JsonClass(generateAdapter = true)
data class AladhanHijriMonth(
    @Json(name = "number") val number: Int? = null,
    @Json(name = "en") val en: String? = null,
    @Json(name = "ar") val ar: String? = null
)

@JsonClass(generateAdapter = true)
data class AladhanMeta(
    @Json(name = "timezone") val timezone: String? = null,
    /** Offset of [timezone] relative to UTC, in seconds. */
    @Json(name = "timezone_offset") val timezoneOffset: Int? = null
)
