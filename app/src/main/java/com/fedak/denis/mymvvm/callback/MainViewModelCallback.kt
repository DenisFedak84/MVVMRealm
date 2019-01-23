package com.fedak.denis.mymvvm.callback

import com.fedak.denis.mymvvm.model.Car
import com.fedak.denis.mymvvm.model.RealmCar

interface MainViewModelCallback {
    fun onFinishDownloadCars(realmCar: List<Car>)

    fun onErrorDownloadCars(error: String)
}