package app.klimatic.ui.currentweather.domain

import app.klimatic.data.model.local.entity.CurrentWeatherEntity
import app.klimatic.data.remote.forecast.ForeCastWeatherResponse
import app.klimatic.data.remote.weather.CurrentWeatherResponse
import app.klimatic.data.response.Response

interface CurrentWeatherUseCase {

    suspend fun fetchCurrentWeather(
        query: String
    ): Response<CurrentWeatherResponse>

    suspend fun fetchCurrentWeatherRemote(
        query: String
    ): Response<CurrentWeatherResponse>

    suspend fun fetchCurrentWeatherLocal(
        query: String
    ): CurrentWeatherEntity?

    suspend fun saveCurrentWeather(
        query: String,
        data: CurrentWeatherResponse,
        lastUpdateTime: Long
    )

    suspend fun fetchForeCast(
        query: String
    ): Response<ForeCastWeatherResponse>
}
