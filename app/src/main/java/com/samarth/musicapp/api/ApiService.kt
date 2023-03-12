package com.samarth.musicapp.api

import com.samarth.musicapp.model.api.response.albumDetails.AlbumDetailsResponse
import com.samarth.musicapp.model.api.response.artistDetails.ArtistDetailResponse
import com.samarth.musicapp.model.api.response.genreDetails.GenreDetailsResponse
import com.samarth.musicapp.model.api.response.topAlbum.TopAlbumsResponse
import com.samarth.musicapp.model.api.response.topArtist.TopArtistResponse
import com.samarth.musicapp.model.api.response.topGenres.TopGenresResponse
import com.samarth.musicapp.model.api.response.topTracks.TopTrackResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("2.0/")
    suspend fun getTopTags(
        @Query("method") method: String,
        @Query("page") page: Int,
    ): Response<TopGenresResponse>

    @GET("2.0/")
    suspend fun getArtistDetail(
        @Query("method") method: String,

        @Query("artist") artistName: String
    ): Response<ArtistDetailResponse>

    @GET("2.0/")
    suspend fun getTopAlbum(
        @Query("method") method: String,
        @Query("tag") genre: String,
        @Query("page") page: Int = 1,
    ): Response<TopAlbumsResponse>

    @GET("2.0/")
    suspend fun getTopTracks(
        @Query("method") method: String,
        @Query("tag") genre: String,
        @Query("page") page: Int
    ): Response<TopTrackResponse>

    @GET("2.0/")
    suspend fun getGenreDetails(
        @Query("method") method: String,
        @Query("tag") genre: String,
    ): Response<GenreDetailsResponse>

    @GET("2.0/")
    suspend fun getTopArtists(
        @Query("method") method: String,
        @Query("tag") genre: String,
        @Query("page") page: Int
    ): Response<TopArtistResponse>


    @GET("2.0/")
    suspend fun getAlbumDetails(
        @Query("method") method: String,
        @Query("artist") artist: String,
        @Query("album") album: String,
    ): Response<AlbumDetailsResponse>
}