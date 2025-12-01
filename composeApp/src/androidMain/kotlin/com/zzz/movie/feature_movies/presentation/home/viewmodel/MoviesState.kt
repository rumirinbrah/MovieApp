package com.zzz.movie.feature_movies.presentation.home.viewmodel

import com.zzz.movie.domain.model.Movie

data class MoviesState(
    val movies : List<Movie> = emptyList()
)
