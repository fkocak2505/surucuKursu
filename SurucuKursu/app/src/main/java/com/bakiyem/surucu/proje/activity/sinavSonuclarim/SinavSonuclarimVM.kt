package com.bakiyem.surucu.proje.activity.sinavSonuclarim

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.proje.base.vm.BaseVM
import com.bakiyem.surucu.proje.model.sinavSonuclarim.Response4SinavSonuclarim
import com.bakiyem.surucu.proje.utils.RxUtils

class SinavSonuclarimVM: BaseVM() {

    val sinavSonuclarimLD = MutableLiveData<Response4SinavSonuclarim>()
    fun getSinavSonuclarim(){
        loadingHUD.value = true
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getSinavSonuclarim()
            ).subscribe({ rr ->
                loadingHUD.value = false
                checkServiceStatus(rr)?.let {
                    sinavSonuclarimLD.value = it
                }?: run{
                    sinavSonuclarimLD.value = null
                }

            }, {
                loadingHUD.value = false
                sinavSonuclarimLD.value = null
            })
        )
    }
}