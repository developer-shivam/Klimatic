package app.klimatic.data.model.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.klimatic.data.remote.weather.WeatherResponse

@Entity
data class Weather(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "query")
    val query: String,
    @ColumnInfo(name = "data")
    val data: WeatherResponse
)
