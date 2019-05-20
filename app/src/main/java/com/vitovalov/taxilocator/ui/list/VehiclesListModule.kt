package com.vitovalov.taxilocator.ui.list

import android.app.Activity
import com.vitovalov.taxilocator.di.PerActivity
import com.vitovalov.taxilocator.domain.GetVehiclesUseCase
import com.vitovalov.taxilocator.domain.VehiclesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named

@Module
abstract class VehiclesListModule {

    @Binds
    @PerActivity
    internal abstract fun provideView(activity: VehiclesListActivity): VehiclesListContract.View

    @Binds
    internal abstract fun provideActivity(activity: VehiclesListActivity): Activity

    @Module
    companion object {
        @Provides
        @PerActivity
        @JvmStatic
        internal fun provideGetVehiclesUseCase(
            vehiclesRepository: VehiclesRepository,
            @Named("observeOn") observeOn: Scheduler,
            @Named("subscribeOn") subscribeOn: Scheduler
        ): GetVehiclesUseCase = GetVehiclesUseCase(vehiclesRepository, observeOn, subscribeOn)

        @Provides
        @PerActivity
        @JvmStatic
        internal fun providePresenter(
            view: VehiclesListContract.View,
            getVehiclesUseCase: GetVehiclesUseCase
        ): VehiclesListContract.Presenter =
            VehiclesListPresenter(
                view,
                getVehiclesUseCase
            )

    }

}
