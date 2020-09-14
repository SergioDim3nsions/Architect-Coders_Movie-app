package com.dim3nsions.movieapp.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PresentationMoviePreview(
    val id: Int,
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
    val genreIds: List<Int>,
    val releaseDate: String
) : Parcelable