package com.estebanposada.testapp.app.database

import com.estebanposada.testapp.data.source.LocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(private val dao: ItemDao) : LocalDataSource {
    override suspend fun isEmpty(): Boolean = withContext(Dispatchers.IO) { dao.itemCount() <= 0 }

    override suspend fun saveItems(items: List<Item>) =
        withContext(Dispatchers.IO) { dao.insertItems(items) }

    override suspend fun getItems(): List<Item> = withContext(Dispatchers.IO) { dao.getAll() }

    override suspend fun findById(id: String): Item = withContext(Dispatchers.IO) { dao.findById(id) }
}