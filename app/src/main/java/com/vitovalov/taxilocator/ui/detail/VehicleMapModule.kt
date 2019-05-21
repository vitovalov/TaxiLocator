package com.vitovalov.taxilocator.ui.detail

import android.app.Activity
import com.vitovalov.taxilocator.internal.di.PerActivity
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class VehicleMapModule {

    @Binds
    @PerActivity
    internal abstract fun provideView(activity: VehicleMapActivity): VehicleMapContract.View

    @Binds
    internal abstract fun provideActivity(activity: VehicleMapActivity): Activity

    @Module
    companion object {

        @Provides
        @PerActivity
        @JvmStatic
        internal fun providePresenter(
            view: VehicleMapContract.View
        ): VehicleMapContract.Presenter =
            VehicleMapPresenter(view)
    }
}