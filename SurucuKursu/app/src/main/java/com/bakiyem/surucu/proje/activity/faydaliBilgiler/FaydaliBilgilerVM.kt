package com.bakiyem.surucu.proje.activity.faydaliBilgiler

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.proje.base.vm.BaseVM
import com.bakiyem.surucu.proje.model.faydaliBilgiler.Response4FaydaliBilgiler
import com.bakiyem.surucu.proje.utils.RxUtils

class FaydaliBilgilerVM: BaseVM() {

    val faydaliBilgilerLD = MutableLiveData<MutableList<Response4FaydaliBilgiler>>()
    fun getFaydaliBilgiler(){
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getFaydaliBilgiler()
            ).subscribe({ rr->

                checkServiceStatusArr(rr)?.let {
                    faydaliBilgilerLD.value = it
                }?: run{
                    faydaliBilgilerLD.value = null
                }

            }, {
                faydaliBilgilerLD.value = null
            })
        )
    }

}