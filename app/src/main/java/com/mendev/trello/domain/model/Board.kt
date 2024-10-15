package com.mendev.trello.domain.model

data class Board(
    val id: String,
    val name: String,
    val closed: Boolean,
    val backgroundColor: String?
)
