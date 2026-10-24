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
        @Query("method") method: Int = 2, // 2 = Islamic Society of North America
        @Query("date") date: String? = null
    ): AladhanResponse
}
