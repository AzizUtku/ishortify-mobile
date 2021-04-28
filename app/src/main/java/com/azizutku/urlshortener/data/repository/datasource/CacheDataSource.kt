package com.azizutku.urlshortener.data.repository.datasource

import com.azizutku.urlshortener.data.model.Data

interface CacheDataSource {
    suspend fun getShotsDataFromCache(): List<Data>?
    suspend fun saveShotsDataToCache(dataList: List<Data>)

}