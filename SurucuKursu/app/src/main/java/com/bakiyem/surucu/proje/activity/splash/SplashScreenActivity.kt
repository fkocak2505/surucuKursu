package com.bakiyem.surucu.proje.activity.splash

import android.content.Intent
import android.os.Handler
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.proje.MainActivity
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.bakiyem.surucu.proje.fragments.contact.IletisimVM
import com.bakiyem.surucu.proje.fragments.main.viewModel.MainFragmentVM
import com.orhanobut.hawk.Hawk

class SplashScreenActivity : BaseActivity() {

    lateinit var mainFragmentVM: MainFragmentVM

    lateinit var iletisimVM: IletisimVM

    override fun getLayoutID(): Int = R.layout.splash

    override fun initVM() {
        mainFragmentVM = ViewModelProviders.of(this).get(MainFragmentVM::class.java)
        iletisimVM = ViewModelProviders.of(this).get(IletisimVM::class.java)
    }

    override fun initChangeFont() {

    }

    override fun initReq() {
        mainFragmentVM.getKurs()
    }

    override fun initVMListener() {
        prepareWithBaseVM(mainFragmentVM)
        prepareWithBaseVM(iletisimVM)

        mainFragmentVM.kursLD.observe(this, {
            it?.let {
                Hawk.put("token", it.key)
                iletisimVM.getIletisim()
                mainFragmentVM.getAnnouncements()

            } ?: run {
                Toast.makeText(applicationContext, "Error Kurs Token", Toast.LENGTH_SHORT).show()
            }
        })

        iletisimVM.iletisimLD.observe(this, {
            it?.let {
                Hawk.put("iletisim", it)
                Handler().postDelayed({
                    startActivity(Intent(this, MainActivity::class.java))
                }, 1000)
            } ?: run {
                Toast.makeText(
                    applicationContext,
                    "error fetch iletisim",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun onCreateMethod() {

    }
}