package com.zzz.movie.domain

import com.zzz.core.ui.domain.Result
import com.zzz.movie.data.remote.dto.GetMoviesResponse
import com.zzz.movie.data.remote.dto.TvSeriesDetailResponse
import com.zzz.movie.domain.model.Movie
import com.zzz.movie.domain.model.MovieDetail

interface MoviesRemoteSource {

    suspend fun getMovies(
        page : Int = 1,
        adult : Boolean = true,
        video : Boolean = true,
    ) : Result<List<Movie> , NetworkError>

    suspend fun getMovieById(id : Int) : Result<MovieDetail,NetworkError>

}