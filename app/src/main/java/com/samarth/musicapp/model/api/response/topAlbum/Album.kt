package com.samarth.musicapp.model.api.response.topAlbum

data class Album(
    val artist: Artist,
    val image: List<Image>,
    val mbid: String,
    val name: String,
    val url: String
)