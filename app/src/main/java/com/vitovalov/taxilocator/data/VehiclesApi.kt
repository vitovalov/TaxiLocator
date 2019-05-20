package com.vitovalov.taxilocator.data

import com.vitovalov.taxilocator.data.dto.GetPoiResponseDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


//***REMOVED***

interface VehiclesApi {
    @GET("/")
    fun getVehiclesList(
        @Query("p1Lat") lat1: Float,
        @Query("p1Lon") lon1: Float,
        @Query("p2Lat") lat2: Float,
        @Query("p2Lon") lon2: Float
    ): Single<GetPoiResponseDto>
}