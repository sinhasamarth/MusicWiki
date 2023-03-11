package com.samarth.musicapp.api.repository

import com.samarth.musicapp.api.ApiService
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiHelper: ApiService
){
    suspend fun getAllGeneres () = apiHelper.getTopTags("chart.gettoptags")
}