package com.azizutku.urlshortener.presentation.activity


import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.azizutku.urlshortener.data.model.Data
import com.azizutku.urlshortener.domain.usecase.GetUrlsUseCase
import com.azizutku.urlshortener.domain.usecase.UpdateUrlsUseCase
import com.azizutku.urlshortener.utils.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainViewModel @ViewModelInject constructor(
    private val getUrlsUseCase: GetUrlsUseCase,
    private val updateUrlsUseCase: UpdateUrlsUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Data>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<Data>>>
        get() = _dataState

    fun getShotsData() {
        viewModelScope.launch {
            getUrlsUseCase.execute().onEach {
                _dataState.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun updateShotsData() {
        viewModelScope.launch {
            updateUrlsUseCase.execute().onEach {
                _dataState.value = it
            }.launchIn(viewModelScope)
        }
    }
}