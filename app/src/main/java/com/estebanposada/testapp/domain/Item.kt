package com.estebanposada.testapp.domain

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


/*class Result {
    var id: String? = null
    var site_id: String? = null
    var title: String? = null
    var seller: Seller? = null
    var price: Int = 0
    var currency_id: String? = null
    var available_quantity: Int = 0
    var buying_mode: String? = null
    var listing_type_id: String? = null
    var stop_time: Date? = null
    var condition: String? = null
    var permalink: String? = null
    var thumbnail: String? = null
    var accepts_mercadopago: Boolean = false
    var installments: Installments? = null
    var shipping: Shipping? = null
    var attributes: ArrayList<Attribute>? = null
    var differential_pricing: DifferentialPricing? = null
    var original_price: Any? = null
    var category_id: String? = null
    var official_store_id: Any? = null
    var catalog_product_id: String? = null
    var tags: ArrayList<String>? = null
    var catalog_listing: Boolean = false
    var address: Address? = null
    var seller_address: SellerAddress? = null
}*/