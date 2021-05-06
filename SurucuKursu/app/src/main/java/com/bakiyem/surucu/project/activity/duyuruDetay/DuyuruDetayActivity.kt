package com.bakiyem.surucu.project.activity.duyuruDetay

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.project.R
import com.bakiyem.surucu.project.base.activity.BaseActivity
import com.bakiyem.surucu.project.model.duyuruDetay.Response4DuyuruDetay
import com.bakiyem.surucu.project.model.kurs.Response4Kurs
import com.bakiyem.surucu.project.utils.ext.loadImage
import com.bakiyem.surucu.project.utils.ext.regular
import com.bakiyem.surucu.project.utils.ext.renderHtml
import com.bakiyem.surucu.project.utils.ext.semibold
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_duyuru_detay.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class DuyuruDetayActivity : BaseActivity() {

    lateinit var duyuruDetayVM: DuyuruDetayVM

    override fun getLayoutID(): Int = R.layout.activity_duyuru_detay

    override fun initVM() {
        duyuruDetayVM = ViewModelProviders.of(this).get(DuyuruDetayVM::class.java)
    }

    override fun initChangeFont() {
        tv_hugeTitle.semibold()
        tv_date.regular()
        tv_duyuruDetay.regular()

        iv_rootImage.loadImage(Hawk.get<Response4Kurs>("kursBilgisi").logo)
        tv_date.setTextColor(Color.parseColor("#${Hawk.get<Response4Kurs>("kursBilgisi").renk}"))
    }

    override fun initReq() {
        duyuruDetayVM.getDuyuruDetay(duyuruKey!!)
    }

    override fun initVMListener() {
        prepareWithBaseVM(duyuruDetayVM)
        duyuruDetayVM.duyuruDetayLD.observe(this, {
            it?.let {
                prepareDuyuruDetayData(it[0])
            } ?: run {

            }
        })
    }

    override fun onCreateMethod() {
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun prepareDuyuruDetayData(response4DuyuruDetay: Response4DuyuruDetay){
        tv_date.text = response4DuyuruDetay.tarih

        tv_duyuruDetay renderHtml response4DuyuruDetay.detay!!
    }

    companion object {
        var duyuruKey: String? = null

        fun start(
            context: Context?,
            duyuruKey: String? = null
        ) {
            val starter = Intent(context, DuyuruDetayActivity::class.java)
            this.duyuruKey = duyuruKey

            context!!.startActivity(starter)
        }
    }
}