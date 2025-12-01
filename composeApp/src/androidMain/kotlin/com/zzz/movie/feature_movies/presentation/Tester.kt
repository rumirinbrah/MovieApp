package com.zzz.movie.feature_movies.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zzz.movie.core.presentation.ImageComponent
import com.zzz.movie.feature_movies.presentation.home.components.GridMovieItem
import com.zzz.movie.feature_movies.presentation.home.viewmodel.MoviesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Tester(
    modifier: Modifier = Modifier
) {
    val moviesViewModel = koinViewModel<MoviesViewModel>()
    val state by moviesViewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                moviesViewModel.test()
            }
        ) {
            Text("Test")
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.spacedBy(8.dp),
//            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.movies){movie->
//                GridMovieItem(
//                    movie = movie,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .aspectRatio(0.65f),
//                    onClick = {
//
//                    }
//                )
            }
        }
    }
}