package app.klimatic.di.modules

import android.content.Context
import androidx.room.Room
import app.klimatic.data.model.local.AppDatabase
import app.klimatic.data.model.local.CurrentWeatherDao
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val DATABASE_NAME = "DATABASE_NAME"

const val DB_NAME = "klimatic-database"

val databaseModule = module {

    fun provideAppDatabase(
        context: Context,
        databaseName: String
    ): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, databaseName)
            .build()
    }

    fun provideCurrentWeatherDao(
        appDatabase: AppDatabase
    ): CurrentWeatherDao {
        return appDatabase.currentWeatherDao()
    }

    // Database name
    single(qualifier = named(DATABASE_NAME)) {
        DB_NAME
    }

    // AppDatabase
    single {
        provideAppDatabase(androidContext(), get(qualifier = named(DATABASE_NAME)))
    }

    // Current Weather Dao
    single {
        provideCurrentWeatherDao(get())
    }
}
