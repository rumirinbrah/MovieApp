package com.zzz.movie.di

import com.zzz.movie.data.remote.HttpClientFactory
import com.zzz.movie.data.remote.TmdbMoviesSource
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
}

expect val engineModule : Module

