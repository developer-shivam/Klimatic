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

    fun setCurrentWeatherData(currentWeatherResponse: CurrentWeatherResponse?) {

        view.run {
            val location = currentWeatherResponse?.location
            tvLocation.text =
                    context.getString(R.string.two_place_text, location?.name, location?.country)

            val current = currentWeatherResponse?.current
            val condition = current?.condition

            Glide.with(context).load(context.getString(R.string.https_link, condition?.icon))
                    .into(view.ivCurrentWeatherCondition)

            tvCurrentWeatherCondition.text = condition?.text
            tvCurrentTemp.text = context.getString(R.string.temp_c, current?.tempC)
            tvWindSpeedValue.text = context.getString(R.string.wind_kph, current?.windKph)

            tvWindDirectionValue.text =
                    context.getString(R.string.two_place_text, current?.windDegree.toString(),
                            current?.windDir)
            tvHumidityValue.text = current?.humidity.toString()
        }
    }
}
