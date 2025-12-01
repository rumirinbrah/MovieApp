package com.zzz.movie.feature_movies.presentation.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GlassContainer(
    modifier: Modifier = Modifier,
    content : @Composable ()->Unit
) {
    Box(
        modifier.clip(RoundedCornerShape(15))
            .background(Color.Gray.copy(0.4f))
            .border(1.dp,Color.Gray.copy(0.3f),RoundedCornerShape(15))
            .padding(26.dp)
    ){
        content()
    }
}