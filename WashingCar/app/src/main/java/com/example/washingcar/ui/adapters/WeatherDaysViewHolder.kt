package com.example.washingcar.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.washingcar.databinding.ItemWeatherDayBinding
import com.example.washingcar.model.WeatherDay

class WeatherDaysViewHolder(private val binding: ItemWeatherDayBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(weatherDay: WeatherDay) = with(binding) {
        textViewDay.text = weatherDay.day
        textWeatherDay.text = weatherDay.weather
        imageViewWeather.setBackgroundResource(weatherDay.imageWeather)
        root.setOnClickListener {

        }
    }
}