package app.klimatic.ui.currentweather.domain

import app.klimatic.data.remote.weather.CurrentWeatherResponse
import app.klimatic.data.response.Response
import javax.inject.Inject

class CurrentWeatherUseCaseImpl @Inject constructor (
    private val currentWeatherDataRepository: CurrentWeatherDataRepository
) : CurrentWeatherUseCase {

    override suspend fun fetchCurrentWeather(query: String): Response<CurrentWeatherResponse> =
        currentWeatherDataRepository.fetchCurrentWeather(query)
}
