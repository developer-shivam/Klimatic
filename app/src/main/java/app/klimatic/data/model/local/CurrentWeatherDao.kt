package app.klimatic.data.model.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import app.klimatic.data.model.local.entity.CurrentWeatherEntity

@Dao
interface CurrentWeatherDao {

    @Insert
    fun saveCurrentWeather(entity: CurrentWeatherEntity)

    @Query("SELECT * from currentweatherentity WHERE q LIKE :query")
    fun fetchCurrentWeather(query: String): CurrentWeatherEntity?

    @Delete
    fun delete(entity: CurrentWeatherEntity)
}
