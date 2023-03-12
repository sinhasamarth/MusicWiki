package com.samarth.musicapp.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.samarth.musicapp.R
import com.samarth.musicapp.databinding.FragmentGenreDetailsBinding
import com.samarth.musicapp.viewModel.GenresViewModel

class GenreDetailFragment : Fragment(R.layout.fragment_genre_details) {
    val navargs: GenreDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentGenreDetailsBinding

    private val viewModel: GenresViewModel by hiltNavGraphViewModels(R.id.main_nav)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val genre = navargs.genre
        binding = FragmentGenreDetailsBinding.bind(view)
        viewModel.getGenreDetails(genre)
        binding.apply {
            tvGenresName.text = navargs.genre
            ibBackButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        viewModel.genGenresLiveData.observe(
            viewLifecycleOwner
        ) {
            it?.let {
                binding.apply {
                    tvGenreDesc.text = it.tag.wiki.summary
                }
            }
        }

    }
}