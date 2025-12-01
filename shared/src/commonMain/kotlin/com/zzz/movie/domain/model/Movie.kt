package com.zzz.movie.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Movie entity
 *
 * @author zyzz
 */
@Serializable
data class Movie(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String?,
    val genreIds: List<Int>,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val firstAirDate: String,
    val name: String,
    val voteAverage: Double,
    val voteCount: Int
)
/*
val id: Int ,
    val adult: Boolean ,
    @SerialName("backdrop_path")
    val backdropPath: String? ,
    @SerialName("genre_ids")
    val genreIds: List<Int> ,
    @SerialName("origin_country")
    val originCountry: List<String> ,
    @SerialName("original_language")
    val originalLanguage: String ,
    @SerialName("original_name")
    val originalName: String ,
    val overview: String ,
    val popularity: Double ,
    @SerialName("poster_path")
    val posterPath: String? ,
    @SerialName("first_air_date")
    val firstAirDate: String ,
    val name: String ,
    @SerialName("vote_average")
    val voteAverage: Double ,
    @SerialName("vote_count")
    val voteCount: Int
 */
