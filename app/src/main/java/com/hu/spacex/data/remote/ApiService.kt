package com.hu.spacex.data.remote

import com.hu.spacex.data.dto.CompanyInfoDto
import com.hu.spacex.data.dto.LaunchDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/v3/info")
    suspend fun fetchCompanyInfo(): Response<CompanyInfoDto>

    @GET("/v3/launches")
    suspend fun fetchAllLaunches(): Response<List<LaunchDto>>
}