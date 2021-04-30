package com.bakiyem.surucu.proje.activity.hakkimizda

import android.os.Build
import android.text.Html
import android.text.Spannable
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.proje.GlideImageGetter
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.bakiyem.surucu.proje.model.hakkimizda.Response4Hakkimizda
import com.bakiyem.surucu.proje.model.kurs.Response4Kurs
import com.bakiyem.surucu.proje.utils.ext.loadImage
import com.bakiyem.surucu.proje.utils.ext.regular
import com.bakiyem.surucu.proje.utils.ext.semibold
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_hakkimizda.*
import kotlinx.android.synthetic.main.activity_hakkimizda.iv_back
import kotlinx.android.synthetic.main.activity_hakkimizda.tv_hugeTitle
import kotlinx.android.synthetic.main.toolbar_layout.*

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

        iv_rootImage.loadImage(Hawk.get<Response4Kurs>("kursBilgisi").logo)
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

        response4Hakkimizda.icon?.let {
            cardView.visibility = View.VISIBLE
            cardView.background = ContextCompat.getDrawable(context, R.drawable.bg_circle_answer);
            iv_hakkimizdaIcon.loadImage(it)
        } ?: run {
            cardView.visibility = View.GONE
        }

        response4Hakkimizda.baslik?.let {
            tv_hakkimizdaTitle.visibility = View.VISIBLE

            tv_hakkimizdaTitle.text = it

        } ?: run {
            tv_hakkimizdaTitle.visibility = View.GONE
        }

        response4Hakkimizda.detay?.let {
            tv_hakkimizdaDesc.visibility = View.VISIBLE

            val imageGetter = GlideImageGetter(tv_hakkimizdaDesc, true)
            val html: Spannable = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(
                    it,
                    Html.FROM_HTML_MODE_LEGACY,
                    imageGetter,
                    null
                ) as Spannable
            } else {
                Html.fromHtml(it, imageGetter, null) as Spannable
            }
            tv_hakkimizdaDesc.text = html
            //tv_hakkimizdaDesc renderHtml it

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