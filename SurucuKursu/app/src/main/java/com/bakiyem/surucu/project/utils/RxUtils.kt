package com.bakiyem.surucu.project.utils

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response


object RxUtils {

    fun <T> androidDef(single: Single<Response<T>>): Single<Response<T>> {
        return single
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> androidDefaults(observable: Observable<T>): Observable<T> {
        return observable
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
    }

}