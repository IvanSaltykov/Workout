package com.example.washingcar.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(

	@field:SerializedName("elevation")
	val elevation: Double? = null,

	@field:SerializedName("hourly_units")
	val hourlyUnits: HourlyUnits? = null,

	@field:SerializedName("generationtime_ms")
	val generationtimeMs: Double? = null,

	@field:SerializedName("latitude")
	val latitude: Double? = null,

	@field:SerializedName("utc_offset_seconds")
	val utcOffsetSeconds: Int? = null,

	@field:SerializedName("hourly")
	val hourly: Hourly? = null,

	@field:SerializedName("longitude")
	val longitude: Double? = null
)

data class HourlyUnits(

	@field:SerializedName("temperature_2m")
	val temperature2m: String? = null,

	@field:SerializedName("time")
	val time: String? = null
)

data class Hourly(

	@field:SerializedName("temperature_2m")
	val temperature2m: List<Double?>? = null,

	@field:SerializedName("time")
	val time: List<String?>? = null
)
