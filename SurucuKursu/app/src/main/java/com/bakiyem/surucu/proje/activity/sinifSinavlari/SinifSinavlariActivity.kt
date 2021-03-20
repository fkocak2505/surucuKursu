package com.bakiyem.surucu.proje.activity.sinifSinavlari

import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.activity.denemeSinavi.DenemeSinaviActivity
import com.bakiyem.surucu.proje.activity.sinifSinavlari.epoxy.controller.SinifSinaviController
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.bakiyem.surucu.proje.fragments.main.controller.CListener
import com.bakiyem.surucu.proje.model.sinifSinavi.Response4SinifSinavi
import kotlinx.android.synthetic.main.activity_sinif_sinavlari.*

class SinifSinavlariActivity : BaseActivity(), CListener<Response4SinifSinavi> {

    lateinit var sinifSinavi: SinifSinaviVM

    override fun getLayoutID(): Int = R.layout.activity_sinif_sinavlari

    override fun initVM() {
        sinifSinavi = ViewModelProviders.of(this).get(SinifSinaviVM::class.java)
    }

    override fun initChangeFont() {

    }

    override fun initReq() {
        sinifSinavi.getSinifSinavi()
    }

    override fun initVMListener() {
        sinifSinavi.sinifSinaviLD.observe(this, {
            it?.let {
                prepareSinifSinaviData(it)
            } ?: run {
                toast("Error Sinif Sinavi")
                onBackPressed()
            }
        })

        sinifSinavi.sinifSinaviQuizLD.observe(this, {
            it?.let {
                if (it.size != 0)
                    DenemeSinaviActivity.start(this, "1", "2", false,"Sınıf Sınavı",  it)
                else
                    toast("Deneme sinavi size 0")
            } ?: run {
                toast("Error Sinif Sinavi Quiz")
                onBackPressed()
            }
        })


    }

    override fun onCreateMethod() {

        goBack()

    }

    private fun prepareSinifSinaviData(sinifSinaviList: List<Response4SinifSinavi>) {
        val controller = SinifSinaviController(this)
        erv_sinifSinavlarim.setController(controller)
        controller.sinifSinaviList = sinifSinaviList
    }

    private fun goBack() {
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onSelected(data: Response4SinifSinavi) {
        sinifSinavi.getSinifSinaviQuiz(data.sinavId!!)
    }
}