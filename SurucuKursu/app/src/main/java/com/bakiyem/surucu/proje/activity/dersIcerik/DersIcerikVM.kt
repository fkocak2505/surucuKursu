package com.bakiyem.surucu.proje.activity.dersIcerik

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.proje.base.vm.BaseVM
import com.bakiyem.surucu.proje.model.dersIcerik.Response4DersIcerik
import com.bakiyem.surucu.proje.utils.RxUtils

class DersIcerikVM : BaseVM() {

    var dersIcerikLD = MutableLiveData<MutableList<Response4DersIcerik>>()
    fun getDersIcerik(dersKey: String) {
        loadingHUD.value = true
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getDersIcerik(dersKey)
            ).subscribe({ rr ->

                loadingHUD.value = false

                checkServiceStatusArr(rr)?.let {
                    dersIcerikLD.value = rr.data
                } ?: run {
                    dersIcerikLD.value = null
                }

            }, {
                loadingHUD.value = false
                dersIcerikLD.value = null
            })
        )
    }

}