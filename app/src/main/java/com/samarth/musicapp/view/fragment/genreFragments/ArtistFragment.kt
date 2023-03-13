package com.samarth.musicapp.view.fragment.genreFragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.samarth.musicapp.R
import com.samarth.musicapp.databinding.LayoutRecylerviewWithLoaderBinding
import com.samarth.musicapp.view.SingleItemClicked
import com.samarth.musicapp.view.adapters.genersDetails.ArtistRecyclerViewAdapter
import com.samarth.musicapp.viewModel.GenresViewModel

class ArtistFragment : Fragment(R.layout.layout_recylerview_with_loader),
    SingleItemClicked<String> {
    private val viewModel: GenresViewModel by hiltNavGraphViewModels(R.id.main_nav)

    private lateinit var binding: LayoutRecylerviewWithLoaderBinding
    private lateinit var artistAdapter: ArtistRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LayoutRecylerviewWithLoaderBinding.bind(view)
        artistAdapter = ArtistRecyclerViewAdapter(this)
        binding.rvMain.apply {
            adapter = artistAdapter
            layoutManager = GridLayoutManager(context, 2)

        }
        viewModel.topArtistLivedata().observe(viewLifecycleOwner) {
            artistAdapter.submitData(lifecycle, it)

        }
    }

    override fun onItemClickCallback(data: String) {
        findNavController().navigate(
            GenreDetailFragmentDirections.actionGenreDetailFragmentToArtistDetailFragment(data)
        )
    }
}