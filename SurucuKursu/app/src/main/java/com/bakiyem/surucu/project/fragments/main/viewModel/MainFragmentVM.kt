package com.bakiyem.surucu.project.fragments.main.viewModel

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.project.base.vm.BaseVM
import com.bakiyem.surucu.project.model.announcements.Response4Announcements
import com.bakiyem.surucu.project.model.announcements.Response4Slider
import com.bakiyem.surucu.project.model.kurs.Response4Kurs
import com.bakiyem.surucu.project.utils.RxUtils

class MainFragmentVM : BaseVM() {


    var kursLD = MutableLiveData<Response4Kurs>()
    fun getKurs() {
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getKurs()
            ).subscribe({ rr ->
                checkServiceStatus(rr)?.let {
                    kursLD.value = it
                } ?: run{
                    kursLD.value = null
                }

            }, {
                kursLD.value = null
            })
        )
    }


    var announcementsLD = MutableLiveData<MutableList<Response4Announcements>>()
    fun getAnnouncements() {
        loadingHUD.value = true
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getAnnouncements()
            ).subscribe({ rr ->

                checkServiceStatusArr(rr)?.let {
                    announcementsLD.value = it
                } ?: run {
                    announcementsLD.value = null
                }


            }, {
                announcementsLD.value = null
            })
        )
    }

    var sliderLD = MutableLiveData<MutableList<Response4Slider>>()
    fun getSlider(){
        loadingHUD.value = true
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getSlider()
            ).subscribe({ rr ->

                loadingHUD.value = false

                checkServiceStatusArr(rr)?.let {
                    sliderLD.value = it
                }?: run{
                    sliderLD.value = null
                }

            }, { error ->
                loadingHUD.value = false
            })
        )
    }
}