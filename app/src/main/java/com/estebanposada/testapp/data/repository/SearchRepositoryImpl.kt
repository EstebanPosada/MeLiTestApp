package com.estebanposada.testapp.data.repository

import com.estebanposada.testapp.app.server.SearchService
import com.estebanposada.testapp.data.source.LocalDataSource
import com.estebanposada.testapp.data.source.SearchRepository
import com.estebanposada.testapp.domain.Item
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchService: SearchService,
    private val localDataSource: LocalDataSource
) : SearchRepository {
    override suspend fun search(query: String): List<Item> {
        val response = searchService.getItems(query)
        response.body()?.let { res ->
            return res.results.map { it.asItem() }
        }
        return listOf()
    }

}