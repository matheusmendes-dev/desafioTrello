package com.mendev.trello.presentation.views.screens

sealed class StateScreen <out T> {
    object Loading : StateScreen<Nothing>()
    data class Success<T>(val data: T): StateScreen<T>()
    data class Error(val message: String): StateScreen<Nothing>()
}