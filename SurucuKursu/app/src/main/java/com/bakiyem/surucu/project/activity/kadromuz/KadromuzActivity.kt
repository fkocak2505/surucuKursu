package com.bakiyem.surucu.project.activity.kadromuz

import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.project.R
import com.bakiyem.surucu.project.activity.kadromuz.controller.KadromuzController
import com.bakiyem.surucu.project.base.activity.BaseActivity
import com.bakiyem.surucu.project.model.kadromuz.Response4Kadromuz
import com.bakiyem.surucu.project.model.kurs.Response4Kurs
import com.bakiyem.surucu.project.utils.ext.loadImage
import com.bakiyem.surucu.project.utils.ext.semibold
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_kadromuz.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class KadromuzActivity : BaseActivity() {

    lateinit var kadromuzVM: KadromuzVM

    override fun getLayoutID(): Int = R.layout.activity_kadromuz

    override fun initVM() {
        kadromuzVM = ViewModelProviders.of(this).get(KadromuzVM::class.java)
    }

    override fun initChangeFont() {
        tv_hugeTitle.semibold()

        iv_rootImage.loadImage(Hawk.get<Response4Kurs>("kursBilgisi").logo)
    }

    override fun initReq() {
        kadromuzVM.getKadromuz()
    }

    override fun initVMListener() {
        prepareWithBaseVM(kadromuzVM)
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