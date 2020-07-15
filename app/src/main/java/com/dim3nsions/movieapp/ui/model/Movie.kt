package com.dim3nsions.movieapp.ui.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("vote_count") val voteCount: Long,
    @SerializedName("video") val video: Boolean,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("id") val id: Long,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("genre_ids") val genreIDS: List<Long>,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val voteAverage: Long,
    @SerializedName("overview") val overview: String,
    @SerializedName("release_date") val releaseDate: String
)
