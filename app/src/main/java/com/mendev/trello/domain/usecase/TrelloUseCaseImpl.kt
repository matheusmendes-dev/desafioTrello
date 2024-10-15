package com.mendev.trello.domain.usecase

import com.mendev.trello.domain.model.Board
import com.mendev.trello.domain.repository.TrelloRepository
import javax.inject.Inject

class TrelloUseCaseImpl @Inject constructor(
    private val repository: TrelloRepository
) : TrelloUseCase {

    override suspend fun getBoards(): Result<List<Board>> {
        return try {
            val boards = repository.getBoards()
            Result.success(boards)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
