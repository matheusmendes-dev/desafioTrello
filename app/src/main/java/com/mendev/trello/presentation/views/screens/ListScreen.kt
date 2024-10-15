package com.mendev.trello.presentation.views.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.mendev.trello.common.components.MyScaffold

@Composable
fun ListScreen(
    navController: NavHostController,
    boardId: String
) {
    MyScaffold(
        title = "Listas",
        navController = navController
    ) {
        Text("Componente de Listas - Board ID: $boardId")
    }
}