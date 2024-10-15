package com.mendev.trello.presentation.views.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mendev.trello.common.components.MyScaffold
import com.mendev.trello.domain.model.Board
import com.mendev.trello.presentation.components.ListItemDataComponent
import com.mendev.trello.presentation.ui.theme.DesafioTrelloTheme
import com.mendev.trello.presentation.viewmodels.BoardsViewModel

@Composable
fun BoardsScreen(
    navController: NavHostController = rememberNavController()
) {
    val viewModel: BoardsViewModel = hiltViewModel()
    val stateScreen by viewModel.stateScreen.collectAsState()

    LaunchedEffect(Unit) { viewModel.getBoards() }

    MyScaffold(
        title = "Boards",
        navController = navController
    ) {
        when(stateScreen) {
            is StateScreen.Loading -> {
                Column (
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.height(60.dp).width(60.dp)
                    )
                }
            }
            is StateScreen.Success -> {
                val boards = (stateScreen as StateScreen.Success<List<Board>>).data
                ListItemDataComponent(
                    modifier = Modifier.fillMaxSize(),
                    items = boards.map { it.name }
                )
            }
            is StateScreen.Error -> {
                Text(text = (stateScreen as StateScreen.Error).message)
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    DesafioTrelloTheme {
        BoardsScreen()
    }
}