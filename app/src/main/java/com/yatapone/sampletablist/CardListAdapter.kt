package com.yatapone.sampletablist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yatapone.sampletablist.databinding.CardListCellBinding

class CardListAdapter(private val clickListener: (card: Card) -> Unit) : ListAdapter<Card, CardListAdapter.CardListViewHolder>(DiffCallback()) {
    companion object {
        private const val TAG = "CardListAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardListViewHolder {
        val binding = CardListCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CardListViewHolder(private val binding: CardListCellBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(card: Card) {
            // Log.d(TAG, "bind: card=$card")
            binding.cardTitle.text = card.cardTitle
            binding.cardTitle.setOnClickListener { clickListener(card) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Card>() {
        override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem.cardId == newItem.cardId
        }

        override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem == newItem
        }
    }

}