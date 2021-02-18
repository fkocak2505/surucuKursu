package com.bakiyem.surucu.proje.fragments.main.viewModel

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.proje.base.vm.BaseVM
import com.bakiyem.surucu.proje.model.announcements.Response4Announcements
import com.bakiyem.surucu.proje.model.kurs.Response4Kurs
import com.bakiyem.surucu.proje.utils.RxUtils

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
}