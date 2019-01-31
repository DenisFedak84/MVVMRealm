package com.fedak.denis.mymvvm.activity

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.fedak.denis.mymvvm.R
import com.fedak.denis.mymvvm.databinding.ActivityMainBinding
import com.fedak.denis.mymvvm.model.Car
import com.fedak.denis.mymvvm.utils.toast
import com.fedak.denis.mymvvm.viewmodel.MainViewModel
import io.reactivex.disposables.CompositeDisposable
import java.util.logging.Logger


class MainActivity : BaseActivity() {

    private lateinit var mainViewModel: MainViewModel
    private var subscriptions: CompositeDisposable = CompositeDisposable()
    lateinit var testCars: List<Car>

    companion object {
        val LOG = Logger.getLogger(MainActivity::class.java.name)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.postList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        mainViewModel = getViewModel(this, MainViewModel::class.java)
        mainViewModel.setSuscriptions(subscriptions)

        binding.viewModel = mainViewModel

        val data = mainViewModel.getCars()
        val error = mainViewModel.getError()

        data?.observe(this, Observer { cars ->
            testCars = cars!!
            mainViewModel.finishLoading()
            mainViewModel.refreshAdapter(cars)
        })

        error.observe(this, Observer { message ->
            mainViewModel.finishLoading()
            this.toast(message!!)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        subscriptions.clear()
        subscriptions.dispose()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.refresh_list -> {
                if(testCars.isNotEmpty()){
                    mainViewModel.refreshAdapter(testCars)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
