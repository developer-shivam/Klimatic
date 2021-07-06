package app.klimatic.data.model.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.klimatic.data.model.local.entity.Weather

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveWeather(weather: Weather)

    @Query("SELECT * from weather WHERE `query` LIKE :query")
    fun fetchWeather(query: String): Weather?

    @Delete
    fun delete(weather: Weather)

    @Query("DELETE from weather")
    fun clearDb()
}
