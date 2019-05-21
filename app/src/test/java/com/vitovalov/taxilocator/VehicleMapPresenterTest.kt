package com.vitovalov.taxilocator

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.vitovalov.taxilocator.domain.bo.Vehicle
import com.vitovalov.taxilocator.ui.detail.VehicleMapContract
import com.vitovalov.taxilocator.ui.detail.VehicleMapPresenter
import org.junit.Test

class VehicleMapPresenterTest {

    private var mockView: VehicleMapContract.View = mock()

    private val presenter by lazy {
        VehicleMapPresenter(
                mockView
        )
    }

    @Test
    fun `should show error on view ready when no data obtained`() {
        presenter.onViewReady()

        verify(mockView).showError()
    }

    @Test
    fun `should show error on view ready when central vehicle not found`() {
        presenter.onVehiclesObtained(emptyList(), 1)
        presenter.onViewReady()

        verify(mockView).showError()
    }

    @Test
    fun `should show vehicles on map on view ready`() {
        presenter.onVehiclesObtained(givenSomeVehicles(), 3)
        presenter.onViewReady()

        verify(mockView).showVehiclesOnMap(any(), any())
    }

    private fun givenSomeVehicles(): List<Vehicle> =
            listOf(
                    Vehicle(
                            Pair(0.0, 0.0),
                            "", 1.0, 3
                    )
            )
}
