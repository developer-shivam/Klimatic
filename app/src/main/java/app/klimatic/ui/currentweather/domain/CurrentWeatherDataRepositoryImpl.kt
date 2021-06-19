package app.klimatic.ui.currentweather.domain

import app.klimatic.data.remote.service.WeatherService
import app.klimatic.ui.utils.getResponse

class CurrentWeatherDataRepositoryImpl(
    private val weatherService: WeatherService
) : CurrentWeatherDataRepository {

    override suspend fun fetchCurrentWeather(query: String) = getResponse {
        weatherService.fetchCurrentWeather(query)
    }

    override suspend fun fetchForeCast(query: String) = getResponse {
        weatherService.fetchForeCast(query)
    }
}
