package app.klimatic.ui.currentweather.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import app.klimatic.R
import app.klimatic.data.model.weather.ForeCastHour
import app.klimatic.ui.utils.getHours
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_forecast_item.view.cvParent
import kotlinx.android.synthetic.main.layout_forecast_item.view.ivWeather
import kotlinx.android.synthetic.main.layout_forecast_item.view.tvTemp
import kotlinx.android.synthetic.main.layout_forecast_item.view.tvTime

class ForeCastAdapter(private val context: Context) :
    RecyclerView.Adapter<ForeCastAdapter.ViewHolder>() {

    private val dataList: ArrayList<ForeCastHour> = arrayListOf()

    fun updateForeCastData(updatedList: List<ForeCastHour>?) {
        updatedList?.let {
            dataList.clear()
            dataList.addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_forecast_item, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataItem = dataList[position]
        holder.run {
            tvTemp.text = context.getString(R.string.temp_c, dataItem.tempC)
            tvTime.text = dataItem.time.getHours()
            Glide.with(context)
                .load(context.getString(R.string.https_link, dataItem.condition?.icon))
                .into(ivWeather)
        }
    }

    override fun getItemCount() = dataList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvParent: CardView = itemView.cvParent
        val tvTime: TextView = itemView.tvTime
        val ivWeather: ImageView = itemView.ivWeather
        val tvTemp: TextView = itemView.tvTemp
    }
}
