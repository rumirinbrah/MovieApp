package com.zzz.movie.feature_movies.presentation.details.viewmodel

sealed class MovieDetailAction {
    data class GetMovieDetails(val id : Int,val videoKey : String?) : MovieDetailAction()

    data class AddToFavourite(val id : Int) : MovieDetailAction()
}