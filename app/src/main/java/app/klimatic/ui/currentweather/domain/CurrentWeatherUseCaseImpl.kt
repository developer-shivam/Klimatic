package app.klimatic.ui.currentweather.domain

import app.klimatic.data.model.local.CurrentWeatherDao
import app.klimatic.data.model.local.entity.CurrentWeatherEntity
import app.klimatic.data.remote.weather.CurrentWeatherResponse
import app.klimatic.data.response.Response

class CurrentWeatherUseCaseImpl(
    private val currentWeatherDataRepository: CurrentWeatherDataRepository,
    private val currentWeatherDao: CurrentWeatherDao
) : CurrentWeatherUseCase {

    override suspend fun fetchCurrentWeather(query: String): Response<CurrentWeatherResponse> {
        val response = fetchCurrentWeatherRemote(query)
        if (response is Response.Success) {
            saveCurrentWeather(query, response.data, System.currentTimeMillis())
        } else {
            val localResponse = fetchCurrentWeatherLocal(query)
            if (localResponse != null) {
                return Response.Success(localResponse.data)
            }
        }
        return response
    }

    override suspend fun fetchCurrentWeatherRemote(
        query: String
    ): Response<CurrentWeatherResponse> = currentWeatherDataRepository.fetchCurrentWeather(query)

    override suspend fun fetchCurrentWeatherLocal(query: String): CurrentWeatherEntity? {
        return currentWeatherDao.fetchCurrentWeather(query)
    }

    override suspend fun saveCurrentWeather(
        query: String,
        data: CurrentWeatherResponse,
        lastUpdateTime: Long
    ) {
        currentWeatherDao.saveCurrentWeather(
            CurrentWeatherEntity(
                query, data, lastUpdateTime
            )
        )
    }

    override suspend fun fetchForeCast(query: String) =
        currentWeatherDataRepository.fetchForeCast(query)
}
