package com.jjrz.myweatherapplication.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Wind(
    @SerializedName("deg")
    val deg: Int?,
    @SerializedName("gust")
    val gust: Double?,
    @SerializedName("speed")
    val speed: Double?
) : Serializable