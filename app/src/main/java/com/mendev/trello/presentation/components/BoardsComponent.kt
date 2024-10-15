package com.mendev.trello.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mendev.trello.domain.model.Board
import com.mendev.trello.presentation.ui.theme.DesafioTrelloTheme

@Composable
fun BoardsComponent(
    modifier: Modifier = Modifier,
    items: List<Board>,
    onBoardSelected: (board: Board) -> Unit
) {
    LazyColumn(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(
            items = items,
            key = { index, item -> "$index-$item" }
        ) { index, board ->

            val backgroundColor = board.backgroundColor?.let {
                Color(android.graphics.Color.parseColor(it))
            } ?: MaterialTheme.colorScheme.surfaceVariant

            BoardItemComponent(
                modifier = Modifier.fillMaxWidth()
                    .background(backgroundColor)
                    .clickable { onBoardSelected(board) },
                text = board.name
            )
        }
    }
}

@Composable
private fun BoardItemComponent(
    modifier: Modifier = Modifier,
    text: String
) {
    Card(
        modifier = Modifier.height(70.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Column(
            modifier = modifier.fillMaxHeight().padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = text)
        }
    }
}

@Preview
@Composable
private fun PreviewItems() {
    val board = Board(
        id = "1",
        name = "Board",
        closed = false,
        backgroundColor = null
    )
    val boards = listOf(
        board.copy(backgroundColor = "#009900"),
        board.copy(name = "Board 2"),
        board.copy(backgroundColor = "#441155"),
        board,
    )
    DesafioTrelloTheme {
        BoardsComponent(
            items = boards
        ) {}
    }
}