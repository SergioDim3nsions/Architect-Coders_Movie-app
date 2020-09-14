package com.dim3nsions.movieapp.db

import com.dim3nsions.movieapp.db.model.DBMoviePreview
import com.dim3nsions.movieapp.network.model.ServerMoviePreview
import com.dim3nsions.movieapp.ui.model.PresentationMoviePreview

fun ServerMoviePreview.convertToDbMovie(sectionId: Int, page: Int) = DBMoviePreview(
    id,
    page,
    title,
    overview,
    posterPath ?: "",
    backdropPath ?: posterPath,
    popularity,
    voteCount,
    voteAverage,
    video,
    adult,
    originalLanguage,
    originalTitle,
    genreIds,
    releaseDate,
    sectionId
)

fun ServerMoviePreview.convertToPresentationMovie() = PresentationMoviePreview(
    id,
    title,
    overview,
    posterPath ?: "",
    backdropPath ?: posterPath,
    popularity,
    voteCount,
    voteAverage,
    video,
    adult,
    originalLanguage,
    originalTitle,
    mutableListOf(),
    releaseDate
)

fun DBMoviePreview.convertToPresentationMovie() = PresentationMoviePreview(
    id,
    title,
    overview,
    posterPath,
    backdropPath ?: posterPath,
    popularity,
    voteCount,
    voteAverage,
    video,
    adult,
    originalLanguage,
    originalTitle,
    mutableListOf(),
    releaseDate
)