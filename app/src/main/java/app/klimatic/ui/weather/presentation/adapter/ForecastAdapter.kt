package app.klimatic.ui.weather.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.klimatic.R
import app.klimatic.data.model.weather.Forecast
import app.klimatic.ui.utils.getHours
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_forecast_item.view.ivWeather
import kotlinx.android.synthetic.main.layout_forecast_item.view.tvTemp
import kotlinx.android.synthetic.main.layout_forecast_item.view.tvTime

class ForecastAdapter(private val context: Context) :
    ListAdapter<Forecast.ForecastHour, ForecastAdapter.ViewHolder>(ForecastDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_forecast_item, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataItem = getItem(position)
        holder.run {
            tvTemp.text = context.getString(R.string.temp_c, dataItem.tempC)
            tvTime.text = dataItem.time.getHours()
            Glide.with(context)
                .load(context.getString(R.string.https_link, dataItem.condition?.icon))
                .into(ivWeather)
        }
    }

    private class ForecastDiffCallback : DiffUtil.ItemCallback<Forecast.ForecastHour>() {
        override fun areItemsTheSame(
            oldItem: Forecast.ForecastHour,
            newItem: Forecast.ForecastHour
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Forecast.ForecastHour,
            newItem: Forecast.ForecastHour
        ): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTime: TextView = itemView.tvTime
        val ivWeather: ImageView = itemView.ivWeather
        val tvTemp: TextView = itemView.tvTemp
    }
}
