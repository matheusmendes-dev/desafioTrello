package com.mendev.trello.domain.repository

import com.mendev.trello.domain.model.Board

interface TrelloRepository {

    suspend fun getBoards(): List<Board>

}