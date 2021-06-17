package app.klimatic.data.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.klimatic.data.model.local.entity.CurrentWeatherEntity

@Database(
    entities = [
        CurrentWeatherEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(CurrentWeatherTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun currentWeatherDao(): CurrentWeatherDao
}
