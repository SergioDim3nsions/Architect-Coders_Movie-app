package com.dim3nsions.movieapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dim3nsions.movieapp.db.model.DBCredit
import com.dim3nsions.movieapp.db.model.DBMovieDetail
import com.dim3nsions.movieapp.db.model.DBMoviePreview

@Database(entities = [DBMoviePreview::class, DBMovieDetail::class, DBCredit::class], version = 1)
@TypeConverters(Converters::class)
abstract class MovieDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}