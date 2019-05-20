package com.vitovalov.taxilocator.ui.detail

import com.vitovalov.taxilocator.domain.bo.Vehicle

class VehicleMapPresenter(
    private val view: VehicleMapContract.View
) : VehicleMapContract.Presenter {

    lateinit var vehicles: List<Vehicle>
    lateinit var vehicle: Vehicle

    override fun onVehiclesObtained(vehicles: List<Vehicle>, vehicle: Vehicle) {
        this.vehicles = vehicles
        this.vehicle = vehicle
    }

    override fun onViewReady() {
        view.showVehiclesOnMap(vehicles, vehicle)
    }
//    override fun onStop() {
//        getVehiclesUseCase.clear()
//    }
}