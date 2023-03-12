package com.samarth.musicapp.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.samarth.musicapp.R
import com.samarth.musicapp.databinding.FragmentHomeBinding
import com.samarth.musicapp.view.SingleItemClicked
import com.samarth.musicapp.view.adapters.TopGenresAdapters
import com.samarth.musicapp.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MainViewModel by  hiltNavGraphViewModels(R.id.main_nav)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentHomeBinding.bind(view)
        viewModel.getAllGenres()


        binding.apply {
            button.setOnClickListener {
                viewModel.getAllGenres()
            }


            viewModel.allGenesLiveData.observe(viewLifecycleOwner) {
                Log.d("API", it.toString())

                rvGenres.apply {
                    layoutManager = GridLayoutManager(context, 2)
                    setHasFixedSize(true)
                    adapter = TopGenresAdapters(it!!.tags.tag, object : SingleItemClicked<String> {
                        override fun onItemClickCallback(data: String) {
                            findNavController().navigate(
                                HomeFragmentDirections.actionHomeFragmentToGenreDetailFragment(
                                    data
                                )
                            )
                        }
                    })

                }
            }
        }
    }
//    override fun onSaveInstanceState(savedInstanceState: Bundle) {
//        super.onSaveInstanceState(savedInstanceState)
//        savedInstanceState.putBundle("nav_state", fragment.findNavController().saveState())
//    }
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        fragment.findNavController().restoreState(savedInstanceState.getBundle("nav_state"))
//    }

}