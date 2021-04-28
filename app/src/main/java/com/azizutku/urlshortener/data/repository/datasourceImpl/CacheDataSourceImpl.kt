package com.azizutku.urlshortener.data.repository.datasourceImpl

import com.azizutku.urlshortener.data.model.Data
import com.azizutku.urlshortener.data.repository.datasource.CacheDataSource

class CacheDataSourceImpl : CacheDataSource {
    private var shotsData: List<Data>? = null

    override suspend fun getShotsDataFromCache(): List<Data>? {
        return shotsData
    }

    override suspend fun saveShotsDataToCache(shotsData: List<Data>) {
        this.shotsData = shotsData
    }
}