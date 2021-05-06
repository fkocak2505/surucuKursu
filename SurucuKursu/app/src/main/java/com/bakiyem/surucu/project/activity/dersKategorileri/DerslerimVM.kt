package com.bakiyem.surucu.project.activity.dersKategorileri

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.project.base.vm.BaseVM
import com.bakiyem.surucu.project.model.derslerim.Response4Derslerim
import com.bakiyem.surucu.project.utils.RxUtils

class DerslerimVM : BaseVM() {

    var dersKategoriLD = MutableLiveData<MutableList<Response4Derslerim>>()
    fun getDersKategori() {
        loadingHUD.value = true
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getDersKategori()
            ).subscribe({ rr ->

                loadingHUD.value = false

                checkServiceStatusArr(rr)?.let {

                    dersKategoriLD.value = rr.data


                } ?: run {
                    dersKategoriLD.value = null
                }

            }, {
                loadingHUD.value = false
                dersKategoriLD.value = null
            })
        )
    }
}