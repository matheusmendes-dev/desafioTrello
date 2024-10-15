package com.mendev.trello.data.repository

import com.mendev.trello.data.network.TrelloApi
import com.mendev.trello.data.network.exceptions.ApiException
import com.mendev.trello.domain.model.Board
import com.mendev.trello.domain.model.extensions.toModel
import com.mendev.trello.domain.repository.TrelloRepository
import com.mendev.trello.helpers.safeApiCall
import javax.inject.Inject

class TrelloRepositoryImpl @Inject constructor(
    private val trelloApi: TrelloApi
) : TrelloRepository {

    override suspend fun getBoards(): List<Board> {
        return safeApiCall {
            trelloApi.getBoards()
        }?.toModel() ?: throw ApiException.UnknownException()
    }

}