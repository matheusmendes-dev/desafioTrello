package com.mendev.trello.presentation.ui.navigation

interface AppRoute {
    val route: String
}

object TrelloFlowGraph : AppRoute {
    override val route = "trello_flow"

    object BoardsRoute: AppRoute {
        override val route = "boards"
    }

    object ListRoute: AppRoute {
        override val route = "list/{${Args.BOARD_ID}}"
        object Args {
            const val BOARD_ID = "boardId"
        }
    }

    object CardsRoute: AppRoute {
        override val route = "cards"
    }
}