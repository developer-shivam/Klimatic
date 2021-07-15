package app.klimatic.ui.weather.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.klimatic.data.pref.AppSharedPreferences
import app.klimatic.data.remote.weather.WeatherResponse
import app.klimatic.data.response.Response
import app.klimatic.ui.base.BaseCoroutinesViewModel
import app.klimatic.ui.utils.ViewState
import app.klimatic.ui.weather.domain.WeatherDataManager
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val appSharedPreferences: AppSharedPreferences,
    private val repository: WeatherDataManager
) : BaseCoroutinesViewModel() {

    companion object {
        const val DEFAULT_QUERY = "auto:ip"
    }

    private val weatherLiveData = MutableLiveData<ViewState<WeatherResponse>>()
    val weather: LiveData<ViewState<WeatherResponse>> = weatherLiveData

    fun fetchWeatherLocal(query: String = DEFAULT_QUERY) {
        ioScope.launch {
            val entity = repository.fetchWeatherLocal(query)
            if (entity != null) {
                weatherLiveData.postValue(ViewState.Success(entity.data))
            }
        }
    }

    fun fetchWeatherRemote(query: String = DEFAULT_QUERY) {
        ioScope.launch {
            val viewState: ViewState<WeatherResponse> =
                when (val response =
                    repository.fetchWeatherRemote(query)) {
                    is Response.Success -> {
                        repository.saveWeather(query, response.data)
                        ViewState.Success(data = response.data)
                    }
                    is Response.Error -> ViewState.Error(code = response.errorCode)
                }
            weatherLiveData.postValue(viewState)
        }
    }

    fun getCurrentSelectedLocation(): String? = appSharedPreferences.getCurrentSelectedLocation()
}
