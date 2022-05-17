package com.example.washingcar.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.washingcar.ui.fragments.InformationFragment
import com.example.washingcar.R
import com.example.washingcar.ui.fragments.WeatherFragment
import com.example.washingcar.databinding.ActivityHomeBinding
import com.example.washingcar.ui.fragments.ForecastFragment

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
}