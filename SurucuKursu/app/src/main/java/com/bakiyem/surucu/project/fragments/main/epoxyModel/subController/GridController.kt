package com.bakiyem.surucu.project.fragments.main.epoxyModel.subController

import com.airbnb.epoxy.AsyncEpoxyController
import com.bakiyem.surucu.project.fragments.main.controller.CListener
import com.bakiyem.surucu.project.fragments.main.dataModel.StaticData
import com.bakiyem.surucu.project.fragments.main.epoxyModel.subController.epoxyModel.subItemGridData

class GridController(private val listener: CListener<Any>): AsyncEpoxyController() {

    var gridData: List<StaticData> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        gridData.forEach {
            subItemGridData {
                id(it.id)
                subStaticData(it)
                listener {
                    listener.onSelected(it)
                }
            }
        }
    }
}