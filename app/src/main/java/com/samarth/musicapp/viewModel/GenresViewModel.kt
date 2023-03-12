package com.samarth.musicapp.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samarth.musicapp.api.repository.ApiRepository
import com.samarth.musicapp.model.api.response.genreDetails.GenreDetailsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
) : ViewModel() {


    val genGenresLiveData = MutableLiveData<GenreDetailsResponse>()
//    val genersList = Pager(
//        config = PagingConfig(1),
//        pagingSourceFactory = { TopGenresPaginationSource(apiRepository) }
//    ).liveData.cachedIn(viewModelScope)

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
}