package com.dim3nsions.movieapp.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DBCredit(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val cast: List<DBCast>
)