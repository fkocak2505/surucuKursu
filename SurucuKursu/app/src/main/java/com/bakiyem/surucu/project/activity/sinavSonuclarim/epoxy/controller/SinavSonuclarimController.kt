package com.bakiyem.surucu.project.activity.sinavSonuclarim.epoxy.controller

import android.content.Context
import com.airbnb.epoxy.AsyncEpoxyController
import com.bakiyem.surucu.project.activity.sinavSonuclarim.model.sinavSonuclarimGirilenSinavListItem
import com.bakiyem.surucu.project.activity.sinavSonuclarim.model.sinavSonuclarimItem
import com.bakiyem.surucu.project.activity.sinavSonuclarim.model.stringItem
import com.bakiyem.surucu.project.model.sinavSonuclarim.Response4SinavSonuclarim
import com.bakiyem.surucu.project.model.sinifSinavi.Response4SinifSinavi

class SinavSonuclarimController(val context: Context): AsyncEpoxyController() {

    var sinavSonuclarim: List<Response4SinavSonuclarim> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    var sinifSinaviList: List<Response4SinifSinavi> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        stringItem {
            id("ozetBilgi")
            title("Özet Bilgiler")
        }

        sinavSonuclarim.forEachIndexed { index, response4SinavSonuclarim ->
            sinavSonuclarimItem {
                id("sinavSonuc $index")
                sinavSonuclarim(response4SinavSonuclarim)
            }
        }

        stringItem {
            id("girdigimSinavlar")
            title("Girdiğim Sınavlar")
        }

        sinifSinaviList.forEachIndexed { index, sinifSinaviListItem ->
            sinavSonuclarimGirilenSinavListItem {
                id("sinifSinavi $index")
                sinifSinavi(sinifSinaviListItem)
                context(context)
            }
        }
    }
}