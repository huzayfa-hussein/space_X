package com.hu.spacex.data

import com.hu.spacex.data.common.Resource
import com.hu.spacex.data.common.onError
import com.hu.spacex.data.common.onSuccess
import com.hu.spacex.data.dto.CompanyInfoDto
import com.hu.spacex.data.dto.LaunchDto
import com.hu.spacex.data.persistence.DaoService
import com.hu.spacex.data.remote.ApiService
import com.hu.spacex.data.remote.dataSource.BaseRemoteDataSource
import com.hu.spacex.data.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AppRepositoryImpl(private val apiService: ApiService, private val daoService: DaoService) :
    AppRepository,
    BaseRemoteDataSource() {

    override fun fetchCompanyInfo(): Flow<Resource<CompanyInfoDto>> {
        return flow {
            val localData = daoService.getCompanyInfoData()
            if (localData != null) {
                emit(Resource.Success(localData))
            }
            val remoteData = handleApiCall { apiService.fetchCompanyInfo() }
            remoteData.onSuccess {
                daoService.deleteCompanyInfo()
                daoService.insertCompanyInfo(it)
                emit(Resource.Success(it))
            }.onError { message, result ->
                emit(Resource.Error(message = message, data = result))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun fetchLaunches(): Flow<Resource<List<LaunchDto>>> {
        return flow {
            val localData = daoService.getAllLaunches()
            if (!localData.isNullOrEmpty()) {
                emit(Resource.Success(localData))
            }
            val remoteData = handleApiCall { apiService.fetchAllLaunches() }
            remoteData.onSuccess {
                daoService.deleteAllLaunches()
                daoService.insertAllLaunches(it)
                emit(Resource.Success(it))
            }.onError { message, result ->
                emit(Resource.Error(message = message, data = result))
            }
        }.flowOn(Dispatchers.IO)
    }
}