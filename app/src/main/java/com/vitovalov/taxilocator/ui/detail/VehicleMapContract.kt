package com.vitovalov.taxilocator.ui.detail

import com.vitovalov.taxilocator.domain.bo.Vehicle

interface VehicleMapContract {

    interface View {
        fun showVehiclesOnMap(
            vehicles: List<Vehicle>,
            centralVehicle: Vehicle
        )

        fun showError()
    }

    interface Presenter {
        fun onVehiclesObtained(vehicles: List<Vehicle>, vehicleId: Int)
        fun onViewReady()
    }
}