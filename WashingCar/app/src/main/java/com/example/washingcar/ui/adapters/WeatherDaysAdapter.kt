package com.example.washingcar.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.washingcar.databinding.ItemWeatherDayBinding
import com.example.washingcar.model.WeatherDay

class WeatherDaysAdapter : RecyclerView.Adapter<WeatherDaysViewHolder>() {
    private val list = mutableListOf<WeatherDay>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherDaysViewHolder {
        return WeatherDaysViewHolder(ItemWeatherDayBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: WeatherDaysViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun submitList(days: List<WeatherDay>) {
        list.clear()
        list.addAll(days)
        notifyDataSetChanged()
    }
}