package com.example.washingcar.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.washingcar.R
import com.example.washingcar.databinding.ItemInformationBinding
import com.example.washingcar.model.Information

class InfoViewHolder(private val binding: ItemInformationBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(info: Information) = with(binding) {
        textViewInfoTitle.text = info.title
        textViewInfo.text = info.text
        imageViewVisible.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
        root.setOnClickListener {
            when (textViewInfo.visibility) {
                View.GONE -> {
                    textViewInfo.visibility = View.VISIBLE
                    imageViewVisible.setBackgroundResource(R.drawable.ic_arrow_up)
                }
                View.VISIBLE -> {
                    textViewInfo.visibility = View.GONE
                    imageViewVisible.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                }
            }
        }
    }
}