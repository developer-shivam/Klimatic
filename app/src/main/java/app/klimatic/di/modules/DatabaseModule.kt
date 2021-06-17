package app.klimatic.di.modules

import android.content.Context
import androidx.room.Room
import app.klimatic.data.model.local.AppDatabase
import app.klimatic.data.model.local.CurrentWeatherDao
import app.klimatic.di.qualifiers.DatabaseName
import app.klimatic.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule(private val applicationContext: Context) {

    @ApplicationScope
    @Provides
    @DatabaseName
    fun provideDatabaseName(): String = "klimatic-database"

    @ApplicationScope
    @Provides
    fun provideAppDatabase(
        @DatabaseName databaseName: String
    ): AppDatabase {
        return Room.databaseBuilder(applicationContext, AppDatabase::class.java, databaseName)
            .build()
    }

    @ApplicationScope
    @Provides
    fun provideCurrentWeatherDao(
        appDatabase: AppDatabase
    ): CurrentWeatherDao {
        return appDatabase.currentWeatherDao()
    }
}
