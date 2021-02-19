package com.bakiyem.surucu.proje.activity.araclarimiz.controller

import com.airbnb.epoxy.AsyncEpoxyController
import com.bakiyem.surucu.proje.activity.araclarimiz.epoxyModel.araclarItem
import com.bakiyem.surucu.proje.fragments.course.dataModel.CourseItem
import com.bakiyem.surucu.proje.model.araclar.Response4Araclar

class AraclarimizController : AsyncEpoxyController() {

    var araclar: List<Response4Araclar> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {

        araclar.forEachIndexed { index, response4Araclar ->
            araclarItem {
                id("arac $index")
                aracItem(response4Araclar)
            }
        }
    }
}