package com.bakiyem.surucu.proje.activity.login

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.proje.MainActivity
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.bakiyem.surucu.proje.utils.TCKNValidate
import com.bakiyem.surucu.proje.utils.ext.regular
import com.bakiyem.surucu.proje.utils.ext.semibold
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_login.*

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