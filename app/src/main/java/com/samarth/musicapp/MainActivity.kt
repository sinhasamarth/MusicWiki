package com.samarth.musicapp

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.samarth.musicapp.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.allGenersLiveData.observe(
            this
        ) {
            when (it) {
                is com.samarth.musicapp.api.Result.Loading -> {
                    Log.d("APISERVICE", "ISLOADING")
                }
                is com.samarth.musicapp.api.Result.Success->{
                    Log.d("APISERVICE", "DONE ")
                }
                is com.samarth.musicapp.api.Result.Error->{

                }
            }
        }
    }
}