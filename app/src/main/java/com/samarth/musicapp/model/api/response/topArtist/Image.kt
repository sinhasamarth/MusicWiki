package com.samarth.musicapp.model.api.response.topArtist

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("#text")
    val text: String,
    val size: String
)