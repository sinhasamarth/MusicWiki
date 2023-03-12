package com.samarth.musicapp.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.samarth.musicapp.R
import com.samarth.musicapp.databinding.FragmentAlbumDetailsBinding
import com.samarth.musicapp.utils.load
import com.samarth.musicapp.view.SingleItemClicked
import com.samarth.musicapp.view.adapters.GenresAdapter
import com.samarth.musicapp.viewModel.AlbumViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AlbumDetailFragment : Fragment(R.layout.fragment_album_details), SingleItemClicked<String> {

    val navArgs: AlbumDetailFragmentArgs by navArgs()
    lateinit var binding: FragmentAlbumDetailsBinding
    private val albumViewModel: AlbumViewModel by hiltNavGraphViewModels(R.id.main_nav)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAlbumDetailsBinding.bind(view)
        albumViewModel.getAlbumDetails(navArgs.artistName, navArgs.AlbumName)
        initObserver()
        binding.ibBackButton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.rvGenres.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

    }

    private fun initObserver() {
        albumViewModel.albumLiveData.observe(viewLifecycleOwner) {
            it?.album?.let { data ->
                binding.apply {
                    imBanner.load(data.image.last().src)
                    tvAlbumName.text = navArgs.AlbumName
                    tvArtistName.text = navArgs.artistName
                    rvGenres.adapter = GenresAdapter(data.tags.tag, this@AlbumDetailFragment)
                    tvDesc.text = data.wiki.summary
                }
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