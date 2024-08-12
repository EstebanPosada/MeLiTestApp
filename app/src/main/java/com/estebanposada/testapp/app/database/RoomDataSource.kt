package com.estebanposada.testapp.app.database

import com.estebanposada.testapp.data.source.LocalDataSource
import com.estebanposada.testapp.domain.Item

class RoomDataSource(db: ItemDatabase): LocalDataSource {
    override suspend fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun saveItems(movies: List<Item>) {
        TODO("Not yet implemented")
    }

    override suspend fun getItems(): List<Item> {
        TODO("Not yet implemented")
    }

    override suspend fun findById(id: Int): Item {
        TODO("Not yet implemented")
    }

    override suspend fun update(movie: Item) {
        TODO("Not yet implemented")
    }
}