package com.hu.spacex.data.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RocketDto(
    @SerializedName("rocket_name")
    val rocketName: String,
    @SerializedName("rocket_type")
    val rocketType: String
) : Serializable
