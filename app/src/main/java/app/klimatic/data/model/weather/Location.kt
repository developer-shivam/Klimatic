package app.klimatic.data.model.weather


import com.google.gson.annotations.SerializedName


data class Location(
    @SerializedName("country")
    val country: String?,
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("localtime")
    val localtime: String?,
    @SerializedName("localtime_epoch")
    val localtimeEpoch: Int?,
    @SerializedName("lon")
    val lon: Double?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("region")
    val region: String?,
    @SerializedName("tz_id")
    val tzId: String?
)
