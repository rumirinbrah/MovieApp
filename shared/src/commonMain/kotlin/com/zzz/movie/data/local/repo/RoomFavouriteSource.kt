package com.zzz.movie.data.local.repo

import com.zzz.movie.data.local.dao.FavoriteDao
import com.zzz.movie.data.local.dao.FavoriteEntity
import com.zzz.movie.domain.FavouriteLocalSource
import kotlinx.coroutines.flow.Flow

internal class RoomFavouriteSource(
    private val dao : FavoriteDao
) : FavouriteLocalSource {
    override suspend fun addFavorite(entity: FavoriteEntity) {
        dao.addFavorite(entity)
    }

    override suspend fun deleteFavorite(entity: FavoriteEntity) {
        dao.deleteFavorite(entity)
    }

    override fun getAllFavoritesFlow(): Flow<List<FavoriteEntity>> {
        return dao.getAllFavoritesFlow()
    }

    override suspend fun getAllFavorites(): List<FavoriteEntity> {
        return dao.getAllFavorites()
    }
}