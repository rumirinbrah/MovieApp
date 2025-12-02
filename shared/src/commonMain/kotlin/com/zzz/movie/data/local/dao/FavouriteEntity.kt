package com.zzz.movie.data.local.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    val remoteId: String
)
