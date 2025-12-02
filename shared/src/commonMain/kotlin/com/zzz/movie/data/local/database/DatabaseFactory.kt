package com.zzz.movie.data.local.database

import androidx.room.Room
import androidx.room.RoomDatabase

expect class DatabaseFactory {
    fun create(): RoomDatabase.Builder<FavoriteDatabase>
}