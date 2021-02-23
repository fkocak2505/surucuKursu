package com.bakiyem.surucu.proje.activity.profil

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.proje.base.vm.BaseVM
import com.bakiyem.surucu.proje.model.profilim.Response4Profilim
import com.bakiyem.surucu.proje.utils.RxUtils

class ProfilimActivityVM : BaseVM() {

    val profilLD = MutableLiveData<Response4Profilim>()
    fun getProfilim() {
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getProfilim()
            ).subscribe({ rr ->

                checkServiceStatus(rr)?.let {
                    profilLD.value = rr.data
                } ?: run {
                    profilLD.value = null
                }

            }, {
                profilLD.value = null
            })
        )
    }

}