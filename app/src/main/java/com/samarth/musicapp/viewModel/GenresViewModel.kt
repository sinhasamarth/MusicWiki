package com.samarth.musicapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.samarth.musicapp.api.ResultState
import com.samarth.musicapp.api.repository.ApiRepository
import com.samarth.musicapp.model.api.response.genreDetails.GenreDetailsResponse
import com.samarth.musicapp.view.pagination.genersScreen.AlbumPagingSource
import com.samarth.musicapp.view.pagination.genersScreen.ArtistPagingSource
import com.samarth.musicapp.view.pagination.genersScreen.TracksPagingSource
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


    val genGenresLiveData = MutableLiveData<ResultState<GenreDetailsResponse>>()

    //    val getAllAlbumLiveData = MutableLiveData<TopAlbumsResponse>()
    fun albumLiveData() = Pager(
        config = PagingConfig(1),
        pagingSourceFactory = { AlbumPagingSource(apiRepository, genreName) }
    ).liveData.cachedIn(viewModelScope)

    fun topArtistLivedata() = Pager(
        config = PagingConfig(1),
        pagingSourceFactory = { ArtistPagingSource(apiRepository, genreName) }
    ).liveData.cachedIn(viewModelScope)

    fun topTracksLivedata() = Pager(
        config = PagingConfig(1),
        pagingSourceFactory = { TracksPagingSource(apiRepository, genreName) }
    ).liveData.cachedIn(viewModelScope)

    fun getGenreDetails(genreName: String) {
        try {
            viewModelScope.launch {
                genGenresLiveData.postValue(ResultState.Loading())
                val response = apiRepository.getGenreDetails(genreName)
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()!!
                    genGenresLiveData.postValue(ResultState.Success(data))
                } else {
                    genGenresLiveData.postValue(ResultState.Error("Some Error Occurred"))
                }
            }

        } catch (e: java.lang.Exception) {
            genGenresLiveData.postValue(ResultState.Error("Some Error Occurred"))
        }
    }


}