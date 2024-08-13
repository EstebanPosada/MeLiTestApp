package com.estebanposada.testapp.ui.detail

import com.estebanposada.testapp.domain.Item
import com.estebanposada.testapp.domain.item as DefItem

data class DetailUiState(
    val loading: Boolean = true,
    val item: Item = DefItem
)