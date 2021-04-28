package com.azizutku.urlshortener.domain.usecase

import com.azizutku.urlshortener.data.model.Data
import com.azizutku.urlshortener.domain.repository.Repository
import com.azizutku.urlshortener.utils.DataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateUrlsUseCase @Inject constructor(private val repository: Repository) {
    suspend fun execute(): Flow<DataState<List<Data>>> = repository.updateShotsData()
}