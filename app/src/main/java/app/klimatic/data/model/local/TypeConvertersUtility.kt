package app.klimatic.data.model.local

import androidx.room.TypeConverter
import app.klimatic.data.remote.weather.WeatherResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConvertersUtility {

    @TypeConverter
    fun weatherToString(weather: WeatherResponse): String {
        return Gson().toJson(weather)
    }

    @TypeConverter
    fun storedStringToWeather(
        storedWeatherResponseString: String
    ): WeatherResponse {
        val weatherResponseType = object : TypeToken<WeatherResponse>() {}.type
        return Gson().fromJson(storedWeatherResponseString, weatherResponseType)
    }
}
