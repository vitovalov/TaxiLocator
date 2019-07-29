package com.vitovalov.taxilocator.ui.detail

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.vitovalov.taxilocator.R
import com.vitovalov.taxilocator.domain.bo.Vehicle
import com.vitovalov.taxilocator.ui.Navigator
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
        setContentView(com.vitovalov.taxilocator.R.layout.activity_maps)

        val mapFragment = supportFragmentManager
            .findFragmentById(com.vitovalov.taxilocator.R.id.map) as SupportMapFragment
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
        Toast.makeText(this, "Error in ${this.javaClass}", Toast.LENGTH_SHORT).show()
    }

    override fun cleanUp() {
        map.clear()
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
        val taxiFreeIcon = bitmapDescriptorFromVector(R.drawable.ic_local_taxi_green_24dp)
        val taxiBusyIcon = bitmapDescriptorFromVector(R.drawable.ic_local_taxi_red_24dp)
        val centralIcon = bitmapDescriptorFromVector(R.drawable.ic_local_taxi_blue_24dp)

        val bounds = LatLngBounds.Builder()
        for (it in vehicles) {
            val point = LatLng(it.coordinate.first, it.coordinate.second)
            bounds.include(point)
            map.addMarker(
                MarkerOptions().position(point).title("Taxi num. ${it.id}")
                    .icon(if ("TAXI" == it.fleetType) taxiFreeIcon else taxiBusyIcon)
            )
        }
        map.addMarker(
            MarkerOptions().position(pointCenter).title("Taxi num. ${centralVehicle.id}").icon(
                centralIcon
            )
        )
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(pointCenter, 15F))
    }

    private fun bitmapDescriptorFromVector(vectorResId: Int): BitmapDescriptor {
        val vectorDrawable = ContextCompat.getDrawable(this, vectorResId)
        vectorDrawable!!.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)
        val bitmap =
            Bitmap.createBitmap(vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    override fun onStop() {
        super.onStop()

        presenter.onStop()
    }
}
