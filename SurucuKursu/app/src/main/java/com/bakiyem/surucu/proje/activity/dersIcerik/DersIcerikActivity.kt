package com.bakiyem.surucu.proje.activity.dersIcerik

import android.content.Context
import android.content.Intent
import android.text.Html
import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.activity.dersListesi.DersListesiActivity
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.bakiyem.surucu.proje.model.dersIcerik.Response4DersIcerik
import com.bakiyem.surucu.proje.model.dersListesi.Response4DersListesi
import com.bakiyem.surucu.proje.model.derslerim.Response4Derslerim
import com.bakiyem.surucu.proje.utils.ext.renderHtml
import kotlinx.android.synthetic.main.activity_ders_icerik.*

class DersIcerikActivity : BaseActivity() {

    lateinit var dersIcerikVM: DersIcerikVM

    override fun getLayoutID(): Int = R.layout.activity_ders_icerik

    override fun initVM() {
        dersIcerikVM = ViewModelProviders.of(this).get(DersIcerikVM::class.java)
    }

    override fun initChangeFont() {
        dersKategoriItem?.let {
            tv_hugeTitle.text = it.dersAdi
            tv_dersAdiDetail.text = it.dersAdi
        } ?: run {
            tv_hugeTitle.text = "Detaylar"
            tv_dersAdiDetail.text = "Detaylar"
        }
    }

    override fun initReq() {
        dersKategoriItem?.let {
            dersListItem?.let {

                dersIcerikVM.getDersIcerik(it.keyi!!)

            } ?: run {
                toast("Error params dersListItem id")
                onBackPressed()
            }
        } ?: run {
            toast("Error params kategoriItem id")
            onBackPressed()
        }
    }

    override fun initVMListener() {
        dersIcerikVM.dersIcerikLD.observe(this, {
            it?.let {
                prepareDersIcerikData(it)
            } ?: run {
                toast("Error ders icerik")
                onBackPressed()
            }
        })
    }

    override fun onCreateMethod() {
        goback()
    }

    private fun prepareDersIcerikData(listOfDersIcerik: MutableList<Response4DersIcerik>) {
        if (listOfDersIcerik.isNotEmpty()) {
            val htmlData = Html.fromHtml(listOfDersIcerik[0].detay!!).toString()
            tv_dersIcerik renderHtml htmlData
        }

    }

    private fun goback() {
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }

    companion object {

        var dersKategoriItem: Response4Derslerim? = null
        var dersListItem: Response4DersListesi? = null

        fun start(
            context: Context?,
            dersKategoriItem: Response4Derslerim? = null,
            dersListItem: Response4DersListesi? = null
        ) {
            val starter = Intent(context, DersIcerikActivity::class.java)
            this.dersKategoriItem = dersKategoriItem
            this.dersListItem = dersListItem

            context!!.startActivity(starter)
        }

    }
}