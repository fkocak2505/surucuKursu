package com.bakiyem.surucu.proje.activity.dersListesi.epoxy.controller

import com.airbnb.epoxy.AsyncEpoxyController
import com.bakiyem.surucu.proje.activity.dersListesi.epoxy.model.dersListesiItem
import com.bakiyem.surucu.proje.fragments.main.controller.CListener
import com.bakiyem.surucu.proje.model.dersListesi.Response4DersListesi

class DersListesiController(var listener: CListener<Response4DersListesi>, var color: String) :
    AsyncEpoxyController() {

    var dersListesi: List<Response4DersListesi> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }


    override fun buildModels() {

        dersListesi.forEachIndexed { index, dersListesiItem ->
            dersListesiItem {
                id("dersListesi $index")
                dersListesiItem(dersListesiItem)
                color(color)
                position(index + 1)
                listener {
                    listener.onSelected(dersListesiItem)
                }
            }
        }
    }
}