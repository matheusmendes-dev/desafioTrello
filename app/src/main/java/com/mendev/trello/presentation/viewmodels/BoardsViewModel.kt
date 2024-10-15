package com.mendev.trello.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mendev.trello.data.network.exceptions.ApiException
import com.mendev.trello.domain.model.Board
import com.mendev.trello.domain.usecase.TrelloUseCase
import com.mendev.trello.presentation.views.screens.StateScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BoardsViewModel @Inject constructor(
    private val useCase: TrelloUseCase
) : ViewModel() {

    private val _stateScreen = MutableStateFlow<StateScreen<List<Board>>>(StateScreen.Loading)
    val stateScreen = _stateScreen.asStateFlow()

    fun getBoards() {
        viewModelScope.launch {
            val result = useCase.getBoards()

            result.onSuccess { boards ->
                _stateScreen.value = StateScreen.Success(boards)
            }.onFailure { exception ->
                val messageError = when(exception) {
                    is ApiException.NetworkException -> "Erro de rede"
                    is ApiException.HttpException -> "Erro Http: ${exception.code} - {${exception.message}"
                    else -> "Erro desconhecido"
                }

                _stateScreen.value = StateScreen.Error(messageError)
            }
        }
    }

}