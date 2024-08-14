package com.estebanposada.testapp.data.source

import com.estebanposada.testapp.app.database.Item

interface LocalDataSource {
    suspend fun saveItems(items: List<Item>)
    suspend fun getItems(): List<Item>
    suspend fun findById(id: String): Item
    suspend fun isEmpty(): Boolean
    suspend fun deleteAll()
}