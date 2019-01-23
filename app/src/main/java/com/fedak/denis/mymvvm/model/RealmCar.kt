package com.fedak.denis.mymvvm.model

import io.reactivex.annotations.Nullable
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

//@Entity
//data class RealmCar  (
//    var albumId: Int,
//    @field:PrimaryKey
//    var id: Int,
//    var title: String,
//    var url: String,
//    var thumbnailUrl: String
//    )

//@RealmClass
open class RealmCar : RealmObject() {
    var albumId: Int = 0
    @PrimaryKey
    var id: Int = 0
    var title: String = ""
    var url: String = ""
    var thumbnailUrl: String = ""


}
