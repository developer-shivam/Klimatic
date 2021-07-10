package app.klimatic.ui.weather.domain

import app.klimatic.data.model.local.entity.Weather
import app.klimatic.data.remote.weather.WeatherResponse
import app.klimatic.data.response.Response

interface WeatherDataManager {

    suspend fun fetchWeatherLocal(
        query: String
    ): Weather?

    suspend fun saveWeather(
        query: String,
        data: WeatherResponse
    )

    suspend fun fetchWeatherRemote(query: String?): Response<WeatherResponse>
}
