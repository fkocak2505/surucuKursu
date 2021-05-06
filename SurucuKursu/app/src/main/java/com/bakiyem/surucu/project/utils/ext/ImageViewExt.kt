package com.bakiyem.surucu.project.utils.ext

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bakiyem.surucu.project.utils.ImageLoader

fun ImageView.loadImage(url: String?, @DrawableRes placeHolder: Int = 0) {
    if (placeHolder != 0) {
        ImageLoader.loadImage(context, this, url, placeHolder)
    } else {
        ImageLoader.loadImage(context, this, url)
    }
}