package com.bakiyem.surucu.proje.activity.sinavlarim.epoxy.controller

import com.airbnb.epoxy.AsyncEpoxyController
import com.bakiyem.surucu.proje.activity.sinavlarim.epoxy.model.sinavlarimItem
import com.bakiyem.surucu.proje.fragments.main.controller.CListener

class SinavlarimController(var listener: CListener<String>) : AsyncEpoxyController() {

    var sinavlarim: List<String> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        sinavlarim.forEachIndexed { index, sinavItem ->
            sinavlarimItem {
                id("sinavlarim $index")
                sinavItem(sinavItem)
                listener {
                    listener.onSelected(sinavItem)
                }
            }
        }
    }
}