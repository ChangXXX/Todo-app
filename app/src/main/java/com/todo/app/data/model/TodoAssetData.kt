package com.todo.app.data.model

import com.google.gson.annotations.SerializedName

data class TodoAssetData(
    @SerializedName("todo") val todos: List<Todo>
)