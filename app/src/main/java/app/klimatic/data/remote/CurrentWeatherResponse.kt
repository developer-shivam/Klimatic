package app.klimatic.data.remote

import app.klimatic.data.model.Current
import app.klimatic.data.model.Location
import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("location")
    val location: Location?,
    @SerializedName("current")
    val current: Current?
)