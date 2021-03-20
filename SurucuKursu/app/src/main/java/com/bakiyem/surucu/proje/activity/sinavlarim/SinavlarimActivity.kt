package com.bakiyem.surucu.proje.activity.sinavlarim

import android.content.Intent
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.activity.denemeSinavi.DenemeSinaviActivity
import com.bakiyem.surucu.proje.activity.denemeSinavlarimKlavuz.DenemeSinavlarimKlavuzActivity
import com.bakiyem.surucu.proje.activity.ozelSinavlarim.OzelSinavlarimActivity
import com.bakiyem.surucu.proje.activity.sinavlarim.epoxy.controller.SinavlarimController
import com.bakiyem.surucu.proje.activity.sinifSinavlari.SinifSinavlari
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.bakiyem.surucu.proje.fragments.main.controller.CListener
import kotlinx.android.synthetic.main.activity_sinavlarim.*

class SinavlarimActivity: BaseActivity(), CListener<String> {
    override fun getLayoutID(): Int = R.layout.activity_sinavlarim

    override fun initVM() {

    }

    override fun initChangeFont() {

    }

    override fun initReq() {

    }

    override fun initVMListener() {
        prepareERVSinavlarim()
    }

    override fun onCreateMethod() {
        goBack()
    }

    private fun prepareERVSinavlarim(){
        val listOfSinavlarim: MutableList<String> = mutableListOf()
        listOfSinavlarim.add("Deneme Sınavları")
        listOfSinavlarim.add("Özel Sınavlar")
        listOfSinavlarim.add("Sınıf Sınavları")
        listOfSinavlarim.add("Hazır Cevap Sınavlar")

        val sinavlarimController = SinavlarimController(this)
        sinavlarimController.sinavlarim = listOfSinavlarim
        erv_sinavlarim.setController(sinavlarimController)

    }

    private fun goBack(){
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onSelected(data: String) {
        when(data){
            "Deneme Sınavları" -> {
                startActivity(Intent(this, DenemeSinavlarimKlavuzActivity::class.java))
            }
            "Özel Sınavlar" -> {
                startActivity(Intent(this, OzelSinavlarimActivity::class.java))
            }
            "Sınıf Sınavları" -> {
                startActivity(Intent(this, SinifSinavlari::class.java))
            }
            "Hazır Cevap Sınavlar" -> {
                DenemeSinaviActivity.start(this, "2", "", true, mutableListOf())
            }
        }
    }
}