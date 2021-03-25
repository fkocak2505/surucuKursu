package com.bakiyem.surucu.proje.activity.randevular

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.proje.base.vm.BaseVM
import com.bakiyem.surucu.proje.model.iletisim.Response4SendFeedback
import com.bakiyem.surucu.proje.model.randevularim.Response4Randevularim
import com.bakiyem.surucu.proje.utils.RxUtils

class RandevularimVM : BaseVM() {

    val randevularimLD = MutableLiveData<MutableList<Response4Randevularim>>()
    fun getRandevularim() {
        loadingHUD.value = true
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getRandevularim()
            ).subscribe({ rr ->
                loadingHUD.value = false
                checkServiceStatusArr(rr)?.let {
                    randevularimLD.value = it
                } ?: run {
                    randevularimLD.value = null
                }

            }, {
                loadingHUD.value = false
                randevularimLD.value = null
            })
        )
    }

    val infoRandevuIptalLD = MutableLiveData<Response4SendFeedback>()
    fun randevuIptal(randevuId: String) {
        loadingHUD.value = true
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.randevuIptal(randevuId)
            ).subscribe({ rr ->

                checkServiceStatus(rr)?.let {
                    infoRandevuIptalLD.value = it
                } ?: run {
                    infoRandevuIptalLD.value = null
                }

            }, {
                infoRandevuIptalLD.value = null
            })
        )

    }

}