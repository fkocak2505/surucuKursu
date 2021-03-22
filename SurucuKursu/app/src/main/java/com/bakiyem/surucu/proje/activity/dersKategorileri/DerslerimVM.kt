package com.bakiyem.surucu.proje.activity.dersKategorileri

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.proje.base.vm.BaseVM
import com.bakiyem.surucu.proje.model.derslerim.Response4Derslerim
import com.bakiyem.surucu.proje.utils.RxUtils

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