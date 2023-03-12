package com.samarth.musicapp.model.api.response.genreDetails

data class Tag(
    val name: String,
    val reach: Int,
    val total: Int,
    val wiki: Wiki
)