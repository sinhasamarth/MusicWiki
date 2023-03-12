package com.samarth.musicapp.view.fragment.genreFragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.samarth.musicapp.R
import com.samarth.musicapp.databinding.LayoutRecylerviewWithLoaderBinding
import com.samarth.musicapp.view.adapters.genersDetails.TracksRecyclerViewAdapter
import com.samarth.musicapp.viewModel.GenresViewModel

class TracksFragment : Fragment(R.layout.layout_recylerview_with_loader) {
    private val viewModel: GenresViewModel by hiltNavGraphViewModels(R.id.main_nav)

    private lateinit var binding: LayoutRecylerviewWithLoaderBinding
    private lateinit var trackAdapter: TracksRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LayoutRecylerviewWithLoaderBinding.bind(view)
        trackAdapter = TracksRecyclerViewAdapter()
        binding.rvMain.apply {
            adapter = trackAdapter
            layoutManager = GridLayoutManager(context, 2)

        }
        viewModel.topTracksLivedata().observe(viewLifecycleOwner) {
            trackAdapter.submitData(lifecycle, it)
        }
    }
}