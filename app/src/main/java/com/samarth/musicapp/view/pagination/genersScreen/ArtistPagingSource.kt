package com.samarth.musicapp.view.pagination.genersScreen

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.samarth.musicapp.api.repository.ApiRepository
import com.samarth.musicapp.model.api.response.topArtist.Artist

class ArtistPagingSource(private val apiRepository: ApiRepository, private val genreName:String) :
    PagingSource<Int, Artist>() {
    override fun getRefreshKey(state: PagingState<Int, Artist>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Artist> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiRepository.getArtistByGenre(genreName,currentPage)
            val data = response.body()!!.topartists.artist
            val responseData = mutableListOf<Artist>()
            responseData.addAll(data)
            LoadResult.Page(
                data = data,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = if (currentPage == response.body()!!.topartists.attr.totalPages.toInt()) null
                else currentPage.plus(
                    1
                )
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}