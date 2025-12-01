package com.zzz.movie.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvSeriesDetailResponse(
    val name: String ,
    val firstAirDate: String ,
    val episodeRunTime: List<Int> ,
    val genres: List<Genre> ,
    val overview: String ,
    val createdBy: List<Creator>,
    val voteAverage : Double
)

@Serializable
data class Genre(
    val id: Int,
    val name: String
)

@Serializable
data class Creator(
    val id: Int,
    val name: String
)