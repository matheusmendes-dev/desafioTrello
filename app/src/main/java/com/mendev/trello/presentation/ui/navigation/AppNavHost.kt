package com.mendev.trello.presentation.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.mendev.trello.presentation.views.screens.BoardsScreen
import com.mendev.trello.presentation.views.screens.ListScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = TrelloFlowGraph.route
    ) {
        trelloProcessGraph(navController)
    }
}

fun NavGraphBuilder.trelloProcessGraph(navController: NavHostController) {
    navigation(
        route = TrelloFlowGraph.route,
        startDestination = TrelloFlowGraph.BoardsRoute.route
    ) {
        composable(
            route = TrelloFlowGraph.BoardsRoute.route
        ) {
            BoardsScreen(navController)
        }

        composable(
            route = TrelloFlowGraph.ListRoute.route,
            arguments = listOf(
                navArgument(TrelloFlowGraph.ListRoute.Args.BOARD_ID) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val boardId = backStackEntry.arguments?.getString(TrelloFlowGraph.ListRoute.Args.BOARD_ID)
            ListScreen(
                navController = navController,
                boardId = boardId ?: ""
            )
        }

        composable(
            route = TrelloFlowGraph.CardsRoute.route
        ) {
            Text("Componente de Cards")
        }
    }
}