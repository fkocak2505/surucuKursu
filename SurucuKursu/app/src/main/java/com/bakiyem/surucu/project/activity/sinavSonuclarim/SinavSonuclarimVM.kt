package com.bakiyem.surucu.project.activity.sinavSonuclarim

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.project.base.vm.BaseVM
import com.bakiyem.surucu.project.model.sinavSonuclarim.Response4SinavSonuclarim
import com.bakiyem.surucu.project.utils.RxUtils

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