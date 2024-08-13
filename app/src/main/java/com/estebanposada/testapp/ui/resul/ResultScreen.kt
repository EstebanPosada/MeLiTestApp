package com.estebanposada.testapp.ui.resul

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.estebanposada.testapp.domain.item
import com.estebanposada.testapp.ui.search.MeLiItem

@Composable
fun ResultScreen(
    query: String,
    modifier: Modifier = Modifier,
    viewModel: ResultViewModel = hiltViewModel(),
    navigate: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    viewModel.setQuery(query)
    viewModel.searchItems(query)
    ResultScreenContent(
        modifier = modifier,
        uiState = uiState,
        navigate = navigate
    )
}

@Composable
private fun ResultScreenContent(
    modifier: Modifier = Modifier,
    uiState: ResultUiState,
    navigate: (String) -> Unit
) {
    if (uiState.loading) {
        CircularProgressIndicator(
            modifier = modifier.width(100.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    } else {
        Column {
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                text = uiState.query,
                textAlign = TextAlign.Center,
                fontSize = 30.sp
            )
            LazyColumn {
                items(uiState.items) {
                    MeLiItem(modifier = modifier.clickable { navigate(it.id) }, item = it)
                    HorizontalDivider()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ResultPreview() {
    ResultScreenContent(uiState = ResultUiState(query = "Query", items = listOf(item, item))) {}
}

@Preview(showBackground = true)
@Composable
private fun ResultPreviewLoading() {
    ResultScreenContent(uiState = ResultUiState(loading = true)) {}
}