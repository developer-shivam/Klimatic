package app.klimatic.ui.currentweather.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import app.klimatic.R
import app.klimatic.data.remote.weather.CurrentWeatherResponse
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_current_weather.view.*

class CurrentWeatherView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var view = LayoutInflater.from(context)
        .inflate(R.layout.layout_current_weather, this, true)

    fun setCurrentWeatherData(currentWeatherResponse: CurrentWeatherResponse) {

        currentWeatherResponse.location?.apply {

            view.tvLocation.text = String.format("%s, %s", name, country)
        }

        currentWeatherResponse.current?.apply {

            condition?.apply {
                Glide.with(context).load("https:$icon")
                    .into(view.ivCurrentWeatherCondition)
                view.tvCurrentWeatherCondition.text = text
            }

            view.tvCurrentTemp.text = String.format("%s °C", tempC)

            view.tvWindSpeedValue.text = String.format("%s k/h", windKph)
            view.tvWindDirectionValue.text = String.format("%d° %s", windDegree, windDir)

            view.tvHumidityValue.text = humidity?.toString()
        }
    }
}
