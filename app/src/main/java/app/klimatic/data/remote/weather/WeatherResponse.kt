package app.klimatic.data.remote.weather

import app.klimatic.data.model.weather.Current
import app.klimatic.data.model.weather.Forecast
import app.klimatic.data.model.weather.Location
import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("location")
    val location: Location?,
    @SerializedName("current")
    val current: Current?,
    @SerializedName("forecast")
    val forecast: Forecast?
)
