package com.estebanposada.testapp.data.repository

import com.estebanposada.testapp.app.server.SearchService
import com.estebanposada.testapp.app.server.Wrapper
import com.estebanposada.testapp.data.source.LocalDataSource
import com.estebanposada.testapp.data.source.SearchRepository
import com.estebanposada.testapp.domain.Item
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchService: SearchService,
    private val localDataSource: LocalDataSource
) : SearchRepository {
    override suspend fun search(query: String): List<Item> {
        if (localDataSource.isEmpty()) {
            val items = searchService.getItems(query)
            if (items.isSuccessful) {
                items.body()?.let { res ->
                    localDataSource.saveItems(res.results.map { it.asDbItem() })
                }
            }
        }
        return localDataSource.getItems().map { it.asItem() }
    }

    override suspend fun findById(id: String): Item = localDataSource.findById(id).asItem()
}