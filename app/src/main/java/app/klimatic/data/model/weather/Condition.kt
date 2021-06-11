package app.klimatic.data.model.weather

import com.google.gson.annotations.SerializedName

data class Condition(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("icon")
    val icon: String?,
    @SerializedName("text")
    val text: String?
)
