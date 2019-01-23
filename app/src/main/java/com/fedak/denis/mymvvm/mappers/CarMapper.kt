package com.fedak.denis.mymvvm.mappers

import com.fedak.denis.mymvvm.model.Car
import com.fedak.denis.mymvvm.model.RealmCar
import io.realm.RealmModel

class CarMapper {

    fun convertRealmCarToCar(realmCar: RealmCar): Car {
        return Car(realmCar.albumId, realmCar.id, realmCar.title, realmCar.url, realmCar.thumbnailUrl)
    }

    fun convertCarToRealmCar(car:Car) : RealmCar{
        val realmCar = RealmCar()
        realmCar.albumId = car.albumId
        realmCar.id = car.id
        realmCar.title = car.title
        realmCar.url = car.url
        realmCar.thumbnailUrl = car.thumbnailUrl
        return realmCar
    }

    fun convertListRealmCarToListCar(realmCars: List<RealmCar>): List<Car> {
        val cars : MutableList<Car> = ArrayList()
        realmCars.forEach {
            cars.add(convertRealmCarToCar(it))
        }
        return cars
    }

    fun convertListCarToListRealmCar(cars: List<Car>): List<RealmCar> {
        val realmCars : MutableList<RealmCar> = ArrayList()
        cars.forEach{
            realmCars.add(convertCarToRealmCar(it))
        }
        return realmCars
    }
}