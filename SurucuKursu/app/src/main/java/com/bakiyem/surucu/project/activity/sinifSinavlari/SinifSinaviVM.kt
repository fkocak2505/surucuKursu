package com.bakiyem.surucu.project.activity.sinifSinavlari

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.project.base.vm.BaseVM
import com.bakiyem.surucu.project.model.denemeSinavi.Response4DenemeSinavi
import com.bakiyem.surucu.project.model.sinifSinavi.Response4SinifSinavi
import com.bakiyem.surucu.project.utils.RxUtils

class SinifSinaviVM : BaseVM() {

    val sinifSinaviLD = MutableLiveData<MutableList<Response4SinifSinavi>>()
    fun getSinifSinavi() {
        loadingHUD.value = true
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getSinifSinavi()
            ).subscribe({ rr ->
                loadingHUD.value = false
                checkServiceStatusArr(rr)?.let {
                    sinifSinaviLD.value = it
                } ?: run {
                    sinifSinaviLD.value = null
                }

            }, {
                loadingHUD.value = false
                sinifSinaviLD.value = null
            })
        )
    }

    val sinifSonuclarimListLD = MutableLiveData<MutableList<Response4SinifSinavi>>()
    fun getGirilenSinav() {
        loadingHUD.value = true
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getGirilenSinav()
            ).subscribe({ rr ->
                loadingHUD.value = false
                checkServiceStatusArr(rr)?.let {
                    sinifSonuclarimListLD.value = it
                } ?: run {
                    sinifSonuclarimListLD.value = null
                }

            }, {
                loadingHUD.value = false
                sinifSonuclarimListLD.value = null
            })
        )
    }

    val sinifSinaviQuizLD = MutableLiveData<MutableList<Response4DenemeSinavi>>()
    fun getSinifSinaviQuiz(sinavId: String) {
        loadingHUD.value = true
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getSinifSinaviQuiz(sinavId)
            ).subscribe({ rr ->
                loadingHUD.value = false
                checkServiceStatusArr(rr)?.let {
                    sinifSinaviQuizLD.value = it
                } ?: run {
                    sinifSinaviQuizLD.value = null
                }


            }, {
                loadingHUD.value = false
                sinifSinaviQuizLD.value = null
            })
        )
    }

}