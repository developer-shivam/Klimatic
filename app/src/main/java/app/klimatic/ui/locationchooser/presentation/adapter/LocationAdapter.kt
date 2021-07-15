package app.klimatic.ui.locationchooser.presentation.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.klimatic.R
import app.klimatic.data.model.weather.Location
import kotlinx.android.synthetic.main.layout_current_weather_detail.view.tvLocationRegionCountry
import kotlinx.android.synthetic.main.layout_location_item.view.tvLocationName

class LocationAdapter(
    var action: (location: Location) -> Unit
) : ListAdapter<Location, LocationAdapter.LocationViewHolder>(
    object : DiffUtil.ItemCallback<Location>() {
        override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_location_item, parent, false),
            action
        )
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class LocationViewHolder(
        itemView: View,
        action: (location: Location) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        var location: Location? = null

        init {
            itemView.setOnClickListener {
                location?.let {
                    action(it)
                }
            }
        }

        fun bind(location: Location) {
            this.location = location

            itemView.tvLocationName.text = location.name?.split(",")?.get(0)

            itemView.tvLocationRegionCountry.apply {
                if (!TextUtils.isEmpty(location.region) && !TextUtils.isEmpty(location.country)) {
                    text = context.getString(
                        R.string.two_place_text,
                        location.region,
                        location.country
                    )
                } else if (!TextUtils.isEmpty(location.region)) {
                    text = location.region
                } else if (!TextUtils.isEmpty(location.country)) {
                    text = location.country
                }
            }
        }
    }
}
