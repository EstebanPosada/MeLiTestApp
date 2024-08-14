package com.estebanposada.testapp.data.source

import com.estebanposada.testapp.app.server.ResultHandler
import com.estebanposada.testapp.domain.Item

interface SearchRepository {
    suspend fun search(query: String): ResultHandler<List<Item>>
    suspend fun findById(id: String): ResultHandler<Item>
    suspend fun deleteAll(): ResultHandler<Unit>
}