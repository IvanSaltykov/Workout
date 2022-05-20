package com.example.washingcar.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.washingcar.R
import com.example.washingcar.databinding.FragmentInformationBinding
import com.example.washingcar.model.Information
import com.example.washingcar.ui.adapters.InfoAdapter

class InformationFragment : Fragment() {
    lateinit var binding: FragmentInformationBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInformationBinding.inflate(inflater, container, false)
        val infos = listOf(
            Information(
                getString(R.string.info1_Title),
                getString(R.string.info1_text)
            ),
            Information(
                getString(R.string.info2_Title),
                getString(R.string.info2_text)
            )
        )
        val adapter = InfoAdapter()
        binding.recyclerViewInfo.adapter = adapter
        adapter.submitList(infos)
        return binding.root
    }
}