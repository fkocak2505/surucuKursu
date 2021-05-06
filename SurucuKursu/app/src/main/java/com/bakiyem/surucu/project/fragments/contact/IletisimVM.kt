package com.bakiyem.surucu.project.fragments.contact

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.project.base.vm.BaseVM
import com.bakiyem.surucu.project.model.iletisim.Response4Iletisim
import com.bakiyem.surucu.project.model.iletisim.Response4SendFeedback
import com.bakiyem.surucu.project.utils.RxUtils

class IletisimVM : BaseVM() {

    val iletisimLD = MutableLiveData<Response4Iletisim>()
    fun getIletisim() {
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getIletisim()
            ).subscribe({ rr ->

                loadingHUD.value = false

                checkServiceStatus(rr)?.let {
                    iletisimLD.value = it
                } ?: run {
                    iletisimLD.value = null
                }

            }, {
                loadingHUD.value = false
                iletisimLD.value = null
            })
        )
    }

    val sendFeedbackLD = MutableLiveData<Response4SendFeedback>()
    fun sendFeedback(adSoyad: String, mail: String, tel: String, mesaj: String) {
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.sendFeedback(adSoyad, mail, tel, mesaj)
            ).subscribe({ rr->

                checkServiceStatus(rr)?.let {
                    sendFeedbackLD.value = it
                }?: run{
                    sendFeedbackLD.value = null
                }

            },{
                sendFeedbackLD.value = null
            })
        )
    }

}