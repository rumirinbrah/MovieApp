package com.zzz.movie.domain.model

data class MovieDetail(
    val name : String,
    val date : String,
    val runtime : String,
    val genre : String,
    val videoKey : String,
    val synopsis : String,
    val director : String,
    val cast : List<String> = emptyList(),
    val rating : Double
)
