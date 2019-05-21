package com.vitovalov.taxilocator.internal.di

import com.vitovalov.taxilocator.TaxiLocatorApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        ActivitiesModule::class
    ]
)


interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: TaxiLocatorApp): Builder

        fun build(): AppComponent
    }

    fun inject(application: TaxiLocatorApp)
}
