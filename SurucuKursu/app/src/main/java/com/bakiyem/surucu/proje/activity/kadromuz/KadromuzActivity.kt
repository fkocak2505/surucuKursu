package com.bakiyem.surucu.proje.activity.kadromuz

import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.activity.kadromuz.controller.KadromuzController
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.bakiyem.surucu.proje.model.kadromuz.Response4Kadromuz
import com.bakiyem.surucu.proje.utils.ext.semibold
import kotlinx.android.synthetic.main.activity_kadromuz.*

class KadromuzActivity : BaseActivity() {

    lateinit var kadromuzVM: KadromuzVM

    override fun getLayoutID(): Int = R.layout.activity_kadromuz

    override fun initVM() {
        kadromuzVM = ViewModelProviders.of(this).get(KadromuzVM::class.java)
    }

    override fun initChangeFont() {
        tv_hugeTitle.semibold()
    }

    override fun initReq() {
        kadromuzVM.getKadromuz()
    }

    override fun initVMListener() {
        kadromuzVM.kadromuzLD.observe(this, {
            it?.let {
                prepareKadromuz(it)
            } ?: run {
                Toast.makeText(applicationContext, "Error kadromuz", Toast.LENGTH_LONG).show()
                onBackPressed()
            }
        })
    }

    override fun onCreateMethod() {
        goBack()
    }

    private fun prepareKadromuz(listOfKadromuz: MutableList<Response4Kadromuz>) {
        val kadromuzController = KadromuzController()
        kadromuzController.kadromuz = listOfKadromuz
        erv_kadromuz.setController(kadromuzController)
    }

    private fun goBack() {
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }
}