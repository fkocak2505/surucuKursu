package com.bakiyem.surucu.proje.activity.ozelSinavlarim

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.proje.base.vm.BaseVM
import com.bakiyem.surucu.proje.model.denemeSinavi.Response4DenemeSinavi
import com.bakiyem.surucu.proje.utils.RxUtils

class OzelSinavVM : BaseVM() {

    var ozelSinavLD = MutableLiveData<MutableList<Response4DenemeSinavi>>()
    fun getOzelSinav(
        ilkYardimCount: String,
        trafikCount: String,
        motorCount: String,
        adapCount: String
    ) {
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getOzelSinav(ilkYardimCount, trafikCount, motorCount, adapCount)
            ).subscribe({ rr ->

                checkServiceStatusArr(rr)?.let {
                    ozelSinavLD.value = it
                } ?: run {
                    ozelSinavLD.value = null
                }


            }, {
                ozelSinavLD.value = null
            })
        )
    }

}