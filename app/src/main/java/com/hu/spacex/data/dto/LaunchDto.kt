package com.hu.spacex.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

// dto data transfer object
@Entity(tableName = "launches")
data class LaunchDto(
    @PrimaryKey
    @SerializedName("flight_number")
    val flightNumber: Int,
    @SerializedName("mission_name")
    val missionName: String,
    @SerializedName("launch_year")
    val launchYear: String,
    @SerializedName("launch_date_unix")
    val date: Long,
    val rocket: RocketDto,
    val links: LinkDto,
    @SerializedName("launch_success")
    val launchSuccess: Boolean
) : Serializable
