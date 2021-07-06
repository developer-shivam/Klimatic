package app.klimatic.data.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.klimatic.data.model.local.entity.Weather

@Database(
    entities = [
        Weather::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(TypeConvertersUtility::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}
