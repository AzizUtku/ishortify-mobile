package com.azizutku.urlshortener.domain.repository

import com.azizutku.urlshortener.data.model.Data
import com.azizutku.urlshortener.utils.DataState
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getShotsData(): Flow<DataState<List<Data>>>
    suspend fun updateShotsData(): Flow<DataState<List<Data>>>
}