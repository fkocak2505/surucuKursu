package com.bakiyem.surucu.proje.fragments.main.epoxyModel.subController

import com.airbnb.epoxy.AsyncEpoxyController
import com.bakiyem.surucu.proje.fragments.main.controller.CListener
import com.bakiyem.surucu.proje.fragments.main.dataModel.StaticData
import com.bakiyem.surucu.proje.fragments.main.epoxyModel.subController.epoxyModel.subItemGridData

class GridController: AsyncEpoxyController() {

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

            }
        }
    }
}