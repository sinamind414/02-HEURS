package com.example.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface AladhanApiService {
    /**
     * @param date optional date in the `DD-MM-YYYY` format. When omitted the
     *        API returns the timings of the current day.
     */
    @GET("v1/timingsByCity")
    suspend fun getTimingsByCity(
        @Query("city") city: String,
        @Query("country") country: String,
        // 3 = Muslim World League (18°/17°) — the convention actually used
        // across the Maghreb; the previous default (2 = ISNA, 15°/15°) is
        // calibrated for North America and shifts Fajr/Isha by 10-20 min.
        @Query("method") method: Int = 3,
        @Query("date") date: String? = null
    ): AladhanResponse
}
