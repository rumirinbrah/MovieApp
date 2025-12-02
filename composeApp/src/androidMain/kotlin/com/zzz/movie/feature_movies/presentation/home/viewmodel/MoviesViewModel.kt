package com.zzz.movie.feature_movies.presentation.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzz.core.ui.domain.Result
import com.zzz.core.ui.domain.map
import com.zzz.movie.domain.MoviesRemoteSource
import com.zzz.movie.domain.NetworkError
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val moviesRemoteSource: MoviesRemoteSource
) : ViewModel() {

    private val loggingEnabled = true

    private val _state = MutableStateFlow(MoviesState())
    val state = _state.asStateFlow()

    private val _events = Channel<String>()
    val events = _events.receiveAsFlow()

    init {
        loadData()
    }

    fun onAction(action : MovieAction){
        when(action){
            is MovieAction.OnSearchQueryChange -> {
                onQueryChange(action.query)
            }
        }
    }

    private fun onQueryChange(query: String) {
        _state.update {
            it.copy(searchQuery = query)
        }
    }

    private fun loadData(){
        viewModelScope.launch {
            if(_state.value.movies.isNotEmpty()){
                return@launch
            }
            log {
                "Test"
            }
            val result = moviesRemoteSource.getMovies()
            when(result){
                is Result.Error -> {
                    log {
                        "Error ${result.error.message}"
                    }
                    when(result.error){
                        NetworkError.Serialization -> {
                            _events.send("Something went wrong, please try again later")
                        }
                        NetworkError.Unauthorized -> {
                            _events.send("Authentication failed, please try again later")
                        }
                        else->{
                            _events.send("An unknown error occurred")
                        }
                    }
                }
                is Result.Success -> {
                    result.map { list->
                        _state.update {
                            it.copy(movies = list)
                        }
                    }
                }
            }

            log{
                "Loaded $result"
            }
        }
    }

    private fun log(msg : ()->String){
        if(loggingEnabled){
            Log.d("MoviesViewModel" , msg())
        }
    }

}