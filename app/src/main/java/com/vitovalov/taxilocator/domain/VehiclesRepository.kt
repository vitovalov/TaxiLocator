package com.vitovalov.taxilocator.domain

import com.vitovalov.taxilocator.domain.bo.GetVehiclesResponse
import io.reactivex.Single

interface VehiclesRepository {
    fun getVehicles(lat1: Float, lon1: Float, lat2: Float, lon2: Float): Single<GetVehiclesResponse>
}