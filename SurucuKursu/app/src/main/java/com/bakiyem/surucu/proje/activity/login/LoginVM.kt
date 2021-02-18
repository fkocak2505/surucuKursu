package com.bakiyem.surucu.proje.activity.login

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.proje.base.vm.BaseVM
import com.bakiyem.surucu.proje.model.login.Response4Login
import com.bakiyem.surucu.proje.utils.RxUtils

class LoginVM : BaseVM() {

    var loginLD = MutableLiveData<Response4Login>()
    fun doLogin(tckn: String) {
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.doLogin(tckn)
            ).subscribe({ rr ->

                checkServiceStatus(rr)?.let {
                    loginLD.value = rr.data
                } ?: run {
                    loginLD.value = null
                }

            }, {
                loginLD.value = null
            })
        )
    }

}