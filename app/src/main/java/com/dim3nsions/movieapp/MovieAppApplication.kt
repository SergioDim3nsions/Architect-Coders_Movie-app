package com.dim3nsions.movieapp

import android.app.Application
import androidx.room.Room
import com.dim3nsions.movieapp.db.MovieDataBase

class MovieAppApplication : Application() {

    companion object {
        lateinit var instance: MovieAppApplication
        lateinit var movieDB: MovieDataBase
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initDatabase()
    }

    private fun initDatabase() {
        movieDB = Room.databaseBuilder(
            this,
            MovieDataBase::class.java, "movie-db"
        ).build()
    }
}
