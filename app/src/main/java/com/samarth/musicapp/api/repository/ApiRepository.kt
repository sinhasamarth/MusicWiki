package com.samarth.musicapp.api.repository

import com.samarth.musicapp.api.ApiService
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiHelper: ApiService
) {
    suspend fun getAllGenres() = apiHelper.getTopTags()

    suspend fun getGenreDetails(genre: String) = apiHelper.getGenreDetails(genre = genre)

    suspend fun getAllAlbum(genre: String, page: Int) =
        apiHelper.getTopAlbum(genre = genre, page = page)

    suspend fun getArtistByGenre(genre: String, page: Int) =
        apiHelper.getTopArtists(genre = genre, page = page)

    suspend fun getTracksByGenre(genre: String, page: Int) =
        apiHelper.getTopTracks(genre = genre, page = page)

    suspend fun getAlbumDetails(artist: String, album: String) =
        apiHelper.getAlbumDetails(album = album, artist = artist)

    suspend fun getArtistDetails(artist: String) = apiHelper.getArtistDetail(artistName = artist)

    suspend fun getAllTrackByArtist(artist: String, page: Int) = apiHelper.getTopTracksByArtist(
        artist = artist, page = page
    )

    suspend fun getAllAlbumByArtist(artist: String, page: Int) = apiHelper.getTopAlbumByArtist(
        artist = artist, page = page
    )
}