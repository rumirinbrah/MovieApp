package com.zzz.movie.feature_movies.presentation.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zzz.movie.LocalSharedTransitionScope
import com.zzz.movie.R
import com.zzz.movie.core.presentation.CircularIconButton
import com.zzz.movie.core.presentation.ImageComponent
import com.zzz.movie.domain.model.Movie
import movieapp.composeapp.generated.resources.Res

import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun GridMovieItem(
    modifier: Modifier = Modifier ,
    movie: Movie ,
    onClick: (movie: Movie) -> Unit ,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()

    val alpha by animateFloatAsState(
        targetValue = if (pressed) 0.8f else 1f
    )

    val sharedTransiScope = LocalSharedTransitionScope.current ?: return

    Box(
        Modifier
            .clickable(
                enabled = true ,
                onClick = {
                    onClick(movie)
                } ,
                interactionSource = interactionSource ,
                indication = null
            )
            .padding(8.dp)
            .clip(RoundedCornerShape(15))
    ) {
        if (movie.posterPath != null) {
            with(sharedTransiScope) {
                ImageComponent(
                    imagePath = movie.posterPath!! ,
                    contentDescription = movie.name ,
                    modifier = modifier
//                        .sharedElement(
//                            rememberSharedContentState(movie.posterPath!!),
//                            animatedVisibilityScope = animatedVisibilityScope,
//                        )
                        .sharedBounds(
                            rememberSharedContentState(movie.posterPath!!) ,
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                        .fillMaxSize()
                        .alpha(
                            alpha
                        )

                )
            }
        } else {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(Color.DarkGray)
            ) {

            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp) ,
            horizontalArrangement = Arrangement.SpaceBetween ,
            verticalAlignment = Alignment.Top
        ) {
            RatingChip(
                rating = movie.voteAverage.toString()
            )
            CircularIconButton(
                icon = R.drawable.heart ,
                contentDescription = "Favourite" ,
                onClick = {

                } ,
                background = Color.Black.copy(0.3f) ,
                onBackground = Color.White ,
                buttonSize = 40.dp ,
            )
        }

        AnimatedVisibility(
            visible = pressed ,
            modifier = Modifier.align(Alignment.BottomCenter) ,
            enter = slideInVertically(
                initialOffsetY = {
                    it
                }
            ) ,
            exit = slideOutVertically(
                targetOffsetY = {
                    it
                }
            )
        ) {
            Column(
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth() ,
                verticalArrangement = Arrangement.spacedBy(4.dp) ,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = movie.name ,
                    style = MaterialTheme.typography.titleMedium ,
                    fontWeight = FontWeight.Bold ,
                    color = Color.White ,
                    maxLines = 2
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically ,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Action" ,
                        style = MaterialTheme.typography.titleMedium ,
                        color = Color.White.copy(0.8f)
                    )
                    Text(
                        text = movie.firstAirDate.take(4) ,
                        style = MaterialTheme.typography.titleMedium ,
                        color = Color.White.copy(0.8f)
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun GridItemPrev() {
    MaterialTheme {
//        GridMovieItem(
//            posterPath = null ,
//            modifier = Modifier
//                .aspectRatio(0.65f)
//                .size(300.dp)
//        )
    }
}

@Composable
fun RatingChip(
    modifier: Modifier = Modifier ,
    rating: String
) {
    Row(
        modifier = modifier
            .clip(MaterialTheme.shapes.large)
            .background(
                Color.Black.copy(0.3f)
            )
            .padding(vertical = 4.dp , horizontal = 6.dp) ,
        verticalAlignment = Alignment.CenterVertically ,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.rounded_star) ,
            contentDescription = "" ,
            tint = Color(0xFFFFEB3B)
        )
        Text(
            text = rating.take(3) ,
            style = MaterialTheme.typography.titleMedium ,
            fontWeight = FontWeight.Bold ,
            color = Color.White
        )
    }
}
