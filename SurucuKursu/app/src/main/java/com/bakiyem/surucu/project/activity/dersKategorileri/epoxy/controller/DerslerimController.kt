package com.bakiyem.surucu.project.activity.dersKategorileri.epoxy.controller

import android.content.Context
import com.airbnb.epoxy.AsyncEpoxyController
import com.bakiyem.surucu.project.activity.dersKategorileri.epoxy.model.dersItem
import com.bakiyem.surucu.project.fragments.main.controller.CListener
import com.bakiyem.surucu.project.model.derslerim.Response4Derslerim

class DerslerimController(var context: Context, var listener: CListener<Response4Derslerim>): AsyncEpoxyController() {

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
                context(context)
                listener {
                    listener.onSelected(dersItem)
                }
            }
        }
    }
}