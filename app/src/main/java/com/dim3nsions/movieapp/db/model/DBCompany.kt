package com.dim3nsions.movieapp.db.model

import androidx.room.Entity

@Entity
data class DBCompany(
    val id: Int,
    val logoPath: String,
    val name: String,
    val originCountry: String
)
