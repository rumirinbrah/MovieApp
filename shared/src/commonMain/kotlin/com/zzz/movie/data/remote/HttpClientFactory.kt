package com.zzz.movie.data.remote

import com.zzz.movie.util.ApiStuff
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy

/**
 * Creates http client for both android and ios
 *
 * @author zyzz
*/
object HttpClientFactory {
    @OptIn(ExperimentalSerializationApi::class)
    fun create(
        engine : HttpClientEngine,
    ): HttpClient{
        return HttpClient(engine) {
            install(ContentNegotiation){
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                        this.namingStrategy = JsonNamingStrategy.SnakeCase
                    }
                )
            }
            install(DefaultRequest){
                header(HttpHeaders.Authorization,"Bearer ${ApiStuff.TOKEN}")
            }
            install(HttpTimeout){
                socketTimeoutMillis = 20_000L
                requestTimeoutMillis = 20_000L
            }
            install(Logging){
                this.level= LogLevel.ALL
            }
        }
    }
}