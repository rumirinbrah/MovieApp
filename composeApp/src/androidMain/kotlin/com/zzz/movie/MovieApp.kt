package com.zzz.movie

import android.app.Application
import android.os.Build
import coil3.ImageLoader
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.util.DebugLogger
import com.zzz.movie.feature_movies.di.movieModule
import com.zzz.movie.di.initKoin
import com.zzz.movie.feature_movies.util.configureCoilCaching
import okio.Path.Companion.toPath
import org.koin.android.ext.koin.androidContext

class MovieApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MovieApp)
            modules(movieModule)
        }
        //---device cache---
        configureCoilCaching()
    }
}

