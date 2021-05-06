package com.bakiyem.surucu.project.activity.odemeBilgilerim

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.project.base.vm.BaseVM
import com.bakiyem.surucu.project.model.odemeBilgilerim.Response4BorcOzet
import com.bakiyem.surucu.project.model.odemeBilgilerim.Response4OdemeBilgileri
import com.bakiyem.surucu.project.utils.RxUtils

class OdemeBilgilerimVM : BaseVM() {

    val borcListesiLD = MutableLiveData<MutableList<Response4OdemeBilgileri>>()
    fun getBorcListesi() {
        loadingHUD.value = true
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getBorcListesi()
            ).subscribe({ rr ->
                loadingHUD.value = false
                checkServiceStatusArr(rr)?.let {
                    borcListesiLD.value = it
                } ?: run {
                    borcListesiLD.value = null
                }

            }, {
                loadingHUD.value = false
                borcListesiLD.value = null
            })
        )

    }

    val borcOzetLD = MutableLiveData<Response4BorcOzet>()
    fun getBorcOzet() {
        loadingHUD.value = true
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getBorcOzet()
            ).subscribe({ rr ->
                loadingHUD.value = false
                checkServiceStatus(rr)?.let {
                    borcOzetLD.value = it
                } ?: run {
                    borcOzetLD.value = null
                }
            }, {
                loadingHUD.value = false
                borcOzetLD.value = null
            })
        )
    }

}