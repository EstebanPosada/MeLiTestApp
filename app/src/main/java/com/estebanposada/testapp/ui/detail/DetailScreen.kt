package com.estebanposada.testapp.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.estebanposada.testapp.R
import com.estebanposada.testapp.domain.Attribute
import com.estebanposada.testapp.ui.resul.item

@Composable
fun DetailScreen(
    id: String,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    viewModel.getItem(id)
    DetailScreenContent(uiState, modifier)
}

@Composable
private fun DetailScreenContent(
    uiState: DetailUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = modifier.fillMaxWidth(),
            text = uiState.item.title,
            textAlign = TextAlign.Start
        )
        Text(text = uiState.item.condition ?: "")
        AsyncImage(
            modifier = modifier.height(200.dp),
            model = uiState.item.thumbnail, contentDescription = uiState.item.title
        )
        Text(text = uiState.item.price.toString())
        Text(text = stringResource(id = R.string.attributes))
        uiState.item.attributes.forEach {
            Text(text = it.name)
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun DetailPreview() {
    DetailScreenContent(
        DetailUiState(
            item = item.copy(
                attributes = listOf(
                    Attribute(id = "asd", valueName = "value", name = "name"),
                    Attribute(id = "asd", valueName = "value", name = "name"),
                    Attribute(id = "asd", valueName = "value", name = "name")
                )
            )
        )
    )
}