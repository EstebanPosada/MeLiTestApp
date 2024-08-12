package com.estebanposada.testapp.data.source

import com.estebanposada.testapp.domain.Item

interface LocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun saveItems(movies: List<Item>)
    suspend fun getItems(): List<Item>
    suspend fun findById(id: Int): Item
    suspend fun update(movie: Item)
}