package com.zzz.movie.feature_movies.presentation.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzz.core.ui.domain.Result
import com.zzz.core.ui.domain.map
import com.zzz.movie.domain.MoviesRemoteSource
import com.zzz.movie.feature_movies.presentation.home.viewmodel.MoviesState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val moviesRemoteSource: MoviesRemoteSource
) : ViewModel() {

    private val _state = MutableStateFlow(MovieDetailState())
    val state = _state.asStateFlow()

    fun getMovieDetails(
        id : Int,
        videoKey : String?
    ){
        if(_state.value.loading){
            return
        }
        viewModelScope.launch {
            _state.update {
                it.copy(loading = true)
            }
            val result = moviesRemoteSource.getMovieById(id)

            when(result){
                is Result.Error -> {
                    println("Error")
                }
                is Result.Success -> {
                    result.map {data->
                        _state.update {
                            it.copy(
                                name = data.name ,
                                date = data.date ,
                                runtime = data.runtime ,
                                genre =  data.genre,
                                videoKey =  videoKey,
                                synopsis =  data.synopsis,
                                director =  data.director,
                                cast =  data.cast,
                            )
                        }
                    }
                }
            }
            _state.update {
                it.copy(loading = false)
            }

        }
    }

}