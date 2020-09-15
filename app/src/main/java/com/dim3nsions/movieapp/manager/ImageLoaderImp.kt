package com.dim3nsions.movieapp.manager

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.dim3nsions.movieapp.MovieAppApplication


interface ImageLoader {
    companion object {
        val instance by lazy {
            ImageLoaderImp()
        }
    }

    fun loadImage(url: String, imageView: ImageView)
}


class ImageLoaderImp(private val application: MovieAppApplication = MovieAppApplication.instance) :
    ImageLoader {

    override fun loadImage(url: String, imageView: ImageView) {
        Glide.with(application).load(url).into(imageView)
    }
}