package app.klimatic.ui.currentweather.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.klimatic.data.remote.weather.CurrentWeatherResponse
import app.klimatic.data.response.Response
import app.klimatic.ui.utils.ViewState
import app.klimatic.ui.utils.toDataOrNull
import app.klimatic.ui.currentweather.domain.CurrentWeatherDataRepository
import kotlinx.coroutines.launch

class CurrentWeatherViewModel(private val repository: CurrentWeatherDataRepository): ViewModel() {

    val weatherListener = MutableLiveData<ViewState<CurrentWeatherResponse?>>()

    /**
     * query possible values
     * 1. Latitude and Longitude (Decimal degree) e.g: query=48.8567,2.3508
     * 2. city name e.g.: query=Delhi
     * 3. auto:ip IP lookup e.g: query=auto:ip (No location permission needed)
     * */
    fun fetchCurrentWeather(query: String = "auto:ip") {
        viewModelScope.launch {
            val viewState: ViewState<CurrentWeatherResponse?> =
                when (val response = repository.fetchCurrentWeather(query)) {
                    is Response.Success -> ViewState.Success(data = response.toDataOrNull())
                    is Response.Error -> ViewState.Error(code = response.errorCode)
                }
            weatherListener.postValue(viewState)
        }
    }
}
