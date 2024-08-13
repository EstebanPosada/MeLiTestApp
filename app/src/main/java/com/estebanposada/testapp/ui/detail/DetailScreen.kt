package com.estebanposada.testapp.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.estebanposada.testapp.R
import com.estebanposada.testapp.domain.Attribute
import com.estebanposada.testapp.domain.item
import com.estebanposada.testapp.ui.theme.Banana
import com.estebanposada.testapp.ui.theme.GreenPigment
import com.estebanposada.testapp.ui.theme.Saffron

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
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold
        )
        Text(text = uiState.item.condition ?: "", color = GreenPigment)
        AsyncImage(
            modifier = modifier
                .height(200.dp)
                .fillMaxWidth(),
            model = uiState.item.thumbnail, contentDescription = uiState.item.title,
            placeholder = painterResource(id = R.drawable.ic_mercado_libre)
        )
        Text(
            text = stringResource(
                id = R.string.price_quantity,
                uiState.item.price.toString(),
                uiState.item.currency,
                uiState.item.availableQuantity
            ), fontSize = 30.sp, color = GreenPigment
        )
        Text(
            modifier = modifier.padding(10.dp),
            text = stringResource(id = R.string.attributes),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        uiState.item.attributes.forEachIndexed { index, attr ->
            val backgroundColor = if (index % 2 == 0) Saffron
            else Banana
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 3.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(backgroundColor)
                    .padding(horizontal = 10.dp)
                    .padding(),
                text = stringResource(
                    id = R.string.attribute_detail,
                    attr.name,
                    attr.valueName ?: ""
                ), fontSize = 18.sp
            )
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