package com.samarth.musicapp.api

import com.samarth.musicapp.model.api.response.albumByArtist.AlbumArtistResponse
import com.samarth.musicapp.model.api.response.albumDetails.AlbumDetailsResponse
import com.samarth.musicapp.model.api.response.artistDetails.ArtistDetailResponse
import com.samarth.musicapp.model.api.response.genreDetails.GenreDetailsResponse
import com.samarth.musicapp.model.api.response.topAlbum.TopAlbumsResponse
import com.samarth.musicapp.model.api.response.topArtist.TopArtistResponse
import com.samarth.musicapp.model.api.response.topGenres.TopGenresResponse
import com.samarth.musicapp.model.api.response.topTracks.TopTrackResponse
import com.samarth.musicapp.utils.Constants
import com.samarth.musicapp.utils.Constants.Methods.ALBUM_GET_INFO
import com.samarth.musicapp.utils.Constants.Methods.ARTIST_GET_INFO
import com.samarth.musicapp.utils.Constants.Methods.ARTIST_GET_TOP_ALBUM
import com.samarth.musicapp.utils.Constants.Methods.ARTIST_GET_TOP_TRACKS
import com.samarth.musicapp.utils.Constants.Methods.GET_TOP_GENRES
import com.samarth.musicapp.utils.Constants.Methods.TAG_GET_INFO
import com.samarth.musicapp.utils.Constants.Methods.TAG_GET_TOP_ALBUMS
import com.samarth.musicapp.utils.Constants.Methods.TAG_GET_TOP_ARTISTS
import com.samarth.musicapp.utils.Constants.Methods.TAG_GET_TOP_TRACKS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Constants.VERSION)
    suspend fun getTopTags(
        @Query("method") method: String = GET_TOP_GENRES,
    ): Response<TopGenresResponse>

    @GET(Constants.VERSION)
    suspend fun getArtistDetail(
        @Query("method") method: String = ARTIST_GET_INFO,
        @Query("artist") artistName: String
    ): Response<ArtistDetailResponse>

    @GET(Constants.VERSION)
    suspend fun getTopAlbum(
        @Query("method") method: String = TAG_GET_TOP_ALBUMS,
        @Query("tag") genre: String,
        @Query("page") page: Int = 1,
    ): Response<TopAlbumsResponse>

    @GET(Constants.VERSION)
    suspend fun getTopTracks(
        @Query("method") method: String = TAG_GET_TOP_TRACKS,
        @Query("tag") genre: String,
        @Query("page") page: Int
    ): Response<TopTrackResponse>

    @GET(Constants.VERSION)
    suspend fun getGenreDetails(
        @Query("method") method: String = TAG_GET_INFO,
        @Query("tag") genre: String,
    ): Response<GenreDetailsResponse>

    @GET(Constants.VERSION)
    suspend fun getTopArtists(
        @Query("method") method: String = TAG_GET_TOP_ARTISTS,
        @Query("tag") genre: String,
        @Query("page") page: Int
    ): Response<TopArtistResponse>


    @GET(Constants.VERSION)
    suspend fun getAlbumDetails(
        @Query("method") method: String = ALBUM_GET_INFO,
        @Query("artist") artist: String,
        @Query("album") album: String,
    ): Response<AlbumDetailsResponse>

    @GET(Constants.VERSION)
    suspend fun getTopTracksByArtist(
        @Query("method") method: String = ARTIST_GET_TOP_TRACKS,
        @Query("artist") artist: String,
        @Query("page") page: Int
    ): Response<TopTrackResponse>

    @GET(Constants.VERSION)
    suspend fun getTopAlbumByArtist(
        @Query("method") method: String = ARTIST_GET_TOP_ALBUM,
        @Query("artist") artist: String,
        @Query("page") page: Int
    ): Response<AlbumArtistResponse>
}