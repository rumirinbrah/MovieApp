package com.zzz.movie.feature_movies.presentation.details

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush.Companion.horizontalGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zzz.movie.LocalSharedTransitionScope
import com.zzz.movie.R
import com.zzz.movie.core.presentation.CircularIconButton
import com.zzz.movie.core.presentation.ImageComponent
import com.zzz.movie.core.presentation.PulseLoading
import com.zzz.movie.core.presentation.VerticalSpace
import com.zzz.movie.feature_movies.presentation.details.components.GenreChip
import com.zzz.movie.feature_movies.presentation.details.components.GlassContainer
import com.zzz.movie.feature_movies.presentation.details.components.IconTextChip
import com.zzz.movie.feature_movies.presentation.details.viewmodel.MovieDetailAction
import com.zzz.movie.feature_movies.presentation.details.viewmodel.MovieDetailViewModel
import com.zzz.movie.feature_movies.presentation.home.components.RatingChip
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DetailsPage(
    modifier: Modifier = Modifier ,
    id : Int ,
    posterPath : String?,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onBack : ()->Unit,
    innerPadding : PaddingValues = PaddingValues()
) {

    val sharedTransiScope = LocalSharedTransitionScope.current ?: return
    val detailViewModel = koinViewModel<MovieDetailViewModel>()
    val state by detailViewModel.state.collectAsStateWithLifecycle()

    val events = detailViewModel.events

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        events.collect {
            Toast.makeText( context, it , Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(Unit) {
        detailViewModel.onAction(MovieDetailAction.GetMovieDetails(id,null))
    }

    Box(
        modifier
            .background(
                brush = horizontalGradient(
                    colors = listOf(
                        Color(0xFF060618) ,
                        Color(0xFF320756) ,
                    ) ,
                    startX = 100f
                )
            )
    ) {

            //image
            Box() {
                posterPath?.let { imagePath ->
                    with(sharedTransiScope) {
                        ImageComponent(
                            imagePath = imagePath ,
                            modifier = Modifier
                                .sharedBounds(
                                    rememberSharedContentState(imagePath) ,
                                    animatedVisibilityScope = animatedVisibilityScope
                                )
                                .alpha(0.7f)
                                .fillMaxWidth()
                                .fillMaxHeight(0.35f) ,
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                //top bar
                AnimatedVisibility(
                    !sharedTransiScope.isTransitionActive,
                    enter = slideInVertically{
                        -it
                    },
                    exit = slideOutVertically{
                        -it
                    }
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .statusBarsPadding()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween ,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column {
                            CircularIconButton(
                                icon = R.drawable.arrow_back_long_svgrepo_com ,
                                contentDescription = "Back" ,
                                onClick = {
                                    onBack()
                                } ,
                                background = Color.Black.copy(0.3f) ,
                                onBackground = Color.White ,
                                iconSize = 30.dp ,
                            )
                            VerticalSpace(10.dp)
                            RatingChip(rating = state.rating.toString().take(3))
                        }

                        CircularIconButton(
                            icon = R.drawable.heart ,
                            contentDescription = "Favourite" ,
                            onClick = {

                            } ,
                            background = Color.Black.copy(0.3f) ,
                            onBackground = Color.White ,
                            iconSize = 30.dp ,
                        )
                    }
                }
            }
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(innerPadding)
            ) {

                Box(Modifier
                    .fillMaxHeight(0.32f)
                    .background(Color.DarkGray))

                if(state.loading){
                    PulseLoading(
                        Modifier.size(30.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }

                Column(
                    Modifier.verticalScroll(rememberScrollState())
                ){
                    GlassContainer {
                        Column(
                            Modifier.fillMaxWidth() ,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                state.name ,
                                fontWeight = FontWeight.Medium ,
                                fontSize = 18.sp ,
                                color = Color.White
                            )
                            Row (
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ){
                                IconTextChip(
                                    icon = R.drawable.calendar_svgrepo_com,
                                    text = state.date.take(4)
                                )
                                IconTextChip(
                                    icon = R.drawable.time_svgrepo_com,
                                    text = "${state.runtime} Min"
                                )
                                GenreChip(text = state.genre)
                            }
                        }
                    }

                    VerticalSpace()

                    GlassContainer {
                        Column(
                            Modifier.fillMaxWidth() ,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                "Synopsis" ,
                                fontWeight = FontWeight.Bold ,
                                fontSize = 20.sp ,
                                color = Color.White
                            )

                            Text(
                                state.synopsis ,
                                fontWeight = FontWeight.Normal ,
                                fontSize = 17.sp ,
                                color = Color.White.copy(0.8f),
                                lineHeight = 32.sp
                            )

                        }
                    }
                    VerticalSpace()

                    GlassContainer {
                        Column(
                            Modifier.fillMaxWidth() ,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                "Director" ,
                                fontWeight = FontWeight.Bold ,
                                fontSize = 20.sp ,
                                color = Color.White
                            )

                            Text(
                                state.director ,
                                fontWeight = FontWeight.Normal ,
                                fontSize = 17.sp ,
                                color = Color.White.copy(0.8f)
                            )

                        }
                    }
                }

            }





    }
}