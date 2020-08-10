package com.dim3nsions.movieapp.network.model

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @SerializedName("imdb_id") val imdbId: String,
    @SerializedName("genres") val genres: List<Genre>,
    @SerializedName("homepage") val homepage: String?,
    @SerializedName("production_companies") val companies: List<Company>,
    @SerializedName("status") val status: String,
    @SerializedName("tagline") val tagline: String?
)