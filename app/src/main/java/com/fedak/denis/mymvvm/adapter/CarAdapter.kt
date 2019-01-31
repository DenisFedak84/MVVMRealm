package com.fedak.denis.mymvvm.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fedak.denis.mymvvm.R
import com.fedak.denis.mymvvm.activity.MainActivity
import com.fedak.denis.mymvvm.databinding.CarAdapterItemBinding
import com.fedak.denis.mymvvm.model.Car
import com.fedak.denis.mymvvm.model.RealmCar


class CarAdapter : RecyclerView.Adapter<CarHolder>() {

    var cars: List<Car> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarHolder {
        val binding: CarAdapterItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.car_adapter_item, parent, false)
        return CarHolder(binding, parent.context)
    }

    override fun getItemCount() = cars.size

    override fun onBindViewHolder(holder: CarHolder, position: Int) {
        MainActivity.LOG.info("Position = " + position.toString())
        holder.bind(cars[position])
    }

}