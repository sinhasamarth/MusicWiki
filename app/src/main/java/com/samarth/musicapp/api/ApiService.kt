package com.samarth.musicapp.api

import com.samarth.musicapp.model.api.response.TopGenresResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
interface ApiService{
    @GET("2.0/")
    suspend fun getTopTags(
        @Query("method") method:String
    ): Response<TopGenresResponse>
}