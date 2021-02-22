package com.bakiyem.surucu.proje.activity.kadromuz.controller

import com.airbnb.epoxy.AsyncEpoxyController
import com.bakiyem.surucu.proje.activity.kadromuz.epoxyModel.kadromuzItem
import com.bakiyem.surucu.proje.model.araclar.Response4Araclar
import com.bakiyem.surucu.proje.model.kadromuz.Response4Kadromuz

class KadromuzController: AsyncEpoxyController() {

    var kadromuz: List<Response4Kadromuz> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        kadromuz.forEachIndexed { index, response4Kadromuz ->
            kadromuzItem {
                id("kadromuz $index")
                kadromuzItem(response4Kadromuz)
            }
        }
    }

}