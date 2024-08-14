package com.estebanposada.testapp.ui.detail


import com.estebanposada.testapp.app.server.ResultHandler
import com.estebanposada.testapp.domain.Item
import com.estebanposada.testapp.domain.item
import com.estebanposada.testapp.ui.MainDispatcherRule
import com.estebanposada.testapp.usecase.FindItemByIdUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val findItemByIdUseCase = mockk<FindItemByIdUseCase>()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        viewModel = DetailViewModel(findItemByIdUseCase)
    }

    @Test
    fun `When getItem is called, and response is success, then modify uiState with data`() {
        val response = ResultHandler.Success(item)
        coEvery { findItemByIdUseCase.execute(any()) } returns response
        viewModel.getItem("id")
        val uiState = viewModel.uiState

        assertEquals(response.data, uiState.value.item)
        coVerify { viewModel.getItem(any()) }
    }

    @Test
    fun `When searchItem is called, and an error happen, then response is not null`() {
        val response: ResultHandler<Item> = ResultHandler.Error("")
        coEvery { findItemByIdUseCase.execute(any()) } returns response
        viewModel.getItem("id")

        assertNotNull(response)

        coVerify { viewModel.getItem(any()) }
    }
}