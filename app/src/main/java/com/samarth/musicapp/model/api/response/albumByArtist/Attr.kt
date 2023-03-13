package com.samarth.musicapp.model.api.response.albumByArtist

data class Attr(
    val artist: String,
    val page: String,
    val perPage: String,
    val total: String,
    val totalPages: String
)