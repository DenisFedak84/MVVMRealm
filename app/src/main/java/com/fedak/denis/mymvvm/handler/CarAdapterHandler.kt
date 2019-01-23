package com.fedak.denis.mymvvm.handler

import com.fedak.denis.mymvvm.model.Car
import com.fedak.denis.mymvvm.model.RealmCar

 interface CarAdapterHandler {
     fun onItemClick(car: Car)
}