package com.zzz.movie.feature_movies.presentation.home

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Brush.Companion.horizontalGradient
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zzz.movie.R
import com.zzz.movie.core.presentation.CircularIconButton
import com.zzz.movie.core.presentation.RoundedIconButton
import com.zzz.movie.core.presentation.VerticalSpace
import com.zzz.movie.domain.model.Movie
import com.zzz.movie.feature_movies.presentation.home.components.GridMovieItem
import com.zzz.movie.feature_movies.presentation.home.viewmodel.MoviesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    onClick : (movie : Movie)->Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    innerPadding : PaddingValues = PaddingValues()
) {
    val moviesViewModel = koinViewModel<MoviesViewModel>()
    val state by moviesViewModel.state.collectAsStateWithLifecycle()

    Box(
        Modifier
            .background(
                horizontalGradient(
                    colors = listOf(
                        Color(0xFF060618) ,
                        Color(0xFF320756) ,
                    ) ,
                    startX = 100f
                )
            )
            .padding(innerPadding)
    ) {
        Column(
            modifier.fillMaxSize()
                .padding(16.dp) ,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Row(
                Modifier.fillMaxWidth() ,
                horizontalArrangement = Arrangement.SpaceBetween ,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Movies" ,
                    fontWeight = FontWeight.Medium ,
                    fontSize = 20.sp ,
                    color = Color.White
                )

                RoundedIconButton(
                    icon = R.drawable.heart ,
                    contentDescription = "Favourite" ,
                    onClick = {

                    } ,
                    background = Color.White.copy(0.2f) ,
                    onBackground = Color.White ,
                    iconSize = 30.dp ,
                )
            }

            VerticalSpace()

            OutlinedTextField(
                value = "" ,
                onValueChange = {

                } ,
                placeholder = {
                    Text(
                        "Search movies",
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp
                    )
                } ,
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.search_icon) ,
                        contentDescription = "Search" ,
                        tint = Color.Gray,
                        modifier = Modifier.size(30.dp)
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFF301D41),
                    focusedContainerColor = Color(0xFF301D41),
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.Gray
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(25)
            )

            Spacer(Modifier.height(10.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2) ,
                modifier = Modifier.fillMaxWidth() ,
//            horizontalArrangement = Arrangement.spacedBy(8.dp),
//            verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    state.movies,
                    key = {
                        it.id
                    }
                ) { movie ->
                    GridMovieItem(
                        movie = movie ,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(2f / 3f),
                        onClick = {
                            onClick(it)
                        },
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                }
            }
        }
    }

}