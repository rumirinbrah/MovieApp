package com.zzz.movie.domain

import com.zzz.movie.data.local.dao.FavoriteEntity
import kotlinx.coroutines.flow.Flow

interface FavouriteLocalSource {
    suspend fun addFavorite(entity: FavoriteEntity)

    suspend fun deleteFavorite(entity: FavoriteEntity)

    fun getAllFavoritesFlow(): Flow<List<FavoriteEntity>>

    suspend fun getAllFavorites(): List<FavoriteEntity>
}