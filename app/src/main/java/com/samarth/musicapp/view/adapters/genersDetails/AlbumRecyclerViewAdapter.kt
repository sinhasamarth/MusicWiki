package com.samarth.musicapp.view.adapters.genersDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.samarth.musicapp.databinding.ItemImageWithTextBinding
import com.samarth.musicapp.model.api.response.topAlbum.Album
import com.samarth.musicapp.utils.load

class AlbumRecyclerViewAdapter :
    PagingDataAdapter<Album, AlbumRecyclerViewAdapter.ItemAlbumViewHolder>(COMPARATOR) {

    inner class ItemAlbumViewHolder(val binding: ItemImageWithTextBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ItemAlbumViewHolder, position: Int) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAlbumViewHolder {
        val binding =
            ItemImageWithTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemAlbumViewHolder(binding)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Album>() {
            override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

}