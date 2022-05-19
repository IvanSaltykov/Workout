package com.example.washingcar.request_server

import com.example.washingcar.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.*

interface RequestApiGeocoding {
    @GET("v1/forecast")
    fun getWeather(
        @Query("latitude") lat: Double,
        @Query("longitude") lon: Double,
        @Query("hourly") hourly: List<String>
//        @Query("daily") daily: List<String>,
//        @Query("timezone") timezone: String
    ): Call<WeatherResponse>
}