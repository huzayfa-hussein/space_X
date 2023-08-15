package com.hu.spacex.data

import com.hu.spacex.data.common.Resource
import com.hu.spacex.data.dto.CompanyInfoDto
import com.hu.spacex.data.dto.LaunchDto
import com.hu.spacex.data.remote.ApiService
import com.hu.spacex.data.remote.dataSource.BaseRemoteDataSource
import com.hu.spacex.data.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AppRepositoryImpl(private val apiService: ApiService) : AppRepository,
    BaseRemoteDataSource() {

    override fun fetchCompanyInfo(): Flow<Resource<CompanyInfoDto>> {
        return flow {
            emit(handleApiCall { apiService.fetchCompanyInfo() })
        }.flowOn(Dispatchers.IO)
    }

    override fun fetchLaunches(): Flow<Resource<List<LaunchDto>>> {
        return flow {
            emit(handleApiCall { apiService.fetchAllLaunches() })
        }.flowOn(Dispatchers.IO)
    }
}