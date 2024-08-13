package com.estebanposada.testapp.app.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    @PrimaryKey
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
    var valueName: String,
    var name: String,
)
