package com.vitovalov.taxilocator

import android.app.Activity
import com.vitovalov.taxilocator.domain.bo.Vehicle
import com.vitovalov.taxilocator.ui.detail.VehicleMapActivity

interface Navigator {
    fun navigateToDetail(vehicles: List<Vehicle>, vehicle: Vehicle)

    class NavigatorImpl(
        private val activity: Activity
    ): Navigator {
        override fun navigateToDetail(vehicles: List<Vehicle>, vehicle: Vehicle) {
            activity.startActivity(VehicleMapActivity.getCallingIntent(activity, vehicles, vehicle))
        }
    }
}