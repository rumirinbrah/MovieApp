package com.zzz.movie.feature_movies.util

import android.app.Application
import android.os.Build
import coil3.ImageLoader
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.util.DebugLogger
import okio.Path.Companion.toPath

fun Application.configureCoilCaching(
    memoryCachePercentage: Double = 0.25,
    diskCacheSize: Long = 100,
) {

    //-----------WONT WORK ON ANDROID 24-----------
    if (Build.VERSION.SDK_INT < 26) {
        return
    }

    //-----------CONSTRAINTS-----------
    require(memoryCachePercentage <= 0.4) {
        throw IllegalArgumentException("The memory caching percentage must be lower than 40%!")
    }
    require(diskCacheSize < 320) {
        throw IllegalArgumentException("The disk cache size must be lower than 320MB!")
    }


    //-----------IMAGE LOADER-----------
    val imageLoader = ImageLoader.Builder(this)
        .memoryCache {
            MemoryCache.Builder()
                .maxSizePercent(this, memoryCachePercentage)
                .build()
        }
        .diskCache {
            val cachePath = cacheDir.resolve("image_cache").absolutePath.toPath()
            DiskCache.Builder()
                .directory(cachePath)
                .maxSizeBytes(diskCacheSize * 1024 * 1024)
                .build()
        }
        .logger(DebugLogger())
        .build()

    //-----------SET LOADER-----------
    coil3.SingletonImageLoader.setSafe {
        imageLoader
    }
}