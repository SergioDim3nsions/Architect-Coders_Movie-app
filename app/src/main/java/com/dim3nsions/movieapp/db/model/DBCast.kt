package com.dim3nsions.movieapp.db.model

import androidx.room.Entity

@Entity
data class DBCast(
    val id: Int,
    val name: String,
    val profilePath: String
)