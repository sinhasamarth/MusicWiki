package com.samarth.musicapp.model.api.response.topArtist

import com.google.gson.annotations.SerializedName

data class Topartists(
    @SerializedName("@attr")
    val attr: Attr,
    val artist: List<Artist>
)