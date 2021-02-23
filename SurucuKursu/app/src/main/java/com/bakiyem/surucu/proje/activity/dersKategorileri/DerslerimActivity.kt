package com.bakiyem.surucu.proje.activity.dersKategorileri

import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.activity.dersKategorileri.epoxy.controller.DerslerimController
import com.bakiyem.surucu.proje.activity.dersListesi.DersListesiActivity
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.bakiyem.surucu.proje.fragments.main.controller.CListener
import com.bakiyem.surucu.proje.model.derslerim.Response4Derslerim
import kotlinx.android.synthetic.main.activity_derslerim.*

class DerslerimActivity : BaseActivity(), CListener<Response4Derslerim> {

    lateinit var derslerimVM: DerslerimVM

    override fun getLayoutID(): Int = R.layout.activity_derslerim

    override fun initVM() {
        derslerimVM = ViewModelProviders.of(this).get(DerslerimVM::class.java)
    }

    override fun initChangeFont() {
    }

    override fun initReq() {
        derslerimVM.getDersKategori()
    }

    override fun initVMListener() {
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
        val derslerimController = DerslerimController(this)
        derslerimController.derslerim = listOfDersKategori
        erv_derslerim.setController(derslerimController)

    }

    private fun goBack() {
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onSelected(response4Derslerim: Response4Derslerim) {
        DersListesiActivity.start(this, response4Derslerim)
    }
}