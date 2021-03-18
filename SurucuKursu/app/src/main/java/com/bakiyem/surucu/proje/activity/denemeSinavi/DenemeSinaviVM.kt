package com.bakiyem.surucu.proje.activity.denemeSinavi

import androidx.lifecycle.MutableLiveData
import com.bakiyem.surucu.proje.base.vm.BaseVM
import com.bakiyem.surucu.proje.model.denemeSinavi.QuestionsResultModel
import com.bakiyem.surucu.proje.model.denemeSinavi.Response4DenemeSinavi
import com.bakiyem.surucu.proje.model.denemeSinavi.Response4SinavSonuc
import com.bakiyem.surucu.proje.utils.RxUtils

class DenemeSinaviVM : BaseVM() {

    var denemeSinaviLD = MutableLiveData<MutableList<Response4DenemeSinavi>>()
    fun getDenemeSinavi() {
        addDisposable(
            RxUtils.androidDefaults(
                sApiService.getDenemeSinavi()
            ).subscribe({ rr ->

                checkServiceStatusArr(rr)?.let {
                    denemeSinaviLD.value = it
                } ?: run {
                    denemeSinaviLD.value = null
                }

            }, {
                denemeSinaviLD.value = null
            })
        )
    }

    var sinavSonucLD = MutableLiveData<Response4SinavSonuc>()
    var cevapNumberLD = MutableLiveData<String>()
    fun postSinavSonuc(
        sure: String,
        tur: String,
        sinav: String? = null,
        resultQuestions: MutableList<QuestionsResultModel>
    ) {
        var iyDogru = 0
        var iyYanlis = 0
        var iyBos = 0
        var tDogru = 0
        var tYanlis = 0
        var tBos = 0
        var mDogru = 0
        var mYanlis = 0
        var mBos = 0
        var aDogru = 0
        var aYanlis = 0
        var aBos = 0

        resultQuestions.forEachIndexed { index, questionsResultModel ->
            when (questionsResultModel.answer) {
                0 -> {
                    when {
                        index < 12 -> {
                            iyBos++
                        }
                        index in 12..34 -> {
                            tBos++
                        }
                        index in 35..43 -> {
                            mBos++
                        }
                        else -> {
                            aBos++
                        }
                    }
                }
                1 -> {
                    when {
                        index < 12 -> {
                            iyDogru++
                        }
                        index in 12..34 -> {
                            tDogru++
                        }
                        index in 35..43 -> {
                            mDogru++
                        }
                        else -> {
                            aDogru++
                        }
                    }
                }
                -1 -> {
                    when {
                        index < 12 -> {
                            iyYanlis++
                        }
                        index in 12..34 -> {
                            tYanlis++
                        }
                        index in 35..43 -> {
                            mYanlis++
                        }
                        else -> {
                            aYanlis++
                        }
                    }
                }
            }
        }

        addDisposable(
            RxUtils.androidDefaults(
                sApiService.postSinavSonuc(
                    tur,
                    iyDogru.toString(),
                    iyYanlis.toString(),
                    iyBos.toString(),
                    tDogru.toString(),
                    tYanlis.toString(),
                    tBos.toString(),
                    mDogru.toString(),
                    mYanlis.toString(),
                    mBos.toString(),
                    aDogru.toString(),
                    aYanlis.toString(),
                    aBos.toString(),
                    sinav,
                    sure
                )
            ).subscribe({ rr ->

                checkServiceStatus(rr)?.let {
                    cevapNumberLD.value =
                        "${iyDogru + tDogru + mDogru + aDogru}&${iyYanlis + tYanlis + mYanlis + aYanlis}&${iyBos + tBos + mBos + aBos}"
                    sinavSonucLD.value = it
                } ?: run {
                    cevapNumberLD.value = null
                    sinavSonucLD.value = null
                }

            }, {
                cevapNumberLD.value = null
                sinavSonucLD.value = null
            })
        )
    }
}