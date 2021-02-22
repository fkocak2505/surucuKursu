package com.bakiyem.surucu.proje.activity.kadromuz

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.proje.base.vm.BaseVM
import com.bakiyem.surucu.proje.model.kadromuz.Response4Kadromuz
import com.bakiyem.surucu.proje.utils.RxUtils

class KadromuzVM : BaseVM() {


    var kadromuzLD = MutableLiveData<MutableList<Response4Kadromuz>>()
    fun getKadromuz() {
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getKadromuz()
            ).subscribe({ rr ->

                checkServiceStatusArr(rr)?.let {
                    kadromuzLD.value = rr.data
                } ?: run {
                    kadromuzLD.value = null
                }

            }, {
                kadromuzLD.value = null
            })
        )
    }
}