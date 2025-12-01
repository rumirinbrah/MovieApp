package com.zzz.movie.feature_movies.presentation.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GenreChip(
    modifier: Modifier = Modifier,
    text : String
) {
    Box (
        Modifier.clip(MaterialTheme.shapes.extraLarge)
            .background(Color(0xFF550E91))
            .padding(vertical = 5.dp , horizontal = 8.dp)
    ){
        Text(
            text ,
            fontSize = 15.sp ,
            color = Color.White.copy(0.8f)
        )
    }
}