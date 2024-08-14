package com.estebanposada.testapp.domain

data class Item(
    var id: String,
    var siteId: String,
    var title: String,
    var price: Float,
    var currency: String,
    var condition: String?,
    var thumbnail: String?,
    var availableQuantity: Int,
    val attributeList: AttributeList
)

data class AttributeList(
    val attributes: List<Attribute>
)

data class Attribute(
    var id: String,
    var valueName: String?,
    var name: String,
)

val item = Item(
    id = "id",
    siteId = "siteId",
    title = "title",
    price = 0.0f,
    currency = "COP",
    condition = "cond",
    thumbnail = "",
    availableQuantity = 1,
    attributeList = AttributeList(emptyList())
)