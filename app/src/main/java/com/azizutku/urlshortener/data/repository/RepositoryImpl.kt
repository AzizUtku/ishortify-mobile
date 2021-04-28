package com.azizutku.urlshortener.data.repository

import com.azizutku.urlshortener.data.model.Data
import com.azizutku.urlshortener.data.repository.datasource.CacheDataSource
import com.azizutku.urlshortener.data.repository.datasource.LocalDataSource
import com.azizutku.urlshortener.data.repository.datasource.RemoteDataSource
import com.azizutku.urlshortener.domain.repository.Repository
import com.azizutku.urlshortener.utils.DataState
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val cacheDataSource: CacheDataSource,
) : Repository {
    override suspend fun getShotsData(): Flow<DataState<List<Data>>> {
        return getUserFromCache()
    }

    override suspend fun updateShotsData(): Flow<DataState<List<Data>>> {
        return getUserFromAPI()
    }

    private suspend fun getUserFromAPI(): Flow<DataState<List<Data>>> = flow {
        Timber.d("getting shots data from api")
        emit(DataState.Loading)
        try {
            val response = remoteDataSource.getShots()
            val body = response.body()
            if (body != null && body.success!!) {
                val shotsData: List<Data> = body.data!!

                // Update data
                localDataSource.clearAll()
                localDataSource.saveShotsDataToDB(shotsData)
                cacheDataSource.saveShotsDataToCache(shotsData)

                // Emit data
                emit(DataState.Success(shotsData))
            } else {
                emit(DataState.Error(Exception("API Error")))
            }
        } catch (exception: Exception) {
            Timber.i(exception.message.toString())
            emit(DataState.Error(exception))
        }
    }

    private suspend fun getUserFromDB(): Flow<DataState<List<Data>>> = flow {
        Timber.d("getting shots data from db")
        val shotsData: List<Data>?
        try {
            shotsData = localDataSource.getShotsDataFromDB()

            if (shotsData?.size == 0 || shotsData == null) {
                emitAll(getUserFromAPI())
            } else {
                cacheDataSource.saveShotsDataToCache(shotsData)
                emit(DataState.Success(shotsData))
            }
        } catch (exception: Exception) {
            Timber.i(exception.message.toString())
            emit(DataState.Error(exception))
        }
    }

    private suspend fun getUserFromCache(): Flow<DataState<List<Data>>> = flow {
        Timber.d("getting shots data from cache")
        emit(DataState.Loading)
        var shotsData: List<Data>? = null
        try {
            shotsData = cacheDataSource.getShotsDataFromCache()
        } catch (exception: Exception) {
            emit(DataState.Error(exception))
        }
        if (shotsData != null) {
            emit(DataState.Success(shotsData))
        } else {
            emitAll(getUserFromDB())
        }
    }
}