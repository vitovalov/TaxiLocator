package com.vitovalov.taxilocator.ui.detail

import com.vitovalov.taxilocator.domain.bo.Vehicle
import com.vitovalov.taxilocator.internal.MvpPresenter
import com.vitovalov.taxilocator.internal.MvpView

interface VehicleMapContract {

    interface View: MvpView {
        fun showVehiclesOnMap(
            vehicles: List<Vehicle>,
            centralVehicle: Vehicle
        )

        fun showError()
        fun cleanUp()
    }

    interface Presenter: MvpPresenter<View> {
        fun onVehiclesObtained(vehicles: List<Vehicle>, vehicleId: Int)
    }
}