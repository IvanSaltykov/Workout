package com.example.washingcar.request_server

import com.example.washingcar.model.GeocodingResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RequestApiGeocoding {
    @GET("direct")
    fun getGeocoding(
        @Query("q") q: String,
        @Query("appid") appid: String
    ) : Call<GeocodingResponse>

}