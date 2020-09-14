package com.dim3nsions.movieapp.ui.model

data class PresentationMovieDetail(
    val imdbId: String,
    val genres: List<PresentationGenre>,
    val homepage: String?,
    val companies: List<PresentationCompany>,
    val status: String,
    val tagline: String?
)