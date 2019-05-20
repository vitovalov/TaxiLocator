package com.vitovalov.taxilocator.data

import com.vitovalov.taxilocator.data.dto.GetPoiResponseDto
import com.vitovalov.taxilocator.domain.bo.GetVehiclesResponse
import com.vitovalov.taxilocator.domain.bo.Vehicle

class VehiclesMapper {

    fun map(getPoiResponseDto: GetPoiResponseDto): GetVehiclesResponse =
        GetVehiclesResponse(
            vehiclesDtoToBo(getPoiResponseDto)
        )

    private fun vehiclesDtoToBo(getVehiclesResponse: GetPoiResponseDto): List<Vehicle> =
        getVehiclesResponse.poiList.map {
            Vehicle(
                Pair(it.coordinate.latitude, it.coordinate.longitude),
                it.fleetType,
                it.heading,
                it.id
            )
        }

}