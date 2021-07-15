package app.klimatic.ui.weather.presentation

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import app.klimatic.R
import app.klimatic.data.remote.weather.WeatherResponse
import app.klimatic.ui.utils.hide
import kotlinx.android.synthetic.main.layout_current_weather_detail.view.tvCurrentTemp
import kotlinx.android.synthetic.main.layout_current_weather_detail.view.tvCurrentWeatherCondition
import kotlinx.android.synthetic.main.layout_current_weather_detail.view.tvHumidityValue
import kotlinx.android.synthetic.main.layout_current_weather_detail.view.tvLocation
import kotlinx.android.synthetic.main.layout_current_weather_detail.view.tvLocationRegionCountry
import kotlinx.android.synthetic.main.layout_current_weather_detail.view.tvWindDirectionValue
import kotlinx.android.synthetic.main.layout_current_weather_detail.view.tvWindSpeedValue

class CurrentWeatherDetailView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var view = LayoutInflater.from(context)
        .inflate(R.layout.layout_current_weather_detail, this, true)

    fun setOnCurrentLocationClickAction(action: () -> Unit) {
        tvLocation.setOnClickListener {
            action()
        }
    }

    fun setCurrentWeatherData(currentWeatherResponse: WeatherResponse?) {

        view.run {
            val location = currentWeatherResponse?.location
            tvLocation.text = location?.name

            if (!TextUtils.isEmpty(location?.region) && !TextUtils.isEmpty(location?.country)) {
                tvLocationRegionCountry.text =
                    context.getString(R.string.two_place_text, location?.region, location?.country)
            } else if (!TextUtils.isEmpty(location?.region)) {
                tvLocationRegionCountry.text = location?.region
            } else if (!TextUtils.isEmpty(location?.country)) {
                tvLocationRegionCountry.text = location?.country
            } else {
                tvLocationRegionCountry.hide()
            }

            val current = currentWeatherResponse?.current
            val condition = current?.condition

            tvCurrentWeatherCondition.text = condition?.text
            tvCurrentTemp.text = context.getString(R.string.temp_c, current?.tempC)
            tvWindSpeedValue.text = context.getString(R.string.wind_kph, current?.windKph)

            tvWindDirectionValue.text =
                context.getString(
                    R.string.wind_direction_value, current?.windDegree.toString(),
                    current?.windDir
                )
            tvHumidityValue.text = current?.humidity.toString()
        }
    }
}
