package com.estebanposada.testapp.ui.detail

import com.estebanposada.testapp.domain.Item

data class DetailUiState(
    val loading: Boolean = true,
    val item: Item = Item(
        id = "",
        siteId = "",
        title = "",
        price = 0.0f,
        condition = "",
        thumbnail = "",
        availableQuantity = 0,
        attributes = emptyList(),
    )
)