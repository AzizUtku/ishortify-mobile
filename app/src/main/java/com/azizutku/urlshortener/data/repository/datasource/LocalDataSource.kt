package com.azizutku.urlshortener.data.repository.datasource

import com.azizutku.urlshortener.data.model.Data

interface LocalDataSource {
    suspend fun getShotsDataFromDB(): List<Data>?
    suspend fun saveShotsDataToDB(shotsDataList: List<Data>)
    suspend fun clearAll()
}