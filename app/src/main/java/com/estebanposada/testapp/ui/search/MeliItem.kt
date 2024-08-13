package com.estebanposada.testapp.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.estebanposada.testapp.R
import com.estebanposada.testapp.domain.Item
import com.estebanposada.testapp.ui.resul.item


@Composable
fun MeLiItem(
    item: Item,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = modifier.weight(1f)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(item.thumbnail).build(),
                contentDescription = item.title,
                placeholder = painterResource(id = R.drawable.ic_mercado_libre),
                contentScale = ContentScale.Fit,
                modifier = modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.LightGray)
                    .aspectRatio(4 / 2f)
            )
        }
        Spacer(modifier = modifier.size(10.dp))
        Column(
            modifier = modifier
                .weight(2f),
            verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.End
        ) {
            Text(text = item.title, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(
                text = stringResource(
                    id = R.string.price_quantity,
                    item.price,
                    item.availableQuantity
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MeLiItemPreview() {
    MeLiItem(item = item)
}