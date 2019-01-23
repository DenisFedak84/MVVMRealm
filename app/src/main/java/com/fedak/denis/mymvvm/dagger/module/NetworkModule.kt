package com.fedak.denis.mymvvm.dagger.module

import android.content.Context
import com.fedak.denis.mymvvm.mappers.CarMapper
import com.fedak.denis.mymvvm.network.CarApi
import com.fedak.denis.mymvvm.utils.BASE_URL
import dagger.Module
import dagger.Provides
import io.reactivex.annotations.Nullable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmConfiguration
import org.jetbrains.annotations.NotNull
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.Executors
import javax.inject.Singleton



/**
 * Module which provides all required dependencies about network
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
class NetworkModule {

    /**
     * Provides the RealmCar service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the RealmCar service implementation.
     */
    @Provides
    internal fun providePostApi(retrofit: Retrofit): CarApi {
        return retrofit.create(CarApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }

//    @Provides
//    internal fun provideCarDao(context : Context): CarDao {
//        return Room.databaseBuilder(context, AppDatabase::class.java, "realmCars").build().carDao()
//    }

//    RealmModule(Context context){
//        Realm.init(context);
//    }


    @Provides
    internal fun provideRealm(realmConfiguration: RealmConfiguration): Realm {

        var realm: Realm
            try {
                realm = Realm.getDefaultInstance()
            } catch (e: Exception) {
                realm = Realm.getInstance(realmConfiguration)
            }

        return realm
    }


    @Provides
    internal fun provideRealmConfiguration(): RealmConfiguration {

        return RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
    }

    @Provides
    internal fun provideCarMapper() : CarMapper {
        return CarMapper()
    }
}