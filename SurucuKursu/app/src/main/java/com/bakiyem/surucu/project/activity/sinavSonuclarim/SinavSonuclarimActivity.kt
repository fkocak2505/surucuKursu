package com.bakiyem.surucu.project.activity.sinavSonuclarim

import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.project.R
import com.bakiyem.surucu.project.activity.sinavSonuclarim.epoxy.controller.SinavSonuclarimController
import com.bakiyem.surucu.project.activity.sinifSinavlari.SinifSinaviVM
import com.bakiyem.surucu.project.base.activity.BaseActivity
import com.bakiyem.surucu.project.model.kurs.Response4Kurs
import com.bakiyem.surucu.project.model.sinavSonuclarim.Response4SinavSonuclarim
import com.bakiyem.surucu.project.model.sinifSinavi.Response4SinifSinavi
import com.bakiyem.surucu.project.utils.ext.loadImage
import com.bakiyem.surucu.project.utils.ext.semibold
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_sinav_sonuclarim.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class SinavSonuclarimActivity: BaseActivity() {

    lateinit var sinifSinaviVM: SinifSinaviVM

    lateinit var sinavSonuclarimVM: SinavSonuclarimVM

    var sinavSonuclarimList: MutableList<Response4SinavSonuclarim> = mutableListOf()

    var girdigimSinavlarList: MutableList<Response4SinifSinavi> = mutableListOf()

    override fun getLayoutID(): Int = R.layout.activity_sinav_sonuclarim

    override fun initVM() {
        sinifSinaviVM = ViewModelProviders.of(this).get(SinifSinaviVM::class.java)
        sinavSonuclarimVM = ViewModelProviders.of(this).get(SinavSonuclarimVM::class.java)
    }

    override fun initChangeFont() {
        tv_hugeTitle.semibold()

        iv_rootImage.loadImage(Hawk.get<Response4Kurs>("kursBilgisi").logo)
    }

    override fun initReq() {
        sinavSonuclarimVM.getSinavSonuclarim()
    }

    override fun initVMListener() {
        prepareWithBaseVM(sinavSonuclarimVM)
        sinavSonuclarimVM.sinavSonuclarimLD.observe(this, {
            it?.let {
                sinavSonuclarimList.add(it)
                sinifSinaviVM.getGirilenSinav()
            }?: run{
                toast("Error sinav sonuçlarım..")
                onBackPressed()
            }
        })

        sinifSinaviVM.sinifSonuclarimListLD.observe(this, {
            it?.let {
                girdigimSinavlarList = it
                prepareEpoxy()
            } ?: run {
                toast("Error Sinif Sinavi")
                onBackPressed()
            }
        })
    }

    override fun onCreateMethod() {
        goBack()
    }

    private fun prepareEpoxy(){
        val controller = SinavSonuclarimController(applicationContext)
        erv_sinavSonuclarim.setController(controller)
        controller.sinavSonuclarim = sinavSonuclarimList
        controller.sinifSinaviList = girdigimSinavlarList
    }

    private fun goBack(){
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }
}