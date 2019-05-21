package com.vitovalov.taxilocator.ui.list

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.vitovalov.taxilocator.R
import com.vitovalov.taxilocator.domain.bo.Vehicle
import com.vitovalov.taxilocator.internal.extensions.inflate
import com.vitovalov.taxilocator.ui.Navigator
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
        if (vehicle.fleetType.equals("TAXI")) {
            adapter_vehicle_name.setTextColor(getColor(itemView.context, R.color.available_taxi))
        } else if (vehicle.fleetType.equals("POOLING")) {
            adapter_vehicle_name.setTextColor(getColor(itemView.context, R.color.busy_taxi))
        }
        adapter_vehicle_name.text = "Vehicle num.${vehicle.id.toString()}"

        val heading: String = when {
            vehicle.heading in 20F..160F -> "East"
            vehicle.heading in 161F..250F -> "South"
            vehicle.heading in 251F..340F -> "West"
            else -> "North"
        }
        adapter_vehicle_heading.text = heading
        setOnClickListener { navigator.navigateToDetail(vehicles, vehicle.id) }
    }
}