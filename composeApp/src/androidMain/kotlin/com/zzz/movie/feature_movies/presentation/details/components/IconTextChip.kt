package com.zzz.movie.feature_movies.presentation.details.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IconTextChip(
    modifier: Modifier = Modifier,
    @DrawableRes icon : Int,
    text : String
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ){
        Icon(
            painter = painterResource(icon),
            contentDescription = text,
            modifier = Modifier.size(20.dp),
            tint = Color.White.copy(0.8f)
        )
        Text(
            text ,
            fontSize = 15.sp ,
            color = Color.White.copy(0.8f)
        )
    }
}