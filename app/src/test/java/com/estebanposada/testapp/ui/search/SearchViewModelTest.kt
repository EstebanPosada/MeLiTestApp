package com.estebanposada.testapp.ui.search


import com.estebanposada.testapp.app.server.ResultHandler
import com.estebanposada.testapp.ui.MainDispatcherRule
import com.estebanposada.testapp.usecase.DeleteAllUseCase
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchViewModelTest {

    private lateinit var viewModel: SearchViewModel
    private val deleteAllUseCase = mockk<DeleteAllUseCase>()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        viewModel = SearchViewModel(deleteAllUseCase)
    }

    @Test
    fun `When onSearchChange is called, then update uiState value`() {
        val query = "query"

        viewModel.onSearchChange(query)
        val queryValue = viewModel.uiState.value.query
        assertEquals(query, queryValue)
    }

    @Test
    fun `When onDeleteQuery is called, then update uiState value`() {

        viewModel.onDeleteQuery()
        val queryValue = viewModel.uiState.value.query
        assertEquals("", queryValue)
    }

    @Test
    fun `When refreshData is called, then `() {

        val response = ResultHandler.Success(Unit)
        coEvery { deleteAllUseCase.execute() } returns response
        viewModel.refreshData()

        assertEquals(response.data, Unit)
    }
}