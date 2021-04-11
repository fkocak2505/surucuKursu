package com.bakiyem.surucu.proje.activity.odemeYap

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.bakiyem.surucu.proje.utils.ext.regular
import com.bakiyem.surucu.proje.utils.ext.semibold
import kotlinx.android.synthetic.main.activity_odeme_yap.*
import java.text.NumberFormat
import java.util.*


class OdemeYap : BaseActivity() {

    lateinit var odemeYapVM: OdemeYapVM

    override fun getLayoutID(): Int = R.layout.activity_odeme_yap

    override fun initVM() {
        odemeYapVM = ViewModelProviders.of(this).get(OdemeYapVM::class.java)
    }

    override fun initChangeFont() {
        tv_hugeTitle.semibold()
        tv_tutarInfo.semibold()
        et_tutar.regular()
        tv_isimInfo.semibold()
        et_isim.regular()
        tv_kartNoInfo.semibold()
        et_kartNo.regular()
        tv_sktInfo.semibold()
        et_skt.regular()
        tv_cvcInfo.semibold()
        et_cvc.regular()
        tv_odemeYap.semibold()
    }

    override fun initReq() {

    }

    override fun initVMListener() {
        odemeYapVM.odemeYapLD.observe(this, {
            it?.let {
                toast(it.link!!)
            } ?: run {
                toast("Error odeme yap")
                //onBackPressed()
            }
        })
    }

    override fun onCreateMethod() {
        editTextFormatWatcher()

        goback()

        odemeYapClickListener()


    }

    private fun odemeYapClickListener() {
        btn_odemeYap.setOnClickListener {

            if (checkEmptyField()) {
                val kartNo = et_kartNo.text.toString().split(" ")
                val kartIsim = et_isim.text.toString()
                val ay = et_skt.text.toString().split("/")[0]
                val yil = et_skt.text.toString().split("/")[1]
                val cv2 = et_cvc.text.toString()
                val tutar = et_tutar.text.toString()

                val kartNo2 = kartNo[0] + kartNo[1] + kartNo[2] + kartNo[3]

                odemeYapVM.odemeYap(kartNo2, kartIsim, ay, yil, cv2, tutar)
            } else
                toast("Ödeme aşamasında boş alan bırakılamaz..!")

        }
    }

    private fun checkEmptyField(): Boolean {
        if (et_kartNo.text.toString() == "")
            return false

        if (et_isim.text.toString() == "")
            return false

        if (et_skt.text.toString().split("/")[0] == "")
            return false

        if (et_skt.text.toString().split("/")[1] == "")
            return false

        if (et_cvc.text.toString() == "")
            return false

        if (et_tutar.text.toString() == "")
            return false

        val kartNo = et_kartNo.text.toString().split(" ")

        if (kartNo.size < 4)
            return false

        if(kartNo[3].length < 4)
            return false

        return true

    }

    private fun goback() {
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun editTextFormatWatcher() {
        et_kartNo.addTextChangedListener(FourDigitCardFormatWatcher())

        et_skt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            @SuppressLint("SetTextI18n")
            override fun onTextChanged(p0: CharSequence?, start: Int, removed: Int, added: Int) {
                if (p0?.length == 2) {
                    if (start == 2 && removed == 1 && !p0.toString().contains("/")) {
                        et_skt.setText("" + p0.toString()[0]);
                        et_skt.setSelection(1);
                    } else {
                        et_skt.setText("$p0/");
                        et_skt.setSelection(3);
                    }
                }
            }
        })

    }
}