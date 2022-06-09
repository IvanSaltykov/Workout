package com.example.washingcar.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.washingcar.R
import com.example.washingcar.Weather
import com.example.washingcar.databinding.FragmentWeatherBinding
import com.example.washingcar.model.WeatherDay
import com.example.washingcar.model.WeatherResponse
import com.example.washingcar.preferences.PreferencesManager
import com.example.washingcar.request_server.ApiService
import com.example.washingcar.ui.adapters.WeatherDaysAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherFragment : Fragment() {
    lateinit var binding: FragmentWeatherBinding
    lateinit var preferences: PreferencesManager
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        preferences = PreferencesManager(requireContext())
        val adapter = WeatherDaysAdapter()
        binding.recyclerViewWeather.adapter = adapter
        ApiService.retrofit.getWeather(
            preferences.lat.toDouble(),
            preferences.lon.toDouble(),
//                listOf("temperature_2m")
            listOf("temperature_2m_max", "temperature_2m_min"),
            "Europe/Moscow"
        ).enqueue(
            object : Callback<WeatherResponse> {
                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    val responsse = response.body()
                    Weather(responsse!!)
                    Log.d("ress", responsse.toString())
                    Toast.makeText(requireContext(), "${responsse.latitude}, ${responsse.longitude}", Toast.LENGTH_SHORT).show()
                    val time = responsse.daily.time
                    var ind = 0
                    time.forEach {
                        adapter.addItem(
                            WeatherDay(
                                it,
                                "${responsse.daily.temperature2mMin[ind]}...${responsse.daily.temperature2mMax[ind]}",
                                R.drawable.ic_cloud
                            )
                        )
                        ind++
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                }
            }
        )
        return binding.root
    }
}