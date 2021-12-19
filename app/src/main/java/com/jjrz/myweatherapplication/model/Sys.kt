package com.jjrz.myweatherapplication.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Sys(
    @SerializedName("pod")
    val pod: String?
) : Serializable