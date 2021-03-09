package com.bakiyem.surucu.proje.activity.denemeSinavlarim

import android.graphics.Color
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_deneme_sinavlarim.*

class DenemeSinavlarimActivity: BaseActivity() {
    override fun getLayoutID(): Int = R.layout.activity_deneme_sinavlarim

    override fun initVM() {

    }

    override fun initChangeFont() {

    }

    override fun initReq() {

    }

    override fun initVMListener() {

    }

    override fun onCreateMethod() {
        handleClickListener()
    }

    private fun handleClickListener(){
        btn_start.setOnClickListener {
            if(!cb_denemeSinavi.isChecked){
                val pDialog = SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                pDialog.progressHelper.barColor = Color.parseColor("#A5DC86")
                pDialog.titleText = "Lütfen Klavuzu okudum ve anladım işaretleyin!"
                pDialog.confirmText = "Tamam"
                pDialog.setCancelable(false)
                pDialog.show()
            } else
                toast("Deneme")
        }
    }
}