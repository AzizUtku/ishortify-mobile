package com.azizutku.urlshortener.di

import com.azizutku.urlshortener.data.api.ApiService
import com.azizutku.urlshortener.data.db.ShotsDataDao
import com.azizutku.urlshortener.data.repository.RepositoryImpl
import com.azizutku.urlshortener.data.repository.datasource.CacheDataSource
import com.azizutku.urlshortener.data.repository.datasource.LocalDataSource
import com.azizutku.urlshortener.data.repository.datasource.RemoteDataSource
import com.azizutku.urlshortener.data.repository.datasourceImpl.CacheDataSourceImpl
import com.azizutku.urlshortener.data.repository.datasourceImpl.LocalDataSourceImpl
import com.azizutku.urlshortener.data.repository.datasourceImpl.RemoteDataSourceImpl
import com.azizutku.urlshortener.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@InstallIn(ActivityComponent::class)
@Module
object ShotsModule {
    @ActivityScoped
    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        cacheDataSource: CacheDataSource
    ): Repository {
        return RepositoryImpl(
            remoteDataSource,
            localDataSource,
            cacheDataSource
        )
    }

    @ActivityScoped
    @Provides
    fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource {
        return RemoteDataSourceImpl(apiService)
    }

    @ActivityScoped
    @Provides
    fun provideLocalDataSource(dao: ShotsDataDao): LocalDataSource {
        return LocalDataSourceImpl(dao)
    }

    @ActivityScoped
    @Provides
    fun provideCacheDataSource(): CacheDataSource {
        return CacheDataSourceImpl()
    }
}