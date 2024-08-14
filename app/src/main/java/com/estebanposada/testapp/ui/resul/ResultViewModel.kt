package com.estebanposada.testapp.ui.resul

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estebanposada.testapp.app.server.ResultHandler
import com.estebanposada.testapp.usecase.GetItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(private val searchUseCase: GetItemsUseCase) :
    ViewModel() {
    private val _uiState = MutableStateFlow(ResultUiState())
    val uiState: StateFlow<ResultUiState>
        get() = _uiState.asStateFlow()

    init {
        _uiState.update { it.copy(loading = true) }
    }

    fun searchItems(query: String) {
        viewModelScope.launch {
            when (val result = searchUseCase.execute(query)) {
                is ResultHandler.Error -> _uiState.update {
                    it.copy(loading = false, isError = true)
                }

                is ResultHandler.Success -> _uiState.update {
                    it.copy(items = result.data, isError = false, loading = false)
                }
            }
        }
    }

    fun setQuery(query: String) {
        _uiState.update { it.copy(query = query) }
    }
}