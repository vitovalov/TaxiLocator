package com.vitovalov.taxilocator.di

import com.vitovalov.taxilocator.ui.detail.VehicleMapActivity
import com.vitovalov.taxilocator.ui.detail.VehicleMapModule
import com.vitovalov.taxilocator.ui.list.VehiclesListActivity
import com.vitovalov.taxilocator.ui.list.VehiclesListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [VehiclesListModule::class])
    abstract fun contributeVehiclesListActivity(): VehiclesListActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [VehicleMapModule::class])
    abstract fun contributeMapsActivity(): VehicleMapActivity
}
