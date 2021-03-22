package com.bakiyem.surucu.proje.fragments.contact

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.proje.base.vm.BaseVM
import com.bakiyem.surucu.proje.model.iletisim.Response4Iletisim
import com.bakiyem.surucu.proje.model.iletisim.Response4SendFeedback
import com.bakiyem.surucu.proje.utils.RxUtils

class IletisimVM : BaseVM() {

    val iletisimLD = MutableLiveData<Response4Iletisim>()
    fun getIletisim() {
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getIletisim()
            ).subscribe({ rr ->

                checkServiceStatus(rr)?.let {
                    iletisimLD.value = it
                } ?: run {
                    iletisimLD.value = null
                }

            }, {
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