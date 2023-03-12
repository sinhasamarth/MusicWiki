package com.samarth.musicapp.model.api.response.artistDetails

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("#text")
    val link: String,
    val size: String
)