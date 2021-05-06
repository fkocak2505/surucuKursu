package com.bakiyem.surucu.project.activity.araclarimiz.view

import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.project.R
import com.bakiyem.surucu.project.activity.araclarimiz.controller.AraclarimizController
import com.bakiyem.surucu.project.base.activity.BaseActivity
import com.bakiyem.surucu.project.model.araclar.Response4Araclar
import com.bakiyem.surucu.project.model.kurs.Response4Kurs
import com.bakiyem.surucu.project.utils.ext.loadImage
import com.bakiyem.surucu.project.utils.ext.semibold
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_araclarimiz.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class AraclarimizActivity : BaseActivity() {

    lateinit var araclarVM: AraclarVM

    override fun getLayoutID(): Int = R.layout.activity_araclarimiz

    override fun initVM() {
        araclarVM = ViewModelProviders.of(this).get(AraclarVM::class.java)
    }

    override fun initChangeFont() {
        tv_hugeTitle.semibold()

        iv_rootImage.loadImage(Hawk.get<Response4Kurs>("kursBilgisi").logo)
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