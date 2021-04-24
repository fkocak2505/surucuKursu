package com.bakiyem.surucu.proje.activity.dersKategorileri

import android.widget.ImageView
import android.widget.VideoView
import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.activity.dersKategorileri.epoxy.controller.DerslerimController
import com.bakiyem.surucu.proje.activity.dersListesi.DersListesiActivity
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.bakiyem.surucu.proje.fragments.main.controller.CListener
import com.bakiyem.surucu.proje.model.derslerim.Response4Derslerim
import com.bakiyem.surucu.proje.model.kurs.Response4Kurs
import com.bakiyem.surucu.proje.utils.ext.loadImage
import com.bakiyem.surucu.proje.utils.ext.semibold
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
        val derslerimController = DerslerimController(applicationContext,this)
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
        DersListesiActivity.start(this, data)
    }
}