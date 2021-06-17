package app.klimatic.data.model.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.klimatic.data.remote.weather.CurrentWeatherResponse

@Entity
data class CurrentWeatherEntity(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "query")
    val query: String,
    @ColumnInfo(name = "data")
    val data: CurrentWeatherResponse,
    @ColumnInfo(name = "last_updated")
    val last_updated: Long
)
