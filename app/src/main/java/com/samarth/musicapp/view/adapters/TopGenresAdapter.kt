package com.samarth.musicapp.view.adapters

//class TopGenresAdapter() : PagingDataAdapter<Tag, TopGenresAdapter.ItemGenresViewHolder>(COMPARATOR) {
//
//    inner class ItemGenresViewHolder(val binding: ItemGenersBinding) :
//        RecyclerView.ViewHolder(binding.root)
//
//    override fun onBindViewHolder(holder: ItemGenresViewHolder, position: Int) {
//        holder.binding.apply {
//            tvGenresName.text = getItem(position)?.name?.uppercase() ?: "Null"
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemGenresViewHolder {
//        val binding = ItemGenersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return ItemGenresViewHolder(binding)
//    }
//
//    companion object {
//        private val COMPARATOR = object : DiffUtil.ItemCallback<Tag>() {
//            override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean {
//                return oldItem.name == newItem.name
//            }
//
//            override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean {
//                return oldItem == newItem
//            }
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return super.getItemCount()
//    }
//
//}