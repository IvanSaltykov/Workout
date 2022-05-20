package com.example.washingcar.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.washingcar.databinding.ItemInformationBinding
import com.example.washingcar.model.Information

class InfoAdapter : RecyclerView.Adapter<InfoViewHolder>() {
    private val list = mutableListOf<Information>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        return InfoViewHolder(ItemInformationBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun submitList(infos: List<Information>) {
        list.clear()
        list.addAll(infos)
        notifyDataSetChanged()
    }
}