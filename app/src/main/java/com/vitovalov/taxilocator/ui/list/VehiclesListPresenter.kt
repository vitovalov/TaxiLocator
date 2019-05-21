package com.vitovalov.taxilocator.ui.list

import com.vitovalov.taxilocator.domain.GetVehiclesUseCase
import com.vitovalov.taxilocator.domain.bo.GetVehiclesResponse

class VehiclesListPresenter(
    private val view: VehiclesListContract.View,
    private val getVehiclesUseCase: GetVehiclesUseCase
) : VehiclesListContract.Presenter {
    override fun onViewReady() {
        requestData()
    }


    override fun onStop() {
        getVehiclesUseCase.clear()
    }

    override fun onRetryClick() {
        TODO("not implemented")
    }

    private fun requestData() =
        getVehiclesUseCase.execute(53.694865F, 9.757589F, 53.394655F, 10.099891F, ::handleSuccess, ::handleError)

    private fun handleError(throwable: Throwable) {
        view.showError()
    }

    private fun handleSuccess(getVehiclesResponse: GetVehiclesResponse) {
        val vehicles = getVehiclesResponse.vehiclesList
        view.showVehicles(vehicles)
        view.hideLoading()
    }
}