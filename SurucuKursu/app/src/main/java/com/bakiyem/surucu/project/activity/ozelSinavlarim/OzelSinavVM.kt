package com.bakiyem.surucu.project.activity.ozelSinavlarim

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.project.base.vm.BaseVM
import com.bakiyem.surucu.project.model.denemeSinavi.Response4DenemeSinavi
import com.bakiyem.surucu.project.utils.RxUtils

class OzelSinavVM : BaseVM() {

    var ozelSinavLD = MutableLiveData<MutableList<Response4DenemeSinavi>>()
    fun getOzelSinav(
        ilkYardimCount: String,
        trafikCount: String,
        motorCount: String,
        adapCount: String
    ) {
        loadingHUD.value = true
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getOzelSinav(ilkYardimCount, trafikCount, motorCount, adapCount)
            ).subscribe({ rr ->
                loadingHUD.value = false
                checkServiceStatusArr(rr)?.let {
                    ozelSinavLD.value = it
                } ?: run {
                    ozelSinavLD.value = null
                }


            }, {
                loadingHUD.value = false
                ozelSinavLD.value = null
            })
        )
    }

}