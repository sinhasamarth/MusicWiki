package com.samarth.musicapp.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samarth.musicapp.api.repository.ApiRepository
import com.samarth.musicapp.model.api.response.albumDetails.AlbumDetailsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
) : ViewModel() {

    val albumLiveData = MutableLiveData<AlbumDetailsResponse>()

    fun getAlbumDetails(artist: String, album: String) {
        try {
            viewModelScope.launch {

                val response = apiRepository.getAlbumDetails(artist, album)
                if (response.isSuccessful) {
                    albumLiveData.postValue(response.body())
                }
            }

        } catch (e: java.lang.Exception) {
            Log.d("API", e.toString())
        }
    }
}