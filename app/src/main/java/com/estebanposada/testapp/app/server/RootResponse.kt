package com.estebanposada.testapp.app.server

import com.estebanposada.testapp.domain.Attribute
import com.google.gson.annotations.SerializedName

data class RootResponse(
    val results: List<Item>
)

data class Item(
    var id: String,
    @SerializedName("site_id")
    var siteId: String,
    var title: String,
    var price: Int,
    @SerializedName("available_quantity")
    var availableQuantity: Int,
    val attributes: List<Attribute>
)