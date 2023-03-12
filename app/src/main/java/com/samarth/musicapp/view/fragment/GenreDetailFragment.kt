package com.samarth.musicapp.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.samarth.musicapp.R
import com.samarth.musicapp.databinding.FragmentGenreDetailsBinding
import com.samarth.musicapp.view.adapters.genersDetails.GenreViewPagerAdapter
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
        GenresViewModel.genreName = genre
        binding.apply {
            tvGenresName.text = navargs.genre
            ibBackButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        activity?.let {
            binding.vpGenre.adapter = GenreViewPagerAdapter(it)
            binding.tabLayout.addOnTabSelectedListener(
                object : OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        binding.vpGenre.currentItem = tab!!.position
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {
                    }

                    override fun onTabReselected(tab: TabLayout.Tab?) {
                    }

                }
            )
            binding.vpGenre.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.tabLayout.getTabAt(position)?.select()
                }
            })
        }
        binding.vpGenre.isSaveFromParentEnabled = false


        initObservers()

    }
    private fun initObservers() {
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