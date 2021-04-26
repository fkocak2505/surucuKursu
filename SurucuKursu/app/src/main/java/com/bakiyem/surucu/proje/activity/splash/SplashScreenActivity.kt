package com.bakiyem.surucu.proje.activity.splash

import android.content.Intent
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.proje.MainActivity
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.bakiyem.surucu.proje.fragments.contact.IletisimVM
import com.bakiyem.surucu.proje.fragments.main.viewModel.MainFragmentVM
import com.bakiyem.surucu.proje.utils.ext.loadImage
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.splash.*


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
                Hawk.put("kursBilgisi", it)
                iletisimVM.getIletisim()
                mainFragmentVM.getAnnouncements()

            } ?: run {
                Toast.makeText(applicationContext, "Error Kurs Token", Toast.LENGTH_SHORT).show()
            }
        })

        iletisimVM.iletisimLD.observe(this, {
            it?.let {

                iv_logo.loadImage(it.logo)

                Hawk.put("iletisim", it)
                Handler().postDelayed({
                    startActivity(Intent(this, MainActivity::class.java))
                    finishAffinity()
                }, 2000)
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
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("TOKEN..", "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }

                val token = task.result
                Hawk.put("fcmToken", token)
                Log.d("TOKEN..", token ?: "Token is null")
            })

    }
}