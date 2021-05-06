package com.bakiyem.surucu.project.activity.login

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.project.base.vm.BaseVM
import com.bakiyem.surucu.project.model.login.Response4Login
import com.bakiyem.surucu.project.utils.RxUtils

class LoginVM : BaseVM() {

    var loginLD = MutableLiveData<Response4Login>()
    fun doLogin(tckn: String) {
        loadingHUD.value = true
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.doLogin(tckn)
            ).subscribe({ rr ->
                loadingHUD.value = false
                checkServiceStatus(rr)?.let {
                    loginLD.value = rr.data
                } ?: run {
                    loginLD.value = null
                }

            }, {
                loadingHUD.value = false
                loginLD.value = null
            })
        )
    }

}