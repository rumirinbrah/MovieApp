package com.zzz.movie.util

import com.zzz.movie.data.remote.dto.TvSeriesDetailResponse
import com.zzz.movie.domain.model.MovieDetail

internal fun TvSeriesDetailResponse.toMovieDetail(): MovieDetail{
    println("VOTE IS $voteAverage")
    return MovieDetail(
        name = name,
        date = firstAirDate,
        runtime = episodeRunTime.firstOrNull()?.toString()?:"NA",
        genre = genres.first().name,
        videoKey = "",
        synopsis = overview,
        director = createdBy.firstOrNull()?.name?:"NA",
        cast = emptyList(),
        rating = voteAverage
    )
}