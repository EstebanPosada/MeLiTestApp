package com.estebanposada.testapp.data.repository

import com.estebanposada.testapp.app.server.ResultHandler
import com.estebanposada.testapp.app.server.SearchService
import com.estebanposada.testapp.data.source.LocalDataSource
import com.estebanposada.testapp.data.source.SearchRepository
import com.estebanposada.testapp.domain.Item
import retrofit2.HttpException
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchService: SearchService,
    private val localDataSource: LocalDataSource
) : SearchRepository {
    override suspend fun search(query: String): ResultHandler<List<Item>> = try {
        if (localDataSource.isEmpty()) {
            val result = searchService.getItems(query)
            if (result.isSuccessful) {
                result.body()?.let { res ->
                    localDataSource.saveItems(res.results.map { it.asDbItem() })
                }
            }
        }
        ResultHandler.Success(localDataSource.getItems().map { it.asItem() })
    } catch (e: HttpException) {
        e.printStackTrace()
        ResultHandler.Error(e.message())
    } catch (e: Exception) {
        e.printStackTrace()
        ResultHandler.Error(e.message ?: "")
    }

    override suspend fun findById(id: String): ResultHandler<Item> =
        ResultHandler.Success(localDataSource.findById(id).asItem())

    override suspend fun deleteAll(): ResultHandler<Unit> {
        return ResultHandler.Success(localDataSource.deleteAll())
    }
}