package com.vitovalov.taxilocator.ui.detail

import com.vitovalov.taxilocator.domain.bo.Vehicle

interface VehicleMapContract {

    interface View {
        fun showVehiclesOnMap(
            vehicles: List<Vehicle>,
            vehicle: Vehicle
        )
//        fun showError()
//        fun showVehicles(vehicles: List<Vehicle>)
//        fun addVehicles(vehicles: List<Vehicle>)
//        fun showLoading()
//        fun hideLoading()
//        fun hideError()
//        fun showVehicles(vehicles: List<Vehicle>)
    }

    interface Presenter {
        fun onVehiclesObtained(vehicles: List<Vehicle>, vehicle: Vehicle)
        fun onViewReady()
//        fun onStop()
//        fun onRetryClick()
    }
}