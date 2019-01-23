package com.fedak.denis.mymvvm.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import android.view.View
import com.fedak.denis.mymvvm.activity.MainActivity.Companion.LOG
import com.fedak.denis.mymvvm.adapter.CarAdapter
import com.fedak.denis.mymvvm.model.Car
import com.fedak.denis.mymvvm.repository.CarRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(context: Context) : BaseViewModel(context as Application) {

    @Inject
    lateinit var carRepository: CarRepository

    lateinit var subscriptions: CompositeDisposable

    lateinit var data: MutableLiveData<List<Car>>

    val carAdapter: CarAdapter = CarAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    fun getCars(): MutableLiveData<List<Car>>? {

        startLoading()
        data = MutableLiveData()
        getDataFromRepository()
        return data
    }

    private fun getDataFromRepository() {
        val subscription =
            carRepository.getCars()!!.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(
                { cars ->
                    LOG.info("DATA =" + cars.size)
                    carRepository.saveCarsInDB(cars)
                    data.postValue(cars)
                },
                { e ->
                    loadError.postValue(e.toString())
                    LOG.info("ERROR =" + e.toString())
                })
        subscriptions.add(subscription)
    }

    private fun startLoading() {
        loadingVisibility.value = View.VISIBLE
    }

    fun finishLoading() {
        loadingVisibility.value = View.GONE
    }

    fun refreshAdapter(cars: List<Car>) {
        carAdapter.setData(cars)
    }

    fun setSuscriptions(subscriptions: CompositeDisposable) {
        this.subscriptions = subscriptions
    }
}