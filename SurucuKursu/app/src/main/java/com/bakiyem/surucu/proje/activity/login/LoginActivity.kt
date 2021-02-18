package com.bakiyem.surucu.proje.activity.login

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.proje.MainActivity
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    lateinit var loginVM: LoginVM

    override fun getLayoutID(): Int = R.layout.activity_login

    override fun initVM() {
        loginVM = ViewModelProviders.of(this).get(LoginVM::class.java)
    }

    override fun initChangeFont() {

    }

    override fun initReq() {

    }

    override fun initVMListener() {
        loginVM.loginLD.observe(this, {
            it?.let {
                Hawk.put("loginResponse", it)
                startActivity(Intent(this, MainActivity::class.java))
            } ?: run {
                Toast.makeText(applicationContext, "Error Login", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateMethod() {
        handleClickListener()
    }

    private fun handleClickListener() {
        btn_login.setOnClickListener {
            val tckn = et_tckn.text.toString()

            loginVM.doLogin(tckn)
        }
    }
}