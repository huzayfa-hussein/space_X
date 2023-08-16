package com.hu.spacex.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hu.spacex.data.dto.CompanyInfoDto
import com.hu.spacex.data.dto.LaunchDto

@Database(entities = [CompanyInfoDto::class, LaunchDto::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun daoService(): DaoService
}