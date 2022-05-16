package com.todo.app.data.repository.remote

import com.todo.app.data.model.Todo

interface TodoRemDataSource {

    suspend fun addTodo(todo: Todo)

    suspend fun getTodos(): Map<String, Todo>
}