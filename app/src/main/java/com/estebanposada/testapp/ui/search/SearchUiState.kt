package com.estebanposada.testapp.ui.search

import com.estebanposada.testapp.domain.Item

data class SearchUiState(
    val loading: Boolean = false,
    val items : List<Item> = emptyList()
)
