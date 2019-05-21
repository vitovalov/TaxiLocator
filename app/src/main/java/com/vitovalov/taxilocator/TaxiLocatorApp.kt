package com.vitovalov.taxilocator

import android.app.Activity
import android.app.Application
import android.content.Context
import com.vitovalov.taxilocator.internal.di.AppComponent
import com.vitovalov.taxilocator.internal.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class TaxiLocatorApp: Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    private lateinit var component: AppComponent

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun onCreate() {
        super.onCreate()

        initDagger()
    }

    private fun initDagger() {
        component = DaggerAppComponent
            .builder()
            .application(this)
            .build()

        component.inject(this)
    }


}