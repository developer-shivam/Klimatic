package app.klimatic

import app.klimatic.data.model.local.WeatherDao
import app.klimatic.di.module.testAppModules
import org.koin.android.ext.android.inject

class TestKlimaticApplication : KlimaticApplication() {

    private val weatherDao: WeatherDao by inject()

    override fun getAppModules() = testAppModules

    // To be used for testing only
    // Test fails because data get provided from db when network request fails
    fun clearDb() {
        weatherDao.clearDb()
    }
}
