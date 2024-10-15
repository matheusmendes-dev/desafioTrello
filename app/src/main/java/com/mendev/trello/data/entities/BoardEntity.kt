package com.mendev.trello.data.entities

import com.google.gson.annotations.SerializedName

data class BoardEntity(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("closed")
    val closed: Boolean,

    @SerializedName("prefs")
    val prefs: Prefs?
) {
    data class Prefs(
        @SerializedName("backgroundColor")
        val backgroundColor: String?
    )
}
