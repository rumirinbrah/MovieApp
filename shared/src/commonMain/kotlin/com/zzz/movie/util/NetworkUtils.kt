package com.zzz.movie.util

import com.zzz.core.ui.domain.Result
import com.zzz.movie.domain.Error
import com.zzz.movie.domain.NetworkError
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.http.BadContentTypeFormatException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

/**
 * Network util
 *
 * Handles basic errors
 *
 * @author zyzz
*/
internal suspend inline fun<reified T> safeNetworkCall(
    block : ()-> HttpResponse
) : Result<T, NetworkError>{
    val response = try {
        block()
    }catch (e : SerializationException){
        e.printStackTrace()
        return Result.Error(NetworkError.Serialization)
    }catch (e : Exception){
        e.printStackTrace()
        coroutineContext.ensureActive()
        return Result.Error(NetworkError.Unknown)
    }
    return responseToResult(response)
}
/*
catch (e : ClientRequestException){
        e.printStackTrace()
        return when(e.response.status.value){
            401 -> Result.Error(NetworkError.BadRequest)
            404 -> Result.Error(NetworkError.Unknown)
            else -> Result.Error(NetworkError.BadRequest)
        }
    }catch (e : ServerResponseException){
        e.printStackTrace()
        return Result.Error(NetworkError.Internal)
    }
 */


/**
 * Tries mapping response to result
 *
 * @author zyzz
*/
internal suspend inline fun <reified T> responseToResult(
    response : HttpResponse
) : Result<T, NetworkError>{
    return when(response.status.value){
        in 200..299->{
            try {
                Result.Success(response.body<T>())
            }catch (_ : NoTransformationFoundException){
                Result.Error(NetworkError.Serialization)
            }
        }
        400 -> Result.Error(NetworkError.BadRequest)
        401 -> Result.Error(NetworkError.Unauthorized)
        403 -> Result.Error(NetworkError.Forbidden)
        404 -> Result.Error(NetworkError.NotFound)
        in 400..499 -> Result.Error(NetworkError.Client)
        in 500..599->{
            Result.Error(NetworkError.Unknown)
        }
        else->{
            Result.Error(NetworkError.Unknown)
        }
    }
}

