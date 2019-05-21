package com.vitovalov.taxilocator.internal.di

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.vitovalov.taxilocator.data.*
import com.vitovalov.taxilocator.domain.VehiclesRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
class NetworkModule {
    @Provides
    fun provideVehiclesApi(retrofit: Retrofit): VehiclesApi = retrofit.create()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .apply {
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            addConverterFactory(GsonConverterFactory.create())
            baseUrl(NetworkConfig.API_URL)
            client(okHttpClient)
        }
        .build()

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, connectivityInterceptor: ConnectivityInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(connectivityInterceptor)
            .build()

    @Provides
    fun provideConnectivityInterceptor(appContext: Context): ConnectivityInterceptor = ConnectivityInterceptor(appContext)

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    fun provideMapper(): VehiclesMapper = VehiclesMapper()

    @Provides
    fun provideVehicleRepository(vehiclesApi: VehiclesApi, vehiclesMapper: VehiclesMapper): VehiclesRepository =
        VehiclesRepositoryImpl(vehiclesApi, vehiclesMapper)

}
