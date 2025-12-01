package com.zzz.movie.data.remote

import com.zzz.core.ui.domain.Result
import com.zzz.core.ui.domain.map
import com.zzz.movie.data.remote.dto.GetMoviesResponse
import com.zzz.movie.data.remote.dto.TvSeriesDetailResponse
import com.zzz.movie.domain.MoviesRemoteSource
import com.zzz.movie.domain.NetworkError
import com.zzz.movie.domain.model.Movie
import com.zzz.movie.domain.model.MovieDetail
import com.zzz.movie.util.safeNetworkCall
import com.zzz.movie.util.toMovieDetail
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.parameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

const val BASE_URL = "https://api.themoviedb.org/3"
const val TV_SERIES = "/tv/popular?language=en-US&page=1"
const val GET_SERIES = "/tv"
//https://image.tmdb.org/t/p/original/
class TmdbMoviesSource(
    private val httpClient: HttpClient
) : MoviesRemoteSource {

    override suspend fun getMovies(
        page: Int ,
        adult: Boolean ,
        video: Boolean
    ): Result<List<Movie> , NetworkError> {
        return safeNetworkCall<GetMoviesResponse> {
            httpClient.get("$BASE_URL$TV_SERIES"){
//                this.headers {
//                    append(HttpHeaders.Authorization,"Bearer $TOKEN")
//                }
                parameters {
                    append("page",page.toString())
                    append("include_adult",adult.toString())
                    append("include_video",video.toString())
                }
            }
        }.map {data->
            data.results
        }
    }

    override suspend fun getMovieById(id: Int): Result<MovieDetail,NetworkError> {
        return withContext(Dispatchers.IO){
            safeNetworkCall<TvSeriesDetailResponse> {
                httpClient.get("$BASE_URL$GET_SERIES/$id")
            }.map {
                println(it)
                it.toMovieDetail()
            }
        }
    }
}