package com.bakiyem.surucu.proje.activity.galeri

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.proje.base.vm.BaseVM
import com.bakiyem.surucu.proje.model.galeri.Response4Galeri
import com.bakiyem.surucu.proje.utils.RxUtils

class GaleriVM : BaseVM() {

    var galeriLD = MutableLiveData<MutableList<Response4Galeri>>()
    fun getGaleri() {
        loadingHUD.value = true
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getGaleri()
            ).subscribe({ rr ->
                loadingHUD.value = false
                checkServiceStatusArr(rr)?.let {
                    galeriLD.value = rr.data
                } ?: run {
                    galeriLD.value = null
                }
            }, {
                loadingHUD.value = false
                galeriLD.value = null
            })
        )
    }

}