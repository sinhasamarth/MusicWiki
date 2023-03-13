package com.samarth.musicapp.utils

object Constants {
    const val BASE_URL = "https://ws.audioscrobbler.com/"
    const val VERSION= "2.0/"
    object Methods {
        const val GET_TOP_GENRES = "chart.gettoptags"
        const val ARTIST_GET_INFO = "artist.getinfo"
        const val TAG_GET_TOP_ALBUMS="tag.gettopalbums"
        const val TAG_GET_INFO ="tag.getinfo"
        const val ARTIST_GET_TOP_TRACKS = "artist.gettoptracks"
        const val ARTIST_GET_TOP_ALBUM = "artist.gettopalbums"
        const val ALBUM_GET_INFO = "album.getinfo"
        const val TAG_GET_TOP_TRACKS="tag.gettoptracks"
        const val TAG_GET_TOP_ARTISTS="tag.gettopartists"
    }
}

