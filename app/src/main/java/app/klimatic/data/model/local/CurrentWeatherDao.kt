package app.klimatic.data.model.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.klimatic.data.model.local.entity.CurrentWeatherEntity

@Dao
interface CurrentWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCurrentWeather(entity: CurrentWeatherEntity)

    @Query("SELECT * from currentweatherentity WHERE `query` LIKE :query")
    fun fetchCurrentWeather(query: String): CurrentWeatherEntity?

    @Delete
    fun delete(entity: CurrentWeatherEntity)

    @Query("DELETE from currentweatherentity")
    fun clearDb()
}
