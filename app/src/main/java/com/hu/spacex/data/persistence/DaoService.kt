package com.hu.spacex.data.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hu.spacex.data.dto.CompanyInfoDto
import com.hu.spacex.data.dto.LaunchDto
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoService {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompanyInfo(companyInfoDto: CompanyInfoDto)

    @Query("select * from info")
    suspend fun getCompanyInfoData(): CompanyInfoDto?

    @Query("delete from info")
    suspend fun deleteCompanyInfo()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllLaunches(launches: List<LaunchDto>)

    @Query("select * from launches")
    suspend fun getAllLaunches(): List<LaunchDto>?

    @Query("delete from launches")
    suspend fun deleteAllLaunches()
}