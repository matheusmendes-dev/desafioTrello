package com.mendev.trello.domain.usecase

import com.mendev.trello.domain.model.Board

interface TrelloUseCase {

    suspend fun getBoards(): Result<List<Board>>

}