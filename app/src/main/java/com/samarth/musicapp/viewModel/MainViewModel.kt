package com.samarth.musicapp.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samarth.musicapp.api.ResultState
import com.samarth.musicapp.api.repository.ApiRepository
import com.samarth.musicapp.model.api.response.topGenres.TopGenresResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {

    val allGenesLiveData = MutableLiveData<ResultState<TopGenresResponse>>()

    fun getAllGenres() {
        try {
            allGenesLiveData.postValue(ResultState.Loading())
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    val response = apiRepository.getAllGenres()
                    Log.d("response", response.isSuccessful.toString())
                    if (response.isSuccessful && response.body() != null) {
                        response.body()?.let {
                            allGenesLiveData.postValue(ResultState.Success(it))
                        }

                    } else {
                        allGenesLiveData.postValue(ResultState.Error("Some Error Occurred"))
                    }
                }
            }

        } catch (e: Exception) {
            Log.d("APIEE", e.toString())

            allGenesLiveData.postValue(ResultState.Error("Some Error Occurred"))
        }
    }
}