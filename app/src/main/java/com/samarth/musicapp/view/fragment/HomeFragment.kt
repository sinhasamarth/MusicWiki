package com.samarth.musicapp.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.samarth.musicapp.R
import com.samarth.musicapp.api.ResultState
import com.samarth.musicapp.databinding.FragmentHomeBinding
import com.samarth.musicapp.model.api.response.topGenres.Tag
import com.samarth.musicapp.utils.gone
import com.samarth.musicapp.utils.showShortToast
import com.samarth.musicapp.utils.visibleIt
import com.samarth.musicapp.view.SingleItemClicked
import com.samarth.musicapp.view.adapters.TopGenresAdapters
import com.samarth.musicapp.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), SingleItemClicked<String> {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MainViewModel by hiltNavGraphViewModels(R.id.main_nav)

    private lateinit var topGenresAdapters: TopGenresAdapters
    private var showLess = true
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentHomeBinding.bind(view)
        viewModel.getAllGenres()
        initUI()
        initObservers()
    }

    private fun initUI() {
        binding.apply {
            button.setOnClickListener {
                if (showLess) {
                    button.rotation = 180.00f
                } else {
                    button.rotation = 0.00f

                }
                showLess = !showLess

                topGenresAdapters.setLessFlag(showLess)
            }


        }
    }

    private fun initObservers() {
        try {
            viewModel.allGenesLiveData.observe(viewLifecycleOwner) {
                when (it) {
                    is ResultState.Loading -> binding.progressBar.visibleIt()
                    is ResultState.Success -> {
                        it.data?.run {
                            setRecyclerView(this.tags.tag)
                            binding.progressBar.gone()
                        }
                    }
                    is ResultState.Error -> {
                        requireContext().showShortToast(it.message.toString())
                    }
                }
            }
        } catch (e: Exception) {
            requireContext().showShortToast("ERROR")
        }


    }

    private fun setRecyclerView(data: List<Tag>) {
        binding.rvGenres.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            topGenresAdapters =
                TopGenresAdapters(data, this@HomeFragment)
            adapter = topGenresAdapters

        }
    }

    override fun onItemClickCallback(data: String) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToGenreDetailFragment(
                data
            )
        )
    }

}