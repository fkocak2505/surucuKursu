package com.bakiyem.surucu.project.activity.dersKategorileri

import android.widget.ImageView
import android.widget.VideoView
import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.project.R
import com.bakiyem.surucu.project.activity.dersKategorileri.epoxy.controller.DerslerimController
import com.bakiyem.surucu.project.activity.dersListesi.DersListesiActivity
import com.bakiyem.surucu.project.base.activity.BaseActivity
import com.bakiyem.surucu.project.fragments.main.controller.CListener
import com.bakiyem.surucu.project.model.derslerim.Response4Derslerim
import com.bakiyem.surucu.project.model.kurs.Response4Kurs
import com.bakiyem.surucu.project.utils.ext.loadImage
import com.bakiyem.surucu.project.utils.ext.semibold
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_derslerim.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class DerslerimActivity : BaseActivity(), CListener<Response4Derslerim> {

    lateinit var derslerimVM: DerslerimVM

    override fun getLayoutID(): Int = R.layout.activity_derslerim

    override fun initVM() {
        derslerimVM = ViewModelProviders.of(this).get(DerslerimVM::class.java)
    }

    override fun initChangeFont() {
        tv_hugeTitle.semibold()

        iv_rootImage.loadImage(Hawk.get<Response4Kurs>("kursBilgisi").logo)
    }

    override fun initReq() {
        derslerimVM.getDersKategori()
    }

    override fun initVMListener() {
        prepareWithBaseVM(derslerimVM)
        derslerimVM.dersKategoriLD.observe(this, {
            it?.let {
                prepareDersKategoriData(it)
            } ?: run {
                onBackPressed()
            }
        })
    }

    override fun onCreateMethod() {
        goBack()
    }

    private fun prepareDersKategoriData(listOfDersKategori: MutableList<Response4Derslerim>) {
        val derslerimController = DerslerimController(applicationContext, this)
        derslerimController.derslerim = listOfDersKategori
        erv_derslerim.setController(derslerimController)

    }

    private fun goBack() {
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onSelected(
        data: Response4Derslerim,
        videoView: VideoView?,
        placeHolder: ImageView?,
        playIcon: ImageView?
    ) {
        when (data.id) {
            "1" -> {
                DersListesiActivity.start(this, data, "#6AACA9")
            }
            "2" -> {
                DersListesiActivity.start(this, data, "#679A6F")
            }
            "3" -> {
                DersListesiActivity.start(this, data, "#C1B481")
            }
            "4" -> {
                DersListesiActivity.start(this, data, "#6B6086")
            }
        }
    }
}