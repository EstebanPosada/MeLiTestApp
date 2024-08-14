package com.estebanposada.testapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estebanposada.testapp.usecase.DeleteAllUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val deleteAllUseCase: DeleteAllUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState>
        get() = _uiState.asStateFlow()

    fun refreshData() {
        viewModelScope.launch {
            deleteAllUseCase.execute()
        }
    }

    fun onSearchChange(query: String) {
        _uiState.update { it.copy(query = query) }
    }

    fun onDeleteQuery() {
        _uiState.update { it.copy(query = "") }
    }
}