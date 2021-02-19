package com.bakiyem.surucu.proje.activity.hakkimizda

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.proje.base.vm.BaseVM
import com.bakiyem.surucu.proje.model.hakkimizda.Response4Hakkimizda
import com.bakiyem.surucu.proje.utils.RxUtils

class HakkimizdaVM: BaseVM() {

    var hakkimizdaLD = MutableLiveData<Response4Hakkimizda>()
    fun getHakkimizda(){
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getHakkimizda()
            ).subscribe({ rr ->

                checkServiceStatus(rr)?.let {
                    hakkimizdaLD.value = it
                }?: run{
                    hakkimizdaLD.value = null
                }
            }, {
                hakkimizdaLD.value = null
            })
        )
    }

}