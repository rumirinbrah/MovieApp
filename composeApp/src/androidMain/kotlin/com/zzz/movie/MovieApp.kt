package com.zzz.movie

import android.app.Application
import com.zzz.movie.feature_movies.di.androidModule
import com.zzz.movie.di.initKoin
import org.koin.android.ext.koin.androidContext

class MovieApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MovieApp)
            modules(androidModule)
        }
    }
}
