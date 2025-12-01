package com.zzz.movie.core.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter

/**
 * @author zyzz
 */
@Composable
fun ImageComponent(
    modifier: Modifier = Modifier ,
    imagePath: String ,
    contentDescription: String? = null ,
    contentScale: ContentScale = ContentScale.Fit ,
//    size: Dp = 100.dp
) {

    val context = LocalPlatformContext.current
    val painter = rememberAsyncImagePainter(imagePath.loadImageUrl())
    val state by painter.state.collectAsStateWithLifecycle()

    Box(
        modifier
    ) {
        when (state) {
            is AsyncImagePainter.State.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is AsyncImagePainter.State.Error -> {
                Text(
                    "Failed to load image" ,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> Unit
        }
        Image(
            painter = painter ,
            contentDescription = contentDescription ,
            contentScale = contentScale ,
            modifier = Modifier.matchParentSize()
        )
    }
}

private fun String.loadImageUrl(size: String = "original"): String {
    return "https://image.tmdb.org/t/p/$size/$this"
}
//https://image.tmdb.org/t/p/original/cVxVGwHce6xnW8UaVUggaPXbmoE.jpg
