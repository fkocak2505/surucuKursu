package com.bakiyem.surucu.project.activity.sinifSinavlari.epoxy.controller

import com.airbnb.epoxy.AsyncEpoxyController
import com.bakiyem.surucu.project.activity.sinifSinavlari.epoxy.model.sinifSinaviListItem
import com.bakiyem.surucu.project.fragments.main.controller.CListener
import com.bakiyem.surucu.project.model.sinifSinavi.Response4SinifSinavi

class SinifSinaviController(var listener: CListener<Response4SinifSinavi>): AsyncEpoxyController() {

    var sinifSinaviList: List<Response4SinifSinavi> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        sinifSinaviList.forEachIndexed { index, sinifSinaviListItem ->
            sinifSinaviListItem {
                id("sinifSinavi $index")
                sinifSinavi(sinifSinaviListItem)
                listener {
                    listener.onSelected(sinifSinaviListItem)
                }
            }
        }
    }
}