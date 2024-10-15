package com.mendev.trello.presentation.viewmodels

import com.mendev.trello.BaseTest
import com.mendev.trello.data.network.exceptions.ApiException
import com.mendev.trello.domain.model.Board
import com.mendev.trello.domain.usecase.TrelloUseCase
import com.mendev.trello.presentation.views.screens.StateScreen
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BoardsViewModelTest : BaseTest<BoardsViewModel>() {

    @MockK
    private lateinit var useCase: TrelloUseCase

    override fun setupSUT() = BoardsViewModel(useCase)

    @Test
    fun `getBoards should set state to Success when useCase returns success`() = runTest {
        // Arrange
        val boards = listOf(mockk<Board>())
        coEvery { useCase.getBoards() } returns Result.success(boards)

        // Act
        SUT.getBoards()
        advanceUntilIdle()

        // Assert
        val state = SUT.stateScreen.first()
        assertTrue(state is StateScreen.Success)
        assertEquals(boards, (state as StateScreen.Success).data)
    }

    @Test
    fun `getBoards should set state to Error when useCase returns NetworkException`() = runTest {
        // Arrange
        val exception = ApiException.NetworkException()
        coEvery { useCase.getBoards() } returns Result.failure(exception)

        // Act
        SUT.getBoards()
        advanceUntilIdle()

        // Assert
        val state = SUT.stateScreen.first()
        assertTrue(state is StateScreen.Error)
        assertEquals("Erro de rede", (state as StateScreen.Error).message)
    }

    @Test
    fun `getBoards should set state to Error when useCase returns HttpException`() = runTest {
        // Arrange
        val exception = ApiException.HttpException(404, "Not Found")
        coEvery { useCase.getBoards() } returns Result.failure(exception)

        // Act
        SUT.getBoards()
        advanceUntilIdle()

        // Assert
        val state = SUT.stateScreen.first()
        assertTrue(state is StateScreen.Error)
        assertEquals("Erro Http: 404 - {Not Found", (state as StateScreen.Error).message)
    }

    @Test
    fun `getBoards should set state to Error when useCase returns UnknownException`() = runTest {
        // Arrange
        val exception = ApiException.UnknownException()
        coEvery { useCase.getBoards() } returns Result.failure(exception)

        // Act
        SUT.getBoards()
        advanceUntilIdle()

        // Assert
        val state = SUT.stateScreen.first()
        assertTrue(state is StateScreen.Error)
        assertEquals("Erro desconhecido", (state as StateScreen.Error).message)
    }

}