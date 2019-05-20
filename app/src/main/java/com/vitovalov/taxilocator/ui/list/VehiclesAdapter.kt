package com.vitovalov.taxilocator.ui.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vitovalov.taxilocator.Navigator
import com.vitovalov.taxilocator.R
import com.vitovalov.taxilocator.domain.bo.Vehicle
import com.vitovalov.taxilocator.extensions.inflate
import kotlinx.android.synthetic.main.adapter_vehicle.view.*

class VehiclesAdapter(private val navigator: Navigator) : RecyclerView.Adapter<VehicleViewHolder>() {

    private var vehicles: MutableList<Vehicle> = ArrayList()

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int) = position.toLong()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder =
        VehicleViewHolder(parent.inflate(R.layout.adapter_vehicle))


    override fun getItemCount() = vehicles.size

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        holder.bind(vehicles, vehicles[position], navigator)
    }

    fun setVehicles(vehicles: List<Vehicle>) {
        this.vehicles = vehicles.toMutableList()
        notifyDataSetChanged()
    }
}

class VehicleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(vehicles: List<Vehicle>, vehicle: Vehicle, navigator: Navigator) = with(itemView) {
        adapter_vehicle_fleet_type.text = vehicle.fleetType
        adapter_vehicle_heading.text = vehicle.heading.toString()
        setOnClickListener { navigator.navigateToDetail(vehicles, vehicle) }
    }
}