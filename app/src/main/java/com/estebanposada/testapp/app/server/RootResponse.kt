package com.estebanposada.testapp.app.server

import com.google.gson.annotations.SerializedName

data class RootResponse(
    val results: List<ServiceItem>
)

data class ServiceItem(
    var id: String,
    @SerializedName("site_id")
    var siteId: String,
    var title: String,
    var price: Float,
    @SerializedName("currency_id")
    var currency: String,
    var condition: String?,
    var thumbnail: String?,
    @SerializedName("available_quantity")
    var availableQuantity: Int,
    val attributes: List<ServiceAttribute>
)

data class ServiceAttribute(
    var id: String,
    var valueName: String?,
    var name: String,
)