package com.mendev.trello.data.network

import com.mendev.trello.data.entities.BoardEntity
import retrofit2.Response
import retrofit2.http.GET

interface TrelloApi {

    @GET("members/me/boards")
    suspend fun getBoards(): Response<List<BoardEntity>>

}