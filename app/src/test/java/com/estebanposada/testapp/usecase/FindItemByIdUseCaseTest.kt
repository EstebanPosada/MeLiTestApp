package com.estebanposada.testapp.usecase

import com.estebanposada.testapp.app.server.ResultHandler
import com.estebanposada.testapp.data.source.SearchRepository
import com.estebanposada.testapp.domain.Item
import com.estebanposada.testapp.domain.item
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class FindItemByIdUseCaseTest{

    private lateinit var findItemByIdUseCase: FindItemByIdUseCase
    private val repository : SearchRepository = mockk()

    @Before
    fun setUp() {
        findItemByIdUseCase = FindItemByIdUseCase(repository)
    }

    @Test
    fun `When call FindById, and it's successful then return response`(): Unit = runTest {
        val response = ResultHandler.Success(item)
        coEvery { repository.findById(any()) } returns response

        val result = findItemByIdUseCase.execute("asd")

        assertEquals(response, result)

        coVerify { repository.findById(any()) }
    }

    @Test
    fun `When call FindById, and an error happens then return error`(): Unit = runTest {
        //When
        val response: ResultHandler<Item> = ResultHandler.Error("error")
        coEvery { repository.findById(any()) } returns response

        val result = findItemByIdUseCase.execute("asd")

        assertEquals(response, result)

        coVerify { repository.findById(any()) }
    }
}