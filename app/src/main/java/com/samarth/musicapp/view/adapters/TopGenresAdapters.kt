package com.samarth.musicapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samarth.musicapp.databinding.ItemGenersBinding
import com.samarth.musicapp.model.api.response.topGenres.Tag
import com.samarth.musicapp.view.SingleItemClicked

class TopGenresAdapters(
    val data: List<Tag>,
    val listener: SingleItemClicked<String>,
    private var showLess: Boolean = true
) :
    RecyclerView.Adapter<TopGenresAdapters.ItemGenresViewHolder>() {

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

    override fun getItemCount() = if (showLess && data.size > 10) 10 else data.size


    fun setLessFlag(flag: Boolean = showLess) {
        showLess = flag
        notifyDataSetChanged()
    }
}