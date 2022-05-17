package com.example.washingcar.model

import com.google.gson.annotations.SerializedName

data class GeocodingResponse(

	@field:SerializedName("GeocodingResponse")
	val geocodingResponse: GeocodingResponseItem
)

data class GeocodingResponseItem(

	@field:SerializedName("local_names")
	val localNames: LocalNames,

	@field:SerializedName("country")
	val country: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("lon")
	val lon: Double,

	@field:SerializedName("state")
	val state: String,

	@field:SerializedName("lat")
	val lat: Double
)

data class LocalNames(

	@field:SerializedName("tt")
	val tt: String,

	@field:SerializedName("de")
	val de: String,

	@field:SerializedName("be")
	val be: String,

	@field:SerializedName("ru")
	val ru: String,

	@field:SerializedName("ko")
	val ko: String,

	@field:SerializedName("feature_name")
	val featureName: String,

	@field:SerializedName("lt")
	val lt: String,

	@field:SerializedName("en")
	val en: String,

	@field:SerializedName("fr")
	val fr: String,

	@field:SerializedName("zh")
	val zh: String,

	@field:SerializedName("et")
	val et: String,

	@field:SerializedName("cv")
	val cv: String,

	@field:SerializedName("uk")
	val uk: String,

	@field:SerializedName("ja")
	val ja: String,

	@field:SerializedName("sk")
	val sk: String,

	@field:SerializedName("pl")
	val pl: String,

	@field:SerializedName("ascii")
	val ascii: String,

	@field:SerializedName("ca")
	val ca: String
)
