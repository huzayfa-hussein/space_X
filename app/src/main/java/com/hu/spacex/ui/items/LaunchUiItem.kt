package com.hu.spacex.ui.items

data class LaunchUiItem(
    val launchIcon: String = "",
    val missionName: String,
    val missionDateAndTimes: String,
    val rocketName: String,
    val launchDays: String,
    val launchDaysLabel: String,
    val isSuccessful: Boolean,
    val showSeparator: Boolean = true,
    val youtubeLink: String = "https://www.youtube.com/",
    val articleLink: String = "https://www.google.com/",
    val wikipediaLink: String = "https://www.wikipedia.org/"
)
