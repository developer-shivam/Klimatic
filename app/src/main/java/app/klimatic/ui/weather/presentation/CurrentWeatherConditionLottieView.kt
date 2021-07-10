package app.klimatic.ui.weather.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import app.klimatic.R
import app.klimatic.data.model.weather.Condition
import kotlinx.android.synthetic.main.layout_current_weather_condition_lottie_view.view.lottieAnimationView

class CurrentWeatherConditionLottieView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var view = LayoutInflater.from(context)
        .inflate(R.layout.layout_current_weather_condition_lottie_view, this, true)

    fun setCurrentWeatherCondition(isDay: Boolean, condition: Condition) {
        val lottieAnimationView = view.lottieAnimationView
        when (condition.code) {
            Condition.SUNNY_OR_CLEAR -> {
                if (isDay) {
                    lottieAnimationView.setAnimation("weather_sunny.json")
                } else {
                    lottieAnimationView.setAnimation("weather_night_clear.json")
                }
            }
            Condition.PARTLY_CLOUDY,
            Condition.CLOUDY -> {
                if (isDay) {
                    lottieAnimationView.setAnimation("weather_partly_cloudy.json")
                } else {
                    lottieAnimationView.setAnimation("weather_cloudy_night.json")
                }
            }
            Condition.OVERCAST -> {
                lottieAnimationView.setAnimation("weather_windy_overcast.json")
            }
            Condition.PATCHY_LIGHT_DRIZZLE,
            Condition.LIGHT_DRIZZLE,
            Condition.PATCHY_FREEZING_DRIZZLE_POSSIBLE,
            Condition.FREEZING_DRIZZLE,
            Condition.HEAVY_FREEZING_DRIZZLE,
            Condition.PATCHY_RAIN_POSSIBLE,
            Condition.PATCHY_SLEET_POSSIBLE,
            Condition.LIGHT_SLEET,
            Condition.MODERATE_OR_HEAVY_SLEET,
            Condition.PATCHY_LIGHT_RAIN,
            Condition.LIGHT_RAIN,
            Condition.MODERATE_RAIN_AT_TIMES,
            Condition.MODERATE_RAIN,
            Condition.HEAVY_RAIN_AT_TIMES,
            Condition.HEAVY_RAIN,
            Condition.LIGHT_FREEZING_RAIN,
            Condition.MODERATE_OR_HEAVY_FREEZING_RAIN,
            Condition.ICE_PELLETS,
            Condition.LIGHT_SHOWER_OF_ICE_PELLETS,
            Condition.MODERATE_OR_HEAVY_SHOWER_OF_ICE_PELLETS,
            Condition.LIGHT_RAIN_SHOWER,
            Condition.MODERATE_OR_HEAVY_RAIN_SHOWER,
            Condition.TORRENTIAL_RAIN_SHOWER,
            Condition.LIGHT_SLEET_SHOWER,
            Condition.MODERATE_OR_HEAVY_SLEET_SHOWER -> {
                if (isDay) {
                    lottieAnimationView.setAnimation("weather_rainy_day.json")
                } else {
                    lottieAnimationView.setAnimation("weather_rainy_night.json")
                }
            }
            Condition.FOG,
            Condition.FREEZING_FOG,
            Condition.MIST -> {
                lottieAnimationView.setAnimation("weather_mist.json")
            }
            Condition.THUNDERY_OUTBREAKS_POSSIBLE -> {
                lottieAnimationView.setAnimation("weather_thunder.json")
            }
            Condition.PATCHY_LIGHT_RAIN_WITH_THUNDER,
            Condition.MODERATE_OR_HEAVY_RAIN_WITH_THUNDER,
            Condition.PATCHY_LIGHT_SNOW_WITH_THUNDER,
            Condition.MODERATE_OR_HEAVY_SNOW_WITH_THUNDER -> {
                lottieAnimationView.setAnimation("weather_rain_thunder.json")
            }
            Condition.PATCHY_SNOW_POSSIBLE -> {
                if (isDay) {
                    lottieAnimationView.setAnimation("weather_snow_sunny.json")
                } else {
                    lottieAnimationView.setAnimation("weather_snow_night.json")
                }
            }
            Condition.BLOWING_SNOW,
            Condition.BLIZZARD,
            Condition.PATCHY_LIGHT_SNOW,
            Condition.LIGHT_SNOW,
            Condition.PATCHY_MODERATE_SNOW,
            Condition.MODERATE_SNOW,
            Condition.PATCHY_HEAVY_SNOW,
            Condition.HEAVY_SNOW,
            Condition.LIGHT_SNOW_SHOWERS,
            Condition.MODERATE_OR_HEAVY_SNOW_SHOWERS -> {
                lottieAnimationView.setAnimation("weather_snow.json")
            }
        }
        lottieAnimationView.playAnimation()
    }
}
