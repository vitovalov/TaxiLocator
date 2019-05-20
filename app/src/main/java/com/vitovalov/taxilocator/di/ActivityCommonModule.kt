package com.vitovalov.taxilocator.di

import android.app.Activity
import com.vitovalov.taxilocator.Navigator
import dagger.Module
import dagger.Provides

@Module
abstract class ActivityCommonModule {
    @Module
    companion object {
        @Provides
        @PerActivity
        @JvmStatic
        internal fun provideNavigator(activity: Activity): Navigator = Navigator.NavigatorImpl(activity)
    }
}