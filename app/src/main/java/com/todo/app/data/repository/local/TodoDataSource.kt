package com.todo.app.data.repository.local

import com.todo.app.data.model.Todo

interface TodoDataSource {

    suspend fun addTodo(todo: Todo)

    suspend fun getTodos(): List<Todo>
}