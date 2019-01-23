package com.fedak.denis.mymvvm.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable

import io.realm.Realm
import java.util.concurrent.Executor
import java.util.concurrent.Executors

open class BaseViewModel(application : Application) : AndroidViewModel(application){

    val executor: Executor
    val realm: Realm

    lateinit var loadError: MutableLiveData<String>
//    var subscriptions : CompositeDisposable


    init {
        executor = Executors.newSingleThreadExecutor()
        realm = Realm.getDefaultInstance()
//        subscriptions = CompositeDisposable()
    }

    fun getError(): MutableLiveData<String> {
        loadError  = MutableLiveData()
        return loadError
    }

    override fun onCleared() {
        super.onCleared()
//        subscriptions.clear()
//        subscriptions.dispose()
    }
}