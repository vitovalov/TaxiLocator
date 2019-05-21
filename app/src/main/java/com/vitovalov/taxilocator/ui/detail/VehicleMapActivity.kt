package com.vitovalov.taxilocator.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.vitovalov.taxilocator.Navigator
import com.vitovalov.taxilocator.R
import com.vitovalov.taxilocator.domain.bo.Vehicle
import dagger.android.AndroidInjection
import java.util.*
import javax.inject.Inject

class VehicleMapActivity : AppCompatActivity(), OnMapReadyCallback, VehicleMapContract.View {

    private lateinit var map: GoogleMap

    @Inject
    lateinit var presenter: VehicleMapContract.Presenter
    @Inject
    lateinit var navigator: Navigator

    companion object {
        fun getCallingIntent(context: Context, vehicles: List<Vehicle>, vehicleId: Int): Intent {
            val intent = Intent(context, VehicleMapActivity::class.java)
            intent.putExtra(ARG_CENTER_VEHICLE_ID, vehicleId)
            intent.putParcelableArrayListExtra(ARG_VEHICLES, ArrayList(vehicles))
            return intent
        }

        const val ARG_CENTER_VEHICLE_ID = "ARG_CENTER_VEHICLE_ID"
        const val ARG_VEHICLES = "ARG_VEHICLES"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        getExtras()
    }

    private fun getExtras() {
        val extras = intent
        extras?.apply {
            val vehicles: ArrayList<Vehicle> = getParcelableArrayListExtra(ARG_VEHICLES)
            val vehicleId = extras.getIntExtra(ARG_CENTER_VEHICLE_ID, -1)
            presenter.onVehiclesObtained(vehicles, vehicleId)
        }
    }

    override fun showError() {
        TODO("not implemented")
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        map.setOnMapLoadedCallback {
            presenter.onViewReady()
        }
    }

    override fun showVehiclesOnMap(
        vehicles: List<Vehicle>,
        centralVehicle: Vehicle
    ) {
        val pointCenter = LatLng(centralVehicle.coordinate.first, centralVehicle.coordinate.second)

        val bounds = LatLngBounds.Builder()
        for (it in vehicles) {
            val point = LatLng(it.coordinate.first, it.coordinate.second)
            bounds.include(point)
            map.addMarker(MarkerOptions().position(point).title("Marker in ${it.fleetType}"))
        }
//        map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 100))
        map.addMarker(MarkerOptions().position(pointCenter).title("Marker in ${centralVehicle.fleetType}"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(pointCenter, 15F))
    }

    override fun onStop() {
        super.onStop()

        map.clear()
    }
}
