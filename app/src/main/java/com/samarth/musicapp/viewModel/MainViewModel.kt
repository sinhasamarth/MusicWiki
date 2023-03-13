package com.samarth.musicapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samarth.musicapp.api.ResultState
import com.samarth.musicapp.api.repository.ApiRepository
import com.samarth.musicapp.model.api.response.topGenres.TopGenresResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {

    val allGenesLiveData = MutableLiveData<ResultState<TopGenresResponse>>()
//    val genersList = Pager(
//        config = PagingConfig(1),
//        pagingSourceFactory = { TopGenresPaginationSource(apiRepository) }
//    ).liveData.cachedIn(viewModelScope)

    fun getAllGenres() {
        try {
            allGenesLiveData.postValue(ResultState.Loading())
            viewModelScope.launch {
                val response = apiRepository.getAllGenres()
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {
                        allGenesLiveData.postValue(ResultState.Success(it))
                    }

                } else {
                    allGenesLiveData.postValue(ResultState.Error("Some Error Occurred"))
                }
            }

        } catch (e: Exception) {
            allGenesLiveData.postValue(ResultState.Error("Some Error Occurred"))
        }
    }
}