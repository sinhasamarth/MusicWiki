package com.samarth.musicapp.model.api.response.albumDetails

import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("@attr")
    val attr: Attr,
    val artist: Artist,
    val duration: Int,
    val name: String,
    val streamable: Streamable,
    val url: String
)