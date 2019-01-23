package com.fedak.denis.mymvvm.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.fedak.denis.mymvvm.activity.MainActivity.Companion.LOG
import com.fedak.denis.mymvvm.model.Car
import com.fedak.denis.mymvvm.repository.CarRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailViewModel @Inject constructor(context: Context) : BaseViewModel(context as Application) {

    @Inject
    lateinit var carRepository: CarRepository
    lateinit var subscriptions: CompositeDisposable

    lateinit var data: MutableLiveData<Car>

    var url: String? = "default url"

    fun loadCar(id: Int) : MutableLiveData<Car>{
        data = MutableLiveData()
        getDataFromRepositoryById(id)
        return data
    }

    private fun getDataFromRepositoryById(id: Int) {
        val subscription = carRepository.getDataById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { car ->
                    LOG.info("DETAIL")
                    data.postValue(car)
                },
                { e ->
                    loadError.postValue(e.toString())
                })
        subscriptions.add(subscription)
    }

    fun setSuscriptions(subscriptions: CompositeDisposable) {
        this.subscriptions = subscriptions
    }
}