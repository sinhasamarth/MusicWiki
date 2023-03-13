package com.samarth.musicapp.view.adapters.genersDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.samarth.musicapp.databinding.ItemImageWithTextBinding
import com.samarth.musicapp.model.api.response.topArtist.Artist
import com.samarth.musicapp.utils.load
import com.samarth.musicapp.view.SingleItemClicked

class ArtistRecyclerViewAdapter(private val onItemClick: SingleItemClicked<String>) :
    PagingDataAdapter<Artist, ArtistRecyclerViewAdapter.ItemArtistViewHolder>(COMPARATOR) {

    inner class ItemArtistViewHolder(val binding: ItemImageWithTextBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ItemArtistViewHolder, position: Int) {
        holder.binding.apply {
            getItem(position)?.let { data ->
                tvHeading.text = data.name
                // Getting Best Quality Image
                val url = data.image.first().text
                imBg.scaleType = ImageView.ScaleType.CENTER_CROP
                imBg.load(url)
                root.setOnClickListener {
                    onItemClick.onItemClickCallback(data.name)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemArtistViewHolder {
        val binding =
            ItemImageWithTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemArtistViewHolder(binding)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Artist>() {
            override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
                return oldItem == newItem
            }

        }
    }


}