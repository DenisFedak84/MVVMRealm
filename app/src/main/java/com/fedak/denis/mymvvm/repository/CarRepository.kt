package com.fedak.denis.mymvvm.repository


import com.fedak.denis.mymvvm.activity.MainActivity.Companion.LOG
import com.fedak.denis.mymvvm.mappers.CarMapper
import com.fedak.denis.mymvvm.model.Car
import com.fedak.denis.mymvvm.model.RealmCar
import com.fedak.denis.mymvvm.network.CarApi
import com.vicpin.krealmextensions.*
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmList
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class CarRepository @Inject constructor(
    private var carApi: CarApi,
    private var realmn: Realm,
    private var mapper: CarMapper
) {

    fun getCars(): Flowable<List<Car>>? {
        val db = getDataFromDB()
        val server = getDataFromServerFlowable()
//        val mock = getDataFromMock()
        return Flowable.merge(db,server).take(1) //  return db.mergeWith(server).take(1)
    }

    private fun getDataFromServerFlowable(): Flowable<List<Car>> {
        val server = carApi.getPostsFlowable()
            .map { car -> LOG.info("SERVER")
            car
        }
        return server
    }

    private fun getDataFromDB(): Flowable<List<Car>> {
        return RealmCar().queryAllAsFlowable()
            .map { cars -> LOG.info("DB")
                mapper.convertListRealmCarToListCar(cars) }
            .filter { car -> car.isNotEmpty() }
    }

    fun saveCarsInDB(cars: List<Car>) {
        val realm = Realm.getDefaultInstance()
        realm.use { realm ->
            realm.beginTransaction()
            val carsList = RealmList<RealmCar>()
            carsList.addAll(mapper.convertListCarToListRealmCar(cars))
            realm.insertOrUpdate(carsList)
            realm.commitTransaction()
        }
    }

    public fun getDataFromMock(): List<Car> {
        val cars: MutableList<Car> = mutableListOf()
        val car = Car(0, 0, "Test", "Test", "Test")
        val car2 = Car(2, 2, "Test2", "Test2", "Test2")
        val car3 = Car(3, 3, "Test3", "Test3", "Test3")
        cars.add(car)
        cars.add(car2)
        cars.add(car3)
        LOG.info("MOCK")
        return cars
    }

//    fun getDataById(id: Int): Flowable<Car> {
//        val realm = Realm.getDefaultInstance()
//        realm.beginTransaction()
//        var car = realm.where(RealmCar::class.java).equalTo("id", id).findFirst()
//        car = realm.copyFromRealm(car!!)
//        realm.commitTransaction()
//        var newCar = mapper.convertRealmCarToCar(car!!)
//        return Flowable.just(newCar)
//    }

    fun getDataById(id: Int): Flowable <Car> {
        return RealmCar().queryAsFlowable {equalToValue("id", id)}.map{car-> mapper.convertRealmCarToCar(car[0])}
    }
}






