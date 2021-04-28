package com.azizutku.urlshortener.di

import android.content.Context
import androidx.room.Room
import com.azizutku.urlshortener.data.db.Database
import com.azizutku.urlshortener.data.db.ShotsDataDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext appContext: Context): Database {
        return Room.databaseBuilder(appContext, Database::class.java, "links_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideShotsDataDao(database: Database): ShotsDataDao {
        return database.shotsDataDao()
    }
}