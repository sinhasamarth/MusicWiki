package com.samarth.musicapp.model.api.response.topTracks

import com.google.gson.annotations.SerializedName

data class Streamable(
    @SerializedName("#text")
    val src: String,
    val fulltrack: String
)