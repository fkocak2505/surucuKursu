package com.bakiyem.surucu.proje.activity.faydaliBilgilerDetay

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.activity.denemeSinavi.DenemeSinaviActivity
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.bakiyem.surucu.proje.model.denemeSinavi.Response4DenemeSinavi
import com.bakiyem.surucu.proje.model.faydaliBilgiler.Response4FaydaliBilgiler
import com.bakiyem.surucu.proje.model.kurs.Response4Kurs
import com.bakiyem.surucu.proje.utils.ext.loadImage
import com.bakiyem.surucu.proje.utils.ext.regular
import com.bakiyem.surucu.proje.utils.ext.renderHtml
import com.bakiyem.surucu.proje.utils.ext.semibold
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_faydali_bilgiler_detay.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class FaydaliBilgilerDetayActivity: BaseActivity() {
    override fun getLayoutID(): Int = R.layout.activity_faydali_bilgiler_detay

    override fun initVM() {

    }

    override fun initChangeFont() {
        tv_hugeTitle.semibold()
        tv_title.semibold()
        tv_Desc.regular()

        iv_rootImage.loadImage(Hawk.get<Response4Kurs>("kursBilgisi").logo)
    }

    override fun initReq() {

    }

    override fun initVMListener() {

    }

    override fun onCreateMethod() {
        goBack()

        setData()
    }

    private fun setData(){
        tv_title.text = faydaliBilgilerItem?.baslik
        tv_Desc renderHtml faydaliBilgilerItem?.detay!!
    }

    private fun goBack(){
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }

    companion object{

        var faydaliBilgilerItem: Response4FaydaliBilgiler? = null

        fun start(
            activity: AppCompatActivity?,
            faydaliBilgilerItem: Response4FaydaliBilgiler,
        ) {
            val starter = Intent(activity, FaydaliBilgilerDetayActivity::class.java)
            this.faydaliBilgilerItem = faydaliBilgilerItem

            activity!!.startActivity(starter)
        }

    }
}