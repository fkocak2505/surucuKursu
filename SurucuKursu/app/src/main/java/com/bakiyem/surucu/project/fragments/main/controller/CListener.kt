package com.bakiyem.surucu.project.fragments.main.controller

import android.widget.ImageView
import android.widget.VideoView

interface CListener<T> {
    fun onSelected(
        data: T,
        videoView: VideoView? = null,
        placeHolder: ImageView? = null,
        playIcon: ImageView? = null
    )
}