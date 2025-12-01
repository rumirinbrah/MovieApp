package com.zzz.movie.feature_movies.presentation.details.viewmodel

import io.ktor.util.collections.StringMap

data class MovieDetailState(
    val name : String = "",
    val date : String = "",
    val runtime : String = "",
    val genre : String = "",
    val videoKey : String? = null,
    val synopsis : String = "",
    val director : String = "",
    val cast : List<String> = emptyList(),
    val rating : Double = 0.0,
    val loading : Boolean = false
)
