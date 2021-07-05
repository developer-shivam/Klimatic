package app.klimatic

import app.klimatic.data.model.local.CurrentWeatherDao
import app.klimatic.di.module.testAppModules
import org.koin.android.ext.android.inject

class TestKlimaticApplication : KlimaticApplication() {

    private val currentWeatherDao: CurrentWeatherDao by inject()

    override fun getAppModules() = testAppModules

    // To be used for testing only
    // Test fails because data get provided from db when network request fails
    fun clearDb() {
        currentWeatherDao.clearDb()
    }
}
