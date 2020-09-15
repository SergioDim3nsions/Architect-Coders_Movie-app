package com.dim3nsions.movieapp.db

import com.dim3nsions.movieapp.db.model.DBMoviePreview
import com.dim3nsions.movieapp.network.model.ServerMoviePreview
import com.dim3nsions.movieapp.ui.model.PresentationMoviePreview

fun ServerMoviePreview.convertToDbMovie(sectionId: Int, page: Int, isFavorite: Boolean = false) = DBMoviePreview(
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
    sectionId,
    false
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

fun PresentationMoviePreview.convertToDbMovie(sectionId: Int, page: Int, isFavorite: Boolean = false) = DBMoviePreview(
    id,
    page,
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
    releaseDate,
    sectionId,
    isFavorite
)