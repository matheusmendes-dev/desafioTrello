package com.mendev.trello.common.components

import android.app.Activity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mendev.trello.presentation.ui.theme.DesafioTrelloTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    title: String,
    navController: NavController = rememberNavController()
) {
    val context = LocalContext.current
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                if (!navController.navigateUp()) {
                    (context as Activity?)?.apply { finish() }
                }
            }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
            }
        },
        title = { Text(title) }
    )
}

@Preview
@Composable
private fun Preview() {
    DesafioTrelloTheme {
        MyTopAppBar("Desafio Trello")
    }
}