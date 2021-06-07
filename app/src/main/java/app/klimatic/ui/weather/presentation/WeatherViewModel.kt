package app.klimatic.ui.weather.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.klimatic.data.remote.CurrentWeatherResponse
import app.klimatic.data.response.Response
import app.klimatic.ui.utils.ViewState
import app.klimatic.ui.utils.toDataOrNull
import app.klimatic.ui.weather.domain.WeatherDataRepository
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherDataRepository): ViewModel() {

    val weatherListener = MutableLiveData<ViewState<CurrentWeatherResponse?>>()

    fun fetchCurrentWeather(query: String) {
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