package com.bakiyem.surucu.proje.activity.randevular

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.activity.randevuEkle.RandevuEkleActivity
import com.bakiyem.surucu.proje.activity.randevular.epoxy.controller.RandevularimController
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.bakiyem.surucu.proje.fragments.main.controller.CListener
import com.bakiyem.surucu.proje.model.randevularim.Response4Randevularim
import com.bakiyem.surucu.proje.utils.ext.semibold
import kotlinx.android.synthetic.main.activity_randevularim.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class RandevularimActivity : BaseActivity(), CListener<Response4Randevularim> {

    lateinit var randevularimVM: RandevularimVM

    private var isComingRandevuEkle = ""

    override fun getLayoutID(): Int = R.layout.activity_randevularim

    override fun initVM() {
        randevularimVM = ViewModelProviders.of(this).get(RandevularimVM::class.java)
    }

    override fun initChangeFont() {
        tv_hugeTitle.semibold()
        tv_noRandevuTitle.semibold()
        tv_start.semibold()
    }

    override fun initReq() {
        randevularimVM.getRandevularim()
    }

    override fun initVMListener() {
        prepareWithBaseVM(randevularimVM)
        randevularimVM.randevularimLD.observe(this, {
            it?.let {
                prepareRandevularimERV(it)
            } ?: run {
                toast("Error fetch randevularim")
                onBackPressed()
            }
        })

        randevularimVM.infoRandevuIptalLD.observe(this, {
            it?.let {
                toast(it.detay!!)
            } ?: run {
                toast("Error iptal randevu")
            }

            onBackPressed()
        })
    }

    override fun onCreateMethod() {
        goBack()

        btn_goRandevu.setOnClickListener {
            startActivity(Intent(this@RandevularimActivity, RandevuEkleActivity::class.java))
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onRefreshActivity(isRefresh: String) {
        isComingRandevuEkle = isRefresh
        randevularimVM.getRandevularim()
    }

    override fun onStart() {
        super.onStart()
        if (isComingRandevuEkle == "")
            EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    private fun prepareRandevularimERV(listOfRandevularim: MutableList<Response4Randevularim>) {
        if (listOfRandevularim.size == 0) {
            erv_randevularim.visibility = View.GONE
            tv_noRandevuTitle.visibility = View.VISIBLE
        } else {
            tv_noRandevuTitle.visibility = View.GONE
            erv_randevularim.visibility = View.VISIBLE

            val controller = RandevularimController(this)
            erv_randevularim.setController(controller)
            controller.randevularimList = listOfRandevularim
        }
    }

    private fun goBack() {
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onSelected(data: Response4Randevularim) {
        randevularimVM.randevuIptal(data.Id!!)
    }
}