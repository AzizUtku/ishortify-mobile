package com.azizutku.urlshortener.data.repository.datasourceImpl

import com.azizutku.urlshortener.data.api.ApiService
import com.azizutku.urlshortener.data.model.ShotsResponse
import com.azizutku.urlshortener.data.repository.datasource.RemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : RemoteDataSource {
    override suspend fun getShots(
    ): Response<ShotsResponse> = apiService.getShots()
}

