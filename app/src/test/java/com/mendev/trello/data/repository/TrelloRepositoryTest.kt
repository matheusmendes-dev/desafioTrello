package com.mendev.trello.data.repository

import com.mendev.trello.BaseTest
import com.mendev.trello.data.entities.BoardEntity
import com.mendev.trello.data.network.TrelloApi
import com.mendev.trello.data.network.exceptions.ApiException
import com.mendev.trello.domain.model.Board
import com.mendev.trello.domain.repository.TrelloRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

class TrelloRepositoryTest : BaseTest<TrelloRepository>() {

    @MockK
    private lateinit var trelloApi: TrelloApi

    override fun setupSUT() = TrelloRepositoryImpl(trelloApi)

    @Test
    fun `getBoards should return list of boards when API call is successful`() = runBlocking {
        // Arrange
        val apiBoards = listOf(BoardEntity(id = "1", name = "Board1", closed = false, prefs = null))
        val expectedBoards = listOf(Board(id = "1", name = "Board1", closed = false, backgroundColor = null))

        coEvery { trelloApi.getBoards() } returns Response.success(apiBoards)

        // Act
        val result = SUT.getBoards()

        // Assert
        assertEquals(expectedBoards, result)
        coVerify(exactly = 1) { trelloApi.getBoards() }
    }

    @Test(expected = ApiException.UnknownException::class)
    fun `getBoards should throw ApiException when API returns null body`(): Unit = runBlocking {
        // Arrange
        coEvery { trelloApi.getBoards() } returns Response.success(null)

        // Act
        SUT.getBoards()

        // Assert
        // Exceção é esperada, não precisa de assert adicional
    }

}