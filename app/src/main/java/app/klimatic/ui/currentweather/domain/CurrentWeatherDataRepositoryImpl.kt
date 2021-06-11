package app.klimatic.ui.currentweather.domain

import app.klimatic.data.remote.weather.CurrentWeatherService
import app.klimatic.ui.utils.getResponse
import javax.inject.Inject

class CurrentWeatherDataRepositoryImpl @Inject constructor(
    private val weatherService: CurrentWeatherService
) : CurrentWeatherDataRepository {

    override suspend fun fetchCurrentWeather(query: String) = getResponse {
        weatherService.fetchCurrentWeather(query)
    }
}
