package app.klimatic.ui.weather.domain

import app.klimatic.data.model.local.WeatherDao
import app.klimatic.data.model.local.entity.Weather
import app.klimatic.data.remote.service.WeatherService
import app.klimatic.data.remote.weather.WeatherResponse
import app.klimatic.ui.utils.getResponse

class WeatherDataManagerImpl(
    private val weatherDao: WeatherDao,
    private val weatherService: WeatherService
) : WeatherDataManager {

    override suspend fun fetchWeatherLocal(query: String): Weather? {
        return weatherDao.fetchWeather(query)
    }

    override suspend fun saveWeather(
        query: String,
        data: WeatherResponse
    ) {
        weatherDao.saveWeather(
            Weather(query, data)
        )
    }

    override suspend fun fetchWeatherRemote(query: String?) = getResponse {
        weatherService.fetchWeather(query)
    }
}
