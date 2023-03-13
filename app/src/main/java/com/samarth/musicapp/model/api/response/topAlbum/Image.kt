package com.samarth.musicapp.model.api.response.topAlbum

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("#text")
    val src: String,
    val size: String
)