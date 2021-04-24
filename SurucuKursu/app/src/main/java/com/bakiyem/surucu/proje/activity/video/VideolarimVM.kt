package com.bakiyem.surucu.proje.activity.video

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.proje.base.vm.BaseVM
import com.bakiyem.surucu.proje.model.video.Response4Video
import com.bakiyem.surucu.proje.utils.RxUtils

class VideolarimVM : BaseVM() {

    val videolarLD = MutableLiveData<MutableList<Response4Video>>()
    fun getVideolar() {
        loadingHUD.value = true
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getVideolar()
            ).subscribe({ rr ->

                loadingHUD.value = false

                checkServiceStatusArr(rr)?.let {
                    videolarLD.value = it
                } ?: run {
                    videolarLD.value = null
                }

            }, { error ->
                loadingHUD.value = false
            })
        )

    }

}