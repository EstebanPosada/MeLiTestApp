package com.estebanposada.testapp.domain

data class Item(
    var id: String,
    var siteId: String,
    var title: String,
    var price: Float,
    var condition: String?,
    var thumbnail: String?,
    var availableQuantity: Int,
    val attributes: List<Attribute>
)

data class Attribute (
    var id: String,
    var valueName: String?,
    var name: String,
)