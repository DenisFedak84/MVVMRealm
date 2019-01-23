package com.fedak.denis.mymvvm.fragment

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import com.fedak.denis.mymvvm.R
import com.fedak.denis.mymvvm.databinding.FragmentDetailBinding
import com.fedak.denis.mymvvm.handler.DetailFragmentHandler
import com.fedak.denis.mymvvm.model.Car
import com.fedak.denis.mymvvm.utils.DETAIL_ID
import com.fedak.denis.mymvvm.utils.toast
import com.fedak.denis.mymvvm.viewmodel.DetailViewModel
import io.reactivex.disposables.CompositeDisposable

class DetailFragment : BaseFragment(), DetailFragmentHandler {

    lateinit var binding: FragmentDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var subscriptions: CompositeDisposable

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel = getViewModel(this.activity!!, DetailViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        var id = arguments?.getInt(DETAIL_ID) ?: 1
        subscriptions = CompositeDisposable()
        detailViewModel.setSuscriptions(subscriptions)
        getDataFromDB(id)
    }

    private fun getDataFromDB(id: Int) {
        val data = detailViewModel.loadCar(id)

        data.observe(this, Observer {car->
            binding.model = car
            binding.handler = this
        })

        var error = detailViewModel.getError()

        error.observe(this, Observer { message ->
            activity?.toast(message!!)
        })
    }

    override fun onFragmentItemClick(view: View, car: Car) {
        when (view.id) {
            R.id.detailImageView -> {

                val action = DetailFragmentDirections.actionDetailFragmentToDetailImageFragment()
                action.urlArg = car.thumbnailUrl
                findNavController(view).navigate(action)
            }
            R.id.detailTextView -> {

                val action = DetailFragmentDirections.actionDetailFragmentToDetailTextFragment()
                action.textArg = car.title
                findNavController(view).navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        subscriptions.clear()
        subscriptions.dispose()
    }
}