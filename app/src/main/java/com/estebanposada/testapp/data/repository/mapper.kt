package com.estebanposada.testapp.data.repository

import com.estebanposada.testapp.app.database.AttributeList
import com.estebanposada.testapp.app.database.Attribute as DbAttribute
import com.estebanposada.testapp.app.database.Item as DbItem
import com.estebanposada.testapp.app.server.ServiceAttribute
import com.estebanposada.testapp.app.server.ServiceItem
import com.estebanposada.testapp.domain.Attribute
import com.estebanposada.testapp.domain.Item

fun ServiceItem.asItem(): Item = Item(
    id = id,
    siteId = siteId,
    title = title,
    price = price,
    currency = currency,
    condition = condition ?: "",
    thumbnail = thumbnail ?: "",
    availableQuantity = availableQuantity,
    attributes = attributes.map { it.asAttribute() },
)

fun ServiceItem.asDbItem(): DbItem = DbItem(
    id = id,
    siteId = siteId,
    title = title,
    price = price,
    currency = currency,
    condition = condition ?: "",
    thumbnail = thumbnail ?: "",
    availableQuantity = availableQuantity,
    attributeList = AttributeList(attributes.map { it.asDbAttribute() }),
)

fun ServiceAttribute.asAttribute(): Attribute = Attribute(
    id = id,
    valueName = valueName,
    name = name
)

fun ServiceAttribute.asDbAttribute(): DbAttribute = DbAttribute(
    id = id,
    valueName = valueName ?: "",
    name = name
)

fun DbItem.asItem(): Item = Item(
    id = id,
    siteId = siteId,
    title = title,
    price = price,
    currency = currency,
    condition = condition ?: "",
    thumbnail = thumbnail ?: "",
    availableQuantity = availableQuantity,
    attributes = attributeList.attributes.map { it.asAttribute() }
)

fun DbAttribute.asAttribute(): Attribute = Attribute(
    id = id,
    valueName = valueName,
    name = name
)