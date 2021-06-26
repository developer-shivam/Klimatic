package app.klimatic.di.modules

import android.content.Context
import androidx.room.Room
import app.klimatic.data.model.local.AppDatabase
import app.klimatic.data.model.local.CurrentWeatherDao
import org.koin.dsl.module

const val DB_NAME = "klimatic-database"

val databaseModule = module {
    single { provideAppDatabase(get()) }
    single { provideCurrentWeatherDao(get()) }
}

private fun provideAppDatabase(
    context: Context
): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
        .build()
}

private fun provideCurrentWeatherDao(
    appDatabase: AppDatabase
): CurrentWeatherDao {
    return appDatabase.currentWeatherDao()
}
