package com.bakiyem.surucu.proje.activity.duyuruDetay

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.proje.base.vm.BaseVM
import com.bakiyem.surucu.proje.model.duyuruDetay.Response4DuyuruDetay
import com.bakiyem.surucu.proje.utils.RxUtils

class DuyuruDetayVM : BaseVM() {

    var duyuruDetayLD = MutableLiveData<List<Response4DuyuruDetay>>()
    fun getDuyuruDetay(key: String) {
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getDuyuruDetay(key)
            ).subscribe({ rr ->

                checkServiceStatusArr(rr)?.let {
                    duyuruDetayLD.value = rr.data
                } ?: run {
                    duyuruDetayLD.value = null
                }

            }, {
                duyuruDetayLD.value = null
            })
        )
    }

}