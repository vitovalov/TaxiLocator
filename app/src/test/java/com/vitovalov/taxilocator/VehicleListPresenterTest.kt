package com.vitovalov.taxilocator

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.vitovalov.taxilocator.domain.GetVehiclesUseCase
import com.vitovalov.taxilocator.domain.bo.GetVehiclesResponse
import com.vitovalov.taxilocator.domain.bo.Vehicle
import com.vitovalov.taxilocator.internal.error.GenericException
import com.vitovalov.taxilocator.internal.error.NoConnectivityException
import com.vitovalov.taxilocator.ui.list.VehiclesListContract
import com.vitovalov.taxilocator.ui.list.VehiclesListPresenter
import org.junit.Test

class VehicleListPresenterTest {

    private var mockView: VehiclesListContract.View = mock()
    private var mockGetVehiclesUseCase: GetVehiclesUseCase = mock()

    private val presenter by lazy {
        VehiclesListPresenter(
                mockView,
                mockGetVehiclesUseCase
        )
    }

    @Test
    fun `should show loading when fetching vehicles`() {
        presenter.onViewReady()

        verify(mockView).showLoading()
    }

    @Test
    fun `should fetch data on view ready`() {
        presenter.onViewReady()

        verify(mockGetVehiclesUseCase).execute(any(), any(), any(), any(), any(), any())
    }

    @Test
    fun `should fetch data on retry click`() {
        presenter.onRetryClick()

        verify(mockGetVehiclesUseCase).execute(any(), any(), any(), any(), any(), any())
    }

    @Test
    fun `should hide error view on retry click`() {
        presenter.onRetryClick()

        verify(mockView).hideError()
    }

    @Test
    fun `should show loading on retry click`() {
        presenter.onRetryClick()

        verify(mockView).showLoading()
    }

    @Test
    fun `should unsubscribe on stop`() {
        presenter.onStop()

        verify(mockGetVehiclesUseCase).clear()
    }

    @Test
    fun `should show error view given network error`() {
        presenter.handleError(NoConnectivityException())

        verify(mockView).showNoInternetError()
    }

    @Test
    fun `should show error view given generic error`() {
        presenter.handleError(GenericException())

        verify(mockView).showGenericError()
    }

    @Test
    fun `should hide loading even when empty response`() {
        presenter.handleSuccess(givenEmptyResponse())

        verify(mockView).hideLoading()
    }

    @Test
    fun `should show list when first response has data`() {
        val getVehiclesResponse = givenSuccessResponse()
        presenter.handleSuccess(getVehiclesResponse)

        verify(mockView).showVehicles(getVehiclesResponse.vehiclesList)
        verify(mockView).hideLoading()
    }

    private fun givenSuccessResponse(): GetVehiclesResponse =
            GetVehiclesResponse(
                    listOf(
                            Vehicle(
                                    Pair(0.0, 0.0),
                                    "", 1.0, 1
                            )
                    )
            )

    private fun givenEmptyResponse(): GetVehiclesResponse = GetVehiclesResponse(emptyList())
}
