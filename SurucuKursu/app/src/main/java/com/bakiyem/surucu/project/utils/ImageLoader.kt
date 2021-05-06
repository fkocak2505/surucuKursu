package com.bakiyem.surucu.project.utils

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide


object ImageLoader {
    private const val usePicasso = true
    fun loadImage(context: Context?, imageView: ImageView?, url: String?) {
        Glide.with(context!!)
            .load(url)
            .into(imageView!!)

    }

    fun loadImage(
        context: Context?,
        imageView: ImageView?,
        url: String?,
        @DrawableRes placeHolder: Int
    ) {

        Glide.with(context!!)
            .load(url)
            .placeholder(placeHolder)
            .into(imageView!!)
    }
}