package app.klimatic.data.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrentWeatherService {

    // TODO: Need to send API key from interceptor
    @GET("/v1/current.json?key=0ca7bf1a07f84b15903193302210506")
    fun fetchCurrentWeather(
        @Query("q") query: String?
    ) : CurrentWeatherResponse
}