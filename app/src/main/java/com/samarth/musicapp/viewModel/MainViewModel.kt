package com.samarth.musicapp.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samarth.musicapp.api.repository.ApiRepository
import com.samarth.musicapp.model.api.response.topGenres.TopGenresResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {

    val allGenesLiveData = MutableLiveData<TopGenresResponse>()
//    val genersList = Pager(
//        config = PagingConfig(1),
//        pagingSourceFactory = { TopGenresPaginationSource(apiRepository) }
//    ).liveData.cachedIn(viewModelScope)

    fun getAllGenres() {
        try {
          viewModelScope.launch {

                val response = apiRepository.getAllGenres(1)
                if (response.isSuccessful) {
                    val data = response.body()!!
                    allGenesLiveData.postValue (response.body())
                }
            }

        } catch (e: java.lang.Exception) {
            Log.d("API", e.toString())
        }
    }
}