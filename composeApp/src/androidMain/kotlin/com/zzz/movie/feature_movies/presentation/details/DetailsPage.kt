package com.zzz.movie.feature_movies.presentation.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush.Companion.horizontalGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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
import com.zzz.movie.core.presentation.RoundedIconButton
import com.zzz.movie.core.presentation.VerticalSpace
import com.zzz.movie.core.presentation.VerticalSpaceFractional
import com.zzz.movie.domain.model.Movie
import com.zzz.movie.feature_movies.presentation.details.components.GenreChip
import com.zzz.movie.feature_movies.presentation.details.components.GlassContainer
import com.zzz.movie.feature_movies.presentation.details.components.IconTextChip
import com.zzz.movie.feature_movies.presentation.details.viewmodel.MovieDetailViewModel
import com.zzz.movie.feature_movies.presentation.home.components.RatingChip
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DetailsPage(
    modifier: Modifier = Modifier ,
    id : Int ,
    posterPath : String?,
//    videoKey : String,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onBack : ()->Unit,
    innerPadding : PaddingValues = PaddingValues()
) {

    val sharedTransiScope = LocalSharedTransitionScope.current ?: return
    val detailViewModel = koinViewModel<MovieDetailViewModel>()
    val state by detailViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        detailViewModel.getMovieDetails(id,null)
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
                                //                    .sharedElement(
                                //                        rememberSharedContentState(imagePath),
                                //                        animatedVisibilityScope = animatedVisibilityScope,
                                //                    )
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
                AnimatedVisibility(!sharedTransiScope.isTransitionActive) {
                    Row(
                        Modifier.fillMaxWidth()
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
                            RatingChip(rating = state.rating.toString().take(2))
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

                Box(Modifier.fillMaxHeight(0.32f).background(Color.DarkGray))

                if(state.loading){
                    CircularProgressIndicator()
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
                                    text = state.runtime
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