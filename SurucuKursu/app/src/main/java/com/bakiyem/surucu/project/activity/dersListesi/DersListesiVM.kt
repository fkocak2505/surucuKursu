package com.bakiyem.surucu.project.activity.dersListesi

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.project.base.vm.BaseVM
import com.bakiyem.surucu.project.model.dersListesi.Response4DersListesi
import com.bakiyem.surucu.project.utils.RxUtils

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