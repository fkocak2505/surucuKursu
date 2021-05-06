package com.bakiyem.surucu.project.activity.galeri

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.project.base.vm.BaseVM
import com.bakiyem.surucu.project.model.galeri.Response4Galeri
import com.bakiyem.surucu.project.utils.RxUtils

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