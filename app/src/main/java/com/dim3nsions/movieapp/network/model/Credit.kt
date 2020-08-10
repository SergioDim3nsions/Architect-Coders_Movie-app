package com.dim3nsions.movieapp.network.model

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class Credit(
    @SerializedName("id") val id: Int,
    @SerializedName("cast") val cast: List<Cast>
)