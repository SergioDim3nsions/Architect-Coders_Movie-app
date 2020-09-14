package com.dim3nsions.movieapp.network.model

import com.google.gson.annotations.SerializedName

data class ServerCredit(
    @SerializedName("id") val id: Int,
    @SerializedName("cast") val cast: List<ServerCast>
)