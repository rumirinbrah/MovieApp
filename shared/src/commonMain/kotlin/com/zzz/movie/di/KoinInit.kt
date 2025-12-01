package com.zzz.movie.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

/**
 * Start koin
 *
 * @author zyzz
*/
fun initKoin(
    config : KoinAppDeclaration? = null
){
    startKoin {
        config?.invoke(this)
        modules(sharedModule,engineModule)
    }
}