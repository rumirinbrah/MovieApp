package com.zzz.movie

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform