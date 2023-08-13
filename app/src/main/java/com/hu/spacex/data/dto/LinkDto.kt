package com.hu.spacex.data.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LinkDto(
    @SerializedName("mission_patch_small")
    val image: String,
    @SerializedName("article_link")
    val articleLink: String,
    @SerializedName("video_link")
    val youtubeLink: String,
    @SerializedName("wikipedia")
    val wikipediaLink: String
) : Serializable
