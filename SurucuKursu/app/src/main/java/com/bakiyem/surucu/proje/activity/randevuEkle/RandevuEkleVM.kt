package com.bakiyem.surucu.proje.activity.randevuEkle

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.proje.base.vm.BaseVM
import com.bakiyem.surucu.proje.model.iletisim.Response4SendFeedback
import com.bakiyem.surucu.proje.model.randevuEkle.Response4Egitmen
import com.bakiyem.surucu.proje.model.randevuSaat.Response4Saat
import com.bakiyem.surucu.proje.utils.RxUtils

class RandevuEkleVM : BaseVM() {

    val egitmenListLD = MutableLiveData<MutableList<Response4Egitmen>>()
    fun getEgitmenList(tarihData: String) {
        loadingHUD.value = true
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getRandevuEgitmenList(tarihData)
            ).subscribe({ rr ->

                loadingHUD.value = false
                checkServiceStatusArr(rr)?.let {
                    egitmenListLD.value = it
                } ?: run {
                    egitmenListLD.value = null
                }

            }, {
                loadingHUD.value = false
            })
        )

    }

    val saatListLD = MutableLiveData<MutableList<Response4Saat>>()
    fun getEgitmenSaat(tarih: String, egitmenID: String) {
        loadingHUD.value = true
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getRandevuEgitmenSaat(tarih, egitmenID)
            ).subscribe({ rr ->

                loadingHUD.value = false
                checkServiceStatusArr(rr)?.let {
                    saatListLD.value = it
                } ?: run {
                    saatListLD.value = null
                }
            }, {
                loadingHUD.value = false
            })
        )
    }

    val addRandevuLD = MutableLiveData<Response4SendFeedback>()
    fun addRandevu(tarih: String, egitmen: String, saat: String){
        loadingHUD.value
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.addRandevu(tarih, egitmen, saat)
            ).subscribe({rr->
                        loadingHUD.value = false
                        checkServiceStatus(rr)?.let {
                            addRandevuLD.value = it
                        }?: run{
                            addRandevuLD.value = null
                        }

            }, {
                loadingHUD.value = false
            })
        )
    }

}