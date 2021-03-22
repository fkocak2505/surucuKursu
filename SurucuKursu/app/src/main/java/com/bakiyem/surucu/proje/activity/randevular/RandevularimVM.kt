package com.bakiyem.surucu.proje.activity.randevular

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.proje.base.vm.BaseVM
import com.bakiyem.surucu.proje.model.randevularim.Response4Randevularim
import com.bakiyem.surucu.proje.utils.RxUtils

class RandevularimVM: BaseVM() {

    val randevularimLD = MutableLiveData<MutableList<Response4Randevularim>>()
    fun getRandevularim(){
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getRandevularim()
            ).subscribe({ rr->

                checkServiceStatusArr(rr)?.let {
                    randevularimLD.value = it
                }?: run{
                    randevularimLD.value = null
                }

            }, {
                randevularimLD.value = null
            })
        )
    }

}