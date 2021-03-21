package com.bakiyem.surucu.proje.activity.odemeBilgilerim.epoxy.model.controller

import android.content.Context
import com.airbnb.epoxy.AsyncEpoxyController
import com.bakiyem.surucu.proje.activity.odemeBilgilerim.epoxy.model.model.gridSubItemModel4OdemeBilgileri
import com.bakiyem.surucu.proje.fragments.main.controller.CListener
import com.bakiyem.surucu.proje.model.odemeBilgilerim.Response4OdemeBilgileri

class GridController4OdemeBilgileri(private val context: Context,private val listener: CListener<Response4OdemeBilgileri>): AsyncEpoxyController() {

    var gridData: List<Response4OdemeBilgileri> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        gridData.forEachIndexed { index, response4OdemeBilgileri ->
            gridSubItemModel4OdemeBilgileri {
                id("subItem $index")
                response4OdemeBilgileri(response4OdemeBilgileri)
                context(context)
                listener {
                    listener.onSelected(response4OdemeBilgileri)
                }
            }
        }

    }
}