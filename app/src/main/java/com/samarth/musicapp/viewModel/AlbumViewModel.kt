package com.samarth.musicapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samarth.musicapp.api.ResultState
import com.samarth.musicapp.api.repository.ApiRepository
import com.samarth.musicapp.model.api.response.albumDetails.AlbumDetailsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
) : ViewModel() {

    val albumLiveData = MutableLiveData<ResultState<AlbumDetailsResponse>>()

    fun getAlbumDetails(artist: String, album: String) {
        try {
            albumLiveData.postValue(ResultState.Loading())
            viewModelScope.launch {
                val response = apiRepository.getAlbumDetails(artist, album)
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let { albumLiveData.postValue(ResultState.Success(it)) }
                } else {
                    albumLiveData.postValue(ResultState.Error("Some Error Occurred"))

                }
            }

        } catch (e: Exception) {
            albumLiveData.postValue(ResultState.Error("Some Error Occurred"))
        }
    }
}