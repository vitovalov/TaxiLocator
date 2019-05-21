package com.vitovalov.taxilocator.ui.detail

import com.vitovalov.taxilocator.domain.bo.Vehicle

class VehicleMapPresenter(
    private val view: VehicleMapContract.View
) : VehicleMapContract.Presenter {

    private lateinit var vehicles: List<Vehicle>
    private var centralVehicle: Vehicle? = null

    override fun onVehiclesObtained(vehicles: List<Vehicle>, vehicleId: Int) {
        this.vehicles = vehicles
        this.centralVehicle = vehicles.firstOrNull { v -> v.id == vehicleId }
    }

    override fun onViewReady() {
        centralVehicle?.let { view.showVehiclesOnMap(vehicles, it) } ?: view.showError()
    }
}