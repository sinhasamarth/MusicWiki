package com.samarth.musicapp.view.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.samarth.musicapp.api.repository.ApiRepository
import com.samarth.musicapp.model.api.response.topGenres.Tag

class TopGenresPaginationSource(private val apiRepository: ApiRepository) :
    PagingSource<Int, Tag>() {
    override fun getRefreshKey(state: PagingState<Int, Tag>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Tag> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiRepository.getAllGenres(currentPage)
            val data = response.body()!!.tags.tag
            val responseData = mutableListOf<Tag>()
            responseData.addAll(data)
            LoadResult.Page(
                data = data,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = if (currentPage == response.body()!!.tags.attr.totalPages.toInt()) null
                else currentPage.plus(
                    1
                )
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}