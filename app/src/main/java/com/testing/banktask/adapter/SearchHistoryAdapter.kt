package com.testing.banktask.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.testing.banktask.databinding.ItemListSearchHistoryBinding
import com.testing.common.dbentities.SearchHistoryData

class SearchHistoryAdapter : ListAdapter<SearchHistoryData, SearchHistoryAdapter.SearchHistoryViewHolder>(SearchHistoryCallback) {

    class SearchHistoryViewHolder(val binding: ItemListSearchHistoryBinding) : RecyclerView.ViewHolder(binding.root)

    object SearchHistoryCallback : DiffUtil.ItemCallback<SearchHistoryData>() {
        override fun areItemsTheSame(
            oldItem: SearchHistoryData,
            newItem: SearchHistoryData
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SearchHistoryData,
            newItem: SearchHistoryData
        ): Boolean {
            return oldItem == newItem
        }
    }

   // val diffAsync = AsyncListDiffer<SearchHistoryData>(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder {
        return SearchHistoryViewHolder(
            ItemListSearchHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.apply {
            searchHistoryTextView.text = item.queryValue
            dateSearchHistoryTextView.text = item.requestDate
        }
    }
}