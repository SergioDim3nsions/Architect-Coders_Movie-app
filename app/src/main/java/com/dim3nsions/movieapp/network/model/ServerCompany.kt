package com.dim3nsions.movieapp.network.model

import com.google.gson.annotations.SerializedName

data class ServerCompany(
    @SerializedName("id") val id: Int,
    @SerializedName("logo_path") val logoPath: String,
    @SerializedName("name") val name: String,
    @SerializedName("origin_country") val originCountry: String
)
