package com.azizutku.urlshortener.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.azizutku.urlshortener.data.model.Data

@Dao
interface ShotsDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveShotsData(shotsData: List<Data>)

    @Query("DELETE FROM shots_data")
    suspend fun deleteAllShotsData()

    @Query("SELECT * FROM shots_data")
    suspend fun getShotsData(): List<Data>
}