package com.mendev.trello.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mendev.trello.presentation.ui.theme.DesafioTrelloTheme

@Composable
fun ListItemDataComponent(
    modifier: Modifier = Modifier,
    items: List<String>
) {
    LazyColumn(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(
            items = items,
            key = { index, item -> "$index-$item" }
        ) { index, item ->
            ItemDataComponent(
                modifier = Modifier.fillMaxWidth(),
                text = "$index - $item"
            )
        }
    }
}

@Composable
private fun ItemDataComponent(
    modifier: Modifier = Modifier,
    text: String
) {
    Card(
        modifier = modifier.height(70.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = Modifier.fillMaxHeight().padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = text)
        }
    }
}

@Preview
@Composable
private fun PreviewItems() {
    DesafioTrelloTheme {
        ListItemDataComponent(
            items = listOf("Item", "Item", "Item", "Item", "Item", "Item", "Item")
        )
    }
}