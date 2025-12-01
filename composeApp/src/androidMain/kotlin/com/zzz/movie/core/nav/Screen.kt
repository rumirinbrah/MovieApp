package com.zzz.movie.core.nav

import com.zzz.movie.domain.model.Movie
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {

    @Serializable
    data object Home : Screen()

    @Serializable
    data class Detail(
        val id : Int,
        val posterPath : String?,
    ) : Screen()

}