package com.vitovalov.taxilocator

import android.app.Activity
import com.vitovalov.taxilocator.domain.bo.Vehicle
import com.vitovalov.taxilocator.ui.detail.VehicleMapActivity

interface Navigator {
    fun navigateToDetail(vehicle: Vehicle)

    class NavigatorImpl(
        private val activity: Activity
    ): Navigator {
        override fun navigateToDetail(vehicle: Vehicle) {
            activity.startActivity(VehicleMapActivity.getCallingIntent(activity, vehicle))
        }
    }
}