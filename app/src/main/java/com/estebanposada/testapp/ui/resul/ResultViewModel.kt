package com.estebanposada.testapp.ui.resul

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estebanposada.testapp.data.source.SearchRepository
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
        Log.wtf("Searching", "Result - ${uiState.value.query}")
        viewModelScope.launch {
            val result = searchUseCase.execute(query)
            _uiState.update { it.copy(items = result, loading = false) }
        }
    }

    fun setQuery(query: String) {
        _uiState.update { it.copy(query = query) }
    }
}