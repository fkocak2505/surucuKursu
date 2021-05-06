package com.bakiyem.surucu.project.activity.odemeYap

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.project.base.vm.BaseVM
import com.bakiyem.surucu.project.model.odemeYap.Response4OdemeYap
import com.bakiyem.surucu.project.utils.RxUtils

class OdemeYapVM : BaseVM() {

    val odemeYapLD = MutableLiveData<Response4OdemeYap>()
    fun odemeYap(
        kartNo: String,
        kartIsim: String,
        ay: String,
        yil: String,
        cv2: String,
        tutar: String
    ) {
        loadingHUD.value = true

        addDisposable(
            RxUtils.androidDefaults(
                sApiService.odemeYap(kartNo, kartIsim, ay, yil, cv2, tutar)
            ).subscribe({ rr ->

                loadingHUD.value = false
                checkServiceStatus(rr)?.let {
                    odemeYapLD.value = it
                } ?: run {
                    odemeYapLD.value = null
                }
            }, {
                loadingHUD.value = false
                odemeYapLD.value = null
            })
        )

    }

}