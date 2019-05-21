package com.vitovalov.taxilocator.ui.list

import com.vitovalov.taxilocator.domain.bo.Vehicle
import com.vitovalov.taxilocator.internal.MvpPresenter
import com.vitovalov.taxilocator.internal.MvpView
import com.vitovalov.taxilocator.ui.detail.VehicleMapContract

interface VehiclesListContract {

    interface View: MvpView {
        fun showLoading()
        fun hideLoading()
        fun showVehicles(vehicles: List<Vehicle>)
        fun showGenericError()
        fun showNoInternetError()
        fun hideError()
    }

    interface Presenter: MvpPresenter<VehicleMapContract.View> {
        fun onRetryClick()
    }
}