package com.example.washingcar.ui.adapters

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.washingcar.databinding.ItemWeatherDayBinding
import com.example.washingcar.model.WeatherDay
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class WeatherDaysViewHolder(private val binding: ItemWeatherDayBinding) : RecyclerView.ViewHolder(binding.root) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(weatherDay: WeatherDay) = with(binding) {
        textViewDay.text = LocalDate.parse(weatherDay.day).format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))
        textWeatherDay.text = weatherDay.weather
        imageViewWeather.setBackgroundResource(weatherDay.imageWeather)
        root.setOnClickListener {

        }
    }
}