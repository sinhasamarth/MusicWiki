package com.samarth.musicapp.api.repository

import com.samarth.musicapp.api.ApiService
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiHelper: ApiService
) {
    suspend fun getAllGenres(page: Int) = apiHelper.getTopTags("chart.gettoptags", page)

    suspend fun getGenreDetails(genre:String) = apiHelper.getGenreDetails("tag.getinfo", genre)

    suspend fun getAllAlbum(genre: String, page:Int) = apiHelper.getTopAlbum("tag.gettopalbums", genre, page)

    suspend fun getArtistByGenre(genre: String, page:Int) = apiHelper.getTopArtists("tag.gettopartists", genre, page)

    suspend fun getTracksByGenre(genre: String, page:Int) = apiHelper.getTopTracks("tag.gettoptracks", genre, page)
}