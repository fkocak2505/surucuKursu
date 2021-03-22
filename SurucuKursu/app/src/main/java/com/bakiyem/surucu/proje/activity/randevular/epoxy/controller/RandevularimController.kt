package com.bakiyem.surucu.proje.activity.randevular.epoxy.controller

import com.airbnb.epoxy.AsyncEpoxyController
import com.bakiyem.surucu.proje.activity.randevular.epoxy.model.randevularimItem
import com.bakiyem.surucu.proje.model.randevularim.Response4Randevularim

class RandevularimController : AsyncEpoxyController() {

    var randevularimList: MutableList<Response4Randevularim> = mutableListOf()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        randevularimList.forEachIndexed { index, response4Randevularim ->
            randevularimItem {
                id("$index")
                randevu(response4Randevularim)
            }
        }
    }
}