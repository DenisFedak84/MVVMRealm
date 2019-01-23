package com.fedak.denis.mymvvm.handler

import android.view.View
import com.fedak.denis.mymvvm.model.Car
import com.fedak.denis.mymvvm.model.RealmCar

interface DetailFragmentHandler {
    fun onFragmentItemClick (view: View, car : Car)
}