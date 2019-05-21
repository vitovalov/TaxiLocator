package com.vitovalov.taxilocator.ui

import android.app.Activity
import com.vitovalov.taxilocator.domain.bo.Vehicle
import com.vitovalov.taxilocator.ui.detail.VehicleMapActivity


class Navigator(
        private val activity: Activity
) {
    fun navigateToDetail(vehicles: List<Vehicle>, vehicleId: Int) {
        activity.startActivity(VehicleMapActivity.getCallingIntent(activity, vehicles, vehicleId))
    }
}