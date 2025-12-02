package com.zzz.movie.data.local.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import com.zzz.movie.data.local.dao.FavoriteDao
import com.zzz.movie.data.local.dao.FavoriteEntity

@Database(
    entities = [FavoriteEntity::class],
    version = 1
)
@ConstructedBy(DatabaseConstructor::class)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract val favoriteDao: FavoriteDao


    companion object {
        const val DB_NAME = "favorite.db"
    }
}