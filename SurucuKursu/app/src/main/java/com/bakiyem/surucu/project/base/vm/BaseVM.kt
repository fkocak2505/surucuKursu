package com.bakiyem.surucu.project.base.vm

import androidx.annotation.CallSuper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bakiyem.surucu.project.base.model.ResResult
import com.bakiyem.surucu.project.base.model.ResResultArray
import com.bakiyem.surucu.project.model.retrofit.SurucuKursuApiService
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

open class BaseVM : ViewModel() {

    val sApiService = SurucuKursuApiService()

    private var disposables: CompositeDisposable? = null

    val loadingHUD = MutableLiveData<Boolean>()

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposables?.add(disposable)
    }

    private val compositeDisposables: CompositeDisposable?
        get() {
            if (disposables == null) {
                disposables = CompositeDisposable()
            }
            return disposables
        }

    fun <T> checkServiceStatus(resResult: ResResult<T>): T?{
        return when(resResult.isError){
            true -> {
                null
            }
            false -> {
                resResult.data
            }
        }
    }

    fun <T> checkServiceStatusArr(resResult: ResResultArray<T>): MutableList<T>?{
        return when(resResult.isError){
            true -> {
                null
            }
            false -> {
                resResult.data
            }
        }
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        if (disposables != null) {
            disposables!!.dispose()
            disposables = null
        }
    }
}