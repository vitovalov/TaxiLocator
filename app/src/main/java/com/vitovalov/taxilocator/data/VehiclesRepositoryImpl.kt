package com.vitovalov.taxilocator.data

import com.vitovalov.taxilocator.domain.VehiclesRepository
import com.vitovalov.taxilocator.domain.bo.GetVehiclesResponse
import io.reactivex.Single

class VehiclesRepositoryImpl(private val vehiclesApi: VehiclesApi, private val vehiclesMapper: VehiclesMapper) :
    VehiclesRepository {
    override fun getVehicles(lat1: Float, lon1: Float, lat2: Float, lon2: Float): Single<GetVehiclesResponse> =
        vehiclesApi.getVehiclesList(lat1, lon1, lat2, lon2).map { vehiclesMapper.map(it) }

}