package com.bagaspardanailham.greenheroesapp.ui.components

import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.colorResource
import com.bagaspardanailham.greenheroesapp.R

@Composable
fun GHProgressBar(modifier: Modifier, progress: Float) {
    LinearProgressIndicator(
        progress = progress,
        modifier = modifier,
        color = colorResource(id = R.color.dark_green),
        backgroundColor = colorResource(id = R.color.light_grey),
        strokeCap = StrokeCap.Round
    )
}