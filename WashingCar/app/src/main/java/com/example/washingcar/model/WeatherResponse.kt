package com.example.washingcar.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(

	@field:SerializedName("elevation")
	val elevation: Double,

	@field:SerializedName("generationtime_ms")
	val generationtimeMs: Double,

	@field:SerializedName("daily_units")
	val dailyUnits: DailyUnits,

	@field:SerializedName("daily")
	val daily: Daily,

	@field:SerializedName("latitude")
	val latitude: Double,

	@field:SerializedName("utc_offset_seconds")
	val utcOffsetSeconds: Int,

	@field:SerializedName("longitude")
	val longitude: Double
)

data class DailyUnits(

	@field:SerializedName("temperature_2m_max")
	val temperature2mMax: String,

	@field:SerializedName("temperature_2m_min")
	val temperature2mMin: String,

	@field:SerializedName("time")
	val time: String
)

data class Daily(

	@field:SerializedName("temperature_2m_max")
	val temperature2mMax: List<Double>,

	@field:SerializedName("temperature_2m_min")
	val temperature2mMin: List<Double>,

	@field:SerializedName("time")
	val time: List<String>
)
