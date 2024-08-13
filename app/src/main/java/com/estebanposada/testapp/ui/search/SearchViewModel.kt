package com.estebanposada.testapp.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState>
        get() = _uiState.asStateFlow()

    fun onSearchChange(query: String) {
        Log.wtf("Searching", query)
        _uiState.update { it.copy(query = query) }
    }

    fun onDeleteQuery() {
        _uiState.update { it.copy(query = "") }
    }
}