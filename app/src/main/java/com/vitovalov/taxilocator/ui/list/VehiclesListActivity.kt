package com.vitovalov.taxilocator.ui.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.vitovalov.taxilocator.R
import com.vitovalov.taxilocator.extensions.hide
import com.vitovalov.taxilocator.extensions.show
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_vehicles_list.*
import kotlinx.android.synthetic.main.error_layout.*
import kotlinx.android.synthetic.main.loading_layout.*
import javax.inject.Inject
import androidx.recyclerview.widget.RecyclerView
import com.vitovalov.taxilocator.Navigator
import com.vitovalov.taxilocator.domain.bo.Vehicle

class VehiclesListActivity : AppCompatActivity(), VehiclesListContract.View {

    @Inject
    lateinit var presenter: VehiclesListContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicles_list)

        initViews()
        presenter.onViewReady()
    }

    private fun initViews() {
        with(activity_vehicles_list_rv) {
            adapter = VehiclesAdapter(Navigator.NavigatorImpl(this@VehiclesListActivity)) // TODO
            val linearLayoutManager = LinearLayoutManager(this@VehiclesListActivity)
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
        }
    }
    override fun showVehicles(vehicles: List<Vehicle>) {
        activity_vehicles_list_rv.show()
        error_layout.hide()

        val vehiclesAdapter = activity_vehicles_list_rv.adapter as? VehiclesAdapter
        vehiclesAdapter?.setVehicles(vehicles)
    }

    override fun showError() {
        error_layout.show()
        loading_layout_loading_pb.hide() // TODO refactor all views camelcase if google convention says so
        activity_vehicles_list_rv.hide()
    }

    override fun hideError() {
        error_layout.hide()
    }

    override fun showLoading() {
        loading_layout_loading_pb.show()
    }

    override fun hideLoading() {
        loading_layout_loading_pb.hide()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }
}

