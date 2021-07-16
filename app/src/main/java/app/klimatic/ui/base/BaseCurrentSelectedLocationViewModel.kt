package app.klimatic.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.klimatic.data.pref.AppSharedPreferences

open class BaseCurrentSelectedLocationViewModel(
    private val appSharedPreferences: AppSharedPreferences
) : BaseCoroutinesViewModel() {

    private val currentSelectedLocationLiveData: MutableLiveData<String?> = MutableLiveData()
    val currentSelectedLocation: LiveData<String?> = currentSelectedLocationLiveData

    fun fetchCurrentSelectedLocation() {
        currentSelectedLocationLiveData.postValue(getCurrentSelectedLocation())
    }

    private fun getCurrentSelectedLocation(): String? =
        appSharedPreferences.getCurrentSelectedLocation()

    fun setCurrentSelectedLocation(location: String) =
        appSharedPreferences.setCurrentSelectedLocation(location)
}
