package com.zzz.movie.di

import com.zzz.movie.data.local.database.DatabaseFactory
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module

actual val engineModule: Module
    get() = module {
        single<HttpClientEngine> {
            Darwin.create()
        }
        single {
            DatabaseFactory()
        }
    }