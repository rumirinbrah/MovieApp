package com.zzz.movie.core.presentation

import android.widget.Space
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun VerticalSpace(
    height : Dp = 20.dp,
) {
    Spacer(Modifier.height(height))
}
@Composable
fun VerticalSpaceFractional(
    height : Float = 0.1f,

) {
    Spacer(Modifier.fillMaxWidth(height))
}