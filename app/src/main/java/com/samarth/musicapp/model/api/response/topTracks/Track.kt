package com.samarth.musicapp.model.api.response.topTracks

import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("@attr")

    val attr: AttrX,
    val artist: Artist,
    val duration: String,
    val image: List<Image>,
    val mbid: String,
    val name: String,
    val streamable: Streamable,
    val url: String
)