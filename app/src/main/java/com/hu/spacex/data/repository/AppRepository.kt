package com.hu.spacex.data.repository

import com.hu.spacex.data.common.Resource
import com.hu.spacex.data.dto.CompanyInfoDto
import com.hu.spacex.data.dto.LaunchDto
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    fun fetchCompanyInfo(): Flow<Resource<CompanyInfoDto>>

    fun fetchLaunches(): Flow<Resource<List<LaunchDto>>>
}