package com.example.washingcar.preferences

import android.content.Context

class PreferencesManager(context: Context) {
    companion object {
        private const val PREFERENCES_NAME = "geocoding"
        private const val LAT_KEY = "lat"
        private const val LON_KEY = "lon"
    }
    private val geoCodingPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    var lat: String
        get() = geoCodingPreferences.getString(LAT_KEY, null) ?: ""
        set(value) = geoCodingPreferences
            .edit()
            .putString(LAT_KEY, value)
            .apply()

    var lon: String
        get() = geoCodingPreferences.getString(LON_KEY, null) ?: ""
        set(value) = geoCodingPreferences
            .edit()
            .putString(LON_KEY, value)
            .apply()
}