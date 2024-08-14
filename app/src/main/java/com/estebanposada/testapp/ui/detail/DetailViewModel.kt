package com.estebanposada.testapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estebanposada.testapp.app.server.ResultHandler
import com.estebanposada.testapp.usecase.FindItemByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val findItemByIdUseCase: FindItemByIdUseCase) :
    ViewModel() {
    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState>
        get() = _uiState.asStateFlow()

    init {
        _uiState.update { it.copy(loading = true) }
    }

    fun getItem(id: String) {
        viewModelScope.launch {
            when (val result = findItemByIdUseCase.execute(id)) {
                is ResultHandler.Success -> _uiState.update { it.copy(item = result.data, loading = false) }
                is ResultHandler.Error -> _uiState.update { it.copy() }
            }
        }
    }
}