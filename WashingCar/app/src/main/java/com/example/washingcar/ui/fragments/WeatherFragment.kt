package com.example.washingcar.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.washingcar.R
import com.example.washingcar.databinding.FragmentWeatherBinding
import com.example.washingcar.model.WeatherDay
import com.example.washingcar.ui.adapters.WeatherDaysAdapter

class WeatherFragment : Fragment() {
    lateinit var binding: FragmentWeatherBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        val days = listOf(
            WeatherDay("1","1", R.drawable.ic_cloud),
            WeatherDay("2","1", R.drawable.ic_cloud),
            WeatherDay("3","1", R.drawable.ic_cloud),
            WeatherDay("4","1", R.drawable.ic_cloud),
            WeatherDay("5","1", R.drawable.ic_cloud),
            WeatherDay("6","1", R.drawable.ic_cloud),
            WeatherDay("7","1", R.drawable.ic_cloud),
            WeatherDay("8","1", R.drawable.ic_cloud),
            WeatherDay("9","1", R.drawable.ic_cloud),
            WeatherDay("10","1", R.drawable.ic_cloud)
        )
        val adapter = WeatherDaysAdapter()
        binding.recyclerViewWeather.adapter = adapter
        adapter.submitList(days)
        return binding.root
    }
}