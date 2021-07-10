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

        const val PATCHY_LIGHT_DRIZZLE = 1150
        const val LIGHT_DRIZZLE = 1153
        const val FREEZING_DRIZZLE = 1168
        const val PATCHY_FREEZING_DRIZZLE_POSSIBLE = 1072
        const val HEAVY_FREEZING_DRIZZLE = 1171
        const val PATCHY_RAIN_POSSIBLE = 1063
        const val PATCHY_LIGHT_RAIN = 1180
        const val LIGHT_RAIN = 1183
        const val MODERATE_RAIN_AT_TIMES = 1186
        const val MODERATE_RAIN = 1189
        const val HEAVY_RAIN_AT_TIMES = 1192
        const val HEAVY_RAIN = 1195
        const val LIGHT_FREEZING_RAIN = 1198
        const val MODERATE_OR_HEAVY_FREEZING_RAIN = 1201
        const val PATCHY_SLEET_POSSIBLE = 1069
        const val LIGHT_SLEET = 1204
        const val MODERATE_OR_HEAVY_SLEET = 1207
        const val ICE_PELLETS = 1237
        const val LIGHT_SHOWER_OF_ICE_PELLETS = 1261
        const val MODERATE_OR_HEAVY_SHOWER_OF_ICE_PELLETS = 1264
        const val LIGHT_RAIN_SHOWER = 1240
        const val MODERATE_OR_HEAVY_RAIN_SHOWER = 1243
        const val TORRENTIAL_RAIN_SHOWER = 1246
        const val LIGHT_SLEET_SHOWER = 1249
        const val MODERATE_OR_HEAVY_SLEET_SHOWER = 1252

        const val THUNDERY_OUTBREAKS_POSSIBLE = 1087

        const val PATCHY_LIGHT_RAIN_WITH_THUNDER = 1273
        const val MODERATE_OR_HEAVY_RAIN_WITH_THUNDER = 1276
        const val PATCHY_LIGHT_SNOW_WITH_THUNDER = 1279
        const val MODERATE_OR_HEAVY_SNOW_WITH_THUNDER = 1282

        const val BLOWING_SNOW = 1114
        const val PATCHY_SNOW_POSSIBLE = 1066
        const val BLIZZARD = 1117
        const val PATCHY_LIGHT_SNOW = 1210
        const val LIGHT_SNOW = 1213
        const val PATCHY_MODERATE_SNOW = 1216
        const val MODERATE_SNOW = 1219
        const val PATCHY_HEAVY_SNOW = 1222
        const val HEAVY_SNOW = 1225
        const val LIGHT_SNOW_SHOWERS = 1255
        const val MODERATE_OR_HEAVY_SNOW_SHOWERS = 1258

        const val FOG = 1135
        const val FREEZING_FOG = 1147
    }
}
