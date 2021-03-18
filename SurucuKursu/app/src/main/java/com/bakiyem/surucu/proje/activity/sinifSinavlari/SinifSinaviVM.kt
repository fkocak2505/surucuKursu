package com.bakiyem.surucu.proje.activity.sinifSinavlari

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.proje.base.vm.BaseVM
import com.bakiyem.surucu.proje.model.denemeSinavi.Response4DenemeSinavi
import com.bakiyem.surucu.proje.model.sinifSinavi.Response4SinifSinavi
import com.bakiyem.surucu.proje.utils.RxUtils

class SinifSinaviVM : BaseVM() {

    val sinifSinaviLD = MutableLiveData<MutableList<Response4SinifSinavi>>()
    fun getSinifSinavi() {
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getSinifSinavi()
            ).subscribe({ rr ->

                checkServiceStatusArr(rr)?.let {
                    sinifSinaviLD.value = it
                } ?: run {
                    sinifSinaviLD.value = null
                }

            }, {
                sinifSinaviLD.value = null
            })
        )
    }

    val sinifSinaviQuizLD = MutableLiveData<MutableList<Response4DenemeSinavi>>()
    fun getSinifSinaviQuiz(sinavId: String) {
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getSinifSinaviQuiz(sinavId)
            ).subscribe({ rr ->

                checkServiceStatusArr(rr)?.let {
                    sinifSinaviQuizLD.value = it
                } ?: run {
                    sinifSinaviQuizLD.value = null
                }


            }, {
                sinifSinaviQuizLD.value = null
            })
        )
    }

}