package com.bakiyem.surucu.proje.activity.denemeSinavlarimKlavuz

import android.content.Intent
import android.graphics.Color
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.activity.denemeSinavi.DenemeSinaviActivity
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_deneme_sinavlarim_klavuz.*

class DenemeSinavlarimKlavuzActivity : BaseActivity() {
    override fun getLayoutID(): Int = R.layout.activity_deneme_sinavlarim_klavuz

    override fun initVM() {

    }

    override fun initChangeFont() {

    }

    override fun initReq() {

    }

    override fun initVMListener() {

    }

    override fun onCreateMethod() {
        goBack()

        handleClickListener()
    }

    private fun goBack(){
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun handleClickListener() {
        btn_start.setOnClickListener {
            if (!cb_denemeSinavi.isChecked) {
                val pDialog = SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                pDialog.progressHelper.barColor = Color.parseColor("#A5DC86")
                pDialog.titleText = "Lütfen Klavuzu okudum ve anladım işaretleyin!"
                pDialog.confirmText = "Tamam"
                pDialog.setCancelable(false)
                pDialog.show()
            } else
                startActivity(Intent(this, DenemeSinaviActivity::class.java))
        }
    }
}