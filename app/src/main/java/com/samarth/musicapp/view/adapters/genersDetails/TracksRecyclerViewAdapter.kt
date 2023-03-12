package com.samarth.musicapp.view.adapters.genersDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.samarth.musicapp.databinding.ItemImageWithTextBinding
import com.samarth.musicapp.model.api.response.topTracks.Track
import com.samarth.musicapp.utils.load

class TracksRecyclerViewAdapter :
    PagingDataAdapter<Track, TracksRecyclerViewAdapter.ItemTrackViewHolder>(COMPARATOR) {

    inner class ItemTrackViewHolder(val binding: ItemImageWithTextBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ItemTrackViewHolder, position: Int) {
        holder.binding.apply {
            getItem(position)?.let { data ->
                tvHeading.text = data.name
                // Getting Best Quality Image
                val url = data.image.last().src
                imBg.scaleType= ImageView.ScaleType.CENTER_CROP
                imBg.load(url)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTrackViewHolder {
        val binding =
            ItemImageWithTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemTrackViewHolder(binding)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Track>() {
            override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

}