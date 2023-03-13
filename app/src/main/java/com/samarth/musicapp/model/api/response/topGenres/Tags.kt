package com.samarth.musicapp.model.api.response.topGenres

import com.google.gson.annotations.SerializedName

data class Tags(
    @SerializedName("@attr")
    val attr: Attr,
    val tag: List<Tag>
)