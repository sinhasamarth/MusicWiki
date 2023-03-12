package com.samarth.musicapp.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samarth.musicapp.R
import com.samarth.musicapp.databinding.FragmentArtistDetailsBinding
import com.samarth.musicapp.model.api.response.albumByArtist.Album
import com.samarth.musicapp.utils.load
import com.samarth.musicapp.view.SingleItemClicked
import com.samarth.musicapp.view.adapters.AlbumByArtistRecyclerViewAdapter
import com.samarth.musicapp.view.adapters.GenresAdapter
import com.samarth.musicapp.view.adapters.genersDetails.TracksRecyclerViewAdapter
import com.samarth.musicapp.viewModel.ArtistDetailViewModel

class ArtistDetailFragment : Fragment(R.layout.fragment_artist_details), SingleItemClicked<String> {

    private val navArgs: ArtistDetailFragmentArgs by navArgs()
    lateinit var binding: FragmentArtistDetailsBinding
    private val artistDetailViewModel: ArtistDetailViewModel by hiltNavGraphViewModels(R.id.main_nav)
    private lateinit var topTrackAdapter: TracksRecyclerViewAdapter
    private lateinit var topAlbumAdapter: AlbumByArtistRecyclerViewAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArtistDetailsBinding.bind(view)
        artistDetailViewModel.getArtistDetail(navArgs.artistName)
        initObserver()
        setUpView()

    }

    private fun setUpView() {
        binding.ibBackButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.rvGenres.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL, false
        )
        binding.rvTopTracks.layoutManager = getHorizontalLayout()
        binding.rvTopAlbum.layoutManager = getHorizontalLayout()
        topTrackAdapter = TracksRecyclerViewAdapter()
        binding.rvTopTracks.adapter = topTrackAdapter
        topAlbumAdapter = AlbumByArtistRecyclerViewAdapter(object : SingleItemClicked<Album> {
            override fun onItemClickCallback(data: Album) {
                findNavController().navigate(
                    ArtistDetailFragmentDirections.actionArtistDetailFragmentToAlbumDetailFragment(
                        data.artist.name,
                        data.name
                    )
                )
            }

        })
        binding.rvTopAlbum.adapter = topAlbumAdapter
    }

    private fun getHorizontalLayout() =
        object : LinearLayoutManager(requireContext(), HORIZONTAL, false) {
            override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
                lp?.width = 2 * (width / 5)
                return true
            }
        }

    private fun initObserver() {
        artistDetailViewModel.artistLiveData.observe(viewLifecycleOwner) {
            it?.artist?.let { data ->
                binding.apply {
                    imBanner.load(data.image.last().link)
                    tvArtistName.text = navArgs.artistName
                    rvGenres.adapter = GenresAdapter(data.tags.tag, this@ArtistDetailFragment)
                    tvDesc.text = data.bio.summary
                }
            }
        }
        artistDetailViewModel.topTrackPaging(navArgs.artistName).observe(
            viewLifecycleOwner
        ) {
            topTrackAdapter.submitData(
                lifecycle, it
            )
        }

        artistDetailViewModel.topAlbumLiveData(navArgs.artistName).observe(viewLifecycleOwner) {
            it?.let {
                topAlbumAdapter.submitData(
                    lifecycle, it
                )
            }

        }
    }


    override fun onItemClickCallback(data: String) {
        findNavController().navigate(
            AlbumDetailFragmentDirections.actionAlbumDetailFragmentToGenreDetailFragment(
                data
            )
        )
    }

}