package com.vitovalov.taxilocator.ui.list

import com.vitovalov.taxilocator.domain.GetVehiclesUseCase
import com.vitovalov.taxilocator.domain.bo.GetVehiclesResponse
import com.vitovalov.taxilocator.error.GenericException
import com.vitovalov.taxilocator.error.NoConnectivityException

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
        view.hideError()
        requestData()
    }

    private fun requestData() {
        view.showLoading()
        getVehiclesUseCase.execute(53.694865F, 9.757589F, 53.394655F, 10.099891F, ::handleSuccess, ::handleError)
    }

    fun handleError(throwable: Throwable) {
        when (throwable) {
            is NoConnectivityException -> view.showNoInternetError()
            is GenericException -> view.showGenericError()
        }
        view.hideLoading()
    }

    fun handleSuccess(getVehiclesResponse: GetVehiclesResponse) {
        val vehicles = getVehiclesResponse.vehiclesList
        view.showVehicles(vehicles)
        view.hideLoading()
    }
}