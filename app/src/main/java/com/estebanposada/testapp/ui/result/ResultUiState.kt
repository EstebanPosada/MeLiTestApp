package com.estebanposada.testapp.ui.result

import com.estebanposada.testapp.domain.Item

data class ResultUiState(
    val loading: Boolean = false,
    val query: String = "",
    val isError: Boolean = false,
    val items: List<Item> = emptyList()
)
