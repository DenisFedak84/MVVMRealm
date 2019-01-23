package com.fedak.denis.mymvvm.db

//
//@Dao
//interface CarDao {
//
//    @Query("SELECT * FROM realmCar")
//    fun getAll(): List<RealmCar>
//
//    @Query("SELECT * FROM realmCar WHERE id = :id")
//    fun getById(id: Long): RealmCar
//
//    @Delete
//    fun delete(realmCar: RealmCar)
//
//    @Query("DELETE FROM realmCar")
//    fun deleteAll()
//
//    @Insert (onConflict = REPLACE)
//    fun insert(vararg realmCar: RealmCar)
//
//    @Update
//    fun update(vararg realmCar: RealmCar)
//
//}