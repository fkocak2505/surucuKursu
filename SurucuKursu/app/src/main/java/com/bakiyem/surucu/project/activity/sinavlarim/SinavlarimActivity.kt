package com.bakiyem.surucu.project.activity.sinavlarim

import android.content.Intent
import android.widget.ImageView
import android.widget.VideoView
import com.bakiyem.surucu.project.R
import com.bakiyem.surucu.project.activity.denemeSinavi.DenemeSinaviActivity
import com.bakiyem.surucu.project.activity.denemeSinavlarimKlavuz.DenemeSinavlarimKlavuzActivity
import com.bakiyem.surucu.project.activity.ozelSinavlarim.OzelSinavlarimActivity
import com.bakiyem.surucu.project.activity.sinavlarim.epoxy.controller.SinavlarimController
import com.bakiyem.surucu.project.activity.sinifSinavlari.SinifSinavlariActivity
import com.bakiyem.surucu.project.base.activity.BaseActivity
import com.bakiyem.surucu.project.fragments.main.controller.CListener
import com.bakiyem.surucu.project.model.kurs.Response4Kurs
import com.bakiyem.surucu.project.utils.ext.loadImage
import com.bakiyem.surucu.project.utils.ext.semibold
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_sinavlarim.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class SinavlarimActivity: BaseActivity(), CListener<String> {
    override fun getLayoutID(): Int = R.layout.activity_sinavlarim

    override fun initVM() {

    }

    override fun initChangeFont() {
        tv_hugeTitle.semibold()

        iv_rootImage.loadImage(Hawk.get<Response4Kurs>("kursBilgisi").logo)
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

    /*override fun onSelected(data: String) {
        when(data){
            "Deneme Sınavları" -> {
                startActivity(Intent(this, DenemeSinavlarimKlavuzActivity::class.java))
            }
            "Özel Sınavlar" -> {
                startActivity(Intent(this, OzelSinavlarimActivity::class.java))
            }
            "Sınıf Sınavları" -> {
                startActivity(Intent(this, SinifSinavlariActivity::class.java))
            }
            "Hazır Cevap Sınavlar" -> {
                DenemeSinaviActivity.start(this, "2", "", true, "Hazır Cevap Sınavlar", mutableListOf())
            }
        }
    }*/

    override fun onSelected(
        data: String,
        videoView: VideoView?,
        placeHolder: ImageView?,
        playIcon: ImageView?
    ) {
        when(data){
            "Deneme Sınavları" -> {
                startActivity(Intent(this, DenemeSinavlarimKlavuzActivity::class.java))
            }
            "Özel Sınavlar" -> {
                startActivity(Intent(this, OzelSinavlarimActivity::class.java))
            }
            "Sınıf Sınavları" -> {
                startActivity(Intent(this, SinifSinavlariActivity::class.java))
            }
            "Hazır Cevap Sınavlar" -> {
                DenemeSinaviActivity.start(this, "4", "", true, "Hazır Cevap Sınavlar", mutableListOf())
            }
        }
    }
}