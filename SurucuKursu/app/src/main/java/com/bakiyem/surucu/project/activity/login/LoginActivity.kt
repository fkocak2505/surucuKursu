package com.bakiyem.surucu.project.activity.login

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.project.MainActivity
import com.bakiyem.surucu.project.R
import com.bakiyem.surucu.project.base.activity.BaseActivity
import com.bakiyem.surucu.project.model.kurs.Response4Kurs
import com.bakiyem.surucu.project.utils.TCKNValidate
import com.bakiyem.surucu.project.utils.ext.loadImage
import com.bakiyem.surucu.project.utils.ext.regular
import com.bakiyem.surucu.project.utils.ext.semibold
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class LoginActivity : BaseActivity() {

    lateinit var loginVM: LoginVM

    override fun getLayoutID(): Int = R.layout.activity_login

    override fun initVM() {
        loginVM = ViewModelProviders.of(this).get(LoginVM::class.java)
    }

    override fun initChangeFont() {
        et_tckn.regular()
        tv_login.semibold()
    }

    override fun initReq() {

    }

    override fun initVMListener() {
        prepareWithBaseVM(loginVM)
        loginVM.loginLD.observe(this, {
            it?.let {
                Hawk.put("loginResponse", it)
                startActivity(Intent(this, MainActivity::class.java))
            } ?: run {
                Toast.makeText(applicationContext, "Kursiyer Bulunamadı", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateMethod() {
        handleClickListener()

        iv_rootImage.loadImage(Hawk.get<Response4Kurs>("kursBilgisi").logo)
    }

    private fun handleClickListener() {
        btn_login.setOnClickListener {
            val tckn = et_tckn.text.toString()

            when (TCKNValidate.isTCKN(tckn)) {
                true -> {
                    loginVM.doLogin(tckn)
                }
                false -> {
                    Toast.makeText(
                        applicationContext,
                        "Geçerli bir TC Kimlik numarası giriniz..!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}