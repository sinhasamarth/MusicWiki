package com.samarth.musicapp.model.api.response.albumByArtist

import com.google.gson.annotations.SerializedName

data class Topalbums(
    @SerializedName("@attr")
    val attr: Attr,
    val album: List<Album>
)