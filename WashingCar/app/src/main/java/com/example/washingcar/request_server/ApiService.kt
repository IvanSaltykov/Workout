package com.example.washingcar.request_server

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/geo/1.0/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RequestApiGeocoding::class.java)
}