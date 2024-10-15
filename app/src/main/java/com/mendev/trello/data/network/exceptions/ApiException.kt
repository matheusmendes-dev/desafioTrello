package com.mendev.trello.data.network.exceptions

sealed class ApiException(message: String) : Exception(message) {
    class NetworkException : ApiException("Erro de conexão com a rede")
    class HttpException(val code: Int, message: String) : ApiException(message)
    class UnknownException : ApiException("Erro desconhecido")
}