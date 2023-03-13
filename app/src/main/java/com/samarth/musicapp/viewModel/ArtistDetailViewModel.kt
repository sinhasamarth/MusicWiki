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
import com.samarth.musicapp.model.api.response.artistDetails.ArtistDetailResponse
import com.samarth.musicapp.view.pagination.AlbumArtistPagingSource
import com.samarth.musicapp.view.pagination.genersScreen.TracksPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistDetailViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
) : ViewModel() {
    val artistLiveData = MutableLiveData<ResultState<ArtistDetailResponse>>()
    fun topAlbumLiveData(artistName: String) = Pager(
        config = PagingConfig(1),
        pagingSourceFactory = { AlbumArtistPagingSource(apiRepository, artistName = artistName) }
    ).liveData.cachedIn(viewModelScope)

    fun topTrackPaging(artistName: String) = Pager(
        config = PagingConfig(1),
        pagingSourceFactory = { TracksPagingSource(apiRepository, artistName) }
    ).liveData.cachedIn(viewModelScope)


    fun getArtistDetail(artist: String) {
        try {
            artistLiveData.postValue(ResultState.Loading())
            viewModelScope.launch {
                val response = apiRepository.getArtistDetails(artist)
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {
                        artistLiveData.postValue(ResultState.Success(it))
                    }
                }else {
                    artistLiveData.postValue(ResultState.Error("Some Error Occurred"))
                }
            }

        } catch (e: Exception) {
            artistLiveData.postValue(ResultState.Error("Some Error Occurred"))
        }
    }
}