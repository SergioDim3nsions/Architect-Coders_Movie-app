package com.dim3nsions.movieapp.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DBMovieDetail(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val imdbId: String,
    val genres: List<DBGenre>,
    val homepage: String?,
    val companies: List<DBCompany>,
    val status: String,
    val tagline: String?
)