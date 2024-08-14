package com.estebanposada.testapp.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.estebanposada.testapp.R


@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    navigate: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    SearchScreenContent(
        uiState = uiState,
        modifier = modifier,
        onSearchChange = viewModel::onSearchChange,
        onDeleteQuery = viewModel::onDeleteQuery,
        navigate = {
            viewModel.refreshData()
            navigate(it)
        }
    )
}

@Composable
private fun SearchScreenContent(
    uiState: SearchUiState,
    modifier: Modifier = Modifier,
    onSearchChange: (String) -> Unit,
    onDeleteQuery: () -> Unit,
    navigate: (String) -> Unit
) {
    Box {
        Column(
            modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_mercado_libre),
                contentScale = ContentScale.Inside,
                contentDescription = "logo"
            )
            OutlinedTextField(
                value = uiState.query,
                onValueChange = {
                    onSearchChange(it)
                },
                label = { Text(text = "Search") },
                trailingIcon = {
                    if (uiState.query.isNotBlank())
                        IconButton(onClick = onDeleteQuery) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
                        }
                },
                leadingIcon = {
                    IconButton(onClick = {
                        navigate(uiState.query)
                    }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "search")
                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        navigate(uiState.query)
                    }
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchPreview() {
    SearchScreenContent(
        uiState = SearchUiState(loading = false), onSearchChange = {}, onDeleteQuery = {}
    ) {}
}