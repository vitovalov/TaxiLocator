package com.vitovalov.taxilocator.ui.list

import com.vitovalov.taxilocator.domain.bo.Vehicle

interface VehiclesListContract {

    interface View {
        fun showError()
//        fun showVehicles(vehicles: List<Vehicle>)
//        fun addVehicles(vehicles: List<Vehicle>)
        fun showLoading()
        fun hideLoading()
        fun hideError()
        fun showVehicles(vehicles: List<Vehicle>)
    }

    interface Presenter {
        fun onViewReady()
        fun onStop()
        fun onRetryClick()
    }
}