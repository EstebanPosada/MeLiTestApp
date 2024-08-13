package com.estebanposada.testapp.data.repository

import com.estebanposada.testapp.app.server.ServiceAttribute
import com.estebanposada.testapp.app.server.ServiceItem
import com.estebanposada.testapp.domain.Attribute
import com.estebanposada.testapp.domain.Item

fun ServiceItem.asItem(): Item = Item(
    id = id,
    siteId = siteId,
    title = title,
    price = price,
    condition = condition ?: "",
    thumbnail = thumbnail ?: "",
    availableQuantity = availableQuantity,
    attributes = attributes.map { it.asAttribute() },
)

fun ServiceAttribute.asAttribute(): Attribute = Attribute(
    id = id,
    valueName = valueName,
    name = name
)