package com.mendev.trello.presentation.views.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun MyScaffold(
    title: String,
    navController: NavHostController = rememberNavController(),
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            MyTopAppBar(
                title = title,
                navController = navController
            )
        },
        content = {
            Surface(
                modifier = Modifier.fillMaxSize().padding(it)
            ) {
                content()
            }
        }
    )
}