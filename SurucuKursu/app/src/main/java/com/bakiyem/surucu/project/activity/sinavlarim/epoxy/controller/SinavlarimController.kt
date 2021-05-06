package com.bakiyem.surucu.project.activity.sinavlarim.epoxy.controller

import com.airbnb.epoxy.AsyncEpoxyController
import com.bakiyem.surucu.project.activity.sinavlarim.epoxy.model.sinavlarimItem
import com.bakiyem.surucu.project.fragments.main.controller.CListener

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