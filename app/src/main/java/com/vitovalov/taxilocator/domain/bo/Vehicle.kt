package com.vitovalov.taxilocator.domain.bo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Vehicle(
    val coordinate: Pair<Double, Double>,
    val fleetType: String,
    val heading: Double,
    val id: Int
): Parcelable