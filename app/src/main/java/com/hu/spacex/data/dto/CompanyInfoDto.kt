package com.hu.spacex.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

// dto data transfer object
@Entity(tableName = "info")
data class CompanyInfoDto(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val founder: String,
    val founded: String,
    val employees: String,
    @SerializedName("launch_sites")
    val launchSites: String,
    val valuation: String
) : Serializable