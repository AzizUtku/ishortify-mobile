package com.azizutku.urlshortener.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.azizutku.urlshortener.data.model.Data
import com.azizutku.urlshortener.utils.DataTypeConverter

@Database(
    entities = [Data::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DataTypeConverter::class)
abstract class Database : RoomDatabase() {
    abstract fun shotsDataDao(): ShotsDataDao
}