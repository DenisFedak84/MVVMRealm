package com.fedak.denis.mymvvm.adapter

import android.support.v7.util.DiffUtil
import com.fedak.denis.mymvvm.model.Car

class CarDiffUtilCallback(var oldList: List<Car>, var newList: List<Car>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
      return oldList.size
    }

    override fun getNewListSize(): Int {
      return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCar = oldList[oldItemPosition]
        val newCar = newList[newItemPosition]
        return oldCar.id == newCar.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCar = oldList[oldItemPosition]
        val newCar = newList[newItemPosition]
        return oldCar.thumbnailUrl == newCar.thumbnailUrl
        && oldCar.url == newCar.url
        && oldCar.title == newCar.title
    }
}