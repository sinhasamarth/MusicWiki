package com.samarth.musicapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.load(imgUrl: String) {
    Glide.with(this).load(imgUrl).into(this)
}