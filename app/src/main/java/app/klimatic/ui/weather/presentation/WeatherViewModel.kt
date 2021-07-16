package app.klimatic.ui.weather.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.klimatic.data.pref.AppSharedPreferences
import app.klimatic.data.remote.weather.WeatherResponse
import app.klimatic.data.response.Response
import app.klimatic.ui.base.BaseCurrentSelectedLocationViewModel
import app.klimatic.ui.utils.ViewState
import app.klimatic.ui.weather.domain.WeatherDataManager
import kotlinx.coroutines.launch

class WeatherViewModel(
    appSharedPreferences: AppSharedPreferences,
    private val dataManager: WeatherDataManager
) : BaseCurrentSelectedLocationViewModel(
    appSharedPreferences
) {

    private val weatherLiveData = MutableLiveData<ViewState<WeatherResponse>>()
    val weather: LiveData<ViewState<WeatherResponse>> = weatherLiveData

    fun fetchWeatherLocal(query: String) {
        ioScope.launch {
            val entity = dataManager.fetchWeatherLocal(query)
            if (entity != null) {
                weatherLiveData.postValue(ViewState.Success(entity.data))
            }
        }
    }

    fun fetchWeatherRemote(query: String) {
        ioScope.launch {
            val viewState: ViewState<WeatherResponse> =
                when (val response =
                    dataManager.fetchWeatherRemote(query)) {
                    is Response.Success -> {
                        dataManager.saveWeather(query, response.data)
                        ViewState.Success(data = response.data)
                    }
                    is Response.Error -> ViewState.Error(code = response.errorCode)
                }
            weatherLiveData.postValue(viewState)
        }
    }
}
