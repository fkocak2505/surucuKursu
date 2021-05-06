package com.bakiyem.surucu.project.activity.video

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.project.base.vm.BaseVM
import com.bakiyem.surucu.project.model.video.Response4Video
import com.bakiyem.surucu.project.utils.RxUtils

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