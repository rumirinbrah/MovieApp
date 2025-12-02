package com.zzz.movie.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.zzz.movie.data.local.database.DatabaseFactory
import com.zzz.movie.data.local.database.FavoriteDatabase
import com.zzz.movie.data.local.repo.RoomFavouriteSource
import com.zzz.movie.data.remote.HttpClientFactory
import com.zzz.movie.data.remote.TmdbMoviesSource
import com.zzz.movie.domain.FavouriteLocalSource
import com.zzz.movie.domain.MoviesRemoteSource
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

val sharedModule = module {
    single<HttpClient> {
        HttpClientFactory.create(get())
    }
    single<MoviesRemoteSource> {
        TmdbMoviesSource(
            httpClient = get()
        )
    }

    single<FavoriteDatabase>{
        val db = get<DatabaseFactory>()
            .create()
            .setDriver(BundledSQLiteDriver())
            .build()
        db
    }

    single{
        get<FavoriteDatabase>().favoriteDao
    }
    single<FavouriteLocalSource> {
        RoomFavouriteSource(get())
    }

}

expect val engineModule : Module

