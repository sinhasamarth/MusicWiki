package com.samarth.musicapp.view.pagination.genersScreen

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.samarth.musicapp.api.repository.ApiRepository
import com.samarth.musicapp.model.api.response.topTracks.Track

class TracksPagingSource(private val apiRepository: ApiRepository, private val genreName: String) :
    PagingSource<Int, Track>() {
    override fun getRefreshKey(state: PagingState<Int, Track>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Track> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiRepository.getTracksByGenre(genreName, currentPage)
            val data = response.body()!!.tracks.track
            val responseData = mutableListOf<Track>()
            responseData.addAll(data)
            LoadResult.Page(
                data = data,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = if (currentPage == response.body()!!.tracks.attr.totalPages.toInt()) null
                else currentPage.plus(
                    1
                )
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}