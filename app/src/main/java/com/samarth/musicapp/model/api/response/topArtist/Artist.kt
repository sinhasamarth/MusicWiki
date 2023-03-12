package com.samarth.musicapp.model.api.response.topArtist

import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("attr")
    val attr: AttrX,
    val image: List<Image>,
    val mbid: String,
    val name: String,
    val streamable: String,
    val url: String
)