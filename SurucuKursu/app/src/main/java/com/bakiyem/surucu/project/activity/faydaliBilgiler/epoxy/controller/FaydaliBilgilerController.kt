package com.bakiyem.surucu.project.activity.faydaliBilgiler.epoxy.controller

import com.airbnb.epoxy.AsyncEpoxyController
import com.bakiyem.surucu.project.activity.faydaliBilgiler.epoxy.model.faydaliBilgilerItem
import com.bakiyem.surucu.project.fragments.main.controller.CListener
import com.bakiyem.surucu.project.model.faydaliBilgiler.Response4FaydaliBilgiler

class FaydaliBilgilerController(val listener: CListener<Response4FaydaliBilgiler>): AsyncEpoxyController() {

    var faydaliBilgilerList: MutableList<Response4FaydaliBilgiler> = mutableListOf()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        faydaliBilgilerList.forEachIndexed { index, response4FaydaliBilgiler ->
            faydaliBilgilerItem {
                id("faydaliBilgiler $index")
                faydaliBilgilerItem(response4FaydaliBilgiler)
                position(index + 1)
                listener {
                    listener.onSelected(response4FaydaliBilgiler)
                }
            }
        }
    }
}