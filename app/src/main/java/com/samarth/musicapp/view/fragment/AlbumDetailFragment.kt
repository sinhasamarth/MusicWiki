package com.samarth.musicapp.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.samarth.musicapp.MainActivity
import com.samarth.musicapp.R
import com.samarth.musicapp.api.ResultState
import com.samarth.musicapp.databinding.FragmentAlbumDetailsBinding
import com.samarth.musicapp.model.api.response.albumDetails.AlbumDetailsResponse
import com.samarth.musicapp.utils.gone
import com.samarth.musicapp.utils.load
import com.samarth.musicapp.utils.showShortToast
import com.samarth.musicapp.utils.visibleIt
import com.samarth.musicapp.view.SingleItemClicked
import com.samarth.musicapp.view.adapters.GenresAdapter
import com.samarth.musicapp.viewModel.AlbumViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AlbumDetailFragment : Fragment(R.layout.fragment_album_details), SingleItemClicked<String> {

    private val navArgs: AlbumDetailFragmentArgs by navArgs()
    lateinit var binding: FragmentAlbumDetailsBinding
    private val albumViewModel: AlbumViewModel by hiltNavGraphViewModels(R.id.main_nav)
    private var isAlreadyFetched = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAlbumDetailsBinding.bind(view)
        initObserver()
        initUi()


    }

    private fun initUi() {
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

        MainActivity.connectionLiveData.observe(viewLifecycleOwner) {
            if (!isAlreadyFetched && it) try {
                albumViewModel.getAlbumDetails(navArgs.artistName, navArgs.AlbumName)
            } catch (_: Exception) {
            }
        }


        albumViewModel.albumLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ResultState.Loading -> binding.progressBar.visibleIt()
                is ResultState.Success -> {
                    isAlreadyFetched = true
                    it.data?.run {
                        setUiWithData(this)
                        binding.progressBar.gone()
                    }
                }
                is ResultState.Error -> {
                    requireContext().showShortToast(it.message.toString())
                }
            }
        }

    }

    private fun setUiWithData(response: AlbumDetailsResponse) {
        response.album.let { data ->
            binding.apply {
                try {
                    imBanner.load(data.image.last().src)
                    imBanner.scaleType = ImageView.ScaleType.CENTER_CROP
                    tvAlbumName.text = navArgs.AlbumName
                    tvArtistName.text = navArgs.artistName
                    rvGenres.adapter = GenresAdapter(data.tags.tag, this@AlbumDetailFragment)
                    tvDesc.text = data.wiki.summary
                } catch (_: Exception) {
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