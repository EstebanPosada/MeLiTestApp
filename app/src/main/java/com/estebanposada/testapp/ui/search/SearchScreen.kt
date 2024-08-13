package com.estebanposada.testapp.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.estebanposada.testapp.R
import com.estebanposada.testapp.domain.Item


@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    SearchScreenContent(
        uiState = uiState,
        modifier = modifier,
        onSearchChange = viewModel::searchItems
    )
}

@Composable
private fun SearchScreenContent(
    uiState: SearchUiState,
    modifier: Modifier = Modifier,
    onSearchChange: (String) -> Unit
) {
    val verticalArrangement = remember { mutableStateOf(Arrangement.Top) }
    verticalArrangement.value = if (uiState.items.isEmpty()) Arrangement.Center else Arrangement.Top
    val keyboardController = LocalSoftwareKeyboardController.current
    Box {
        Column(
            modifier.fillMaxSize(),
            verticalArrangement = verticalArrangement.value,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val search = remember { mutableStateOf("") }
            if (uiState.loading) {
                CircularProgressIndicator(
                    modifier = modifier.width(100.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            } else {
                Image(
                    modifier = modifier.aspectRatio(2f),
                    painter = painterResource(id = R.drawable.ic_mercado_libre),
                    contentScale = ContentScale.Inside,
                    contentDescription = "logo"
                )
                OutlinedTextField(
                    value = search.value,
                    onValueChange = { search.value = it },
                    label = { Text(text = "Search") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "search"
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onSearchChange(search.value)
                            keyboardController?.hide()
                        }
                    )
                )
                LazyColumn {
                    items(uiState.items) {
                        MeLiItem(
                            title = it.title,
                            image = it.thumbnail,
                            price = it.price,
                            quantity = it.availableQuantity
                        )
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchPreview() {
    SearchScreenContent(
        uiState = SearchUiState(loading = false, items = listOf(item))
    ) {}
}

val item = Item(
    id = "id",
    siteId = "siteId",
    title = "title",
    price = 0.0f,
    condition = "cond",
    thumbnail = "",
    availableQuantity = 1,
    attributes = emptyList()
)

@Preview(showBackground = true)
@Composable
private fun SearchPreview2() {
    SearchScreenContent(uiState = SearchUiState(loading = true)) {}
}