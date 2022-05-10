package com.todo.app.data

import com.google.gson.annotations.SerializedName

data class TodoAssetData(
    @SerializedName("todo") val todos: List<Todo>
)