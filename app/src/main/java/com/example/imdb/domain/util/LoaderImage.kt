package com.example.imdb.domain.util

import android.widget.ImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest

fun ImageView.loadImage(imgUrl: String) {
    val imageLoader = ImageLoader.Builder(this.context)
        .componentRegistry { add(SvgDecoder(this@loadImage.context)) }
        .build()

    val request = ImageRequest.Builder(this.context)
        .data(imgUrl)
        .target(this)
        .build()
    imageLoader.enqueue(request)
}