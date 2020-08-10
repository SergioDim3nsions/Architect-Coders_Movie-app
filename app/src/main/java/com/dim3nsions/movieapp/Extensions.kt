package com.dim3nsions.movieapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import com.dim3nsions.movieapp.manager.ImageLoader

fun ViewGroup.inflate(@LayoutRes resource: Int): View =
    LayoutInflater.from(context).inflate(resource, this, false)

fun ImageView.loadUrl(url: String?, imageType: ImageType = ImageType.POSTER) {
    url?.let {
        ImageLoader.instance.loadImage("https://image.tmdb.org/t/p/${imageType.type}/$it", this)
    }
}

enum class ImageType(val type: String) {
    POSTER("w300"),
    BACKDROP("w780")
}

inline fun <reified T : Activity> Context.startActivity(vararg pairs: Pair<String, Any?>) {
    Intent(this, T::class.java)
        .apply { putExtras(bundleOf(*pairs)) }
        .also(::startActivity)
}

