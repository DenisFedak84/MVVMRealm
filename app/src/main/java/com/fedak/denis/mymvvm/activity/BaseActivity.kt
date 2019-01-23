package com.fedak.denis.mymvvm.activity


import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.fedak.denis.mymvvm.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import io.realm.Realm
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject


open class BaseActivity : DaggerAppCompatActivity() {

    @set:Inject
    var viewModelProviderFactory: ViewModelProviderFactory? = null

    protected fun <T : ViewModel> getViewModel(target: FragmentActivity, viewModelClass: Class<T>): T {
        return ViewModelProviders.of(target, viewModelProviderFactory).get(viewModelClass)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            Realm.init(applicationContext)
    }
}