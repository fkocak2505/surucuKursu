package com.bakiyem.surucu.project.activity.odemeBilgilerim

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.VideoView
import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.project.R
import com.bakiyem.surucu.project.activity.odemeBilgilerim.epoxy.controller.OdemeBilgileriController
import com.bakiyem.surucu.project.activity.odemeYap.OdemeYap
import com.bakiyem.surucu.project.base.activity.BaseActivity
import com.bakiyem.surucu.project.fragments.main.controller.CListener
import com.bakiyem.surucu.project.model.kurs.Response4Kurs
import com.bakiyem.surucu.project.model.odemeBilgilerim.Response4BorcOzet
import com.bakiyem.surucu.project.model.odemeBilgilerim.Response4OdemeBilgileri
import com.bakiyem.surucu.project.utils.ext.loadImage
import com.bakiyem.surucu.project.utils.ext.regular
import com.bakiyem.surucu.project.utils.ext.semibold
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
        tv_toplamBorcInfo.semibold()
        tv_toplamBorc.regular()
        tv_tahsilEdilenInfo.semibold()
        tv_tahsilEdilen.regular()
        tv_kalanInfo.semibold()
        tv_kalan.regular()

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
        cv_ozetBorc.visibility = View.VISIBLE
        val controller = OdemeBilgileriController(applicationContext, this)
        erv_borcListesi.setController(controller)
        controller.borcListesi = borcListesi
        /*controller.borcOzet = borcOzet*/

        tv_toplamBorc.text = borcOzet.toplamBorc
        tv_tahsilEdilen.text = borcOzet.tahsilEdilen
        tv_kalan.text = borcOzet.kalanBorcu
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