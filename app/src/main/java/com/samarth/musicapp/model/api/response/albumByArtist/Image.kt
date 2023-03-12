package com.samarth.musicapp.model.api.response.albumByArtist

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("#text")
    val text: String,
    val size: String
)