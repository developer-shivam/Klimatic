package app.klimatic.data.model.weather

import com.google.gson.annotations.SerializedName

data class Condition(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("icon")
    val icon: String?,
    @SerializedName("text")
    val text: String?
) {

    companion object {
        const val SUNNY_OR_CLEAR = 1000
        const val PARTLY_CLOUDY = 1003
        const val CLOUDY = 1006
        const val OVERCAST = 1009
        const val MIST = 1030
        const val PATCHY_RAIN_POSSIBLE = 1063
        const val PATCHY_SNOW_POSSIBLE = 1066
    }
}
