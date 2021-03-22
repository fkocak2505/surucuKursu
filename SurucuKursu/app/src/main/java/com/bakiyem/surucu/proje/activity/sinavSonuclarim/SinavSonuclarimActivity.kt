package com.bakiyem.surucu.proje.activity.sinavSonuclarim

import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.activity.sinavSonuclarim.epoxy.controller.SinavSonuclarimController
import com.bakiyem.surucu.proje.activity.sinifSinavlari.SinifSinaviVM
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.bakiyem.surucu.proje.model.sinavSonuclarim.Response4SinavSonuclarim
import com.bakiyem.surucu.proje.model.sinifSinavi.Response4SinifSinavi
import com.bakiyem.surucu.proje.utils.ext.semibold
import kotlinx.android.synthetic.main.activity_sinav_sonuclarim.*

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
    }

    override fun initReq() {
        sinavSonuclarimVM.getSinavSonuclarim()
    }

    override fun initVMListener() {
        prepareWithBaseVM(sinavSonuclarimVM)
        sinavSonuclarimVM.sinavSonuclarimLD.observe(this, {
            it?.let {
                sinavSonuclarimList.add(it)
                sinifSinaviVM.getSinifSinavi()
            }?: run{
                toast("Error sinav sonuçlarım..")
                onBackPressed()
            }
        })

        sinifSinaviVM.sinifSinaviLD.observe(this, {
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