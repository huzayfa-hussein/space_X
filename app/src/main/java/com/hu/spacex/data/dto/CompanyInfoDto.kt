package com.hu.spacex.data.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

// dto data transfer object
data class CompanyInfoDto(
    val name: String,
    val founder: String,
    val founded: String,
    val employees: String,
    @SerializedName("launch_sites")
    val launchSites: String,
    val valuation: String
) : Serializable