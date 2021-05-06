package com.bakiyem.surucu.project.activity.odemeBilgilerim.epoxy.controller

import android.content.Context
import com.airbnb.epoxy.AsyncEpoxyController
import com.bakiyem.surucu.project.activity.odemeBilgilerim.epoxy.model.gridItem4OdemeBilgilerim
import com.bakiyem.surucu.project.activity.sinavSonuclarim.model.stringItem
import com.bakiyem.surucu.project.fragments.main.controller.CListener
import com.bakiyem.surucu.project.model.odemeBilgilerim.Response4BorcOzet
import com.bakiyem.surucu.project.model.odemeBilgilerim.Response4OdemeBilgileri

class OdemeBilgileriController(val context: Context,val listener: CListener<Response4OdemeBilgileri>): AsyncEpoxyController() {

    var borcListesi: MutableList<Response4OdemeBilgileri> = mutableListOf()
        set(value) {
            field = value
            requestModelBuild()
        }

    var borcOzet = Response4BorcOzet()
        set(value) {
            field = value
            requestModelBuild()
        }


    override fun buildModels() {

        stringItem {
            id("borcBilgiler")
            title("Borç Bilgilerim")
        }

        gridItem4OdemeBilgilerim {
            id("1")
            odemeBilgilerList(borcListesi)
            context(context)
            listener {
                listener.onSelected(it)
            }
        }

        /*toplamBorcItem {
            id("123123")
            borcOzet(borcOzet)
        }*/

    }
}