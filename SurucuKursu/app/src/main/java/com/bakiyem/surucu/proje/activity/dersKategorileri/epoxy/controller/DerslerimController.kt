package com.bakiyem.surucu.proje.activity.dersKategorileri.epoxy.controller

import com.airbnb.epoxy.AsyncEpoxyController
import com.bakiyem.surucu.proje.activity.dersKategorileri.epoxy.model.dersItem
import com.bakiyem.surucu.proje.fragments.main.controller.CListener
import com.bakiyem.surucu.proje.model.derslerim.Response4Derslerim

class DerslerimController(var listener: CListener<Response4Derslerim>): AsyncEpoxyController() {

    var derslerim: List<Response4Derslerim> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {

        derslerim.forEachIndexed { index, dersItem ->
            dersItem {
                id("derslerim $index")
                dersItem(dersItem)
                listener {
                    listener.onSelected(dersItem)
                }
            }
        }
    }
}