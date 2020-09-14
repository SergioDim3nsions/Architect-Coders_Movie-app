package com.dim3nsions.movieapp.network.model

import com.google.gson.annotations.SerializedName

data class ServerCast(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("profile_path") val profilePath: String
)