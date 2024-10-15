package com.mendev.trello.domain.model.extensions

import com.mendev.trello.data.entities.BoardEntity
import com.mendev.trello.domain.model.Board

fun List<BoardEntity>.toModel() =
    map { it.toModel() }

private fun BoardEntity.toModel() =
    Board(
        id = id,
        name = name,
        closed = closed
    )