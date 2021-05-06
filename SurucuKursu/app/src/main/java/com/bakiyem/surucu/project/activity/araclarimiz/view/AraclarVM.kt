package com.bakiyem.surucu.project.activity.araclarimiz.view

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.project.base.vm.BaseVM
import com.bakiyem.surucu.project.model.araclar.Response4Araclar
import com.bakiyem.surucu.project.utils.RxUtils

class AraclarVM: BaseVM() {

    var araclarLD = MutableLiveData<MutableList<Response4Araclar>>()
    fun getAraclar(){
        loadingHUD.value = true
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getAraclar()
            ).subscribe({ rr ->

                loadingHUD.value = false
                checkServiceStatusArr(rr)?.let {
                    araclarLD.value = it
                }?: run{
                    araclarLD.value = null
                }

            }, {
                loadingHUD.value = false
                araclarLD.value = null
            })
        )
    }

}