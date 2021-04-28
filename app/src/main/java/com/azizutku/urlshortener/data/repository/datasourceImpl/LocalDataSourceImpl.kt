package com.azizutku.urlshortener.data.repository.datasourceImpl

import com.azizutku.urlshortener.data.db.ShotsDataDao
import com.azizutku.urlshortener.data.model.Data
import com.azizutku.urlshortener.data.repository.datasource.LocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val shotsDataDao: ShotsDataDao) :
    LocalDataSource {

    override suspend fun getShotsDataFromDB(): List<Data> {
        return shotsDataDao.getShotsData()
    }

    override suspend fun saveShotsDataToDB(shotsDataList: List<Data>) {
        CoroutineScope(Dispatchers.IO).launch {
            shotsDataDao.saveShotsData(shotsDataList)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            shotsDataDao.deleteAllShotsData()
        }
    }
}