package com.samarth.musicapp.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.ln
import kotlin.math.pow


fun ImageView.load(imgUrl: String, height: Int? = null, width: Int? = null) {
    CoroutineScope(Dispatchers.IO).launch {
        val glide = Glide.with(this@load).load(imgUrl).error(android.R.drawable.stat_notify_error)
        height?.let { h ->
            width?.let { glide.override(h, it) }
        }
        withContext(Dispatchers.Main) {
            glide.into(this@load)
        }
    }


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

fun View.visibleIt() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisibleIt() {
    this.visibility = View.INVISIBLE

}

fun Context.showShortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
