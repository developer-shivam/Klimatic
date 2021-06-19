package app.klimatic.data.remote.service

import app.klimatic.data.remote.forecast.ForeCastWeatherResponse
import app.klimatic.data.remote.weather.CurrentWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("/v1/current.json")
    suspend fun fetchCurrentWeather(
        @Query("q") query: String?
    ): CurrentWeatherResponse

    @GET("/v1/forecast.json")
    suspend fun fetchForeCast(
        @Query("q") query: String?
    ): ForeCastWeatherResponse
}
