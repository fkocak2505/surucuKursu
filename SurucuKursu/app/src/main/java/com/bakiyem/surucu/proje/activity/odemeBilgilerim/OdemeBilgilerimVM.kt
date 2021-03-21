package com.bakiyem.surucu.proje.activity.odemeBilgilerim

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.proje.base.vm.BaseVM
import com.bakiyem.surucu.proje.model.odemeBilgilerim.Response4BorcOzet
import com.bakiyem.surucu.proje.model.odemeBilgilerim.Response4OdemeBilgileri
import com.bakiyem.surucu.proje.utils.RxUtils

class OdemeBilgilerimVM : BaseVM() {

    val borcListesiLD = MutableLiveData<MutableList<Response4OdemeBilgileri>>()
    fun getBorcListesi() {

        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getBorcListesi()
            ).subscribe({ rr ->

                checkServiceStatusArr(rr)?.let {
                    borcListesiLD.value = it
                } ?: run {
                    borcListesiLD.value = null
                }

            }, {
                borcListesiLD.value = null
            })
        )

    }

    val borcOzetLD = MutableLiveData<Response4BorcOzet>()
    fun getBorcOzet() {
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getBorcOzet()
            ).subscribe({ rr ->
                checkServiceStatus(rr)?.let {
                    borcOzetLD.value = it
                } ?: run {
                    borcOzetLD.value = null
                }
            }, {
                borcOzetLD.value = null
            })
        )
    }

}