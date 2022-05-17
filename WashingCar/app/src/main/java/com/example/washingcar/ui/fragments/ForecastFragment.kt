package com.example.washingcar.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.washingcar.R
import com.example.washingcar.databinding.FragmentForecastBinding
import com.example.washingcar.model.GeocodingResponse
import com.example.washingcar.request_server.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastFragment : Fragment() {
    lateinit var binding: FragmentForecastBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForecastBinding.inflate(inflater, container, false)
        binding.button.setOnClickListener {
            ApiService.retrofit.getGeocoding("Moscow", "53488f31a317113b69d05578dfe18728").enqueue(
                object : Callback<GeocodingResponse> {
                    override fun onResponse(
                        call: Call<GeocodingResponse>,
                        response: Response<GeocodingResponse>
                    ) {
                        Toast.makeText(requireContext(), response.code().toString(), Toast.LENGTH_SHORT).show()
                    }

                    override fun onFailure(call: Call<GeocodingResponse>, t: Throwable) {
                        Toast.makeText(requireContext(), t.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
        return binding.root
    }
}