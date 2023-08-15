package com.hu.spacex.data.remote.dataSource

import com.hu.spacex.data.common.Resource
import retrofit2.HttpException
import retrofit2.Response

open class BaseRemoteDataSource {

    suspend fun <T : Any> handleApiCall(
        request: suspend () -> Response<T>
    ): Resource<T> {
        return try {
            val result = request.invoke()
            if (result.isSuccessful && result.body() != null) {
                Resource.Success(data = result.body()!!)
            } else {
                Resource.Error(
                    message = result.message()
                )
            }
        } catch (e: HttpException) {
            Resource.Error(message = e.message())
        } catch (e: Exception) {
            Resource.Error(message = e.message ?: "")
        }

    }
}