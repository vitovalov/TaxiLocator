package com.vitovalov.taxilocator.ui.list

import com.vitovalov.taxilocator.domain.bo.Vehicle

interface VehiclesListContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showVehicles(vehicles: List<Vehicle>)
        fun showGenericError()
        fun showNoInternetError()
        fun hideError()
    }

    interface Presenter {
        fun onViewReady()
        fun onStop()
        fun onRetryClick()
    }
}