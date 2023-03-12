package com.samarth.musicapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlin.math.ln
import kotlin.math.pow


fun ImageView.load(imgUrl: String) {
    Glide.with(this).load(imgUrl).into(this)
}

fun Long.readAbleFormat(): String {
    val count = this
    if (count < 1000) return "" + count
    val exp = (ln(count.toDouble()) / ln(1000.0)).toInt()
    return String.format(
        "%.1f %c",
        count / 1000.0.pow(exp.toDouble()),
        "kMGTPE"[exp - 1]
    )
}