package com.samarth.musicapp.view.fragment.genreFragments

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
import com.samarth.musicapp.api.ResultState
import com.samarth.musicapp.databinding.FragmentGenreDetailsBinding
import com.samarth.musicapp.utils.gone
import com.samarth.musicapp.utils.showShortToast
import com.samarth.musicapp.utils.visibleIt
import com.samarth.musicapp.view.adapters.genersDetails.GenreViewPagerAdapter
import com.samarth.musicapp.viewModel.GenresViewModel

class GenreDetailFragment : Fragment(R.layout.fragment_genre_details) {
    private val navArgs: GenreDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentGenreDetailsBinding
    private val viewModel: GenresViewModel by hiltNavGraphViewModels(R.id.main_nav)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGenreDetailsBinding.bind(view)
        initApiCall()
        initUI()
        initObservers()
    }

    private fun initApiCall() {
        val genre = navArgs.genre
        viewModel.getGenreDetails(genre)
        GenresViewModel.genreName = genre
    }

    private fun initUI() {
        setViewPager()
        binding.apply {
            tvGenresName.text = navArgs.genre.uppercase()
            ibBackButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setViewPager() {
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
    }

    private fun initObservers() {
        viewModel.genGenresLiveData.observe(
            viewLifecycleOwner
        ) {
            when (it) {
                is ResultState.Loading -> binding.progressBar.visibleIt()
                is ResultState.Success -> {
                    it.data?.run {
                        binding.tvGenreDesc.text = this.tag.wiki.summary
                        binding.progressBar.gone()
                    }
                }
                is ResultState.Error -> {
                    requireContext().showShortToast(it.message.toString())
                }
            }
        }
    }

}