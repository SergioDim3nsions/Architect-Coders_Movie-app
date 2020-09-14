package com.dim3nsions.movieapp.db.model

import androidx.room.Entity

@Entity
data class DBGenre(
    val id: Int,
    val name: String
)
