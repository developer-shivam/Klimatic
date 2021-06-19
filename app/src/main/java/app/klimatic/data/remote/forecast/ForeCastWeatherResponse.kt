package app.klimatic.data.remote.forecast

import app.klimatic.data.model.weather.ForeCast
import com.google.gson.annotations.SerializedName

data class ForeCastWeatherResponse(
    @SerializedName("forecast")
    val forecast: ForeCast?
)