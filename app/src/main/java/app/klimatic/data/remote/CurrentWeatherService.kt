package app.klimatic.data.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrentWeatherService {

    @GET("/v1/current.json")
    fun fetchCurrentWeather(
        @Query("q") query: String?
    ) : Observable<CurrentWeatherResponse>
}