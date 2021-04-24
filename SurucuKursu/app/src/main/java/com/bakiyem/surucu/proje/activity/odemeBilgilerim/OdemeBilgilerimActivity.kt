package com.bakiyem.surucu.proje.activity.odemeBilgilerim

import android.content.Intent
import android.widget.ImageView
import android.widget.VideoView
import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.activity.odemeBilgilerim.epoxy.controller.OdemeBilgileriController
import com.bakiyem.surucu.proje.activity.odemeYap.OdemeYap
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.bakiyem.surucu.proje.fragments.main.controller.CListener
import com.bakiyem.surucu.proje.model.kurs.Response4Kurs
import com.bakiyem.surucu.proje.model.odemeBilgilerim.Response4BorcOzet
import com.bakiyem.surucu.proje.model.odemeBilgilerim.Response4OdemeBilgileri
import com.bakiyem.surucu.proje.utils.ext.loadImage
import com.bakiyem.surucu.proje.utils.ext.semibold
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_odeme_bilgilerim.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class OdemeBilgilerimActivity : BaseActivity(), CListener<Response4OdemeBilgileri> {

    lateinit var odemeBilgilerimVM: OdemeBilgilerimVM

    var borcListesi: MutableList<Response4OdemeBilgileri> = mutableListOf()

    lateinit var borcOzet: Response4BorcOzet

    override fun getLayoutID(): Int = R.layout.activity_odeme_bilgilerim

    override fun initVM() {
        odemeBilgilerimVM = ViewModelProviders.of(this).get(OdemeBilgilerimVM::class.java)
    }

    override fun initChangeFont() {
        tv_hugeTitle.semibold()

        iv_rootImage.loadImage(Hawk.get<Response4Kurs>("kursBilgisi").logo)
    }

    override fun initReq() {
        odemeBilgilerimVM.getBorcListesi()
    }

    override fun initVMListener() {
        prepareWithBaseVM(odemeBilgilerimVM)
        odemeBilgilerimVM.borcListesiLD.observe(this, {
            it?.let {
                borcListesi = it
                odemeBilgilerimVM.getBorcOzet()
            } ?: run {
                toast("Error fetch borc listesi")
            }
        })

        odemeBilgilerimVM.borcOzetLD.observe(this, {
            it?.let {
                borcOzet = it
                prepareBorcListesiData()
            } ?: run {
                toast("Error fetch borc ozet")
            }
        })
    }

    override fun onCreateMethod() {
        goBack()
    }

    private fun prepareBorcListesiData() {
        val controller = OdemeBilgileriController(applicationContext, this)
        erv_borcListesi.setController(controller)
        controller.borcListesi = borcListesi
        controller.borcOzet = borcOzet
    }

    private fun goBack() {
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }


    override fun onSelected(
        data: Response4OdemeBilgileri,
        videoView: VideoView?,
        placeHolder: ImageView?,
        playIcon: ImageView?
    ) {
        if (data.durum != "Ödendi")
            startActivity(Intent(this@OdemeBilgilerimActivity, OdemeYap::class.java))
    }
}