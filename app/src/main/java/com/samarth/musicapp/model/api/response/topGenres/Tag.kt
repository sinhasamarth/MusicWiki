package com.samarth.musicapp.model.api.response.topGenres

data class Tag(
    val name: String,
    val reach: String,
    val streamable: String,
    val taggings: String,
    val url: String,
    val wiki: Wiki
)