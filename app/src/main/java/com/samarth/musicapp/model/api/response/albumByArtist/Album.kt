package com.samarth.musicapp.model.api.response.albumByArtist

data class Album(
    val artist: Artist,
    val image: List<Image>,
    val mbid: String,
    val name: String,
    val playcount: Int,
    val url: String
)