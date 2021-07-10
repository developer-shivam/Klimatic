package app.klimatic.ui.weather.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import app.klimatic.R
import app.klimatic.data.model.weather.Condition
import kotlinx.android.synthetic.main.layout_current_weather_condition.view.motionLayout

class CurrentWeatherCondition @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var view = LayoutInflater.from(context)
        .inflate(R.layout.layout_current_weather_condition, this, true)

    fun setCurrentWeatherCondition(isDay: Boolean, condition: Condition) {
        when (condition.code) {
            Condition.SUNNY_OR_CLEAR -> {
                if (isDay) {
                    updateWeatherScene(R.id.sunny_scene)
                } else {
                    updateWeatherScene(R.id.clear_scene)
                }
            }
            Condition.PARTLY_CLOUDY -> {
                if (isDay) {
                    updateWeatherScene(R.id.partly_cloudy_day_scene)
                } else {
                    updateWeatherScene(R.id.partly_cloudy_night_scene)
                }
            }
            Condition.CLOUDY -> {
                updateWeatherScene(R.id.cloudy_scene)
            }
            Condition.OVERCAST -> {
                updateWeatherScene(R.id.overcast_scene)
            }
            Condition.MIST -> {
                updateWeatherScene(R.id.mist_scene)
            }
            Condition.PATCHY_RAIN_POSSIBLE -> {
                updateWeatherScene(R.id.patchy_rain_scene)
            }
            Condition.PATCHY_SNOW_POSSIBLE -> {
                updateWeatherScene(R.id.patchy_snow_scene)
            }
        }
    }

    private fun updateWeatherScene(updatedWeatherScene: Int) {
        val currentWeatherScene = view.motionLayout
        currentWeatherScene.setTransition(currentWeatherScene.currentState, updatedWeatherScene)
        currentWeatherScene.transitionToEnd()
    }
}
