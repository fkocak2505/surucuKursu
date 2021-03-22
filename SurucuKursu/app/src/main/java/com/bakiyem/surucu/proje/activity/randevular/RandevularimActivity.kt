package com.bakiyem.surucu.proje.activity.randevular

import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.activity.randevular.epoxy.controller.RandevularimController
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.bakiyem.surucu.proje.model.randevularim.Response4Randevularim
import com.bakiyem.surucu.proje.utils.ext.semibold
import kotlinx.android.synthetic.main.activity_randevularim.*

class RandevularimActivity: BaseActivity() {

    lateinit var randevularimVM: RandevularimVM

    override fun getLayoutID(): Int = R.layout.activity_randevularim

    override fun initVM() {
        randevularimVM = ViewModelProviders.of(this).get(RandevularimVM::class.java)
    }

    override fun initChangeFont() {
        tv_hugeTitle.semibold()
    }

    override fun initReq() {
        randevularimVM.getRandevularim()
    }

    override fun initVMListener() {
        randevularimVM.randevularimLD.observe(this, {
            it?.let {
                prepareRandevularimERV(it)
            }?: run{
                toast("Error fetch randevularim")
                onBackPressed()
            }
        })
    }

    override fun onCreateMethod() {
        goBack()
    }

    private fun prepareRandevularimERV(listOfRandevularim: MutableList<Response4Randevularim>){
        val controller = RandevularimController()
        erv_randevularim.setController(controller)
        controller.randevularimList = listOfRandevularim

    }

    private fun goBack(){
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }
}