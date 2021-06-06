package app.klimatic.ui.weather.domain

import app.klimatic.data.remote.CurrentWeatherService
import app.klimatic.ui.utils.getResponse

class WeatherDataRepositoryImpl(private val weatherService: CurrentWeatherService) : WeatherDataRepository {
    override suspend fun fetchCurrentWeather(query: String) = getResponse {
        weatherService.fetchCurrentWeather(query)
    }
}