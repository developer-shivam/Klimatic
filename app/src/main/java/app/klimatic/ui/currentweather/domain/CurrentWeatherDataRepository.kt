package app.klimatic.ui.currentweather.domain

import app.klimatic.data.remote.forecast.ForeCastWeatherResponse
import app.klimatic.data.remote.weather.CurrentWeatherResponse
import app.klimatic.data.response.Response

interface CurrentWeatherDataRepository {
    suspend fun fetchCurrentWeather(query: String): Response<CurrentWeatherResponse>
    suspend fun fetchForeCast(query: String): Response<ForeCastWeatherResponse>
}
