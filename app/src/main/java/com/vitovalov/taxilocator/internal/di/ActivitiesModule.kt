package com.vitovalov.taxilocator.internal.di

import com.vitovalov.taxilocator.ui.detail.VehicleMapActivity
import com.vitovalov.taxilocator.ui.detail.VehicleMapModule
import com.vitovalov.taxilocator.ui.list.VehiclesListActivity
import com.vitovalov.taxilocator.ui.list.VehiclesListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [VehiclesListModule::class, ActivityCommonModule::class])
    abstract fun contributeVehiclesListActivity(): VehiclesListActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [VehicleMapModule::class, ActivityCommonModule::class])
    abstract fun contributeMapsActivity(): VehicleMapActivity
}
