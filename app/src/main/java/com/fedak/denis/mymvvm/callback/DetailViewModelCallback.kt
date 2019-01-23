package com.fedak.denis.mymvvm.callback

import com.fedak.denis.mymvvm.model.Car
import com.fedak.denis.mymvvm.model.RealmCar

interface DetailViewModelCallback {

    fun onFinishDownloadCar(car: Car)

    fun onErrorDownloadCar(error: String)
}