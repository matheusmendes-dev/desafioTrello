package com.mendev.trello.domain.usecase

import com.mendev.trello.BaseTest
import com.mendev.trello.domain.model.Board
import com.mendev.trello.domain.repository.TrelloRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TrelloUseCaseTest : BaseTest<TrelloUseCase>() {

    @MockK
    private lateinit var repository: TrelloRepository

    override fun setupSUT() = TrelloUseCaseImpl(repository)

    @Test
    fun `getBoards should return success result when repository returns list of boards`() = runBlocking {
        // Arrange
        val expectedBoards = listOf(mockk<Board>())

        // Simula que o repository retorna a lista de boards
        coEvery { repository.getBoards() } returns expectedBoards

        // Act
        val result = SUT.getBoards()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(expectedBoards, result.getOrNull())
        coVerify(exactly = 1) { repository.getBoards() }
    }

    @Test
    fun `getBoards should return failure result when repository throws exception`() = runBlocking {
        // Arrange
        val exception = RuntimeException("An error occurred")

        // Simula que o repository lança uma exceção
        coEvery { repository.getBoards() } throws exception

        // Act
        val result = SUT.getBoards()

        // Assert
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
        coVerify(exactly = 1) { repository.getBoards() }
    }

}