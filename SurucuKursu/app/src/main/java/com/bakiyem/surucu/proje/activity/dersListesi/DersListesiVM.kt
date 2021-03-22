package com.bakiyem.surucu.proje.activity.dersListesi

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.proje.base.vm.BaseVM
import com.bakiyem.surucu.proje.model.dersListesi.Response4DersListesi
import com.bakiyem.surucu.proje.utils.RxUtils

class DersListesiVM : BaseVM() {

    var dersListesiLD = MutableLiveData<MutableList<Response4DersListesi>>()
    fun getDersListesi(kategori: String) {
        loadingHUD.value = true
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getDersListesi(kategori)
            ).subscribe({ rr ->

                loadingHUD.value = false

                checkServiceStatusArr(rr)?.let {
                    dersListesiLD.value = rr.data
                } ?: run {
                    dersListesiLD.value = null
                }

            }, {

                loadingHUD.value = false
                dersListesiLD.value = null
            })
        )
    }

}