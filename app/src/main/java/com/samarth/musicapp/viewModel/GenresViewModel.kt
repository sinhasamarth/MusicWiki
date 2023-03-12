package com.samarth.musicapp.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.samarth.musicapp.api.repository.ApiRepository
import com.samarth.musicapp.model.api.response.genreDetails.GenreDetailsResponse
import com.samarth.musicapp.view.pagination.AlbumPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
) : ViewModel() {
    companion object {
        var genreName = "disco"
    }


    val genGenresLiveData = MutableLiveData<GenreDetailsResponse>()
//    val getAllAlbumLiveData = MutableLiveData<TopAlbumsResponse>()
    val albumLiveData = Pager(
        config = PagingConfig(1),
        pagingSourceFactory = { AlbumPagingSource(apiRepository, genreName) }
    ).liveData.cachedIn(viewModelScope)


    fun getGenreDetails(genreName: String) {
        try {
            viewModelScope.launch {

                val response = apiRepository.getGenreDetails(genreName)
                if (response.isSuccessful) {
                    genGenresLiveData.postValue(response.body())
                }
            }

        } catch (e: java.lang.Exception) {
            Log.d("API", e.toString())
        }
    }

//    fun getTopAlbum() {
//        try {
//            viewModelScope.launch {
//                val response = apiRepository.getAllAlbum(genere)
//                if (response.isSuccessful) {
//                    getAllAlbumLiveData.postValue(response.body())
//                }
//            }
//
//        } catch (e: java.lang.Exception) {
//            Log.d("API", e.toString())
//        }
//    }
}