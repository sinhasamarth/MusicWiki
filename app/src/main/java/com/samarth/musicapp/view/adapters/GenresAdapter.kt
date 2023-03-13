package com.samarth.musicapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samarth.musicapp.databinding.ItemGenersBinding
import com.samarth.musicapp.view.SingleItemClicked
import com.samarth.musicapp.model.api.response.albumDetails.Tag


class GenresAdapter(val data: List<Tag>, val listener: SingleItemClicked<String>) :
    RecyclerView.Adapter<GenresAdapter.ItemGenresViewHolder>() {

    inner class ItemGenresViewHolder(val binding: ItemGenersBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ItemGenresViewHolder, position: Int) {
        holder.binding.apply {
            val data = data[position].name
            tvGenresName.text = data
            tvGenresName.setOnClickListener {
                listener.onItemClickCallback(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemGenresViewHolder {
        val binding = ItemGenersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemGenresViewHolder(binding)
    }

    override fun getItemCount() = data.size
}