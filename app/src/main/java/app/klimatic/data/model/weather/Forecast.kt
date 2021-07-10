package app.klimatic.data.model.weather

import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("forecastday")
    val forecastDay: List<ForecastDay>?
) {

    data class ForecastDay(
        @SerializedName("hour")
        val hour: List<ForecastHour>?
    )

    data class ForecastHour(
        @SerializedName("time")
        val time: String?,
        @SerializedName("temp_c")
        val tempC: Double?,
        @SerializedName("temp_f")
        val tempF: Double?,
        @SerializedName("is_day")
        val isDay: Int?,
        @SerializedName("condition")
        val condition: Condition?
    )
}
