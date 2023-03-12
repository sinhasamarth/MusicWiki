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
    val artistLiveData = MutableLiveData<ArtistDetailResponse>()
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
            viewModelScope.launch {

                val response = apiRepository.getArtistDetails(artist)
                if (response.isSuccessful) {
                    artistLiveData.postValue(response.body())
                }
            }

        } catch (e: java.lang.Exception) {
            Log.d("API", e.toString())
        }
    }
}