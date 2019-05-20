package com.vitovalov.taxilocator.domain

import com.vitovalov.taxilocator.domain.bo.GetVehiclesResponse
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables

class GetVehiclesUseCase(
    private val vehiclesRepository: VehiclesRepository,
    private val observeOn: Scheduler,
    private val subscribeOn: Scheduler
) {

    private var subscription: Disposable = Disposables.empty()

    fun execute(
        lat1: Float,
        lon1: Float,
        lat2: Float,
        lon2: Float,
        onComplete: (GetVehiclesResponse) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        subscription = vehiclesRepository.getVehicles(lat1, lon1, lat2, lon2)
            .subscribeOn(subscribeOn)
            .observeOn(observeOn)
            .subscribe(onComplete, onError)
    }

    fun clear() {
        if (!subscription.isDisposed) {
            subscription.dispose()
        }
    }
}