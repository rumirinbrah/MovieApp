package com.zzz.movie.feature_movies.presentation.home.viewmodel

sealed class MovieAction {
    data class OnSearchQueryChange(val query : String) : MovieAction()
}