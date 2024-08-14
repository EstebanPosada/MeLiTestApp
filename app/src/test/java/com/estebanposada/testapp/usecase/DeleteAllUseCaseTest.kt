package com.estebanposada.testapp.usecase

import com.estebanposada.testapp.app.server.ResultHandler
import com.estebanposada.testapp.data.source.SearchRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class DeleteAllUseCaseTest {

    private lateinit var deleteAllUseCase: DeleteAllUseCase
    private val repository = mockk<SearchRepository>()

    @Before
    fun setUp() {
        deleteAllUseCase = DeleteAllUseCase(repository)
    }

    @Test
    fun `When deleteAll is called, and it's successful, then return unit`() = runTest {
        val response = ResultHandler.Success(Unit)
        coEvery { repository.deleteAll() } returns response

        val result = deleteAllUseCase.execute()
        assertEquals(response, result)

        coVerify { repository.deleteAll() }
    }
}