package com.azizutku.urlshortener.data.repository.datasource

import com.azizutku.urlshortener.data.model.ShotsResponse
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getShots(): Response<ShotsResponse>
}