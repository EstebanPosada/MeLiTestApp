package com.estebanposada.testapp.data.repository

import com.estebanposada.testapp.app.server.ResultHandler
import com.estebanposada.testapp.app.server.RootResponse
import com.estebanposada.testapp.app.server.SearchService
import com.estebanposada.testapp.data.source.LocalDataSource
import com.estebanposada.testapp.data.source.SearchRepository
import com.estebanposada.testapp.domain.Item
import com.estebanposada.testapp.domain.item
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class SearchRepositoryImplTest {

    private lateinit var repository: SearchRepository
    private val searchService = mockk<SearchService>()
    private val localDataSource = mockk<LocalDataSource>()

    @Before
    fun setUp() {
        repository = SearchRepositoryImpl(searchService, localDataSource)
    }

    @Test
    fun `When  search is called, db is empty, then return empty list`() = runTest {
        val response = ResultHandler.Success(listOf<Item>())
        coEvery { localDataSource.isEmpty() } returns false
        coEvery { localDataSource.getItems() } returns emptyList()

        val result = repository.search("q")

        assertEquals(response, result)

        coVerify { localDataSource.isEmpty() }
        coVerify { localDataSource.getItems() }
    }

    @Test
    fun `When  search is called, db is not empty and getItems is successful, then return data`() =
        runTest {
            val response = ResultHandler.Success(listOf<Item>())
            val servResponse = mockk<Response<RootResponse>>(relaxed = true)
            coEvery { localDataSource.isEmpty() } returns true
            coEvery { searchService.getItems(any()) } returns servResponse
            coEvery { localDataSource.getItems() } returns emptyList()

            val result = repository.search("q")

            assertEquals(response, result)

            coVerify { localDataSource.isEmpty() }
            coVerify { searchService.getItems(any()) }
        }

    @Test
    fun `When  findById is called, and it's a successful response, then return data`() = runTest {
        val response = ResultHandler.Success(item.asDbItem())
        coEvery { localDataSource.findById(any()) } returns response.data

        val result = repository.findById("id")

        assertNotNull(result)

        coVerify { localDataSource.findById(any()) }
    }

    @Test
    fun `When  deleteAll is called, and it's a successful response, then return Unit`() = runTest {
        val response = ResultHandler.Success(Unit)
        coEvery { localDataSource.deleteAll() } returns response.data

        val result = repository.deleteAll()

        assertEquals(response, result)

        coVerify { localDataSource.deleteAll() }
    }
}