package com.bakiyem.surucu.proje.activity.dersListesi

import android.content.Context
import android.content.Intent
import android.widget.ImageView
import android.widget.VideoView
import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.activity.dersIcerik.DersIcerikActivity
import com.bakiyem.surucu.proje.activity.dersListesi.epoxy.controller.DersListesiController
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.bakiyem.surucu.proje.fragments.main.controller.CListener
import com.bakiyem.surucu.proje.model.dersListesi.Response4DersListesi
import com.bakiyem.surucu.proje.model.derslerim.Response4Derslerim
import com.bakiyem.surucu.proje.model.kurs.Response4Kurs
import com.bakiyem.surucu.proje.utils.ext.loadImage
import com.bakiyem.surucu.proje.utils.ext.semibold
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_ders_listesi.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class DersListesiActivity : BaseActivity(), CListener<Response4DersListesi> {

    lateinit var dersListesiVM: DersListesiVM

    override fun getLayoutID(): Int = R.layout.activity_ders_listesi

    override fun initVM() {
        dersListesiVM = ViewModelProviders.of(this).get(DersListesiVM::class.java)
    }

    override fun initChangeFont() {
        dersKategoriItem?.let {
            tv_hugeTitle.text = it.dersAdi
            tv_dersAdiDetail.text = "${it.dersAdi} Konuları"
        } ?: run {
            tv_hugeTitle.text = "Detaylar"
            tv_dersAdiDetail.text = "Detaylar"
        }

        tv_hugeTitle.semibold()
        tv_dersAdiDetail.semibold()

        iv_rootImage.loadImage(Hawk.get<Response4Kurs>("kursBilgisi").logo)
    }

    override fun initReq() {
        dersKategoriItem?.let {
            dersListesiVM.getDersListesi(it.id!!)
        } ?: run {
            toast("Error params kategoriID")
            onBackPressed()
        }

    }

    override fun initVMListener() {
        prepareWithBaseVM(dersListesiVM)
        dersListesiVM.dersListesiLD.observe(this, {
            it?.let {
                prepareDersListesi(it)
            } ?: run {
                toast("Error ders listesi items..")
                onBackPressed()
            }
        })
    }

    override fun onCreateMethod() {
        goBack()
    }

    private fun prepareDersListesi(listOfDersListesi: MutableList<Response4DersListesi>) {
        val dersListesiController = DersListesiController(this)
        dersListesiController.dersListesi = listOfDersListesi
        erv_dersIcerik.setController(dersListesiController)
    }

    private fun goBack() {
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }

    companion object {

        var dersKategoriItem: Response4Derslerim? = null

        fun start(
            context: Context?,
            dersKategoriItem: Response4Derslerim? = null
        ) {
            val starter = Intent(context, DersListesiActivity::class.java)
            this.dersKategoriItem = dersKategoriItem

            context!!.startActivity(starter)
        }

    }

    override fun onSelected(
        data: Response4DersListesi,
        videoView: VideoView?,
        placeHolder: ImageView?,
        playIcon: ImageView?
    ) {
        DersIcerikActivity.start(this, dersKategoriItem, data)
    }
}