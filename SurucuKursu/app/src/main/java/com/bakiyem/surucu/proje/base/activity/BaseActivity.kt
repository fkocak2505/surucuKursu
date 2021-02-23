package com.bakiyem.surucu.proje.base.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.base.vm.BaseVM
import com.kaopiz.kprogresshud.KProgressHUD
import com.orhanobut.hawk.Hawk

abstract class BaseActivity: AppCompatActivity() {

    val context: Context
        get() = this

    val activity: Activity
        get() = this

    val compatActivity: AppCompatActivity
        get() = this

    private var hud_loading: KProgressHUD? = null

    @SuppressLint("SourceLockedOrientationActivity")
    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutID())

        Hawk.init(applicationContext).build()

        initVM()
        initChangeFont()
        initReq()
        initVMListener()
        onCreateMethod()

    }

    abstract fun getLayoutID(): Int

    abstract fun initVM()

    abstract fun initChangeFont()

    abstract fun initReq()

    abstract fun initVMListener()

    abstract fun onCreateMethod()


    fun prepareWithBaseVM(baseVm: BaseVM, isLoading: Boolean = true) {
        if (isLoading) {
            baseVm.loadingHUD.observe(this, androidx.lifecycle.Observer {
                if (it && canShowHud()) showLoadingHUD() else hideLoadingHUD()
            })
        }
    }

    private fun canShowHud(): Boolean {
        return !activity.isFinishing && !activity.isDestroyed
    }

    private fun showLoadingHUD(cancalable: Boolean = false) {
        showLoadingHUD(null, cancalable)
    }

    private fun showLoadingHUD(
        label: String?,
        cancalable: Boolean = false
    ) {
        showLoadingHUD(label, null, cancalable)
    }

    fun showLoadingHUD(
        label: String?,
        detailLabel: String?,
        cancelable: Boolean
    ) {
        if (hud_loading == null) {
            hud_loading = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setBackgroundColor(loadingHudColor())
                .setAnimationSpeed(1)
                .setDimAmount(0.65f)
        }
        if (!label.isNullOrEmpty()) hud_loading!!.setLabel(label)
        if (!detailLabel.isNullOrEmpty()) hud_loading!!.setDetailsLabel(
            detailLabel
        )
        hud_loading!!.setCancellable(cancelable)
        hud_loading!!.show()
    }

    private fun hideLoadingHUD() {
        if (hud_loading != null && hud_loading!!.isShowing) {
            hud_loading!!.dismiss()
        }
        hud_loading = null
    }

    private fun loadingHudColor(): Int {
        return getColorById(R.color.green)
    }

    private fun getColorById(colorId: Int): Int {
        return ContextCompat.getColor(context, colorId)
    }

    fun toast(message: String){
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

}