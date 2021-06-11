package app.klimatic.ui.weather.domain

import app.klimatic.data.remote.CurrentWeatherResponse
import app.klimatic.data.response.Response

interface WeatherDataRepository {
    suspend fun fetchCurrentWeather(query: String): Response<CurrentWeatherResponse>
}
