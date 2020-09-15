package com.dim3nsions.movieapp.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DBMoviePreview(
    @PrimaryKey val id: Int,
    val page: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String?,
    val popularity: Double,
    val voteCount: Long,
    val voteAverage: Double,
    val video: Boolean,
    val adult: Boolean,
    val originalLanguage: String,
    val originalTitle: String,
    val genreIds: List<Int> = listOf(),
    val releaseDate: String,
    val movieSection: Int,
    var isFavorite: Boolean
)

