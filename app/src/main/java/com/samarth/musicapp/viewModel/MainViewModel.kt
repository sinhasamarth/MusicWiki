package com.samarth.musicapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.samarth.musicapp.api.Result
import com.samarth.musicapp.api.repository.ApiRepository
import com.samarth.musicapp.model.api.response.TopGenresResponse
import com.samarth.musicapp.view.pagination.TopGenresPaginationSource
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

    val allGenesLiveData = MutableLiveData<Result<TopGenresResponse>>()
    val genersList = Pager(
        config = PagingConfig(1),
        pagingSourceFactory =  {TopGenresPaginationSource(apiRepository)}
    ).liveData.cachedIn(viewModelScope)

    fun getAllGenres() {
        CoroutineScope(Dispatchers.IO).launch {
            allGenesLiveData.postValue(Result.Loading())
            val response = apiRepository.getAllGenres(1)
            if (response.isSuccessful && response.body() != null) {
                val data = response.body()!!
                allGenesLiveData.postValue(Result.Success(data))
            }
        }

    }
}