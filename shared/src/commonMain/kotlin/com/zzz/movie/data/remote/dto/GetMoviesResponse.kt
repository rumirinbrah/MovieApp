package com.zzz.movie.data.remote.dto

import com.zzz.movie.domain.model.Movie
import kotlinx.serialization.Serializable

/**
 * @author zyzz
*/
@Serializable
data class GetMoviesResponse(
    val results : List<Movie> = emptyList()
)
