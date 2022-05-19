package com.example.washingcar.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.washingcar.databinding.FragmentForecastBinding
import com.example.washingcar.model.WeatherResponse
import com.example.washingcar.preferences.PreferencesManager
import com.example.washingcar.request_server.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ForecastFragment : Fragment() {
    lateinit var preferences: PreferencesManager
    lateinit var binding: FragmentForecastBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForecastBinding.inflate(inflater, container, false)
        preferences = PreferencesManager(requireContext())
        binding.button.setOnClickListener {
            ApiService.retrofit.getWeather(
                preferences.lat.toDouble(),
                preferences.lon.toDouble(),
                listOf("temperature_2m")
            ).enqueue(
                object : Callback<WeatherResponse> {
                    override fun onResponse(
                        call: Call<WeatherResponse>,
                        response: Response<WeatherResponse>
                    ) {
                        val responsse = response.body()
                        Log.d("ress", responsse.toString())
                        Toast.makeText(requireContext(), "${responsse?.latitude}, ${responsse?.longitude}", Toast.LENGTH_SHORT).show()
                    }

                    override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                        Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
        return binding.root
    }
}