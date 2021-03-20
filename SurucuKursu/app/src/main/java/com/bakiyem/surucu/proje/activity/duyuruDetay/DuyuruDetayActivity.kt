package com.bakiyem.surucu.proje.activity.duyuruDetay

import android.content.Context
import android.content.Intent
import android.os.Build
import android.text.Html
import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.bakiyem.surucu.proje.model.duyuruDetay.Response4DuyuruDetay
import com.bakiyem.surucu.proje.utils.ext.regular
import com.bakiyem.surucu.proje.utils.ext.renderHtml
import com.bakiyem.surucu.proje.utils.ext.semibold
import kotlinx.android.synthetic.main.activity_duyuru_detay.*

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
    }

    override fun initReq() {
        duyuruDetayVM.getDuyuruDetay(duyuruKey!!)
    }

    override fun initVMListener() {
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