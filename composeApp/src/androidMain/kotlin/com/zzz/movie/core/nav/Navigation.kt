package com.zzz.movie.core.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.zzz.movie.feature_movies.presentation.details.DetailsPage
import com.zzz.movie.feature_movies.presentation.home.HomePage

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    innerPadding : PaddingValues = PaddingValues()
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home,
        modifier = modifier
    ){
        composable<Screen.Home>{
            HomePage(
                animatedVisibilityScope = this,
                onClick = {
                    navController.navigate(Screen.Detail(it.id,it.posterPath))
                },
                innerPadding = innerPadding
            )
        }

        composable<Screen.Detail>{
            val route = it.toRoute<Screen.Detail>()
            DetailsPage(
                animatedVisibilityScope = this,
                id = route.id,
                posterPath = route.posterPath,
                innerPadding = innerPadding,
                onBack = {
                    navController.navigateUp()
                }
            )
        }
    }

}