package com.bakiyem.surucu.project.activity.denemeSinavlarimKlavuz

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import com.bakiyem.surucu.project.R
import com.bakiyem.surucu.project.activity.denemeSinavi.DenemeSinaviActivity
import com.bakiyem.surucu.project.base.activity.BaseActivity
import com.bakiyem.surucu.project.model.kurs.Response4Kurs
import com.bakiyem.surucu.project.utils.ext.loadImage
import com.bakiyem.surucu.project.utils.ext.regular
import com.bakiyem.surucu.project.utils.ext.semibold
import com.orhanobut.hawk.Hawk
import dev.shreyaspatil.MaterialDialog.MaterialDialog
import kotlinx.android.synthetic.main.activity_deneme_sinavlarim_klavuz.*
import kotlinx.android.synthetic.main.activity_deneme_sinavlarim_klavuz.iv_back
import kotlinx.android.synthetic.main.activity_deneme_sinavlarim_klavuz.tv_hugeTitle
import kotlinx.android.synthetic.main.toolbar_layout.*


class DenemeSinavlarimKlavuzActivity : BaseActivity() {
    override fun getLayoutID(): Int = R.layout.activity_deneme_sinavlarim_klavuz

    override fun initVM() {

    }

    override fun initChangeFont() {
        tv_hugeTitle.semibold()
        tv_klavuzTitleInfo.semibold()
        tv_one.regular()
        tv_two.regular()
        tv_three.regular()
        tv_four.regular()
        tv_five.regular()
        tv_six.regular()
        tv_startExam.semibold()

        val font = Typeface.createFromAsset(assets, "fonts/montserrat_regular.ttf")
        cb_denemeSinavi.typeface = font

        val state = arrayOf(
            intArrayOf(android.R.attr.state_checked),
            intArrayOf(android.R.attr.state_enabled),
            intArrayOf(android.R.attr.state_pressed),
            intArrayOf(android.R.attr.state_focused),
            intArrayOf(android.R.attr.state_pressed)
        )

        val color = intArrayOf(
            Color.parseColor("#${Hawk.get<Response4Kurs>("kursBilgisi").renk}"),
            Color.GRAY,
            Color.GRAY,
            Color.GRAY,
            Color.GRAY
        )

        val colorStateList1 = ColorStateList(state, color)

        cb_denemeSinavi.buttonTintList = colorStateList1

        iv_rootImage.loadImage(Hawk.get<Response4Kurs>("kursBilgisi").logo)
    }

    override fun initReq() {

    }

    override fun initVMListener() {

    }

    override fun onCreateMethod() {
        goBack()

        handleClickListener()
    }

    private fun goBack() {
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun handleClickListener() {
        btn_start.setOnClickListener {
            if (!cb_denemeSinavi.isChecked) {
                val mDialog = MaterialDialog.Builder(this)
                    .setTitle("Emin misin?")
                    .setMessage("Lütfen Klavuzu okudum ve anladım işaretleyin!")
                    .setCancelable(false)
                    .setPositiveButton(
                        "Tamam", R.drawable.ic_green_tick
                    ) { dialogInterface, which ->
                        dialogInterface.dismiss()
                    }
                    .build()
                mDialog.show()
                /*val pDialog = SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                pDialog.progressHelper.barColor = Color.parseColor("#${Hawk.get<Response4Kurs>("kursBilgisi").renk}")
                pDialog.titleText = "Lütfen Klavuzu okudum ve anladım işaretleyin!"
                pDialog.confirmText = "Tamam"
                pDialog.setCancelable(false)
                pDialog.show()*/
            } else
            //startActivity(Intent(this, DenemeSinaviActivity::class.java))
                DenemeSinaviActivity.start(this, "2", "", false, "Deneme Sınavı", mutableListOf())
        }
    }
}