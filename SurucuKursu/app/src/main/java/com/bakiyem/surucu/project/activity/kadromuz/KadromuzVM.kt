package com.bakiyem.surucu.project.activity.kadromuz

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.project.base.vm.BaseVM
import com.bakiyem.surucu.project.model.kadromuz.Response4Kadromuz
import com.bakiyem.surucu.project.utils.RxUtils

class KadromuzVM : BaseVM() {


    var kadromuzLD = MutableLiveData<MutableList<Response4Kadromuz>>()
    fun getKadromuz() {
        loadingHUD.value = true
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getKadromuz()
            ).subscribe({ rr ->
                loadingHUD.value = false
                checkServiceStatusArr(rr)?.let {
                    kadromuzLD.value = rr.data
                } ?: run {
                    kadromuzLD.value = null
                }

            }, {
                loadingHUD.value = false
                kadromuzLD.value = null
            })
        )
    }
}