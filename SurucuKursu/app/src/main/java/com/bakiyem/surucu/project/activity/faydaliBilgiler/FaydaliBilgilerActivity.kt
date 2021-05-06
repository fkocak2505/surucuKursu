package com.bakiyem.surucu.project.activity.faydaliBilgiler

import android.widget.ImageView
import android.widget.VideoView
import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.project.R
import com.bakiyem.surucu.project.activity.faydaliBilgiler.epoxy.controller.FaydaliBilgilerController
import com.bakiyem.surucu.project.activity.faydaliBilgilerDetay.FaydaliBilgilerDetayActivity
import com.bakiyem.surucu.project.base.activity.BaseActivity
import com.bakiyem.surucu.project.fragments.main.controller.CListener
import com.bakiyem.surucu.project.model.faydaliBilgiler.Response4FaydaliBilgiler
import com.bakiyem.surucu.project.model.kurs.Response4Kurs
import com.bakiyem.surucu.project.utils.ext.loadImage
import com.bakiyem.surucu.project.utils.ext.semibold
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_faydali_bilgileri.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class FaydaliBilgilerActivity : BaseActivity(), CListener<Response4FaydaliBilgiler> {

    lateinit var faydaliBilgilerVM: FaydaliBilgilerVM

    override fun getLayoutID(): Int = R.layout.activity_faydali_bilgileri

    override fun initVM() {
        faydaliBilgilerVM = ViewModelProviders.of(this).get(FaydaliBilgilerVM::class.java)
    }

    override fun initChangeFont() {
        tv_hugeTitle.semibold()

        iv_rootImage.loadImage(Hawk.get<Response4Kurs>("kursBilgisi").logo)
    }

    override fun initReq() {
        faydaliBilgilerVM.getFaydaliBilgiler()
    }

    override fun initVMListener() {
        prepareWithBaseVM(faydaliBilgilerVM)
        faydaliBilgilerVM.faydaliBilgilerLD.observe(this, {
            it?.let {
                prepareFaydaliBilgilerERV(it)
            } ?: run {
                toast("Error Faydali Bilgiler")
                onBackPressed()
            }
        })
    }

    override fun onCreateMethod() {
        goBack()
    }

    private fun prepareFaydaliBilgilerERV(listOfFaydaliBilgiler: MutableList<Response4FaydaliBilgiler>) {
        val controller = FaydaliBilgilerController(this)
        erv_faydaliBilgiler.setController(controller)
        controller.faydaliBilgilerList = listOfFaydaliBilgiler
    }

    private fun goBack() {
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }


    override fun onSelected(
        data: Response4FaydaliBilgiler,
        videoView: VideoView?,
        placeHolder: ImageView?,
        playIcon: ImageView?
    ) {
        FaydaliBilgilerDetayActivity.start(this, data)
    }
}