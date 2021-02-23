package com.bakiyem.surucu.proje.activity.dersIcerik

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.proje.base.vm.BaseVM
import com.bakiyem.surucu.proje.model.dersIcerik.Response4DersIcerik
import com.bakiyem.surucu.proje.utils.RxUtils

class DersIcerikVM : BaseVM() {

    var dersIcerikLD = MutableLiveData<MutableList<Response4DersIcerik>>()
    fun getDersIcerik(dersKey: String) {
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getDersIcerik(dersKey)
            ).subscribe({ rr ->

                checkServiceStatusArr(rr)?.let {
                    dersIcerikLD.value = rr.data
                } ?: run {
                    dersIcerikLD.value = null
                }

            }, {
                dersIcerikLD.value = null
            })
        )
    }

}