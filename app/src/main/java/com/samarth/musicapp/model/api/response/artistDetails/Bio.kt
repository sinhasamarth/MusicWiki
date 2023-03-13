package com.samarth.musicapp.model.api.response.artistDetails

data class Bio(
    val content: String,
    val links: Links,
    val published: String,
    val summary: String
)