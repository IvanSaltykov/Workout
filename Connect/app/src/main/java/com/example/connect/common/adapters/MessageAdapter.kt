package com.example.connect.common.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.connect.databinding.ItemMessegeBinding
import com.example.connect.models.UserMessege

class MessageAdapter : ListAdapter<UserMessege, MessageAdapter.MessageViewHolder>(ItemComparetion()) {
    class MessageViewHolder(
        private val binding: ItemMessegeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(message: UserMessege) = with(binding) {
            textViewMessage.text = message.message
            textViewName.text = message.name
        }
    }
    class ItemComparetion : DiffUtil.ItemCallback<UserMessege>() {
        override fun areItemsTheSame(oldItem: UserMessege, newItem: UserMessege): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: UserMessege, newItem: UserMessege): Boolean {
            return oldItem == newItem
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(ItemMessegeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}