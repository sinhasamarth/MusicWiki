package com.samarth.musicapp.api.repository

import com.samarth.musicapp.api.ApiService
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiHelper: ApiService
) {
    suspend fun getAllGenres(page: Int) = apiHelper.getTopTags("chart.gettoptags", page)

    suspend fun getGenreDetails(genre:String) = apiHelper.getGenreDetails("tag.getinfo", genre)
}