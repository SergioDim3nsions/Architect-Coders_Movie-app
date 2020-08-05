package com.dim3nsions.movieapp

import android.app.Application

class MovieAppApplication : Application() {

    companion object {
        lateinit var instance: MovieAppApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
