package com.samarth.musicapp

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.samarth.musicapp.databinding.FragmentHomeBinding
import com.samarth.musicapp.view.adapters.TopGenresAdapter
import com.samarth.musicapp.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val topGenresAdapter = TopGenresAdapter()

        binding.apply {
            rvGenres.apply {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = topGenresAdapter

            }

            viewModel.genersList.observe(
                this@MainActivity,
            ) {
                topGenresAdapter.submitData(lifecycle, it)
            }
        }

        viewModel.allGenesLiveData.observe(
            this
        ) {
            when (it) {
                is com.samarth.musicapp.api.Result.Loading -> {
                    Log.d("APISERVICE", "ISLOADING")
                }
                is com.samarth.musicapp.api.Result.Success -> {
                    Log.d("APISERVICE", "DONE ")
                }
                is com.samarth.musicapp.api.Result.Error -> {

                }
            }
        }
    }
}