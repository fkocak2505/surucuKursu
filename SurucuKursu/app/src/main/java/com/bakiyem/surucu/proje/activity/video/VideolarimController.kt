package com.bakiyem.surucu.proje.activity.video

import android.content.Context
import com.airbnb.epoxy.AsyncEpoxyController
import com.bakiyem.surucu.proje.fragments.main.controller.CListener
import com.bakiyem.surucu.proje.model.video.Response4Video

class VideolarimController(var context: Context, var listener: CListener<Response4Video>): AsyncEpoxyController()  {

    var videolarim: MutableList<Response4Video> = mutableListOf()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        videolarim.forEachIndexed { index, response4Video ->
            videolarimItem {
                id("videolarim $index")
                videoItem(response4Video)
                context(context)
                listener { response4Video, videoView, placeHolder, playIcon ->
                    listener.onSelected(response4Video, videoView, placeHolder, playIcon)
                }
            }
        }
    }

}