package com.vitovalov.taxilocator.ui.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.vitovalov.taxilocator.Navigator
import com.vitovalov.taxilocator.R
import com.vitovalov.taxilocator.domain.bo.Vehicle
import com.vitovalov.taxilocator.extensions.hide
import com.vitovalov.taxilocator.extensions.show
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_vehicles_list.*
import kotlinx.android.synthetic.main.error_layout.*
import kotlinx.android.synthetic.main.loading_layout.*
import javax.inject.Inject

class VehiclesListActivity : AppCompatActivity(), VehiclesListContract.View {

    @Inject
    lateinit var presenter: VehiclesListContract.Presenter
    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicles_list)

        initViews()
        presenter.onViewReady()
    }

    private fun initViews() {
        with(activity_vehicles_list_rv) {
            adapter = VehiclesAdapter(navigator)
            val linearLayoutManager = LinearLayoutManager(this@VehiclesListActivity)
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
        }

        error_layout_retry.setOnClickListener {
            presenter.onRetryClick()
        }
    }

    override fun showVehicles(vehicles: List<Vehicle>) {
        activity_vehicles_list_rv.show()
        error_layout.hide()

        val vehiclesAdapter = activity_vehicles_list_rv.adapter as? VehiclesAdapter
        vehiclesAdapter?.setVehicles(vehicles)
    }

    override fun showGenericError() {
        error_layout_text_error.text = getString(R.string.error_error_generic)
        error_layout.show()
    }

    override fun showNoInternetError() {
        error_layout_text_error.text = getString(R.string.error_error_no_internet)
        error_layout.show()
        loading_layout_loading_pb.hide()
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

