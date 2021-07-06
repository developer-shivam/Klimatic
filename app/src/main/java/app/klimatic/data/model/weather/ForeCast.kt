package app.klimatic.data.model.weather

import com.google.gson.annotations.SerializedName

data class ForeCast(
    @SerializedName("forecastday")
    val forecastDay: List<ForeCastDay>?
) {

    data class ForeCastDay(
        @SerializedName("hour")
        val hour: List<ForeCastHour>?
    )

    data class ForeCastHour(
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
