package com.bakiyem.surucu.project.activity.sinifSinavlari

import android.widget.ImageView
import android.widget.VideoView
import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.project.R
import com.bakiyem.surucu.project.activity.denemeSinavi.DenemeSinaviActivity
import com.bakiyem.surucu.project.activity.sinifSinavlari.epoxy.controller.SinifSinaviController
import com.bakiyem.surucu.project.base.activity.BaseActivity
import com.bakiyem.surucu.project.fragments.main.controller.CListener
import com.bakiyem.surucu.project.model.kurs.Response4Kurs
import com.bakiyem.surucu.project.model.sinifSinavi.Response4SinifSinavi
import com.bakiyem.surucu.project.utils.ext.loadImage
import com.bakiyem.surucu.project.utils.ext.semibold
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_sinif_sinavlari.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class SinifSinavlariActivity : BaseActivity(), CListener<Response4SinifSinavi> {

    lateinit var sinifSinavi: SinifSinaviVM

    lateinit var sinavId: String

    override fun getLayoutID(): Int = R.layout.activity_sinif_sinavlari

    override fun initVM() {
        sinifSinavi = ViewModelProviders.of(this).get(SinifSinaviVM::class.java)
    }

    override fun initChangeFont() {
        tv_hugeTitle.semibold()

        iv_rootImage.loadImage(Hawk.get<Response4Kurs>("kursBilgisi").logo)
    }

    override fun initReq() {
        sinifSinavi.getSinifSinavi()
    }

    override fun initVMListener() {
        prepareWithBaseVM(sinifSinavi)
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
                    DenemeSinaviActivity.start(this, "1", sinavId, false,"Sınıf Sınavı",  it)
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

    /*override fun onSelected(data: Response4SinifSinavi) {
        sinifSinavi.getSinifSinaviQuiz(data.sinavId!!)
    }*/

    override fun onSelected(
        data: Response4SinifSinavi,
        videoView: VideoView?,
        placeHolder: ImageView?,
        playIcon: ImageView?
    ) {
        sinavId = data.sinavId!!
        sinifSinavi.getSinifSinaviQuiz(sinavId)
    }
}