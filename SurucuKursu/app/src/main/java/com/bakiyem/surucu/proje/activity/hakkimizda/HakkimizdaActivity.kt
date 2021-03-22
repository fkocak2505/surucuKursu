package com.bakiyem.surucu.proje.activity.hakkimizda

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.bakiyem.surucu.proje.model.hakkimizda.Response4Hakkimizda
import com.bakiyem.surucu.proje.utils.ext.loadImage
import com.bakiyem.surucu.proje.utils.ext.regular
import com.bakiyem.surucu.proje.utils.ext.renderHtml
import com.bakiyem.surucu.proje.utils.ext.semibold
import kotlinx.android.synthetic.main.activity_hakkimizda.*

class HakkimizdaActivity : BaseActivity() {

    lateinit var hakkimizdaVM: HakkimizdaVM

    override fun getLayoutID(): Int = R.layout.activity_hakkimizda

    override fun initVM() {
        hakkimizdaVM = ViewModelProviders.of(this).get(HakkimizdaVM::class.java)
    }

    override fun initChangeFont() {
        tv_hugeTitle.semibold()
        tv_hakkimizdaTitle.semibold()
        tv_hakkimizdaDesc.regular()
    }

    override fun initReq() {
        hakkimizdaVM.getHakkimizda()
    }

    override fun initVMListener() {
        prepareWithBaseVM(hakkimizdaVM)
        hakkimizdaVM.hakkimizdaLD.observe(this, {
            it?.let {
                prepareHakkimizdaData(it)
            } ?: run {
                Toast.makeText(applicationContext, "Error Hakkimizda", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun prepareHakkimizdaData(response4Hakkimizda: Response4Hakkimizda) {
        response4Hakkimizda.resim?.let {
            iv_hakkimizdaImage.loadImage(it, R.drawable.hakkimizda_image)
        } ?: run {
            iv_hakkimizdaImage.setImageResource(R.drawable.hakkimizda_image)
        }

        response4Hakkimizda.baslik?.let {
            tv_hakkimizdaTitle.visibility = View.VISIBLE

            tv_hakkimizdaTitle.text = it

        } ?: run {
            tv_hakkimizdaTitle.visibility = View.GONE
        }

        response4Hakkimizda.detay?.let {
            tv_hakkimizdaDesc.visibility = View.VISIBLE

            tv_hakkimizdaDesc renderHtml it

        } ?: run {
            tv_hakkimizdaDesc.visibility = View.GONE
        }

    }

    override fun onCreateMethod() {
        goBackClickListener()
    }

    private fun goBackClickListener() {
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }
}