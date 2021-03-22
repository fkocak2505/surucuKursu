package com.bakiyem.surucu.proje.activity.araclarimiz.view

import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.activity.araclarimiz.controller.AraclarimizController
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.bakiyem.surucu.proje.model.araclar.Response4Araclar
import com.bakiyem.surucu.proje.utils.ext.semibold
import kotlinx.android.synthetic.main.activity_araclarimiz.*

class AraclarimizActivity : BaseActivity() {

    lateinit var araclarVM: AraclarVM

    override fun getLayoutID(): Int = R.layout.activity_araclarimiz

    override fun initVM() {
        araclarVM = ViewModelProviders.of(this).get(AraclarVM::class.java)
    }

    override fun initChangeFont() {
        tv_hugeTitle.semibold()
    }

    override fun initReq() {
        araclarVM.getAraclar()
    }

    override fun initVMListener() {
        prepareWithBaseVM(araclarVM)
        araclarVM.araclarLD.observe(this, {
            it?.let {
                prepareAraclarList(it)
            } ?: run {
                Toast.makeText(applicationContext, "Error Araclar", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateMethod() {
        goBackClickListener()
    }

    private fun prepareAraclarList(listOfAraclar: MutableList<Response4Araclar>) {
        val araclarController = AraclarimizController()
        araclarController.araclar = listOfAraclar
        erv_araclarimiz.setController(araclarController)
    }

    private fun goBackClickListener() {
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }
}