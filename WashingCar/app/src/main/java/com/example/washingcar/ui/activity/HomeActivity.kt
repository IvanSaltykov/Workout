package com.example.washingcar.ui.activity

import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.washingcar.ui.fragments.InformationFragment
import com.example.washingcar.R
import com.example.washingcar.ui.fragments.WeatherFragment
import com.example.washingcar.databinding.ActivityHomeBinding
import com.example.washingcar.preferences.PreferencesManager
import com.example.washingcar.ui.fragments.ForecastFragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var geocodingpreferences: PreferencesManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getLastKnownLocation()
        geocodingpreferences = PreferencesManager(this)
        binding.bottomNavigationHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.itemBottomMenuForecast -> {
                    containerNavigate(ForecastFragment())
                    binding.materialToolbarHome.title = "Прогноз"
                }
                R.id.itemBottomMenuWeather -> {
                    containerNavigate(WeatherFragment())
                    binding.materialToolbarHome.title = "Погода"
                }
                R.id.itemBottomMenuInformation -> {
                    containerNavigate(InformationFragment())
                    binding.materialToolbarHome.title = "Информация"
                }
            }
            true
        }
    }
    private fun containerNavigate(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.containerViewHome, fragment)
            .commit()
    }
    fun getLastKnownLocation() {
        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 101)
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                if (location != null) {
                    Toast.makeText(this, "${location.latitude}, ${location.longitude}", Toast.LENGTH_SHORT).show()
                    geocodingpreferences.lat = location.latitude.toString()
                    geocodingpreferences.lon = location.longitude.toString()
                }
            }
    }
}