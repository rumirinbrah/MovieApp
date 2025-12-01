package com.zzz.movie.feature_movies.di

import com.zzz.movie.feature_movies.presentation.details.viewmodel.MovieDetailViewModel
import com.zzz.movie.feature_movies.presentation.home.viewmodel.MoviesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    viewModel {
        MoviesViewModel(
            moviesRemoteSource = get()
        )
    }
    viewModel {
        MovieDetailViewModel(
            moviesRemoteSource = get()
        )
    }
}