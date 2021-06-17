package app.klimatic.data.model.local

import androidx.room.TypeConverter
import app.klimatic.data.remote.weather.CurrentWeatherResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CurrentWeatherTypeConverter {

    @TypeConverter
    fun currentWeatherToString(currentWeather: CurrentWeatherResponse): String {
        return Gson().toJson(currentWeather)
    }

    @TypeConverter
    fun storedStringToCurrentWeather(
        storedCurrentWeatherResponseString: String
    ): CurrentWeatherResponse {
        val currentWeatherType = object : TypeToken<CurrentWeatherResponse>() {}.type
        return Gson().fromJson(storedCurrentWeatherResponseString, currentWeatherType)
    }
}
