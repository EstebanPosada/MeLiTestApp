package com.estebanposada.testapp.usecase

import com.estebanposada.testapp.app.server.ResultHandler
import com.estebanposada.testapp.data.source.SearchRepository
import com.estebanposada.testapp.domain.item
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class GetItemsUseCaseTest {

    private lateinit var getItemsUseCase: GetItemsUseCase
    private val repository = mockk<SearchRepository>()

    @Before
    fun setup(){
        getItemsUseCase = GetItemsUseCase(repository)
    }

    @Test
    fun `When call FindById, and it's successful then return response`(): Unit = runTest {
        val response = ResultHandler.Success(listOf(item))
        coEvery { repository.search(any()) } returns response

        val result = getItemsUseCase.execute("asd")

        assertEquals(response, result)

        coVerify { repository.search(any()) }
    }
}