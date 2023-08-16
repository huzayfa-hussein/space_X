package com.hu.spacex.data.persistence

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.hu.spacex.data.dto.LinkDto
import com.hu.spacex.data.dto.RocketDto

class Converters {

    @TypeConverter
    fun stringToRocket(value: String): RocketDto? {
        return Gson().fromJson(value, RocketDto::class.java)
    }

    @TypeConverter
    fun rocketToString(rocket: RocketDto): String {
        return Gson().toJson(rocket)
    }

    @TypeConverter
    fun stringToLinks(value: String): LinkDto? {
        return Gson().fromJson(value, LinkDto::class.java)
    }

    @TypeConverter
    fun linksToString(links: LinkDto): String {
        return Gson().toJson(links)
    }
}