package com.estebanposada.testapp.app.database

import androidx.room.Entity

@Entity
data class Item(
    var id: String,
    var siteId: String,
    var title: String,
    var price: Int,
    var availableQuantity: Int,
    val attributes: List<Attribute>
)

data class Attribute (
    var id: String,
    var valueName: String,
    var name: String,
)
