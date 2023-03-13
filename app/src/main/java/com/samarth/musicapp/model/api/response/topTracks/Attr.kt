package com.samarth.musicapp.model.api.response.topTracks

data class Attr(
    val page: String,
    val perPage: String,
    val tag: String,
    val total: String,
    val totalPages: String
)