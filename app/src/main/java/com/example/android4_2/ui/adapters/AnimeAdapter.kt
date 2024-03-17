package com.example.android4_2.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android4_2.databinding.ItemAnimeBinding
import com.example.android4_2.remote.models.Data
import com.example.android4_2.ui.models.AnimeData

class AnimeAdapter : PagingDataAdapter<Data, AnimeAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemAnimeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(animeData: Data) = with(binding) {
            Glide.with(ivItem.context)
                .load("https://media.kitsu.io/anime/poster_images/1/${animeData.id}original.jpg")
                .into(ivItem)
            textItem.text = animeData.type
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAnimeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem == newItem
            }
        }
    }
}