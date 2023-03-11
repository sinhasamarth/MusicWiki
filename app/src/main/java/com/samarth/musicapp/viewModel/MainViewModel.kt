package com.samarth.musicapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samarth.musicapp.api.Result
import com.samarth.musicapp.api.repository.ApiRepository
import com.samarth.musicapp.model.api.response.TopGenresResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {

    init {
        getAllGenres()
    }

     val allGenersLiveData = MutableLiveData<Result<TopGenresResponse>>()

    fun getAllGenres() {
        CoroutineScope(Dispatchers.IO).launch {
            allGenersLiveData.postValue(Result.Loading())
            val response = apiRepository.getAllGeneres()
            if (response.isSuccessful && response.body() != null) {
                val data = response.body()!!
                allGenersLiveData.postValue(Result.Success(data))
            }
        }

    }
}