package com.samarth.musicapp.view.fragment.genreFragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.samarth.musicapp.R
import com.samarth.musicapp.databinding.LayoutRecylerviewWithLoaderBinding
import com.samarth.musicapp.model.api.response.topAlbum.Album
import com.samarth.musicapp.view.SingleItemClicked
import com.samarth.musicapp.view.adapters.genersDetails.AlbumRecyclerViewAdapter
import com.samarth.musicapp.viewModel.GenresViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumFragments : Fragment(R.layout.layout_recylerview_with_loader), SingleItemClicked<Album> {
    private val viewModel: GenresViewModel by hiltNavGraphViewModels(R.id.main_nav)

    private lateinit var binding: LayoutRecylerviewWithLoaderBinding
    private lateinit var albumAdapter: AlbumRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LayoutRecylerviewWithLoaderBinding.bind(view)
        albumAdapter = AlbumRecyclerViewAdapter(this)
        binding.rvMain.apply {
            adapter = albumAdapter
            layoutManager = GridLayoutManager(context, 2)

        }
        viewModel.albumLiveData().observe(viewLifecycleOwner) {
            albumAdapter.submitData(lifecycle, it)

        }
    }

    override fun onItemClickCallback(data: Album) {
        findNavController().navigate(
            GenreDetailFragmentDirections.actionGenreDetailFragmentToAlbumDetailFragment(
                AlbumName = data.name,
                artistName = data.artist.name
            )
        )
    }
}