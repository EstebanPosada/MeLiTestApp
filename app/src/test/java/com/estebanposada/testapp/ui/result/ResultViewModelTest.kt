package com.estebanposada.testapp.ui.result

import com.estebanposada.testapp.app.server.ResultHandler
import com.estebanposada.testapp.domain.Item
import com.estebanposada.testapp.domain.item
import com.estebanposada.testapp.ui.MainDispatcherRule
import com.estebanposada.testapp.usecase.GetItemsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ResultViewModelTest {

    private lateinit var viewModel: ResultViewModel
    private val getItemsUseCase = mockk<GetItemsUseCase>()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        viewModel = ResultViewModel(getItemsUseCase)
    }

    @Test
    fun `When setQuery is called, then update uiState value`() {
        val query = "query"

        viewModel.setQuery(query)
        val queryValue = viewModel.uiState.value.query
        assertEquals(query, queryValue)
    }

    @Test
    fun `When searchItem is called, and response is success then modify uiState with items`() {
        val query = "q"
        val list = listOf(item)
        val response = ResultHandler.Success(list)
        coEvery { getItemsUseCase.execute(any()) } returns response
        viewModel.searchItems(query)
        val uiState = viewModel.uiState

        assertEquals(response.data, uiState.value.items)

        coVerify { getItemsUseCase.execute(any()) }
    }

    @Test
    fun `When searchItem is called, and an error happen, then response is not null`() {
        val query = "q"
        val response: ResultHandler<List<Item>> = ResultHandler.Error(error = "")
        coEvery { getItemsUseCase.execute(any()) } returns response
        viewModel.searchItems(query)

        assertNotNull(response)

        coVerify { getItemsUseCase.execute(any()) }
    }
}