package com.vitovalov.taxilocator.data.dto


data class PoiDto(
    val coordinate: CoordinateDto,
    val fleetType: String,
    val heading: Double,
    val id: Int
)