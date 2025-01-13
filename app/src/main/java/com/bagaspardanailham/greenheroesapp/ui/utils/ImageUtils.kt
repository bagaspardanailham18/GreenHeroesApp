package com.bagaspardanailham.greenheroesapp.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.size.Scale
import kotlinx.coroutines.Dispatchers

@Composable
fun CoilImage(modifier: Modifier, imgUrl: String?, contentScale: ContentScale?, colorFilter: ColorFilter?) {
    val context = LocalContext.current
    val listener = object : ImageRequest.Listener {
        override fun onError(request: ImageRequest, result: ErrorResult) {
            super.onError(request, result)
        }

        override fun onSuccess(request: ImageRequest, result: SuccessResult) {
            super.onSuccess(request, result)
        }
    }
    val imageRequest = ImageRequest.Builder(context)
        .data(imgUrl)
        .crossfade(true)
        .scale(Scale.FILL)
        .listener(listener)
        .dispatcher(Dispatchers.IO)
        .memoryCacheKey(imgUrl)
        .diskCacheKey(imgUrl)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()

    AsyncImage(
        model = imageRequest,
        contentDescription = "Image Description",
        modifier = modifier,
        contentScale = contentScale ?: ContentScale.None,
        colorFilter = colorFilter
    )
}